package org.uagrm.addressbook.view.component;

import java.awt.Frame;

import org.uagrm.addressbook.model.Phone;
import org.uagrm.addressbook.view.dialog.PhoneEditDialog;
import org.uagrm.addressbook.view.event.GenericEvent;
import org.uagrm.addressbook.view.event.GenericEventListener;
import org.uagrm.addressbook.view.event.GenericEventType;

/**
 * @author Timoteo Ponce
 * 
 */
public class PhoneActionPanelList extends ActionPanelList<Phone> {



	public PhoneActionPanelList(boolean editionEnabled) {
		super(editionEnabled);
	}

	@Override
	public void addNewElement() {
		PhoneEditDialog dialog = new PhoneEditDialog((Frame) null);
		dialog.setSaveable(false);
		dialog.setModel(new Phone());
		dialog.setVisible(true);
		dialog.addEventListener(this);
	}

	@Override
	public void editSelected() {
		PhoneEditDialog dialog = new PhoneEditDialog((Frame) null);
		dialog.setEditable(true);
		dialog.setSaveable(false);
		dialog.setModel(getSelected());
		dialog.setVisible(true);
		dialog.addEventListener(this);
	}


}
