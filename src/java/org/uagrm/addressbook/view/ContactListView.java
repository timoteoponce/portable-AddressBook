/*
 * Created by JFormDesigner on Sat Mar 13 12:54:54 VET 2010
 */

package org.uagrm.addressbook.view;

import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.swing.ListModel;
import org.uagrm.addressbook.view.cell.CustomListCellRenderer;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class ContactListView extends JPanel implements View<Contact> {

	private static final Logger LOG = Logger.getLogger(ContactListView.class);

	private final Controller<Contact> controller = ControllerFactory.getInstanceFor(Contact.class);

	private final ListModel<Contact> listModel = new ListModel<Contact>();

	private JFrame mainWindow;

	public ContactListView() {
		initComponents();
		init();
	}

	private void init() {
		controller.addView(this);
		contactList.setModel(listModel);
		contactList.setCellRenderer(new CustomListCellRenderer());
		updateList();
	}

	private void updateList() {
		listModel.clear();
		listModel.addAllElements(controller.getElements());
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		scrollContactList = new JScrollPane();
		contactList = new JList();
		CellConstraints cc = new CellConstraints();

		// ======== this ========
		setLayout(new FormLayout("16dlu, $lcgap, default:grow, $lcgap, 16dlu", "15dlu, $lgap, default:grow, $lgap, 15dlu"));

		// ======== scrollContactList ========
		{
			scrollContactList.setViewportView(contactList);
		}
		add(scrollContactList, cc.xywh(3, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		// //GEN-END:initComponents
	}

	@Override
	public void close() {
		controller.removeView(this);
		this.setVisible(false);
	}

	@Override
	public void setModel(Contact model) {
		// not needed in this view,
	}

	@Override
	public void update(Observable source, Object model) {
		if (source.equals(controller)) {
			updateList();
		}

	}

	public void setMainView(JFrame mainView) {
		this.mainWindow = mainView;
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JScrollPane scrollContactList;
	private JList contactList;

	// JFormDesigner - End of variables declaration //GEN-END:variables

	@Override
	public Contact getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
