package org.uagrm.addressbook.controller;

import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.Home;
import org.uagrm.addressbook.util.BaseFactory;

public class ControllerFactory {

	private static final BaseFactory factory = new BaseFactory();

	static {
		factory.bind(AddressController.class, AddressController.class);
		factory.bind(ContactController.class, ContactController.class);
		factory.bind(CountryController.class, CountryController.class);
		factory.bind(GroupController.class, GroupController.class);
		factory.bind(PhoneController.class, PhoneController.class);
		factory.bind(ServiceController.class, ServiceController.class);
		factory.bind(VirtualAddressController.class, VirtualAddressController.class);
	}

	private static <T> Controller<T> getInstance(final Class<T> controllerClass, final Class<T> entityClass) {
		final Home<T> entityHome = (Home<T>) DaoFactory.getInstance(entityClass);			
		final Controller<T> instance = factory.getInstance(controllerClass, entityHome);
		return instance;
	}

	public static <T> Controller<T> getInstanceFor(final Class<T> entityClass) {
		final String className = Controller.class.getPackage().getName() + "." + entityClass.getSimpleName() + Controller.class.getSimpleName();
		try {
			final Class<T> outputClass = (Class<T>) Class.forName(className);
			return getInstance(outputClass, entityClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Problem instantiating Controller class");
		}
	}
}
