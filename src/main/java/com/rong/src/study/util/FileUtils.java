/**
 * @Description TODO
 * @author rongh
 * @date 2019-08-07 14:52
 * @Title: FileUtil.java
 * @Company: CORSWORK
 * @Copyright: Copyright (c) 2018
 */
package com.rong.src.study.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description 文件工具类
 * @author rongh
 * @Copyright: Copyright (c) 2018
 */
public class FileUtils {
	public static void deleteFile(String file) {
		deleteFile(new File(file));
	}

	public static void deleteFile(File file) {
		if ((!file.delete()) && (file.exists())) {
			try {
				OutputStream out = new FileOutputStream(file);
				out.close();
			} catch (IOException localIOException) {
			}
		}
	}

	public static String filePathCvt(String pathFile) {
		Assert.assertNotEmpty(pathFile, "pathFile");
		return pathFile.replace("\\", "/");
	}

	public static String getFilePath(String file) {
		Assert.assertNotEmpty(file, "file");
		String _file = filePathCvt(file);
		String path = ".";
		int index = _file.lastIndexOf("/");
		if (index > 0) {
			path = _file.substring(0, index + 1);
		}
		return path;
	}

	public static String getFileName(String file) {
		Assert.assertNotEmpty(file, "file");
		String _file = filePathCvt(file);
		int index = _file.lastIndexOf("/");
		if (index > 0) {
			return _file.substring(index + 1);
		}
		return _file;
	}

	public static String getFileType(String file) {
		file = getFileName(file);
		int i = file.lastIndexOf('.');
		if (i >= 0) {
			return file.substring(i + 1);
		}
		return file;
	}

	public static File getFile(String pathFile) {
		Assert.assertNotEmpty(pathFile, "pathFile");
		return new File(pathFile);
	}

	public static void mkdir(File file) {
		Assert.assertNotEmpty(file, "file");
		String p = getFilePath(file.getPath());
		File f = getFile(p);
		if (!f.exists()) {
			f.mkdirs();
		}
	}

	public static void mkdir(String path) {
		mkdir(getFile(path + "/inner$$$.tmp"));
	}

	public static File createFile(String pathFile) {
		return createFile(getFile(pathFile));
	}

	public static File createFile(File file) {
		Assert.assertNotEmpty(file, "file");
		mkdir(file);
		return FileUtils.createFile(file);
	}

	public static String editDirPath(String dirPath) {
		if (!dirPath.endsWith("/")) {
			dirPath = dirPath + "/";
		}
		return dirPath;
	}
}
