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

	public AddressController(final Home<Address> home) {
		super(home);
	}

	@Override
	protected void saveReferences(final Address element, final Class<?> target) {
		// not needed
	}

}
