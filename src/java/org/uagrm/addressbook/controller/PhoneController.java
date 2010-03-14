package org.uagrm.addressbook.controller;

import org.uagrm.addressbook.model.Phone;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.GenericDao;
import org.uagrm.addressbook.model.dao.PhoneDao;


/**
 * @author Timoteo Ponce
 * 
 */
public class PhoneController extends AbstractController<Phone> implements
		Controller<Phone> {


	private static Controller<Phone> instance;

	private final PhoneDao dao = DaoFactory.getInstance(PhoneDao.class);

	private PhoneController() {
	}

	public static Controller<Phone> getInstance() {
		if (instance == null) {
			instance = new PhoneController();
		}
		return instance;
	}

	@Override
	protected GenericDao<Phone> getDao() {
		return dao;
	}

	@Override
	protected void saveReferences(Phone element, Class<?> target) {
		// not needed
	}

}
