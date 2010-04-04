/*
 * Created by JFormDesigner on Sat Mar 13 12:58:21 VET 2010
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
import org.uagrm.addressbook.model.Protocol;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class ProtocolEditDialog extends AbstractDialogView<Protocol> {
	private static final long serialVersionUID = 5700285433210269078L;

	private final Controller<Protocol> protocolController = ControllerFactory.getInstanceFor(Protocol.class);

	public ProtocolEditDialog(Frame owner) {
		super(owner);
		initComponents();
		init();
	}

	public ProtocolEditDialog(Dialog owner) {
		super(owner);
		initComponents();
		init();
	}

	private void init() {
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
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		separatorProperties = compFactory.createSeparator("Properties");
		lblName = new JLabel();
		txtName = new JTextField();
		lblPort = new JLabel();
		txtPort = new JTextField();
		buttonBar = new JPanel();
		okButton = new JButton();
		cancelButton = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(Borders.DIALOG_BORDER);
			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new FormLayout(
"17dlu, $lcgap, 67dlu, $lcgap, default:grow, $lcgap, 17dlu", "3*(default, $lgap), default"));
				contentPanel.add(separatorProperties, cc.xywh(3, 1, 3, 1));

				// ---- lblName ----
				lblName.setText("Name:");
				lblName.setLabelFor(txtName);
				contentPanel.add(lblName, cc.xy(3, 3));
				contentPanel.add(txtName, cc.xy(5, 3));

				// ---- lblPort ----
				lblPort.setText("Port:");
				lblPort.setLabelFor(txtPort);
				contentPanel.add(lblPort, cc.xy(3, 5));
				contentPanel.add(txtPort, cc.xy(5, 5));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
				buttonBar.setLayout(new FormLayout(
					"$glue, $button, $rgap, $button",
					"pref"));

				//---- okButton ----
				okButton.setText("OK");
				buttonBar.add(okButton, cc.xy(2, 1));

				//---- cancelButton ----
				cancelButton.setText("Cancel");
				buttonBar.add(cancelButton, cc.xy(4, 1));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		setSize(405, 195);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JComponent separatorProperties;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblPort;
	private JTextField txtPort;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	@Override
	Controller<Protocol> getController() {
		return protocolController;
	}

	@Override
	public void loadValues() {
		txtName.setText(getModel().getName());
		if (getModel().getPort() != null) {
			txtPort.setText(getModel().getPort().toString());
		}
	}

	@Override
	public void updateValues() {
		getModel().setName(txtName.getText());
		getModel().setPort(Integer.parseInt(txtPort.getText()));
	}

}
