package com.github.leonardocouto.s3listener;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.s3.model.S3Object;

public class EventListener {
	
	private static List<Class<? extends Listener>> listeners = new ArrayList<>(); 
	
	public static void register(Class<? extends Listener> listener) {
		listeners.add(listener);
	}
	
	public static void notify(S3Object file) {
		listeners.stream().map(Reflection::newInstance).forEach((listener) -> listener.run(file));
	}
	
	private static class Reflection {

		public static <T extends Listener> Listener newInstance(Class<T> clazz) {
			try {
				return clazz.newInstance();
			} catch (ReflectiveOperationException e) {
				throw new RuntimeException(e);
			}
		}
		
	} 
	
}
