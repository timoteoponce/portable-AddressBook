package org.ponce.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.ponce.addressbook.controller.actions.CommonActions;
import org.ponce.addressbook.model.Address;
import org.ponce.addressbook.model.Contact;
import org.ponce.addressbook.model.Phone;
import org.ponce.addressbook.model.ReferenceLink;
import org.ponce.addressbook.model.VirtualAddress;
import org.ponce.addressbook.model.dao.ContactDao;
import org.ponce.addressbook.model.dao.GenericDao;
import org.ponce.addressbook.model.dao.GroupDao;
import org.ponce.addressbook.util.Configuration;

public class SqlContactDao extends AbstractSqlDao<Contact> implements ContactDao {

	private static final Logger log = Logger.getLogger(SqlContactDao.class);

	// private boolean belongsTo(Contact entity, Group group) {
	// String query = Configuration.getConfigKey(GenericDao.SQL_SELECT).trim();
	// query = query.replace(GenericDao.VAR_COLUMNS, "*");
	// query = query.replace(GenericDao.VAR_TABLE,
	// GroupDao.CONTACT_JOIN_TABLE_NAME);
	// query = query.replace(GenericDao.VAR_CONDITION, "ID_GROUP = "
	// + group.getId() + " AND ID_CONTACT = " + entity.getId());
	//
	// ResultSet rs = handler.executeQuery(query);
	// try {
	// return rs.next();
	// } catch (SQLException e) {
	// log.error(e, e);
	// } finally {
	// handler.closeQuietly(rs);
	// }
	// return false;
	// }

	@Override
	public Set<Contact> getByGroup(final Integer groupId) {
		String query = Configuration.getConfigKey(GenericDao.SQL_SELECT_ALL)
				.trim();
		query = query.replace(GenericDao.VAR_COLUMNS, "c.*");
		query = query.replace(GenericDao.VAR_TABLE, TABLE_NAME);
		query = query + " c INNER JOIN " + GroupDao.CONTACT_JOIN_TABLE_NAME
				+ " gc ON c.ID = gc.ID_CONTACT";
		Set<Contact> contacts = new HashSet<Contact>();

		ResultSet rs = getDatabaseHandler().executeQuery(query);
		try {
			while (rs.next()) {
				contacts.add(new Contact(Integer.valueOf(rs.getInt(1)), rs
						.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			getDatabaseHandler().closeQuietly(rs);
		}
		return contacts;
	}

	// public static void main(String[] args) {
	// ContactDao contactDao = (ContactDao) CacheFactory
	// .getInstance(SqlContactDao.class);
	// GroupDao groupDao = (GroupDao) CacheFactory
	// .getInstance(SqlGroupDao.class);
	//
	// Group myGroup = (Group) groupDao.read(new Group(1, null, null));
	// log.info("group: " + myGroup.toString());
	//
	// Contact contact = new Contact(null, "Timo", "Canaca");
	// contact.getGroups().add(myGroup);
	// contactDao.create(contact);
	// contactDao.saveGroups(contact);
	//
	// contact = new Contact(null, "Dios", "Eolo");
	// contact.getGroups().add(myGroup);
	// contactDao.create(contact);
	// contactDao.saveGroups(contact);
	// }

	@Override
	protected void fillValues(Contact entity, ResultSet rs) throws SQLException {
		final Contact contact = (Contact) entity;
		contact.setId(rs.getInt(1));
		contact.setFirstName(rs.getString(2));
		contact.setLastName(rs.getString(3));
	}

	@Override
	protected String getFields(Contact entity, CommonActions action) {
		String out = "";
		final Contact contact = (Contact) entity;
		switch (action) {
		case CREATE:
			out = "(null,'" + contact.getFirstName() + "','"
					+ contact.getLastName() + "');";
			break;

		case UPDATE:
			out = "FIRST_NAME='" + contact.getFirstName() + "',LAST_NAME='"
					+ contact.getLastName() + "'";
			break;
		}
		return out;
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public void loadReferences(Contact entity, Class<?> target) {
		// TODO implement
	}

	@Override
	protected Contact loadValues(ResultSet rs) throws SQLException {
		final Contact contact = new Contact();
		fillValues(contact, rs);
		return contact;
	}

	@Override
	public void saveAddresses(Contact entity) {
		final Contact contact = (Contact) entity;
		removeAddressReferences(contact);

		for (Address address : contact.getAddresses()) {
			if (address.getId() == null) {
				throw new IllegalArgumentException("Unsaved address");
			} else {// update
				addAddressReference(contact, address);
			}
		}
		log.info("Addresses saved: " + contact.getAddresses().size());
	}

	private void addAddressReference(Contact contact, Address address) {
		ReferenceLink ref = new ReferenceLink(contact.getId(), address.getId(),
				null, null, "CONTACT_ADDRESSES");
		createReference(ref);
	}

	private void removeAddressReferences(Contact contact) {
		log.debug("Removing contact -> address references.");
		ReferenceLink ref = new ReferenceLink(contact.getId(), null,
				"ID_CONTACT", null, "CONTACT_ADDRESSES");
		deleteReference(ref);
	}

	@Override
	public void savePhones(Contact entity) {
		final Contact contact = (Contact) entity;
		removePhoneReferences(contact);

		for (Phone phone : contact.getPhones()) {
			if (phone.getId() == null) {
				throw new IllegalArgumentException("Unsaved phone");
			} else {// update
				addPhoneReference(contact, phone);
			}
		}
		log.info("Phones saved: " + contact.getPhones().size());
	}

	private void addPhoneReference(Contact contact, Phone phone) {
		ReferenceLink ref = new ReferenceLink(contact.getId(), phone.getId(),
				null, null, "CONTACT_PHONES");
		createReference(ref);
	}

	private void removePhoneReferences(Contact contact) {
		log.debug("Removing contact -> phone references.");
		ReferenceLink ref = new ReferenceLink(contact.getId(), null,
				"ID_CONTACT", null, "CONTACT_PHONES");
		deleteReference(ref);
	}

	@Override
	public void saveVirtualAddresses(Contact entity) {
		final Contact contact = (Contact) entity;
		removeVirtualAddressReferences(contact);

		for (VirtualAddress vaddress : contact.getVirtualAddresses()) {
			if (vaddress.getId() == null) {
				throw new IllegalArgumentException("Unsaved vaddress");
			} else {// update
				addVirtualAddressReference(contact, vaddress);
			}
		}
		log.info("VirtualAddresses saved: "
				+ contact.getVirtualAddresses().size());
	}

	private void addVirtualAddressReference(Contact contact,
			VirtualAddress vaddress) {
		ReferenceLink ref = new ReferenceLink(contact.getId(),
				vaddress.getId(), null, null, "CONTACT_PHONES");
		createReference(ref);
	}

	private void removeVirtualAddressReferences(Contact contact) {
		log.debug("Removing contact -> virtualAddress references.");
		ReferenceLink ref = new ReferenceLink(contact.getId(), null,
				"ID_CONTACT", null, "CONTACT_VIRTUAL_ADDRESSES");
		deleteReference(ref);
	}

	@Override
	protected Collection<ReferenceLink> getReferences(Contact entity) {
		Collection<ReferenceLink> list = new ArrayList<ReferenceLink>();

		list.add(new ReferenceLink(entity.getId(), null, "ID_CONTACT", null,
				"CONTACT_VIRTUAL_ADDRESSES"));

		list.add(new ReferenceLink(entity.getId(), null, "ID_CONTACT", null,
				"CONTACT_PHONES"));

		list.add(new ReferenceLink(entity.getId(), null, "ID_CONTACT", null,
				"CONTACT_ADDRESSES"));

		return list;
	}

}
