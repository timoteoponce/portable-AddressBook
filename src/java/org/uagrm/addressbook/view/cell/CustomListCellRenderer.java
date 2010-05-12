package org.uagrm.addressbook.view.cell;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.uagrm.addressbook.model.Address;
import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.model.Country;
import org.uagrm.addressbook.model.Group;
import org.uagrm.addressbook.model.Service;

/**
 * @author Timoteo Ponce
 * 
 */
public class CustomListCellRenderer extends JLabel implements ListCellRenderer {

	public CustomListCellRenderer() {
		super();
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
			final Group group = (Group) value;
			setText(group.getName());
			setToolTipText(group.getDescription());
		} else if (value instanceof Contact) {
			final Contact contact = (Contact) value;
			setText(contact.getFirstName() + " " + contact.getLastName());
			setToolTipText(contact.getFirstName() + " " + contact.getLastName());
		} else if (value instanceof Address) {
			final Address address = (Address) value;
			setText(address.toString());
		} else if (value instanceof Country) {
			final Country country = (Country) value;
			setText(country.getName());
		} else if (value instanceof Service) {
			final Service protocol = (Service) value;
			setText(protocol.getName());
		}
		return this;
	}
}
