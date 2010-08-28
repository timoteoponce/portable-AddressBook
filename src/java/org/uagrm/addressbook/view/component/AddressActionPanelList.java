package org.uagrm.addressbook.view.component;

import java.awt.Frame;

import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.view.dialog.AddressEditDialog;

/**
 * @author Timoteo Ponce
 * 
 */
public class AddressActionPanelList extends ActionPanelList<Address> {



	public AddressActionPanelList(final Boolean editionEnabled) {
		super(editionEnabled);
	}

	@Override
	public void addNewElement() {
		AddressEditDialog dialog = new AddressEditDialog((Frame) null);
		dialog.setSaveable(false);
		dialog.setModel(new Address());
		dialog.setVisible(true);
		dialog.addEventListener(this);
	}

	@Override
	public void editSelected() {
		AddressEditDialog dialog = new AddressEditDialog((Frame) null);
		dialog.setEditable(true);
		dialog.setSaveable(false);
		dialog.setModel(getSelected());
		dialog.setVisible(true);
		dialog.addEventListener(this);
	}


}
