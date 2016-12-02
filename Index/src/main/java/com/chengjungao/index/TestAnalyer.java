package com.chengjungao.index;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class TestAnalyer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 Analyzer analyzer= new SimpleAnalyzer();
		 TokenStream  token = analyzer.tokenStream("", "test.java");
		  
		  CharTermAttribute cta = token.addAttribute(CharTermAttribute.class);
			token.reset();
			while(token.incrementToken()){
				System.out.println(cta);
			}
	}

}
