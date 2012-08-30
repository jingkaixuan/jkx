package net.jkx.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {
	public static boolean createFile(File fileName) throws IOException {
		File parent = fileName.getParentFile();
		if (parent.exists()) {
			return fileName.createNewFile();
		}
		else {
			parent.mkdirs();
			return fileName.createNewFile();
		}
	}
	
	public static boolean createFile(String fileName) throws IOException {
		return createFile(new File(fileName));
	}
	
	public static File createSubFile(File folder, String subFileName, boolean createWhenAbsent) throws IOException {
		File value = null;
		if(!folder.exists() && createWhenAbsent) {
			FileUtil.createFile(folder);
		}
		if(folder.isDirectory()) {
			StringBuilder sb = new StringBuilder(folder.getAbsolutePath());
			sb.append(File.separator);
			sb.append(subFileName);
			value = new File(sb.toString());
		}
		return value;
	}
}
