package org.uagrm.addressbook.controller;

import org.uagrm.addressbook.model.Service;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.GenericDao;
import org.uagrm.addressbook.model.dao.ServiceDao;


/**
 * @author Timoteo Ponce
 * 
 */
public class ServiceController extends AbstractController<Service> {


	private static Controller<Service> instance;

	private final ServiceDao dao = DaoFactory.getInstance(ServiceDao.class);

	private ServiceController() {
	}

	public static Controller<Service> getInstance() {
		if (instance == null) {
			instance = new ServiceController();
		}
		return instance;
	}

	@Override
	protected GenericDao<Service> getDao() {
		return dao;
	}

	@Override
	protected void saveReferences(Service element, Class<?> target) {
		// not needed
	}

}
