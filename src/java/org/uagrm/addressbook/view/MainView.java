package org.uagrm.addressbook.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif_lite.panel.SimpleInternalFrame;

/**
 * @author Timoteo Ponce
 * 
 */
public class MainView extends JFrame {

	private final GroupView groupView;

	public MainView() {
		initComponents();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		groupView = new GroupView();
		panelGroups.add(groupView, BorderLayout.CENTER);
		setTitle("MainView");
	}

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		UIManager
				.setLookAndFeel(com.jgoodies.looks.plastic.PlasticXPLookAndFeel.class
						.getName());
		MainView frame = new MainView();
		frame.setVisible(true);
	}

	private void tbButtonCreateGroupActionPerformed(ActionEvent e) {
	    groupView.showcreateDialog();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		ResourceBundle bundle = ResourceBundle.getBundle("messages");
		mainMenuBar = new JMenuBar();
		toolBar1 = new JToolBar();
		tbButtonCreateGroup = new JButton();
		mainPanel = new JPanel();
		panelGroups = new SimpleInternalFrame();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle(bundle.getString("MainView.title"));
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
		    "default, $lcgap, default:grow, $lcgap, default",
		    "29dlu, $lgap, default:grow, $lgap, default"));
		setJMenuBar(mainMenuBar);

		//======== toolBar1 ========
		{

		    //---- tbButtonCreateGroup ----
		    tbButtonCreateGroup.setText(bundle.getString("MainView.createGroup"));
		    tbButtonCreateGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    tbButtonCreateGroupActionPerformed(e);
			}
		    });
		    toolBar1.add(tbButtonCreateGroup);
		}
		contentPane.add(toolBar1, cc.xywh(1, 1, 3, 1));

		//======== mainPanel ========
		{
		    mainPanel.setLayout(new FormLayout(
			"63dlu, $lcgap, 56dlu, $lcgap, default:grow",
			"default:grow"));

		    //======== panelGroups ========
		    {
			panelGroups.setTitle("Groups");
			Container panelGroupsContentPane = panelGroups.getContentPane();
			panelGroupsContentPane.setLayout(new BorderLayout());
		    }
		    mainPanel.add(panelGroups, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		}
		contentPane.add(mainPanel, cc.xywh(3, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		setSize(485, 415);
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JMenuBar mainMenuBar;
	private JToolBar toolBar1;
	private JButton tbButtonCreateGroup;
	private JPanel mainPanel;
	private SimpleInternalFrame panelGroups;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
