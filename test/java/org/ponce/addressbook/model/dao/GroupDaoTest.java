package org.ponce.addressbook.model.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.ponce.addressbook.model.Contact;
import org.ponce.addressbook.model.Group;

public class GroupDaoTest implements GenericDaoTest {
	final GroupDao dao = (GroupDao) CacheFactory.getInstance(GroupDao.class);

	@Override
	@Test
	public void createEntries() {
		final Group group = new Group();
		group.setName("Axis");
		group.setDescription("Japan & Germany");

		dao.create(group);
		group.getContacts().addAll(createContacts());
		dao.saveContacts(group);
	}

	private Collection<Contact> createContacts() {
		Collection<Contact> contactList = new ArrayList<Contact>();
		GenericDao<Contact> contactDao = (GenericDao<Contact>) CacheFactory
				.getInstance(ContactDao.class);

		for (int i = 0; i < TEST_ENTRIES; i++) {
			Contact contact = new Contact();
			contact.setFirstName("test " + i);
			contact.setLastName("last " + i);
			contactDao.create(contact);
			contactList.add(contact);
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
