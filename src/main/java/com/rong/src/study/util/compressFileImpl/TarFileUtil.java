package com.rong.src.study.util.compressFileImpl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;
import org.apache.tools.tar.TarOutputStream;

import com.rong.src.study.util.AbstractCompressedFile;
import com.rong.src.study.util.Assert;
import com.rong.src.study.util.FileUtils;

/**
 * @author RongH
 *
 */
public class TarFileUtil extends AbstractCompressedFile {
	private final static Log logger = LogFactory.getLog(TarFileUtil.class);

	/**
	 * @Description 按照指定的编码格式解压文件到指定目录
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: TarFileServoce.java
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
		logger.debug("解压文件编码: " + encoding);
		TarInputStream tis = null;
		try {
			TarEntry te = null;
			tis = new TarInputStream((new FileInputStream(compressedFile)));
			while ((te = tis.getNextEntry()) != null) {
				if (te.isDirectory()) {
					logger.debug("tar创建目录: " + directory + te.getName());
					FileUtils.mkdir(directory + te.getName());
				} else {
					logger.debug("tar写入文件: " + directory + te.getName());
					WriteFileFromStream(tis, directory + te.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new RuntimeException("解压" + compressedFile + "文件失败，请确认该压缩包的编码格式正确: " + e.getMessage());
		} finally {
			try {
				if (tis != null) {
					tis.close();
				}
			} catch (Exception e) {
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
	 * @Title: TarFileServoce.java
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
		String innerFileName = getFileName(innerFileRelativePath);
		TarInputStream tis = null;
		try {
			tis = new TarInputStream((new FileInputStream(compressedFile)));
			TarEntry te = null;
			while ((te = tis.getNextEntry()) != null) {
				if (innerFileRelativePath.equals(te.getName())) {
					logger.debug("tar写入文件: " + directory + innerFileName);
					WriteFileFromStream(tis, directory + innerFileName);
					break;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException("解压" + compressedFile + "文件失败，请确认该压缩包的编码格式正确 ：" + e.getMessage());
		} finally {
			try {
				if (tis != null) {
					tis.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new RuntimeException("关闭文件流失败 ： " + e.getMessage());
			}
		}

		return true;
	}

	/**
	 * @Description 按照编码格式解压压缩包内的指定目录到指定目录中
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: TarFileService.java
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
		TarInputStream tis = null;
		try {
			tis = new TarInputStream((new FileInputStream(compressedFile)));
			TarEntry te = null;
			while ((te = tis.getNextEntry()) != null) {
				if (te.getName().startsWith(innerDirectoryPath)) {
					String fileName = te.getName().replaceFirst(innerDirectoryPath, "");
					if (te.isDirectory()) {
						logger.debug("tar创建目录: " + directory + fileName);
						FileUtils.mkdir(directory + fileName);
					} else {
						logger.debug("tar写入文件: " + directory + fileName);
						WriteFileFromStream(tis, directory + fileName);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException("解压" + compressedFile + "文件失败，请确认该压缩包的编码格式正确: " + e.getMessage());
		} finally {
			try {
				if (tis != null) {
					tis.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new RuntimeException("关闭文件流失败: " + e.getMessage());
			}
		}
		return true;
	}

	/**
	 * @Description 按照编码格式压缩文件到指定的目录里
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: TarFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @param directFilePath
	 *            压缩目标目录文件
	 * @param file
	 *            需要压缩的文件
	 * @param encoding
	 *            编码格式
	 * @return 压缩是否完成
	 */
	@Override
	public boolean CompressedFile(String directFilePath, File file, String encoding) {
		TarOutputStream tos = null;
		try {
			File tar_file = new File(directFilePath);
			logger.debug("tar递归压缩成指定文件: " + directFilePath);
			tar_file.getParentFile().mkdirs();
			tos = new TarOutputStream(new BufferedOutputStream(new FileOutputStream(tar_file)));
			tos.setLongFileMode(TarOutputStream.LONGFILE_GNU);
			getTarFile(file, tos, "");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException("压缩" + file.getAbsolutePath() + " 文件失败，请确认该压缩包的编码格式正确: " + e.getMessage());
		} finally {
			try {
				if (Assert.notEmpty(tos)) {
					tos.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new RuntimeException("关闭文件流失败: " + e.getMessage());
			}
		}
		return true;
	}

	/**
	 * 
	 * @Description 递归压缩文件
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: TarFileService.java
	 * @Copyright: Copyright (c) 2018
	 * @return void
	 */
	private static void getTarFile(File file, TarOutputStream tos, String fileName) throws IOException {
		BufferedInputStream bis = null;
		try {
			if (file.isDirectory()) {
				if (Assert.notEmpty(file.listFiles())) {
					logger.debug("tar递归目录: " + file.getName());
					for (File temp : file.listFiles()) {
						getTarFile(temp, tos, fileName + file.getName() + "/");
					}
				} else {
					logger.debug("tar写入空目录: " + fileName + file.getName() + "/");
					TarEntry entry = new TarEntry(fileName + file.getName() + "/");
					entry.setSize(file.length());
					tos.putNextEntry(entry);
					tos.closeEntry();
					tos.flush();
				}
			} else {
				logger.debug("tar写入文件: " + fileName + file.getName());
				TarEntry entry = new TarEntry(fileName + file.getName());
				entry.setSize(file.length());
				tos.putNextEntry(entry);
				bis = new BufferedInputStream(new FileInputStream(file));
				byte[] data = new byte[BUFF];
				int length = 0;
				while ((length = bis.read(data, 0, BUFF)) != -1) {
					tos.write(data, 0, length);
				}
				tos.closeEntry();
				tos.flush();
			}

		} finally {
			if (Assert.notEmpty(bis)) {
				bis.close();
			}
		}
	}

}
