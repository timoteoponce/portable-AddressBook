package org.uagrm.addressbook.controller;

import org.uagrm.addressbook.model.Country;
import org.uagrm.addressbook.model.dao.Home;


/**
 * @author Timoteo Ponce
 * 
 */
public class CountryController extends AbstractController<Country> {

	public CountryController(final Home<Country> home) {
		super(home);
	}

	@Override
	protected void saveReferences(final Country element, final Class<?> target) {
		// not needed
	}

}
