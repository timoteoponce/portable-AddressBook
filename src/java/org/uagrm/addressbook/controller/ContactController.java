package org.uagrm.addressbook.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.dao.ContactDao;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.GroupDao;
import org.uagrm.addressbook.view.View;

/**
 * @author Timoteo Ponce
 * 
 */
public class ContactController implements Controller<Contact> {

	private static final Logger LOG = Logger.getLogger(ContactController.class);

	private static Controller<Contact> instance;

	private final List<View<Contact>> viewList = new ArrayList<View<Contact>>();
	
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
	public void addView(View<Contact> view) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modelChanged(Contact model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeView(View<Contact> view) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAllViews() {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Contact> getElements() {		
		return dao.selectAll();
	}

	@Override
	public void delete(Contact element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(Contact element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveReferences(Contact element, Class<?> target) {
		// TODO Auto-generated method stub

	}

	@Override
	public Contact preloadEntity(Contact entity, Class<?> target) {
		// TODO Auto-generated method stub
		return null;
	}

}
