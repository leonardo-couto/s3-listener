package com.github.leonardocouto.s3listener;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.s3.AmazonS3Client;
import com.github.leonardocouto.s3listener.context.Context;

import flexjson.JSONDeserializer;

public class Main extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		JSONDeserializer<S3FileInfo> deserializer = new JSONDeserializer<>();
		S3FileInfo fileInfo = deserializer.deserialize(req.getReader(), S3FileInfo.class);
		
		if (!fileInfo.isValid()) {
			error(resp, 400, "Invalid s3 path format, should be like s3://my-bucket/path/to/file.txt");
			return;
		}
		
		if (!Context.bucketName().equals(fileInfo.getBucket())) {
			error(resp, 400, "Invalid bucket-name, see s3.bucket value in s3-listener.properties");
			return;
		}
		
		AmazonS3Client s3Client = new AmazonS3Client();
		EventListener.notify(s3Client.getObject(fileInfo.getBucket(), fileInfo.getKey()), fileInfo.getOptions());
	}
	
	private void error(HttpServletResponse resp, int status, String msg) throws IOException {
		resp.setStatus(status);
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.println(msg);
		writer.flush();
		writer.close();
	}

}
