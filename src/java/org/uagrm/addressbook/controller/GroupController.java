package org.uagrm.addressbook.controller;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.model.dao.GroupDao;
import org.uagrm.addressbook.model.dao.Home;
import org.uagrm.addressbook.model.dto.EntityStatus;
import org.uagrm.addressbook.model.dto.StatusType;

/**
 * @author Timoteo Ponce
 * 
 */
public class GroupController extends AbstractController<Group> {

	private static final Logger LOG = Logger.getLogger(GroupController.class);

	public GroupController(final Home<Group> home) {
		super(home);
	}

	private GroupDao getSpecificHome() {
		return (GroupDao) getHome();
	}

	@Override
	public void save(final Group group, final boolean updateViews) {
		LOG.debug("Saving group");
		getSpecificHome().setInstance(group);
		// save or update the group
		if (group.getId() == null) {
			LOG.debug("Creating...");
			getHome().persist();
		} else {
			LOG.debug("Updating...");
			getHome().update();
		}
		saveReferences(group, Contact.class);
		if (updateViews) {
			updateAllViews(EntityStatus.create(group, StatusType.UPDATED));
		}
		getSpecificHome().clearInstance();
	}

	@Override
	protected void saveReferences(final Group group, final Class<?> target) {
		LOG.info("Saving Group references: " + target + " [ "
				+ group.getContacts().size() + " ]");
		getSpecificHome().setInstance(group);

		if (target.equals(Contact.class)) {
			getSpecificHome().saveContacts();
		}
		getSpecificHome().clearInstance();
	}

}
