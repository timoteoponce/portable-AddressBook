/*
 * Created by JFormDesigner on Sat Mar 13 12:58:36 VET 2010
 */

package org.uagrm.addressbook.view.dialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.model.Protocol;
import org.uagrm.addressbook.model.VirtualAddress;
import org.uagrm.addressbook.model.dto.EntityStatus;
import org.uagrm.addressbook.model.dto.StatusType;
import org.uagrm.addressbook.view.cell.CustomListCellRenderer;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class VirtualAddressEditDialog extends AbstractDialogView<VirtualAddress> {

	private static final long serialVersionUID = -4735109565115532865L;

	private static final Logger LOG = Logger.getLogger(VirtualAddressEditDialog.class);

	private final Controller<VirtualAddress> vAddressController = ControllerFactory.getInstanceFor(VirtualAddress.class);

	private final Controller<Protocol> protocolController = ControllerFactory.getInstanceFor(Protocol.class);

	public VirtualAddressEditDialog(Frame owner) {
		super(owner);
		initComponents();
		init();
	}

	public VirtualAddressEditDialog(Dialog owner) {
		super(owner);
		initComponents();
		init();
	}

	private void init() {
		vAddressController.addView(this);
		protocolController.addView(this);
		//
		comboProtocol.setRenderer(new CustomListCellRenderer());
		updateProtocolCombo();
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
		btnAddProtocol.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addProtocol();
			}

		});
	}

	private void updateProtocolCombo() {
		comboProtocol.removeAllItems();

		for (Protocol protocol : protocolController.getElements()) {
			comboProtocol.addItem(protocol);
		}
		comboProtocol.updateUI();
	}

	private void addProtocol() {
		ProtocolEditDialog dialog = new ProtocolEditDialog(this);
		dialog.setModel(new Protocol());
		dialog.setSaveable(true);
		dialog.setVisible(true);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		separator = compFactory.createSeparator("Properties");
		lblIdentifier = new JLabel();
		txtIdentifier = new JTextField();
		lblWebsite = new JLabel();
		txtWebsite = new JTextField();
		lblProtocol = new JLabel();
		comboProtocol = new JComboBox();
		btnAddProtocol = new JButton();
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
					"19dlu, $lcgap, 66dlu, $lcgap, default:grow, $lcgap, default, $lcgap, 16dlu",
					"3*(default, $lgap), default"));
				contentPanel.add(separator, cc.xywh(3, 1, 3, 1));

				//---- lblIdentifier ----
				lblIdentifier.setText("Identifier:");
				lblIdentifier.setLabelFor(txtIdentifier);
				contentPanel.add(lblIdentifier, cc.xy(3, 3));
				contentPanel.add(txtIdentifier, cc.xy(5, 3));

				//---- lblWebsite ----
				lblWebsite.setText("Website:");
				lblWebsite.setLabelFor(txtWebsite);
				contentPanel.add(lblWebsite, cc.xy(3, 5));
				contentPanel.add(txtWebsite, cc.xy(5, 5));

				//---- lblProtocol ----
				lblProtocol.setText("Protocol:");
				lblProtocol.setLabelFor(comboProtocol);
				contentPanel.add(lblProtocol, cc.xy(3, 7));
				contentPanel.add(comboProtocol, cc.xy(5, 7));

				//---- btnAddProtocol ----
				btnAddProtocol.setText("+");
				contentPanel.add(btnAddProtocol, cc.xy(7, 7));
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
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JComponent separator;
	private JLabel lblIdentifier;
	private JTextField txtIdentifier;
	private JLabel lblWebsite;
	private JTextField txtWebsite;
	private JLabel lblProtocol;
	private JComboBox comboProtocol;
	private JButton btnAddProtocol;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	@Override
	Controller<VirtualAddress> getController() {
		return vAddressController;
	}

	@Override
	public void loadValues() {
		txtIdentifier.setText(getModel().getIdentifier());
		txtWebsite.setText(getModel().getWebsite());

		if (getModel().getProtocol() != null) {
			comboProtocol.setSelectedItem(getModel().getProtocol());
		}
	}

	@Override
	public void updateValues() {
		getModel().setIdentifier(txtIdentifier.getText());
		getModel().setWebsite(txtWebsite.getText());
		getModel().setProtocol((Protocol) comboProtocol.getSelectedItem());
	}

	@Override
	public void update(Observable source, Object model) {
		if (source.equals(vAddressController)) {
			final EntityStatus<VirtualAddress> entityStatus = (EntityStatus<VirtualAddress>) model;

			if (entityStatus.getEntity().equals(getModel())) {
				if (entityStatus.getStatus() == StatusType.DELETED) {
					close();
				} else {
					LOG.info("Updating model : " + this.getClass().getName() + ", values: " + model.toString());
					setModel((VirtualAddress) model);
				}
			}
		} else if (source.equals(protocolController)) {
			updateProtocolCombo();
		}
	}

	@Override
	public void close() {
		vAddressController.removeView(this);
		protocolController.removeView(this);
		this.dispose();
	}

}
