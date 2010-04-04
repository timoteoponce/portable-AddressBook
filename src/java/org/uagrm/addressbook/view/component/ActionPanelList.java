/*
 * Created by JFormDesigner on Sat Mar 27 15:46:19 VET 2010
 */

package org.uagrm.addressbook.view.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.uagrm.addressbook.model.swing.ListModel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class ActionPanelList<T> extends JPanel {

	private final ListModel<T> listModel = new ListModel<T>();
	private boolean editionEnabled;

	public ActionPanelList(boolean editionEnabled) {
		initComponents();
		list.setModel(listModel);
		this.editionEnabled = editionEnabled;
		initActionPanel();
	}

	private void initActionPanel() {
		actionPanel.getEditButton().setEnabled(editionEnabled);
		actionPanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals(ActionPanel.ACTION_ADD)) {
					addNewElement();
				} else if (e.getActionCommand().equals(ActionPanel.ACTION_EDIT)) {
					editSelected();
				} else if (e.getActionCommand().equals(ActionPanel.ACTION_REMOVE)) {
					removeSelected();
				}

			}
		});
	}

	public void addNewElement() {
	}

	public void editSelected() {
	}

	public void addElement(T element) {
		listModel.addElement(element);
	}

	public void addAllElements(Collection<T> elements) {
		listModel.addAllElements(elements);
	}

	public T getSelected() {
		final int index = list.getSelectedIndex();

		if (index >= 0) {
			return listModel.getElement(index);
		}
		return null;
	}

	public void removeSelected() {
		final int index = list.getSelectedIndex();

		if (index >= 0) {
			listModel.removeElement(index);
			list.updateUI();
		}
	}

	public void setTitle(String title) {
		actionPanel.setTitle(title);
	}

	public Collection<T> getElements() {
		return listModel.getElements();
	}

	public void clear() {
		listModel.clear();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		actionPanel = new ActionPanel();
		listScrollPane = new JScrollPane();
		list = new JList();
		CellConstraints cc = new CellConstraints();

		// ======== this ========
		setLayout(new FormLayout("default:grow", "default, $lgap, 115dlu"));
		add(actionPanel, cc.xy(1, 1));

		// ======== listScrollPane ========
		{
			listScrollPane.setViewportView(list);
		}
		add(listScrollPane, cc.xywh(1, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private ActionPanel actionPanel;
	private JScrollPane listScrollPane;
	private JList list;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
