package com.gmail.realtadukoo.BMC.File;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.gmail.realtadukoo.BMC.EnumBible;

public class BookFiles{
	
	public static Properties loadMinecraftBook(EnumBible book, boolean parallel, int numThreads) throws IOException{
		Properties prop = new Properties();
		String basePath = "resource/Bible/Minecraft/";
		if(parallel){
			basePath += "parallel/" + numThreads + "/";
		}else if(numThreads == -1){
			basePath += "old/";
		}
		File file = new File(basePath + book.book().replaceAll(" ", "") + ".properties");
		file.createNewFile();
		InputStream is = new FileInputStream(file);
		prop.load(is);
		return prop;
	}
	
	public static void saveMinecraftBook(Properties prop, EnumBible book, boolean parallel, int numThreads) throws IOException{
		//System.out.println("Book: " + book.book() + " Page 1: " + prop.getProperty("Book1Page1"));
		String basePath = "resource/Bible/Minecraft/";
		if(parallel){
			basePath += "parallel/" + numThreads + "/";
		}else if(numThreads == -1){
			basePath += "old/";
		}
		try{
			FileOutputStream fos = new FileOutputStream(basePath + 
					book.book().replaceAll(" ", "") + ".properties");
			fos.close();
		}catch(FileNotFoundException e){
			File directory = new File(basePath);
			directory.mkdirs();
		}
		OutputStream os = new FileOutputStream(basePath +
				book.book().replaceAll(" ", "") + ".properties");
		prop.store(os, "No Comment");
	}
	
	public static void addToMinecraftBook(EnumBible book, int chpNum, int bookEnd, int pageEnd){
		Properties prop = new Properties();
		try{
			prop = loadMinecraftBook(book, false, -1);
		}catch(IOException e){
			
		}
		prop.setProperty("Ch" + chpNum, "B" + bookEnd + "P" + pageEnd);
		try{
			saveMinecraftBook(prop, book, false, -1);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void addToMinecraftBook(EnumBible book, int bookNum, int pageNum, String page){
		Properties prop = new Properties();
		try{
			prop = loadMinecraftBook(book, false, -1);
		}catch(IOException e){
			
		}
		prop.setProperty("Book" + bookNum + "Page" + pageNum, page);
		try{
			saveMinecraftBook(prop, book, false, -1);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}