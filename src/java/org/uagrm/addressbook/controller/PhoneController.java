package org.uagrm.addressbook.controller;

import org.uagrm.addressbook.model.Phone;
import org.uagrm.addressbook.model.dao.Home;


/**
 * @author Timoteo Ponce
 * 
 */
public class PhoneController extends AbstractController<Phone> {

	public PhoneController(final Home<Phone> home) {
		super(home);
	}

	@Override
	protected void saveReferences(final Phone element, final Class<?> target) {
		// not needed
	}

}
