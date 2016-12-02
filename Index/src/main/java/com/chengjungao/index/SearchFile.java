package com.chengjungao.index;

import java.io.File;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;

public class SearchFile {
	
	static final String fields[] = { "name","extName"};;  
	static final String[] keywords = {"cheng*","java"};
	static final int rows = 100;
	
	
	public static void main(String[] args) throws Exception {
		Analyzer analyzer= new SimpleAnalyzer();
		File indexDirFile = new File(WriteIndex.indexDir);
		Directory directory2 = new NIOFSDirectory(indexDirFile);
		IndexReader reader = DirectoryReader.open(directory2);
		IndexSearcher searcher = new IndexSearcher(reader);
		BooleanClause.Occur occurs[] = new BooleanClause.Occur[1];
		occurs[0] = BooleanClause.Occur.SHOULD;
		Query query = MultiFieldQueryParser.parse(keywords, fields, analyzer);
		TopDocs top = searcher.search(query, null, rows);
		ScoreDoc[] docs = top.scoreDocs;
		System.out.println(docs.length);
		for (ScoreDoc scoreDoc : docs) {
			Document doc = searcher.doc(scoreDoc.doc);
			List<IndexableField> fss = doc.getFields();
			for (IndexableField indexableField : fss) {
				String val = indexableField.stringValue();
				System.out.print(indexableField.name() + "-" + val + " ");
			}
			System.out.println("");
		}
	}
}
