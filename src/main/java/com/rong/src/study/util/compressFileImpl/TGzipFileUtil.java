package com.rong.src.study.util.compressFileImpl;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rong.src.study.util.AbstractCompressedFile;
import com.rong.src.study.util.Assert;
import com.rong.src.study.util.FileUtils;


/**
 * @author RongH
 *
 */
public class TGzipFileUtil extends AbstractCompressedFile {
	private static final Log logger = LogFactory.getLog(TGzipFileUtil.class);
	/**
	 * @Description 按照指定的编码格式解压文件到指定目录
	 * @author RongH
	 * @date 2018年5月4日
	 * @Copyright: Copyright (c) 2018
	 * @param tGzipFile
	 *            需要解压的压缩文件
	 * @param directory
	 *            解压指定目录
	 * @param encoding
	 *            编码格式
	 * @return 解压是否完成
	 */
	@Override
	public boolean ExpandCompressedFile(File tGzipFile, String directory, String encoding) {
		boolean flag = false;
		String tarFile = null;
		try {
			// 将tar.gz文件先解压为tar文件
			tarFile = unGzipFile(tGzipFile, directory,encoding);
			logger.info("生成临时的tar包路径： " + tarFile);
			// 解压tar文件
			flag = unTarFile(new File(tarFile), directory, encoding);
			logger.info("解压tar文件： " + tarFile + "到 "+ directory);
		} finally {
			// 删除临时生成的tar文件
			logger.info("刪除临时的tar包路径： " + tarFile);
			if (Assert.notEmpty(tarFile))
				FileUtils.deleteFile(tarFile);
		}
		return flag;
	}

	/**
	 * @Description 按照编码格式解压压缩包内的指定文件到指定目录中
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: TGzipFileService.java
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
		boolean flag = false;
		String tarFile = null;
		try {
			// 将tar.gz文件先解压为tar文件
			tarFile = unGzipFile(compressedFile, directory,encoding);
			logger.info("生成临时的tar包路径： " + tarFile);
			// 解压tar文件中指定的文件
			flag = unTarSingleFile(new File(tarFile), directory, encoding, innerFileRelativePath);
			logger.info("解压tar文件中指定文件" + innerFileRelativePath+"到： " + tarFile + "到 "+ directory);
		} finally {
			// 删除临时生成的tar文件
			logger.info("刪除临时的tar包路径： " + tarFile);
			if (Assert.notEmpty(tarFile))
				FileUtils.deleteFile(tarFile);
		}
		return flag;
	}

	/**
	 * @Description 按照编码格式解压压缩包内的指定目录到指定目录中
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: TGzipFileService.java
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
		boolean flag = false;
		String tarFile = null;
		try {
			// 将tar.gz文件先解压为tar文件
			tarFile = unGzipFile(compressedFile, directory, encoding);
			logger.info("生成临时的tar包路径： " + tarFile);
			// 解压tar文件中指定的文件
			flag = unTarSingleDirectory(new File(tarFile), directory, encoding, innerDirectoryPath);
			logger.info("解压tar文件中指定目录" + innerDirectoryPath+"到： " + tarFile + "到 "+ directory);
		} finally {
			// 删除临时生成的tar文件
			logger.info("刪除临时的tar包路径： " + tarFile);
			if (Assert.notEmpty(tarFile))
				FileUtils.deleteFile(tarFile);
		}
		return flag;
	}

	/**
	 * @Description 按照编码格式压缩文件到指定的目录里
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: TGzipFileService.java
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
		boolean flag = false;
		String tarFile = null;
		try {
			tarFile = compressedTarFile(directFilePath, file, encoding);
			logger.info("生成临时的tar包路径： " + tarFile);
			flag = compressedGzFile(directFilePath, new File(tarFile), encoding);
			logger.info("压缩临时的tar包成.tar.gz文件： " + tarFile+"到指定目录： "+directFilePath);
		} finally {
			logger.info("刪除临时的tar包路径： " + tarFile);
			// 删除临时生成的tar文件
			if (Assert.notEmpty(tarFile))
				FileUtils.deleteFile(tarFile);
		}
		return flag;
	}

	/**
	 * 
	 * @Description 将.tar.gz解压成.tar文件
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: TGzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @return String .tar文件路径
	 */
	private String unGzipFile(File compressedFile, String directory, String encoding) {
		String fileName = getFileName(compressedFile.getAbsolutePath());
		if (new GzipFileUtil().ExpandCompressedFile(compressedFile, directory, encoding)) {
			return directory + fileName.substring(0, fileName.lastIndexOf('.'));
		} else {
			throw new RuntimeException("解压" + compressedFile.getAbsolutePath() + "文件失败！");
		}
	}

	/**
	 * 
	 * @Description 解压tar文件
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: TGzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 */
	private boolean unTarFile(File compressedFile, String directory, String encoding) {
		return new TarFileUtil().ExpandCompressedFile(compressedFile, directory, encoding);
	}

	/**
	 * 
	 * @Description 解压tar文件中指定的文件
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: TGzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 */
	private boolean unTarSingleFile(File compressedFile, String directory, String encoding,
			String innerFileRelativePath) {
		return new TarFileUtil().ExpandSingleFile(compressedFile, directory, encoding, innerFileRelativePath);
	}

	/**
	 * 
	 * @Description 解压tar文件中指定的目录
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: TGzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 */
	private boolean unTarSingleDirectory(File compressedFile, String directory, String encoding,
			String innerDirectoryPath) {
		return new TarFileUtil().ExpandSingleDirectory(compressedFile, directory, encoding, innerDirectoryPath);
	}

	/**
	 * @Description 将目录打包为.tar文件，以便压缩成.tar.gz
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: TGzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @return String
	 */
	private String compressedTarFile(String directFilePath, File file, String encoding) {
		if (new TarFileUtil().CompressedFile(directFilePath.replace(".gz", ""), file, encoding)) {
			return directFilePath.replace(".gz", "");
		} else {
			throw new RuntimeException("压缩" + file.getAbsolutePath() + "文件失败！");
		}
	}

	/**
	 * @Description 将.tar文件压缩成.tar.gz
	 * @author RongH
	 * @date 2018年5月4日
	 * @Title: TGzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @return String
	 */
	private boolean compressedGzFile(String directFilePath, File tarFile, String encoding) {
		return new GzipFileUtil().CompressedFile(directFilePath, tarFile, encoding);
	}
}
