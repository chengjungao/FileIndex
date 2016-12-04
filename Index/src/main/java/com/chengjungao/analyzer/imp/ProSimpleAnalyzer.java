package com.chengjungao.analyzer.imp;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;

import com.chengjungao.analyzer.ProvideAnalyzer;

public class ProSimpleAnalyzer implements ProvideAnalyzer{
	public Analyzer Provide(){
		return new SimpleAnalyzer();
	}
}
