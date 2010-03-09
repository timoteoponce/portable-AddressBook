/*
 * Created by JFormDesigner on Tue Mar 09 11:13:52 BOT 2010
 */

package org.uagrm.addressbook.view.dialog;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.ContactController;
import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.controller.GroupController;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.model.swing.ListModel;
import org.uagrm.addressbook.view.View;
import org.uagrm.addressbook.view.event.SearchEvent;
import org.uagrm.addressbook.view.event.SearchEventListener;
import org.uagrm.addressbook.view.event.SearchEventType;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jgoodies.uif_lite.panel.*;

/**
 * @author Timoteo Ponsh
 */
public class ContactEditDialog extends JDialog implements View<Contact> {

	private static final Logger LOG = Logger.getLogger(ContactEditDialog.class);

	private final Controller<Contact> controller = ControllerFactory
			.getInstance(ContactController.class);

	private final Controller<Group> groupController = ControllerFactory
			.getInstance(GroupController.class);
	private Contact contact;
	private boolean isCreation;

	private final ListModel<Group> groupListModel = new ListModel<Group>();

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
		groupListModel.clear();
		this.listGroups.setModel(groupListModel);
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

	private void btnAddGroupActionPerformed(ActionEvent e) {
		searchGroup();
	}

	private void searchGroup() {
		SearchDialog dialog = new SearchDialog(this);
		dialog
				.setValidElements((List<SelectableItem>) ((List<? extends SelectableItem>) groupController
						.getElements()));
		dialog.setInvalidElements((List<SelectableItem>) ((List<? extends SelectableItem>)groupListModel.getElements()));
		dialog.showDialog();
		dialog.addSearchEventListener(getSearchGroupListener());
	}	

	private SearchEventListener getSearchGroupListener() {
		SearchEventListener listener = new SearchEventListener() {

			@Override
			public void eventFired(SearchEvent event) {
				SearchDialog dialog = (SearchDialog) event.getSource();

				if (event.getType() == SearchEventType.SELECTED) {
					LOG.debug("Selected group: " + dialog.getSelected());
					addGroup((Group) dialog.getSelected());
				} else {
					LOG.debug("Group search cancelled");
				}
			}
		};
		return listener;
	}

	private void addGroup(Group group) {
		if (group != null) {
			groupListModel.addElement(group);
		}
	}

	private void btnRemoveGroupActionPerformed(ActionEvent e) {
		removeGroup();
	}

	private void removeGroup() {
		int index = listGroups.getSelectedIndex();

		if (index >= 0) {
			groupListModel.removeElement(index);
			listGroups.updateUI();
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		ResourceBundle bundle = ResourceBundle.getBundle("messages");
		DefaultComponentFactory compFactory = DefaultComponentFactory
				.getInstance();
		simpleInternalFrame1 = new SimpleInternalFrame();
		panelMain = new JPanel();
		lblFirstName = new JLabel();
		txtFirstName = new JTextField();
		lblLastName = new JLabel();
		txtLastName = new JTextField();
		sepRef = compFactory.createSeparator("text");
		btnMngPhones = new JButton();
		btnMngAddresses = new JButton();
		btnMngVAddresses = new JButton();
		simpleInternalFrame2 = new SimpleInternalFrame();
		panelGroups = new JPanel();
		sepOperations = compFactory.createSeparator("Operations");
		panelOpBtns = new JPanel();
		btnAddGroup = new JButton();
		btnRemoveGroup = new JButton();
		scrollPaneGroups = new JScrollPane();
		listGroups = new JList();
		panelActions = new JPanel();
		btnAccept = new JButton();
		btnCancel = new JButton();
		CellConstraints cc = new CellConstraints();

		// ======== this ========
		setTitle("Dialog Contact");
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
				"27dlu, $lcgap, 329dlu:grow, $lcgap, 25dlu",
				"16dlu, $lgap, 147dlu, $lgap, 35dlu:grow, 2*($lgap, default)"));

		// ======== simpleInternalFrame1 ========
		{
			simpleInternalFrame1.setTitle("Contact");
			Container simpleInternalFrame1ContentPane = simpleInternalFrame1
					.getContentPane();
			simpleInternalFrame1ContentPane.setLayout(new FormLayout(
					"default, $lcgap, default:grow, $lcgap, default",
					"default, $lgap, 163dlu, $lgap, default"));

			// ======== panelMain ========
			{
				panelMain.setLayout(new FormLayout("78dlu, 241dlu",
						"5*(default, $lgap), default"));

				// ---- lblFirstName ----
				lblFirstName.setText("First name:");
				lblFirstName.setLabelFor(txtFirstName);
				panelMain.add(lblFirstName, cc.xy(1, 1));
				panelMain.add(txtFirstName, cc.xy(2, 1));

				// ---- lblLastName ----
				lblLastName.setText("Last name:");
				lblLastName.setLabelFor(txtLastName);
				panelMain.add(lblLastName, cc.xy(1, 3));
				panelMain.add(txtLastName, cc.xy(2, 3));
				panelMain.add(sepRef, cc.xywh(1, 5, 2, 1));

				// ---- btnMngPhones ----
				btnMngPhones.setText("Manage phones");
				panelMain.add(btnMngPhones, cc.xy(2, 7));

				// ---- btnMngAddresses ----
				btnMngAddresses.setText("Manage Addresses");
				panelMain.add(btnMngAddresses, cc.xy(2, 9));

				// ---- btnMngVAddresses ----
				btnMngVAddresses.setText("Manage virtual addresses");
				panelMain.add(btnMngVAddresses, cc.xy(2, 11));
			}
			simpleInternalFrame1ContentPane.add(panelMain, cc.xywh(3, 3, 1, 1,
					CellConstraints.FILL, CellConstraints.TOP));
		}
		contentPane.add(simpleInternalFrame1, cc.xywh(3, 3, 1, 1,
				CellConstraints.FILL, CellConstraints.TOP));

		// ======== simpleInternalFrame2 ========
		{
			simpleInternalFrame2.setTitle("Groups");
			Container simpleInternalFrame2ContentPane = simpleInternalFrame2
					.getContentPane();
			simpleInternalFrame2ContentPane.setLayout(new FormLayout(
					"21dlu, $lcgap, 262dlu:grow, $lcgap, 23dlu",
					"2*(default, $lgap), default:grow, $lgap, default"));

			// ======== panelGroups ========
			{
				panelGroups.setLayout(new FormLayout(
						"201dlu:grow, $lcgap, 45dlu:grow", "default"));
				panelGroups.add(sepOperations, cc.xy(1, 1));

				// ======== panelOpBtns ========
				{
					panelOpBtns.setLayout(new FormLayout(
							"default, $lcgap, default", "default"));

					// ---- btnAddGroup ----
					btnAddGroup.setText("+");
					btnAddGroup.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							btnAddGroupActionPerformed(e);
						}
					});
					panelOpBtns.add(btnAddGroup, cc.xy(1, 1));

					// ---- btnRemoveGroup ----
					btnRemoveGroup.setText("-");
					btnRemoveGroup.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							btnRemoveGroupActionPerformed(e);
						}
					});
					panelOpBtns.add(btnRemoveGroup, cc.xy(3, 1));
				}
				panelGroups.add(panelOpBtns, cc.xy(3, 1));
			}
			simpleInternalFrame2ContentPane.add(panelGroups, cc.xy(3, 3));

			// ======== scrollPaneGroups ========
			{
				scrollPaneGroups.setViewportView(listGroups);
			}
			simpleInternalFrame2ContentPane.add(scrollPaneGroups, cc.xy(3, 5));
		}
		contentPane.add(simpleInternalFrame2, cc.xywh(3, 5, 1, 1,
				CellConstraints.DEFAULT, CellConstraints.FILL));

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
		contentPane.add(panelActions, cc.xy(3, 7));
		setSize(600, 570);
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private SimpleInternalFrame simpleInternalFrame1;
	private JPanel panelMain;
	private JLabel lblFirstName;
	private JTextField txtFirstName;
	private JLabel lblLastName;
	private JTextField txtLastName;
	private JComponent sepRef;
	private JButton btnMngPhones;
	private JButton btnMngAddresses;
	private JButton btnMngVAddresses;
	private SimpleInternalFrame simpleInternalFrame2;
	private JPanel panelGroups;
	private JComponent sepOperations;
	private JPanel panelOpBtns;
	private JButton btnAddGroup;
	private JButton btnRemoveGroup;
	private JScrollPane scrollPaneGroups;
	private JList listGroups;
	private JPanel panelActions;
	private JButton btnAccept;
	private JButton btnCancel;

	// JFormDesigner - End of variables declaration //GEN-END:variables

	@Override
	public void close() {
		getController().removeView(this);
		this.dispose();
	}

	@Override
	public Controller<Contact> getController() {
		return controller;
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

		for (Group group : contact.getGroups()) {
			groupListModel.addElement(group);
		}
		listGroups.updateUI();
	}

	private void updateValues() {
		contact.setFirstName(txtFirstName.getText());
		contact.setLastName(txtLastName.getText());
		//
		contact.getGroups().clear();
		for (int i = 0; i < groupListModel.getSize(); i++) {
			contact.getGroups().add(groupListModel.getElement(i));
		}
		// TODO phones,addresses,virtual_addresses.
	}

	@Override
	public void update(Contact model) {
		if (model == null) {// was deleted
			this.close();
		} else if (this.contact.equals(model)) {
			LOG.info("Updating model : " + this.getClass().getName()
					+ ", values: " + model.toString());
			setModel(model);
		}

	}
}
