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
		dialog.addEventListener(new GenericEventListener() {

			@Override
			public void eventFired(GenericEvent event) {
				if (event.getType() == GenericEventType.DIALOG_SAVE) {
					VirtualAddress vAddress = ((VirtualAddressEditDialog) event.getSource()).getModel();
					addElement(vAddress);
				}
			}
		});
	}

	@Override
	public void editSelected() {
		VirtualAddressEditDialog dialog = new VirtualAddressEditDialog((Frame) null);
		dialog.setEditable(true);
		dialog.setSaveable(false);
		dialog.setModel(getSelected());
		dialog.setVisible(true);
		dialog.addEventListener(new GenericEventListener() {

			@Override
			public void eventFired(GenericEvent event) {
				if (event.getType() == GenericEventType.DIALOG_SAVE) {
					VirtualAddress vAddress = ((VirtualAddressEditDialog) event.getSource()).getModel();
					removeSelected();
					addElement(vAddress);
				}
			}
		});
	}


}
