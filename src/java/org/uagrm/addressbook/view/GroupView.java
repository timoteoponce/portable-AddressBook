/*
 * Created by JFormDesigner on Sat Mar 13 12:55:24 VET 2010
 */

package org.uagrm.addressbook.view;

import java.util.Observable;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.view.event.GenericEvent;
import org.uagrm.addressbook.view.event.GenericEventListener;
import org.uagrm.addressbook.view.event.GenericEventType;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class GroupView extends JPanel implements View<Group>, GenericEventListener {

	private static final Logger LOG = Logger.getLogger(GroupView.class);

	private final Controller<Group> groupController = ControllerFactory.getInstanceFor(Group.class);

	private Group group;

	public GroupView() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblGroup = new JLabel();
		lblGroupName = new JLabel();
		lblContacts = new JLabel();
		lblGroupContacts = new JLabel();
		sepDescription = compFactory.createSeparator("Description");
		scrollPane = new JScrollPane();
		textDescription = new JTextPane();
		CellConstraints cc = new CellConstraints();

		// ======== this ========
		setLayout(new FormLayout("default, $lcgap, 67dlu, $lcgap, 61dlu:grow, $lcgap, default", "4*(default, $lgap), default:grow, $lgap, default"));

		// ---- lblGroup ----
		lblGroup.setText("Group:");
		add(lblGroup, cc.xy(3, 3));
		add(lblGroupName, cc.xy(5, 3));

		// ---- lblContacts ----
		lblContacts.setText("Members:");
		add(lblContacts, cc.xy(3, 5));
		add(lblGroupContacts, cc.xy(5, 5));
		add(sepDescription, cc.xywh(3, 7, 3, 1));

		// ======== scrollPane ========
		{
			scrollPane.setViewportView(textDescription);
		}
		add(scrollPane, cc.xywh(3, 9, 3, 1, CellConstraints.FILL, CellConstraints.FILL));
		// //GEN-END:initComponents
	}

	@Override
	public void close() {
		this.setVisible(false);
	}

	@Override
	public void setModel(Group model) {
		this.group = model;
		if (group.getContacts().isEmpty()) {
			groupController.preloadEntity(group, Contact.class);
		}
		loadValues();
	}

	private void loadValues() {
		lblGroupName.setText("<html><h3>" + getModel().getName() + "</h3></html>");
		lblGroupContacts.setText(getModel().getContacts().size() + "");
	}

	@Override
	public void update(Observable o, Object arg) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Group getModel() {
		return group;
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JLabel lblGroup;
	private JLabel lblGroupName;
	private JLabel lblContacts;
	private JLabel lblGroupContacts;
	private JComponent sepDescription;
	private JScrollPane scrollPane;
	private JTextPane textDescription;

	// JFormDesigner - End of variables declaration //GEN-END:variables
	@Override
	public void eventFired(GenericEvent event) {
		if (event.getType() == GenericEventType.ELEMENT_SELECTED) {
			LOG.debug("Event ELEMENT_SELECTED received");
			if (event.getSource() instanceof GroupListView) {
				final GroupListView listView = (GroupListView) event.getSource();
				setModel(listView.getModel());
			}
		}
	}

}
