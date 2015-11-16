package com.github.leonardocouto.s3listener;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amazonaws.services.s3.internal.ServiceUtils;
import com.amazonaws.services.s3.model.S3Object;
import com.github.leonardocouto.s3listener.context.Context;

public class CopyFile implements Listener {
	
	private static final Pattern FILE_NAME = Pattern.compile("((.+)\\/)?(.+)$");

	@Override
	public void run(S3Object object) {
		String destinationPath = Context.destinationPath();
		
		String key = object.getKey();
		String fileName = fileName(key);
		
		File file = new File(destinationPath, fileName);
		ServiceUtils.downloadObjectToFile(object, file, true, false);
		
	}
	
	private String fileName(String key) {
		Matcher matcher = FILE_NAME.matcher(key);
		matcher.matches();
		return matcher.group(3);
	}

}
