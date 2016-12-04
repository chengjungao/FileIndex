package com.chengjungao.index;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.util.Version;

import com.chengjungao.analyzer.ProvideAnalyzer;

public class WriteIndex {
   static final String indexDir = "D:\\lucene";
   
   public static void main(String[] args) throws IOException, ParseException {
	 // Directory directory = new RAMDirectory();
	   File indexDirFile = new File(indexDir);
	   if (deleteDir(indexDirFile) & indexDirFile.mkdirs()) {
			  Directory directory2 = new NIOFSDirectory(indexDirFile);
			  //使用spi 获得analyze的实现
			  Analyzer analyzer = null;
			  ServiceLoader<ProvideAnalyzer> serviceLoader = ServiceLoader.load(ProvideAnalyzer.class);
			  Iterator<ProvideAnalyzer> provideAnalyzers = serviceLoader.iterator();  
		        if (provideAnalyzers.hasNext()) {  
		        	ProvideAnalyzer provideAnalyzer = provideAnalyzers.next();  
		        	analyzer =  provideAnalyzer.Provide();
		        }  
			  IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_4_10_2,analyzer);
			  IndexWriter indexWriter = new IndexWriter(directory2, conf);
		      File[] RootFiles = File.listRoots();
			  List<FileInfo> files = new ArrayList<>();
			  for (File RootFile : RootFiles) {
				  getFiles(RootFile, files);
			 }
			  for (FileInfo fileinfo : files) {
				  WriteLucene(indexWriter, fileinfo);
			   } 
			  indexWriter.commit();
			  
		}
   }
   
   public static void WriteLucene(IndexWriter indexWriter ,FileInfo file) throws IOException {
	   Document doc = new Document();
	   IndexableField name  = new StringField("name",file.getName() ,Store.YES);
	   IndexableField path  = new StringField("path",file.getPath() ,Store.YES);
	   IndexableField size  = new StringField("size",file.getSize() ,Store.YES);
	   IndexableField extName  = new StringField("extName",file.getExtName() ,Store.YES);
	   doc.add(name);
	   doc.add(path);
	   doc.add(size);
	   doc.add(extName);
	   indexWriter.addDocument(doc);
   }
	
	public  static  void getFiles (File DirFile , List<FileInfo> list){
		if (DirFile != null) {
			list.add(parseFile(DirFile));
			File [] files = DirFile.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.isDirectory()) {
						getFiles(file, list);
					}else{
						list.add(parseFile(file));
					}
				}
			}
		
		}
	}
	
	public static FileInfo parseFile(File file){
		FileInfo fileInfo = new FileInfo();
		fileInfo.setName(file.getName());
		fileInfo.setPath(file.getAbsolutePath());
		fileInfo.setSize(file.length()+"");
		if (file.getName().contains(".")) {
			fileInfo.setExtName(file.getName().substring(file.getName().lastIndexOf(".")+1));
		}else{
			fileInfo.setExtName("");
		}
		return fileInfo;
	}
	
	
	public static boolean deleteDir(File file){
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File childFile : files) {
					if (!deleteDir(childFile)) {
						return false;
					}
				}
			}
			
		}
		return file.delete();
	}
	
}
