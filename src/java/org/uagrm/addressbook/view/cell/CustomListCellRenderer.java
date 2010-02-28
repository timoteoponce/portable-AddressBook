package org.uagrm.addressbook.view.cell;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Group;

/**
 * @author Timoteo Ponce
 * 
 */
public class CustomListCellRenderer extends JLabel implements ListCellRenderer {

    public CustomListCellRenderer() {
	setOpaque(true);
	setHorizontalAlignment(CENTER);
	setVerticalAlignment(CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
	    int index, boolean isSelected, boolean cellHasFocus) {

	if (isSelected) {
	    setBackground(list.getSelectionBackground());
	    setForeground(list.getSelectionForeground());
	} else {
	    setBackground(list.getBackground());
	    setForeground(list.getForeground());
	}

	if (value instanceof Group) {
	    Group group = (Group) value;
	    setText(group.getName());
	    setToolTipText(group.getDescription());
	} else if (value instanceof Contact) {
	    Contact contact= (Contact) value;
	    setText(contact.getFirstName() + " " + contact.getLastName());
	    setToolTipText(contact.getFirstName() + " " + contact.getLastName());
	}
	return this;
    }
}
