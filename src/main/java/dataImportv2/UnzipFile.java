package dataImportv2;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class UnzipFile {
    public boolean unzipFiles(String fileName) {

        try {

            int BUFFER_SIZE = 2048;
            ZipFile zipFile = new ZipFile(fileName);

            ZipInputStream zipIn = new ZipInputStream(new FileInputStream(fileName));
            ZipEntry entry = zipIn.getNextEntry();

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(ImportConstants.CSV_FILE_NAME));
            byte[] bytesIn = new byte[BUFFER_SIZE];
            int read = 0;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
            bos.close();
            zipIn.closeEntry();
            zipIn.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
