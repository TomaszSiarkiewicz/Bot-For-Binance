package dataImportv2;

import dataImportv2.csvParser.CsvParser;
import dataImportv2.indicators.IndicatorsCalculator;
import enums.Pairs;
import enums.Tics;
import hibernate.DbConnector;
import hibernate.entities.Pair_Candle;

import java.time.LocalDate;
import java.util.List;

public class DataImporter {
    private static List<Pair_Candle> list;
    private final int daysToDownload;
    private final Tics interval;
    private final Pairs pair;
    private final DbConnector dbConnector;
    private boolean succesfulDownload = true;
    private int daysDownloaded = 0;

    public DataImporter(int daysToDownload, Tics interval, Pairs pair) {
        this.daysToDownload = daysToDownload;
        this.interval = interval;
        this.pair = pair;
        dbConnector = new DbConnector();

    }

    public void fetch() {
        while (succesfulDownload && daysDownloaded <= daysToDownload) { // checks if last download was successful, if not there is no older data
            String date = LocalDate.now().minusDays(daysDownloaded + 2).toString(); //string with date for url generator
            String url = DownloadUtility.generateURL(pair, interval, date);
            succesfulDownload = DownloadUtility.download(url);  // downloads zip and sets succesfulDownload variable
            new FilesUnziper().unzipFiles();
            list = new CsvParser().getPair_candleList();
            IndicatorsCalculator ic = new IndicatorsCalculator(list, interval, pair);
            dbConnector.putIndicatorsInDatabase(ic.getTics());
            System.out.println(date + " " +succesfulDownload+ " " + url);
            daysDownloaded++;

        }
    }
}
