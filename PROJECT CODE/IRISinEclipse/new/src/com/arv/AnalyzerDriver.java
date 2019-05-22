package com.arv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;





import edu.stanford.nlp.simple.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.io.FileWriter;

import org.apache.commons.io.IOUtils;
import org.apache.lucene.analysis.hi.HindiStemmer;

/**
 * 			A tokenizer
 *         according to the following rules: Tokenize all abbreviations
 *         containing periods as strings without periods Treat the rest of the
 *         punctuation as word separators Lowercase all letters
 * 
 *         A stemmer implemented via the Porter Stemmer package:
 *         http://tartarus.org/~martin/PorterStemmer/ Implement stopword removal
 *         using the following stopword list: http://bit.ly/1bqQWaV
 * 
 *         The word list is tokenized first, stopwords are removed, and finally
 *         the words are put through the stemmer
 */
public class AnalyzerDriver {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

//		String fileName = "C:/Users/arkr94/Desktop/FYP1/input.txt";
		
		Scanner in = new Scanner(System.in);
		String query = in.nextLine();
				
		
		String stopWordsFileName = "C:/Users/arkr94/Desktop/FYP1/stopwords-en.txt";
		String stopWordsFileName2 = "C:/Users/arkr94/Desktop/FYP1/stopwords_hi.txt";
//		String stopWordsFileName3 = "";

		String stopWordsString = processStopWords(stopWordsFileName);
		String stopWordsString1 = processStopWords(stopWordsFileName2);
//		String stopWordsString2 = processStopWords(stopWordsFileName3);

//		System.out.println(query);

		String[] seperatedWords = query.split(" ");
		String[] stopWords = stopWordsString.split("\n");
		String[] stopWordshindi = stopWordsString1.split("\n");
//		String[] stopWordstamil = stopWordsString2.split("\n");
		
		ArrayList<String> hindi = new ArrayList<String>();
		ArrayList<String> tamil = new ArrayList<String>();
		ArrayList<String> hindistop = new ArrayList<String>();
		ArrayList<String> tamilstop = new ArrayList<String>();
		ArrayList<String> hindistem = new ArrayList<String>();
		ArrayList<String> tamilstem = new ArrayList<String>();
		
		List<String> stringList = new ArrayList<String>(Arrays.asList(seperatedWords));
		
		for(int i=0;i<stringList.size();i++)
		{	if(stringList.get(i).length()==0)
				stringList.remove(i);
		}
		
		for(int i=0;i<stringList.size();i++)
		{	int x=stringList.get(i).codePointAt(0);
			if(x>=2304 && x<=2431)
			{	hindi.add(stringList.get(i));
				stringList.remove(i);
				i--;
			}
			if(x>=2944 && x<=3071)
			{	tamil.add(stringList.get(i));
				stringList.remove(i);
				i--;
			}
		}
		
		hindistop=hindi;
		tamilstop=tamil;
		List<String> hindiStopList = new ArrayList<String>(Arrays.asList(stopWordshindi));
		hindistop.removeAll(hindiStopList);
//		List<String> tamilStopList = new ArrayList<String>(Arrays.asList(stopWordstamil));
//		tamilstop.removeAll(tamilStopList);

		String[] seperatedWords1 = new String[stringList.size()];
		seperatedWords1 = stringList.toArray(seperatedWords1);
		
		StringBuilder builder = new StringBuilder();
		for(String s : seperatedWords1) {
		    builder.append(s+" ");
		}
		MWE_NER(builder.toString());
		
		Tokenizer tokenizer = new Tokenizer(seperatedWords1, stopWords);

		String tokenizedWords = tokenizer.getProcessedWords();

		System.out.println(tokenizedWords);
		
//		HindiStemmerLight q = new HindiStemmerLight();
//		
//		for(int i=0;i<hindi.size();i++)
//		{	
//			hindistem.add(q.stemHindi(hindi.get(i)));
//		}
//		
//		for(String s:hindistem)
//		{
//			System.out.println(s);
//		}
		
		FileWriter writer = new FileWriter("C:/Users/arkr94/Desktop/FYP1/hindi.txt"); 
		for(String str: hindi) {
		  writer.write(str+" ");
		}
		writer.close();
		
		FileWriter writer2 = new FileWriter("C:/Users/arkr94/Desktop/FYP1/tamil.txt"); 
		for(String str: tamil) {
		  writer2.write(str+" ");
		}
		writer2.close();
		
		FileWriter writer3 = new FileWriter("C:/Users/arkr94/Desktop/FYP1/english.txt"); 
		writer3.write(tokenizedWords);
		writer3.close();
		
		ArrayList<String> TamtoEng = new ArrayList<String>();
		ArrayList<String> HintoEng = new ArrayList<String>();
		
		TamtoEng = Transliterator.TamiltoEnglish(tamil);
		HintoEng = Transliterator.HinditoEnglish(hindi);
		
		String query2 = query;
		
		for(int i=0;i<tamil.size();i++)
		{	System.out.println(tamil.get(i));
			query2 = query2.replaceAll(tamil.get(i), TamtoEng.get(i));
			
//			if(tamil.get(i).equals("vazhkkai"))
//				query2 = query2.replaceAll("vazhkkai", "வாழ்க்கை");
//			
//			if(tamil.get(i).equals("uraippar"))
//				query2 = query2.replaceAll("uraippar", "உரைப்பார்");
//			
//			if(tamil.get(i).equals("uraippavi"))
//				query2 = query2.replaceAll("uraippavi", "உரைப்பவை");
//			
//			if(tamil.get(i).equals("ellaam"))
//				query2 = query2.replaceAll("ellaam", "எல்லாம்");
//			
		}
		
		for(int i=0;i<hindi.size();i++)
		{	System.out.println(hindi.get(i));
			query2 = query2.replaceAll(hindi.get(i), HintoEng.get(i));
			
//			if(tamil.get(i).equals("dhoom"))
//				query2 = query2.replaceAll("dhoom", "धूम");
//			
//			if(tamil.get(i).equals("machaale"))
//				query2 = query2.replaceAll("machaale", "मचाले");
		}
		
		System.out.println(query2);
	
		
//		BufferedReader br1 = new BufferedReader(
//				new InputStreamReader(
//						new FileInputStream(
//								new File(
//										"C:/Users/arkr94/Desktop/FYP1/hindi.txt")),
//						"UTF-8"));
//
//		BufferedReader br = new BufferedReader(
//				new InputStreamReader(
//						new FileInputStream(
//								new File(
//										"C:/Users/arkr94/Desktop/FYP1/tamil.txt")),
//						"UTF-8"));
//		
//		FileWriter writer4 = new FileWriter("C:/Users/arkr94/Desktop/FYP1/hindimeanings.txt");
		
		FileWriter writer4 = new FileWriter("C:/Users/arkr94/Desktop/FYP1/querylist.txt");
//		

		
		
		
//		String str1;
//		while((str1=br1.readLine())!=null)
//		{	String[] words1 = str1.split("\\s+");
//			for(int k=0;k<words1.length;k++)
//				if(Translate.TranslateHindi(words1[k]) != null){
//					writer4.write(Translate.TranslateHindi(words1[k])+" ");
//				}
//		}
		
		String query3 = query;
		for(int i=0;i<tamil.size();i++)
		{	if(Translate.TranslateTamil(tamil.get(i)) != null){
					query3 = query3.replaceAll(tamil.get(i), Translate.TranslateTamil(tamil.get(i)));
			}
		}		
	
		for(int i=0;i<hindi.size();i++)
		{	if(Translate.TranslateHindi(hindi.get(i)) != null){
					query3 = query3.replaceAll(hindi.get(i), Translate.TranslateHindi(hindi.get(i)));
			}
		}		
	
		System.out.println(query3);
		
		writer4.write(query);
		writer4.write(System.getProperty( "line.separator" ));
		writer4.write(query2);
		writer4.write(System.getProperty( "line.separator" ));
		writer4.write(query3);
		
		
//		br1.close();
//		br.close();
		writer4.close();
//		writer5.close();
		
}
	

	static String captureText(String fileName) throws IOException {
		String textToProcess;
		FileInputStream inputStream = new FileInputStream(fileName);
		try {
			textToProcess = IOUtils.toString(inputStream);
		} finally {
			inputStream.close();
		}
		
		return textToProcess;
////		
//		File f=new File(fileName);
//        Reader decoded=new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8);
//        bufferedWriter = new BufferedWriter (new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
//        char[] buffer = new char[1024];
//        int n;
//        StringBuilder build=new StringBuilder();
//        while(true){
//            n=decoded.read(buffer);
//            if(n<0){break;}
//            build.append(buffer,0,n);
//            bufferedWriter.write(buffer);
//        }
//        String verse = build.toString();
//        
//        return verse;
	}

	static String processStopWords(String fileName) throws IOException {
		String stopWords;
		FileInputStream inputStream = new FileInputStream(fileName);
		try {
			stopWords = IOUtils.toString(inputStream);
		} finally {
			inputStream.close();
		}
		return stopWords;

	}
	

	public static void MWE_NER(String arg) throws IOException { 
	    // Create a document. No computation is done yet.
	    Document doc = new Document(arg);
	    
	    FileWriter writer3 = new FileWriter("C:/Users/arkr94/Desktop/FYP1/NER.txt"); 
		
	    for (Sentence sent : doc.sentences()) { 
	    
	    System.out.println(sent.nerTags());
//	    System.out.println(sent.posTags());
	    List<String> tags = sent.nerTags();
	    
	    for(int i=0;i<tags.size();i++)
	    {	String tag= tags.get(i);
	    	String out="";
	    	if(!tag.equals("O"))
	    	{	int j=i;
	    		if(j==tags.size()-1)
	    			break;
	    		while(tags.get(j+1).equals(tag))
	    		{
	    			j++;
	    			if(j==tags.size()-1)
		    			break;
	    		}
	    		for(int k=i; k<=j; k++)
	    		{
	    			out = out.concat(sent.word(k));
	    			out = out.concat(" ");
	    		}
	    		writer3.write(out);
	    		writer3.write(System.getProperty( "line.separator" ));
	    		i=j;
	    	} 
	    	else
	    		LevDist.LevDistance(sent.word(i));
	    }
	    
	   }
	    
	    writer3.close();
	}
}