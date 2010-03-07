package org.uagrm.addressbook.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.actions.CommonActions;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.model.ReferenceLink;
import org.uagrm.addressbook.model.dao.ContactDao;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.GroupDao;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Timoteo Ponce
 * 
 */
public class SqlGroupDao extends AbstractSqlDao<Group> implements GroupDao {

    private static final Logger LOG = Logger.getLogger(SqlGroupDao.class);

    @Override
    public void loadReferences(Group entity, Class<?> clazz) {
	if (clazz.equals(Contact.class)) {
	    final Group group = entity;
	    ContactDao contactDao = DaoFactory.getInstance(ContactDao.class);

	    group.getContacts().clear();
	    group.getContacts().addAll(contactDao.getByGroup(entity.getId()));
	}
    }

    @Override
    public Set<Group> getByContact(Integer contactId) {
	// TODO not needed yet
	throw new NotImplementedException();
    }

    @Override
    public void saveContacts(Group entity) {
	final Group group = entity;
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
    protected void fillValues(Group entity, ResultSet rs) throws SQLException {
	Group group = entity;
	group.setId(rs.getInt(1));
	group.setName(rs.getString(2));
	group.setDescription(rs.getString(3));
    }

    @Override
    protected String getFields(Group entity, CommonActions action) {
	String out = "";
	final Group group = entity;
	switch (action) {
	case CREATE:
	    out = "(null,'" + group.getName() + "','" + group.getDescription()
		    + "');";
	    break;

	case UPDATE:
	    out = "name='" + group.getName() + "',description='"
		    + group.getDescription() + "'";
	    break;
	}
	return out;
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

    public static void main(String[] args) {
	// GroupDao dao = new GroupDaoImpl();
	//
	// Group group1 = new Group();
	// group1.setName("1stGroup");
	//
	// Group group2 = new Group();
	// group2.setName("2stGroup");
	//
	// Group group3 = new Group();
	// group3.setName("3stGroup");
	//
	// dao.create(group1);
	// log.info("created group : " + group1.getId());
	//
	// dao.create(group2);
	// log.info("created group : " + group2.getId());
	//
	// dao.create(group3);
	// log.info("created group : " + group3.getId());
	GroupDao groupDao = DaoFactory.getInstance(SqlGroupDao.class);

	Group myGroup = groupDao.read(new Group(1, null, null));
	LOG.info("group : " + myGroup.toString());

	groupDao.loadReferences(myGroup, Contact.class);
	for (Contact contact : myGroup.getContacts()) {
	    LOG.info("contact entry: " + contact.toString());
	}
    }

    @Override
    protected Collection<ReferenceLink> getReferences(Group entity) {
	Collection<ReferenceLink> list = new ArrayList<ReferenceLink>();
	list.add(new ReferenceLink(entity.getId(), null, "ID_GROUP", null,
		GroupDao.TABLE_GROUP_CONTACTS));
	return list;
    }
}
