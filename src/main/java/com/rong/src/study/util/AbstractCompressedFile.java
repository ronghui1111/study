package com.rong.src.study.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description ��ѹ���ļ�������
 * @author rongh
 * @date 2019-05-07 18:24
 * @Copyright: Copyright (c) 2018
 */
public abstract class AbstractCompressedFile {
	// ��ȡѹ���ļ�Ĭ�ϵı����ʽ
	public static final String DEFAULT_ENCODING = "GBK";
	// IO����ȡ�����С
	public static final int BUFF = 1024 * 1024;

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
	 */
	public abstract boolean ExpandCompressedFile(File compressedFile, String directory, String encoding);

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
	 */
	public abstract boolean CompressedFile(String directFilePath, File file, String encoding);

	/**
	 * @Description ���ձ����ʽ��ѹѹ�����ڵ�ָ���ļ���ָ��Ŀ¼��
	 * @author rongh
	 * @date 2018-05-17 09:20
	 * @Title: CompressedFileUtil.java
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
	public abstract boolean ExpandSingleFile(File compressedFile, String directory, String encoding,
			String innerFileRelativePath);

	/**
	 * @Description ���ձ����ʽ��ѹѹ�����ڵ�ָ��Ŀ¼��ָ��Ŀ¼��
	 * @author rongh
	 * @date 2018-05-17 09:21
	 * @Title: CompressedFileUtil.java
	 * @Company: CORSWORK
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
	 */
	public abstract boolean ExpandSingleDirectory(File compressedFile, String directory, String encoding,
			String innerDirectoryRelativePath);

	/**
	 * @Description ����д�뵽�ļ���
	 * @author rongh
	 * @date 2018-05-17 09:41
	 * @Title: CompressedFileUtil.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @param is
	 *            ������
	 * @param filePath
	 *            д���ļ���·��
	 * @throws IOException
	 */
	protected void WriteFileFromStream(InputStream is, String filePath) throws IOException {
		BufferedOutputStream bos = null;
		try {
			byte[] b = new byte[BUFF];
			int length = 0;
			new File(filePath).getParentFile().mkdirs();
			bos = new BufferedOutputStream(new FileOutputStream(filePath));
			while ((length = is.read(b, 0, BUFF)) != -1) {
				bos.write(b, 0, length);
			}
			bos.flush();
		} finally {
			if (Assert.isEmpty(bos))
				bos.close();
		}

	}

	protected String getFileName(String filePath) {
		String fileName = "";
		if (filePath.contains("\\")) {
			if (Assert.notEmpty(filePath.split("\\\\"))) {
				fileName = filePath.split("\\\\")[filePath.split("\\\\").length - 1];
			}
		} else {
			if (Assert.notEmpty(filePath.split("/"))) {
				fileName = filePath.split("/")[filePath.split("/").length - 1];
			}
		}
		return fileName;

	}
}
