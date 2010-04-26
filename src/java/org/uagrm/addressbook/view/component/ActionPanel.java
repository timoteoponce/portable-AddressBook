/*
 * Created by JFormDesigner on Sat Mar 27 13:23:25 BOT 2010
 */

package org.uagrm.addressbook.view.component;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

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

	private final ActionButtons actionButtons = new ActionButtons();

	public ActionPanel() {
		initComponents();
		actionPanel.add(actionButtons, BorderLayout.CENTER);
	}

	public JLabel getLabel() {
		return label;
	}

	public JPanel getActionPanel() {
		return actionPanel;
	}

	public void setActionPanel(JPanel actionPanel) {
		this.actionPanel = actionPanel;
	}

	public void setTitle(String title) {
		this.label.setText(title);
	}

	public ActionButtons getActionButtons() {
		return actionButtons;
	}

	public void addActionListener(ActionListener listener) {
		actionButtons.addActionListener(listener);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		label = compFactory.createLabel("text");
		separator = new JSeparator();
		actionPanel = new JPanel();
		CellConstraints cc = new CellConstraints();

		// ======== this ========
		setLayout(new FormLayout("42dlu, $lcgap, default:grow, $lcgap, default", "default"));
		add(label, cc.xy(1, 1));
		add(separator, cc.xy(3, 1));

		// ======== actionPanel ========
		{
			actionPanel.setLayout(new BorderLayout());
		}
		add(actionPanel, cc.xywh(5, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JLabel label;
	private JSeparator separator;
	private JPanel actionPanel;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
