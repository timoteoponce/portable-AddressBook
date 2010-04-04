/*
 * Created by JFormDesigner on Sat Mar 13 12:57:54 VET 2010
 */

package org.uagrm.addressbook.view.dialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.model.Phone;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class PhoneEditDialog extends AbstractDialogView<Phone> {

	private static final long serialVersionUID = -35206773748747801L;

	private final Controller<Phone> phoneController = ControllerFactory.getInstanceFor(Phone.class);

	public PhoneEditDialog(Frame owner) {
		super(owner);
		initComponents();
		init();
	}

	public PhoneEditDialog(Dialog owner) {
		super(owner);
		initComponents();
		init();
	}

	private void init() {
		phoneController.addView(this);
		// listeners
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveModel();
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		separator = compFactory.createSeparator("text");
		lblHousePhone = new JLabel();
		txtHousePhone = new JTextField();
		lblMobilePhone = new JLabel();
		txtMobilePhone = new JTextField();
		lblWorkPhone = new JLabel();
		txtWorkPhone = new JTextField();
		actionButtonBar = new JPanel();
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
				contentPanel.setLayout(new FormLayout("20dlu, $lcgap, 65dlu, $lcgap, default:grow, $lcgap, 17dlu", "4*(default, $lgap), default"));
				contentPanel.add(separator, cc.xywh(3, 3, 3, 1));

				// ---- lblHousePhone ----
				lblHousePhone.setText("House phone:");
				contentPanel.add(lblHousePhone, cc.xy(3, 5));
				contentPanel.add(txtHousePhone, cc.xy(5, 5));

				// ---- lblMobilePhone ----
				lblMobilePhone.setText("Mobile phone:");
				contentPanel.add(lblMobilePhone, cc.xy(3, 7));
				contentPanel.add(txtMobilePhone, cc.xy(5, 7));

				// ---- lblWorkPhone ----
				lblWorkPhone.setText("Work phone:");
				contentPanel.add(lblWorkPhone, cc.xy(3, 9));
				contentPanel.add(txtWorkPhone, cc.xy(5, 9));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			// ======== actionButtonBar ========
			{
				actionButtonBar.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
				actionButtonBar.setLayout(new FormLayout("$glue, $button, $rgap, $button", "pref"));

				// ---- okButton ----
				okButton.setText("OK");
				actionButtonBar.add(okButton, cc.xy(2, 1));

				// ---- cancelButton ----
				cancelButton.setText("Cancel");
				actionButtonBar.add(cancelButton, cc.xy(4, 1));
			}
			dialogPane.add(actionButtonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		setSize(405, 235);
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JComponent separator;
	private JLabel lblHousePhone;
	private JTextField txtHousePhone;
	private JLabel lblMobilePhone;
	private JTextField txtMobilePhone;
	private JLabel lblWorkPhone;
	private JTextField txtWorkPhone;
	private JPanel actionButtonBar;
	private JButton okButton;
	private JButton cancelButton;

	// JFormDesigner - End of variables declaration //GEN-END:variables
	@Override
	public void close() {
		phoneController.removeView(this);
		this.dispose();
	}

	@Override
	public void loadValues() {
		txtHousePhone.setText(getModel().getHousePhone());
		txtMobilePhone.setText(getModel().getMobilePhone());
		txtWorkPhone.setText(getModel().getWorkPhone());
	}

	@Override
	public void updateValues() {
		getModel().setHousePhone(txtHousePhone.getText());
		getModel().setMobilePhone(txtMobilePhone.getText());
		getModel().setWorkPhone(txtWorkPhone.getText());
	}

	@Override
	Controller<Phone> getController() {
		return phoneController;
	}

}
