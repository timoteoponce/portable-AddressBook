package org.uagrm.addressbook.view.dialog;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;

import javax.swing.JDialog;
import javax.swing.event.EventListenerList;

import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.model.Phone;
import org.uagrm.addressbook.view.event.GenericEvent;
import org.uagrm.addressbook.view.event.GenericEventListener;
import org.uagrm.addressbook.view.event.GenericEventType;

public abstract class AbstractDialogView<T> extends JDialog implements DialogView<T>{
	
	private final EventListenerList listenerList = new EventListenerList();
	
	private boolean isEditable;

	private boolean isSaveable;
	
	private T model;
	
	public AbstractDialogView(Frame owner) {
		super(owner);
		addListeners();
	}
	
	public AbstractDialogView(Dialog owner) {
		super(owner);
		addListeners();
	}
	
	private void addListeners(){
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fireEvent(GenericEventType.DIALOG_CANCEL);
			}
		});
	}
	
	@Override	
	public void addEventListener(GenericEventListener listener){
		listenerList.add(GenericEventListener.class, listener);
	}

	@Override
	public void fireEvent(GenericEventType type){
		GenericEvent event = new GenericEvent(this, type);
		GenericEventListener[] listeners = listenerList.getListeners(GenericEventListener.class);

		for (GenericEventListener item : listeners) {
			item.eventFired(event);
		}
	}
	
	@Override
	public boolean isEditable() {		
		return isEditable;
	}
	
	@Override
	public boolean isSaveable() {		
		return isSaveable;
	}
	
	@Override
	public void setSaveable(boolean canSave) {
		this.isSaveable = canSave;		
	}
	
	@Override
	public void setEditable(boolean isEditing) {
		this.isEditable = isEditing;		
	}
	
	@Override
	public T getModel() {		
		return model;
	}
	
	@Override
	public void setModel(T model) {
		this.model = model;
	}
	
	@Override
	public void update(Observable source, Object model) {
		if(source.equals(getController())){
			if (model != null && getModel().equals(model)) {				
				setModel((T) model);
			}
		}
	}
	
	protected void saveModel(){
		updateValues();
		if (isSaveable()) {
			getController().save(getModel(), true);
		}
		fireEvent(GenericEventType.DIALOG_SAVE);
		close();		
	}
	
	
	abstract Controller<T> getController();
	
	
}
