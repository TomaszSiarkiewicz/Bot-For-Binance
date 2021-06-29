package dataImportv2;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static dataImportv2.ImportConstants.CSV_FILE_NAME;
import static dataImportv2.ImportConstants.ZIP_FILE_NAME;

public class FilesUnziper {
    public void unzipFiles()  {
        ZipFile zipFile = new ZipFile(ZIP_FILE_NAME);
        try {
            zipFile.extractFile(zipFile.getFileHeaders().get(0), System.getProperty("user.dir"), CSV_FILE_NAME);
        } catch (ZipException e) {
            e.printStackTrace();
        }

    }
}



