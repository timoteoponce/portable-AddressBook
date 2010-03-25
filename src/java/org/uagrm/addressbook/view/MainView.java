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
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.lang.StringUtils;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.view.dialog.ContactEditDialog;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif_lite.panel.SimpleInternalFrame;

/**
 * @author Timoteo Ponce
 * 
 */
public class MainView extends JFrame {

	private final GroupListView groupListView;
	private final ContactListView contactListView;

	public MainView() {
		initComponents();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// groupView
		groupListView = new GroupListView();
		groupListView.setMainView(this);
		panelGroups.add(groupListView, BorderLayout.CENTER);
		// contactView
		contactListView = new ContactListView();
		contactListView.setMainView(this);
		panelContacts.add(contactListView, BorderLayout.CENTER);
		//
		setTitle("MainView");
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

	private void tbButtonCreateGroupActionPerformed(ActionEvent e) {
	    groupListView.showcreateDialog();
	}

	private void tbButtonCreateContactActionPerformed(ActionEvent e) {
		//TODO put this is ContactView
		ContactEditDialog dialog = new ContactEditDialog(this);
		dialog.setContact(new Contact());
		dialog.setVisible(true);
	}


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		ResourceBundle bundle = ResourceBundle.getBundle("messages");
		mainMenuBar = new JMenuBar();
		toolBar1 = new JToolBar();
		tbButtonCreateGroup = new JButton();
		tbButtonCreateContact = new JButton();
		mainPanel = new JPanel();
		panelGroups = new SimpleInternalFrame();
		panelContacts = new SimpleInternalFrame();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle(bundle.getString("MainView.title"));
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"default, $lcgap, default:grow, $lcgap, default",
			"29dlu, $lgap, default:grow, $lgap, default"));
		setJMenuBar(mainMenuBar);

		//======== toolBar1 ========
		{

			//---- tbButtonCreateGroup ----
			tbButtonCreateGroup.setText(bundle.getString("MainView.createGroup"));
			tbButtonCreateGroup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tbButtonCreateGroupActionPerformed(e);
				}
			});
			toolBar1.add(tbButtonCreateGroup);

			//---- tbButtonCreateContact ----
			tbButtonCreateContact.setText(bundle.getString("MainView.tbButtonCreateContact.text"));
			tbButtonCreateContact.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tbButtonCreateContactActionPerformed(e);
				}
			});
			toolBar1.add(tbButtonCreateContact);
		}
		contentPane.add(toolBar1, cc.xywh(1, 1, 3, 1));

		//======== mainPanel ========
		{
			mainPanel.setLayout(new FormLayout(
					"63dlu:grow, $lcgap, 75dlu:grow, $lcgap, default:grow",
				"default:grow"));

			//======== panelGroups ========
			{
				panelGroups.setTitle("Groups");
				Container panelGroupsContentPane = panelGroups.getContentPane();
				panelGroupsContentPane.setLayout(new BorderLayout());
			}
			mainPanel.add(panelGroups, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

			// ======== panelContacts ========
			{
				panelContacts.setTitle(bundle
						.getString("MainView.panelContacts.title"));
				Container panelContactsContentPane = panelContacts
						.getContentPane();
				panelContactsContentPane.setLayout(new BorderLayout());
			}
			mainPanel.add(panelContacts, cc.xywh(3, 1, 1, 1,
					CellConstraints.FILL, CellConstraints.FILL));
		}
		contentPane.add(mainPanel, cc.xywh(3, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		setSize(485, 415);
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JMenuBar mainMenuBar;
	private JToolBar toolBar1;
	private JButton tbButtonCreateGroup;
	private JButton tbButtonCreateContact;
	private JPanel mainPanel;
	private SimpleInternalFrame panelGroups;
	private SimpleInternalFrame panelContacts;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
