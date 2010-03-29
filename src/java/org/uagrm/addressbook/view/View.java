package org.uagrm.addressbook.view;

import java.util.Observer;

/**
 * @author Timoteo Ponce
 * 
 * @param <T>
 */
public interface View<T> extends Observer {

	String ACTION_SAVE = "save";
	String ACTION_DELETE = "delete";
	String ACTION_SELECT = "select";
	String ACTION_CLOSE = "close";
	String ACTION_CANCEL = "cancel";
	String ACTION_FILTER = "filter";

	void setModel(T model);

	void show();

	/**
	 * This operation removes the current view from the controller view list and
	 * close/hide the view.
	 */
	void close();

}
