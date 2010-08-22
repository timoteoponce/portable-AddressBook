package org.uagrm.addressbook.controller;

import java.lang.reflect.Method;

import org.apache.commons.lang.reflect.MethodUtils;
import org.apache.log4j.Logger;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.Home;

public class ControllerFactory {

	private static final Logger LOG = Logger.getLogger(ControllerFactory.class);

	private static <T> T getInstance(Class<T> controllerClass, Class<T> entityClass) {
		T instance = (T) createInstance(controllerClass, entityClass);
		return instance;
	}

	private static <T> T createInstance(Class<T> clazz, Class<T> entityClass) {
		try {
			final Home<T> entityHome = (Home<T>) DaoFactory.getInstance(entityClass);
			Method method = MethodUtils.getAccessibleMethod(clazz, "getInstance", Home.class);
			T instance = (T) method.invoke(clazz, (Home) entityHome);
			return instance;
		} catch (Exception e) {
			throw new IllegalStateException("Problem instantiating Controller class");
		}
	}

	public static <T> Controller<T> getInstanceFor(Class<T> entityClass) {
		final String className = Controller.class.getPackage().getName() + "." + entityClass.getSimpleName() + Controller.class.getSimpleName();
		try {
			final Class<T> outputClass = (Class<T>) Class.forName(className);
			return (Controller<T>) getInstance(outputClass, entityClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Problem instantiating Controller class");
		}
	}
}
