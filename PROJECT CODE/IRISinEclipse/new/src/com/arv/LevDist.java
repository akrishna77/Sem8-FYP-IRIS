package com.arv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class LevDist {

  
  // Misc
  //-----------------------------------------------------------------------
  /**
   * <p>Find the Levenshtein distance between two Strings.</p>
   *
   * <p>This is the number of changes needed to change one String into
   * another, where each change is a single character modification (deletion,
   * insertion or substitution).</p>
   *
   * <p>The previous implementation of the Levenshtein distance algorithm
   * was from <a href="http://www.merriampark.com/ld.htm">http://www.merriampark.com/ld.htm</a></p>
   *
   * <p>Chas Emerick has written an implementation in Java, which avoids an OutOfMemoryError
   * which can occur when my Java implementation is used with very large strings.<br>
   * This implementation of the Levenshtein distance algorithm
   * is from <a href="http://www.merriampark.com/ldjava.htm">http://www.merriampark.com/ldjava.htm</a></p>
   *
   * <pre>
   * StringUtils.getLevenshteinDistance(null, *)             = IllegalArgumentException
   * StringUtils.getLevenshteinDistance(*, null)             = IllegalArgumentException
   * StringUtils.getLevenshteinDistance("","")               = 0
   * StringUtils.getLevenshteinDistance("","a")              = 1
   * StringUtils.getLevenshteinDistance("aaapppp", "")       = 7
   * StringUtils.getLevenshteinDistance("frog", "fog")       = 1
   * StringUtils.getLevenshteinDistance("fly", "ant")        = 3
   * StringUtils.getLevenshteinDistance("elephant", "hippo") = 7
   * StringUtils.getLevenshteinDistance("hippo", "elephant") = 7
   * StringUtils.getLevenshteinDistance("hippo", "zzzzzzzz") = 8
   * StringUtils.getLevenshteinDistance("hello", "hallo")    = 1
   * </pre>
   *
   * @param s  the first String, must not be null
   * @param t  the second String, must not be null
   * @return result distance
   * @throws IllegalArgumentException if either String input <code>null</code>
   */
  public static int getLevenshteinDistance(String s, String t) {
      if (s == null || t == null) {
          throw new IllegalArgumentException("Strings must not be null");
      }

      /*
         The difference between this impl. and the previous is that, rather 
         than creating and retaining a matrix of size s.length()+1 by t.length()+1, 
         we maintain two single-dimensional arrays of length s.length()+1.  The first, d,
         is the 'current working' distance array that maintains the newest distance cost
         counts as we iterate through the characters of String s.  Each time we increment
         the index of String t we are comparing, d is copied to p, the second int[].  Doing so
         allows us to retain the previous cost counts as required by the algorithm (taking 
         the minimum of the cost count to the left, up one, and diagonally up and to the left
         of the current cost count being calculated).  (Note that the arrays aren't really 
         copied anymore, just switched...this is clearly much better than cloning an array 
         or doing a System.arraycopy() each time  through the outer loop.)

         Effectively, the difference between the two implementations is this one does not 
         cause an out of memory condition when calculating the LD over two very large strings.
       */

      int n = s.length(); // length of s
      int m = t.length(); // length of t

      if (n == 0) {
          return m;
      } else if (m == 0) {
          return n;
      }

      if (n > m) {
          // swap the input strings to consume less memory
          String tmp = s;
          s = t;
          t = tmp;
          n = m;
          m = t.length();
      }

      int p[] = new int[n+1]; //'previous' cost array, horizontally
      int d[] = new int[n+1]; // cost array, horizontally
      int _d[]; //placeholder to assist in swapping p and d

      // indexes into strings s and t
      int i; // iterates through s
      int j; // iterates through t

      char t_j; // jth character of t

      int cost; // cost

      for (i = 0; i<=n; i++) {
          p[i] = i;
      }

      for (j = 1; j<=m; j++) {
          t_j = t.charAt(j-1);
          d[0] = j;

          for (i=1; i<=n; i++) {
              cost = s.charAt(i-1)==t_j ? 0 : 1;
              // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
              d[i] = Math.min(Math.min(d[i-1]+1, p[i]+1),  p[i-1]+cost);
          }

          // copy current distance counts to 'previous row' distance counts
          _d = p;
          p = d;
          d = _d;
      }

      // our last action in the above loop was to switch d and p, so p now 
      // actually has the most recent cost counts
      return p[n];
  }

	public static void LevDistance(String keyword) throws IOException
	{
		FileWriter writer3 = new FileWriter("C:/Users/arkr94/Desktop/FYP1/NER.txt",true);
	    Scanner sc1 = new Scanner(new File("C:/Users/arkr94/Desktop/FYP1/Indic_NER_CWF.txt"));
	    List<String> stringList = new ArrayList<String>();
//	    Scanner inputword = new Scanner(new File("C:/Users/arkr94/Desktop/FYP1/tamil2english.txt"));
//	    int flag=0;
//	    while(inputword.hasNext())
//	    {	String keyword = inputword.next();
//	    	flag=0;
//	    	String keyword = "chidambaram";	
	   	    String s = keyword.toLowerCase();
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
		   
			while(sc1.hasNext())
			{	
				String x= sc1.next();
				
				if(getLevenshteinDistance(x,s)<2)
				{	System.out.println(getLevenshteinDistance(x,s));
					writer3.write(keyword);
					writer3.write(System.getProperty( "line.separator" ));
//					flag=1;
					break;
				}
			}
//			if(flag==1)
//				stringList.add(keyword);
//	    }
//	
//	for(int i=0;i<stringList.size();i++)
//		System.out.println(stringList.get(i));
//	}
			writer3.close();
	    }
}
