/*
 * Created by JFormDesigner on Sun Apr 25 20:04:56 BOT 2010
 */

package org.uagrm.addressbook.view.component;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.uagrm.addressbook.controller.actions.ActionType;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class ActionButtons extends JPanel {
	public ActionButtons() {
		initComponents();
		getAddButton().setActionCommand(ActionType.CREATE.toString());
		getEditButton().setActionCommand(ActionType.UPDATE.toString());
		getRemoveButton().setActionCommand(ActionType.DELETE.toString());
	}

	private JButton getAddButton() {
		return addButton;
	}

	private JButton getEditButton() {
		return editButton;
	}

	private JButton getRemoveButton() {
		return removeButton;
	}

	public boolean isEditionEnabled() {
		return getEditButton().isEnabled();
	}

	public void setEditionEnabled(boolean value) {
		getEditButton().setEnabled(value);
	}

	public void addActionListener(ActionListener listener) {
		getAddButton().addActionListener(listener);
		getEditButton().addActionListener(listener);
		getRemoveButton().addActionListener(listener);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		addButton = new JButton();
		editButton = new JButton();
		removeButton = new JButton();
		CellConstraints cc = new CellConstraints();

		// ======== this ========
		setLayout(new FormLayout("2*(default, $lcgap), default", "default"));

		// ---- addButton ----
		addButton.setText("+");
		add(addButton, cc.xy(1, 1));

		// ---- editButton ----
		editButton.setText("*");
		add(editButton, cc.xy(3, 1));

		// ---- removeButton ----
		removeButton.setText("-");
		add(removeButton, cc.xy(5, 1));
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JButton addButton;
	private JButton editButton;
	private JButton removeButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
