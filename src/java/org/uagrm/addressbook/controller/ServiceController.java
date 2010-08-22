package org.uagrm.addressbook.controller;

import org.uagrm.addressbook.model.Service;
import org.uagrm.addressbook.model.dao.Home;


/**
 * @author Timoteo Ponce
 * 
 */
public class ServiceController extends AbstractController<Service> {


	private static Controller<Service> instance;

	private ServiceController(Home<Service> home) {
		super(home);
	}

	public static Controller<Service> getInstance(Home<Service> home) {
		if (instance == null) {
			instance = new ServiceController(home);
		}
		return instance;
	}


	@Override
	protected void saveReferences(Service element, Class<?> target) {
		// not needed
	}

}
