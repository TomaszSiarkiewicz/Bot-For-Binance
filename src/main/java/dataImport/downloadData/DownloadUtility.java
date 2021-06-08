package dataImport.downloadData;

import enums.Pairs;
import enums.Tics;

public class DownloadUtility {
    protected static String generate(Pairs pair, Tics tic, String date) {
        String url;
        url = "https://data.binance.vision/data/spot/daily/klines/" + pair.getName() + "/" + tic.getName() + "/" + pair.getName() + "-" + tic.getName() + "-" + date + ".zip";
        System.out.println(url);
        return url;
    }
        public static boolean isURL(String url) {
        try {
            (new java.net.URL(url)).openStream().close();
            return true;
        } catch (Exception ex) { }
        return false;
    }

}
//  https://data.binance.vision/data/spot/daily/klines/AIONUSDT/1m/AIONUSDT-1m-2021-05-14.zip
//  https://data.binance.vision/data/spot/daily/klines/AIONUSDT/1m/AIONUSDT-2021-05-11.zip