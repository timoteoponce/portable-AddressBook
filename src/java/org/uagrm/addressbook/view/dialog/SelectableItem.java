package org.uagrm.addressbook.view.dialog;

import org.uagrm.addressbook.model.Entity;

/**
 * @author Timoteo Ponce
 *
 */
public interface SelectableItem extends Entity {
	String toString();
	boolean equals(SelectableItem item);
}
