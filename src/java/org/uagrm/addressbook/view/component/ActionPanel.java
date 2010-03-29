/*
 * Created by JFormDesigner on Sat Mar 27 13:23:25 BOT 2010
 */

package org.uagrm.addressbook.view.component;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class ActionPanel extends JPanel {

	public static final String ACTION_ADD = "add";
	public static final String ACTION_EDIT = "edit";
	public static final String ACTION_REMOVE = "remove";

	public ActionPanel() {
		initComponents();
		addButton.setActionCommand(ACTION_ADD);
		editButton.setActionCommand(ACTION_EDIT);
		removeButton.setActionCommand(ACTION_REMOVE);
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JPanel getActionPanel() {
		return actionPanel;
	}

	public void setActionPanel(JPanel actionPanel) {
		this.actionPanel = actionPanel;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public void setAddButton(JButton addButton) {
		this.addButton = addButton;
	}

	public JButton getEditButton() {
		return editButton;
	}

	public void setEditButton(JButton editButton) {
		this.editButton = editButton;
	}

	public JButton getRemoveButton() {
		return removeButton;
	}

	public void setRemoveButton(JButton removeButton) {
		this.removeButton = removeButton;
	}

	public void setTitle(String title) {
		this.label.setText(title);
	}

	public void addActionListener(ActionListener listener) {
		if (this.addButton.isEnabled()) {
			this.addButton.addActionListener(listener);
		}
		if (this.editButton.isEnabled()) {
			this.editButton.addActionListener(listener);
		}
		if (this.removeButton.isEnabled()) {
			this.removeButton.addActionListener(listener);
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		label = compFactory.createLabel("text");
		separator = new JSeparator();
		actionPanel = new JPanel();
		addButton = new JButton();
		editButton = new JButton();
		removeButton = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"42dlu, $lcgap, default:grow, $lcgap, default",
			"default"));
		add(label, cc.xy(1, 1));
		add(separator, cc.xy(3, 1));

		//======== actionPanel ========
		{
			actionPanel.setLayout(new FormLayout(
				"2*(default, $lcgap), default",
				"default"));

			//---- addButton ----
			addButton.setText("+");
			actionPanel.add(addButton, cc.xy(1, 1));

			//---- editButton ----
			editButton.setText("*");
			actionPanel.add(editButton, cc.xy(3, 1));

			//---- removeButton ----
			removeButton.setText("-");
			actionPanel.add(removeButton, cc.xy(5, 1));
		}
		add(actionPanel, cc.xy(5, 1));
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JLabel label;
	private JSeparator separator;
	private JPanel actionPanel;
	private JButton addButton;
	private JButton editButton;
	private JButton removeButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
