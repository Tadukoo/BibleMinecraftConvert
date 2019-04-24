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
	
	public static Properties loadMinecraftBook(EnumBible book) throws IOException{
		Properties prop = new Properties();
		InputStream is = new FileInputStream("resource/Bible/Minecraft/" + 
				book.book().replaceAll(" ", "") + ".properties");
		prop.load(is);
		return prop;
	}
	
	public static void saveMinecraftBook(Properties prop, EnumBible book) throws IOException{
		try{
			FileOutputStream fos = new FileOutputStream("resource/Bible/Minecraft/" + 
					book.book().replaceAll(" ", "") + ".properties");
			fos.close();
		}catch(FileNotFoundException e){
			File directory = new File("resource/Bible/Minecraft");
			directory.mkdirs();
		}
		OutputStream os = new FileOutputStream("resource/Bible/Minecraft/" +
				book.book().replaceAll(" ", "") + ".properties");
		prop.store(os, "No Comment");
	}
	
	public static void addToMinecraftBook(EnumBible book, int chpNum, int bookEnd, int pageEnd){
		Properties prop = new Properties();
		try{
			prop = loadMinecraftBook(book);
		}catch(IOException e){
			
		}
		prop.setProperty("Ch" + chpNum, "B" + bookEnd + "P" + pageEnd);
		try{
			saveMinecraftBook(prop, book);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void addToMinecraftBook(EnumBible book, int bookNum, int pageNum, String page){
		Properties prop = new Properties();
		try{
			prop = loadMinecraftBook(book);
		}catch(IOException e){
			
		}
		prop.setProperty("Book" + bookNum + "Page" + pageNum, page);
		try{
			saveMinecraftBook(prop, book);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}