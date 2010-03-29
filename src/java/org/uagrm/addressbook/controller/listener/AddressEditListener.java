package org.uagrm.addressbook.controller.listener;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import org.uagrm.addressbook.controller.AddressController;
import org.uagrm.addressbook.controller.ContactController;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.swing.ListModel;
import org.uagrm.addressbook.view.View;
import org.uagrm.addressbook.view.dialog.AddressEditDialog;

public class AddressEditListener implements ActionListener, View<Contact> {

	private AddressEditDialog dialog;

	private final AddressController addressController = ControllerFactory.getInstance(AddressController.class);

	private final ContactController contactController = ControllerFactory.getInstance(ContactController.class);

	private Address currentAddress;

	private Contact contact;

	private boolean isEditing;

	private final ListModel<Address> addressListModel = new ListModel<Address>();

	public AddressEditListener(Frame frameParent, Dialog dialogParent) {
		if (frameParent != null) {
			dialog = new AddressEditDialog(frameParent);
		} else {
			dialog = new AddressEditDialog(dialogParent);
		}
		init();
	}

	private void init() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setModel(Contact model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}
