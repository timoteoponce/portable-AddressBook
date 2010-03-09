package org.uagrm.addressbook.controller;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.dao.ContactDao;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.GenericDao;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Timoteo Ponce
 * 
 */
public class ContactController extends AbstractController<Contact> implements
		Controller<Contact> {

	private static final Logger LOG = Logger.getLogger(ContactController.class);

	private static Controller<Contact> instance;

	private final ContactDao dao = DaoFactory.getInstance(ContactDao.class);

	private ContactController() {
	}

	public static Controller<Contact> getInstance() {
		if (instance == null) {
			instance = new ContactController();
		}
		return instance;
	}

	@Override
	protected GenericDao<Contact> getDao() {
		return dao;
	}

	@Override
	public void save(Contact element) {
		throw new NotImplementedException();
	}

	@Override
	protected void saveReferences(Contact element, Class<?> target) {
		throw new NotImplementedException();
	}

}
