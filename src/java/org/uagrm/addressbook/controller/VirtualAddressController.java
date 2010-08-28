package org.uagrm.addressbook.controller;

import org.uagrm.addressbook.model.VirtualAddress;
import org.uagrm.addressbook.model.dao.Home;


/**
 * @author Timoteo Ponce
 * 
 */
public class VirtualAddressController extends
 AbstractController<VirtualAddress> {

	public VirtualAddressController(final Home<VirtualAddress> home) {
		super(home);
	}

	@Override
	protected void saveReferences(final VirtualAddress element, final Class<?> target) {
		// not needed
	}

}
