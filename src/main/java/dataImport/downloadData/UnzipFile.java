package dataImport.downloadData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class UnzipFile {
    public static List<String> unzipedFiles(List<String> urls) throws IOException {
        String zipFileName = "zip.tmp";
        List<String> unzippedFiles = new ArrayList<>();

        for (String url : urls) {
            int BUFFER_SIZE = 2048;
            new DownloadFile().download(url, zipFileName);
            ZipFile zipFile = new ZipFile(zipFileName);

            ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFileName));
            ZipEntry entry = zipIn.getNextEntry();
            unzippedFiles.add(entry.getName());

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(entry.getName()));
            byte[] bytesIn = new byte[BUFFER_SIZE];
            int read = 0;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
            bos.close();
            zipIn.closeEntry();
            zipIn.close();
        }

        return unzippedFiles;
    }

}
