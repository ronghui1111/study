package com.rong.src.study.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description 解压缩文件工具类
 * @author rongh
 * @date 2019-05-07 18:24
 * @Copyright: Copyright (c) 2018
 */
public abstract class AbstractCompressedFile {
	// 读取压缩文件默认的编码格式
	public static final String DEFAULT_ENCODING = "GBK";
	// IO流读取缓存大小
	public static final int BUFF = 1024 * 1024;

	/**
	 * @Description 按照指定的编码格式解压文件到指定目录
	 * @author rongh
	 * @date 2018-05-17 09:17
	 * @Copyright: Copyright (c) 2018
	 * @param compressedFile
	 *            需要解压的压缩文件
	 * @param directory
	 *            解压指定目录
	 * @param encoding
	 *            编码格式
	 * @return 解压是否完成
	 */
	public abstract boolean ExpandCompressedFile(File compressedFile, String directory, String encoding);

	/**
	 * @Description 按照编码格式压缩文件到指定的目录里
	 * @author rongh
	 * @date 2018-05-17 09:19
	 * @Title: CompressedFileUtil.java
	 * @Copyright: Copyright (c) 2018
	 * @param directFilePath
	 *            压缩指定目录
	 * @param file
	 *            需要压缩的文件
	 * @param encoding
	 *            编码格式
	 * @return 压缩是否完成
	 */
	public abstract boolean CompressedFile(String directFilePath, File file, String encoding);

	/**
	 * @Description 按照编码格式解压压缩包内的指定文件到指定目录中
	 * @author rongh
	 * @date 2018-05-17 09:20
	 * @Title: CompressedFileUtil.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @param compressedFile
	 *            压缩文件
	 * @param directory
	 *            解压指定目录
	 * @param encoding
	 *            编码格式
	 * @param innerFileRelativePath
	 *            压缩包内指定文件的相对路径
	 * @return 是否解压完成
	 */
	public abstract boolean ExpandSingleFile(File compressedFile, String directory, String encoding,
			String innerFileRelativePath);

	/**
	 * @Description 按照编码格式解压压缩包内的指定目录到指定目录中
	 * @author rongh
	 * @date 2018-05-17 09:21
	 * @Title: CompressedFileUtil.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @param compressedFile
	 *            压缩文件
	 * @param directory
	 *            解压指定目录
	 * @param encoding
	 *            编码格式
	 * @param innerDirectoryRelativePath
	 *            压缩包内指定目录的相对路径
	 * @return
	 */
	public abstract boolean ExpandSingleDirectory(File compressedFile, String directory, String encoding,
			String innerDirectoryRelativePath);

	/**
	 * @Description 将流写入到文件中
	 * @author rongh
	 * @date 2018-05-17 09:41
	 * @Title: CompressedFileUtil.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @param is
	 *            读入流
	 * @param filePath
	 *            写入文件的路径
	 * @throws IOException
	 */
	protected void WriteFileFromStream(InputStream is, String filePath) throws IOException {
		BufferedOutputStream bos = null;
		try {
			byte[] b = new byte[BUFF];
			int length = 0;
			new File(filePath).getParentFile().mkdirs();
			bos = new BufferedOutputStream(new FileOutputStream(filePath));
			while ((length = is.read(b, 0, BUFF)) != -1) {
				bos.write(b, 0, length);
			}
			bos.flush();
		} finally {
			if (Assert.isEmpty(bos))
				bos.close();
		}

	}

	protected String getFileName(String filePath) {
		String fileName = "";
		if (filePath.contains("\\")) {
			if (Assert.notEmpty(filePath.split("\\\\"))) {
				fileName = filePath.split("\\\\")[filePath.split("\\\\").length - 1];
			}
		} else {
			if (Assert.notEmpty(filePath.split("/"))) {
				fileName = filePath.split("/")[filePath.split("/").length - 1];
			}
		}
		return fileName;

	}
}
