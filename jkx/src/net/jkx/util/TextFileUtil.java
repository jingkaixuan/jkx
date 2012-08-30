package net.jkx.util;

import java.io.File;
import java.io.IOException;

import net.mindview.util.TextFile;

public class TextFileUtil {
	public static void copyTo(String fileIn, String fileOut,
			boolean deleteSource) throws IOException {
		File inFile = new File(fileIn);
		if (inFile.exists()) {
			if (inFile.isFile()) {
				String content = TextFile.read(fileIn);
				TextFile.write(fileOut, content);
			} else if(inFile.isDirectory()) {
				{
					File[] files = inFile.listFiles();
					for(File file : files) {
						if(file.isFile()) {
							StringBuilder sb = new StringBuilder(fileOut);
							sb.append(File.separator);
							sb.append(file.getName());
							FileUtil.createFile(sb.toString());
							copyTo(fileIn + File.separator + file.getName(), sb.toString(), deleteSource);
						} else if(file.isDirectory()) {
							StringBuilder sb = new StringBuilder(fileOut);
							sb.append(File.separator);
							sb.append(file.getName());
							File sbFile = new File(sb.toString());
							sbFile.mkdir();
							copyTo(fileIn + File.separator + file.getName(), sb.toString(), deleteSource);
						}
					}
				}
			}
		}
	}

	public static void copyTo(File fileIn, File fileOut, boolean deleteSource)
			throws IOException {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TextFileUtil.copyTo("lab/net/jkx/lab/designpattern/factory/", "E:/abc/", false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
