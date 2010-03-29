package org.uagrm.addressbook.controller;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.model.dao.DaoFactory;
import org.uagrm.addressbook.model.dao.GenericDao;
import org.uagrm.addressbook.model.dao.GroupDao;

/**
 * @author Timoteo Ponce
 * 
 */
public class GroupController extends AbstractController<Group> implements
		Controller<Group> {

	private static final Logger LOG = Logger.getLogger(GroupController.class);

	private static Controller<Group> instance;

	private final GroupDao dao = DaoFactory.getInstance(GroupDao.class);

	private GroupController() {
	}

	public static Controller<Group> getInstance() {
		if (instance == null) {
			instance = new GroupController();
		}
		return instance;
	}

	@Override
	public void save(Group group, boolean updateViews) {
		LOG.debug("Saving group");
		// save or update the group
		if (group.getId() == null) {
			LOG.debug("Creating...");
			dao.create(group);
		} else {
			LOG.debug("Updating...");
			dao.update(group);
		}
		saveReferences(group, Contact.class);
		if (updateViews) {
			updateAllViews(group);
		}
	}

	@Override
	protected void saveReferences(Group group, Class<?> target) {
		LOG.info("Saving Group references: " + target + " [ "
				+ group.getContacts().size() + " ]");
		if (target.equals(Contact.class)) {
			dao.saveContacts(group);
		}
	}

	@Override
	protected GenericDao<Group> getDao() {
		return dao;
	}

}
