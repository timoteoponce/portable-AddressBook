package org.uagrm.addressbook.controller;

import org.uagrm.addressbook.model.Protocol;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.GenericDao;
import org.uagrm.addressbook.model.dao.ProtocolDao;


/**
 * @author Timoteo Ponce
 * 
 */
public class ProtocolController extends AbstractController<Protocol> implements
		Controller<Protocol> {


	private static Controller<Protocol> instance;

	private final ProtocolDao dao = DaoFactory.getInstance(ProtocolDao.class);

	private ProtocolController() {
	}

	public static Controller<Protocol> getInstance() {
		if (instance == null) {
			instance = new ProtocolController();
		}
		return instance;
	}

	@Override
	protected GenericDao<Protocol> getDao() {
		return dao;
	}

	@Override
	protected void saveReferences(Protocol element, Class<?> target) {
		// not needed
	}

}
