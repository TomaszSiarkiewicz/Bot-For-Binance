package dataImport;

import dataImport.downloadData.*;
import enums.Pairs;
import enums.Tics;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
/**
 * Gets enums pair and tics, date
 * fetches data to database for pairs and tics chosen as enums from given date
 * data downloaded from binance.com
 */






public class Importer {

    public void fetchData(Pairs pair, LocalDate dateFrom, Tics tic) throws IOException {

        List<String> urls = DataAvilable.dataAvailable(dateFrom, tic, pair);
        List<String> unzipedFilesList = UnzipFile.unzipedFiles(urls);

        DataSaver dataSaver = new DataSaver();
        dataSaver.save(pair, tic, unzipedFilesList);

    }
}
