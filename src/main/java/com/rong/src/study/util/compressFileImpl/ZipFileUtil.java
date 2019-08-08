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
	 * @Description ����ָ���ı����ʽ��ѹ�ļ���ָ��Ŀ¼
	 * @author rongh
	 * @date 2018-05-17 09:29
	 * @Title: ZipFileService.java
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
		BufferedInputStream bis = null;
		ZipFile zFile = null;
		try {
			zFile = new ZipFile(compressedFile, encoding);
			Enumeration<ZipEntry> entry = zFile.getEntries();
			while (entry.hasMoreElements()) {
				ZipEntry ze = entry.nextElement();
				if (ze.isDirectory()) {
					logger.debug("zip����Ŀ¼: " + directory + ze.getName());
					FileUtils.mkdir(directory + ze.getName());
				} else {
					logger.debug("zipд���ļ�: " + directory + ze.getName());
					bis = new BufferedInputStream(zFile.getInputStream(ze));
					WriteFileFromStream(bis, directory + ze.getName());
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException("��ѹ�ļ�ʧ��: " + e.getMessage());
		} finally {
			try {
				if (zFile != null)
					zFile.close();
				if (bis != null)
					bis.close();
			} catch (IOException e) {
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
	 * @Title: ZipFileService.java
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
		if (Assert.isEmpty(encoding))
			encoding = DEFAULT_ENCODING;
		logger.debug("��ѹ�����ļ�����: " + encoding);
		BufferedInputStream bis = null;
		String innerFileName = getFileName(innerFileRelativePath);
		ZipFile zFile = null;
		try {
			zFile = new ZipFile(compressedFile, encoding);
			Enumeration<ZipEntry> entry = zFile.getEntries();
			while (entry.hasMoreElements()) {
				ZipEntry ze = entry.nextElement();
				if (innerFileRelativePath.equals(ze.getName())) {
					logger.debug("zipд���ļ�: " + directory + innerFileName);
					bis = new BufferedInputStream(zFile.getInputStream(ze));
					WriteFileFromStream(bis, directory + innerFileName);
					break;
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException("��ȡ" + compressedFile + "�ļ���ʧ��: " + e.getMessage());
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
				throw new RuntimeException("�ر��ļ���ʧ��: " + e.getMessage());
			}
		}
		return true;
	}

	/**
	 * @Description ���ձ����ʽ��ѹѹ�����ڵ�ָ��Ŀ¼��ָ��Ŀ¼��
	 * @author RongH
	 * @date 2018��5��4��
	 * @Title: ZipFileService.java
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
		if (Assert.isEmpty(encoding))
			encoding = DEFAULT_ENCODING;
		logger.debug("��ѹ����Ŀ¼����: " + encoding);
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
						logger.debug("zip����Ŀ¼: " + directory + fileName);
						FileUtils.mkdir(directory + fileName);
					} else {
						logger.debug("zipд���ļ�: " + directory + fileName);
						bis = new BufferedInputStream(zFile.getInputStream(ze));
						WriteFileFromStream(bis, directory + fileName);
					}
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException("��ȡ" + compressedFile + "�ļ���ʧ��: " + e.getMessage());
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
				throw new RuntimeException("�ر��ļ���ʧ��: " + e.getMessage());
			}
		}
		return true;
	}

	/**
	 * @Description ���ձ����ʽ�ݹ�ѹ���ļ���ָ����Ŀ¼��
	 * @author RongH
	 * @date 2018��5��4��
	 * @Title: ZipFileService.java
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
		if (Assert.isEmpty(encoding))
			encoding = DEFAULT_ENCODING;
		ZipOutputStream zos = null;
		try {
			File zip_file = new File(directFilePath);
			logger.debug("zip�ݹ�ѹ����ָ���ļ�:" + directFilePath);
			zip_file.getParentFile().mkdirs();
			zos = new ZipOutputStream(new FileOutputStream(zip_file));
			zos.setEncoding(encoding);
			logger.debug("zip�ݹ�ѹ������: " + encoding);
			getZipFile(file, zos, "");
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException("ѹ��" + file.getAbsolutePath() + " �ļ�ʧ��: " + e.getMessage());
		} finally {
			try {
				if (Assert.notEmpty(zos)) {
					zos.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new RuntimeException("zip�ر��ļ���ʧ��: " + e.getMessage());
			}
		}
		return true;
	}

	/**
	 * @Description �ݹ�ѹ���ļ�
	 * @author RongH
	 * @date 2018��5��4��
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
					logger.debug("zip�ݹ�Ŀ¼: " + source_file.getName());
					for (File file_in_dir : source_file.listFiles()) {
						getZipFile(file_in_dir, zos, file_path_in_zip);
					}
				} else {
					logger.debug("zipд���Ŀ¼: " + file_path_in_zip);
					ZipEntry entry = new ZipEntry(file_path_in_zip);
					entry.setSize(source_file.length());
					entry.setUnixMode(755);
					zos.putNextEntry(entry);
					zos.closeEntry();
					zos.flush();
				}
			} else {
				logger.debug("zipд���ļ�: " + file_path_in_zip);
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
