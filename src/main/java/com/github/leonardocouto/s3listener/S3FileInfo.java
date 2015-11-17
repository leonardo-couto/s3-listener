package com.github.leonardocouto.s3listener;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import flexjson.JSON;

public class S3FileInfo {
	
	private static final Pattern S3_PATTERN = Pattern.compile("s3://(.+?)/(.+)$");
	
	@JSON
	private String path;
	
	@JSON
	private Map<String, String> options;
	
	public String getKey() {
		Matcher matcher = S3_PATTERN.matcher(this.path);
		matcher.matches();
		return matcher.group(2);
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getBucket() {
		Matcher matcher = S3_PATTERN.matcher(this.path);
		matcher.matches();
		return matcher.group(1);
	}
	
	public boolean isValid() {
		Matcher matcher = S3_PATTERN.matcher(this.path);
		return matcher.matches();
	}
	
	public Map<String, String> getOptions() {
		return this.options == null ? new HashMap<>() : this.options;
	}
	
}
