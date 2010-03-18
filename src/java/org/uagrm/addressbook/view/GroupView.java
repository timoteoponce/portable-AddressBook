/*
 * Created by JFormDesigner on Sat Mar 13 12:55:24 VET 2010
 */

package org.uagrm.addressbook.view;

import java.util.Observable;







import javax.swing.JPanel;

import org.uagrm.addressbook.controller.Controller;
import org.uagrm.addressbook.model.Group;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class GroupView extends JPanel implements View<Group> {













	public GroupView() {
		initComponents();
	}









































































































	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"default, $lcgap, default",
			"2*(default, $lgap), default"));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public Controller<Group> getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModel(Group model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// JFormDesigner - End of variables declaration  //GEN-END:variables










}
