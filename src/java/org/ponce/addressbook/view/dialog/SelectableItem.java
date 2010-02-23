package org.ponce.addressbook.view.dialog;

import org.ponce.addressbook.model.Entity;

public interface SelectableItem extends Entity {
	String toString();
	boolean equals(SelectableItem item);
}
