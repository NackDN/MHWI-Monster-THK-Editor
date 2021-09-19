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

public final class Config extends JFrame implements ActionListener {   
	JFileChooser fileChooser;
	
	JSplitPane containerPanel1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

	JPanel mainPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel mainPanel2 = new JPanel();
	
	JPanel sub1Panel1 = new JPanel();
	
	JPanel sub2Panel1 = new JPanel();
	
	JLabel label_openDFolder = new JLabel("Default Directory: ");
	JTextField textfield_openDFolder = new JTextField(30);	
	JButton button_openDFolder = new JButton("Open");
	
	JButton button_saveFunc = new JButton("Save");
	
	AppGUI parent = null;
	boolean changeReady = true;
	
	public Config(AppGUI parent) {
		this.parent = parent;
		setTitle("THK Editor - Configure");
		setSize(560,455);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    addWindowListener(new WindowAdapter() {
	    	@Override
	    	public void windowClosing(WindowEvent e) {
	    	    setVisible(false);
	    	    textfield_openDFolder.setText(parent.defaultDir);
	    	}
	    });
	    
	    fileChooser = new JFileChooser(parent.fileChooser.getCurrentDirectory());
	    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(false);
	    
	    button_saveFunc.addActionListener(this);
	    button_saveFunc.setActionCommand("Save");
	    button_openDFolder.addActionListener(this);
	    button_openDFolder.setActionCommand("OpenDFolder");
	    //--------------------------------------------------------------------------------------
	    containerPanel1.resetToPreferredSizes();
	    containerPanel1.setDividerLocation(375);
	    containerPanel1.setDividerSize(-1);
	    
	    containerPanel1.add(mainPanel1);
	    containerPanel1.add(mainPanel2);
	    
	    mainPanel1.add(sub1Panel1);
	    sub1Panel1.add(label_openDFolder);
	    sub1Panel1.add(textfield_openDFolder);
	    sub1Panel1.add(button_openDFolder);
	    
	    mainPanel2.add(sub2Panel1);
	    sub2Panel1.add(button_saveFunc);	    
	        
	    add(containerPanel1);
	    //--------------------------------------------------------------------------------------
	    textfield_openDFolder.setText(parent.defaultDir);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Save")) {
			System.out.println("Starting Config Save");
			parent.defaultDir = textfield_openDFolder.getText();
			parent.configToString();
			parent.conv.writeTextTo(parent.configData, parent.configFile);
			setVisible(false);
		}
		if (cmd.equals("OpenDFolder")) {
			int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal==0) {
            	textfield_openDFolder.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
		}
	}
}
