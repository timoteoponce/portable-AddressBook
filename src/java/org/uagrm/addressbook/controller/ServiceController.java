package org.uagrm.addressbook.controller;

import org.uagrm.addressbook.model.Service;
import org.uagrm.addressbook.model.dao.Home;


/**
 * @author Timoteo Ponce
 * 
 */
public class ServiceController extends AbstractController<Service> {

	public ServiceController(final Home<Service> home) {
		super(home);
	}

	@Override
	protected void saveReferences(final Service element, final Class<?> target) {
		// not needed
	}

}
