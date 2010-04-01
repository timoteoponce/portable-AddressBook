/*
 * Created by JFormDesigner on Sat Feb 27 12:28:09 VET 2010
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
import org.uagrm.addressbook.model.swing.ListModel;
import org.uagrm.addressbook.view.View;
import org.uagrm.addressbook.view.component.ContactActionPanelList;
import org.uagrm.addressbook.view.event.SearchEvent;
import org.uagrm.addressbook.view.event.SearchEventListener;
import org.uagrm.addressbook.view.event.SearchEventType;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 * @deprecated Use {@link ContactActionPanelList} instead.
 */
public class EditGroupContactsDialog extends JDialog implements View<Group> {

	private static final Logger LOG = Logger
			.getLogger(EditGroupContactsDialog.class);

	private final Controller<Group> groupController = ControllerFactory
			.getInstance(GroupController.class);

	private final Controller<Contact> contactController = ControllerFactory
			.getInstance(ContactController.class);

	private final ListModel<Contact> contactListModel = new ListModel<Contact>();

	private Group group;

	public EditGroupContactsDialog(Frame owner) {
		super(owner);
		initComponents();
		init();
	}

	private void init() {
		ControllerFactory.getInstance(GroupController.class).addView(this);
		contactListModel.clear();
		this.listContacts.setModel(contactListModel);
	}

	public EditGroupContactsDialog(Dialog owner) {
		super(owner);
		initComponents();
		init();
	}

	private void btnSearchGroupActionPerformed(ActionEvent e) {
		searchGroup();
	}

	private void searchGroup() {
		SearchDialog dialog = new SearchDialog(this);
		dialog
				.setValidElements((List<SelectableItem>) ((List<? extends SelectableItem>) groupController
						.getElements()));
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
					setModel((Group) dialog.getSelected());
				} else {
					LOG.debug("Group search cancelled");
				}
			}
		};
		return listener;
	}

	private void btnAddActionPerformed(ActionEvent e) {
		searchContact();
	}

	private void searchContact() {
		SearchDialog dialog = new SearchDialog(this);
		dialog
				.setValidElements((List<SelectableItem>) ((List<? extends SelectableItem>) contactController
						.getElements()));
		dialog
				.setInvalidElements((List<SelectableItem>) ((List<? extends SelectableItem>) contactListModel
						.getElements()));

		dialog.showDialog();
		dialog.addSearchEventListener(getSearchContactListener());
	}










	private SearchEventListener getSearchContactListener() {
		SearchEventListener listener = new SearchEventListener() {

			@Override
			public void eventFired(SearchEvent event) {
				SearchDialog dialog = (SearchDialog) event.getSource();

				if (event.getType() == SearchEventType.SELECTED) {
					LOG.debug("Selected contact: " + dialog.getSelected());
					addContact((Contact) dialog.getSelected());
				} else {
					LOG.debug("Contact search cancelled");
				}
			}
		};
		return listener;
	}

	private void addContact(Contact contact) {
		if (contact != null) {
			contactListModel.addElement(contact);
			listContacts.updateUI();
		}
	}

	private void btnRemoveActionPerformed(ActionEvent e) {
		removeContact();
	}

	private void removeContact() {
		int index = listContacts.getSelectedIndex();

		if (index >= 0) {
			contactListModel.removeElement(index);
		}
	}

	public void setLocked(boolean value) {
		btnSearchGroup.setEnabled(!value);
	}

	private void okButtonActionPerformed(ActionEvent e) {
		okAction();
	}

	private void okAction() {
		group.getContacts().clear();
		group.getContacts().addAll(contactListModel.getElements());
		close();
	}

	private void cancelButtonActionPerformed(ActionEvent e) {
		close();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		ResourceBundle bundle = ResourceBundle.getBundle("messages");
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		panel1 = new JPanel();
		lblGroup = new JLabel();
		txtGroup = new JTextField();
		btnSearchGroup = new JButton();
		panel2 = new JPanel();
		membersSep = compFactory.createSeparator(bundle.getString("GroupEdit.members"));
		panel3 = new JPanel();
		btnAdd = new JButton();
		btnRemove = new JButton();
		scrollPaneContacts = new JScrollPane();
		listContacts = new JList();
		buttonBar = new JPanel();
		okButton = new JButton();
		cancelButton = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(Borders.DIALOG_BORDER);
			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new FormLayout(
					"181dlu:grow",
					"2*(default, $lgap), default:grow"));

				//======== panel1 ========
				{
					panel1.setLayout(new FormLayout(
						"33dlu, $lcgap, 113dlu, $lcgap, 43dlu",
						"default"));

					//---- lblGroup ----
					lblGroup.setText(bundle.getString("EditContactsDialog.group"));
					panel1.add(lblGroup, cc.xy(1, 1));

					//---- txtGroup ----
					txtGroup.setEditable(false);
					panel1.add(txtGroup, cc.xy(3, 1));

					//---- btnSearchGroup ----
					btnSearchGroup.setText(bundle.getString("SearchDialog.search"));
					btnSearchGroup.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							btnSearchGroupActionPerformed(e);
						}
					});
					panel1.add(btnSearchGroup, cc.xy(5, 1));
				}
				contentPanel.add(panel1, cc.xy(1, 1));

				//======== panel2 ========
				{
					panel2.setLayout(new FormLayout(
						"default:grow, $lcgap, 33dlu",
						"default"));
					panel2.add(membersSep, cc.xy(1, 1));

					//======== panel3 ========
					{
						panel3.setLayout(new FormLayout(
							"default, $lcgap, default",
							"default"));

						//---- btnAdd ----
						btnAdd.setText("+");
						btnAdd.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								btnAddActionPerformed(e);
							}
						});
						panel3.add(btnAdd, cc.xy(1, 1));

						//---- btnRemove ----
						btnRemove.setText("-");
						btnRemove.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								btnRemoveActionPerformed(e);
							}
						});
						panel3.add(btnRemove, cc.xy(3, 1));
					}
					panel2.add(panel3, cc.xy(3, 1));
				}
				contentPanel.add(panel2, cc.xy(1, 3));

				//======== scrollPaneContacts ========
				{
					scrollPaneContacts.setViewportView(listContacts);
				}
				contentPanel.add(scrollPaneContacts, cc.xy(1, 5));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
				buttonBar.setLayout(new FormLayout(
					"$glue, $button, $rgap, $button",
					"pref"));

				//---- okButton ----
				okButton.setText("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okButtonActionPerformed(e);
					}
				});
				buttonBar.add(okButton, cc.xy(2, 1));

				//---- cancelButton ----
				cancelButton.setText("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelButtonActionPerformed(e);
					}
				});
				buttonBar.add(cancelButton, cc.xy(4, 1));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		setSize(330, 300);
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JPanel panel1;
	private JLabel lblGroup;
	private JTextField txtGroup;
	private JButton btnSearchGroup;
	private JPanel panel2;
	private JComponent membersSep;
	private JPanel panel3;
	private JButton btnAdd;
	private JButton btnRemove;
	private JScrollPane scrollPaneContacts;
	private JList listContacts;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables

	@Override
	public void setModel(Group model) {
		this.group = model;
		loadValues();
	}

	private void loadValues() {
		this.txtGroup.setText(group.getName());
		contactListModel.clear();

		if (group.getContacts().isEmpty()) {
			groupController.preloadEntity(group, Contact.class);
		}
		contactListModel.addAllElements(group.getContacts());
		listContacts.updateUI();




	}

	@Override
	public void close() {
		groupController.removeView(this);
		this.dispose();
	}

	@Override
	public void update(Observable source, Object model) {
		if (source.equals(groupController)) {
			if (model == null) {// was deleted
				this.close();
			} else if (this.group.equals((Group) model)) {
				setModel((Group) model);
			}
		}
	}

	@Override
	public Group getModel() {
		return group;
	}
}
