package com.rong.src.study.util.compressFileImpl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import com.rong.src.study.util.AbstractCompressedFile;
import com.rong.src.study.util.Assert;
import com.rong.src.study.util.FileUtils;

/**
 * @author RongH
 *
 */
public class ZipFileUtil extends AbstractCompressedFile {

	private static final Log logger = LogFactory.getLog(ZipFileUtil.class);

	/**
	 * @Description 按照指定的编码格式解压文件到指定目录
	 * @author rongh
	 * @date 2018-05-17 09:29
	 * @Title: ZipFileService.java
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
	@Override
	public boolean ExpandCompressedFile(File compressedFile, String directory, String encoding) {
		if (Assert.isEmpty(encoding)) {
			encoding = DEFAULT_ENCODING;
		}
		BufferedInputStream bis = null;
		ZipFile zFile = null;
		try {
			zFile = new ZipFile(compressedFile, encoding);
			Enumeration<ZipEntry> entry = zFile.getEntries();
			while (entry.hasMoreElements()) {
				ZipEntry ze = entry.nextElement();
				if (ze.isDirectory()) {
					logger.debug("zip创建目录: " + directory + ze.getName());
					FileUtils.mkdir(directory + ze.getName());
				} else {
					logger.debug("zip写入文件: " + directory + ze.getName());
					bis = new BufferedInputStream(zFile.getInputStream(ze));
					WriteFileFromStream(bis, directory + ze.getName());
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException("解压文件失败: " + e.getMessage());
		} finally {
			try {
				if (zFile != null)
					zFile.close();
				if (bis != null)
					bis.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new RuntimeException("关闭文件流失败: " + e.getMessage());
			}
		}

		return true;

	}

	/**
	 * @Description 按照编码格式解压压缩包内的指定文件到指定目录中
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: ZipFileService.java
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
		if (Assert.isEmpty(encoding))
			encoding = DEFAULT_ENCODING;
		logger.debug("解压单个文件编码: " + encoding);
		BufferedInputStream bis = null;
		String innerFileName = getFileName(innerFileRelativePath);
		ZipFile zFile = null;
		try {
			zFile = new ZipFile(compressedFile, encoding);
			Enumeration<ZipEntry> entry = zFile.getEntries();
			while (entry.hasMoreElements()) {
				ZipEntry ze = entry.nextElement();
				if (innerFileRelativePath.equals(ze.getName())) {
					logger.debug("zip写入文件: " + directory + innerFileName);
					bis = new BufferedInputStream(zFile.getInputStream(ze));
					WriteFileFromStream(bis, directory + innerFileName);
					break;
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException("获取" + compressedFile + "文件流失败: " + e.getMessage());
		} finally {
			try {
				if (zFile != null) {
					zFile.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new RuntimeException("关闭文件流失败: " + e.getMessage());
			}
		}
		return true;
	}

	/**
	 * @Description 按照编码格式解压压缩包内的指定目录到指定目录中
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: ZipFileService.java
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
		if (Assert.isEmpty(encoding))
			encoding = DEFAULT_ENCODING;
		logger.debug("解压单个目录编码: " + encoding);
		BufferedInputStream bis = null;
		ZipFile zFile = null;
		try {
			zFile = new ZipFile(compressedFile, encoding);
			Enumeration<ZipEntry> entry = zFile.getEntries();
			while (entry.hasMoreElements()) {
				ZipEntry ze = entry.nextElement();
				if (ze.getName().startsWith(innerDirectoryPath)) {
					String fileName = ze.getName().replaceFirst(innerDirectoryPath, "");
					if (ze.isDirectory()) {
						logger.debug("zip创建目录: " + directory + fileName);
						FileUtils.mkdir(directory + fileName);
					} else {
						logger.debug("zip写入文件: " + directory + fileName);
						bis = new BufferedInputStream(zFile.getInputStream(ze));
						WriteFileFromStream(bis, directory + fileName);
					}
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException("获取" + compressedFile + "文件流失败: " + e.getMessage());
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (zFile != null) {
					zFile.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new RuntimeException("关闭文件流失败: " + e.getMessage());
			}
		}
		return true;
	}

	/**
	 * @Description 按照编码格式递归压缩文件到指定的目录里
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: ZipFileService.java
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
		if (Assert.isEmpty(encoding))
			encoding = DEFAULT_ENCODING;
		ZipOutputStream zos = null;
		try {
			File zip_file = new File(directFilePath);
			logger.debug("zip递归压缩成指定文件:" + directFilePath);
			zip_file.getParentFile().mkdirs();
			zos = new ZipOutputStream(new FileOutputStream(zip_file));
			zos.setEncoding(encoding);
			logger.debug("zip递归压缩编码: " + encoding);
			getZipFile(file, zos, "");
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException("压缩" + file.getAbsolutePath() + " 文件失败: " + e.getMessage());
		} finally {
			try {
				if (Assert.notEmpty(zos)) {
					zos.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new RuntimeException("zip关闭文件流失败: " + e.getMessage());
			}
		}
		return true;
	}

	/**
	 * @Description 递归压缩文件
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: ZipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @return void
	 */
	private void getZipFile(File source_file, ZipOutputStream zos, String dir_in_zip) throws IOException {
		FileInputStream fi = null;
		try {
			String file_path_in_zip = dir_in_zip + source_file.getName();
			if (source_file.isDirectory()) {
				file_path_in_zip += "/";
				if (Assert.notEmpty(source_file.listFiles())) {
					logger.debug("zip递归目录: " + source_file.getName());
					for (File file_in_dir : source_file.listFiles()) {
						getZipFile(file_in_dir, zos, file_path_in_zip);
					}
				} else {
					logger.debug("zip写入空目录: " + file_path_in_zip);
					ZipEntry entry = new ZipEntry(file_path_in_zip);
					entry.setSize(source_file.length());
					entry.setUnixMode(755);
					zos.putNextEntry(entry);
					zos.closeEntry();
					zos.flush();
				}
			} else {
				logger.debug("zip写入文件: " + file_path_in_zip);
				ZipEntry entry = new ZipEntry(file_path_in_zip);
				entry.setSize(source_file.length());
				entry.setUnixMode(644);
				zos.putNextEntry(entry);
				fi = new FileInputStream(source_file);
				byte[] data = new byte[BUFF];
				int length = 0;
				while ((length = fi.read(data, 0, BUFF)) != -1) {
					zos.write(data, 0, length);
				}
				zos.closeEntry();
				zos.flush();
			}
		} finally {
			if (Assert.notEmpty(fi)) {
				fi.close();
			}
		}
	}
}
