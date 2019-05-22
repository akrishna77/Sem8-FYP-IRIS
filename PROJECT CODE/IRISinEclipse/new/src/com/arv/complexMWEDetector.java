package com.arv;

import java.io.*;
import java.util.*;

import edu.mit.jmwe.data.IMWE;
import edu.mit.jmwe.data.IToken;
import edu.mit.jmwe.data.Token;
import edu.mit.jmwe.detect.CompositeDetector;
import edu.mit.jmwe.detect.Consecutive;
import edu.mit.jmwe.detect.IMWEDetector;
import edu.mit.jmwe.detect.InflectionPattern;
import edu.mit.jmwe.detect.MoreFrequentAsMWE;
import edu.mit.jmwe.detect.ProperNouns;
import edu.mit.jmwe.detect.TrulyExhaustive;
import edu.mit.jmwe.index.IMWEIndex;

class complexMWEDetector{
	
	public void complexDetector( IMWEIndex alreadyOpenIndex ) {
		// make some detectors
		IMWEDetector pnDetector = ProperNouns . getInstance () ;
		IMWEDetector exhaustive = new TrulyExhaustive ( alreadyOpenIndex ) ;
		IMWEDetector goodDetector = new MoreFrequentAsMWE ( new InflectionPattern ( new Consecutive ( alreadyOpenIndex ) ) ) ;
		// 	construct two detectors to test
		IMWEDetector d1 = new CompositeDetector ( pnDetector , exhaustive ) ;
		IMWEDetector d2 = new CompositeDetector ( pnDetector , goodDetector ) ;
		// construct test sentence :
		// " Ben Bitdiddle watched as the ship passed out of sight ."
		List<IToken> sentence = new ArrayList<IToken>() ;
		sentence.add(new Token("Ben"," NNP "));
		sentence . add ( new Token ( " Bitdiddle " , " NNP " ) ) ;
		sentence . add ( new Token ( " watched " ," VBD " , " watch " ) ) ;
		sentence . add ( new Token ( " as " ," IN " ) ) ;
		sentence . add ( new Token ( " the " ,	" DT " ) ) ;
		sentence . add ( new Token ( " ship " ,	" NN " ) ) ;
		sentence . add ( new Token ( " passed " , " DT " , " pass " ) ) ;
		sentence . add ( new Token ( " out " ,	" IN " ) ) ;
		sentence . add ( new Token ( " of " , " IN " ) ) ;
		sentence . add ( new Token ( " sight " ,	" NN " ) ) ;
		sentence . add ( new Token ( " . " ,		"."));
		// run bad detector and print out results
		for ( IMWE<IToken> mwe:d1.detect(sentence))
			System.out.println(mwe);
		// run good detector and print out results
		for ( IMWE<IToken> mwe:d2.detect(sentence))
			System.out.println(mwe);
	}
}