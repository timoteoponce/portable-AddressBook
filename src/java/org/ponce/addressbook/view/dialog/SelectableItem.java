package org.ponce.addressbook.view.dialog;

import org.ponce.addressbook.model.Entity;

/**
 * @author Timoteo Ponce
 *
 */
public interface SelectableItem extends Entity {
	String toString();
	boolean equals(SelectableItem item);
}
