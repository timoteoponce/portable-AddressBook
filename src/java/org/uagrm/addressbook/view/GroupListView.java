/*
 * Created by JFormDesigner on Sat Jan 30 22:52:25 VET 2010
 */

package org.uagrm.addressbook.view;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.view.component.ActionButtons;
import org.uagrm.addressbook.view.dialog.GroupEditDialog;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class GroupListView extends AbstractListView<Group> {
	// private static final Logger LOG = Logger.getLogger(GroupListView.class);

	private final Controller<Group> controller = ControllerFactory.getInstanceFor(Group.class);
	private final ActionButtons actionButtons = new ActionButtons();

	public GroupListView() {
		initComponents();
		this.init();
	}

	@Override
	protected void init() {
		super.init();
		operationPanel.add(actionButtons, BorderLayout.CENTER);
		actionButtons.addActionListener(createActionButtonListener());
	}

	@Override
	public void updateList() {
		getListModel().clear();
		getListModel().addElement(new Group(null, "All", ""));
		getListModel().addAllElements(controller.getElements());
		groupList.updateUI();
	}

	@Override
	public void addNew() {
		GroupEditDialog dialog = new GroupEditDialog(getMainWindow());
		dialog.setModel(new Group());
		dialog.setEditable(true);
		dialog.setSaveable(true);
		dialog.setVisible(true);
	}

	@Override
	public void editCurrent() {
		if (getModel() != null) {
			GroupEditDialog dialog = new GroupEditDialog(getMainWindow());
			dialog.setModel(getModel());
			dialog.setEditable(true);
			dialog.setSaveable(true);
			dialog.setVisible(true);
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		groupsPanel = new JScrollPane();
		groupList = new JList();
		operationPanel = new JPanel();
		CellConstraints cc = new CellConstraints();

		// ======== this ========
		setLayout(new FormLayout("14dlu, $lcgap, 82dlu:grow, $lcgap, 15dlu", "15dlu, $lgap, 190dlu:grow, $lgap, 15dlu"));

		// ======== groupsPanel ========
		{

			// ---- groupList ----
			groupList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			groupsPanel.setViewportView(groupList);
		}
		add(groupsPanel, cc.xywh(3, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		// ======== operationPanel ========
		{
			operationPanel.setLayout(new BorderLayout());
		}
		add(operationPanel, cc.xywh(3, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JScrollPane groupsPanel;
	private JList groupList;
	private JPanel operationPanel;
	// JFormDesigner - End of variables declaration //GEN-END:variables

	@Override
	public Controller<Group> getController() {
		return controller;
	}

	@Override
	public JList getList() {
		return groupList;
	}

}
