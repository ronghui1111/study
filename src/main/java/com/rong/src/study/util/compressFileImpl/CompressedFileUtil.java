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
	 * @Description ����ָ���ı����ʽ��ѹ�ļ���ָ��Ŀ¼
	 * @author rongh
	 * @date 2018-05-17 09:17
	 * @Copyright: Copyright (c) 2018
	 * @param compressedFile
	 *            ��Ҫ��ѹ��ѹ���ļ�
	 * @param directory
	 *            ��ѹָ��Ŀ¼
	 * @param encoding
	 *            �����ʽ
	 * @return ��ѹ�Ƿ����
	 * @throws FileNotFoundException
	 */
	public static boolean ExpandCompressedFile(File compressedFile, String directory, String encoding)
			throws FileNotFoundException {
		logger.debug("�����ʽΪ:-----------------" + encoding + "------------------");
		Assert.assertNotEmpty(directory, "��ѹָ��·��");
		FileUtils.mkdir(directory);
		logger.debug("��ѹ·���Ƿ���ڣ� " + new File(directory).exists());
		if (!compressedFile.exists()) {
			throw new FileNotFoundException(compressedFile.getAbsolutePath());
		}
		AbstractCompressedFile service = CompressedFileFactory.getCompressedFileBean(compressedFile.getAbsolutePath());
		return service.ExpandCompressedFile(compressedFile, FileUtils.editDirPath(directory), encoding);
	}

	/**
	 * @Description ���ձ����ʽ��ѹѹ�����ڵ�ָ���ļ���ָ��Ŀ¼��
	 * @author rongh
	 * @date 2018-05-17 09:20
	 * @Copyright: Copyright (c) 2018
	 * @param compressedFile
	 *            ѹ���ļ�
	 * @param directory
	 *            ��ѹָ��Ŀ¼
	 * @param encoding
	 *            �����ʽ
	 * @param innerFileRelativePath
	 *            ѹ������ָ���ļ������·��
	 * @return �Ƿ��ѹ���
	 * @throws FileNotFoundException
	 */
	public static boolean ExpandSingleFile(File compressedFile, String directory, String encoding, String innerFilePath)
			throws FileNotFoundException {
		logger.debug("�����ʽΪ:-----------------" + encoding + "------------------");
		Assert.assertNotEmpty(directory, "��ѹ�ļ�·��");
		FileUtils.mkdir(directory);
		logger.debug("��ѹ·���Ƿ���ڣ� " + new File(directory).exists());
		Assert.assertNotEmpty(innerFilePath, "ѹ������ָ���ļ�·��");
		if (!compressedFile.exists()) {
			throw new FileNotFoundException(compressedFile.getAbsolutePath());
		}
		AbstractCompressedFile service = CompressedFileFactory.getCompressedFileBean(compressedFile.getAbsolutePath());
		return service.ExpandSingleFile(compressedFile, FileUtils.editDirPath(directory), encoding, innerFilePath);

	}

	/**
	 * @Description ���ձ����ʽ��ѹѹ�����ڵ�ָ��Ŀ¼��ָ��Ŀ¼��
	 * @author rongh
	 * @date 2018-05-17 09:21
	 * @Copyright: Copyright (c) 2018
	 * @param compressedFile
	 *            ѹ���ļ�
	 * @param directory
	 *            ��ѹָ��Ŀ¼
	 * @param encoding
	 *            �����ʽ
	 * @param innerDirectoryRelativePath
	 *            ѹ������ָ��Ŀ¼�����·��
	 * @return
	 * @throws FileNotFoundException
	 */
	public static boolean ExpandSingleDirectory(File compressedFile, String directory, String encoding,
			String innerDirectoryPath) throws FileNotFoundException {
		logger.debug("�����ʽΪ:-----------------" + encoding + "------------------");
		Assert.assertNotEmpty(directory, "��ѹ�ļ�·��");
		FileUtils.mkdir(directory);
		logger.debug("��ѹ·���Ƿ���ڣ� " + new File(directory).exists());
		Assert.assertNotEmpty(innerDirectoryPath, "ѹ������ָ��Ŀ¼·��");
		if (!compressedFile.exists()) {
			throw new FileNotFoundException(compressedFile.getAbsolutePath());
		}
		AbstractCompressedFile service = CompressedFileFactory.getCompressedFileBean(compressedFile.getAbsolutePath());
		return service.ExpandSingleDirectory(compressedFile, FileUtils.editDirPath(directory), encoding,
				FileUtils.editDirPath(innerDirectoryPath));

	}

	/**
	 * @Description ���ձ����ʽѹ���ļ���ָ����Ŀ¼��
	 * @author rongh
	 * @date 2018-05-17 09:19
	 * @Title: CompressedFileUtil.java
	 * @Copyright: Copyright (c) 2018
	 * @param directFilePath
	 *            ѹ��ָ��Ŀ¼
	 * @param file
	 *            ��Ҫѹ�����ļ�
	 * @param encoding
	 *            �����ʽ
	 * @return ѹ���Ƿ����
	 * @throws FileNotFoundException
	 */
	public static boolean CompressedFile(String directFilePath, File file, String encoding)
			throws FileNotFoundException {
		logger.debug("�����ʽΪ:-----------------" + encoding + "------------------");
		Assert.assertNotEmpty(directFilePath, "ѹ��Ŀ���ļ���ȫ·��");
		if (!file.exists()) {
			throw new FileNotFoundException(file.getAbsolutePath());
		}
		AbstractCompressedFile service = CompressedFileFactory.getCompressedFileBean(directFilePath);
		return service.CompressedFile(directFilePath, file, encoding);
	}
}
