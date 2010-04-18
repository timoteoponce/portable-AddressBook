/*
 * Created by JFormDesigner on Sun Jan 31 22:34:12 VET 2010
 */

package org.uagrm.addressbook.view.dialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.model.dto.EntityStatus;
import org.uagrm.addressbook.model.dto.StatusType;
import org.uagrm.addressbook.view.component.ActionPanelList;
import org.uagrm.addressbook.view.component.ContactActionPanelList;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class GroupEditDialog extends AbstractDialogView<Group> {

	private static final Logger LOG = Logger.getLogger(GroupEditDialog.class);

	private final Controller<Group> controller = ControllerFactory.getInstanceFor(Group.class);

	private final Controller<Contact> contactController = ControllerFactory.getInstanceFor(Contact.class);

	private final ActionPanelList<Contact> contactList = new ContactActionPanelList(false);


	public GroupEditDialog(Frame owner) {
		super(owner);
		initComponents();
		init();
	}

	public GroupEditDialog(Dialog parent) {
		super(parent);
		initComponents();
		init();
	}

	private void init() {
		controller.addView(this);
		contactController.addView(this);
		this.panelContacts.add(BorderLayout.CENTER, contactList);
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
		separator2 = compFactory.createSeparator(bundle.getString("GroupEdit.separator1.text"));
		lblName = new JLabel();
		txtName = new JTextField();
		lblDescription = new JLabel();
		txtDescription = new JTextField();
		panelContacts = new JPanel();
		panelOperations = new JPanel();
		btnAccept = new JButton();
		btnCancel = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle(bundle.getString("GroupEdit.title"));
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"31dlu, 55dlu, 109dlu, default:grow",
 "default, default:grow, 4*(default), default:grow, 2*(default), fill:16dlu"));
		contentPane.add(separator2, cc.xywh(2, 2, 2, 1));

		//---- lblName ----
		lblName.setText(bundle.getString("GroupEdit.label.name"));
		lblName.setLabelFor(txtName);
		lblName.setDisplayedMnemonic('N');
		contentPane.add(lblName, cc.xy(2, 3));
		contentPane.add(txtName, cc.xy(3, 3));

		//---- lblDescription ----
		lblDescription.setText(bundle.getString("GroupEdit.label.description"));
		lblDescription.setLabelFor(txtDescription);
		lblDescription.setDisplayedMnemonic('D');
		contentPane.add(lblDescription, cc.xy(2, 4));
		contentPane.add(txtDescription, cc.xy(3, 4));

		// ======== panelContacts ========
		{
			panelContacts.setLayout(new BorderLayout());
		}
		contentPane.add(panelContacts, cc.xywh(2, 6, 2, 2, CellConstraints.FILL, CellConstraints.FILL));

		//======== panelOperations ========
		{
			panelOperations.setLayout(new FormLayout(
				"2*(default:grow, $lcgap), default:grow",
				"default"));

			//---- btnAccept ----
			btnAccept.setText(bundle.getString("common.accept"));
			btnAccept.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnAcceptActionPerformed(e);
				}
			});
			panelOperations.add(btnAccept, cc.xy(3, 1));

			//---- btnCancel ----
			btnCancel.setText(bundle.getString("common.cancel"));
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnCancelActionPerformed(e);
				}
			});
			panelOperations.add(btnCancel, cc.xy(5, 1));
		}
		contentPane.add(panelOperations, cc.xywh(2, 9, 2, 1));
		setSize(490, 355);
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JComponent separator2;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblDescription;
	private JTextField txtDescription;
	private JPanel panelContacts;
	private JPanel panelOperations;
	private JButton btnAccept;
	private JButton btnCancel;
	// JFormDesigner - End of variables declaration //GEN-END:variables

	@Override
	public void loadValues() {
		txtName.setText(getModel().getName());
		txtDescription.setText(getModel().getDescription());
		updateContactList();
	}

	private void updateContactList() {
		// if (getModel().getContacts().isEmpty()) {
		controller.preloadEntity(getModel(), Contact.class);
		// }
		contactList.addAllElements(getModel().getContacts());
	}

	@Override
	public void updateValues() {
		getModel().setName(txtName.getText());
		getModel().setDescription(txtDescription.getText());
		//
		getModel().getContacts().clear();
		getModel().getContacts().addAll(contactList.getElements());
	}

	@Override
	Controller<Group> getController() {
		return controller;
	}

	@Override
	public void close() {
		controller.removeView(this);
		contactController.removeView(this);
		this.dispose();
	}

	@Override
	public void update(Observable source, Object model) {
		if (source.equals(controller)) {
			final EntityStatus<Group> entityStatus = (EntityStatus<Group>) model;
			if (entityStatus.getEntity().equals(model)) {
				if (entityStatus.getStatus() == StatusType.DELETED) {
					this.close();
				} else {
					setModel(entityStatus.getEntity());
				}
			}
		} else if (source.equals(contactController)) {
			final EntityStatus<Contact> entityStatus = (EntityStatus<Contact>) model;			
			final int index = ((List<Contact>) contactList.getElements()).lastIndexOf(entityStatus.getEntity());

			if (index > -1) {
				if (entityStatus.getStatus() == StatusType.DELETED) {
					((List<Contact>) contactList.getElements()).remove(index);
					contactList.updateUI();
				}
			}
		}
	}


}
