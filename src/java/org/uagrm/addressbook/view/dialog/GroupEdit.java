/*
 * Created by JFormDesigner on Sun Jan 31 22:34:12 VET 2010
 */

package org.uagrm.addressbook.view.dialog;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.controller.GroupController;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.view.View;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class GroupEdit extends JDialog implements View<Group> {

    private static final Logger LOG = Logger.getLogger(GroupEdit.class);

    private final Controller<Group> controller = ControllerFactory
	    .getInstance(GroupController.class);
    private Group group;
    private boolean isCreation;

    public GroupEdit(Frame owner) {
	super(owner);
	initComponents();
    }

    private void btnAcceptActionPerformed(ActionEvent e) {
	setVisible(false);
	updateValues();
	controller.save(group);
	controller.saveReferences(group, Contact.class);
	close();
	getController().fireChange(group);
    }

    private void btnCancelActionPerformed(ActionEvent e) {
	close();
    }

    private void btnEditActionPerformed(ActionEvent e) {
	editMembers();
    }

    /*
     * public GroupEdit(Dialog owner) { super(owner); initComponents(); }
     */

    private void editMembers() {
	EditContactsDialog dialog = new EditContactsDialog(this);
	dialog.setModel(group);
	dialog.setLocked(isCreation);
	controller.addView(dialog);
	dialog.setVisible(true);
    }

    private void initComponents() {
	// JFormDesigner - Component initialization - DO NOT MODIFY
	// //GEN-BEGIN:initComponents
	ResourceBundle bundle = ResourceBundle.getBundle("messages");
	DefaultComponentFactory compFactory = DefaultComponentFactory
		.getInstance();
	separator2 = compFactory.createSeparator(bundle
		.getString("GroupEdit.separator1.text"));
	lblName = new JLabel();
	txtName = new JTextField();
	lblDescription = new JLabel();
	txtDescription = new JTextField();
	separator1 = compFactory.createSeparator(bundle
		.getString("GroupEdit.members"));
	btnEdit = new JButton();
	panelOperations = new JPanel();
	btnAccept = new JButton();
	btnCancel = new JButton();
	CellConstraints cc = new CellConstraints();

	// ======== this ========
	setTitle(bundle.getString("GroupEdit.title"));
	Container contentPane = getContentPane();
	contentPane
		.setLayout(new FormLayout("31dlu, 55dlu, 109dlu, default:grow",
			"default:grow, 4*(default), default:grow, default, fill:16dlu"));
	contentPane.add(separator2, cc.xywh(2, 1, 2, 1));

	// ---- lblName ----
	lblName.setText(bundle.getString("GroupEdit.label.name"));
	lblName.setLabelFor(txtName);
	lblName.setDisplayedMnemonic('N');
	contentPane.add(lblName, cc.xy(2, 2));
	contentPane.add(txtName, cc.xy(3, 2));

	// ---- lblDescription ----
	lblDescription.setText(bundle.getString("GroupEdit.label.description"));
	lblDescription.setLabelFor(txtDescription);
	lblDescription.setDisplayedMnemonic('D');
	contentPane.add(lblDescription, cc.xy(2, 3));
	contentPane.add(txtDescription, cc.xy(3, 3));
	contentPane.add(separator1, cc.xywh(2, 4, 2, 1));

	// ---- btnEdit ----
	btnEdit.setText(bundle.getString("GroupEdit.editMembers"));
	btnEdit.setMnemonic('E');
	btnEdit.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnEditActionPerformed(e);
	    }
	});
	contentPane.add(btnEdit, cc.xy(3, 5));

	// ======== panelOperations ========
	{
	    panelOperations.setLayout(new FormLayout(
		    "2*(default:grow, $lcgap), default:grow", "default"));

	    // ---- btnAccept ----
	    btnAccept.setText(bundle.getString("common.accept"));
	    btnAccept.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    btnAcceptActionPerformed(e);
		}
	    });
	    panelOperations.add(btnAccept, cc.xy(3, 1));

	    // ---- btnCancel ----
	    btnCancel.setText(bundle.getString("common.cancel"));
	    btnCancel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    btnCancelActionPerformed(e);
		}
	    });
	    panelOperations.add(btnCancel, cc.xy(5, 1));
	}
	contentPane.add(panelOperations, cc.xywh(2, 7, 2, 1));
	setSize(410, 195);
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
    private JComponent separator1;
    private JButton btnEdit;
    private JPanel panelOperations;
    private JButton btnAccept;
    private JButton btnCancel;

    // JFormDesigner - End of variables declaration //GEN-END:variables

    private void loadValues() {
	txtName.setText(group.getName());
	txtDescription.setText(group.getDescription());
    }

    private void updateValues() {
	group.setName(txtName.getText());
	group.setDescription(txtDescription.getText());
    }

    @Override
    public Controller<Group> getController() {
	return controller;
    }

    @Override
    public void setModel(Group model) {
	this.group = model;
	loadValues();
    }

    @Override
    public void update(Group model) {
	if (this.isVisible()) {
	    if (model == null) {// was deleted
		this.close();
	    } else if (this.group.equals(model)) {
		LOG.info("Updating model : " + this.getClass().getName()
			+ ", values: " + model.toString());
		setModel(model);
	    }
	}
    }

    public void setIsCreation(boolean value) {
	this.isCreation = value;
	setModel(new Group());
	btnAccept.setText("Save");
	LOG.debug("IsCreation: " + value);
    }

    @Override
    public void close() {
	getController().removeView(this);
	this.dispose();
    }
}
