package com.gmail.realtadukoo.BMC;

public enum EnumBible{
	GENESIS("Genesis"),
	EXODUS("Exodus"),
	LEVITICUS("Leviticus"),
	NUMBERS("Numbers"),
	DEUTERONOMY("Deuteronomy"),
	JOSHUA("Joshua"),
	JUDGES("Judges"),
	RUTH("Ruth"),
	SAMUEL1("1 Samuel"),
	SAMUEL2("2 Samuel"),
	KINGS1("1 Kings"),
	KINGS2("2 Kings"),
	CHRONICLES1("1 Chronicles"),
	CHRONICLES2("2 Chronicles"),
	EZRA("Ezra"),
	NEHEMIAH("Nehemiah"),
	ESTHER("Esther"),
	JOB("Job"),
	PSALMS("Psalms"),
	PROVERBS("Proverbs"),
	ECCLESIASTES("Ecclesiastes"),
	SONGOFSONGS("Song of Songs"),
	ISAIAH("Isaiah"),
	JEREMIAH("Jeremiah"),
	LAMENTATIONS("Lamentations"),
	EZEKIEL("Ezekiel"),
	DANIEL("Daniel"),
	HOSEA("Hosea"),
	JOEL("Joel"),
	AMOS("Amos"),
	OBADIAH("Obadiah"),
	JONAH("Jonah"),
	MICAH("Micah"),
	NAHUM("Nahum"),
	HABAKKUK("Habakkuk"),
	ZEPHANIAH("Zephaniah"),
	HAGGAI("Haggai"),
	ZECHARIAH("Zechariah"),
	MALACHI("Malachi"),
	MATTHEW("Matthew"),
	MARK("Mark"),
	LUKE("Luke"),
	JOHN("John"),
	ACTS("Acts"),
	ROMANS("Romans"),
	CORINTHIANS1("1 Corinthians"),
	CORINTHIANS2("2 Corinthians"),
	GALATIANS("Galatians"),
	EPHESIANS("Ephesians"),
	PHILIPPIANS("Philippians"),
	COLOSSIANS("Colossians"),
	THESSALONIANS1("1 Thessalonians"),
	THESSALONIANS2("2 Thessalonians"),
	TIMOTHY1("1 Timothy"),
	TIMOTHY2("2 Timothy"),
	TITUS("Titus"),
	PHILEMON("Philemon"),
	HEBREWS("Hebrews"),
	JAMES("James"),
	PETER1("1 Peter"),
	PETER2("2 Peter"),
	JOHN1("1 John"),
	JOHN2("2 John"),
	JOHN3("3 John"),
	JUDE("Jude"),
	REVELATION("Revelation");
	
	private String book;
	
	private EnumBible(String book){
		this.book = book;
	}
	
	public String book(){
		return book;
	}
	
	public static EnumBible fromInt(int i){
		for(EnumBible e: EnumBible.values()){
			if(e.ordinal() == i - 1){
				return e;
			}
		}
		return null;
	}
}