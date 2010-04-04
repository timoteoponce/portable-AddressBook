package org.uagrm.addressbook.controller;


public class ControllerFactory {	

	private static <T> T getInstance(Class<T> clazz) {
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

	public static <T> Controller<T> getInstanceFor(Class<T> clazz) {
		final String className = Controller.class.getPackage().getName() + "." + clazz.getSimpleName() + Controller.class.getSimpleName();
		try {
			final Class<T> outputClass = (Class<T>) Class.forName(className);
			return (Controller<T>) getInstance(outputClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
