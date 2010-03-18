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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.controller.AddressController;
import org.uagrm.addressbook.controller.ContactController;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.controller.CountryController;
import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Country;
import org.uagrm.addressbook.model.swing.ListModel;
import org.uagrm.addressbook.view.View;
import org.uagrm.addressbook.view.cell.CustomListCellRenderer;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif_lite.panel.SimpleInternalFrame;

/**
 * @author Timoteo Ponce
 */
public class AddressEditDialog extends JDialog implements View<Contact> {

	private static final Logger LOG = Logger.getLogger(AddressEditDialog.class);

	private final AddressController addressController = ControllerFactory
			.getInstance(AddressController.class);
	private final ContactController contactController = ControllerFactory
			.getInstance(ContactController.class);

	private Address currentAddress ;

	private Contact contact;
	
	private boolean isEditing;

	private final ListModel<Address> addressListModel = new ListModel<Address>();

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
		contactController.addView(this);
		//
		currentAddress = new Address();
		isEditing = false;
		//
		listAddresses.setModel(addressListModel);
		comboCountry.setRenderer(new CustomListCellRenderer());
		updateCountryCombo();
	}

	private void updateCountryCombo() {
		CountryController countryController = ControllerFactory.getInstance(CountryController .class);
		comboCountry.removeAllItems();
		
		Collection<Country> countryList = countryController.getElements();
		for (Country country : countryList) {
			comboCountry.addItem(country);
		}
		comboCountry.updateUI();
	}

	private void listAddressesMouseClicked(MouseEvent e) {
		e.consume();
		selectAddress();
	}

	private void btnResetActionPerformed(ActionEvent e) {		
		reset();
	}

	private void reset() {		
		currentAddress = new Address();
		isEditing = false;
		loadAddressValues();
	}

	private void btnSaveActionPerformed(ActionEvent e) {
		saveAddress();
	}

	private void saveAddress() {		
		updateAddressValues();
		if( !isEditing){			
			addressListModel.addElement(currentAddress);
		}
		reset();
	}

	private void btnAddActionPerformed(ActionEvent e) {
		reset();
		txtStreet.requestFocus();
	}

	private void okButtonActionPerformed(ActionEvent e) {
		okAction();
	}

	private void okAction() {
		//we don't store anything here, we just add all addresses to the contact
		updateValues();	
		close();
	}

	private void cancelButtonActionPerformed(ActionEvent e) {
		this.close();
	}

	private void btnRemoveActionPerformed(ActionEvent e) {
		removeSelectedAddress();
	}

	private void removeSelectedAddress() {
		final int index = listAddresses.getSelectedIndex();
		if(index > -1){
			addressListModel.removeElement(index);
		}
		listAddresses.updateUI();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
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
		panelActions1 = new JPanel();
		btnSave = new JButton();
		btnReset = new JButton();
		panelList = new SimpleInternalFrame();
		panelOperations = new JPanel();
		sepOperations = compFactory.createSeparator("Operations");
		panelActions2 = new JPanel();
		btnAdd = new JButton();
		btnRemove = new JButton();
		scrollPane1 = new JScrollPane();
		listAddresses = new JList();
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
					"default, $lgap, 119dlu, $lgap, default:grow, $lgap, default"));

				//======== panelProperties ========
				{
					panelProperties.setContentPaneBorder(null);
					panelProperties.setTitle("Properties");
					Container panelPropertiesContentPane = panelProperties.getContentPane();
					panelPropertiesContentPane.setLayout(new FormLayout(
						"39dlu, $lcgap, 114dlu, $lcgap, default",
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

					//======== panelActions1 ========
					{
						panelActions1.setLayout(new FormLayout(
							"default, $lcgap, right:47dlu, $lcgap, center:48dlu",
							"default"));

						//---- btnSave ----
						btnSave.setText("Save");
						btnSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								btnSaveActionPerformed(e);
							}
						});
						panelActions1.add(btnSave, cc.xywh(3, 1, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));

						//---- btnReset ----
						btnReset.setText("Reset");
						btnReset.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								btnResetActionPerformed(e);
							}
						});
						panelActions1.add(btnReset, cc.xywh(4, 1, 2, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
					}
					panelPropertiesContentPane.add(panelActions1, cc.xy(3, 11));
				}
				contentPanel.add(panelProperties, cc.xywh(3, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

				//======== panelList ========
				{
					panelList.setTitle("Address List");
					Container panelListContentPane = panelList.getContentPane();
					panelListContentPane.setLayout(new FormLayout(
						"default, $lcgap, default:grow, $lcgap, default",
						"2*(default, $lgap), default:grow, $lgap, default"));

					//======== panelOperations ========
					{
						panelOperations.setLayout(new FormLayout(
							"116dlu:grow, $lcgap, default",
							"default"));
						panelOperations.add(sepOperations, cc.xy(1, 1));

						//======== panelActions2 ========
						{
							panelActions2.setLayout(new FormLayout(
								"default, $lcgap, default",
								"default"));

							//---- btnAdd ----
							btnAdd.setText("+");
							btnAdd.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									btnAddActionPerformed(e);
								}
							});
							panelActions2.add(btnAdd, cc.xy(1, 1));

							//---- btnRemove ----
							btnRemove.setText("-");
							btnRemove.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									btnRemoveActionPerformed(e);
								}
							});
							panelActions2.add(btnRemove, cc.xy(3, 1));
						}
						panelOperations.add(panelActions2, cc.xy(3, 1));
					}
					panelListContentPane.add(panelOperations, cc.xy(3, 3));

					//======== scrollPane1 ========
					{

						//---- listAddresses ----
						listAddresses.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								listAddressesMouseClicked(e);
							}
						});
						scrollPane1.setViewportView(listAddresses);
					}
					panelListContentPane.add(scrollPane1, cc.xy(3, 5));
				}
				contentPanel.add(panelList, cc.xywh(3, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
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
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okButtonActionPerformed(e);
					}
				});
				panelMainActions.add(okButton, cc.xy(2, 1));

				//---- cancelButton ----
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
	private JPanel panelActions1;
	private JButton btnSave;
	private JButton btnReset;
	private SimpleInternalFrame panelList;
	private JPanel panelOperations;
	private JComponent sepOperations;
	private JPanel panelActions2;
	private JButton btnAdd;
	private JButton btnRemove;
	private JScrollPane scrollPane1;
	private JList listAddresses;
	private JPanel panelMainActions;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
	@Override
	public void close() {
		addressController.removeView(this);
		contactController.removeView(this);
		this.dispose();
	}	

	private void selectAddress() {
		currentAddress = (Address) listAddresses.getSelectedValue();
		isEditing = (currentAddress != null); 
		loadAddressValues();
	}

	private void loadAddressValues() {
		if (currentAddress != null) {			
			txtStreet.setText(currentAddress.getStreet());
			txtCity.setText(currentAddress.getCity());
			txtNumber.setText(currentAddress.getNumber());
		}		
	}

	private void updateAddressValues() {
		currentAddress.setCity(txtCity.getText());
		currentAddress.setNumber(txtCity.getText());
		currentAddress.setStreet(txtStreet.getText());
		currentAddress.setCountry((Country) comboCountry.getSelectedItem());
	}

	@Override
	public void setModel(Contact model) {
		this.contact = model;
		loadValues();
	}

	private void loadValues() {
		if(contact.getAddresses().isEmpty()){
			contact = contactController.preloadEntity(contact, Address.class);
		}
		updateAddressList();
	}

	private void updateAddressList() {
		addressListModel.clear();
		addressListModel.addAllElements(contact.getAddresses());
	}
	
	private void updateValues() {
		contact.getAddresses().clear();
		contact.getAddresses().addAll(addressListModel.getElements());
	}

	@Override
	public void update(Observable source, Object model) {
		if (source.equals(contactController)) {
			if (model == null) {// was deleted
				this.close();
			} else if (this.contact.equals((Contact) model)) {
				LOG.info("Updating model : " + this.getClass().getName()
						+ ", values: " + model.toString());
				setModel((Contact) model);
			}
		} else if (source.equals(addressController)) {
			// ?
		}
	}
}
