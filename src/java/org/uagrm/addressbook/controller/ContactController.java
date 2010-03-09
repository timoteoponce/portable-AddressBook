package org.uagrm.addressbook.controller;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.model.Phone;
import org.uagrm.addressbook.model.VirtualAddress;
import org.uagrm.addressbook.model.dao.ContactDao;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.GenericDao;
import org.uagrm.addressbook.model.dao.GroupDao;

import com.sun.jndi.cosnaming.IiopUrl.Address;

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
	public void save(Contact contact) {
		LOG.debug("Saving contact");
		// save or update the group
		if (contact.getId() == null) {
			LOG.debug("Creating...");
			dao.create(contact);
		} else {
			LOG.debug("Updating...");
			dao.update(contact);
		}		
		saveReferences(contact, Address.class);
		saveReferences(contact, Phone.class);
		saveReferences(contact, VirtualAddress.class);
		saveReferences(contact, Group.class);		
		updateAllViews(contact);
	}

	@Override
	protected void saveReferences(Contact contact, Class<?> target) {
		LOG.info("Saving Contact references: " + target );
		if (target.equals(Address.class)) {
			dao.saveAddresses(contact);
		}else if (target.equals(Phone.class)) {
			dao.savePhones(contact);
		}else if (target.equals(VirtualAddress.class)) {
			dao.saveVirtualAddresses(contact);
		}else if (target.equals(Group.class)) {
			dao.saveGroups(contact);
		}
	}

}
