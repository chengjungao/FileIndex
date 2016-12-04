package com.chengjungao.index;

import java.io.IOException;
import java.util.Iterator;
import java.util.ServiceLoader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import com.chengjungao.analyzer.ProvideAnalyzer;

public class TestAnalyer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		  Analyzer analyzer = null;
		  ServiceLoader<ProvideAnalyzer> serviceLoader = ServiceLoader.load(ProvideAnalyzer.class);
		  Iterator<ProvideAnalyzer> provideAnalyzers = serviceLoader.iterator();  
	        if (provideAnalyzers.hasNext()) {  
	        	ProvideAnalyzer provideAnalyzer = provideAnalyzers.next();  
	        	analyzer =  provideAnalyzer.Provide();
	        }  
		 TokenStream  token = analyzer.tokenStream("", "test.java");
		  
		  CharTermAttribute cta = token.addAttribute(CharTermAttribute.class);
			token.reset();
			while(token.incrementToken()){
				System.out.println(cta);
			}
	}

}
