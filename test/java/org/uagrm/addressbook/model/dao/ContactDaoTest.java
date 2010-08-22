package org.uagrm.addressbook.model.dao;

import org.junit.Test;
import org.uagrm.addressbook.model.Phone;

/**
 * @author Timoteo Ponce
 * 
 */
public class ContactDaoTest implements GenericDaoTestNot {

	private final ContactDao dao = DaoFactory.getInstance(ContactDao.class);

	@Override
	@Test
	public void createEntries() {
		dao.getInstance().setFirstName("Juan Timoteo");
		dao.getInstance().setLastName("Ponce Ortiz");
		dao.persist();
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
		final Home<Phone> phoneDao = DaoFactory
.getInstance(PhoneDao.class);
		phoneDao.getInstance().setHousePhone("1111aa");
		phoneDao.getInstance().setMobilePhone("2222aa");
		phoneDao.getInstance().setWorkPhone("3333aa");

		phoneDao.persist();
		dao.getInstance().getPhones().add(phoneDao.getInstance());

		phoneDao.clearInstance();
		phoneDao.getInstance().setHousePhone("1111bb");
		phoneDao.getInstance().setMobilePhone("2222bb");
		phoneDao.getInstance().setWorkPhone("3333bb");

		phoneDao.persist();
		dao.getInstance().getPhones().add(phoneDao.getInstance());
		dao.savePhones();
	}

	@Override
	public void deleteEntries() {
		dao.delete();
	}

	@Override
	public void updateEntries() {
		// TODO Auto-generated method stub

	}

}
