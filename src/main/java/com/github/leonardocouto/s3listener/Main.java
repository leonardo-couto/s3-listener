package com.github.leonardocouto.s3listener;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import flexjson.JSONDeserializer;

public class Main extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		JSONDeserializer<S3FileInfo> deserializer = new JSONDeserializer<>();
		S3FileInfo fileInfo = deserializer.deserialize(req.getReader(), S3FileInfo.class);
		
		String path = "s3://my-bucket/path/MySubdirectory/MyFile3.txt";
		
		// TODO: fazer o parse no path da classe s3fileinfo
		
		// TODO: gerar um evento, atravez de um factory pegar os listeners
		
	}

}
