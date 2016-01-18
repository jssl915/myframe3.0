package com.yf.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
/**
 * 文件操作类
 */
public class FileUtil {
	private static Logger logger = Logger.getLogger(FileUtil.class);
	public static void main(String[] args) {
		String url = "D:/nginx/html/ivfox_upload/download/";
		String copy_url = "D:/nginx/html/ivfox_upload_copy/download/";
		String fileName = "52135434.doc";
		File targetFile = new File(copy_url, fileName);
		copyFile(targetFile,url,url+fileName);
		moveFile(targetFile,url,url+fileName);
	}
	
	/**
	 * spring mvc上传MultipartFile文件
	 * 文件上传功能
	 */
	public static void transferMultipartFile(MultipartFile file, String destDir, String destFileName)
			throws IllegalStateException, IOException {
		File newFile = new File(destDir);
		if (!newFile.isDirectory()){
			newFile.mkdirs();
		}
		File targetFile = new File(destDir, destFileName);
		file.transferTo(targetFile);
	}
	
	/**
	 * 复制文件
	 * 
	 * @param sourcePath源文件路径
	 * @param destPath目标文件路径
	 * @throws IOException 
	 * @throws BusinessException
	 */
	public static void copyFile(File sourceFile,String destDir,String destPath){
		try {
			File newFile = new File(destDir);
			if (!newFile.isDirectory()){
				newFile.mkdirs();
			}
			FileCopyUtils.copy(sourceFile, new File(destPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//移动文件
	public static void moveFile(File sourceFile,String destDir,String destPath){
		try {
			File newFile = new File(destDir);
			if (!newFile.isDirectory()){
				newFile.mkdirs();
			}
			FileCopyUtils.copy(sourceFile, new File(destPath));
			sourceFile.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//删除文件
	public static void deleteFile(String filePath, String fileName) {
		File file = new File(filePath + fileName);
		if (file.exists()){
			file.delete();
		}
	}
	
	//删除文件夹
	public static void deleteFolder(File folder) {
		if (folder.isDirectory()) {
			File[] files = folder.listFiles();
			if (files.length > 0) {
				for (File f : files) {
					if (f.isDirectory()) {
						deleteFolder(f);
					} else if (f.exists()) {
						f.delete();
					}
				}
			}
			folder.delete();
		}
	}
	
	/**
	 * 从url获得文件名。如http://www.cykj.com/some/a.txt将得到a.txt
	 * 
	 * @param url
	 * @return 文件名
	 */
	public static String getFileNameFromUrl(String url) {
		return url.substring(url.lastIndexOf("/") + 1);
	}

	/**
	 * 从文件名获得文件扩展名。如a.doc将得到.doc
	 * 
	 * @param fileName文件名
	 * @return 扩展名
	 */
	public static String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	/**
	 * 获取文件大小
	 * 
	 * @param filepath
	 *            文件路径
	 * @return 文件大小
	 * @throws Exception
	 */
	public static int getFilesize(String filepath) throws Exception {
		int size = 0;
		File f = new File(filepath);

		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			size = fis.available();
		} else {
			logger.debug("File is not exists: " + filepath);
		}

		return size;
	}

	/**
	 * 获取文件大小
	 * 
	 * @param file
	 *            文件
	 * @return 文件大小
	 * @throws Exception
	 */
	public static int getFilesize(File file) throws Exception {
		int size = 0;

		if (file.exists()) {
			FileInputStream fis = new FileInputStream(file);
			size = fis.available();
		} else {
			logger.debug("File is not exists: " + file.getAbsolutePath());
		}

		return size;
	}

	/**
	 * 根据根目录及文件名称获取文件的全路径如:c:/a/b.txt
	 * 
	 * @param rootPath
	 * @param fileName
	 * @return
	 */
	public static String getFileRealPath(String rootPath, String fileName) {
		File root = new File(rootPath);
		if (root.isDirectory()) {
			File fileArray[] = root.listFiles();
			for (File f : fileArray) {
				if (f.getName().equals(fileName)) {
					return f.getPath();
				} else if (f.isDirectory()) {
					String path = getFileRealPath(f.getPath(), fileName);
					if (path != null) {
						return path;
					}
				}
			}
		}
		return null;
	}
}
