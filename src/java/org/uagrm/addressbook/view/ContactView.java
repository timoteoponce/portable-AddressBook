/*
 * Created by JFormDesigner on Sat Mar 13 12:55:36 VET 2010
 */

package org.uagrm.addressbook.view;

import java.util.Observable;

import javax.swing.JPanel;

import org.uagrm.addressbook.model.Contact;
import org.uagrm.addressbook.view.event.GenericEvent;
import org.uagrm.addressbook.view.event.GenericEventListener;
import org.uagrm.addressbook.view.event.GenericEventType;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class ContactView extends JPanel implements View<Contact>, GenericEventListener {
	public ContactView() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		CellConstraints cc = new CellConstraints();

		// ======== this ========
		setLayout(new FormLayout("default, $lcgap, default", "2*(default, $lgap), default"));
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setModel(Contact model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public Contact getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eventFired(GenericEvent event) {
		if (event.getType() == GenericEventType.ELEMENT_SELECTED) {
			if (event.getSource() instanceof ContactListView) {
				final ContactListView listView = (ContactListView) event.getSource();
				setModel(listView.getModel());
			}
		}

	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
