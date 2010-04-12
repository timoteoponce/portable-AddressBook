package org.uagrm.addressbook.view.component;

import java.awt.Frame;
import java.util.List;

import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.view.dialog.SearchDialog;
import org.uagrm.addressbook.view.dialog.SelectableItem;
import org.uagrm.addressbook.view.event.SearchEvent;
import org.uagrm.addressbook.view.event.SearchEventListener;
import org.uagrm.addressbook.view.event.SearchEventType;

/**
 * @author Timoteo Ponce
 * 
 */
public class GroupActionPanelList extends ActionPanelList<Group> {

	private Controller<Group> groupController = ControllerFactory.getInstanceFor(Group.class);

	public GroupActionPanelList(boolean editionEnabled) {
		super(editionEnabled);
	}

	@Override
	public void addNewElement() {
		SearchDialog dialog = new SearchDialog((Frame) null);
		dialog.setValidElements((List<SelectableItem>) ((List<? extends SelectableItem>) groupController.getElements()));
		dialog.setInvalidElements((List<SelectableItem>) ((List<? extends SelectableItem>) getElements()));
		dialog.showDialog();
		dialog.addSearchEventListener(this);
	}
	
	@Override
	public void editSelected() {
		// TODO implement
	}


}
