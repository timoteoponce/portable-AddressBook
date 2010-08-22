package org.uagrm.addressbook.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.model.Phone;
import org.uagrm.addressbook.model.VirtualAddress;
import org.uagrm.addressbook.model.dao.ContactDao;
import org.uagrm.addressbook.model.dao.Home;
import org.uagrm.addressbook.model.dto.EntityStatus;
import org.uagrm.addressbook.model.dto.StatusType;

/**
 * @author Timoteo Ponce
 * 
 */
public class ContactController extends AbstractController<Contact> {

	private static final Logger LOG = Logger.getLogger(ContactController.class);

	private static Controller<Contact> instance;

	private ContactController(Home<Contact> home) {
		super(home);
	}

	public static Controller<Contact> getInstance(Home<Contact> home) {
		if (instance == null) {
			instance = new ContactController(home);
		}
		return instance;
	}

	@Override
	public void save(Contact contact, boolean updateViews) {
		LOG.debug("Saving contact");
		getHome().setInstance(contact);

		// save or update the group
		if (contact.getId() == null) {
			LOG.debug("Creating...");
			getHome().persist();
		} else {
			LOG.debug("Updating...");
			getHome().update();
		}
		saveReferences(contact, Address.class);
		saveReferences(contact, Phone.class);
		saveReferences(contact, VirtualAddress.class);
		saveReferences(contact, Group.class);
		if (updateViews) {
			updateAllViews(EntityStatus.create(contact, StatusType.UPDATED));
		}
		getHome().clearInstance();
	}

	private ContactDao getSpecificHome() {
		return (ContactDao) getHome();
	}

	@Override
	protected void saveReferences(Contact contact, Class<?> target) {
		LOG.info("Saving Contact references: " + target);
		getHome().setInstance(contact);

		if (target.equals(Address.class)) {
			updateAddresses(contact.getAddresses());
			getSpecificHome().saveAddresses();
		} else if (target.equals(Phone.class)) {
			updatePhones(contact.getPhones());
			getSpecificHome().savePhones();
		} else if (target.equals(VirtualAddress.class)) {
			updateVirtualAddresses(contact.getVirtualAddresses());
			getSpecificHome().saveVirtualAddresses();
		} else if (target.equals(Group.class)) {
			//groups are validated in other controller
			getSpecificHome().saveGroups();
		}
		getHome().clearInstance();
	}

	private void updateAddresses(Set<Address> addresses) {
		Controller<Address> controller = ControllerFactory
.getInstanceFor(Address.class);
		for (Address address : addresses) {
			controller.save(address, false);
		}
	}

	private void updatePhones(Set<Phone> phones) {
		Controller<Phone> controller = ControllerFactory
.getInstanceFor(Phone.class);
		for (Phone phone : phones) {
			controller.save(phone, false);
		}
	}

	private void updateVirtualAddresses(Set<VirtualAddress> vAddresses) {
		Controller<VirtualAddress> controller = ControllerFactory
.getInstanceFor(VirtualAddress.class);
		for (VirtualAddress vAddress : vAddresses) {
			controller.save(vAddress, false);
		}
	}

	@Override
	public <K> java.util.Collection<Contact> getElementsBy(java.lang.Class<K> targetClass, K target) {
		final List<Contact> list = new ArrayList<Contact>();
		if (targetClass.equals(Group.class) && ((Group) target).getId() != null) {			
			Group group = (Group) target;
			list.addAll(getSpecificHome().getByGroup(group));
		} else {
			refreshElementList();
			list.addAll((List<Contact>) getElements());
		}
		return list;
	}


}
