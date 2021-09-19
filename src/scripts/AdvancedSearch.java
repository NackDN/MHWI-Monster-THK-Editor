package scripts;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.*;
import javafx.stage.Stage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;

public final class AdvancedSearch extends JFrame implements ItemListener {   
	JSplitPane containerPanel1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	JSplitPane containerPanel2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

	JSplitPane binPanel1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	JSplitPane binPanel2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	
	JPanel mainPanel1 = new JPanel(new GridLayout(1, 2));
	JPanel mainPanel2 = new JPanel(new GridLayout(4, 2));
	JPanel mainPanel3 = new JPanel();
	
	JPanel sub1Panel1 = new JPanel();
	JPanel sub1Panel2 = new JPanel();
	JPanel sub2Panel1 = new JPanel();
	JPanel sub2Panel2 = new JPanel();
	JPanel sub2Panel3 = new JPanel();
	JPanel sub2Panel4 = new JPanel();
	JPanel sub2Panel5 = new JPanel();
	JPanel sub2Panel6 = new JPanel();
	JPanel sub2Panel7 = new JPanel();

	JLabel label_mP1 = new JLabel("Primary Search");
	JLabel label_mP2 = new JLabel("Secondary Search");
	
	String[] array_searchFunc = {"Local Ref.", "Ext. Ref.", "Action", "Function Type", "ID"};
	JComboBox combobox_searchFunc = new JComboBox(array_searchFunc);	
	JTextField textfield_searchFunc = new JTextField(10);
	
	JLabel label_para1Search = new JLabel("Parameter 1: ");
	JTextField textfield_para1Search = new JTextField(10);
	JLabel label_para2Search = new JLabel("Parameter 2: ");
	JTextField textfield_para2Search = new JTextField(10);
	
	JLabel label_actUnkn1 = new JLabel("Action Unknown 1: ");
	JTextField textfield_actUnkn1 = new JTextField(10);
	JLabel label_actUnkn2 = new JLabel("Action Unknown 2: ");
	JTextField textfield_actUnkn2 = new JTextField(10);
	JLabel label_actUnkn3 = new JLabel("Action Unknown 3: ");
	JTextField textfield_actUnkn3 = new JTextField(10);
	JLabel label_actUnkn4 = new JLabel("Action Unknown 4: ");
	JTextField textfield_actUnkn4 = new JTextField(10);
	JLabel label_actUnkn5 = new JLabel("Action Unknown 5: ");
	JTextField textfield_actUnkn5 = new JTextField(10);
	
	JButton button_searchFunc = new JButton("Search");
	
	AppGUI parent = null;
	boolean changeReady = true;
	
	public AdvancedSearch(AppGUI parent) {
		this.parent = parent;
		setTitle("THK Editor - Advanced Search");
		setSize(400,650);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    addWindowListener(new WindowAdapter() {
	    	@Override
	    	public void windowClosing(WindowEvent e) {
	    	    setVisible(false);
	    	}
	    });
	    

	    combobox_searchFunc.addItemListener(this);
	    
		textfield_searchFunc.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				updatePrimarySearch(parent.searchMenu);
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				updatePrimarySearch(parent.searchMenu);			
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				updatePrimarySearch(parent.searchMenu);			
			}
		});
	    
	    //----------------------------------------

	    containerPanel1.resetToPreferredSizes();
	    containerPanel1.setDividerLocation(75);
	    containerPanel1.setDividerSize(-1);
	    
	    containerPanel2.resetToPreferredSizes();
	    containerPanel2.setDividerLocation(445);
	    containerPanel2.setDividerSize(-1);
	    
	    binPanel1.resetToPreferredSizes();
	    //binPanel1.setDividerLocation(445);
	    binPanel1.setDividerSize(-1);
	    
	    binPanel2.resetToPreferredSizes();
	    //binPanel2.setDividerLocation(445);
	    binPanel2.setDividerSize(-1);
	    
	    sub1Panel1.add(combobox_searchFunc);
	    sub1Panel2.add(textfield_searchFunc);	    
	    
	    sub2Panel1.add(label_para1Search);
	    sub2Panel1.add(textfield_para1Search);
	    sub2Panel2.add(label_para2Search);
	    sub2Panel2.add(textfield_para2Search);	    
	    sub2Panel3.add(label_actUnkn1);
	    sub2Panel3.add(textfield_actUnkn1);
	    sub2Panel4.add(label_actUnkn2);
	    sub2Panel4.add(textfield_actUnkn2);
	    sub2Panel5.add(label_actUnkn3);
	    sub2Panel5.add(textfield_actUnkn3);
	    sub2Panel6.add(label_actUnkn4);
	    sub2Panel6.add(textfield_actUnkn4);
	    sub2Panel7.add(label_actUnkn5);
	    sub2Panel7.add(textfield_actUnkn5);
	    
	    mainPanel1.add(sub1Panel1);
	    mainPanel1.add(sub1Panel2);
	    
	    binPanel1.add(label_mP1);
	    binPanel1.add(mainPanel1);
	    
	    mainPanel2.add(sub2Panel1);
	    mainPanel2.add(sub2Panel2);
	    mainPanel2.add(sub2Panel3);
	    mainPanel2.add(sub2Panel4);
	    mainPanel2.add(sub2Panel5);
	    mainPanel2.add(sub2Panel6);
	    mainPanel2.add(sub2Panel7);
	    
	    binPanel2.add(label_mP2);
	    binPanel2.add(mainPanel2);	    
	    
	    mainPanel3.add(button_searchFunc);
	    
	    containerPanel1.add(binPanel1);
	    containerPanel1.add(containerPanel2);

	    containerPanel2.add(binPanel2);
	    containerPanel2.add(mainPanel3);
	    
	    add(containerPanel1);
	}
	public void updatePrimarySearch (Object caller) {
		if ((caller==parent)&&(changeReady)) {
			changeReady = false;
			if (!(parent.textfield_searchFunc.getText().equals(this.textfield_searchFunc.getText()))) {
				this.textfield_searchFunc.setText(parent.textfield_searchFunc.getText());
			}
			changeReady = true;
		} else if (changeReady) {
			changeReady = false;
			if (!(parent.textfield_searchFunc.getText().equals(this.textfield_searchFunc.getText()))) {
				parent.textfield_searchFunc.setText(this.textfield_searchFunc.getText());
			}
			changeReady = true;
		}
	}
	public void itemStateChanged(ItemEvent e) {
		if ((parent.combobox_searchFunc.getSelectedIndex())!=(this.combobox_searchFunc.getSelectedIndex())) {
			parent.combobox_searchFunc.setSelectedIndex(this.combobox_searchFunc.getSelectedIndex());
		}
	}
}
