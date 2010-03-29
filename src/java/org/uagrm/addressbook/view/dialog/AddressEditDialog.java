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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.EventListenerList;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.AddressController;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.controller.CountryController;
import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.model.Country;
import org.uagrm.addressbook.view.View;
import org.uagrm.addressbook.view.cell.CustomListCellRenderer;
import org.uagrm.addressbook.view.event.GenericEvent;
import org.uagrm.addressbook.view.event.GenericEventListener;
import org.uagrm.addressbook.view.event.GenericEventTrigger;
import org.uagrm.addressbook.view.event.GenericEventType;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif_lite.panel.SimpleInternalFrame;

/**
 * @author Timoteo Ponce
 */
public class AddressEditDialog extends JDialog implements View<Address>, GenericEventTrigger {

	private static final Logger LOG = Logger.getLogger(AddressEditDialog.class);

	private final AddressController addressController = ControllerFactory.getInstance(AddressController.class);
	private final EventListenerList listenerList = new EventListenerList();

	private Address address;

	private boolean isEditing;

	private boolean canSave;

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
		ControllerFactory.getInstance(CountryController.class).addView(this);
		//
		address = new Address();
		isEditing = false;
		//		
		comboCountry.setRenderer(new CustomListCellRenderer());
		updateCountryCombo();
	}

	private void updateCountryCombo() {
		CountryController countryController = ControllerFactory.getInstance(CountryController.class);
		comboCountry.removeAllItems();

		Collection<Country> countryList = countryController.getElements();
		for (Country country : countryList) {
			comboCountry.addItem(country);
		}
		comboCountry.updateUI();
	}

	private void saveAddress() {
		updateValues();
		if (canSave) {
			addressController.save(address, true);
		}
		close();
		fireEvent(GenericEventType.DIALOG_SAVE);
	}

	public boolean isEditing() {
		return isEditing;
	}

	public void setEditing(boolean isEditing) {
		this.isEditing = isEditing;
	}

	public boolean isCanSave() {
		return canSave;
	}

	public void setCanSave(boolean canSave) {
		this.canSave = canSave;
	}

	private void okButtonActionPerformed(ActionEvent e) {
		okAction();
	}

	private void okAction() {
		saveAddress();
	}

	private void cancelButtonActionPerformed(ActionEvent e) {
		this.close();
		fireEvent(GenericEventType.DIALOG_CANCEL);
	}

	private void btnAddCountryActionPerformed(ActionEvent e) {
		addCountry();
	}

	private void addCountry() {
		CountryEditDialog dialog = new CountryEditDialog(this);
		dialog.setModel(new Country());
		dialog.setVisible(true);
	}

	private void thisWindowClosed(WindowEvent e) {
		fireEvent(GenericEventType.DIALOG_CANCEL);
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

		// ======== this ========
		setTitle("Address Edit");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				thisWindowClosed(e);
			}
		});
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		// ======== dialogPane ========
		{
			dialogPane.setBorder(Borders.DIALOG_BORDER);
			dialogPane.setLayout(new BorderLayout());

			// ======== contentPanel ========
			{
				contentPanel.setLayout(new FormLayout("default, $lcgap, default:grow, $lcgap, default", "default, $lgap, 119dlu, $lgap, default"));

				// ======== panelProperties ========
				{
					panelProperties.setContentPaneBorder(null);
					panelProperties.setTitle("Properties");
					Container panelPropertiesContentPane = panelProperties.getContentPane();
					panelPropertiesContentPane.setLayout(new FormLayout("39dlu, $lcgap, 126dlu, $lcgap, 27dlu", "5*(default, $lgap), default"));

					// ---- lblStreet ----
					lblStreet.setText("Street:");
					panelPropertiesContentPane.add(lblStreet, cc.xywh(1, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPropertiesContentPane.add(txtStreet, cc.xy(3, 3));

					// ---- lblNumber ----
					lblNumber.setText("Number:");
					panelPropertiesContentPane.add(lblNumber, cc.xywh(1, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPropertiesContentPane.add(txtNumber, cc.xy(3, 5));

					// ---- lblCity ----
					lblCity.setText("City:");
					panelPropertiesContentPane.add(lblCity, cc.xywh(1, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPropertiesContentPane.add(txtCity, cc.xy(3, 7));

					// ---- lblCountry ----
					lblCountry.setText("Country:");
					panelPropertiesContentPane.add(lblCountry, cc.xywh(1, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPropertiesContentPane.add(comboCountry, cc.xy(3, 9));

					// ---- btnAddCountry ----
					btnAddCountry.setText("+");
					btnAddCountry.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							btnAddCountryActionPerformed(e);
						}
					});
					panelPropertiesContentPane.add(btnAddCountry, cc.xy(5, 9));
				}
				contentPanel.add(panelProperties, cc.xywh(3, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			// ======== panelMainActions ========
			{
				panelMainActions.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
				panelMainActions.setLayout(new FormLayout("$glue, $button, $rgap, $button", "pref"));

				// ---- okButton ----
				okButton.setText("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okButtonActionPerformed(e);
					}
				});
				panelMainActions.add(okButton, cc.xy(2, 1));

				// ---- cancelButton ----
				cancelButton.setText("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelButtonActionPerformed(e);
					}
				});
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
		ControllerFactory.getInstance(CountryController.class).removeView(this);
		this.dispose();
	}


	@Override
	public void setModel(Address model) {
		this.address = model;
		loadValues();
	}

	private void loadValues() {
		txtCity.setText(address.getCity());
		txtNumber.setText(address.getNumber());
		txtStreet.setText(address.getStreet());

		if (address.getCountry() != null) {
			comboCountry.setSelectedItem(address.getCountry());
		}
	}

	private void updateValues() {
		address.setCity(txtCity.getText());
		address.setNumber(txtCity.getText());
		address.setStreet(txtStreet.getText());
		address.setCountry((Country) comboCountry.getSelectedItem());
	}

	@Override
	public void update(Observable source, Object model) {
		if (source.equals(addressController)) {
			if (model == null) {// was deleted
				this.close();
			} else if (this.address.equals((Address) model)) {
				LOG.info("Updating model : " + this.getClass().getName() + ", values: " + model.toString());
				setModel((Address) model);
			}
		} else if (source.getClass().equals(CountryController.class)) {
			updateCountryCombo();
		}
	}

	@Override
	public Address getModel() {
		return address;
	}

	@Override
	public void addEventListener(GenericEventListener listener) {
		listenerList.add(GenericEventListener.class, listener);
	}

	@Override
	public void fireEvent(GenericEventType type) {
		GenericEvent event = new GenericEvent(this, type);
		GenericEventListener[] listeners = listenerList.getListeners(GenericEventListener.class);

		for (GenericEventListener item : listeners) {
			item.eventFired(event);
		}
	}

}
