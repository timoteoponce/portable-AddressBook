package org.uagrm.addressbook.controller.listener;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import org.apache.commons.lang.NotImplementedException;
import org.uagrm.addressbook.controller.ControllerFactory;
import org.uagrm.addressbook.controller.CountryController;
import org.uagrm.addressbook.model.Country;
import org.uagrm.addressbook.view.View;
import org.uagrm.addressbook.view.dialog.CountryEditDialog;

public class CountryEditListener implements ActionListener, View<Country> {

	private CountryEditDialog countryDialog;

	private final CountryController countryController = ControllerFactory.getInstance(CountryController.class);

	private Country country;

	public CountryEditListener(Frame frameParent, Dialog dialogParent) {
		if (frameParent != null) {
			countryDialog = new CountryEditDialog(frameParent);
		} else {
			countryDialog = new CountryEditDialog(dialogParent);
		}
		init();
	}

	private void init() {
		countryDialog.getOkButton().addActionListener(this);
		countryDialog.getCancelButton().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String action = e.getActionCommand();

		if (action.equals(ACTION_SAVE)) {
			updateValues();
			countryController.save(country);
			this.close();
		} else if (action.equals(ACTION_CANCEL)) {
			this.close();
		}
	}

	@Override
	public void close() {
		countryDialog.dispose();
	}

	@Override
	public void setModel(Country model) {
		this.country = model;
		loadValues();
	}

	private void loadValues() {
		countryDialog.getTxtName().setText(country.getName());
	}

	private void updateValues() {
		country.setName(countryDialog.getTxtName().getText());
	}

	@Override
	public void update(Observable o, Object arg) {
		throw new NotImplementedException();
	}

	@Override
	public void show() {
		countryDialog.setVisible(true);
	}

}
