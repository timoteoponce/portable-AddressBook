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
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.model.dao.ContactDao;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.GroupDao;
import org.uagrm.addressbook.model.dao.ReferenceLink;
import org.uagrm.addressbook.model.dao.SqlOperation;

/**
 * @author Timoteo Ponce
 * 
 */
public class SqlGroupDao extends AbstractSqlDao<Group> implements GroupDao {

	private static final Logger LOG = Logger.getLogger(SqlGroupDao.class);

	@Override
	public void loadReferences(Group group, Class<?> clazz) { 
		if (clazz.equals(Contact.class)) {			
			ContactDao contactDao = DaoFactory.getInstance(ContactDao.class);

			group.getContacts().clear();
			group.getContacts().addAll(contactDao.getByGroup(group));
		}
	}

	@Override
	public Set<Group> getByContact(Contact contact) {
		final QueryBuilder builder = QueryBuilder.createQuery(SqlOperation.SQL_SELECT_ALL);
		builder.setVariable(VAR_COLUMNS, "g.*");
		builder.setVariable(VAR_TABLE, TABLE_NAME);
		builder.append(" g INNER JOIN " + GroupDao.TABLE_GROUP_CONTACTS);
		builder.append(" gc ON g.ID = gc.ID_GROUP WHERE gc.ID_CONTACT = " + contact.getId());
		
		final Set<Group> groups = new HashSet<Group>();
		ResultSet rs = getDatabaseHandler().executeQuery(builder.getQuery());
		try {
			while (rs.next()) {
				groups.add(new Group(Integer.valueOf(rs.getInt(1)), rs
						.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			LOG.error(e, e);
		} finally {
			getDatabaseHandler().closeQuietly(rs);
		}
		return groups;
	}

	@Override
	public void saveContacts(Group group) {		
		removeContactReferences(group);

		for (Contact contact : group.getContacts()) {
			if (contact.getId() == null) {
				throw new IllegalArgumentException("Unsaved contact");
			} else {// update
				addContactReference(group, contact);
			}
		}
		LOG.info("Contacts saved: " + group.getContacts().size());
	}

	private void addContactReference(Group group, Contact contact) {
		LOG.info("Adding contact '" + contact.getId() + "' to group -> "
				+ group.getId());
		ReferenceLink ref = new ReferenceLink(group.getId(), contact.getId(),
				null, null, GroupDao.TABLE_GROUP_CONTACTS);
		createReference(ref);
	}

	private void removeContactReferences(Group group) {
		LOG.debug("Removing group -> contact references.");
		deleteReference(getReferences(group).iterator().next());
	}

	@Override
	protected void fillValues(Group group, ResultSet rs) throws SQLException {		
		group.setId(rs.getInt("ID"));
		group.setName(rs.getString("NAME"));
		group.setDescription(rs.getString("DESCRIPTION"));
		group.setImage(rs.getString("IMAGE"));
	}

	@Override
	protected String getFields(Group group, ActionType action) {
		final StrBuilder buffer = new StrBuilder();
		switch (action) {
		case CREATE:
			buffer.append("(null,'" + group.getName() + "',");
			buffer.append("'" + group.getDescription() + "',");
			buffer.append("'" + group.getImage() + "')");
			break;

		case UPDATE:
			buffer.append("NAME='" + group.getName() + "',");
			buffer.append("DESCRIPTION='" + group.getDescription() + "',");
			buffer.append("IMAGE='" + group.getImage() + "'");
			break;
		}
		return buffer.toString();
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	protected Group loadValues(ResultSet rs) throws SQLException {
		Group group = new Group();
		fillValues(group, rs);

		return group;
	}

	@Override
	protected Collection<ReferenceLink> getReferences(Group entity) {
		Collection<ReferenceLink> list = new ArrayList<ReferenceLink>();
		list.add(new ReferenceLink(entity.getId(), null, "ID_GROUP", null,
				GroupDao.TABLE_GROUP_CONTACTS));
		return list;
	}
}
