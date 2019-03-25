package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;


public class FileManager {
	
	//upload
	
	//path:파일을 저장할 경로
	//return : 서버에 저장할 새로운 파일이름
	public static String doFileUpload(File file, String originalFileName, String path) throws Exception{
		
		String newFileName = null;
		
		if(file==null)
			return null;
				
		if(originalFileName.equals(""))
			return null;
		
		//확장자 분리
		String fileExt = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		if(fileExt==null||fileExt.equals(""))
			return null;
		
		//서버에 저장할 파일명 생성
		newFileName = 
				String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS",
						Calendar.getInstance());
		
		newFileName += System.nanoTime();//10의 -9승
		newFileName += fileExt;
		
		File f = new File(path);
		
		if(!f.exists())
			f.mkdirs();
		
		String fullFilePath = path + File.separator + newFileName;
		
		//파일 업로드
		FileInputStream fis = new FileInputStream(file);
		
		FileOutputStream fos = new FileOutputStream(fullFilePath);
		
		int byteRead = 0;
		byte[] buffer = new byte[1024];
		
		while((byteRead=fis.read(buffer, 0, 1024))!=-1){
			fos.write(buffer,0,byteRead);
		}
		
		fos.close();
		fis.close();
		
		return newFileName;
		
	}
	
	//파일 삭제
	public static void doFileDelete(String fileName, String path){
		
		String fullFilePath = path + File.separator + fileName;
		
		File f = new File(fullFilePath);
		
		if(f.exists())
			f.delete();
		
	}
	
	//파일 다운로드
	//saveFileName : 서버에 저장된 파일명
	//originalFileName : 클라이언트가 업로드한 파일명
	//path : 서버에 저장된 경로
	public static boolean doFileDownload (String saveFileName, 
			String originalFileName,String path,HttpServletResponse response){
		
		String fullFilePath = path + File.separator + saveFileName;
		
		try {
			
			if(originalFileName==null||originalFileName.equals(""))
				originalFileName = saveFileName;
			
			originalFileName = 
					new String(originalFileName.getBytes("euc-kr"),"8859_1");
			
		} catch (Exception e) {
			
		}
		
		try {
			
			File f = new File(fullFilePath);
			
			if(f.exists()){
				
				byte readByte[] = new byte[4096];
				
				response.setContentType("application/octet-stream");
				
				response.setHeader("Content-disposition",
						"attachment;fileName=" + originalFileName);
				
				BufferedInputStream fis = 
						new BufferedInputStream(new FileInputStream(f));
				
				OutputStream os = response.getOutputStream();
				
				int read;
				while((read=fis.read(readByte, 0, 4096))!=-1){
					os.write(readByte, 0, read);					
				}
				
				os.flush();
				os.close();
				fis.close();
				
				return true;
				
			}					
			
		} catch (Exception e) {
			
		}
		
		return false;
		
	}

}



























