package org.uagrm.addressbook.view;

import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import org.uagrm.addressbook.model.swing.ListModel;
import org.uagrm.addressbook.view.event.GenericEvent;
import org.uagrm.addressbook.view.event.GenericEventListener;
import org.uagrm.addressbook.view.event.GenericEventType;

public abstract class AbstractListView<T> extends JPanel implements ListView<T> {

	private static final long serialVersionUID = 6455270438653422762L;

	private final ListModel<T> listModel = new ListModel<T>();
	
	private final EventListenerList listenerList = new EventListenerList();

	private JFrame mainWindow;

	private T model;

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

	@Override
	public void close() {
		getController().removeView(this);
		this.setVisible(false);
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
		if (source.equals(getController())) {
			updateList();
		}
	}

	@Override
	public ListModel<T> getListModel() {
		return listModel;
	}

	@Override
	public void removeCurrent() {
		final int index = getList().getSelectedIndex();
		if (index > 0) {
			getController().delete((T) getList().getSelectedValue(), true);
		}
	}

	@Override
	public void setMainWindow(JFrame frame) {
		this.mainWindow = frame;
	}

	@Override
	public JFrame getMainWindow() {
		return mainWindow;
	}

}
