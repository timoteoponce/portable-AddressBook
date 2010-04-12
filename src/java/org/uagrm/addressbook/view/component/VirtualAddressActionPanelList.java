package org.uagrm.addressbook.view.component;

import java.awt.Frame;

import org.uagrm.addressbook.model.VirtualAddress;
import org.uagrm.addressbook.view.dialog.VirtualAddressEditDialog;
import org.uagrm.addressbook.view.event.GenericEvent;
import org.uagrm.addressbook.view.event.GenericEventListener;
import org.uagrm.addressbook.view.event.GenericEventType;

/**
 * @author Timoteo Ponce
 * 
 */
public class VirtualAddressActionPanelList extends ActionPanelList<VirtualAddress> {



	public VirtualAddressActionPanelList(boolean editionEnabled) {
		super(editionEnabled);
	}

	@Override
	public void addNewElement() {
		VirtualAddressEditDialog dialog = new VirtualAddressEditDialog((Frame) null);
		dialog.setSaveable(false);
		dialog.setModel(new VirtualAddress());
		dialog.setVisible(true);
		dialog.addEventListener(this);
	}

	@Override
	public void editSelected() {
		VirtualAddressEditDialog dialog = new VirtualAddressEditDialog((Frame) null);
		dialog.setEditable(true);
		dialog.setSaveable(false);
		dialog.setModel(getSelected());
		dialog.setVisible(true);
		dialog.addEventListener(this);
	}


}
