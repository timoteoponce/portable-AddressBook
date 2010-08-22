package org.uagrm.addressbook.controller;

import org.uagrm.addressbook.model.Phone;
import org.uagrm.addressbook.model.dao.Home;


/**
 * @author Timoteo Ponce
 * 
 */
public class PhoneController extends AbstractController<Phone> {


	private static Controller<Phone> instance;

	private PhoneController(Home<Phone> home) {
		super(home);
	}

	public static Controller<Phone> getInstance(Home<Phone> home) {
		if (instance == null) {
			instance = new PhoneController(home);
		}
		return instance;
	}

	@Override
	protected void saveReferences(Phone element, Class<?> target) {
		// not needed
	}

}
