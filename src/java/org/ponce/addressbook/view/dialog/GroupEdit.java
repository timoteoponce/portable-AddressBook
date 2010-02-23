/*
 * Created by JFormDesigner on Sun Jan 31 22:34:12 VET 2010
 */

package org.ponce.addressbook.view.dialog;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.ponce.addressbook.controller.Controller;
import org.ponce.addressbook.model.Group;
import org.ponce.addressbook.view.View;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponsh
 */
public class GroupEdit extends JDialog implements View<Group> {

    private static final Logger LOG = Logger.getLogger(GroupEdit.class);

    private Controller<Group> controller;
    private Group group;
    private boolean isCreation;

    public GroupEdit(Frame owner) {
	super(owner);
	initComponents();
    }

    private void btnAcceptActionPerformed(ActionEvent e) {
	if (isCreation) {
	    updateValues();
	    controller.save(group);
	    getController().removeView(this);
	    getController().modelChanged(group);
	    this.dispose();
	}

    }

    /*
     * public GroupEdit(Dialog owner) { super(owner); initComponents(); }
     */

    private void initComponents() {
	// JFormDesigner - Component initialization - DO NOT MODIFY
	// //GEN-BEGIN:initComponents
	lblName = new JLabel();
	txtName = new JTextField();
	lblDescription = new JLabel();
	txtDescription = new JTextField();
	btnEdit = new JButton();
	panelOperations = new JPanel();
	btnAccept = new JButton();
	btnCancel = new JButton();
	CellConstraints cc = new CellConstraints();

	// ======== this ========
	Container contentPane = getContentPane();
	contentPane.setLayout(new FormLayout(
		"default, 55dlu, 109dlu, default:grow",
		"5*(default), fill:default:grow"));

	// ---- lblName ----
	lblName.setText("Name:");
	lblName.setLabelFor(txtName);
	lblName.setDisplayedMnemonic('N');
	contentPane.add(lblName, cc.xy(2, 2));
	contentPane.add(txtName, cc.xy(3, 2));

	// ---- lblDescription ----
	lblDescription.setText("Description:");
	lblDescription.setLabelFor(txtDescription);
	lblDescription.setDisplayedMnemonic('D');
	contentPane.add(lblDescription, cc.xy(2, 3));
	contentPane.add(txtDescription, cc.xy(3, 3));

	// ---- btnEdit ----
	btnEdit.setText("Edit group members");
	btnEdit.setMnemonic('E');
	contentPane.add(btnEdit, cc.xy(2, 4));

	// ======== panelOperations ========
	{
	    panelOperations.setLayout(new FormLayout(
		    "default:grow, $lcgap, default:grow", "default"));

	    // ---- btnAccept ----
	    btnAccept.setText("Accept");
	    btnAccept.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    btnAcceptActionPerformed(e);
		}
	    });
	    panelOperations.add(btnAccept, cc.xy(1, 1));

	    // ---- btnCancel ----
	    btnCancel.setText("Cancel");
	    panelOperations.add(btnCancel, cc.xy(3, 1));
	}
	contentPane.add(panelOperations, cc.xywh(2, 5, 2, 1));
	pack();
	setLocationRelativeTo(getOwner());
	// JFormDesigner - End of component initialization
	// //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY
    // //GEN-BEGIN:variables
    private JLabel lblName;
    private JTextField txtName;
    private JLabel lblDescription;
    private JTextField txtDescription;
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
    public void setController(Controller<Group> controller) {
	this.controller = controller;
    }

    @Override
    public void setModel(Group model) {
	this.group = model;
	loadValues();
    }

    @Override
    public void update() {
	LOG.info("Updating view ..");
	loadValues();
    }

    public void setIsCreation(boolean value) {
	this.isCreation = value;
	setModel(new Group());
	btnAccept.setText("Save");
	LOG.debug("IsCreation: " + value);
    }
}
