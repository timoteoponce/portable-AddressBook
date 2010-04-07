/*
 * Created by JFormDesigner on Sat Jan 30 22:52:25 VET 2010
 */

package org.uagrm.addressbook.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.view.cell.CustomListCellRenderer;
import org.uagrm.addressbook.view.dialog.GroupEditDialog;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class GroupListView extends AbstractListView<Group> {
	private static final Logger LOG = Logger.getLogger(GroupListView.class);

	private final Controller<Group> controller = ControllerFactory.getInstanceFor(Group.class);

	public GroupListView() {
		initComponents();
		init();
	}

	private void init() {
		controller.addView(this);
		groupList.setModel(getListModel());
		groupList.setCellRenderer(new CustomListCellRenderer());
		//
		updateList();
	}

	@Override
	public void updateList() {
		getListModel().clear();
		getListModel().addElement(new Group(null, "All", ""));
		getListModel().addAllElements(controller.getElements());
		groupList.updateUI();
	}

	private void groupListMouseClicked(MouseEvent e) {
		final int index = groupList.getSelectedIndex();

		if (e.getModifiers() == MouseEvent.BUTTON3_MASK && index > 0) {
			showPopUpMenu(e.getX(), e.getY());
		}
	}

	private void showPopUpMenu(final int posX, final int posY) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages");
		//
		JPopupMenu menu = new JPopupMenu();
		JMenuItem editItem = new JMenuItem(bundle.getString("common.edit"));
		JMenuItem removeItem = new JMenuItem(bundle.getString("common.remove"));
		menu.add(editItem);
		menu.add(removeItem);

		removeItem.addActionListener(getRemoveActionListener());
		editItem.addActionListener(getEditActionListener());
		menu.show(this, posX, posY);
	}

	@Override
	public void addNew() {
		GroupEditDialog dialog = new GroupEditDialog(getMainWindow());
		dialog.setIsCreation(true);
		dialog.setVisible(true);
	}

	private ActionListener getEditActionListener() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editCurrent();
			}
		};
		return listener;
	}

	@Override
	public void editCurrent() {
		final int index = groupList.getSelectedIndex();
		if (index > 0) {
			GroupEditDialog dialog = new GroupEditDialog(getMainWindow());
			dialog.setModel((Group) groupList.getSelectedValue());
			dialog.setVisible(true);
		}
	}

	private ActionListener getRemoveActionListener() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeCurrent();
			}
		};
		return listener;
	}



	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		groupsPanel = new JScrollPane();
		groupList = new JList();
		CellConstraints cc = new CellConstraints();

		// ======== this ========
		setLayout(new FormLayout("14dlu, $lcgap, 82dlu:grow, $lcgap, 15dlu", "15dlu, $lgap, 190dlu:grow, $lgap, 15dlu"));

		// ======== groupsPanel ========
		{

			// ---- groupList ----
			groupList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			groupList.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					groupListMouseClicked(e);
				}
			});
			groupsPanel.setViewportView(groupList);
		}
		add(groupsPanel, cc.xywh(3, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JScrollPane groupsPanel;
	private JList groupList;

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
