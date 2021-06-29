package dataImportv2;

import enums.Pairs;
import enums.Tics;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;



/**
 * generate() returns URL for specified zip file
 */
public class DownloadUtility {
    protected static String generateURL(Pairs pair, Tics tic, String date) {
        String url;
        url = ImportConstants.BASE_DOWNLOAD_URL + pair.getName() + "/" + tic.getName() + "/" + pair.getName() + "-" + tic.getName() + "-" + date + ".zip";
        return url;
    }
    protected static boolean download(String url) {
        try {
            Files.copy(new URL(url).openStream(), Paths.get(ImportConstants.ZIP_FILE_NAME), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}