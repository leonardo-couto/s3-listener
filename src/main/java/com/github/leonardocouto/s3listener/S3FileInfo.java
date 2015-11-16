package com.github.leonardocouto.s3listener;

import flexjson.JSON;

public class S3FileInfo {
	
	@JSON
	private String path;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
