package com.rong.src.study.util;

import com.rong.src.study.util.compressFileImpl.GzipFileUtil;
import com.rong.src.study.util.compressFileImpl.TGzipFileUtil;
import com.rong.src.study.util.compressFileImpl.TarFileUtil;
import com.rong.src.study.util.compressFileImpl.ZipFileUtil;

/**
 * @author RongH
 *
 */
public abstract class CompressedFileFactory {
	/**
	 * @Description 获取实例对象
	 * @author rongh
	 * @Copyright: Copyright (c) 2018
	 * @param filePath
	 * @return
	 */
	public static AbstractCompressedFile getCompressedFileBean(String filePath) {
		Assert.assertNotEmpty(filePath, "文件路径");
		return getInstance(filePath);
	}

	/**
	 * @Description 根据文件类型获取对应的实例
	 * @author rongh
	 * @Copyright: Copyright (c) 2018
	 * @param fileName
	 * @return
	 */
	private static AbstractCompressedFile getInstance(String fileName) {
		if (fileName.endsWith(".tar")) {
			return new TarFileUtil();
		} else if (fileName.endsWith(".zip")) {
			return new ZipFileUtil();
		} else if (fileName.endsWith(".tar.gz")) {
			return new TGzipFileUtil();
		} else if (fileName.endsWith(".gz")) {
			return new GzipFileUtil();
		} else {
			throw new RuntimeException("请选择tar,zip,gz类型的压缩文件进行上传");
		}
	}

}
