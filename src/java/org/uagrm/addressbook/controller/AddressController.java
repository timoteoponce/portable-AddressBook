package org.uagrm.addressbook.controller;

import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.model.dao.AddressDao;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.GenericDao;


/**
 * @author Timoteo Ponce
 * 
 */
public class AddressController extends AbstractController<Address> implements
		Controller<Address> {

	// private static final Logger LOG =
	// Logger.getLogger(AddressController.class);

	private static Controller<Address> instance;

	private final AddressDao dao = DaoFactory.getInstance(AddressDao.class);

	private AddressController() {
	}

	public static Controller<Address> getInstance() {
		if (instance == null) {
			instance = new AddressController();
		}
		return instance;
	}

	@Override
	protected GenericDao<Address> getDao() {
		return dao;
	}

	@Override
	protected void saveReferences(Address element, Class<?> target) {
		// not needed
	}

}
