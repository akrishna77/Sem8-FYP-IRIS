package com.arv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

public class CWF {


	public static void CWFexec() throws IOException {
		
		Scanner sc1 = new Scanner(new File("C:/Users/arkr94/Desktop/FYP1/Indic_NER_en.txt"));
		FileWriter writer = new FileWriter("C:/Users/arkr94/Desktop/FYP1/Indic_NER_CWF.txt");
		while(sc1.hasNext()){
			String s = sc1.next().toLowerCase();
			s=s.replaceAll("ch", "s");
			s=s.replaceAll("ck", "k");
			s=s.replaceAll("gh", "g");
			s=s.replaceAll("gn", "n");
			s=s.replaceAll("kn", "n");
			s=s.replaceAll("th", "t");
			s=s.replaceAll("dh", "d");
			s=s.replaceAll("wh", "w");
			s=s.replaceAll("wr", "r");
			s=s.replaceAll("bb", "b");
			s=s.replaceAll("cc", "c");
			s=s.replaceAll("dd", "d");
			s=s.replaceAll("ff", "f");
			s=s.replaceAll("gg", "g");
			s=s.replaceAll("hh", "h");
			s=s.replaceAll("jj", "j");
			s=s.replaceAll("kk", "k");
			s=s.replaceAll("ll", "l");
			s=s.replaceAll("mm", "m");
			s=s.replaceAll("nn", "n");
			s=s.replaceAll("pp", "p");
			s=s.replaceAll("qq", "q");
			s=s.replaceAll("rr", "r");
			s=s.replaceAll("ss", "s");
			s=s.replaceAll("tt", "t");
			s=s.replaceAll("vv", "v");
			s=s.replaceAll("ww", "w");
			s=s.replaceAll("xx", "x");
			s=s.replaceAll("yy", "y");
			s=s.replaceAll("zz", "z");
			s=s.replaceAll("zh", "z");
			s=s.replace("d", "t");
			s=s.replace("q", "ky");
			s=s.replace("x", "ks");
			s=s.replace("w", "v");
			s=s.replace("b", "p");
			s=s.replace("g", "k");
			writer.write(s);
			writer.write(System.getProperty( "line.separator" ));
		}
	sc1.close();
	writer.close();
		    

	}
}
