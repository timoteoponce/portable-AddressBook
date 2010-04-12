package org.uagrm.addressbook.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.EventListenerList;

import org.uagrm.addressbook.model.swing.ListModel;
import org.uagrm.addressbook.view.cell.CustomListCellRenderer;
import org.uagrm.addressbook.view.event.GenericEvent;
import org.uagrm.addressbook.view.event.GenericEventListener;
import org.uagrm.addressbook.view.event.GenericEventType;

public abstract class AbstractListView<T> extends JPanel implements ListView<T> {

	private static final long serialVersionUID = 6455270438653422762L;

	private final ListModel<T> listModel = new ListModel<T>();
	
	private final EventListenerList listenerList = new EventListenerList();

	private JFrame mainWindow;

	private T model;

	protected void init() {
		getController().addView(this);
		getList().setModel(getListModel());
		getList().setCellRenderer(new CustomListCellRenderer());
		updateList();
		//
		getList().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked(e);
			}
		});
	}

	@Override
	public void addEventListener(GenericEventListener listener) {
		listenerList.add(GenericEventListener.class, listener);
	}

	@Override
	public void fireEvent(GenericEventType type) {
		GenericEvent event = new GenericEvent(this, type);
		GenericEventListener[] listeners = listenerList.getListeners(GenericEventListener.class);

		for (GenericEventListener item : listeners) {
			item.eventFired(event);
		}
	}

	@Override
	public void close() {
		getController().removeView(this);
		this.setVisible(false);
	}

	@Override
	public T getModel() {
		return model;
	}

	@Override
	public void setModel(T model) {
		this.model = model;
	}

	@Override
	public void update(Observable source, Object model) {
		if (source.equals(getController())) {
			updateList();
		}
	}

	@Override
	public ListModel<T> getListModel() {
		return listModel;
	}

	@Override
	public void removeCurrent() {
		if (getModel() != null) {
			getController().delete(getModel(), true);
		}
	}

	@Override
	public void setMainWindow(JFrame frame) {
		this.mainWindow = frame;
	}

	@Override
	public JFrame getMainWindow() {
		return mainWindow;
	}

	@Override
	public void updateList() {
		listModel.clear();
		listModel.addAllElements(getController().getElements());
	}

	protected void clicked(MouseEvent e) {
		final int index = getList().getSelectedIndex();		
		setModel((T) getList().getSelectedValue());

		if (e.getModifiers() == MouseEvent.BUTTON3_MASK && index > 0) {
			showPopUpMenu(e.getX(), e.getY());
		} else {
			if (getModel() != null) {
				fireEvent(GenericEventType.ELEMENT_SELECTED);
			}
		}
	}

	private void showPopUpMenu(final int posX, final int posY) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages");
		//
		JPopupMenu menu = new JPopupMenu();
		JMenuItem editItem = new JMenuItem(bundle.getString("common.edit"));
		JMenuItem removeItem = new JMenuItem(bundle.getString("common.remove"));
		menu.add(editItem);
		menu.add(removeItem);

		removeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeCurrent();
			}
		});
		editItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editCurrent();
			}
		});
		menu.show(this, posX, posY);
	}

}
