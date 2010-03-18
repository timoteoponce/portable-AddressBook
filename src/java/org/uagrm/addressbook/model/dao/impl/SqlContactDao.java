package org.uagrm.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.text.StrBuilder;
import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.actions.ActionType;
import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.model.Phone;
import org.uagrm.addressbook.model.ReferenceLink;
import org.uagrm.addressbook.model.VirtualAddress;
import org.uagrm.addressbook.model.dao.ContactDao;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.GroupDao;
import org.uagrm.addressbook.util.ConfigKeys;
import org.uagrm.addressbook.util.Configuration;

/**
 * @author Timoteo Ponce
 * 
 */
public class SqlContactDao extends AbstractSqlDao<Contact> implements
		ContactDao {

	private static final Logger LOG = Logger.getLogger(SqlContactDao.class);

	@Override
	public Set<Contact> getByGroup(final Integer groupId) {
		final StrBuilder buffer = new StrBuilder();
		buffer.append(Configuration.getConfigKey(ConfigKeys.SQL_SELECT_ALL)
				.trim());
		buffer.replaceAll(VAR_COLUMNS, "c.*");
		buffer.replaceAll(VAR_TABLE, TABLE_NAME);
		buffer.append(" c INNER JOIN " + GroupDao.TABLE_GROUP_CONTACTS);
		buffer.append(" gc ON c.ID = gc.ID_CONTACT WHERE gc.ID_GROUP = "
				+ groupId);

		Set<Contact> contacts = new HashSet<Contact>(); 

		ResultSet rs = getDatabaseHandler().executeQuery(buffer.toString());
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
	protected void fillValues(Contact contact, ResultSet rs)
			throws SQLException {
		contact.setId(rs.getInt(1));
		contact.setFirstName(rs.getString(2));
		contact.setLastName(rs.getString(3));
	}

	@Override
	protected String getFields(Contact contact, ActionType action) {
		final StrBuilder buffer = new StrBuilder();
		switch (action) {
		case CREATE:
			buffer.append("(null,'" + contact.getFirstName() + "',");
			buffer.append("'" + contact.getLastName() + "')");
			break;

		case UPDATE:
			buffer.append("FIRST_NAME='" + contact.getFirstName() + "',");
			buffer.append("LAST_NAME='" + contact.getLastName() + "'");
			break;
		}
		return buffer.toString();
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public void loadReferences(Contact contact, Class<?> target) {
		if (target.equals(Group.class)) {
			GroupDao groupDao = DaoFactory.getInstance(GroupDao.class);

			contact.getGroups().clear();
			contact.getGroups().addAll(groupDao.getByContact(contact.getId()));
		}
	}

	@Override
	protected Contact loadValues(ResultSet rs) throws SQLException {
		final Contact contact = new Contact();
		fillValues(contact, rs);
		return contact;














	}

	@Override
	public void saveGroups(Contact contact) {
		deleteReference(new ReferenceLink(null, contact.getId(), null,
				"ID_CONTACT", "GROUP_CONTACTS"));


		for (Group group : contact.getGroups()) {
			createReference(new ReferenceLink(group.getId(), contact.getId(),
					null, null, "GROUP_CONTACTS"));
		}
		LOG.info("Addresses saved: " + contact.getAddresses().size());
	}

	@Override
	public void saveAddresses(Contact contact) {
		deleteReference(new ReferenceLink(contact.getId(), null, "ID_CONTACT",
				null, "CONTACT_ADDRESSES"));

		for (Address address : contact.getAddresses()) {
			if (address.getId() == null) {
				throw new IllegalArgumentException("Unsaved address");
			} else {// update
				createReference(new ReferenceLink(contact.getId(), address
						.getId(), null, null, "CONTACT_ADDRESSES"));
			}
		}
		LOG.info("Addresses saved: " + contact.getAddresses().size());
	}

	@Override
	public void savePhones(Contact contact) {
		deleteReference(new ReferenceLink(contact.getId(), null, "ID_CONTACT",
				null, "CONTACT_PHONES"));


		for (Phone phone : contact.getPhones()) {
			if (phone.getId() == null) {
				throw new IllegalArgumentException("Unsaved phone");
			} else {// update
				createReference(new ReferenceLink(contact.getId(), phone
						.getId(), null, null, "CONTACT_PHONES"));
			}
		}
		LOG.info("Phones saved: " + contact.getPhones().size());
	}

	@Override
	public void saveVirtualAddresses(Contact contact) {
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
