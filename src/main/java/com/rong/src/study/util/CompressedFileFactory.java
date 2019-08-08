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
	 * @Description ��ȡʵ������
	 * @author rongh
	 * @Copyright: Copyright (c) 2018
	 * @param filePath
	 * @return
	 */
	public static AbstractCompressedFile getCompressedFileBean(String filePath) {
		Assert.assertNotEmpty(filePath, "�ļ�·��");
		return getInstance(filePath);
	}

	/**
	 * @Description �����ļ����ͻ�ȡ��Ӧ��ʵ��
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
			throw new RuntimeException("��ѡ��tar,zip,gz���͵�ѹ���ļ������ϴ�");
		}
	}

}
