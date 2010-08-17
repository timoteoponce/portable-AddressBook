/*
 * Created by JFormDesigner on Sat Mar 13 12:54:54 VET 2010
 */

package org.uagrm.addressbook.view;


import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.view.component.ActionButtons;
import org.uagrm.addressbook.view.dialog.ContactEditDialog;
import org.uagrm.addressbook.view.event.GenericEvent;
import org.uagrm.addressbook.view.event.GenericEventListener;
import org.uagrm.addressbook.view.event.GenericEventType;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class ContactListView extends AbstractListView<Contact> implements GenericEventListener {

	private static final Logger LOG = Logger.getLogger(ContactListView.class);

	private final Controller<Contact> controller = ControllerFactory.getInstanceFor(Contact.class);
	private final ActionButtons actionButtons = new ActionButtons();


	public ContactListView() {
		initComponents();
		this.init();
	}

	@Override
	protected void init() {
		super.init();
		operationPanel.add(actionButtons, BorderLayout.CENTER);
		actionButtons.addActionListener(createActionButtonListener());
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		scrollContactList = new JScrollPane();
		contactList = new JList();
		operationPanel = new JPanel();
		CellConstraints cc = new CellConstraints();

		// ======== this ========
		setLayout(new FormLayout("16dlu, $lcgap, default:grow, $lcgap, 16dlu", "15dlu, $lgap, default:grow, $lgap, 15dlu"));

		// ======== scrollContactList ========
		{
			scrollContactList.setViewportView(contactList);
		}
		add(scrollContactList, cc.xywh(3, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		// ======== operationPanel ========
		{
			operationPanel.setLayout(new BorderLayout());
		}
		add(operationPanel, cc.xywh(3, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JScrollPane scrollContactList;
	private JList contactList;
	private JPanel operationPanel;
	// JFormDesigner - End of variables declaration //GEN-END:variables

	@Override
	public void addNew() {
		ContactEditDialog dialog = new ContactEditDialog(getMainWindow());
		dialog.setModel(new Contact());
		dialog.setVisible(true);
		dialog.setSaveable(true);
		dialog.setEditable(true);
	}

	@Override
	public void editCurrent() {
		if (getModel() != null) {
			ContactEditDialog dialog = new ContactEditDialog(getMainWindow());
			dialog.setModel(getModel());
			dialog.setEditable(true);
			dialog.setSaveable(true);
			dialog.setVisible(true);
		}
	}

	@Override
	public Controller<Contact> getController() {
		return controller;
	}

	@Override
	public JList getList() {
		return contactList;
	}

	@Override
	public void eventFired(GenericEvent event) {
		if(event.getType() == GenericEventType.ELEMENT_SELECTED){
			LOG.debug("Event ELEMENT_SELECTED received");
			final ListView<Group> groupListView = (ListView<Group>) event.getSource();
			final Group group = groupListView.getModel();
			getListModel().clear();
			getListModel().addAllElements(controller.getElementsBy(Group.class, group));
			getList().updateUI();
		}
	}
}
