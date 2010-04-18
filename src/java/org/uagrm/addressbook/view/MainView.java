package org.uagrm.addressbook.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.lang.StringUtils;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.view.event.GenericEvent;
import org.uagrm.addressbook.view.event.GenericEventListener;
import org.uagrm.addressbook.view.event.GenericEventType;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif_lite.panel.SimpleInternalFrame;

/**
 * @author Timoteo Ponce
 * 
 */
public class MainView extends JFrame {

	private final ListView<Group> groupListView;
	private final ListView<Contact> contactListView;
	private final View<Group> groupView;
	private final View<Contact> contactView;

	public MainView() {
		initComponents();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// groupView
		groupListView = new GroupListView();
		groupListView.setMainWindow(this);
		panelGroups.add((JPanel) groupListView, BorderLayout.CENTER);
		// contactView
		contactListView = new ContactListView();
		contactListView.setMainWindow(this);
		panelContacts.add((JPanel) contactListView, BorderLayout.CENTER);
		// views
		groupView = new GroupView();
		contactView = new ContactView();
		// panelView.add((JPanel) groupView, BorderLayout.CENTER);
		// panelView.add((JPanel) contactView, BorderLayout.CENTER);
		// listeners
		groupListView.addEventListener((GenericEventListener) contactListView);
		groupListView.addEventListener((GenericEventListener) groupView);
		contactListView.addEventListener((GenericEventListener) contactView);
		//
		setTitle("MainView");
		addListeners();
	}

	private void addListeners() {
		btnGroupsAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				groupListView.addNew();
			}
		});
		btnContactAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				contactListView.addNew();
			}
		});
		btnOpsEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				contactListView.addNew();
			}
		});

		groupListView.addEventListener(new GenericEventListener() {
			@Override
			public void eventFired(GenericEvent event) {
				if (event.getType() == GenericEventType.ELEMENT_SELECTED) {
					scrollPaneView.setViewportView((JPanel) groupView);
				}
			}
		});
		contactListView.addEventListener(new GenericEventListener() {
			@Override
			public void eventFired(GenericEvent event) {
				if (event.getType() == GenericEventType.ELEMENT_SELECTED) {
					scrollPaneView.setViewportView((JPanel) contactView);
				}
			}
		});
	}

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		if(StringUtils.isEmpty(System.getProperty("current.env"))){
			System.setProperty("current.env", "dev");// by default, dev is used
		}
		UIManager
				.setLookAndFeel(com.jgoodies.looks.plastic.PlasticXPLookAndFeel.class
						.getName());
		MainView frame = new MainView();
		frame.setVisible(true);
	}


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		ResourceBundle bundle = ResourceBundle.getBundle("messages");
		mainMenuBar = new JMenuBar();
		mainPanel = new JPanel();
		panelGroups = new SimpleInternalFrame();
		panelContacts = new SimpleInternalFrame();
		panelView = new SimpleInternalFrame();
		scrollPaneView = new JScrollPane();
		panelOpsGroup = new JPanel();
		btnGroupsAdd = new JButton();
		panelOpsContact = new JPanel();
		btnContactAdd = new JButton();
		panelOpsView = new JPanel();
		btnOpsEdit = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle(bundle.getString("MainView.title"));
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"default:grow",
			"default:grow"));
		setJMenuBar(mainMenuBar);

		//======== mainPanel ========
		{
			mainPanel.setLayout(new FormLayout(
				"87dlu:grow, $lcgap, 86dlu:grow, $lcgap, default:grow",
				"default:grow, $lgap, default"));

			//======== panelGroups ========
			{
				panelGroups.setTitle("Groups");
				Container panelGroupsContentPane = panelGroups.getContentPane();
				panelGroupsContentPane.setLayout(new BorderLayout());
			}
			mainPanel.add(panelGroups, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

			//======== panelContacts ========
			{
				panelContacts.setTitle(bundle.getString("MainView.panelContacts.title"));
				Container panelContactsContentPane = panelContacts.getContentPane();
				panelContactsContentPane.setLayout(new BorderLayout());
			}
			mainPanel.add(panelContacts, cc.xywh(3, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

			// ======== panelView ========
			{
				panelView.setTitle(bundle.getString("MainView.panelView.title"));
				Container panelViewContentPane = panelView.getContentPane();
				panelViewContentPane.setLayout(new BorderLayout());
				panelViewContentPane.add(scrollPaneView, BorderLayout.CENTER);
			}
			mainPanel.add(panelView, cc.xywh(5, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

			//======== panelOpsGroup ========
			{
				panelOpsGroup.setLayout(new FormLayout(
					"2*(default, $lcgap), default",
					"default"));

				//---- btnGroupsAdd ----
				btnGroupsAdd.setText(bundle.getString("MainView.btnGroupsAdd.text"));
				panelOpsGroup.add(btnGroupsAdd, cc.xy(3, 1));
			}
			mainPanel.add(panelOpsGroup, cc.xy(1, 3));

			//======== panelOpsContact ========
			{
				panelOpsContact.setLayout(new FormLayout(
					"2*(default, $lcgap), default",
					"default"));

				//---- btnContactAdd ----
				btnContactAdd.setText(bundle.getString("MainView.btnContactAdd.text"));
				panelOpsContact.add(btnContactAdd, cc.xy(3, 1));
			}
			mainPanel.add(panelOpsContact, cc.xy(3, 3));

			//======== panelOpsView ========
			{
				panelOpsView.setLayout(new FormLayout(
					"2*(default, $lcgap), default",
					"default"));

				//---- btnOpsEdit ----
				btnOpsEdit.setText(bundle.getString("MainView.btnOpsEdit.text"));
				panelOpsView.add(btnOpsEdit, cc.xy(1, 1));
			}
			mainPanel.add(panelOpsView, cc.xy(5, 3));
		}
		contentPane.add(mainPanel, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		setSize(625, 360);
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JMenuBar mainMenuBar;
	private JPanel mainPanel;
	private SimpleInternalFrame panelGroups;
	private SimpleInternalFrame panelContacts;
	private SimpleInternalFrame panelView;
	private JScrollPane scrollPaneView;
	private JPanel panelOpsGroup;
	private JButton btnGroupsAdd;
	private JPanel panelOpsContact;
	private JButton btnContactAdd;
	private JPanel panelOpsView;
	private JButton btnOpsEdit;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
