package org.uagrm.addressbook.view.dialog;

import org.uagrm.addressbook.view.View;
import org.uagrm.addressbook.view.event.GenericEventTrigger;

public interface DialogView<T> extends GenericEventTrigger, View<T> {
	boolean isEditable();

	void setEditable(boolean isEditing);

	void setSaveable(boolean canSave);

	boolean isSaveable();	
	
    void loadValues();
    
    void updateValues();
	 
}
