/*
 * Created by JFormDesigner on Mon Feb 22 21:16:38 BOT 2010
 */

package org.uagrm.addressbook.view.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.EventListenerList;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.view.event.SearchEvent;
import org.uagrm.addressbook.view.event.SearchEventListener;
import org.uagrm.addressbook.view.event.SearchEventType;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Timoteo Ponce
 */
public class SearchDialog extends JDialog {
	private static Logger LOG = Logger.getLogger(SearchDialog.class);

	private final Collection<SelectableItem> validElements = new ArrayList<SelectableItem>();
	private final Collection<SelectableItem> invalidElements = new ArrayList<SelectableItem>();
	private final EventListenerList listenerList = new EventListenerList();

	public SearchDialog(Frame owner) {
		super(owner);
		initComponents();		
	}

	public void showDialog() {
		init();
		this.setVisible(true);
	}

	private void init() {
	    	elementList.setModel(new DefaultListModel());
		elementList.setCellRenderer(new SearchListCellRenderer());
		getListModel().clear();
		//
		for (SelectableItem item : validElements) {
			getListModel().addElement(item);
		}
	}

	private DefaultListModel getListModel() {
		return (DefaultListModel) elementList.getModel();
	}

	public SearchDialog(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void okButtonActionPerformed(ActionEvent e) {
		okAction();
	}

	private void okAction() {
		LOG.info("Selected: " + getSelected());
		this.dispose();
		fireEvent(SearchEventType.SELECTED);
	}

	private void cancelButtonActionPerformed(ActionEvent e) {
		cancelAction();		
	}

	private void cancelAction() {
		this.dispose();		
		fireEvent(SearchEventType.CANCELLED);
	}

	public Collection<SelectableItem> getValidElements() {
		return validElements;
	}

	public void setValidElements(Collection<SelectableItem> validElements) {
		this.validElements.clear();
		this.validElements.addAll(validElements);
	}

	public Collection<SelectableItem> getInvalidElements() {
		return invalidElements;
	}

	public void setInvalidElements(Collection<SelectableItem> invalidElements) {
		this.invalidElements.clear();
		this.invalidElements.addAll(invalidElements);
	}

	public SelectableItem getSelected() {
		SelectableItem selected = (SelectableItem) elementList
				.getSelectedValue();
		if (isInvalid(selected)) {
			selected = null;
		}
		return selected;
	}

	private boolean isInvalid(SelectableItem item) {
		return invalidElements.contains(item);
	}

	class SearchListCellRenderer extends JLabel implements ListCellRenderer {

		public SearchListCellRenderer() {
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

			final SelectableItem item = (SelectableItem) value;
			final boolean invalid = isInvalid(item);
			setText(item.toString());
			setEnabled(!invalid);

			return this;
		}
	}
	
	//Search event structure	
	
	public void addSearchEventListener(SearchEventListener listener){
	    listenerList.add(SearchEventListener.class, listener);
	}
	
	public void fireEvent(SearchEventType type){
	    SearchEvent event = new SearchEvent(this,type);
	    SearchEventListener[] listeners = listenerList.getListeners(SearchEventListener.class);
	    
	    for (SearchEventListener listener : listeners) {
		listener.eventFired(event);
	    }
	}

//	private static List<SelectableItem> list1 = new ArrayList<SelectableItem>();
//	private static List<SelectableItem> list2 = new ArrayList<SelectableItem>();
//
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		SearchDialog dialog = new SearchDialog(frame);
//		createElements();
//		dialog.setValidElements(list1);
//		dialog.setInvalidElements(list2);
//		frame.setVisible(true);
//		dialog.showDialog();
//	}
//
//	private static void createElements() {
//		Contact contact = new Contact();
//		contact.setId(1);
//		contact.setFirstName("Timoteo");
//		contact.setLastName("Ponce");
//		list1.add(contact);
//
//		contact = new Contact();
//		contact.setId(2);
//		contact.setFirstName("Pedro");
//		contact.setLastName("Ponce");
//		list1.add(contact);
//
//		contact = new Contact();
//		contact.setId(3);
//		contact.setFirstName("Marcelo");
//		contact.setLastName("Ponce");
//		list1.add(contact);
//		list2.add(contact);
//
//		contact = new Contact();
//		contact.setId(4);
//		contact.setFirstName("Hugo");
//		contact.setLastName("Ponce");
//		list1.add(contact);
//
//		contact = new Contact();
//		contact.setId(5);
//		contact.setFirstName("Miguel");
//		contact.setLastName("Ponce");
//		list1.add(contact);
//		list2.add(contact);
//	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		ResourceBundle bundle = ResourceBundle.getBundle("messages");
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		lblFilter = new JLabel();
		txtFilter = new JTextField();
		btnFilter = new JButton();
		listSeparator = compFactory.createSeparator("List");
		scrollPane = new JScrollPane();
		elementList = new JList();
		buttonBar = new JPanel();
		okButton = new JButton();
		cancelButton = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(bundle.getString("SearchDialog.title"));
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
		    dialogPane.setBorder(Borders.DIALOG_BORDER);
		    dialogPane.setLayout(new BorderLayout());

		    //======== contentPanel ========
		    {
			contentPanel.setLayout(new FormLayout(
			    "32dlu, $lcgap, 124dlu, $lcgap, 34dlu",
			    "2*(default, $lgap), default:grow"));

			//---- lblFilter ----
			lblFilter.setText(bundle.getString("SearchDialog.filter"));
			lblFilter.setLabelFor(txtFilter);
			contentPanel.add(lblFilter, cc.xy(1, 1));
			contentPanel.add(txtFilter, cc.xy(3, 1));

			//---- btnFilter ----
			btnFilter.setText(bundle.getString("SearchDialog.search"));
			contentPanel.add(btnFilter, cc.xy(5, 1));
			contentPanel.add(listSeparator, cc.xywh(1, 3, 5, 1));

			//======== scrollPane ========
			{

			    //---- elementList ----
			    elementList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			    scrollPane.setViewportView(elementList);
			}
			contentPanel.add(scrollPane, cc.xywh(3, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		    }
		    dialogPane.add(contentPanel, BorderLayout.CENTER);

		    //======== buttonBar ========
		    {
			buttonBar.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
			buttonBar.setLayout(new FormLayout(
			    "$glue, $button, $rgap, $button",
			    "pref"));

			//---- okButton ----
			okButton.setText(bundle.getString("common.ok"));
			okButton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
				okButtonActionPerformed(e);
			    }
			});
			buttonBar.add(okButton, cc.xy(2, 1));

			//---- cancelButton ----
			cancelButton.setText(bundle.getString("common.cancel"));
			cancelButton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
				cancelButtonActionPerformed(e);
			    }
			});
			buttonBar.add(cancelButton, cc.xy(4, 1));
		    }
		    dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JLabel lblFilter;
	private JTextField txtFilter;
	private JButton btnFilter;
	private JComponent listSeparator;
	private JScrollPane scrollPane;
	private JList elementList;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
