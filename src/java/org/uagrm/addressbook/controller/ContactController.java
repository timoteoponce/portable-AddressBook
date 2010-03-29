package org.uagrm.addressbook.controller;

import java.util.Set;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.model.Phone;
import org.uagrm.addressbook.model.VirtualAddress;
import org.uagrm.addressbook.model.dao.ContactDao;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.GenericDao;

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
	public void save(Contact contact, boolean updateViews) {
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
		if (updateViews) {
			updateAllViews(contact);
		}
	}

	@Override
	protected void saveReferences(Contact contact, Class<?> target) {
		LOG.info("Saving Contact references: " + target);
		if (target.equals(Address.class)) {
			updateAddresses(contact.getAddresses());
			dao.saveAddresses(contact);
		} else if (target.equals(Phone.class)) {
			updatePhones(contact.getPhones());
			dao.savePhones(contact);
		} else if (target.equals(VirtualAddress.class)) {
			updateVirtualAddresses(contact.getVirtualAddresses());
			dao.saveVirtualAddresses(contact);
		} else if (target.equals(Group.class)) {
			//groups are validated in other controller
			dao.saveGroups(contact);
		}
	}

	private void updateAddresses(Set<Address> addresses) {
		Controller<Address> controller = ControllerFactory
				.getInstance(AddressController.class);
		for (Address address : addresses) {
			controller.save(address, false);
		}
	}

	private void updatePhones(Set<Phone> phones) {
		Controller<Phone> controller = ControllerFactory
				.getInstance(PhoneController.class);
		for (Phone phone : phones) {
			controller.save(phone, false);
		}
	}

	private void updateVirtualAddresses(Set<VirtualAddress> vAddresses) {
		Controller<VirtualAddress> controller = ControllerFactory
				.getInstance(VirtualAddressController.class);
		for (VirtualAddress vAddress : vAddresses) {
			controller.save(vAddress, false);
		}
	}

}
