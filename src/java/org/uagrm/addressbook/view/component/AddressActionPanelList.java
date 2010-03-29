package org.uagrm.addressbook.view.component;

import java.awt.Frame;

import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.view.dialog.AddressEditDialog;
import org.uagrm.addressbook.view.event.GenericEvent;
import org.uagrm.addressbook.view.event.GenericEventListener;
import org.uagrm.addressbook.view.event.GenericEventType;

/**
 * @author Timoteo Ponce
 * 
 */
public class AddressActionPanelList extends ActionPanelList<Address> {



	public AddressActionPanelList(boolean editionEnabled) {
		super(editionEnabled);
	}

	@Override
	public void addNewElement() {
		AddressEditDialog dialog = new AddressEditDialog((Frame) null);
		dialog.setCanSave(false);
		dialog.setModel(new Address());
		dialog.setVisible(true);
		dialog.addEventListener(new GenericEventListener() {

			@Override
			public void eventFired(GenericEvent event) {
				if (event.getType() == GenericEventType.DIALOG_SAVE) {
					Address address = ((AddressEditDialog) event.getSource()).getModel();
					addElement(address);
				}
			}
		});
	}

	@Override
	public void editSelected() {
		AddressEditDialog dialog = new AddressEditDialog((Frame) null);
		dialog.setEditing(true);
		dialog.setCanSave(false);
		dialog.setModel(getSelected());
		dialog.setVisible(true);
		dialog.addEventListener(new GenericEventListener() {

			@Override
			public void eventFired(GenericEvent event) {
				if (event.getType() == GenericEventType.DIALOG_SAVE) {
					Address address = ((AddressEditDialog) event.getSource()).getModel();
					removeSelected();
					addElement(address);
				}
			}
		});
	}


}
