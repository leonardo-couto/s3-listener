package com.github.leonardocouto.s3listener.context;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.github.leonardocouto.s3listener.CopyFile;
import com.github.leonardocouto.s3listener.EventListener;

public class Context implements ServletContextListener {

    private static Context context;
    private final Properties properties;

    public Context() {
        this.properties = new Properties();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	EventListener.register(CopyFile.class);
    	
        try {
        	String path = (String) new InitialContext().lookup("java:comp/env/properties-path");
            InputStream stream = new FileInputStream(path);
            this.properties.load(stream);
            stream.close();
            
        } catch (NamingException | IOException e) {
            throw new RuntimeException(e);
        }
        
        context = this;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    	this.properties.clear();
    	context = null;
    }
    
    public static String bucketName() {
    	return property("s3.bucket");
    }
    
    public static String destinationPath() {
    	return property("destination.path");
    }

    private static String property(String name) {
        return context.properties.getProperty(name);
    }
    
}