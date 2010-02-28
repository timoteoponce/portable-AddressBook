package org.uagrm.addressbook.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.model.dao.CacheFactory;
import org.uagrm.addressbook.model.dao.GroupDao;
import org.uagrm.addressbook.view.View;

/**
 * @author Timoteo Ponce
 *
 */
public class GroupController implements Controller<Group> {

	private static final Logger LOG = Logger.getLogger(GroupController.class);

	private static Controller<Group> instance;

	private final List<View<Group>> viewList = new ArrayList<View<Group>>();

	private GroupDao dao = CacheFactory.getInstance(GroupDao.class);

	private GroupController() {
	}

	public static Controller<Group> getInstance() {
		if (instance == null) {
			instance = new GroupController();
		}
		return instance;
	}

	@Override
	public void save(Group group) {
		LOG.debug("Saving...");
		// save or update the group

		if (group.getId() == null) {
			LOG.debug("Creating...");
			dao.create(group);
		} else {
			LOG.debug("Updating...");
			dao.update(group);
		}
	}

	@Override
	public void saveReferences(Group group, Class<?> target) {
		if (target.equals(Contact.class)) {
			dao.saveContacts(group);
		}
	}

	@Override
	public void delete(Group group) {
		dao.delete(group);
	}

	@Override
	public Collection<Group> getElements() {
		return dao.selectAll();
	}

	@Override
	public void addView(View<Group> view) {
		if (!viewList.contains(view)) {
			viewList.add(view);
			view.setController(this);
			LOG.info("Adding view: " + view);
		} else {
			LOG.warn("Duplicated view : " + view);
		}
	}

	@Override
	public void removeView(View<Group> view) {
		LOG.info("Removing view: " + view);
		viewList.remove(view);
	}

	@Override
	public void modelChanged(Group model) {
		LOG.info("Model has changed, updating all views: [ " + model + " ]");
		updateAllViews();
	}

	@Override
	public void updateAllViews() {
		for (View<Group> view : viewList) {
			view.update();
		}
	}

}
