package com.arv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

public class fileMod {

	public static void main(String[] args) throws IOException {
		
		Scanner sc1 = new Scanner(new File("C:/Users/arkr94/Desktop/FYP1/Indic_NER.txt"));
		FileWriter writer3 = new FileWriter("C:/Users/arkr94/Desktop/FYP1/Indic_NER_en.txt");
		try {
			
			while(sc1.hasNext())
			{	String s = sc1.next();
				if(s.codePointAt(0)<=2304 || s.codePointAt(0)>=2431)
				{
					writer3.write(s);
					writer3.write(System.getProperty( "line.separator" ));
					
				}
			
			}
		}
		finally {
			writer3.close();
		}
		sc1.close();
	}
}
