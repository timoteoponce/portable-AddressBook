package org.uagrm.addressbook.view;

import javax.swing.JFrame;
import javax.swing.JList;

import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.model.swing.ListModel;
import org.uagrm.addressbook.view.event.GenericEventTrigger;

public interface ListView<T> extends GenericEventTrigger, View<T> {
	ListModel<T> getListModel();

	Controller<T> getController();

	void updateList();

	JList getList();

	void removeCurrent();

	void addNew();

	void editCurrent();

	void setMainWindow(JFrame frame);

	JFrame getMainWindow();
}
