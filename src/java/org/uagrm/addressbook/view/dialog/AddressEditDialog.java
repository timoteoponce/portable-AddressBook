/*
 * Created by JFormDesigner on Sat Mar 13 12:57:04 VET 2010
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.model.Country;
import org.uagrm.addressbook.model.dto.EntityStatus;
import org.uagrm.addressbook.model.dto.StatusType;
import org.uagrm.addressbook.view.cell.CustomListCellRenderer;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif_lite.panel.SimpleInternalFrame;

/**
 * @author Timoteo Ponce
 */
public class AddressEditDialog extends AbstractDialogView<Address>{

	private static final Logger LOG = Logger.getLogger(AddressEditDialog.class);

	private final Controller<Address> addressController = ControllerFactory.getInstanceFor(Address.class);
	
	private final Controller<Country> countryController = ControllerFactory.getInstanceFor(Country.class);

	public AddressEditDialog(Frame owner) {
		super(owner);
		initComponents();
		init();
	}

	public AddressEditDialog(Dialog owner) {
		super(owner);
		initComponents();
		init();
	}

	private void init() {
		addressController.addView(this);
		countryController.addView(this);
		//		
		comboCountry.setRenderer(new CustomListCellRenderer());
		updateCountryCombo();
		//listeners
		btnAddCountry.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				addCountry();				
			}});
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				saveModel();		
			}});
		cancelButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				close();				
			}});
	}

	private void updateCountryCombo() {
		comboCountry.removeAllItems();

		for (Country country : countryController.getElements()) {
			comboCountry.addItem(country);
		}
		comboCountry.updateUI();
	}
	
	
	private void addCountry() {
		CountryEditDialog dialog = new CountryEditDialog(this);
		dialog.setModel(new Country());
		dialog.setSaveable(true);
		dialog.setVisible(true);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		panelProperties = new SimpleInternalFrame();
		lblStreet = new JLabel();
		txtStreet = new JTextField();
		lblNumber = new JLabel();
		txtNumber = new JTextField();
		lblCity = new JLabel();
		txtCity = new JTextField();
		lblCountry = new JLabel();
		comboCountry = new JComboBox();
		btnAddCountry = new JButton();
		panelMainActions = new JPanel();
		okButton = new JButton();
		cancelButton = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Address Edit");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(Borders.DIALOG_BORDER);
			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new FormLayout(
					"default, $lcgap, default:grow, $lcgap, default",
					"default, $lgap, 119dlu, $lgap, default"));

				//======== panelProperties ========
				{
					panelProperties.setContentPaneBorder(null);
					panelProperties.setTitle("Properties");
					Container panelPropertiesContentPane = panelProperties.getContentPane();
					panelPropertiesContentPane.setLayout(new FormLayout(
						"39dlu, $lcgap, 126dlu, $lcgap, 27dlu",
						"5*(default, $lgap), default"));

					//---- lblStreet ----
					lblStreet.setText("Street:");
					panelPropertiesContentPane.add(lblStreet, cc.xywh(1, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPropertiesContentPane.add(txtStreet, cc.xy(3, 3));

					//---- lblNumber ----
					lblNumber.setText("Number:");
					panelPropertiesContentPane.add(lblNumber, cc.xywh(1, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPropertiesContentPane.add(txtNumber, cc.xy(3, 5));

					//---- lblCity ----
					lblCity.setText("City:");
					panelPropertiesContentPane.add(lblCity, cc.xywh(1, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPropertiesContentPane.add(txtCity, cc.xy(3, 7));

					//---- lblCountry ----
					lblCountry.setText("Country:");
					panelPropertiesContentPane.add(lblCountry, cc.xywh(1, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPropertiesContentPane.add(comboCountry, cc.xy(3, 9));

					//---- btnAddCountry ----
					btnAddCountry.setText("+");
					panelPropertiesContentPane.add(btnAddCountry, cc.xy(5, 9));
				}
				contentPanel.add(panelProperties, cc.xywh(3, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== panelMainActions ========
			{
				panelMainActions.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
				panelMainActions.setLayout(new FormLayout(
					"$glue, $button, $rgap, $button",
					"pref"));

				//---- okButton ----
				okButton.setText("OK");
				panelMainActions.add(okButton, cc.xy(2, 1));

				//---- cancelButton ----
				cancelButton.setText("Cancel");
				panelMainActions.add(cancelButton, cc.xy(4, 1));
			}
			dialogPane.add(panelMainActions, BorderLayout.SOUTH);
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
	private SimpleInternalFrame panelProperties;
	private JLabel lblStreet;
	private JTextField txtStreet;
	private JLabel lblNumber;
	private JTextField txtNumber;
	private JLabel lblCity;
	private JTextField txtCity;
	private JLabel lblCountry;
	private JComboBox comboCountry;
	private JButton btnAddCountry;
	private JPanel panelMainActions;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
	@Override
	public void close() {
		addressController.removeView(this);
		countryController.removeView(this);
		this.dispose();
	}

	@Override
	public void loadValues() {
		txtCity.setText(getModel().getCity());
		txtNumber.setText(getModel().getNumber());
		txtStreet.setText(getModel().getStreet());

		if (getModel().getCountry() != null) {
			comboCountry.setSelectedItem(getModel().getCountry());
		}
	}

	@Override
	public void updateValues() {
		getModel().setCity(txtCity.getText());
		getModel().setNumber(txtCity.getText());
		getModel().setStreet(txtStreet.getText());
		getModel().setCountry((Country) comboCountry.getSelectedItem());
	}

	@Override
	public void update(Observable source, Object model) {
		if (source.equals(addressController)) {
			final EntityStatus<Address> entityStatus = (EntityStatus<Address>) model;

			if (entityStatus.getEntity().equals(getModel())) {
				if (entityStatus.getStatus() == StatusType.DELETED) {
					close();
				} else {
					LOG.info("Updating model : " + this.getClass().getName() + ", values: " + entityStatus);
					setModel(entityStatus.getEntity());
				}
			}
		} else if (source.equals(countryController)) {
			updateCountryCombo();
		}
	}

	@Override
	Controller<Address> getController() {
		return addressController;
	}

}
