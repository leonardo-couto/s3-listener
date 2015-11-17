package com.github.leonardocouto.s3listener;

import java.io.File;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amazonaws.services.s3.internal.ServiceUtils;
import com.amazonaws.services.s3.model.S3Object;
import com.github.leonardocouto.s3listener.context.Context;

public class CopyFile implements Listener {
	
	private static final Pattern FILE_NAME = Pattern.compile("((.+)\\/)?(.+)$");

	@Override
	public void run(S3Object object, Map<String, String> options) {
		String destinationPath = Context.destinationPath();
		
		String key = object.getKey();
		String targetName = options.get("targetName");
		String fileName = targetName == null ? fileName(key) : targetName;
		
		File file = new File(destinationPath, sanitize(fileName));
		ServiceUtils.downloadObjectToFile(object, file, true, false);
		
	}
	
	private String fileName(String key) {
		Matcher matcher = FILE_NAME.matcher(key);
		matcher.matches();
		return matcher.group(3);
	}
	
	private static String sanitize(String fileName) {
		return fileName.replace("\\", "").replace("/", "");
	}
	
}
