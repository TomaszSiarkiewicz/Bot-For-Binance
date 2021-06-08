package dataImport.downloadData;

import enums.Pairs;
import enums.Tics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DataAvilable {
    public static List<String> dataAvailable(LocalDate startDate, Tics tic , Pairs pair) {
        List<String> list = new ArrayList<>();
        LocalDate data = LocalDate.now().minusDays(1);
        while (data.isAfter(startDate)) {
            String url = DownloadUtility.generate(pair, tic, data.toString());
            if (DownloadUtility.isURL(url)){
                list.add(url);
            }

            data = data.minusDays(1);
        }


        return list;
    }
}
