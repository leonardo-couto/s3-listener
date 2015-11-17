package com.github.leonardocouto.s3listener;

import java.util.Map;

import com.amazonaws.services.s3.model.S3Object;

public interface Listener {
	
	public void run(S3Object object, Map<String, String> options);

}
