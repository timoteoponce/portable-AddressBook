/*
 * Created by JFormDesigner on Tue Mar 09 11:13:52 BOT 2010
 */

package org.uagrm.addressbook.view.dialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.ContactController;
import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.controller.GroupController;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.view.View;
import org.uagrm.addressbook.view.component.ActionPanel;
import org.uagrm.addressbook.view.component.AddressActionPanelList;
import org.uagrm.addressbook.view.component.GroupActionPanelList;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class ContactEditDialog extends JDialog implements View<Contact> {

	private static final Logger LOG = Logger.getLogger(ContactEditDialog.class);

	private final Controller<Contact> controller = ControllerFactory
			.getInstance(ContactController.class);

	private final Controller<Group> groupController = ControllerFactory
			.getInstance(GroupController.class);

	private final GroupActionPanelList groupPanelList = new GroupActionPanelList(false);
	private final AddressActionPanelList addressPanelList = new AddressActionPanelList(true);
	private Contact contact;
	private boolean isCreation;


	public ContactEditDialog(Frame owner) {
		super(owner);
		initComponents();
		init();
	}

	public ContactEditDialog(Dialog owner) {
		super(owner);
		initComponents();
		init();
	}

	private void init() {
		panelGroups.add(groupPanelList, BorderLayout.CENTER);
		panelAddress.add(addressPanelList, BorderLayout.CENTER);
		ControllerFactory.getInstance(ContactController.class).addView(this);
		ControllerFactory.getInstance(GroupController.class).addView(this);
	}


	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public boolean isCreation() {
		return isCreation;
	}

	public void setCreation(boolean isCreation) {
		this.isCreation = isCreation;
	}

	private void btnAcceptActionPerformed(ActionEvent e) {
		okAction();
	}

	private void okAction() {
		updateValues();
		controller.save(contact);
		close();
	}

	private void btnCancelActionPerformed(ActionEvent e) {
		cancelAction();
	}

	private void cancelAction() {
		close();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		ResourceBundle bundle = ResourceBundle.getBundle("messages");
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		separator1 = compFactory.createSeparator("Contact");
		panelMain = new JPanel();
		lblFirstName = new JLabel();
		txtFirstName = new JTextField();
		lblLastName = new JLabel();
		txtLastName = new JTextField();
		sepRef = compFactory.createSeparator("Related elements");
		panelRelated = new JPanel();
		panelAddress = new JPanel();
		panelPhones = new JPanel();
		actionPhone = new ActionPanel();
		scrollPanePhone = new JScrollPane();
		list2 = new JList();
		panelVAddresses = new JPanel();
		actionVAddress = new ActionPanel();
		scrollPaneVAddress = new JScrollPane();
		listVAddresses = new JList();
		panelGroups = new JPanel();
		panelActions = new JPanel();
		btnAccept = new JButton();
		btnCancel = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Dialog Contact");
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
"27dlu, $lcgap, 263dlu:grow, $lcgap, 25dlu",
			"2*(default, $lgap), 82dlu, $lgap, default, $lgap, 35dlu:grow, 2*($lgap, default)"));
		contentPane.add(separator1, cc.xy(3, 3));

		//======== panelMain ========
		{
			panelMain.setLayout(new FormLayout(
"121dlu, $lcgap, 140dlu",
				"3*(default, $lgap), 36dlu"));

			//---- lblFirstName ----
			lblFirstName.setText("First name:");
			lblFirstName.setLabelFor(txtFirstName);
			panelMain.add(lblFirstName, cc.xy(1, 1));
			panelMain.add(txtFirstName, cc.xy(3, 1));

			//---- lblLastName ----
			lblLastName.setText("Last name:");
			lblLastName.setLabelFor(txtLastName);
			panelMain.add(lblLastName, cc.xy(1, 3));
			panelMain.add(txtLastName, cc.xy(3, 3));
			panelMain.add(sepRef, cc.xywh(1, 5, 3, 1));

			//======== panelRelated ========
			{
				panelRelated.setLayout(new FormLayout(
					"109dlu, $lcgap, 97dlu, $lcgap, default",
					"default"));

				// ======== panelAddress ========
				{
					panelAddress.setLayout(new BorderLayout());
				}
				panelRelated.add(panelAddress, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

				//======== panelPhones ========
				{
					panelPhones.setLayout(new FormLayout(
"71dlu",
						"default, $lgap, default"));
					panelPhones.add(actionPhone, cc.xy(1, 1));

					//======== scrollPanePhone ========
					{
						scrollPanePhone.setViewportView(list2);
					}
					panelPhones.add(scrollPanePhone, cc.xy(1, 3));
				}
				panelRelated.add(panelPhones, cc.xy(3, 1));

				//======== panelVAddresses ========
				{
					panelVAddresses.setLayout(new FormLayout(
"51dlu",
						"default, $lgap, default"));
					panelVAddresses.add(actionVAddress, cc.xy(1, 1));

					//======== scrollPaneVAddress ========
					{
						scrollPaneVAddress.setViewportView(listVAddresses);
					}
					panelVAddresses.add(scrollPaneVAddress, cc.xy(1, 3));
				}
				panelRelated.add(panelVAddresses, cc.xy(5, 1));
			}
			panelMain.add(panelRelated, cc.xywh(1, 7, 3, 1));
		}
		contentPane.add(panelMain, cc.xy(3, 5));

		// ======== panelGroups ========
		{
			panelGroups.setLayout(new BorderLayout());
		}
		contentPane.add(panelGroups, cc.xywh(3, 9, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//======== panelActions ========
		{
			panelActions.setLayout(new FormLayout(
				"default:grow, 2*($lcgap, default)",
				"default"));

			//---- btnAccept ----
			btnAccept.setText(bundle.getString("common.accept"));
			btnAccept.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnAcceptActionPerformed(e);
				}
			});
			panelActions.add(btnAccept, cc.xy(3, 1));

			//---- btnCancel ----
			btnCancel.setText(bundle.getString("common.cancel"));
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnCancelActionPerformed(e);
				}
			});
			panelActions.add(btnCancel, cc.xy(5, 1));
		}
		contentPane.add(panelActions, cc.xy(3, 11));
		setSize(645, 455);
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JComponent separator1;
	private JPanel panelMain;
	private JLabel lblFirstName;
	private JTextField txtFirstName;
	private JLabel lblLastName;
	private JTextField txtLastName;
	private JComponent sepRef;
	private JPanel panelRelated;
	private JPanel panelAddress;
	private JPanel panelPhones;
	private ActionPanel actionPhone;
	private JScrollPane scrollPanePhone;
	private JList list2;
	private JPanel panelVAddresses;
	private ActionPanel actionVAddress;
	private JScrollPane scrollPaneVAddress;
	private JList listVAddresses;
	private JPanel panelGroups;
	private JPanel panelActions;
	private JButton btnAccept;
	private JButton btnCancel;
	// JFormDesigner - End of variables declaration //GEN-END:variables

	@Override
	public void close() {
		controller.removeView(this);
		ControllerFactory.getInstance(GroupController.class).removeView(this);
		this.dispose();
	}	

	@Override
	public void setModel(Contact model) {
		this.contact = model;
		loadValues();
	}

	private void loadValues() {
		txtFirstName.setText(contact.getFirstName());
		txtLastName.setText(contact.getLastName());
		//
		if (contact.getGroups().isEmpty()) {
			controller.preloadEntity(contact, Group.class);
		}
		updateGroupList();
	}

	private void updateValues() {
		contact.setFirstName(txtFirstName.getText());
		contact.setLastName(txtLastName.getText());
		//
		contact.getGroups().clear();
		contact.getGroups().addAll(groupPanelList.getListModel().getElements());
		// TODO phones,virtual_addresses.
	}

	private void updateGroupList() {
		groupPanelList.getListModel().clear();
		groupPanelList.getListModel().addAllElements(contact.getGroups());
		groupPanelList.updateUI();
	}

	@Override
	public void update(Observable source, Object model) {
		// from contacts
		if (source.equals(controller)) {
			if (model == null) {// was deleted
				this.close();
			} else if (this.contact.equals((Contact) model)) {
				LOG.info("Updating model : " + this.getClass().getName()
						+ ", values: " + model.toString());
				setModel((Contact) model);
			}
		}// from groups
		else if (source.getClass().equals(GroupController.class)) {
			updateGroupList();
		}
	}

	@Override
	public Contact getModel() {
		return contact;
	}
}
