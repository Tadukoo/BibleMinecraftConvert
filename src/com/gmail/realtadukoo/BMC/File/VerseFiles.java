package com.gmail.realtadukoo.BMC.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.gmail.realtadukoo.BMC.EnumBible;

public class VerseFiles{
	
	public static Properties getBook(EnumBible book){
		try{
			Properties prop = new Properties();
			InputStream is = new FileInputStream("resource/Bible/" + 
					book.book().replaceAll(" ", "") + ".properties");
			prop.load(is);
			return prop;
		}catch(IOException e){
			return null;
		}
	}
	
	public static String getVerse(Properties book, int chp, int verse){
		return book.getProperty("ch" + chp + "v" + verse);
	}
}
