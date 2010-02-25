/*
 * Created by JFormDesigner on Sat Jan 30 22:52:25 VET 2010
 */

package org.ponce.addressbook.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.apache.log4j.Logger;
import org.ponce.addressbook.controller.Controller;
import org.ponce.addressbook.controller.GroupController;
import org.ponce.addressbook.model.Group;
import org.ponce.addressbook.view.dialog.GroupEdit;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class GroupView extends JPanel implements View<Group> {
	private static final Logger LOG = Logger.getLogger(GroupView.class);

	private Controller<Group> controller;

	public GroupView(Controller<Group> controller) {
		this.controller = controller;
		controller.addView(this);
		initComponents();
		init();
	}

	private void init() {
		groupList.setModel(new DefaultListModel());
		groupList.setCellRenderer(new GroupListCellRenderer());
		//
		updateList();
	}

	private DefaultListModel getListModel() {
		return (DefaultListModel) groupList.getModel();

	}

	public void updateList() {
		getListModel().clear();
		getListModel().addElement("All");

		Collection<Group> groups = controller.getElements();
		for (Group group : groups) {
			getListModel().addElement(group);
		}
		groupList.updateUI();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GroupView groupView = new GroupView(GroupController.getInstance());
		frame.setContentPane(groupView);
		frame.setVisible(true);
	}

	private void groupListMouseClicked(MouseEvent e) {
		final int index = groupList.getSelectedIndex();

		if (e.getModifiers() == MouseEvent.BUTTON3_MASK /* && index > 0 */) {
			showPopUpMenu(e.getX(), e.getY());
		}
	}

	private void showPopUpMenu(final int posX, final int posY) {
		JPopupMenu menu = new JPopupMenu();
		JMenuItem createItem = new JMenuItem("Create");
		JMenuItem editItem = new JMenuItem("Edit");
		JMenuItem removeItem = new JMenuItem("Remove");
		menu.add(createItem);
		menu.add(editItem);
		menu.add(removeItem);

		createItem.addActionListener(getCreateActionListener());
		removeItem.addActionListener(getRemoveActionListener());
		editItem.addActionListener(getEditActionListener());

		menu.show(this, posX, posY);
	}

	private ActionListener getCreateActionListener() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GroupEdit dialog = new GroupEdit(null);
				dialog.setIsCreation(true);
				controller.addView(dialog);
				dialog.setVisible(true);
			}
		};
		return listener;
	}

	private ActionListener getEditActionListener() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final int index = groupList.getSelectedIndex();
				if (index > 0) {
					GroupEdit dialog = new GroupEdit(null);
					dialog.setModel((Group) groupList.getSelectedValue());
					controller.addView(dialog);
					dialog.setVisible(true);
				}
			}
		};
		return listener;
	}

	private ActionListener getRemoveActionListener() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final int index = groupList.getSelectedIndex();
				if (index > 0) {
					final Group group = (Group) groupList.getSelectedValue();
					controller.delete((Group) groupList.getSelectedValue());
					controller.modelChanged(group);
				}
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
		setLayout(new FormLayout(
				"default, $lcgap, default:grow, $lcgap, default",
				"default, $lgap, default:grow, $lgap, default"));

		// ======== groupsPanel ========
		{

			// ---- groupList ----
			groupList
					.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			groupList.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					groupListMouseClicked(e);
				}
			});
			groupsPanel.setViewportView(groupList);
		}
		add(groupsPanel, cc.xywh(3, 3, 1, 1, CellConstraints.FILL,
				CellConstraints.FILL));
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JScrollPane groupsPanel;
	private JList groupList;

	// JFormDesigner - End of variables declaration //GEN-END:variables
	@Override
	public void setModel(Group model) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		LOG.info("Updating view.");
		updateList();
	}

	@Override
	public Controller<Group> getController() {
		return controller;
	}

	@Override
	public void setController(Controller<Group> controller) {
		this.controller = controller;

	}
}
