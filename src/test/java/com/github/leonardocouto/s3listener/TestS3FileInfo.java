package com.github.leonardocouto.s3listener;

import org.junit.Assert;
import org.junit.Test;

public class TestS3FileInfo {
	
	@Test
	public void regex() {
		String uri = "s3://my-bucket/path/MySubdirectory/MyFile3.txt";
		
		S3FileInfo fileInfo = new S3FileInfo();
		fileInfo.setPath(uri);
		
		Assert.assertTrue(fileInfo.isValid());
		Assert.assertEquals("my-bucket", fileInfo.getBucket());
		Assert.assertEquals("path/MySubdirectory/MyFile3.txt", fileInfo.getKey());
		
	}
	

}
