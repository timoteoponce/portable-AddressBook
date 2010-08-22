package org.uagrm.addressbook.model.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.uagrm.addressbook.model.Contact;

/**
 * @author Timoteo Ponce
 *
 */
public class GroupDaoTest implements GenericDaoTestNot {
	final GroupDao dao = DaoFactory.getInstance(GroupDao.class);

	@Override
	@Test
	public void createEntries() {
		dao.getInstance().setName("Axis");
		dao.getInstance().setDescription("Japan & Germany");

		dao.persist();
		dao.getInstance().getContacts().addAll(createContacts());
		dao.saveContacts();
	}

	private Collection<Contact> createContacts() {
		Collection<Contact> contactList = new ArrayList<Contact>();
		Home<Contact> contactDao = DaoFactory
				.getInstance(ContactDao.class);

		for (int i = 0; i < TEST_ENTRIES; i++) {
			contactDao.clearInstance();
			contactDao.getInstance().setFirstName("test " + i);
			contactDao.getInstance().setLastName("last " + i);
			contactDao.persist();
			contactList.add(contactDao.getInstance());
		}
		return contactList;
	}

	@Override
	public void deleteEntries() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateEntries() {
		// TODO Auto-generated method stub

	}

}
