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
	 * @Description ����ָ���ı����ʽ��ѹ�ļ���ָ��Ŀ¼
	 * @author RongH
	 * @date 2018��5��4��
	 * @Title: TarFileServoce.java
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
	@Override
	public boolean ExpandCompressedFile(File compressedFile, String directory, String encoding) {
		if (Assert.isEmpty(encoding)) {
			encoding = DEFAULT_ENCODING;
		}
		logger.debug("��ѹ�ļ�����: " + encoding);
		TarInputStream tis = null;
		try {
			TarEntry te = null;
			tis = new TarInputStream((new FileInputStream(compressedFile)));
			while ((te = tis.getNextEntry()) != null) {
				if (te.isDirectory()) {
					logger.debug("tar����Ŀ¼: " + directory + te.getName());
					FileUtils.mkdir(directory + te.getName());
				} else {
					logger.debug("tarд���ļ�: " + directory + te.getName());
					WriteFileFromStream(tis, directory + te.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new RuntimeException("��ѹ" + compressedFile + "�ļ�ʧ�ܣ���ȷ�ϸ�ѹ�����ı����ʽ��ȷ: " + e.getMessage());
		} finally {
			try {
				if (tis != null) {
					tis.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new RuntimeException("�ر��ļ���ʧ��: " + e.getMessage());
			}
		}
		return true;
	}

	/**
	 * @Description ���ձ����ʽ��ѹѹ�����ڵ�ָ���ļ���ָ��Ŀ¼��
	 * @author RongH
	 * @date 2018��5��4��
	 * @Title: TarFileServoce.java
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
		String innerFileName = getFileName(innerFileRelativePath);
		TarInputStream tis = null;
		try {
			tis = new TarInputStream((new FileInputStream(compressedFile)));
			TarEntry te = null;
			while ((te = tis.getNextEntry()) != null) {
				if (innerFileRelativePath.equals(te.getName())) {
					logger.debug("tarд���ļ�: " + directory + innerFileName);
					WriteFileFromStream(tis, directory + innerFileName);
					break;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException("��ѹ" + compressedFile + "�ļ�ʧ�ܣ���ȷ�ϸ�ѹ�����ı����ʽ��ȷ ��" + e.getMessage());
		} finally {
			try {
				if (tis != null) {
					tis.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new RuntimeException("�ر��ļ���ʧ�� �� " + e.getMessage());
			}
		}

		return true;
	}

	/**
	 * @Description ���ձ����ʽ��ѹѹ�����ڵ�ָ��Ŀ¼��ָ��Ŀ¼��
	 * @author RongH
	 * @date 2018��5��4��
	 * @Title: TarFileService.java
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
		TarInputStream tis = null;
		try {
			tis = new TarInputStream((new FileInputStream(compressedFile)));
			TarEntry te = null;
			while ((te = tis.getNextEntry()) != null) {
				if (te.getName().startsWith(innerDirectoryPath)) {
					String fileName = te.getName().replaceFirst(innerDirectoryPath, "");
					if (te.isDirectory()) {
						logger.debug("tar����Ŀ¼: " + directory + fileName);
						FileUtils.mkdir(directory + fileName);
					} else {
						logger.debug("tarд���ļ�: " + directory + fileName);
						WriteFileFromStream(tis, directory + fileName);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException("��ѹ" + compressedFile + "�ļ�ʧ�ܣ���ȷ�ϸ�ѹ�����ı����ʽ��ȷ: " + e.getMessage());
		} finally {
			try {
				if (tis != null) {
					tis.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new RuntimeException("�ر��ļ���ʧ��: " + e.getMessage());
			}
		}
		return true;
	}

	/**
	 * @Description ���ձ����ʽѹ���ļ���ָ����Ŀ¼��
	 * @author RongH
	 * @date 2018��5��4��
	 * @Title: TarFileService.java
	 * @Company: CORSWORK
	 * @Copyright: Copyright (c) 2018
	 * @param directFilePath
	 *            ѹ��Ŀ��Ŀ¼�ļ�
	 * @param file
	 *            ��Ҫѹ�����ļ�
	 * @param encoding
	 *            �����ʽ
	 * @return ѹ���Ƿ����
	 */
	@Override
	public boolean CompressedFile(String directFilePath, File file, String encoding) {
		TarOutputStream tos = null;
		try {
			File tar_file = new File(directFilePath);
			logger.debug("tar�ݹ�ѹ����ָ���ļ�: " + directFilePath);
			tar_file.getParentFile().mkdirs();
			tos = new TarOutputStream(new BufferedOutputStream(new FileOutputStream(tar_file)));
			tos.setLongFileMode(TarOutputStream.LONGFILE_GNU);
			getTarFile(file, tos, "");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException("ѹ��" + file.getAbsolutePath() + " �ļ�ʧ�ܣ���ȷ�ϸ�ѹ�����ı����ʽ��ȷ: " + e.getMessage());
		} finally {
			try {
				if (Assert.notEmpty(tos)) {
					tos.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new RuntimeException("�ر��ļ���ʧ��: " + e.getMessage());
			}
		}
		return true;
	}

	/**
	 * 
	 * @Description �ݹ�ѹ���ļ�
	 * @author RongH
	 * @date 2018��5��4��
	 * @Title: TarFileService.java
	 * @Copyright: Copyright (c) 2018
	 * @return void
	 */
	private static void getTarFile(File file, TarOutputStream tos, String fileName) throws IOException {
		BufferedInputStream bis = null;
		try {
			if (file.isDirectory()) {
				if (Assert.notEmpty(file.listFiles())) {
					logger.debug("tar�ݹ�Ŀ¼: " + file.getName());
					for (File temp : file.listFiles()) {
						getTarFile(temp, tos, fileName + file.getName() + "/");
					}
				} else {
					logger.debug("tarд���Ŀ¼: " + fileName + file.getName() + "/");
					TarEntry entry = new TarEntry(fileName + file.getName() + "/");
					entry.setSize(file.length());
					tos.putNextEntry(entry);
					tos.closeEntry();
					tos.flush();
				}
			} else {
				logger.debug("tarд���ļ�: " + fileName + file.getName());
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
