package com.rong.src.study.util.compressFileImpl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rong.src.study.util.AbstractCompressedFile;
import com.rong.src.study.util.Assert;


/**
 * @author RongH
 *
 */
/**
 * @author rongh
 *
 */
public class GzipFileUtil extends AbstractCompressedFile {

	private static final Log logger = LogFactory.getLog(GzipFileUtil.class);

	/**
	 * @Description
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: GzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 */
	@Override
	public boolean ExpandCompressedFile(File compressedFile, String directory, String encoding) {
		return ExpandCompressedFile(compressedFile, directory);
	}

	/**
	 * @Description 按照编码格式解压压缩包内的指定文件到指定目录中
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: GzipFileService.java
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
	@Override
	public boolean ExpandSingleFile(File compressedFile, String directory, String encoding,
			String innerFileRelativePath) {
		String fileName = getFileName(compressedFile.getAbsolutePath());
		String innerFileName = getFileName(innerFileRelativePath);
		if (fileName.replace(".gz", "").equals(innerFileName)) {
			ExpandCompressedFile(compressedFile, directory);
		} else {
			throw new RuntimeException("gz压缩文件不支持读取压缩包中的部分文件！");
		}
		return true;
	}

	/**
	 * @Description 按照编码格式解压压缩包内的指定目录到指定目录中
	 * @author rongh
	 * @date 2018-05-17 09:33
	 * @Title: GzipFileService.java
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
	@Override
	public boolean ExpandSingleDirectory(File compressedFile, String directory, String encoding,
			String innerDirectoryPath) {
		throw new RuntimeException("gz压缩文件不支持读取压缩包中的目录！");
	}

	/**
	 * @Description 压缩文件到指定目录
	 * @author rongh
	 * @date 2018-05-17 09:23
	 * @Title: GzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @param directFilePath
	 *            压缩指定目录
	 * @param file
	 *            需要压缩的文件
	 * @param encoding
	 *            编码格式
	 * @return 压缩是否完成
	 */
	@Override
	public boolean CompressedFile(String directFilePath, File file, String encoding) {
		new File(directFilePath).getParentFile().mkdirs();
		return CompressedFile(directFilePath, file);
	}

	/**
	 * @Description 按照指定的编码格式解压文件到指定目录
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: GzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @param compressedFile
	 *            需要解压的压缩文件
	 * @param directory
	 *            解压指定目录
	 * @param encoding
	 *            编码格式
	 * @return 解压是否完成
	 */
	private boolean ExpandCompressedFile(File compressedFile, String directory) {
		String fileName = getFileName(compressedFile.getAbsolutePath());
		GZIPInputStream gzin = null;
		try {
			gzin = new GZIPInputStream(new FileInputStream(compressedFile));
			WriteFileFromStream(gzin, directory + fileName.substring(0, fileName.lastIndexOf('.')));
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(
					"解压" + compressedFile.getAbsolutePath() + "文件失败，请确认该压缩包的编码格式正确: " + e.getMessage());
		} finally {
			try {
				if (gzin != null)
					gzin.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new RuntimeException("关闭文件流失败: " + e.getMessage());
			}
		}
		return true;

	}

	/**
	 * @Description 压缩文件到指定目录
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: GzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @return boolean
	 */
	private boolean CompressedFile(String directFilePath, File file) {
		if (file.isDirectory()) {
			throw new RuntimeException("gz压缩不支持压缩目录！");
		}
		BufferedInputStream bis = null;
		GZIPOutputStream gos = null;
		try {
			gos = new GZIPOutputStream(new FileOutputStream(directFilePath));
			bis = new BufferedInputStream(new FileInputStream(file));
			byte[] buff = new byte[BUFF];
			int length = 0;
			while ((length = bis.read(buff, 0, BUFF)) != -1) {
				gos.write(buff, 0, length);
			}
			gos.flush();
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException("压缩" + file.getAbsolutePath() + " 文件失败: " + e.getMessage());
		} finally {
			try {
				if (Assert.notEmpty(gos))
					gos.close();
				if (Assert.notEmpty(bis))
					bis.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new RuntimeException("关闭文件流失败: " + e.getMessage());
			}
		}
		return true;

	}

}
