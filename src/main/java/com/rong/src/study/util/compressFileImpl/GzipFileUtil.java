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
	 * @date 2018��5��4��
	 * @Title: GzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 */
	@Override
	public boolean ExpandCompressedFile(File compressedFile, String directory, String encoding) {
		return ExpandCompressedFile(compressedFile, directory);
	}

	/**
	 * @Description ���ձ����ʽ��ѹѹ�����ڵ�ָ���ļ���ָ��Ŀ¼��
	 * @author RongH
	 * @date 2018��5��4��
	 * @Title: GzipFileService.java
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
		String fileName = getFileName(compressedFile.getAbsolutePath());
		String innerFileName = getFileName(innerFileRelativePath);
		if (fileName.replace(".gz", "").equals(innerFileName)) {
			ExpandCompressedFile(compressedFile, directory);
		} else {
			throw new RuntimeException("gzѹ���ļ���֧�ֶ�ȡѹ�����еĲ����ļ���");
		}
		return true;
	}

	/**
	 * @Description ���ձ����ʽ��ѹѹ�����ڵ�ָ��Ŀ¼��ָ��Ŀ¼��
	 * @author rongh
	 * @date 2018-05-17 09:33
	 * @Title: GzipFileService.java
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
		throw new RuntimeException("gzѹ���ļ���֧�ֶ�ȡѹ�����е�Ŀ¼��");
	}

	/**
	 * @Description ѹ���ļ���ָ��Ŀ¼
	 * @author rongh
	 * @date 2018-05-17 09:23
	 * @Title: GzipFileService.java
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
		new File(directFilePath).getParentFile().mkdirs();
		return CompressedFile(directFilePath, file);
	}

	/**
	 * @Description ����ָ���ı����ʽ��ѹ�ļ���ָ��Ŀ¼
	 * @author RongH
	 * @date 2018��5��4��
	 * @Title: GzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @param compressedFile
	 *            ��Ҫ��ѹ��ѹ���ļ�
	 * @param directory
	 *            ��ѹָ��Ŀ¼
	 * @param encoding
	 *            �����ʽ
	 * @return ��ѹ�Ƿ����
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
					"��ѹ" + compressedFile.getAbsolutePath() + "�ļ�ʧ�ܣ���ȷ�ϸ�ѹ�����ı����ʽ��ȷ: " + e.getMessage());
		} finally {
			try {
				if (gzin != null)
					gzin.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new RuntimeException("�ر��ļ���ʧ��: " + e.getMessage());
			}
		}
		return true;

	}

	/**
	 * @Description ѹ���ļ���ָ��Ŀ¼
	 * @author RongH
	 * @date 2018��5��4��
	 * @Title: GzipFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @return boolean
	 */
	private boolean CompressedFile(String directFilePath, File file) {
		if (file.isDirectory()) {
			throw new RuntimeException("gzѹ����֧��ѹ��Ŀ¼��");
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
			throw new RuntimeException("ѹ��" + file.getAbsolutePath() + " �ļ�ʧ��: " + e.getMessage());
		} finally {
			try {
				if (Assert.notEmpty(gos))
					gos.close();
				if (Assert.notEmpty(bis))
					bis.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new RuntimeException("�ر��ļ���ʧ��: " + e.getMessage());
			}
		}
		return true;

	}

}
