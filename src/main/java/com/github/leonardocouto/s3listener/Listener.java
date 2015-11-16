package com.github.leonardocouto.s3listener;

import com.amazonaws.services.s3.model.S3Object;

public interface Listener {
	
	public void run(S3Object object);

}
