package org.uagrm.addressbook.model.swing;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class ListModel<T> extends AbstractListModel {

	private final List<T> elementList = new ArrayList<T>();

	@Override
	public Object getElementAt(int index) {
		return elementList.get(index);
	}	
	
	public T getElement(int index) {
		return elementList.get(index);
	}

	@Override
	public int getSize() {
		return elementList.size();
	}

	public void addElement(T element) {
		elementList.add(element);
		final int index = elementList.size();
		fireIntervalAdded(this, index, index);		
	}

	public void removeElement(T element) {
		final int index = elementList.indexOf(element);
		if (index < elementList.size()) {
			elementList.remove(element);
			fireIntervalRemoved(this, index, index);
		}
	}

	public void removeElement(int index) {
		if (index < elementList.size()) {
			elementList.remove(index);
			fireIntervalRemoved(this, index, index);
		}
	}

	public void clear() {
		elementList.clear();
		fireIntervalRemoved(this, 0, 0);
	}

}
