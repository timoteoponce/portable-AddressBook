package org.uagrm.addressbook.controller;

import org.uagrm.addressbook.model.Country;
import org.uagrm.addressbook.model.dao.Home;


/**
 * @author Timoteo Ponce
 * 
 */
public class CountryController extends AbstractController<Country> {


	private static Controller<Country> instance;



	private CountryController(Home<Country> home) {
		super(home);
	}

	public static Controller<Country> getInstance(Home<Country> home) {
		if (instance == null) {
			instance = new CountryController(home);
		}
		return instance;
	}

	@Override
	protected void saveReferences(Country element, Class<?> target) {
		// not needed
	}

}
