package com.aos.utils;

import java.io.*;
import java.util.zip.*;

public class ZipUtil {
	public static void zipFiles(String zipFileName, String... filePaths) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(zipFileName); ZipOutputStream zos = new ZipOutputStream(fos)) {

			for (String filePath : filePaths) {
				File fileToZip = new File(filePath);
				if (fileToZip.isDirectory()) {
					zipDirectory(fileToZip, fileToZip.getName(), zos);
				} else {
					zipFile(fileToZip, zos, "");
				}
			}
		}
	}

	private static void zipFile(File fileToZip, ZipOutputStream zos, String parentDirectoryName) throws IOException {
		if (fileToZip.isHidden()) {
			return;
		}

		try (FileInputStream fis = new FileInputStream(fileToZip)) {
			String zipEntryName = parentDirectoryName + fileToZip.getName();
			if (parentDirectoryName.isEmpty()) {
				zipEntryName = fileToZip.getName();
			}
			ZipEntry zipEntry = new ZipEntry(zipEntryName);
			zos.putNextEntry(zipEntry);

			byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zos.write(bytes, 0, length);
			}
		}
	}

	private static void zipDirectory(File folderToZip, String parentFolder, ZipOutputStream zos) throws IOException {
		if (folderToZip.isHidden()) {
			return;
		}

		File[] files = folderToZip.listFiles();
		if (files == null || files.length == 0) {
			zos.putNextEntry(new ZipEntry(parentFolder + "/"));
			zos.closeEntry();
			return;
		}

		for (File file : files) {
			if (file.isDirectory()) {
				zipDirectory(file, parentFolder + "/" + file.getName(), zos);
			} else {
				zipFile(file, zos, parentFolder + "/");
			}
		}
	}
}
