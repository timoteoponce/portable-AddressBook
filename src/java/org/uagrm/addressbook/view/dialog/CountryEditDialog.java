/*
 * Created by JFormDesigner on Sat Mar 13 12:57:36 VET 2010
 */

package org.uagrm.addressbook.view.dialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.uagrm.addressbook.view.View;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class CountryEditDialog extends JDialog {

	public CountryEditDialog(Frame owner) {
		super(owner);
		initComponents();
		setEventCommands();
	}

	public CountryEditDialog(Dialog owner) {
		super(owner);
		initComponents();
		setEventCommands();
	}

	private void setEventCommands() {
		this.okButton.setActionCommand(View.ACTION_SAVE);
		this.cancelButton.setActionCommand(View.ACTION_CANCEL);
	}

	public JPanel getDialogPane() {
		return dialogPane;
	}

	public void setDialogPane(JPanel dialogPane) {
		this.dialogPane = dialogPane;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public JComponent getSepProperties() {
		return sepProperties;
	}

	public void setSepProperties(JComponent sepProperties) {
		this.sepProperties = sepProperties;
	}

	public JPanel getPanelProperties() {
		return panelProperties;
	}

	public void setPanelProperties(JPanel panelProperties) {
		this.panelProperties = panelProperties;
	}

	public JLabel getLblName() {
		return lblName;
	}

	public void setLblName(JLabel lblName) {
		this.lblName = lblName;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JPanel getButtonBar() {
		return buttonBar;
	}

	public void setButtonBar(JPanel buttonBar) {
		this.buttonBar = buttonBar;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		sepProperties = compFactory.createSeparator("Country properties");
		panelProperties = new JPanel();
		lblName = new JLabel();
		txtName = new JTextField();
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
				contentPanel.setLayout(new FormLayout("17dlu, $lcgap, 106dlu:grow, $lcgap, 17dlu", "2*(default, $lgap), default"));
				contentPanel.add(sepProperties, cc.xy(3, 1));

				// ======== panelProperties ========
				{
					panelProperties.setLayout(new FormLayout("25dlu, $lcgap, 8dlu, $lcgap, default:grow", "default"));

					// ---- lblName ----
					lblName.setText("Name:");
					lblName.setLabelFor(txtName);
					panelProperties.add(lblName, cc.xy(1, 1));
					panelProperties.add(txtName, cc.xy(5, 1));
				}
				contentPanel.add(panelProperties, cc.xy(3, 5));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			// ======== buttonBar ========
			{
				buttonBar.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
				buttonBar.setLayout(new FormLayout("$glue, $button, $rgap, $button", "pref"));

				// ---- okButton ----
				okButton.setText("Save");
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
	private JComponent sepProperties;
	private JPanel panelProperties;
	private JLabel lblName;
	private JTextField txtName;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables

}
