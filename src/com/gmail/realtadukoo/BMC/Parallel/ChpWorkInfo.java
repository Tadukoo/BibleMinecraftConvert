package com.gmail.realtadukoo.BMC.Parallel;

import java.util.ArrayList;

import com.gmail.realtadukoo.BMC.EnumBible;

public class ChpWorkInfo{
	private EnumBible book;
	private int chp;
	private ArrayList<String> pages;
	
	public ChpWorkInfo(EnumBible book, int chp){
		this.book = book;
		this.chp = chp;
		pages = new ArrayList<String>();
	}
	
	public EnumBible getBook(){
		return book;
	}
	
	public int getChp(){
		return chp;
	}
	
	public ArrayList<String> getPages(){
		return pages;
	}
	
	public void setPages(ArrayList<String> pages){
		this.pages = pages;
	}
}
