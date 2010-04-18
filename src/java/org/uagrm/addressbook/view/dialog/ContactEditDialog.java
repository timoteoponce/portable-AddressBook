/*
 * Created by JFormDesigner on Tue Mar 09 11:13:52 BOT 2010
 */

package org.uagrm.addressbook.view.dialog;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.controller.GroupController;
import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.model.Phone;
import org.uagrm.addressbook.model.VirtualAddress;
import org.uagrm.addressbook.model.dto.EntityStatus;
import org.uagrm.addressbook.model.dto.StatusType;
import org.uagrm.addressbook.view.component.ActionPanelList;
import org.uagrm.addressbook.view.component.AddressActionPanelList;
import org.uagrm.addressbook.view.component.GroupActionPanelList;
import org.uagrm.addressbook.view.component.PhoneActionPanelList;
import org.uagrm.addressbook.view.component.VirtualAddressActionPanelList;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class ContactEditDialog extends AbstractDialogView<Contact> {

	private static final Logger LOG = Logger.getLogger(ContactEditDialog.class);

	private final Controller<Contact> contactController = ControllerFactory.getInstanceFor(Contact.class);

	private final Controller<Group> groupController = ControllerFactory.getInstanceFor(Group.class);	

	private final ActionPanelList<Group> groupPanelList = new GroupActionPanelList(false);

	private final ActionPanelList<Address> addressPanelList = new AddressActionPanelList(true);

	private final ActionPanelList<Phone> phonePanelList = new PhoneActionPanelList(true);

	private final ActionPanelList<VirtualAddress> vAddressPanelList = new VirtualAddressActionPanelList(true);

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
		initTabs();
		contactController.addView(this);
		groupController.addView(this);
	}

	private void initTabs() {
		groupPanelList.setTitle("Groups");
		addressPanelList.setTitle("Addresses");
		phonePanelList.setTitle("Phones");
		vAddressPanelList.setTitle("Virtual Address");
		addTab("Groups", groupPanelList);
		addTab("Addresses", addressPanelList);
		addTab("Phone", phonePanelList);
		addTab("Virtual Address", vAddressPanelList);
	}

	private void addTab(final String label, final ActionPanelList<?> component) {
		tabbedPane.addTab(label, component);
	}

	private void btnAcceptActionPerformed(ActionEvent e) {
		saveModel();
	}


	private void btnCancelActionPerformed(ActionEvent e) {
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
		tabbedPane = new JTabbedPane();
		panelActions = new JPanel();
		btnAccept = new JButton();
		btnCancel = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Dialog Contact");
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
"11dlu, $lcgap, 170dlu:grow, $lcgap, 25dlu", "2*(default, $lgap), 37dlu, $lgap, default, $lgap, 79dlu:grow, 2*($lgap, default)"));
		contentPane.add(separator1, cc.xy(3, 3));

		//======== panelMain ========
		{
			panelMain.setLayout(new FormLayout(
				"73dlu, $lcgap, 111dlu",
				"2*(default, $lgap), default"));

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
		}
		contentPane.add(panelMain, cc.xy(3, 5));

		// ======== tabbedPane ========
		{
			tabbedPane.setTabPlacement(SwingConstants.LEFT);
			tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		}
		contentPane.add(tabbedPane, cc.xywh(3, 9, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

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
		setSize(525, 370);
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
	private JTabbedPane tabbedPane;
	private JPanel panelActions;
	private JButton btnAccept;
	private JButton btnCancel;
	// JFormDesigner - End of variables declaration //GEN-END:variables

	@Override
	public void close() {
		contactController.removeView(this);
		groupController.removeView(this);
		this.dispose();
	}

	@Override
	public void loadValues() {
		txtFirstName.setText(getModel().getFirstName());
		txtLastName.setText(getModel().getLastName());
		updateLists();
	}

	@Override
	public void updateValues() {
		Contact contact = getModel();
		contact.setFirstName(txtFirstName.getText());
		contact.setLastName(txtLastName.getText());
		//
		contact.getGroups().clear();
		contact.getGroups().addAll(groupPanelList.getElements());
		//
		contact.getAddresses().clear();
		contact.getAddresses().addAll(addressPanelList.getElements());
		//
		contact.getPhones().clear();
		contact.getPhones().addAll(phonePanelList.getElements());
		// 
		contact.getVirtualAddresses().clear();
		contact.getVirtualAddresses().addAll(vAddressPanelList.getElements());
	}

	private void updateLists() {
		updateGroupList();
		updateAddressList();
		updatePhoneList();
		updateVAddressList();
	}

	private void updateGroupList() {
		if (getModel().getGroups().isEmpty()) {
			contactController.preloadEntity(getModel(), Group.class);
		}
		groupPanelList.clear();
		groupPanelList.addAllElements(getModel().getGroups());
		groupPanelList.updateUI();
	}

	private void updateAddressList() {
		if (!getModel().hasAddresses()) {
			contactController.preloadEntity(getModel(), Address.class);
		}
		addressPanelList.clear();
		addressPanelList.addAllElements(getModel().getAddresses());
		addressPanelList.updateUI();
	}

	private void updatePhoneList() {
		if (getModel().getPhones().isEmpty()) {
			contactController.preloadEntity(getModel(), Phone.class);
		}
		phonePanelList.clear();
		phonePanelList.addAllElements(getModel().getPhones());
		phonePanelList.updateUI();
	}

	private void updateVAddressList() {
		if (getModel().getVirtualAddresses().isEmpty()) {
			contactController.preloadEntity(getModel(), VirtualAddress.class);
		}
		vAddressPanelList.clear();
		vAddressPanelList.addAllElements(getModel().getVirtualAddresses());
		vAddressPanelList.updateUI();
	}

	@Override
	public void update(Observable source, Object model) {
		// from contacts
		if (source.equals(contactController)) {
			final EntityStatus<Contact> entityStatus = (EntityStatus<Contact>) model;

			if (getModel().equals(entityStatus.getEntity())) {// was deleted
				if (entityStatus.getStatus() == StatusType.DELETED) {
					this.close();
				} else {
					LOG.info("Updating model : " + this.getClass().getName() + ", values: " + entityStatus.toString());
					setModel(entityStatus.getEntity());
				}
			}
		}// from groups
		else if (source.getClass().equals(GroupController.class)) {
			updateLists();
		}
	}

	@Override
	Controller<Contact> getController() {
		return contactController;
	}
}
