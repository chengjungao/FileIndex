package com.chengjungao.analyzer.imp;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;

import com.chengjungao.analyzer.ProvideAnalyzer;

@SuppressWarnings("deprecation")
public class ProChineseAnalyzer implements ProvideAnalyzer{
	public Analyzer Provide(){
		return new ChineseAnalyzer();
	}
}
