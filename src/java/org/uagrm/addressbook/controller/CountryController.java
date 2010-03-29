package org.uagrm.addressbook.controller;

import org.apache.commons.lang.NotImplementedException;
import org.uagrm.addressbook.model.Country;
import org.uagrm.addressbook.model.dao.CountryDao;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.GenericDao;


/**
 * @author Timoteo Ponce
 * 
 */
public class CountryController extends AbstractController<Country> implements
 Controller<Country> {


	private static Controller<Country> instance;

	private final CountryDao dao = DaoFactory.getInstance(CountryDao.class);

	private CountryController() {
	}

	public static Controller<Country> getInstance() {
		if (instance == null) {
			instance = new CountryController();
		}
		return instance;
	}

	@Override
	protected GenericDao<Country> getDao() {
		return dao;
	}

	@Override
	protected void saveReferences(Country element, Class<?> target) {
		throw new NotImplementedException();
	}

}
