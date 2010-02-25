package org.ponce.addressbook.view;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.ponce.addressbook.model.Group;

/**
 * @author Timoteo Ponce
 *
 */
public class GroupListCellRenderer extends JLabel implements ListCellRenderer {

	public GroupListCellRenderer() {
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

		if (index > 0) {
			Group group = (Group) value;
			setText(group.getName());
			setToolTipText(group.getDescription());
		} else {
			setText("All");
			setToolTipText("");
		}
		return this;
	}
}
