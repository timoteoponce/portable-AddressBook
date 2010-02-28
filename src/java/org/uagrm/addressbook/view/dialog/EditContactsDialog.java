/*
 * Created by JFormDesigner on Sat Feb 27 12:28:09 VET 2010
 */

package org.uagrm.addressbook.view.dialog;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.controller.GroupController;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.view.event.SearchEvent;
import org.uagrm.addressbook.view.event.SearchEventListener;
import org.uagrm.addressbook.view.event.SearchEventType;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author Timoteo Ponce
 */
public class EditContactsDialog extends JDialog {

    private static final Logger LOG = Logger
	    .getLogger(EditContactsDialog.class);

    private Controller<Group> groupController;

    public EditContactsDialog(Frame owner, Controller<Group> groupController) {
	super(owner);
	initComponents();
	this.groupController = groupController;
    }

    public EditContactsDialog(Dialog owner, Controller<Group> groupController) {
	super(owner);
	initComponents();
	this.groupController = groupController;
    }

    private void btnSearchGroupActionPerformed(ActionEvent e) {
	searchGroup();
    }

    private void searchGroup() {
	SearchDialog dialog = new SearchDialog(this);
	dialog
		.setValidElements((Collection<SelectableItem>) ((Collection<? extends SelectableItem>) groupController
			.getElements()));
	dialog.showDialog();
	dialog.addSearchEventListener(new SearchEventListener() {

	    @Override
	    public void eventFired(SearchEvent event) {
		SearchDialog dialog = (SearchDialog ) event.getSource();
		
		if(event.getType() == SearchEventType.SELECTED){
		    LOG.info("Selected group: " + dialog.getSelected());
		}else{
		    LOG.info("Search cancelled");
		}
	    }
	});
    }

    private void initComponents() {
	// JFormDesigner - Component initialization - DO NOT MODIFY
	// //GEN-BEGIN:initComponents
	ResourceBundle bundle = ResourceBundle.getBundle("messages");
	DefaultComponentFactory compFactory = DefaultComponentFactory
		.getInstance();
	dialogPane = new JPanel();
	contentPanel = new JPanel();
	panel1 = new JPanel();
	lblGroup = new JLabel();
	txtGroup = new JTextField();
	btnSearchGroup = new JButton();
	panel2 = new JPanel();
	membersSep = compFactory.createSeparator(bundle
		.getString("GroupEdit.members"));
	panel3 = new JPanel();
	btnAdd = new JButton();
	btnRemove = new JButton();
	scrollPaneContacts = new JScrollPane();
	listContacts = new JList();
	buttonBar = new JPanel();
	okButton = new JButton();
	cancelButton = new JButton();
	CellConstraints cc = new CellConstraints();

	// ======== this ========
	Container contentPane = getContentPane();
	contentPane.setLayout(new BorderLayout());

	// ======== dialogPane ========
	{
	    dialogPane.setBorder(Borders.DIALOG_BORDER);
	    dialogPane.setLayout(new BorderLayout());

	    // ======== contentPanel ========
	    {
		contentPanel.setLayout(new FormLayout("181dlu",
			"2*(default, $lgap), default:grow"));

		// ======== panel1 ========
		{
		    panel1.setLayout(new FormLayout(
			    "33dlu, $lcgap, 99dlu, $lcgap, 42dlu", "default"));

		    // ---- lblGroup ----
		    lblGroup.setText(bundle
			    .getString("EditContactsDialog.group"));
		    panel1.add(lblGroup, cc.xy(1, 1));

		    // ---- txtGroup ----
		    txtGroup.setEditable(false);
		    panel1.add(txtGroup, cc.xy(3, 1));

		    // ---- btnSearchGroup ----
		    btnSearchGroup.setText(bundle
			    .getString("SearchDialog.search"));
		    btnSearchGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    btnSearchGroupActionPerformed(e);
			}
		    });
		    panel1.add(btnSearchGroup, cc.xy(5, 1));
		}
		contentPanel.add(panel1, cc.xy(1, 1));

		// ======== panel2 ========
		{
		    panel2.setLayout(new FormLayout(
			    "default:grow, $lcgap, default", "default"));
		    panel2.add(membersSep, cc.xy(1, 1));

		    // ======== panel3 ========
		    {
			panel3.setLayout(new FormLayout(
				"default, $lcgap, default", "default"));

			// ---- btnAdd ----
			btnAdd.setText("+");
			panel3.add(btnAdd, cc.xy(1, 1));

			// ---- btnRemove ----
			btnRemove.setText("-");
			panel3.add(btnRemove, cc.xy(3, 1));
		    }
		    panel2.add(panel3, cc.xy(3, 1));
		}
		contentPanel.add(panel2, cc.xy(1, 3));

		// ======== scrollPaneContacts ========
		{
		    scrollPaneContacts.setViewportView(listContacts);
		}
		contentPanel.add(scrollPaneContacts, cc.xy(1, 5));
	    }
	    dialogPane.add(contentPanel, BorderLayout.CENTER);

	    // ======== buttonBar ========
	    {
		buttonBar.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
		buttonBar.setLayout(new FormLayout(
			"$glue, $button, $rgap, $button", "pref"));

		// ---- okButton ----
		okButton.setText("OK");
		buttonBar.add(okButton, cc.xy(2, 1));

		// ---- cancelButton ----
		cancelButton.setText("Cancel");
		buttonBar.add(cancelButton, cc.xy(4, 1));
	    }
	    dialogPane.add(buttonBar, BorderLayout.SOUTH);
	}
	contentPane.add(dialogPane, BorderLayout.CENTER);
	pack();
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
}
