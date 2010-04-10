package org.uagrm.addressbook.controller;

import org.uagrm.addressbook.model.VirtualAddress;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.GenericDao;
import org.uagrm.addressbook.model.dao.VirtualAddressDao;


/**
 * @author Timoteo Ponce
 * 
 */
public class VirtualAddressController extends
 AbstractController<VirtualAddress> {


	private static Controller<VirtualAddress> instance;

	private final VirtualAddressDao dao = DaoFactory
			.getInstance(VirtualAddressDao.class);

	private VirtualAddressController() {
	}

	public static Controller<VirtualAddress> getInstance() {
		if (instance == null) {
			instance = new VirtualAddressController();
		}
		return instance;
	}

	@Override
	protected GenericDao<VirtualAddress> getDao() {
		return dao;
	}

	@Override
	protected void saveReferences(VirtualAddress element, Class<?> target) {
		// not needed
	}

}
