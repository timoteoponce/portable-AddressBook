package org.uagrm.addressbook.controller;

public class ControllerFactory {	

	public static <T> T getInstance(Class<T> clazz) {
		T instance = (T) createInstance(clazz);
		return instance;
	}

	public static <T> T createInstance(Class<T> clazz) {
		T instance = null;
		try {
			instance = (T) clazz.getMethod("getInstance").invoke(null);
		} catch (Exception e) {
			System.err.println("Problem instantiating Controller class");
		}
		return instance;
	}
}
