package com.rong.src.study.util.compressFileImpl;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rong.src.study.util.AbstractCompressedFile;
import com.rong.src.study.util.Assert;
import com.rong.src.study.util.CompressedFileFactory;
import com.rong.src.study.util.FileUtils;

/**
 * @author RongH
 *
 */
public class CompressedFileUtil {
	private final static Log logger = LogFactory.getLog(CompressedFileUtil.class);

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
	 * @throws FileNotFoundException
	 */
	public static boolean ExpandCompressedFile(File compressedFile, String directory, String encoding)
			throws FileNotFoundException {
		logger.debug("编码格式为:-----------------" + encoding + "------------------");
		Assert.assertNotEmpty(directory, "解压指定路径");
		FileUtils.mkdir(directory);
		logger.debug("解压路径是否存在： " + new File(directory).exists());
		if (!compressedFile.exists()) {
			throw new FileNotFoundException(compressedFile.getAbsolutePath());
		}
		AbstractCompressedFile service = CompressedFileFactory.getCompressedFileBean(compressedFile.getAbsolutePath());
		return service.ExpandCompressedFile(compressedFile, FileUtils.editDirPath(directory), encoding);
	}

	/**
	 * @Description 按照编码格式解压压缩包内的指定文件到指定目录中
	 * @author rongh
	 * @date 2018-05-17 09:20
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
	 * @throws FileNotFoundException
	 */
	public static boolean ExpandSingleFile(File compressedFile, String directory, String encoding, String innerFilePath)
			throws FileNotFoundException {
		logger.debug("编码格式为:-----------------" + encoding + "------------------");
		Assert.assertNotEmpty(directory, "解压文件路径");
		FileUtils.mkdir(directory);
		logger.debug("解压路径是否存在： " + new File(directory).exists());
		Assert.assertNotEmpty(innerFilePath, "压缩包内指定文件路径");
		if (!compressedFile.exists()) {
			throw new FileNotFoundException(compressedFile.getAbsolutePath());
		}
		AbstractCompressedFile service = CompressedFileFactory.getCompressedFileBean(compressedFile.getAbsolutePath());
		return service.ExpandSingleFile(compressedFile, FileUtils.editDirPath(directory), encoding, innerFilePath);

	}

	/**
	 * @Description 按照编码格式解压压缩包内的指定目录到指定目录中
	 * @author rongh
	 * @date 2018-05-17 09:21
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
	 * @throws FileNotFoundException
	 */
	public static boolean ExpandSingleDirectory(File compressedFile, String directory, String encoding,
			String innerDirectoryPath) throws FileNotFoundException {
		logger.debug("编码格式为:-----------------" + encoding + "------------------");
		Assert.assertNotEmpty(directory, "解压文件路径");
		FileUtils.mkdir(directory);
		logger.debug("解压路径是否存在： " + new File(directory).exists());
		Assert.assertNotEmpty(innerDirectoryPath, "压缩包内指定目录路径");
		if (!compressedFile.exists()) {
			throw new FileNotFoundException(compressedFile.getAbsolutePath());
		}
		AbstractCompressedFile service = CompressedFileFactory.getCompressedFileBean(compressedFile.getAbsolutePath());
		return service.ExpandSingleDirectory(compressedFile, FileUtils.editDirPath(directory), encoding,
				FileUtils.editDirPath(innerDirectoryPath));

	}

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
	 * @throws FileNotFoundException
	 */
	public static boolean CompressedFile(String directFilePath, File file, String encoding)
			throws FileNotFoundException {
		logger.debug("编码格式为:-----------------" + encoding + "------------------");
		Assert.assertNotEmpty(directFilePath, "压缩目标文件名全路径");
		if (!file.exists()) {
			throw new FileNotFoundException(file.getAbsolutePath());
		}
		AbstractCompressedFile service = CompressedFileFactory.getCompressedFileBean(directFilePath);
		return service.CompressedFile(directFilePath, file, encoding);
	}
}
