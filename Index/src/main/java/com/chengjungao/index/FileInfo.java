package com.chengjungao.index;

public class FileInfo {
	/** 	文件名称		*/
	private String name;
	/** 	文件路径		*/
	private String path;
	/** 	文件大小		*/
	private String size;
	/** 	文件扩展名		*/
	private String extName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getExtName() {
		return extName;
	}
	public void setExtName(String extName) {
		this.extName = extName;
	}
}
