package com.arv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Translate {
	
	public static String TranslateTamil(String TamilS) throws FileNotFoundException{
	HashMap<String,String> TamilDictionary = new HashMap<String,String>();
	
	Scanner sc1 = new Scanner(new File("C:/Users/arkr94/Desktop/FYP1/Dictionary_Tamil.txt"));
	while(sc1.hasNext())
	{
		TamilDictionary.put(sc1.next().trim(), sc1.nextLine().trim());
	}
	
	sc1.close();
	
	return TamilDictionary.get(TamilS.trim());
	
	}

	public static String TranslateHindi(String HindiS) throws FileNotFoundException{

	HashMap<String,String> HindiDictionary = new HashMap<String,String>();
	
	Scanner sc2 = new Scanner(new File("C:/Users/arkr94/Desktop/FYP1/Dictionary_Hindi.txt"));
	while(sc2.hasNext())
	{
		HindiDictionary.put(sc2.next().trim(), sc2.nextLine().trim());
	}
	
	sc2.close();

	return HindiDictionary.get(HindiS.trim());
	
	}

}
