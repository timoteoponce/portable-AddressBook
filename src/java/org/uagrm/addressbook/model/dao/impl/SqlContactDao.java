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
import org.uagrm.addressbook.model.VirtualAddress;
import org.uagrm.addressbook.model.dao.AddressDao;
import org.uagrm.addressbook.model.dao.ContactDao;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.GroupDao;
import org.uagrm.addressbook.model.dao.PhoneDao;
import org.uagrm.addressbook.model.dao.ReferenceLink;
import org.uagrm.addressbook.model.dao.SqlOperation;
import org.uagrm.addressbook.model.dao.VirtualAddressDao;

/**
 * @author Timoteo Ponce
 * 
 */
public class SqlContactDao extends AbstractSqlDao<Contact> implements
		ContactDao {

	private static final Logger LOG = Logger.getLogger(SqlContactDao.class);

	public SqlContactDao() {
		super(Contact.class);
	}

	@Override
	public Set<Contact> getByGroup(Group group) {
		final QueryBuilder builder = QueryBuilder.createQuery(SqlOperation.SQL_SELECT_ALL);
		builder.setVariable(VAR_COLUMNS, "c.*");
		builder.setVariable(VAR_TABLE, TABLE_NAME);
		builder.append(" c INNER JOIN " + GroupDao.TABLE_GROUP_CONTACTS);
		builder.append(" gc ON c.ID = gc.ID_CONTACT WHERE gc.ID_GROUP = " + group.getId());

		final Set<Contact> contacts = new HashSet<Contact>();
		ResultSet rs = getDatabaseHandler().executeQuery(builder.getQuery());
		try {
			while (rs.next()) {
				Contact contact = new Contact();
				fillValues(contact, rs);
				contacts.add(contact);
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
		contact.setId(rs.getInt("ID"));
		contact.setFirstName(rs.getString("FIRST_NAME"));
		contact.setLastName(rs.getString("LAST_NAME"));
		contact.setImage(rs.getString("IMAGE"));
	}

	@Override
	protected String getFields(ActionType action) {
		final StrBuilder buffer = new StrBuilder();
		final Contact contact = getInstance();

		switch (action) {
		case CREATE:
			buffer.append("(null,'" + contact.getFirstName() + "',");
			buffer.append("'" + contact.getLastName() + "',");
			buffer.append("'" + contact.getImage() + "')");
			break;

		case UPDATE:
			buffer.append("FIRST_NAME='" + contact.getFirstName() + "',");
			buffer.append("LAST_NAME='" + contact.getLastName() + "',");
			buffer.append("IMAGE='" + contact.getImage() + "'");
			break;
		}
		return buffer.toString();
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public void loadReferences(final Contact contact) {
		GroupDao groupDao = DaoFactory.getInstance(GroupDao.class);
		contact.getGroups().clear();
		contact.getGroups().addAll(groupDao.getByContact(contact));

		AddressDao addressDao = DaoFactory.getInstance(AddressDao.class);
		contact.getAddresses().clear();
		contact.getAddresses().addAll(addressDao.getByContact(contact));

		PhoneDao phoneDao = DaoFactory.getInstance(PhoneDao.class);
		contact.getPhones().clear();
		contact.getPhones().addAll(phoneDao.getByContact(contact));

		VirtualAddressDao vAddressDao = DaoFactory.getInstance(VirtualAddressDao.class);
		contact.getVirtualAddresses().clear();
		contact.getVirtualAddresses().addAll(vAddressDao.getByContact(contact));
	}
	

	@Override
	protected Contact loadValues(ResultSet rs) throws SQLException {
		final Contact contact = new Contact();
		fillValues(contact, rs);
		return contact;
	}

	@Override
	public void saveGroups() {
		deleteReference(new ReferenceLink(null, getId().intValue(), null,
				"ID_CONTACT", "GROUP_CONTACTS"));


		for (Group group : getInstance().getGroups()) {
			createReference(new ReferenceLink(group.getId(), getId().intValue(),
					null, null, "GROUP_CONTACTS"));
		}
		LOG.info("Addresses saved: " + getInstance().getAddresses().size());
	}

	@Override
	public void saveAddresses() {
		deleteReference(new ReferenceLink(getId().intValue(), null, "ID_CONTACT",
				null, "CONTACT_ADDRESSES"));

		for (Address address : getInstance().getAddresses()) {
			if (address.getId() == null) {
				throw new IllegalArgumentException("Unsaved address");
			} else {// update
				createReference(new ReferenceLink(getInstance().getId(), address
						.getId(), null, null, "CONTACT_ADDRESSES"));
			}
		}
		LOG.info("Addresses saved: " + getInstance().getAddresses().size());
	}

	@Override
	public void savePhones() {
		deleteReference(new ReferenceLink(getInstance().getId(), null, "ID_CONTACT",
				null, "CONTACT_PHONES"));
		for (Phone phone : getInstance().getPhones()) {
			if (phone.getId() == null) {
				throw new IllegalArgumentException("Unsaved phone");
			} else {// update
				createReference(new ReferenceLink(getInstance().getId(), phone
						.getId(), null, null, "CONTACT_PHONES"));
			}
		}
		LOG.info("Phones saved: " + getInstance().getPhones().size());
	}

	@Override
	public void saveVirtualAddresses() {
		deleteReference(new ReferenceLink(getInstance().getId(), null,
				"ID_CONTACT", null, "CONTACT_VIRTUAL_ADDRESSES"));		

		for (VirtualAddress vaddress : getInstance().getVirtualAddresses()) {
			if (vaddress.getId() == null) {
				throw new IllegalArgumentException("Unsaved vaddress");
			} else {// update
				createReference(new ReferenceLink(getInstance().getId(),
				vaddress.getId(), null, null, "CONTACT_VIRTUAL_ADDRESSES"));
			}
		}
		LOG.info("VirtualAddresses saved: "
 + getInstance().getVirtualAddresses().size());
	}

	@Override
	protected Collection<ReferenceLink> getReferences() {
		Collection<ReferenceLink> list = new ArrayList<ReferenceLink>();

		list.add(new ReferenceLink(getId().intValue(), null, "ID_CONTACT", null,
				"CONTACT_VIRTUAL_ADDRESSES"));

		list.add(new ReferenceLink(getId().intValue(), null, "ID_CONTACT", null,
				"CONTACT_PHONES"));

		list.add(new ReferenceLink(getId().intValue(), null, "ID_CONTACT", null,
				"CONTACT_ADDRESSES"));

		return list;
	}

}
