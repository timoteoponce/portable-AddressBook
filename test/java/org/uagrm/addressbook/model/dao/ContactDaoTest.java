package org.uagrm.addressbook.model.dao;

import org.junit.Test;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Phone;

/**
 * @author Timoteo Ponce
 *
 */
public class ContactDaoTest implements GenericDaoTest {

	final ContactDao dao = CacheFactory.getInstance(ContactDao.class);

	final Contact contact = new Contact();

	@Override
	@Test
	public void createEntries() {
		contact.setFirstName("Juan Timoteo");
		contact.setLastName("Ponce Ortiz");
		dao.create(contact);
		// phone
		createPhones();
		// address
		createAddresses();
		// VirtualAddress
		createVirtualAddresses();
	}

	private void createVirtualAddresses() {
		// TODO Auto-generated method stub

	}

	private void createAddresses() {
		// TODO Auto-generated method stub

	}

	private void createPhones() {
		final GenericDao<Phone> phoneDao = CacheFactory
				.getInstance(PhoneDao.class);
		Phone phone1 = new Phone();
		phone1.setHousePhone("1111aa");
		phone1.setMobilePhone("2222aa");
		phone1.setWorkPhone("3333aa");

		phoneDao.create(phone1);

		Phone phone2 = new Phone();
		phone2.setHousePhone("1111bb");
		phone2.setMobilePhone("2222bb");
		phone2.setWorkPhone("3333bb");

		phoneDao.create(phone2);

		contact.getPhones().add(phone1);
		contact.getPhones().add(phone2);
		dao.savePhones(contact);
	}

	@Override
	public void deleteEntries() {
		dao.delete(contact);
	}

	@Override
	public void updateEntries() {
		// TODO Auto-generated method stub

	}

}
