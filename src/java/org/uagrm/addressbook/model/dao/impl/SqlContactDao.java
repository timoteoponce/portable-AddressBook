package org.uagrm.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.actions.CommonActions;
import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Phone;
import org.uagrm.addressbook.model.ReferenceLink;
import org.uagrm.addressbook.model.VirtualAddress;
import org.uagrm.addressbook.model.dao.ContactDao;
import org.uagrm.addressbook.model.dao.GroupDao;
import org.uagrm.addressbook.util.ConfigKeys;
import org.uagrm.addressbook.util.Configuration;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Timoteo Ponce
 * 
 */
public class SqlContactDao extends AbstractSqlDao<Contact> implements
	ContactDao {

    private static final Logger LOG = Logger.getLogger(SqlContactDao.class);

    @Override
    public Set<Contact> getByGroup(final Integer groupId) {
	String query = Configuration.getConfigKey(ConfigKeys.SQL_SELECT_ALL)
		.trim();
	query = query.replace(VAR_COLUMNS, "c.*");
	query = query.replace(VAR_TABLE, TABLE_NAME);
	query = query + " c INNER JOIN " + GroupDao.TABLE_GROUP_CONTACTS
		+ " gc ON c.ID = gc.ID_CONTACT WHERE gc.ID_GROUP = " + groupId;
	Set<Contact> contacts = new HashSet<Contact>();

	ResultSet rs = getDatabaseHandler().executeQuery(query);
	try {
	    while (rs.next()) {
		contacts.add(new Contact(Integer.valueOf(rs.getInt(1)), rs
			.getString(2), rs.getString(3)));
	    }
	} catch (SQLException e) {
	    LOG.error(e, e);
	} finally {
	    getDatabaseHandler().closeQuietly(rs);
	}
	return contacts;
    }

    @Override
    protected void fillValues(Contact entity, ResultSet rs) throws SQLException {
	final Contact contact = entity;
	contact.setId(rs.getInt(1));
	contact.setFirstName(rs.getString(2));
	contact.setLastName(rs.getString(3));
    }

    @Override
    protected String getFields(Contact entity, CommonActions action) {
	String out = "";
	final Contact contact = entity;
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
	throw new NotImplementedException();
    }

    @Override
    protected Contact loadValues(ResultSet rs) throws SQLException {
	final Contact contact = new Contact();
	fillValues(contact, rs);
	return contact;
    }

    @Override
    public void saveAddresses(Contact entity) {
	final Contact contact = entity;
	removeAddressReferences(contact);

	for (Address address : contact.getAddresses()) {
	    if (address.getId() == null) {
		throw new IllegalArgumentException("Unsaved address");
	    } else {// update
		addAddressReference(contact, address);
	    }
	}
	LOG.info("Addresses saved: " + contact.getAddresses().size());
    }

    private void addAddressReference(Contact contact, Address address) {
	ReferenceLink ref = new ReferenceLink(contact.getId(), address.getId(),
		null, null, "CONTACT_ADDRESSES");
	createReference(ref);
    }

    private void removeAddressReferences(Contact contact) {
	LOG.debug("Removing contact -> address references.");
	ReferenceLink ref = new ReferenceLink(contact.getId(), null,
		"ID_CONTACT", null, "CONTACT_ADDRESSES");
	deleteReference(ref);
    }

    @Override
    public void savePhones(Contact entity) {
	final Contact contact = entity;
	removePhoneReferences(contact);

	for (Phone phone : contact.getPhones()) {
	    if (phone.getId() == null) {
		throw new IllegalArgumentException("Unsaved phone");
	    } else {// update
		addPhoneReference(contact, phone);
	    }
	}
	LOG.info("Phones saved: " + contact.getPhones().size());
    }

    private void addPhoneReference(Contact contact, Phone phone) {
	ReferenceLink ref = new ReferenceLink(contact.getId(), phone.getId(),
		null, null, "CONTACT_PHONES");
	createReference(ref);
    }

    private void removePhoneReferences(Contact contact) {
	LOG.debug("Removing contact -> phone references.");
	ReferenceLink ref = new ReferenceLink(contact.getId(), null,
		"ID_CONTACT", null, "CONTACT_PHONES");
	deleteReference(ref);
    }

    @Override
    public void saveVirtualAddresses(Contact entity) {
	final Contact contact = entity;
	removeVirtualAddressReferences(contact);

	for (VirtualAddress vaddress : contact.getVirtualAddresses()) {
	    if (vaddress.getId() == null) {
		throw new IllegalArgumentException("Unsaved vaddress");
	    } else {// update
		addVirtualAddressReference(contact, vaddress);
	    }
	}
	LOG.info("VirtualAddresses saved: "
		+ contact.getVirtualAddresses().size());
    }

    private void addVirtualAddressReference(Contact contact,
	    VirtualAddress vaddress) {
	ReferenceLink ref = new ReferenceLink(contact.getId(),
		vaddress.getId(), null, null, "CONTACT_PHONES");
	createReference(ref);
    }

    private void removeVirtualAddressReferences(Contact contact) {
	LOG.debug("Removing contact -> virtualAddress references.");
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
