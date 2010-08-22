package org.uagrm.addressbook.controller;

import org.uagrm.addressbook.model.VirtualAddress;
import org.uagrm.addressbook.model.dao.Home;


/**
 * @author Timoteo Ponce
 * 
 */
public class VirtualAddressController extends
 AbstractController<VirtualAddress> {


	private static Controller<VirtualAddress> instance;

	private VirtualAddressController(Home<VirtualAddress> home) {
		super(home);
	}

	public static Controller<VirtualAddress> getInstance(Home<VirtualAddress> home) {
		if (instance == null) {
			instance = new VirtualAddressController(home);
		}
		return instance;
	}

	@Override
	protected void saveReferences(VirtualAddress element, Class<?> target) {
		// not needed
	}

}
