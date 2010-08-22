package org.uagrm.addressbook.controller;

import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.model.dao.Home;


/**
 * @author Timoteo Ponce
 * 
 */
public class AddressController extends AbstractController<Address> {

	// private static final Logger LOG =
	// Logger.getLogger(AddressController.class);

	private static Controller<Address> instance;


	private AddressController(Home<Address> home) {
		super(home);
	}

	public static Controller<Address> getInstance(Home<Address> home) {
		if (instance == null) {
			instance = new AddressController(home);
		}
		return instance;
	}

	@Override
	protected void saveReferences(Address element, Class<?> target) {
		// not needed
	}

}
