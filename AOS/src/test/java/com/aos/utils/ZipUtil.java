package com.aos.utils;

import java.io.*;
import java.util.zip.*;

public class ZipUtil {
    public static void zipFiles(String zipFileName, String... filePaths) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipFileName);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            
            for (String filePath : filePaths) {
                File fileToZip = new File(filePath);
                if (fileToZip.isDirectory()) {
                    zipDirectory(fileToZip, fileToZip.getName(), zos);
                } else {
                    zipFile(fileToZip, zos);
                }
            }
        }
    }

    private static void zipFile(File fileToZip, ZipOutputStream zos) throws IOException {
        try (FileInputStream fis = new FileInputStream(fileToZip)) {
        	ZipEntry zipEntry = new ZipEntry(fileToZip.getPath());
            zos.putNextEntry(zipEntry);
            
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }
        }
    }

    private static void zipDirectory(File folderToZip, String folderName, ZipOutputStream zos) throws IOException {
        File[] files = folderToZip.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    zipDirectory(file, folderName + "/" + file.getName(), zos);
                } else {
                    zipFile(new File(folderName + "/" + file.getName()), zos);
                }
            }
        }
    }
}
