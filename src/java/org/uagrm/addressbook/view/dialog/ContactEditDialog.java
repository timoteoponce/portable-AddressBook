/*
 * Created by JFormDesigner on Tue Mar 09 11:13:52 BOT 2010
 */

package org.uagrm.addressbook.view.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.ContactController;
import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.controller.GroupController;
import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.model.Phone;
import org.uagrm.addressbook.view.View;
import org.uagrm.addressbook.view.component.ActionPanelList;
import org.uagrm.addressbook.view.component.AddressActionPanelList;
import org.uagrm.addressbook.view.component.GroupActionPanelList;
import org.uagrm.addressbook.view.component.PhoneActionPanelList;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.l2fprod.common.swing.JButtonBar;

/**
 * @author Timoteo Ponce
 */
public class ContactEditDialog extends JDialog implements View<Contact> {

	private static final Logger LOG = Logger.getLogger(ContactEditDialog.class);

	private final Controller<Contact> contactController = ControllerFactory
			.getInstance(ContactController.class);

	private final Controller<Group> groupController = ControllerFactory
			.getInstance(GroupController.class);

	private final ActionPanelList<Group> groupPanelList = new GroupActionPanelList(
			false);
	private final ActionPanelList<Address> addressPanelList = new AddressActionPanelList(
			true);
	private final ActionPanelList<Phone> phonePanelList = new PhoneActionPanelList(
			true);
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
		initToolbar();
		groupPanelList.getActionPanel().setTitle("Groups");
		addressPanelList.getActionPanel().setTitle("Addresses");
		phonePanelList.getActionPanel().setTitle("Phones");
		contactController.addView(this);
		groupController.addView(this);
		setPluggablePanel(groupPanelList);
	}

	private void initToolbar() {
		buttonBar.setOrientation(JButtonBar.VERTICAL);
		JButton btnGroup = new JButton("Groups");
		btnGroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setPluggablePanel(groupPanelList);
			}
		});

		JButton btnAddress = new JButton("Addresses");
		btnAddress.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setPluggablePanel(addressPanelList);
			}
		});
		
		JButton btnPhone = new JButton("Phone");
		btnPhone .addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setPluggablePanel(phonePanelList);
			}
		});

		buttonBar.add(btnGroup);
		buttonBar.add(btnAddress);
		buttonBar.add(btnPhone);
	}

	private void setPluggablePanel(Component component) {
		if (pluggablePanel.getComponentCount() == 0 || !pluggablePanel.getComponent(0).equals(component)) {
			pluggablePanel.removeAll();
			pluggablePanel.add(component, BorderLayout.CENTER);
			pluggablePanel.updateUI();
		}
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
		contactController.save(contact, true);
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
		DefaultComponentFactory compFactory = DefaultComponentFactory
				.getInstance();
		separator1 = compFactory.createSeparator("Contact");
		buttonBar = new JButtonBar();
		panelMain = new JPanel();
		lblFirstName = new JLabel();
		txtFirstName = new JTextField();
		lblLastName = new JLabel();
		txtLastName = new JTextField();
		pluggablePanel = new JPanel();
		panelActions = new JPanel();
		btnAccept = new JButton();
		btnCancel = new JButton();
		CellConstraints cc = new CellConstraints();

		// ======== this ========
		setTitle("Dialog Contact");
		Container contentPane = getContentPane();
		contentPane
				.setLayout(new FormLayout(
						"11dlu, $lcgap, 52dlu, $lcgap, 170dlu:grow, $lcgap, 25dlu",
						"2*(default, $lgap), 47dlu, $lgap, default, $lgap, 79dlu:grow, 2*($lgap, default)"));
		contentPane.add(separator1, cc.xy(5, 3));
		contentPane.add(buttonBar, cc.xywh(3, 5, 1, 5));

		// ======== panelMain ========
		{
			panelMain.setLayout(new FormLayout("73dlu, $lcgap, 111dlu",
					"2*(default, $lgap), default"));

			// ---- lblFirstName ----
			lblFirstName.setText("First name:");
			lblFirstName.setLabelFor(txtFirstName);
			panelMain.add(lblFirstName, cc.xy(1, 1));
			panelMain.add(txtFirstName, cc.xy(3, 1));

			// ---- lblLastName ----
			lblLastName.setText("Last name:");
			lblLastName.setLabelFor(txtLastName);
			panelMain.add(lblLastName, cc.xy(1, 3));
			panelMain.add(txtLastName, cc.xy(3, 3));
		}
		contentPane.add(panelMain, cc.xy(5, 5));

		// ======== pluggablePanel ========
		{
			pluggablePanel.setLayout(new BorderLayout());
		}
		contentPane.add(pluggablePanel, cc.xywh(5, 9, 1, 1,
				CellConstraints.FILL, CellConstraints.FILL));

		// ======== panelActions ========
		{
			panelActions.setLayout(new FormLayout(
					"default:grow, 2*($lcgap, default)", "default"));

			// ---- btnAccept ----
			btnAccept.setText(bundle.getString("common.accept"));
			btnAccept.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnAcceptActionPerformed(e);
				}
			});
			panelActions.add(btnAccept, cc.xy(3, 1));

			// ---- btnCancel ----
			btnCancel.setText(bundle.getString("common.cancel"));
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnCancelActionPerformed(e);
				}
			});
			panelActions.add(btnCancel, cc.xy(5, 1));
		}
		contentPane.add(panelActions, cc.xy(5, 11));
		setSize(600, 365);
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JComponent separator1;
	private JButtonBar buttonBar;
	private JPanel panelMain;
	private JLabel lblFirstName;
	private JTextField txtFirstName;
	private JLabel lblLastName;
	private JTextField txtLastName;
	private JPanel pluggablePanel;
	private JPanel panelActions;
	private JButton btnAccept;
	private JButton btnCancel;

	// JFormDesigner - End of variables declaration //GEN-END:variables

	@Override
	public void close() {
		contactController.removeView(this);
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
		updateLists();
	}

	private void updateValues() {
		contact.setFirstName(txtFirstName.getText());
		contact.setLastName(txtLastName.getText());
		//
		contact.getGroups().clear();
		contact.getGroups().addAll(groupPanelList.getListModel().getElements());
		//
		contact.getAddresses().clear();
		contact.getAddresses().addAll(
				addressPanelList.getListModel().getElements());
		//
		contact.getPhones().clear();
		contact.getPhones().addAll(
				phonePanelList.getListModel().getElements());
		// TODO virtual_addresses.
	}

	private void updateLists() {
		updateGroupList();
		updateAddressList();
		updatePhoneList();
		updateVAddressList();
	}

	private void updateGroupList() {
		if (contact.getGroups().isEmpty()) {
			contactController.preloadEntity(contact, Group.class);
		}
		groupPanelList.getListModel().clear();
		groupPanelList.getListModel().addAllElements(contact.getGroups());
		groupPanelList.updateUI();
	}

	private void updateAddressList() {
		if (!contact.hasAddresses()) {
			contactController.preloadEntity(contact, Address.class);
		}
		addressPanelList.getListModel().clear();
		addressPanelList.getListModel().addAllElements(contact.getAddresses());
		addressPanelList.updateUI();
	}

	private void updatePhoneList() {
		if (contact.getPhones().isEmpty()) {
			contactController.preloadEntity(contact, Phone.class);
		}
		phonePanelList.getListModel().clear();
		phonePanelList.getListModel().addAllElements(contact.getPhones());
		phonePanelList.updateUI();
	}

	private void updateVAddressList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable source, Object model) {
		// from contacts
		if (source.equals(contactController)) {
			if (model == null) {// was deleted
				this.close();
			} else if (this.contact.equals((Contact) model)) {
				LOG.info("Updating model : " + this.getClass().getName()
						+ ", values: " + model.toString());
				setModel((Contact) model);
			}
		}// from groups
		else if (source.getClass().equals(GroupController.class)) {
			updateLists();
		}
	}

	@Override
	public Contact getModel() {
		return contact;
	}
}
