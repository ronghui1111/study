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
	 * @Description ����ָ���ı����ʽ��ѹ�ļ���ָ��Ŀ¼
	 * @author RongH
	 * @date 2018��5��4��
	 * @Copyright: Copyright (c) 2018
	 * @param tGzipFile
	 *            ��Ҫ��ѹ��ѹ���ļ�
	 * @param directory
	 *            ��ѹָ��Ŀ¼
	 * @param encoding
	 *            �����ʽ
	 * @return ��ѹ�Ƿ����
	 */
	@Override
	public boolean ExpandCompressedFile(File tGzipFile, String directory, String encoding) {
		boolean flag = false;
		String tarFile = null;
		try {
			// ��tar.gz�ļ��Ƚ�ѹΪtar�ļ�
			tarFile = unGzipFile(tGzipFile, directory,encoding);
			logger.info("������ʱ��tar��·���� " + tarFile);
			// ��ѹtar�ļ�
			flag = unTarFile(new File(tarFile), directory, encoding);
			logger.info("��ѹtar�ļ��� " + tarFile + "�� "+ directory);
		} finally {
			// ɾ����ʱ���ɵ�tar�ļ�
			logger.info("�h����ʱ��tar��·���� " + tarFile);
			if (Assert.notEmpty(tarFile))
				FileUtils.deleteFile(tarFile);
		}
		return flag;
	}

	/**
	 * @Description ���ձ����ʽ��ѹѹ�����ڵ�ָ���ļ���ָ��Ŀ¼��
	 * @author RongH
	 * @date 2018��5��4��
	 * @Title: TGzipFileService.java
	 * @Company: CORSWORK
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
	 */
	@Override
	public boolean ExpandSingleFile(File compressedFile, String directory, String encoding,
			String innerFileRelativePath) {
		boolean flag = false;
		String tarFile = null;
		try {
			// ��tar.gz�ļ��Ƚ�ѹΪtar�ļ�
			tarFile = unGzipFile(compressedFile, directory,encoding);
			logger.info("������ʱ��tar��·���� " + tarFile);
			// ��ѹtar�ļ���ָ�����ļ�
			flag = unTarSingleFile(new File(tarFile), directory, encoding, innerFileRelativePath);
			logger.info("��ѹtar�ļ���ָ���ļ�" + innerFileRelativePath+"���� " + tarFile + "�� "+ directory);
		} finally {
			// ɾ����ʱ���ɵ�tar�ļ�
			logger.info("�h����ʱ��tar��·���� " + tarFile);
			if (Assert.notEmpty(tarFile))
				FileUtils.deleteFile(tarFile);
		}
		return flag;
	}

	/**
	 * @Description ���ձ����ʽ��ѹѹ�����ڵ�ָ��Ŀ¼��ָ��Ŀ¼��
	 * @author RongH
	 * @date 2018��5��4��
	 * @Title: TGzipFileService.java
	 * @Company: CORSWORK
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
	 */
	@Override
	public boolean ExpandSingleDirectory(File compressedFile, String directory, String encoding,
			String innerDirectoryPath) {
		boolean flag = false;
		String tarFile = null;
		try {
			// ��tar.gz�ļ��Ƚ�ѹΪtar�ļ�
			tarFile = unGzipFile(compressedFile, directory, encoding);
			logger.info("������ʱ��tar��·���� " + tarFile);
			// ��ѹtar�ļ���ָ�����ļ�
			flag = unTarSingleDirectory(new File(tarFile), directory, encoding, innerDirectoryPath);
			logger.info("��ѹtar�ļ���ָ��Ŀ¼" + innerDirectoryPath+"���� " + tarFile + "�� "+ directory);
		} finally {
			// ɾ����ʱ���ɵ�tar�ļ�
			logger.info("�h����ʱ��tar��·���� " + tarFile);
			if (Assert.notEmpty(tarFile))
				FileUtils.deleteFile(tarFile);
		}
		return flag;
	}

	/**
	 * @Description ���ձ����ʽѹ���ļ���ָ����Ŀ¼��
	 * @author RongH
	 * @date 2018��5��4��
	 * @Title: TGzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @param directFilePath
	 *            ѹ��ָ��Ŀ¼
	 * @param file
	 *            ��Ҫѹ�����ļ�
	 * @param encoding
	 *            �����ʽ
	 * @return ѹ���Ƿ����
	 */
	@Override
	public boolean CompressedFile(String directFilePath, File file, String encoding) {
		boolean flag = false;
		String tarFile = null;
		try {
			tarFile = compressedTarFile(directFilePath, file, encoding);
			logger.info("������ʱ��tar��·���� " + tarFile);
			flag = compressedGzFile(directFilePath, new File(tarFile), encoding);
			logger.info("ѹ����ʱ��tar����.tar.gz�ļ��� " + tarFile+"��ָ��Ŀ¼�� "+directFilePath);
		} finally {
			logger.info("�h����ʱ��tar��·���� " + tarFile);
			// ɾ����ʱ���ɵ�tar�ļ�
			if (Assert.notEmpty(tarFile))
				FileUtils.deleteFile(tarFile);
		}
		return flag;
	}

	/**
	 * 
	 * @Description ��.tar.gz��ѹ��.tar�ļ�
	 * @author RongH
	 * @date 2018��5��4��
	 * @Title: TGzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @return String .tar�ļ�·��
	 */
	private String unGzipFile(File compressedFile, String directory, String encoding) {
		String fileName = getFileName(compressedFile.getAbsolutePath());
		if (new GzipFileUtil().ExpandCompressedFile(compressedFile, directory, encoding)) {
			return directory + fileName.substring(0, fileName.lastIndexOf('.'));
		} else {
			throw new RuntimeException("��ѹ" + compressedFile.getAbsolutePath() + "�ļ�ʧ�ܣ�");
		}
	}

	/**
	 * 
	 * @Description ��ѹtar�ļ�
	 * @author RongH
	 * @date 2018��5��4��
	 * @Title: TGzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 */
	private boolean unTarFile(File compressedFile, String directory, String encoding) {
		return new TarFileUtil().ExpandCompressedFile(compressedFile, directory, encoding);
	}

	/**
	 * 
	 * @Description ��ѹtar�ļ���ָ�����ļ�
	 * @author RongH
	 * @date 2018��5��4��
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
	 * @Description ��ѹtar�ļ���ָ����Ŀ¼
	 * @author RongH
	 * @date 2018��5��4��
	 * @Title: TGzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 */
	private boolean unTarSingleDirectory(File compressedFile, String directory, String encoding,
			String innerDirectoryPath) {
		return new TarFileUtil().ExpandSingleDirectory(compressedFile, directory, encoding, innerDirectoryPath);
	}

	/**
	 * @Description ��Ŀ¼���Ϊ.tar�ļ����Ա�ѹ����.tar.gz
	 * @author RongH
	 * @date 2018��5��4��
	 * @Title: TGzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @return String
	 */
	private String compressedTarFile(String directFilePath, File file, String encoding) {
		if (new TarFileUtil().CompressedFile(directFilePath.replace(".gz", ""), file, encoding)) {
			return directFilePath.replace(".gz", "");
		} else {
			throw new RuntimeException("ѹ��" + file.getAbsolutePath() + "�ļ�ʧ�ܣ�");
		}
	}

	/**
	 * @Description ��.tar�ļ�ѹ����.tar.gz
	 * @author RongH
	 * @date 2018��5��4��
	 * @Title: TGzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @return String
	 */
	private boolean compressedGzFile(String directFilePath, File tarFile, String encoding) {
		return new GzipFileUtil().CompressedFile(directFilePath, tarFile, encoding);
	}
}
