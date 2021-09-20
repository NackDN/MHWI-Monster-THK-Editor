package scripts;

/* To-Do List:
-Switch from JavaFX File Explorer to OS File Explorer
Add Check Types...
- 1C or 1D: In-Combat Check?
*/

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
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.javafx.css.Stylesheet;
import com.sun.javafx.css.parser.CSSParser;

import javax.swing.UIManager;

import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.*;
import javafx.stage.Stage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;

public final class AppGUI extends JFrame implements ActionListener, ItemListener {
	static UIManager UIManager = new UIManager();
	JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
	
    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	JPanel mainPanel1 = new JPanel(new GridLayout(1, 2));	//For the Node and Segment Lists (And Now, Search Bar)
    JPanel sub1Panel1 = new JPanel();
    JPanel sub1Panel2 = new JPanel(); 
    JTabbedPane mainPanel2 = new JTabbedPane();
    JPanel sub2Panel1 = new JPanel(new GridLayout(3, 2));	//For Node-Editing Buttons
    JPanel sub2Panel2 = new JPanel(new GridLayout(3, 5));	//For Segment-Editing Buttons
    JPanel sub2Panel3 = new JPanel(new GridLayout(2, 3));	//For Unknown Segment Values
	JPanel mainPanel3 = new JPanel(new GridLayout(1, 2));
    JPanel sub3Panel1 = new JPanel();
    JPanel sub3Panel2 = new JPanel();
    JPanel panel_check = new JPanel();
    JPanel panel_interrupt = new JPanel();
    JPanel panel_if = new JPanel();
    JPanel panel_parameter = new JPanel();
    JPanel panel_paramete2 = new JPanel();
    JPanel panel_reftype = new JPanel();
    JPanel panel_refid = new JPanel();
    JPanel panel_action = new JPanel();
    JPanel panel_random = new JPanel();
    JPanel panel_saveSeg = new JPanel();
    JPanel panel_nodeOffset = new JPanel();
    JPanel panel_nodeSegCount = new JPanel();
    JPanel panel_nodeID = new JPanel();
    JPanel panel_saveNode = new JPanel();
    JPanel panel_nodeListBtn = new JPanel();
    JPanel panel_segListBtn = new JPanel();
    JPanel panel_nodeTitleLabel = new JPanel();
    JPanel panel_nodeTitleTextField = new JPanel(); 
    JPanel panel_actUnkn1 = new JPanel();
    JPanel panel_actUnkn2 = new JPanel();
    JPanel panel_actUnkn3 = new JPanel();
    JPanel panel_actUnkn4 = new JPanel();
    JPanel panel_actUnkn5 = new JPanel();
    JPanel panel_saveSeg2 = new JPanel();
    JPanel panel_goTo = new JPanel();

    JMenuBar menuBar = new JMenuBar();	
    JMenu fileMenu = new JMenu("File");
    JMenu helpMenu = new JMenu("Help");
	JMenuItem fileNew = new JMenuItem("New");
	JMenuItem fileOpen = new JMenuItem("Open");
	JMenuItem fileSave = new JMenuItem("Save");	
	JMenuItem fileSaveAs = new JMenuItem("Save As");
	JMenuItem helpConfig = new JMenuItem("Configure");
	JMenuItem helpAbout = new JMenuItem("About");
	JCheckBoxMenuItem titleSaveEnable = new JCheckBoxMenuItem("Save Title");
	
	JLabel label_NodeList = new JLabel("Nodes:");
	JLabel label_SegList = new JLabel("Segments:");
	DefaultListModel listModel_Nodes = new DefaultListModel();
	JList list_NodeSelect = new JList(listModel_Nodes);
	DefaultListModel listModel_Segs = new DefaultListModel();
	JList list_SegSelect = new JList(listModel_Segs);
	JScrollPane jsp_1 = new JScrollPane(list_NodeSelect);
	JScrollPane jsp_2 = new JScrollPane(list_SegSelect);

	JCheckBox checkbox_lastSeg = new JCheckBox("- Node-Ending Seg?");
	String[] array_SegCheckChoice = {"NULL (00)",
			"Nothing (02)",
			"Set Target (Entity) (03)",
			"Set Target (Area) (05)",
			"IF (Total) Dist. < (06)",
			"IF (Total) Dist. > (07)",
			"IF (Hori.) Dist. < (08)",
			"IF (Hori.) Dist. > (09)",
			"IF Height (0F)",
			"IF Rotation (16)",
			"IF Enraged (1E)",
			"IF Fatigued (1F)",
			"IF Poisoned (20)",
			"IF Mounted Part = (24)",
			"IF Mounted (25)",
			"IF HP < (Para2)% (2C)",
			"IF Target's Status (2E)",
			"IF Quest ID (37)",
			"Alt. Target (?) (5E)",
			"IF Nearest Meat > (6B)",
			"IF Flying (70)",
			"IF Part P1 Broken (76)",
			"Chng Area/THK17 (AE)",
			"IF Quest Rank (B8)",
			"Area Check (BF)",
			"Set Int A (80)",
			"Set Int B (81)",
			"Set Int C (82)",
			"Set Int D (83)",
			"Set Int E (84)",
			"Set Int F (85)",
			"Set Int G (86)",
			"Set Int H (87)",
			"Set Int I (88)",
			"Set Int J (89)",
			"Set Int K (8A)",
			"Set Int L (8B)",
			"Set Int M (8C)",
			"Set Int N (8D)",
			"Set Int O (8E)",
			"Set Int P (8F)",
			"Set Int Q (90)",
			"Set Int R (91)",
			"Set Int S (92)",
			"Set Int T (93)",
			"Check Int A (94)",
			"Check Int B (95)",
			"Check Int C (96)",
			"Check Int D (97)",
			"Check Int E (98)",
			"Check Int F (99)",
			"Check Int G (9A)",
			"Check Int H (9B)",
			"Check Int I (9C)",
			"Check Int J (9D)",
			"Check Int K (9E)",
			"Check Int L (9F)",
			"Check Int M (A0)",
			"Check Int N (A1)",
			"Check Int O (A2)",
			"Check Int P (A3)",
			"Check Int Q (A4)",
			"Check Int R (A5)",
			"Check Int S (A6)",
			"Check Int T (A7)",
			"Custom"};
	// 20 (Index 0) FTypes Pre-Counter
	// 61 (Index 0) FTypes Total (Set A is 21; Check A is 41)
	JLabel label_SegCheckChoice = new JLabel("Function Type: ");
	JComboBox combobox_SegCheckChoice = new JComboBox(array_SegCheckChoice);
	String[] array_SegInterruptChoice = {"None (00)", "Loop Node (04)", "Return (08)",  "Loop THK (80)", "Unknown"};
	JLabel label_SegInterruptChoice = new JLabel("Flow Control: ");
	JComboBox combobox_SegInterruptChoice = new JComboBox(array_SegInterruptChoice);
	String[] array_SegIfChoice = {"None (00)",
			"[End % Chance] (01)",
			"If... (02)",
			"Else/Else If... (04)",
			"End If (08)",
			"Initial Seg. (10)",
			"Unknown"};
	JLabel label_SegIfChoice = new JLabel("Branching Type: ");
	JComboBox combobox_SegIfChoice = new JComboBox(array_SegIfChoice);
	JLabel label_Parameter = new JLabel("Parameter 1: ");
	JTextField textfield_Parameter = new JTextField(10);
	JLabel label_Paramete2 = new JLabel("Parameter 2: ");
	JTextField textfield_Paramete2 = new JTextField(10);
	String[] array_RefTypeChoice = {"None (00)", "External (THK55)", "Local"};
	JLabel label_RefTypeChoice = new JLabel("Reference Type: ");
	JComboBox combobox_RefTypeChoice = new JComboBox(array_RefTypeChoice);
	JLabel label_ReferenceId = new JLabel("Reference ID: ");
	JTextField textfield_ReferenceId = new JTextField(10);
	JLabel label_Action = new JLabel("   Action ID:    ");	
	JTextField textfield_Action = new JTextField(10);
	String[] array_RandomChoice = {"None (00)", "% Chance (40)", "Cont. % Choice (CO)", "Final % Chance (80)", "Unknown"};
	JLabel label_RandomChoice = new JLabel("Random Type: ");
	JComboBox combobox_RandomChoice = new JComboBox(array_RandomChoice);
	JButton button_saveSeg = new JButton("Save Segment");
	
	JLabel label_nodeOffset = new JLabel("Node Index: ");
	JLabel label_nodeSegCount = new JLabel("# of Segments: ");
	JLabel label_nodeID = new JLabel("Node ID: ");
	JTextField textfield_nodeID = new JTextField(10);
	JButton button_saveNode = new JButton("Save Node");
	
	JButton button_addNode = new JButton("Add");
	JButton button_delNode = new JButton("Delete");
	JButton button_sNodeUp = new JButton("Up");
	JButton button_sNodeMass = new JButton("Shift");
	JLabel label_shiftNode = new JLabel(":SHIFT:");
	JButton button_sNodeDo = new JButton("Down");
	JButton button_cloneNode = new JButton("Copy");	
	
	JButton button_addSeg = new JButton("Add");
	JButton button_delSeg = new JButton("Delete");
	JButton button_sSegUp = new JButton("Up");
	JButton button_sSegMass = new JButton("Shift");	
	JLabel label_shiftSeg = new JLabel(":SHIFT:");
	JButton button_sSegDo = new JButton("Down");
	JButton button_cloneSeg = new JButton("Copy");	
	
	JLabel label_nodeTitle = new JLabel("Name: ");
	JTextField textfield_nodeTitle = new JTextField(30);
	
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
	JButton button_saveSeg2 = new JButton("Save Segment");
	
	JButton button_goTo = new JButton("Go To Ref.");
	
	JLabel label_searchFunc = new JLabel("0 results found");	
	String[] array_searchFunc = {"Local Ref.", "Ext. Ref.", "Action", "Function Type", "ID"};
	JComboBox combobox_searchFunc = new JComboBox(array_searchFunc);	
	JTextField textfield_searchFunc = new JTextField(11);
	JButton button_searchFunc = new JButton("Search");
	JButton button_advancedSearchFunc = new JButton("Adv.");

	Converter conv = new Converter();
	String configFile = System.getProperty("user.dir") + "\\thkcofig";
	String defaultDir = System.getProperty("user.dir");
	String configData = "THKCONFIG" + verNum + "\ndefaultDir=" + defaultDir + "\n";
	String currentFile = "";
	String currentTitle = "";
	String saveToFile = "";
	String hexData = "";
	String titleData = "";
	String headerA = "54484b002800";
	String headerB = "d87afb990000000020000000000000002000000000000000";	
	Node selectedNode = null;
	Segment selectedSeg = null;
	boolean latestVersion = true;
	boolean PromptCustomCheck = true;
	AdvancedSearch searchMenu;
	Config configMenu;
	int SegCheckLastChoice = 1;
	static String verNum = "2.0";					// Version Number
//Structures the GUI
	public AppGUI() {
//Frame Stuff
		setTitle("MHWI THK Editor (Ver. " + verNum + ")");
		setSize(800,650);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    addWindowListener(new WindowAdapter() {
	    	@Override
	    	public void windowClosing(WindowEvent e) {
	    		if(JOptionPane.showConfirmDialog(null, "Would you like to close the editor?", "Closing THK Editor", 0, 0, null)==0) {
	    			if (searchMenu!=null) {
	    				searchMenu.setVisible(false);
	    				searchMenu.dispose();
	    				searchMenu = null;
	    			}
	    			setVisible(false);
	    			dispose();
					System.exit(0);
	    		}
	    	}	    	
	    });
	    
	    // Checks for the "thkconfig" file in the same directory, and either copys the content to configData, or creates a new thkconfig
	    if (new File(configFile).exists()) {
	    	String nConfigData = conv.getTextFrom("THKCONFIG" + verNum, configFile);
	    	if (!nConfigData.equals("END")) {
	    		configData = nConfigData;	    		
	    	}
	    } else {
	    	conv.writeTextTo(configData, configFile);
	    }
	    
	    // Fills in all of the appropriate values from configData
    	if (configData.substring(verNum.length()+10,verNum.length()+21).equals("defaultDir=")) {
    		int pointer = 21 + verNum.length(); 
    		StringBuilder tempFileTest = new StringBuilder();
	    	while (!configData.substring(pointer,pointer+1).equals("\n")) {
	    		tempFileTest.append(configData.substring(pointer,pointer+1));
	    		pointer++;
	    	}
	    	defaultDir = tempFileTest.toString();
	    }
	    
	    // Reinitializes the fileChooser with the appropriate defaultDir
	    if (new File(defaultDir).exists() && new File(defaultDir).isDirectory()) {
	    	fileChooser = new JFileChooser(defaultDir);
	    } else {
	    	defaultDir = System.getProperty("user.dir");	    	
	    	configToString();
	    }
	    fileChooser.setFileFilter(new FileNameExtensionFilter("THK File", "thk"));
		fileChooser.setMultiSelectionEnabled(false);
		
		// Sets up the other JFrame classes
		searchMenu = new AdvancedSearch(this);
		configMenu = new Config(this);
	    
	    fileMenu.add(fileNew);
	    fileNew.addActionListener(this);
	    fileNew.setActionCommand("NewFile");
	    fileMenu.add(fileOpen);
	    fileOpen.addActionListener(this);
	    fileOpen.setActionCommand("OpenFile");
	    fileMenu.add(fileSave);
	    fileSave.addActionListener(this);
	    fileSave.setActionCommand("SaveFile");	    
	    fileMenu.add(fileSaveAs);
	    fileSaveAs.addActionListener(this);
	    fileSaveAs.setActionCommand("SaveFileAs");
	    fileMenu.add(titleSaveEnable);	    
	    menuBar.add(fileMenu);
	    menuBar.add(helpMenu);
	    helpMenu.add(helpConfig);
	    helpConfig.addActionListener(this);
	    helpConfig.setActionCommand("Config");	    
	    helpMenu.add(helpAbout);
	    helpAbout.addActionListener(this);
	    helpAbout.setActionCommand("About");
	    setJMenuBar(menuBar);
	    
	    titleSaveEnable.setState(true);
	    
	    button_addNode.addActionListener(this);
	    button_addNode.setActionCommand("AddNode");
	    
	    button_delNode.addActionListener(this);
	    button_delNode.setActionCommand("DeleteNode");
	    
	    button_sNodeUp.addActionListener(this);
	    button_sNodeUp.setActionCommand("ShiftNodeUp");
	    
	    button_sNodeMass.addActionListener(this);
	    button_sNodeMass.setActionCommand("ShiftNodeMass");
	    
	    button_sNodeDo.addActionListener(this);
	    button_sNodeDo.setActionCommand("ShiftNodeDown");
	    
	    button_cloneNode.addActionListener(this);
	    button_cloneNode.setActionCommand("CloneNode");	
	    
	    //------------
	    
	    button_saveSeg.addActionListener(this);
	    button_saveSeg.setActionCommand("SaveSeg");
	    
	    button_saveSeg2.addActionListener(this);
	    button_saveSeg2.setActionCommand("SaveSeg");
	    
	    button_saveNode.addActionListener(this);
	    button_saveNode.setActionCommand("SaveNode");
	    
	    button_addSeg.addActionListener(this);
	    button_addSeg.setActionCommand("AddSeg");
	    
	    button_delSeg.addActionListener(this);
	    button_delSeg.setActionCommand("DeleteSeg");
	    
	    button_sSegUp.addActionListener(this);
	    button_sSegUp.setActionCommand("ShiftSegUp");
	    
	    button_sSegMass.addActionListener(this);
	    button_sSegMass.setActionCommand("ShiftSegMass");
	    
	    button_sSegDo.addActionListener(this);
	    button_sSegDo.setActionCommand("ShiftSegDown");
	    
	    button_cloneSeg.addActionListener(this);
	    button_cloneSeg.setActionCommand("CloneSeg");	
	    
	    //------------
	    
	    button_goTo.addActionListener(this);
	    button_goTo.setActionCommand("GoTo");
	    
	    button_searchFunc.addActionListener(this);
	    button_searchFunc.setActionCommand("Search");
	    searchMenu.button_searchFunc.addActionListener(this);
	    searchMenu.button_searchFunc.setActionCommand("Search");
	    
	    button_advancedSearchFunc.addActionListener(this);
	    button_advancedSearchFunc.setActionCommand("AdvancedSearch");
	    
	    checkbox_lastSeg.addItemListener(this);
	    combobox_SegCheckChoice.addItemListener(this);
	    combobox_SegInterruptChoice.addItemListener(this);
	    combobox_SegIfChoice.addItemListener(this);
	    combobox_RefTypeChoice.addItemListener(this);
	    combobox_RandomChoice.addItemListener(this);
	    
	    //-------------
	    
	    combobox_searchFunc.addItemListener(this);
	    
		textfield_searchFunc.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				searchMenu.updatePrimarySearch(searchMenu.parent);
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				searchMenu.updatePrimarySearch(searchMenu.parent);		
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				searchMenu.updatePrimarySearch(searchMenu.parent);		
			}
		});
	    
	    list_NodeSelect.setFixedCellWidth(350);
	    list_NodeSelect.setFixedCellHeight(18);
	    list_NodeSelect.setVisibleRowCount(10);
	    list_NodeSelect.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    ListSelectionListener listSeleMod_NodeSelect = new ListSelectionListener() {
	    	public void valueChanged(ListSelectionEvent listSelectionEvent) {
	    		if ((list_NodeSelect.getSelectedIndex()>-1)&&(selectedNode!=null)) {
	    			selectedNode = selectedNode.GetFirstNode();
	    			for (int i=0;i<list_NodeSelect.getSelectedIndex();i++) {
	    				selectedNode = selectedNode.next;
	    			}
	    			refreshSegList();
	    			refreshNodeTab();
	    		}
	    	}
	    };
	    list_NodeSelect.addListSelectionListener(listSeleMod_NodeSelect);
	    
	    list_SegSelect.setFixedCellWidth(350);
	    list_SegSelect.setFixedCellHeight(18);
	    list_SegSelect.setVisibleRowCount(10);	
	    list_SegSelect.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    ListSelectionListener listSeleMod_SegSelect = new ListSelectionListener() {
	    	public void valueChanged(ListSelectionEvent listSelectionEvent) {
	    		if ((list_SegSelect.getSelectedIndex()>-1)&&(selectedSeg!=null)) {
	    			selectedSeg = selectedNode.firstseg;
	    			for (int i=0;i<list_SegSelect.getSelectedIndex();i++) {
	    				selectedSeg = selectedSeg.next;
	    			}
	    			refreshSegTab();
	    		}
	    	}
	    };
	    list_SegSelect.addListSelectionListener(listSeleMod_SegSelect);

	    panel_nodeListBtn.add(button_addNode);
	    panel_nodeListBtn.add(button_delNode);
	    panel_nodeListBtn.add(button_sNodeUp);
	    panel_nodeListBtn.add(button_sNodeMass);
	    panel_nodeListBtn.add(button_sNodeDo);
	    panel_nodeListBtn.add(button_cloneNode);
	    
	    panel_segListBtn.add(button_addSeg);
	    panel_segListBtn.add(button_delSeg);
	    panel_segListBtn.add(button_sSegUp);
	    panel_segListBtn.add(button_sSegMass);
	    panel_segListBtn.add(button_sSegDo);
	    panel_segListBtn.add(button_cloneSeg);

	    sub1Panel1.add(label_NodeList);
	    sub1Panel1.add(jsp_1);
	    sub1Panel1.add(panel_nodeListBtn);
	    sub1Panel2.add(label_SegList);
	    sub1Panel2.add(jsp_2);
	    sub1Panel2.add(panel_segListBtn);
	    
	    splitPane.resetToPreferredSizes();
	    splitPane.setDividerLocation(250);
	    splitPane.setDividerSize(-1);
	    
	    splitPane2.resetToPreferredSizes();
	    splitPane2.setDividerSize(-1);
	    
	    mainPanel1.add(sub1Panel1);
	    mainPanel1.add(sub1Panel2);

	    panel_check.add(label_SegCheckChoice);
	    panel_check.add(combobox_SegCheckChoice);
	    
	    panel_interrupt.add(label_SegInterruptChoice);
	    panel_interrupt.add(combobox_SegInterruptChoice);
	    
	    panel_if.add(label_SegIfChoice);
	    panel_if.add(combobox_SegIfChoice);
	    
	    panel_parameter.add(label_Parameter);
	    panel_parameter.add(textfield_Parameter);
	    
	    panel_reftype.add(label_RefTypeChoice);
	    panel_reftype.add(combobox_RefTypeChoice);
	    
	    panel_refid.add(label_ReferenceId);
	    panel_refid.add(textfield_ReferenceId);
	    
	    panel_action.add(label_Action);
	    panel_action.add(textfield_Action);
	    
	    panel_random.add(label_RandomChoice);
	    panel_random.add(combobox_RandomChoice);	
	    
	    panel_saveSeg.add(button_saveSeg);
	    
	    panel_paramete2.add(label_Paramete2);
	    panel_paramete2.add(textfield_Paramete2);
	    
	    panel_goTo.add(button_goTo);	    
	    
	    //---
	    
	    panel_actUnkn1.add(label_actUnkn1);
	    panel_actUnkn1.add(textfield_actUnkn1);
	    panel_actUnkn2.add(label_actUnkn2);
	    panel_actUnkn2.add(textfield_actUnkn2);
	    panel_actUnkn3.add(label_actUnkn3);
	    panel_actUnkn3.add(textfield_actUnkn3);
	    panel_actUnkn4.add(label_actUnkn4);
	    panel_actUnkn4.add(textfield_actUnkn4);
	    panel_actUnkn5.add(label_actUnkn5);
	    panel_actUnkn5.add(textfield_actUnkn5);
	    
	    panel_saveSeg2.add(button_saveSeg2);
	    
	    //---
	    
	    panel_nodeOffset.add(label_nodeOffset);
	    
	    panel_nodeSegCount.add(label_nodeSegCount);
	    
	    panel_nodeID.add(label_nodeID);
	    panel_nodeID.add(textfield_nodeID);	
	    
	    panel_saveNode.add(button_saveNode);
	    
	    panel_nodeTitleLabel.add(label_nodeTitle);
	    panel_nodeTitleTextField.add(textfield_nodeTitle);	    
	    
	    //---
	    
	    sub3Panel1.add(combobox_searchFunc);	    
	    sub3Panel1.add(textfield_searchFunc);	    
	    sub3Panel1.add(button_searchFunc);
	    sub3Panel1.add(button_advancedSearchFunc);
	    
	    //---
	    
	    sub3Panel2.add(label_searchFunc);
	    label_searchFunc.setVisible(false);
	    
	    //---
	    
	    sub2Panel1.add(panel_nodeTitleLabel);
	    sub2Panel1.add(panel_nodeTitleTextField);
	    sub2Panel1.add(panel_nodeOffset);
	    sub2Panel1.add(panel_nodeSegCount);
	    sub2Panel1.add(panel_nodeID);
	    sub2Panel1.add(panel_saveNode);
	    
	    sub2Panel2.add(checkbox_lastSeg);
	    sub2Panel2.add(panel_random);
	    sub2Panel2.add(panel_interrupt);
	    sub2Panel2.add(panel_if);
	    sub2Panel2.add(panel_check);
	    sub2Panel2.add(panel_parameter);
	    sub2Panel2.add(panel_reftype);
	    sub2Panel2.add(panel_refid);
	    sub2Panel2.add(panel_action);
	    sub2Panel2.add(panel_saveSeg);
	    sub2Panel2.add(panel_paramete2);
	    sub2Panel2.add(panel_goTo);	    
	    for (short i=0;i<3;i++) {
	    	sub2Panel2.add(new JPanel());
	    }
	    
	    sub2Panel3.add(panel_actUnkn1);
	    sub2Panel3.add(panel_actUnkn2);
	    sub2Panel3.add(panel_actUnkn3);
	    sub2Panel3.add(panel_actUnkn4);
	    sub2Panel3.add(panel_actUnkn5);
	    sub2Panel3.add(panel_saveSeg2);

	    mainPanel2.addTab("Node Settings:", sub2Panel1);
	    mainPanel2.addTab("Segment Settings:", sub2Panel2);
	    mainPanel2.addTab("Segment Unknowns:", sub2Panel3);	    

	    splitPane.add(mainPanel1);
	    splitPane.add(mainPanel2);
	    
	    mainPanel3.add(sub3Panel2);
	    mainPanel3.add(sub3Panel1);
	    
	    splitPane2.add(mainPanel3);
	    splitPane2.add(splitPane);
	    
	    //--
	    	
		add(splitPane2);
		setVisible(true);			//Finally
	}
//Checks Actions
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("NewFile")) {
        	selectedNode = null;
        	selectedSeg = null;
        	refreshNodeList();
        	headerA = "54484b002800";
        	headerB = "d87afb990000000020000000000000002000000000000000";
        	if (currentFile.equals("")) {
    			latestVersion = true;
        	} else {
    			latestVersion = false;
        	}
        	refreshTitle();
        } else if (cmd.equals("OpenFile")) {
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal==0) {
            	currentFile = fileChooser.getSelectedFile().getAbsolutePath();
        		String fileName = fileChooser.getSelectedFile().getName();
        		currentTitle = currentFile.substring(0, currentFile.length() - fileName.length()) + fileName.substring(0, fileName.length() - 4) + ".thktit";
        		//System.out.println(currentTitle);
            	this.setTitle("Loading File... - MHWI THK Editor");
            	hexData = conv.FileToHex(currentFile);
                if (hexData.equals("y")) {
                	JOptionPane.showMessageDialog(null, "ERROR: The selected file is not a .THK");
                	currentFile = "";
                	hexData = "";
                	refreshTitle();
                } else if (hexData.equals("z")) {
                	JOptionPane.showMessageDialog(null, "ERROR: The file was not found");
                	currentFile = "";
                	hexData = "";
                	refreshTitle();
                } else {
                	saveToFile = currentFile;
                	headerA = hexData.substring(0, 12);
                	headerB = hexData.substring(16, 64);
                	selectedNode = loadFile();
                	if ((selectedNode!=null)&&(selectedNode.firstseg!=null)) {
                    	selectedSeg = selectedNode.firstseg;
                    	String titleholder = conv.getTextFrom("THKTIT", currentTitle);
                    	if (!titleholder.equals("END")) {
                    		loadNames(titleholder);
                    	}
                	} else if (selectedNode==null) {
                		selectedNode = null;
                		selectedSeg = null;
                    	JOptionPane.showMessageDialog(null, "The selected .thk file contains no nodes");                		
                	}
                	refreshNodeList();
                	latestVersion = true;
                	refreshTitle();
                }
            }
        } else if (cmd.equals("SaveFile")) {
        	if (!currentFile.equals("")) {
        		hexData = selectedNode.toString(headerA, headerB);       		
        		conv.HexToFile(hexData, fileChooser.getSelectedFile().getAbsolutePath());
        		conv.writeTextTo(selectedNode.TitletoString(), currentTitle);
        		latestVersion = true;
            	refreshTitle();
        	} else {
            	int returnVal = fileChooser.showSaveDialog(this);
            	if (returnVal==0) {
            		if (selectedNode!=null) {
            			hexData = selectedNode.toString(headerA, headerB);
            		} else {
            			hexData = headerA + "0000" + headerB; //-- Header Used
            		}
                	currentFile = fileChooser.getSelectedFile().getAbsolutePath();       		
            		String fileName = fileChooser.getSelectedFile().getName();
            		currentTitle = currentFile.substring(0, currentFile.length() - fileName.length()) + fileName.substring(0, fileName.length() - 4) + ".thktit";        		
                	this.setTitle("Saving File... - MHWI THK Editor");
            		conv.HexToFile(hexData, currentFile);
                	if (titleSaveEnable.getState()) {
                		conv.writeTextTo(selectedNode.TitletoString(), currentTitle);
                	}
            		latestVersion = true;
                	refreshTitle();
            	} 
        	}
        } else if (cmd.equals("SaveFileAs")) {
        	int returnVal = fileChooser.showSaveDialog(this);
        	if (returnVal==0) {
        		if (selectedNode!=null) {
        			hexData = selectedNode.toString(headerA, headerB);
        		} else {
        			hexData = headerA + "0000" + headerB; //-- Header Used
        		}
            	currentFile = fileChooser.getSelectedFile().getAbsolutePath();
        		String fileName = fileChooser.getSelectedFile().getName();
            	String extension = currentFile.substring(currentFile.length()-4, currentFile.length());
            	if (!(extension.equals(".thk"))) {
            		currentFile += ".thk";
            		fileName += ".thk";
            	}
        		currentTitle = currentFile.substring(0, currentFile.length() - fileName.length()) + fileName.substring(0, fileName.length() - 4) + ".thktit";        		
            	this.setTitle("Saving File... - MHWI THK Editor");
        		conv.HexToFile(hexData, currentFile);
            	if (titleSaveEnable.getState()) {
            		conv.writeTextTo(selectedNode.TitletoString(), currentTitle);
            	}
        		latestVersion = true;
            	refreshTitle();
        	} 
        } else if (cmd.equals("SaveSeg")) {
        	if ((selectedNode!=null)&&(selectedSeg!=null)) {
	        	Segment curSeg = selectedNode.firstseg;
        		/*if ((combobox_SegInterruptChoice.getSelectedIndex()==combobox_SegInterruptChoice.getModel().getSize()-1)
        				||(combobox_SegIfChoice.getSelectedIndex()==combobox_SegIfChoice.getModel().getSize()-1)
        				||(combobox_RandomChoice.getSelectedIndex()==combobox_RandomChoice.getModel().getSize()-1)) {
                	JOptionPane.showMessageDialog(null, "Unknown values can not be saved!");
        		} else*/ if ((!conv.stringAllInts(textfield_Parameter.getText()))
        				||(!conv.stringAllInts(textfield_Paramete2.getText()))       				
        				||(!conv.stringAllInts(textfield_ReferenceId.getText()))
        				||(!conv.stringAllInts(textfield_Action.getText()))
        				||(!conv.stringAllInts(textfield_actUnkn1.getText()))
        				||(!conv.stringAllInts(textfield_actUnkn2.getText()))   
        				||(!conv.stringAllInts(textfield_actUnkn3.getText()))
        				||(!conv.stringAllInts(textfield_actUnkn4.getText()))
        				||(!conv.stringAllInts(textfield_actUnkn5.getText()))) {
                	JOptionPane.showMessageDialog(null, "Only enter integers into fields!");
        		} else {
        			if ((combobox_SegInterruptChoice.getSelectedIndex()==combobox_SegInterruptChoice.getModel().getSize()-1)
            				||(combobox_SegIfChoice.getSelectedIndex()==combobox_SegIfChoice.getModel().getSize()-1)
            				||(combobox_RandomChoice.getSelectedIndex()==combobox_RandomChoice.getModel().getSize()-1)) {
                    	JOptionPane.showMessageDialog(null, "Unknown field(s) detected. The values will remain unchanged.");
            		}
		        	for (int i=0;i<list_SegSelect.getSelectedIndex();i++) {
		        		if (curSeg.next!=null) {
		        			curSeg = curSeg.next;
		        		}
		        	}
					if (checkbox_lastSeg.isSelected()) {
						curSeg.nodeEnd = true;
					} else {
						curSeg.nodeEnd = false;
					}
		        	if (combobox_SegCheckChoice.isVisible()) {
		        		switch ((String) combobox_SegCheckChoice.getSelectedItem()) {
		        			case "NULL (00)":
								curSeg.check = "0000";
								break;
		        			case "Nothing (02)":
								curSeg.check = "0200";
								break;
		        			case "Set Target (Entity) (03)":
								curSeg.check = "0300";
								break;
		        			case "Set Target (Area) (05)":
								curSeg.check = "0500";
								break;
		        			case "IF (Total) Dist. < (06)":
								curSeg.check = "0600";
								break;
		        			case "IF (Total) Dist. > (07)":
								curSeg.check = "0700";
								break;
		        			case "IF (Hori.) Dist. < (08)":
								curSeg.check = "0800";
								break;
		        			case "IF (Hori.) Dist. > (09)":
								curSeg.check = "0900";
								break;
		        			case "IF Height (0F)":
								curSeg.check = "0f00";
								break;
		        			case "IF Rotation (16)":
								curSeg.check = "1600";
								break;
		        			case "IF Enraged (1E)":
								curSeg.check = "1e00";
								break;
		        			case "IF Fatigued (1F)":
								curSeg.check = "1f00";
								break;
		        			case "IF Poisoned (20)":
								curSeg.check = "2000";
								break;		        			
		        			case "IF Mounted Part = (24)":
								curSeg.check = "2400";
								break;		        			
		        			case "IF Mounted (25)":
								curSeg.check = "2500";
								break;		       
		        			case "IF HP < (Para2)% (2C)":
								curSeg.check = "2c00";
								break;		 
		        			case "IF Target's Status (2E)":
								curSeg.check = "2e00";
								break;		        			
		        			case "IF Quest ID (37)":
								curSeg.check = "3700";
								break;
		        			case "Alt. Target (?) (5E)":
								curSeg.check = "b800";
								break;								
		        			case "IF Nearest Meat > (6B)":
								curSeg.check = "6b00";
								break;
		        			case "IF Flying (70)":
								curSeg.check = "7000";
								break;
		        			case "IF Part P1 Broken (76)":
								curSeg.check = "7600";
								break;	
		        			case "Chng Area/THK17 (AE)":
								curSeg.check = "ae00";
								break;	
		        			case "IF Quest Rank (B8)":
								curSeg.check = "b800";
								break;
		        			case "Area Check (BF)":
								curSeg.check = "bf00";
								break;		        			
		        			case "Set Int A (80)":
								curSeg.check = "8000";
								break;
		        			case "Set Int B (81)":
								curSeg.check = "8100";
								break;		        			
		        			case "Set Int C (82)":
								curSeg.check = "8200";
								break;			        			
		        			case "Set Int D (83)":
								curSeg.check = "8300";
								break;		        			
		        			case "Set Int E (84)":
								curSeg.check = "8400";
								break;		        			
		        			case "Set Int F (85)":
								curSeg.check = "8500";
								break;			        			
		        			case "Set Int G (86)":
								curSeg.check = "8600";
								break;			        			
		        			case "Set Int H (87)":
								curSeg.check = "8700";
								break;			        			
		        			case "Set Int I (88)":
								curSeg.check = "8800";
								break;			        			
		        			case "Set Int J (89)":
								curSeg.check = "8900";
								break;			        			
		        			case "Set Int K (8A)":
								curSeg.check = "8a00";
								break;			        			
		        			case "Set Int L (8B)":
								curSeg.check = "8b00";
								break;			        			
		        			case "Set Int M (8C)":
								curSeg.check = "8c00";
								break;			        			
		        			case "Set Int N (8D)":
								curSeg.check = "8d00";
								break;			        			
		        			case "Set Int O (8E)":
								curSeg.check = "8e00";
								break;			        			
		        			case "Set Int P (8F)":
								curSeg.check = "8f00";
								break;			        			
		        			case "Set Int Q (90)":
								curSeg.check = "9000";
								break;			        			
		        			case "Set Int R (91)":
								curSeg.check = "9100";
								break;			        			
		        			case "Set Int S (92)":
								curSeg.check = "9200";
								break;			        			
		        			case "Set Int T (93)":
								curSeg.check = "9300";
								break;			        			
		        			case "Check Int A (94)":
								curSeg.check = "9400";
								break;	
		        			case "Check Int B (95)":
								curSeg.check = "9500";
								break;
		        			case "Check Int C (96)":
								curSeg.check = "9600";
								break;			        			
		        			case "Check Int D (97)":
								curSeg.check = "9700";
								break;		        			
		        			case "Check Int E (98)":
								curSeg.check = "9800";
								break;		        			
		        			case "Check Int F (99)":
								curSeg.check = "9900";
								break;			        			
		        			case "Check Int G (9A)":
								curSeg.check = "9a00";
								break;			        			
		        			case "Check Int H (9B)":
								curSeg.check = "9b00";
								break;			        			
		        			case "Check Int I (9C)":
								curSeg.check = "9c00";
								break;			        			
		        			case "Check Int J (9D)":
								curSeg.check = "9d00";
								break;			        			
		        			case "Check Int K (9E)":
								curSeg.check = "9e00";
								break;			        			
		        			case "Check Int L (9F)":
								curSeg.check = "9f00";
								break;			        			
		        			case "Check Int M (A0)":
								curSeg.check = "a000";
								break;			        			
		        			case "Check Int N (A1)":
								curSeg.check = "a100";
								break;			        			
		        			case "Check Int O (A2)":
								curSeg.check = "a200";
								break;			        			
		        			case "Check Int P (A3)":
								curSeg.check = "a300";
								break;			        			
		        			case "Check Int Q (A4)":
								curSeg.check = "a400";
								break;			        			
		        			case "Check Int R (A5)":
								curSeg.check = "a500";
								break;			        			
		        			case "Check Int S (A6)":
								curSeg.check = "a600";
								break;			        			
		        			case "Check Int T (A7)":
								curSeg.check = "a700";
								break;		
		        			default:
								if (PromptCustomCheck) {	//Newest addition
			        				String newCheck = JOptionPane.showInputDialog("Enter a hex value for the custom check");
									if ((newCheck!=null)&&(newCheck.length()<=4)) {
										newCheck = newCheck.toLowerCase();
										if (conv.stringAllIntsHex(newCheck)) {
											if (newCheck.length()<2) {
												newCheck = "0" + newCheck;
											}
											for (int i=newCheck.length();i<4;i++) {
												newCheck = newCheck + "0";
											}
											newCheck = revOrd(newCheck);
											curSeg.check = newCheck;
										} else {
						                	curSeg.check = "0000";									
										}
									}
								}
								break;
		        		}
		        	}
		        	if (combobox_SegInterruptChoice.isVisible()) {
		        		if (combobox_SegInterruptChoice.getSelectedIndex()==0) {
							curSeg.interruptType = "00";
		        		} else if (combobox_SegInterruptChoice.getSelectedIndex()==1) {
							curSeg.interruptType = "04";		        			
		        		} else if (combobox_SegInterruptChoice.getSelectedIndex()==2) {
							curSeg.interruptType = "08";		        			
		        		} else if (combobox_SegInterruptChoice.getSelectedIndex()==3) {
							curSeg.interruptType = "80";		        			
		        		}
		        	}  
		        	if (combobox_SegIfChoice.isVisible()) {
		        		if (combobox_SegIfChoice.getSelectedIndex()==0) {
							curSeg.ifCon = "00";		        			
		        		} else if (combobox_SegIfChoice.getSelectedIndex()==1) {
		        			curSeg.ifCon = "01";
		        		} else if (combobox_SegIfChoice.getSelectedIndex()==2) {
		        			curSeg.ifCon = "02";		        			
		        		} else if (combobox_SegIfChoice.getSelectedIndex()==3) {
		        			curSeg.ifCon = "04";		        			
		        		} else if (combobox_SegIfChoice.getSelectedIndex()==4) {
		        			curSeg.ifCon = "08";		        			
		        		} else if (combobox_SegIfChoice.getSelectedIndex()==5) {
		        			curSeg.ifCon = "10";		        			
		        		}
		        	}
		        	if (textfield_Parameter.isVisible()) {
		        		curSeg.parameter = Integer.parseInt(textfield_Parameter.getText());
		        	}
		        	if (textfield_Paramete2.isVisible()) {
		        		curSeg.paramete2 = Integer.parseInt(textfield_Paramete2.getText());
		        	}
		        	if (combobox_RefTypeChoice.isVisible()) {
		        		curSeg.referenceType = combobox_RefTypeChoice.getSelectedIndex();
		        	}		        	
		        	if (textfield_ReferenceId.isVisible()) {
		        		curSeg.referenceID = Integer.parseInt(textfield_ReferenceId.getText());
		        	}
		        	if (textfield_Action.isVisible()) {
		        		curSeg.action = Integer.parseInt(textfield_Action.getText());
		        	}
		        	if (combobox_RandomChoice.isVisible()) {
		        		if (combobox_RandomChoice.getSelectedIndex()==0) {
							curSeg.choice = "00";		        			
		        		} else if (combobox_RandomChoice.getSelectedIndex()==1) {
							curSeg.choice = "40";
		        		} else if (combobox_RandomChoice.getSelectedIndex()==2) {
							curSeg.choice = "c0";		        			
		        		} else if (combobox_RandomChoice.getSelectedIndex()==3) {
							curSeg.choice = "80";		        			
		        		}
		        	}		        	
	        		curSeg.actUnkn1 = Integer.parseInt(textfield_actUnkn1.getText());
	        		curSeg.actUnkn2 = Integer.parseInt(textfield_actUnkn2.getText());
	        		curSeg.actUnkn3 = Integer.parseInt(textfield_actUnkn3.getText());
	        		curSeg.actUnkn4 = Integer.parseInt(textfield_actUnkn4.getText());
	        		curSeg.actUnkn5 = Integer.parseInt(textfield_actUnkn5.getText());
		        	
	        		int curListSeg = list_SegSelect.getSelectedIndex();
		        	JOptionPane.showMessageDialog(null, "Changes were saved sucessfully!");
		        	latestVersion = false;
		        	refreshSegList();
		        	list_SegSelect.setSelectedIndex(curListSeg);
		        	refreshSegTab();
		        	refreshTitle();
        		}
        	} else {
            	JOptionPane.showMessageDialog(null, "Select an existing segment!");        		
        	}
        } else if (cmd.equals("SaveNode")) {
        	if (selectedNode==null) {
            	JOptionPane.showMessageDialog(null, "Select an existing node!");       		
        	} else if ((!conv.stringAllInts(textfield_nodeID.getText()))) {
            	JOptionPane.showMessageDialog(null, "Only enter integers into the ID field!");
        	} else {
        		int newIndex = Integer.parseInt(textfield_nodeID.getText());
        		boolean unique = true;
        		Node curNode = selectedNode.GetFirstNode();
        		while (curNode!=null) {
        			if ((curNode.id == newIndex)&&(curNode.index != selectedNode.index)&&(curNode.id != 0)) {
        				unique = false;
        				newIndex = curNode.index-1;
        				break;
        			}
        			curNode = curNode.next;
        		}
        		if (unique) {
	        		selectedNode.id = Integer.parseInt(textfield_nodeID.getText());
	    			selectedNode.title = textfield_nodeTitle.getText();
	    			latestVersion = false;
	    			JOptionPane.showMessageDialog(null, "Changes were saved sucessfully!");
	    			int nodeListIndex = list_NodeSelect.getSelectedIndex();
	    			refreshNodeList();
		        	list_NodeSelect.setSelectedIndex(nodeListIndex);
	    			refreshTitle();
        		} else {
	    			JOptionPane.showMessageDialog(null, "The ID is already in use! (Node " + newIndex + ")");
        		}
    		}
        } else if (cmd.equals("AddNode")) {
        	if (selectedNode!=null) {
	        	while (selectedNode.next!=null) {
	        		selectedNode = selectedNode.next;
	        	}
	        	Node newNode = new Node(selectedNode, (selectedNode.index+1), 0, 0, 0);
	        	selectedNode.next = newNode;
	        	selectedNode = newNode;
	        	selectedSeg = newNode.newSegment();
	        	selectedSeg.nodeEnd = true;
	        	selectedNode.calcOffset();
	        	int holder = selectedNode.index-1;
	        	refreshNodeList();
	        	list_NodeSelect.setSelectedIndex(holder);
	        	refreshNodeTab();
	        	refreshSegTab();
        	} else {
	        	Node newNode = new Node(null, 1, 0, 0, 0);
	        	selectedNode = newNode;
	        	selectedSeg = newNode.newSegment();
	        	selectedSeg.nodeEnd = true;
	        	int holder = selectedNode.index-1;
	        	refreshNodeList();
	        	list_NodeSelect.setSelectedIndex(holder);
	        	refreshNodeTab();
	        	refreshSegTab();
    			latestVersion = false;
	        	refreshTitle();
        	}
        } else if (cmd.equals("DeleteNode")) {
        	if (selectedNode!=null) { 
	        	int deletingNode = list_NodeSelect.getSelectedIndex()+1;
	        	if (deletingNode!=-1) {
	            	Node curNode = selectedNode.GetFirstNode();
	        		while (curNode.index!=deletingNode) {
	        			if (curNode.next!=null) {
	        				curNode = curNode.next;
	        			}
	        		}
	        		Node prevNode = curNode.prev;
	        		Node nextNode = curNode.next;
	        		if (nextNode!=null) {
	        			if (prevNode!=null) {
		        			prevNode.next = nextNode;
		        			nextNode.prev = prevNode;
	        			} else {
	        				nextNode.prev = null;
	        			}
	        			selectedNode = nextNode.GetFirstNode();
	        		} else if (prevNode!=null) {
	        			prevNode.next = null;
	        			selectedNode = prevNode.GetFirstNode();
	        		} else {
	        			selectedNode = null;
	        		}
	        		if (selectedNode!=null) {
	        			int i = 1;
	        			curNode = selectedNode;
	        			while (curNode.next!=null) {
	        				curNode.index = i;
	        				curNode = curNode.next;
	        				i++;
	        			}
	    				curNode.index = i;
	        		}
	        		shiftNodeRef((deletingNode-1), (deletingNode-1)-(list_NodeSelect.getModel().getSize()-1), 1);
	        		refreshNodeList();    			
	    			if (selectedNode!=null) {
	    				selectedNode.calcOffset();
	    			}
	    			latestVersion = false;		        	
	    			refreshTitle();
	        	}
        	}
        } else if (cmd.equals("ShiftNodeUp")) {
        	if (selectedNode!=null) {
        		int holder = 0;
        		if (selectedNode.prev!=null) {
        			Node nodeA = selectedNode;
        			Node nodeB = selectedNode.prev;
        			nodeB.index += 1;
        			nodeA.index -= 1;
        			if (nodeB.prev!=null) {
        				nodeB.prev.next = nodeA;
        			}
        			if (nodeA.next!=null) {
        				nodeA.next.prev = nodeB;
        			}
        			nodeA.prev = nodeB.prev;
        			nodeB.next = nodeA.next;        			
        			nodeB.prev = nodeA;
        			nodeA.next = nodeB;
        			selectedNode = nodeA;
        			holder = nodeA.index-1;
            		shiftNodeRef(holder+1,1,0);
            		nodeA.calcOffset();
            		nodeB.calcOffset();
        		} 			
        		refreshNodeList();
	        	list_NodeSelect.setSelectedIndex(holder);
	        	refreshNodeTab();
	        	refreshSegTab();
    			latestVersion = false;
	        	refreshTitle();
        	}
        } else if (cmd.equals("ShiftNodeMass")) {
        	Node nodeA = selectedNode;
        	Node nodeB = selectedNode.GetFirstNode();
        	int nodeALoca = list_NodeSelect.getSelectedIndex();
        	if (selectedNode!=null) {
        		String enteredIndex = JOptionPane.showInputDialog("Enter the index to shift the selected node to");
        		if (conv.stringAllInts(enteredIndex)) {
        			int nodeBLoca = Integer.parseInt(enteredIndex);
        			for (int i=0;i<nodeBLoca;i++) {
        				if (nodeB.next!=null) {	
        					nodeB = nodeB.next;
        				}
        			}
        			if ((nodeALoca>nodeBLoca)&&(nodeBLoca>=0)) {
        				nodeA.prev.next = nodeA.next;
        				if (nodeA.next != null) {
        					nodeA.next.prev = nodeA.prev;
        				}
        				if (nodeB.prev != null) {
        					nodeB.prev.next = nodeA;
        				}
        				nodeA.next = nodeB;
        				nodeA.prev = nodeB.prev;
        				nodeB.prev = nodeA;
        				nodeA.index = nodeBLoca+1;
        				for (int i=nodeA.index-1;i<nodeALoca;i++) {
        					nodeB.index++;
        					nodeB = nodeB.next;
        				}
                		shiftNodeRef(nodeALoca,nodeALoca-nodeBLoca,0);
                		nodeA.calcAllOffset();
                		refreshNodeList();        			
            			list_NodeSelect.setSelectedIndex(nodeBLoca);
        	        	refreshNodeTab();
        	        	refreshSegTab();
            			latestVersion = false;
        	        	refreshTitle();
        			} else if ((nodeALoca<nodeBLoca)&&(nodeBLoca<list_NodeSelect.getModel().getSize())) {
        				nodeA.next.prev = nodeA.prev;
        				if (nodeA.prev != null) {
        					nodeA.prev.next = nodeA.next;
        				}
        				if (nodeB.next != null) {
        					nodeB.next.prev = nodeA;
        				}
        				nodeA.prev = nodeB;
        				nodeA.next = nodeB.next;
        				nodeB.next = nodeA;
        				nodeA.index = nodeBLoca+1;
        				for (int i=nodeA.index-1;i>nodeALoca;i--) {
        					nodeB.index--;
        					nodeB = nodeB.prev;
        				}
                		shiftNodeRef(nodeALoca,nodeALoca-nodeBLoca,0);
                		nodeA.calcAllOffset();
                		refreshNodeList();        			
            			list_NodeSelect.setSelectedIndex(nodeBLoca);
        	        	refreshNodeTab();
        	        	refreshSegTab();
            			latestVersion = false;
        	        	refreshTitle();
        			}
        		} else if (enteredIndex != null) {
                	JOptionPane.showMessageDialog(null, "Please only enter integers into the field");
        		}
        	}
        } else if (cmd.equals("ShiftNodeDown")) {
        	if (selectedNode!=null) {
        		int holder = 0;
        		if (selectedNode.next!=null) {
        			Node nodeA = selectedNode;
        			Node nodeB = selectedNode.next;
        			nodeB.index -= 1;
        			nodeA.index += 1;
        			if (nodeB.next!=null) {
        				nodeB.next.prev = nodeA;
        			}
        			if (nodeA.prev!=null) {
            			nodeA.prev.next = nodeB;
        			}
        			nodeA.next = nodeB.next;
        			nodeB.prev = nodeA.prev;
        			nodeB.next = nodeA;
        			nodeA.prev = nodeB;
        			selectedNode = nodeA;
        			holder = nodeA.index-1;
            		shiftNodeRef(holder-1,-1, 0);
        			nodeA.calcOffset();
        			nodeB.calcOffset();
            		refreshNodeList();
    	        	list_NodeSelect.setSelectedIndex(holder);
    	        	refreshNodeTab();
    	        	refreshSegTab();
        			latestVersion = false;
    	        	refreshTitle();
        		}
        	}
        } else if (cmd.equals("CloneNode")) {
    		if (selectedNode!=null) {
	        	Node sourceNode = selectedNode;
    			Segment sourceSeg = selectedNode.firstseg;
	        	while (selectedNode.next!=null) {
	        		selectedNode = selectedNode.next;
	        	}
	        	Node newNode = new Node(selectedNode, (list_NodeSelect.getModel().getSize()+1), 0, 0, 0);
	        	System.out.println(newNode.index);
	        	Segment newSeg = null;
	        	selectedNode.next = newNode;
	        	selectedNode = newNode;
	        	
	        	if ((sourceNode.title.length()>6)&&
	        		(sourceNode.title.substring(sourceNode.title.length()-6, sourceNode.title.length()).equals("- COPY"))) {
	        		newNode.title = sourceNode.title;		        	
		        } else {
		        	if ((sourceNode.title.equals(""))) {
				        newNode.title = "Node" + (sourceNode.index-1) + " - COPY";
			        } else {
				        newNode.title = sourceNode.title + " - COPY";
			        }
		        }
	        	for (int i=0;i<sourceNode.segcount;i++) {
	        		newSeg = newNode.newSegment();
	        		if (sourceSeg.nodeEnd) {
	        			newSeg.nodeEnd = true;
	        		} else {
	        			newSeg.choice = sourceSeg.choice;
	        			newSeg.interruptType = sourceSeg.interruptType;
	        			newSeg.ifCon = sourceSeg.ifCon;
	        			newSeg.check = sourceSeg.check;
	        			newSeg.parameter = sourceSeg.parameter;
	        			newSeg.paramete2 = sourceSeg.paramete2;
	        			newSeg.referenceType = sourceSeg.referenceType;
	        			newSeg.referenceID = sourceSeg.referenceID;
	        			newSeg.action = sourceSeg.action;
	        			newSeg.actUnkn1 = sourceSeg.actUnkn1;
	        			newSeg.actUnkn2 = sourceSeg.actUnkn2;
	        			newSeg.actUnkn3 = sourceSeg.actUnkn3;
	        			newSeg.actUnkn4 = sourceSeg.actUnkn4;
	        			newSeg.actUnkn5 = sourceSeg.actUnkn5;
	        			newSeg.unknGap1 = sourceSeg.unknGap1;
	        			newSeg.unknGap2 = sourceSeg.unknGap2;
	        			newSeg.unknGap3 = sourceSeg.unknGap3;
	        			newSeg.unknGap4 = sourceSeg.unknGap4;
	        			newSeg.unknGap5 = sourceSeg.unknGap5;
	        			newSeg.unknGap6 = sourceSeg.unknGap6;
	        			newSeg.unknGap7 = sourceSeg.unknGap7;
	        		}
	        		if (sourceSeg.next!=null) {
	        			sourceSeg = sourceSeg.next;
	        		}
	        	}       	
	        	selectedNode.calcOffset();
	        	selectedSeg = selectedNode.firstseg;	        	
	        	int holder = selectedNode.index-1;
	        	refreshNodeList();
	        	list_NodeSelect.setSelectedIndex(holder);
	        	refreshNodeTab();
	        	refreshSegTab();
    		}
    	} else if (cmd.equals("AddSeg")) {
        	if (selectedNode!=null) {
        		Segment nSeg = selectedNode.newSegment();
        		if (selectedSeg==null) {
        			selectedSeg = nSeg;
        		}
        		selectedNode.calcOffset();
        		refreshSegList();
        		refreshNodeTab();
	        	list_SegSelect.setSelectedIndex(list_SegSelect.getModel().getSize()-1);
	        	refreshSegTab();
    			latestVersion = false;
	        	refreshTitle();
        	}
        } else if (cmd.equals("DeleteSeg")) {
        	if ((selectedSeg!=null)&&(selectedNode!=null)&&(selectedNode.segcount>1)) {
        		int loca = list_SegSelect.getSelectedIndex();
        		Segment curSeg = selectedNode.firstseg;
        		for (int i=0;i<loca-1;i++) {
        			curSeg = curSeg.next;
        		}
        		Segment prevSeg = null;
        		Segment delSeg = null;
        		Segment nextSeg = null;
        		if ((loca!=0)&&(curSeg.next.next!=null)) {
        			prevSeg = curSeg;
        			delSeg = curSeg.next;
        			nextSeg = delSeg.next;
        		} else if (loca==0) {
        			delSeg = curSeg;
        			nextSeg = delSeg.next;
        		} else if (curSeg.next.next==null) {
        			prevSeg = curSeg;
        			delSeg = curSeg.next;		
        		}
        		if ((prevSeg==null)&&(nextSeg==null)) {
        			selectedSeg=null;
        			selectedNode.firstseg=null;
        		} else if (prevSeg==null) {
        			selectedNode.firstseg=nextSeg;
        		} else if (nextSeg==null) {
        			prevSeg.next=null;
        		} else {
        			prevSeg.next = nextSeg;
        		}
            	selectedNode.segcount-=1;
            	selectedNode.calcOffset();
            	refreshNodeTab();
            	refreshSegList();
	        	list_SegSelect.setSelectedIndex(loca-1);
	        	refreshSegTab();
    			latestVersion = false;
	        	refreshTitle();
        	}  else if ((selectedSeg!=null)&&(selectedNode!=null)) {
    			JOptionPane.showMessageDialog(null, "At least one segment must remain in the node!");         		
        	}
        } else if (cmd.equals("ShiftSegUp")) {
        	if ((selectedSeg!=null)&&(selectedNode!=null)&&(list_SegSelect.getSelectedIndex()>0)) {
        		int loca = list_SegSelect.getSelectedIndex();
        		Segment curSeg = selectedNode.firstseg;
        		for (int i=0;i<loca-2;i++) {
        			curSeg = curSeg.next;
        		}
        		Segment prevSeg1 = null;
        		Segment prevSeg2 = null;
        		Segment shiftSeg = null;
        		if (curSeg.next!=null) {
	        		if ((curSeg.next.next!=null)&&(loca!=1)) {
	        			prevSeg1 = curSeg;
	        			prevSeg2 = curSeg.next;
	        			shiftSeg = curSeg.next.next;
	        			prevSeg2.next = shiftSeg.next;
	        			shiftSeg.next = prevSeg2;
	        			prevSeg1.next = shiftSeg;      			
	        		} else {
	        			prevSeg2 = curSeg;
	        			shiftSeg = curSeg.next;
	        			prevSeg2.next = shiftSeg.next;
	        			shiftSeg.next = prevSeg2;
	        			selectedNode.firstseg = shiftSeg;
	        			selectedSeg = shiftSeg;
	        		}
        		}
        		selectedNode.calcOffset();
        		refreshSegList();
	        	list_SegSelect.setSelectedIndex(loca-1);
	        	refreshSegTab();
    			latestVersion = false;
	        	refreshTitle();
        	}
        } else if (cmd.equals("ShiftSegMass")) {
        	Segment segA = selectedSeg;
        	Segment segB = selectedNode.firstseg;
        	Segment curSeg = selectedNode.firstseg;
        	Segment segAPrev = null;
        	Segment segBPrev = null;
        	int segALoca = list_SegSelect.getSelectedIndex();
        	if (selectedSeg!=null) {
        		String enteredIndex = JOptionPane.showInputDialog("Enter the index to shift the selected segment to");
        		if (conv.stringAllInts(enteredIndex)) {
        			int segBLoca = Integer.parseInt(enteredIndex);
        			int i = 0;
        			while (curSeg!=null) {	
        					if (i==segALoca-1) {
        						segAPrev = curSeg;
        						//System.out.println("segAPrev: " + segAPrev.choice + "/" + segAPrev.interruptType + "/" + segAPrev.ifCon);
        					} else if (i==segBLoca-1) {
        						segBPrev = curSeg;
        						//System.out.println("segBPrev: " + segBPrev.choice + "/" + segBPrev.interruptType + "/" + segBPrev.ifCon);
        					} else if (i==segBLoca) {
        						segB = curSeg;
        						//System.out.println("segB: " + segB.choice + "/" + segB.interruptType + "/" + segB.ifCon);
        					}
        					i++;
        					curSeg = curSeg.next;
        			}
        			if ((segALoca>segBLoca)&&(segBLoca>=0)) {
        				if (segBLoca==0) {
        					selectedNode.firstseg = segA;
        				}
        				if (segAPrev!=null) {
        					segAPrev.next = segA.next;
        				}
        				if (segBPrev!=null) {
        					segBPrev.next = segA;
        				}
        				segA.next = segB;
                		selectedNode.calcOffset();
                		refreshSegList();
        	        	list_SegSelect.setSelectedIndex(segBLoca);
        	        	refreshSegTab();
            			latestVersion = false;
        	        	refreshTitle();
        			} else if ((segALoca<segBLoca)&&(segBLoca<list_SegSelect.getModel().getSize())) {
        				if (segALoca==0) {
        					selectedNode.firstseg = segA.next;
        				} else {
        					segAPrev.next = segA.next;
        				}
        				segA.next = segB.next;
        				segB.next = segA;
                		selectedNode.calcOffset();
                		refreshSegList();
        	        	list_SegSelect.setSelectedIndex(segBLoca);
        	        	refreshSegTab();
            			latestVersion = false;
        	        	refreshTitle();
        			}
        		} else if (enteredIndex != null) {
                	JOptionPane.showMessageDialog(null, "Please only enter integers into the field");
        		}
        	}
        } else if (cmd.equals("ShiftSegDown")) {
        	if ((selectedSeg!=null)&&(selectedNode!=null)&&(list_SegSelect.getSelectedIndex()<list_SegSelect.getModel().getSize()-1)) {
        		int loca = list_SegSelect.getSelectedIndex();
        		Segment curSeg = selectedNode.firstseg;
        		for (int i=0;i<loca-1;i++) {
        			curSeg = curSeg.next;
        		}
        		Segment prevSeg = null;
        		Segment shiftSeg = null;
        		Segment nextSeg = null;
        		if ((curSeg.next.next!=null)&&(loca>0)) {
        			prevSeg = curSeg;
        			shiftSeg = curSeg.next;
        			nextSeg = shiftSeg.next;
        			prevSeg.next = nextSeg;
        			shiftSeg.next = nextSeg.next;
        			nextSeg.next = shiftSeg;
        		} else {
        			shiftSeg = curSeg;
        			nextSeg = curSeg.next;
        			shiftSeg.next = nextSeg.next;
        			nextSeg.next = shiftSeg;
        			selectedNode.firstseg = nextSeg;
        			selectedSeg = nextSeg;
        		}
        		selectedNode.calcOffset();
        		refreshSegList();
	        	list_SegSelect.setSelectedIndex(loca+1);
	        	refreshSegTab();
    			latestVersion = false;
	        	refreshTitle();
        	}
        } else if (cmd.equals("CloneSeg")) {
        	if ((selectedNode!=null)&&(selectedSeg!=null)) {
        		Segment newLastSeg = selectedSeg;
	        	while ((newLastSeg.next!=null)) {
	        		newLastSeg = newLastSeg.next;
	        	}
	        	int segPos = 0;
	        	Segment curSeg = selectedNode.firstseg;
	        	while (curSeg!=selectedSeg) {
	        		segPos++;
	        		curSeg=curSeg.next;
	        	}
        		Segment newSeg = selectedNode.newSegment();
        		Segment sourceSeg = selectedSeg;
        		if (sourceSeg.nodeEnd) {
        			newSeg.nodeEnd = true;
        		} else {
        			newSeg.choice = sourceSeg.choice;
        			newSeg.interruptType = sourceSeg.interruptType;
        			newSeg.ifCon = sourceSeg.ifCon;
        			newSeg.check = sourceSeg.check;
        			newSeg.parameter = sourceSeg.parameter;
        			newSeg.paramete2 = sourceSeg.paramete2;
        			newSeg.referenceType = sourceSeg.referenceType;
        			newSeg.referenceID = sourceSeg.referenceID;
        			newSeg.action = sourceSeg.action;
        			newSeg.actUnkn1 = sourceSeg.actUnkn1;
        			newSeg.actUnkn2 = sourceSeg.actUnkn2;
        			newSeg.actUnkn3 = sourceSeg.actUnkn3;
        			newSeg.actUnkn4 = sourceSeg.actUnkn4;
        			newSeg.actUnkn5 = sourceSeg.actUnkn5;
        			newSeg.unknGap1 = sourceSeg.unknGap1;
        			newSeg.unknGap2 = sourceSeg.unknGap2;
        			newSeg.unknGap3 = sourceSeg.unknGap3;
        			newSeg.unknGap4 = sourceSeg.unknGap4;
        			newSeg.unknGap5 = sourceSeg.unknGap5;
        			newSeg.unknGap6 = sourceSeg.unknGap6;
        			newSeg.unknGap7 = sourceSeg.unknGap7;
        		}
        		newSeg.next = sourceSeg.next;
        		sourceSeg.next = newSeg;
        		newLastSeg.next = null;
        		selectedSeg = newSeg; 
        		selectedNode.calcOffset();
        		refreshSegList();
        		refreshNodeTab();
	        	list_SegSelect.setSelectedIndex(segPos+1);
	        	refreshSegTab();
    			latestVersion = false;
	        	refreshTitle();
        	}
    	} else if (cmd.equals("GoTo")) {
    		if ((selectedSeg!=null)&&(selectedSeg.referenceID>=0)&&(selectedSeg.referenceID<=selectedNode.CountAllNodes(selectedNode.GetFirstNode())-1)) {
    			int targetID = selectedSeg.referenceID;
    			selectedNode = selectedNode.GetFirstNode();
    			for (int i=0;i<targetID;i++) {
    				selectedNode = selectedNode.next;
    			}
				selectedSeg = selectedNode.firstseg;
    			refreshSegTab();
				refreshNodeTab();
	        	list_NodeSelect.setSelectedIndex(targetID);
	        	list_SegSelect.setSelectedIndex(0);
    		} else if (selectedSeg!=null) {
    			JOptionPane.showMessageDialog(null, "The Reference Index is not valid!");
    		}
    	} else if (cmd.equals("Search")) {
    		if (textfield_searchFunc.getText().equals("")) {
    			label_searchFunc.setVisible(false);
    		} else if ((selectedSeg!=null)&&((conv.stringAllInts(textfield_searchFunc.getText())))||((conv.stringAllIntsHex(textfield_searchFunc.getText())))) {
    			label_searchFunc.setVisible(true);
    			int searchVal;
    			try {
    				searchVal = Integer.parseInt(textfield_searchFunc.getText());
    			} catch(Exception NumberFormatException) {
    				searchVal = -1;
    			}
    			int searchValHex;
    			try {
    				searchValHex = Integer.parseInt(/*revOrd(*/textfield_searchFunc.getText()/*)*/, 16);
    			} catch(Exception NumberFormatException) {
    				searchValHex = -1;
    			}
    			Node curNode = selectedNode.GetFirstNode();
    			Segment curSeg = curNode.firstseg;
    			int searchCount = 0;
    			int curCount = 1;
    			boolean preCur = true;
    			int totalNodes = curNode.CountAllNodes(curNode);
    			for (int i=0;i<totalNodes;i++) {		// Counts the # of searched segs
    				while (curSeg!=null) {
    					if (((combobox_searchFunc.getSelectedIndex()==0)&&(curSeg.referenceType==2)&&(curSeg.referenceID==searchVal))||
    						((combobox_searchFunc.getSelectedIndex()==1)&&(curSeg.referenceType==1)&&(curSeg.referenceID==searchVal))||
    						((combobox_searchFunc.getSelectedIndex()==2)&&(curSeg.action==searchVal))||
    						((combobox_searchFunc.getSelectedIndex()==3)&&(Integer.parseInt(revOrd(curSeg.check), 16)==searchValHex))) {
    						if ((checkSearchEXValue(searchMenu.textfield_para1Search, curSeg.parameter))&&
        						(checkSearchEXValue(searchMenu.textfield_para2Search, curSeg.paramete2))&&
        						(checkSearchEXValue(searchMenu.textfield_actUnkn1, curSeg.actUnkn1))&&
        						(checkSearchEXValue(searchMenu.textfield_actUnkn2, curSeg.actUnkn2))&&
        						(checkSearchEXValue(searchMenu.textfield_actUnkn3, curSeg.actUnkn3))&&
        						(checkSearchEXValue(searchMenu.textfield_actUnkn4, curSeg.actUnkn4))&&
            					(checkSearchEXValue(searchMenu.textfield_actUnkn5, curSeg.actUnkn5))) {
	    						searchCount++;
	    						if (preCur) {
	    							curCount++;
	    						}
    						}
    					} else if ((combobox_searchFunc.getSelectedIndex()==4)&&(curNode.id==(searchVal))) {
        					takeToNodeSeg(curNode, curNode.firstseg, 0);
                			label_searchFunc.setVisible(false);
    					}
    					if ((curSeg==selectedSeg)) {
    						preCur = false;					
    					}
    					curSeg = curSeg.next;
    				}
    				if (curNode.next!=null) {
    					curNode = curNode.next;
    				}
    				if (curNode.firstseg!=null) {
    					curSeg = curNode.firstseg;
    				}
    			}
    			if (searchCount>1) {
    				label_searchFunc.setText(searchCount + " results found (" + curCount + "/" + searchCount + ")");
    			} else if (searchCount>0) {
    				label_searchFunc.setText(searchCount + " result found");    				
    			} else {
    				label_searchFunc.setText(searchCount + " results found");      				
    			}
    			curNode = selectedNode;
    			curSeg = selectedSeg.next;
    			int segID = list_SegSelect.getSelectedIndex()+1;
    			while (curNode!=null) {
    				if (curSeg!=null) {
    					if (((combobox_searchFunc.getSelectedIndex()==0)&&(curSeg.referenceType==2)&&(curSeg.referenceID==searchVal))||
    						((combobox_searchFunc.getSelectedIndex()==1)&&(curSeg.referenceType==1)&&(curSeg.referenceID==searchVal))||
    						((combobox_searchFunc.getSelectedIndex()==2)&&(curSeg.action==searchVal))||
    						((combobox_searchFunc.getSelectedIndex()==3)&&(Integer.parseInt(revOrd(curSeg.check), 16)==searchValHex))) {
    						if ((checkSearchEXValue(searchMenu.textfield_para1Search, curSeg.parameter))&&
            					(checkSearchEXValue(searchMenu.textfield_para2Search, curSeg.paramete2))&&
            					(checkSearchEXValue(searchMenu.textfield_actUnkn1, curSeg.actUnkn1))&&
            					(checkSearchEXValue(searchMenu.textfield_actUnkn2, curSeg.actUnkn2))&&
            					(checkSearchEXValue(searchMenu.textfield_actUnkn3, curSeg.actUnkn3))&&
            					(checkSearchEXValue(searchMenu.textfield_actUnkn4, curSeg.actUnkn4))&&
                				(checkSearchEXValue(searchMenu.textfield_actUnkn5, curSeg.actUnkn5))) {
    							takeToNodeSeg(curNode, curSeg, segID);
    							break;
    						} else {
    							segID++;
        						curSeg = curSeg.next;
    						}
    					} else {
    						segID++;
    						curSeg = curSeg.next;
    					}
    				} else {
    					curNode = curNode.next;
    					if (curNode!=null) {
    						curSeg = curNode.firstseg;
    					}
    					segID = 0;
    				}
    			}
    			if ((curNode==null)&&(searchCount!=0)) {
					curNode = selectedNode.GetFirstNode();
					curSeg = curNode.firstseg;
        			takeToNodeSeg(curNode, curSeg, 0);	
    				if ((searchCount!=0)/*&&(combobox_searchFunc.getSelectedIndex()!=4)*/) {
            			label_searchFunc.setVisible(false);
    					takeToNodeSeg(curNode, curSeg, 0);
            			JOptionPane.showMessageDialog(null, "There are no further results in the file!");   					
    				} else {
            			label_searchFunc.setVisible(false);
    					takeToNodeSeg(curNode, curSeg, 0);	
        				JOptionPane.showMessageDialog(null, "No results were found!");
    				}
    			}  			
    		} else if (selectedSeg!=null) {
    			JOptionPane.showMessageDialog(null, "Please only enter integers or hex values into the search field!");   			
    		}
    	} else if (cmd.equals("AdvancedSearch")) {
    		searchMenu.setVisible(false);
    		//System.out.println("Opening Search Menu");
    		searchMenu.setVisible(true);
    	} else if (cmd.equals("About")) {
			JLabel aboutTxt = new JLabel("<html><center>Monser Hunter World: Iceborne THK Editor - Version " + verNum + "<br><br>Created by NackDN, using the information of THK files researched by Fandirus, as well as the rest of the MHW Moddng Community. Please contact @NackDN#7777 to report any bugs.<br><br>Special Thanks to Fandirus, Asterisk, Dave uRrr, Deathcream, Fexty, Miralis, Nekotaga, and Stracker for their research into Monster AI.");
    		JOptionPane.showMessageDialog(null, aboutTxt, "About", JOptionPane.DEFAULT_OPTION);
    	} else if (cmd.equals("Config")) {
    		configMenu.setVisible(false);
    		configMenu.setVisible(true);
    	}
	}
//Checks if an item is changed
	public void itemStateChanged(ItemEvent e) {
		if ((searchMenu.combobox_searchFunc.getSelectedIndex())!=(this.combobox_searchFunc.getSelectedIndex())) {
			searchMenu.combobox_searchFunc.setSelectedIndex(this.combobox_searchFunc.getSelectedIndex());
		}
		if (selectedSeg!=null) {
			if (checkbox_lastSeg.isSelected()) {
				checkbox_lastSeg.setVisible(true);
				combobox_SegCheckChoice.setVisible(false);
				combobox_SegInterruptChoice.setVisible(false);
				combobox_SegIfChoice.setVisible(false);
				textfield_Parameter.setVisible(false);
				textfield_Paramete2.setVisible(false);
				combobox_RefTypeChoice.setVisible(false);	
				textfield_ReferenceId.setVisible(false);
				textfield_Action.setVisible(false);
				combobox_RandomChoice.setVisible(false);
			} else if (combobox_RandomChoice.getSelectedIndex()!=0) {
				checkbox_lastSeg.setVisible(false);
				combobox_SegCheckChoice.setVisible(false);
				combobox_SegInterruptChoice.setVisible(false);
				combobox_SegIfChoice.setVisible(false);
				textfield_Parameter.setVisible(true);
				textfield_Paramete2.setVisible(true);
				combobox_RefTypeChoice.setVisible(true);	
				textfield_ReferenceId.setVisible(true);
				textfield_Action.setVisible(true);
				combobox_RandomChoice.setVisible(true);		
			} else {
				checkbox_lastSeg.setVisible(true);				
				combobox_SegCheckChoice.setVisible(true);
				combobox_SegInterruptChoice.setVisible(true);
				combobox_SegIfChoice.setVisible(true);
				textfield_Parameter.setVisible(true);
				textfield_Paramete2.setVisible(true);				
				combobox_RefTypeChoice.setVisible(true);	
				textfield_ReferenceId.setVisible(true);
				textfield_Action.setVisible(true);
				combobox_RandomChoice.setVisible(true);							
			}
			if ((combobox_SegCheckChoice.getSelectedIndex()!=combobox_SegCheckChoice.getModel().getSize()-1)&(!PromptCustomCheck)) {
				PromptCustomCheck = true;
				combobox_SegCheckChoice.removeItemAt(combobox_SegCheckChoice.getModel().getSize()-1);
				combobox_SegCheckChoice.addItem("Custom");
			}
			//SegCheckChoiceUpToDate = false;
		}
	}
//Refreshes the Node List	
	public void refreshNodeList() {
		listModel_Nodes.clear();
		if (selectedNode!=null) {
			Node curNode = selectedNode.GetFirstNode();
			for (int i=0;i<selectedNode.CountAllNodes(selectedNode.GetFirstNode());i++) {
				if (!curNode.title.equals("")) {
					listModel_Nodes.addElement("Node " + (i) + " - " + curNode.title);
				} else {
					listModel_Nodes.addElement("Node " + (i));					
				}
				if (curNode.next!=null) {
					curNode = curNode.next;
				}
			}
		}
		list_NodeSelect.setSelectedIndex(0);
		refreshSegList();
		refreshNodeTab();
	}
//Refreshes the Seg List
	public void refreshSegList() {
		listModel_Segs.clear();
		String iden = "";
		String ide2 = "";
		String ide3 = "";
		String indent = "";
		int indentAmt = 0;
		if (selectedNode!=null) {
			Segment curSeg = selectedNode.firstseg;
			for (int i=0;i<selectedNode.segcount;i++) {
				if (curSeg!=null) {					
					indent = "";
					for (int j=0;j<indentAmt;j++) {
						indent += "----";
					}
					if (curSeg.nodeEnd) {
						iden = "END NODE";
					} else if (curSeg.ifCon.equals("10")) {
						iden = "INITIAL SEGMENT";
					} else if (curSeg.choice.equals("40")) {
						iden = "CHOICE (" + curSeg.parameter + "%)";
						indentAmt++;
					} else if (curSeg.choice.equals("80")) {
						iden = "LAST CHOICE (" + curSeg.parameter + "%)";
					} else if (curSeg.choice.equals("c0")) {
						iden = "CONTINUE CHOICE (" + curSeg.parameter + "%)";
					} else if (curSeg.ifCon.equals("01")) {
						iden = "END CHOICE";
						if (indentAmt>0) {
							indentAmt--;
							indent = indent.substring(0, indent.length()-4);
						}
					} else if (curSeg.ifCon.equals("02")) {
						iden = "IF";
						indentAmt++;
					} else if (curSeg.ifCon.equals("04")) {
						iden = "ELSE IF";
					} else if (curSeg.ifCon.equals("08")) {
						iden = "END IF";
						if (indentAmt>0) {
							indentAmt--;
							indent = indent.substring(0, indent.length()-4);
						}
					} else {
						iden = "";						
					}
					
					if (curSeg.referenceType==1) {
						ide2 = "EXT. CALL (" + curSeg.referenceID + ")";
					} else if (curSeg.referenceType==2) {
						ide2 = "LOCAL CALL (" + curSeg.referenceID + ")";
					} else if (curSeg.action!=0) {
						ide2 = "ACTION (" + curSeg.action + ")";
					} else {
						ide2 = "";
					}
					
					if (curSeg.interruptType.equals("04")) {
						ide3 = "> GOTO SEGMENT 0";
					} else if (curSeg.interruptType.equals("08")) {
						ide3 = "> RETURN";
					} else if (curSeg.interruptType.equals("80")) {
						ide3 = "> GOTO NODE 0";
					} else {
						ide3 = "";
					}
				}
				
				if (ide2.equals("")&&iden.equals("")) {
					listModel_Segs.addElement(indent + "Segment " + (i) + ide3);
				} else if (ide2.equals("")) {
					listModel_Segs.addElement(indent + "Segment " + (i) + " - " + iden + ide3);
				} else if (iden.equals("")) {
					listModel_Segs.addElement(indent + "Segment " + (i) + " -> " + ide2 + ide3);					
				} else {
					listModel_Segs.addElement(indent + "Segment " + (i) + " - " + iden + " -> " + ide2 + ide3);					
				}
				
				curSeg = curSeg.next;
			}
		}
		list_SegSelect.setSelectedIndex(0);		
		refreshSegTab();
	}
//Refreshes the Node Tab
	public void refreshNodeTab() {
		if (selectedNode!=null) {
			textfield_nodeTitle.setText(selectedNode.title);
			label_nodeOffset.setText("Node Index: " + (selectedNode.index-1));
			label_nodeSegCount.setText("# of Segments: " + selectedNode.segcount);
			textfield_nodeID.setText("" + selectedNode.id);
		}		
	}
//Refreshes the Seg Tab
		public void refreshSegTab() {
			if (selectedSeg!=null) {
				checkbox_lastSeg.setSelected(false);
				combobox_SegInterruptChoice.setSelectedIndex(0);		
				combobox_RandomChoice.setSelectedIndex(0);
				if (selectedSeg.nodeEnd) {
					checkbox_lastSeg.setSelected(true);
					checkbox_lastSeg.setVisible(true);
					combobox_SegCheckChoice.setVisible(false);
					combobox_SegInterruptChoice.setVisible(false);
					combobox_SegIfChoice.setVisible(false);
					textfield_Parameter.setVisible(false);
					textfield_Paramete2.setVisible(false);					
					combobox_RefTypeChoice.setVisible(false);
					textfield_ReferenceId.setVisible(false);
					textfield_Action.setVisible(false);
					combobox_RandomChoice.setVisible(false);
				} else if (!selectedSeg.choice.equals("00")) {					
					if (selectedSeg.choice.equals("40")) {
						combobox_RandomChoice.setSelectedIndex(1);
					} else if (selectedSeg.choice.equals("c0")) {
						combobox_RandomChoice.setSelectedIndex(2);							
					} else if (selectedSeg.choice.equals("80")) {
						combobox_RandomChoice.setSelectedIndex(3);									
					} else {
						combobox_RandomChoice.setSelectedIndex(4);							
					}
					checkbox_lastSeg.setVisible(false);
					combobox_SegCheckChoice.setVisible(false);
					combobox_SegInterruptChoice.setVisible(false);
					combobox_SegIfChoice.setVisible(false);
					textfield_Parameter.setVisible(true);
					textfield_Paramete2.setVisible(true);
					combobox_RefTypeChoice.setVisible(true);
					textfield_ReferenceId.setVisible(true);
					textfield_Action.setVisible(true);
					combobox_RandomChoice.setVisible(true);
				} else {
					checkbox_lastSeg.setVisible(true);
					combobox_SegCheckChoice.setVisible(true);
					combobox_SegInterruptChoice.setVisible(true);
					combobox_SegIfChoice.setVisible(true);
					textfield_Parameter.setVisible(true);
					textfield_Paramete2.setVisible(true);
					combobox_RefTypeChoice.setVisible(true);
					textfield_ReferenceId.setVisible(true);
					textfield_Action.setVisible(true);
					combobox_RandomChoice.setVisible(true);
				}
				
				if (selectedSeg.interruptType.equals("00")) {
					combobox_SegInterruptChoice.setSelectedIndex(0);						
				} else if (selectedSeg.interruptType.equals("04")) {
					combobox_SegInterruptChoice.setSelectedIndex(1);
				} else if (selectedSeg.interruptType.equals("08")) {
					combobox_SegInterruptChoice.setSelectedIndex(2);
				} else if (selectedSeg.interruptType.equals("80")) {
					combobox_SegInterruptChoice.setSelectedIndex(3);									
				} else {
					combobox_SegInterruptChoice.setSelectedIndex(4);						
				}
				
				if (selectedSeg.ifCon.equals("00")) {
					combobox_SegIfChoice.setSelectedIndex(0);
				} else if (selectedSeg.ifCon.equals("01")) {
					combobox_SegIfChoice.setSelectedIndex(1);
				} else if (selectedSeg.ifCon.equals("02")) {
					combobox_SegIfChoice.setSelectedIndex(2);
				} else if (selectedSeg.ifCon.equals("04")) {
					combobox_SegIfChoice.setSelectedIndex(3);
				} else if (selectedSeg.ifCon.equals("08")) {
					combobox_SegIfChoice.setSelectedIndex(4);				
				} else if (selectedSeg.ifCon.equals("10")) {
					combobox_SegIfChoice.setSelectedIndex(5);					
			    } else {
					combobox_SegIfChoice.setSelectedIndex(6);					
				}
				combobox_SegCheckChoice.removeItemAt(combobox_SegCheckChoice.getModel().getSize()-1);
				combobox_SegCheckChoice.addItem("Custom");
				switch (selectedSeg.check) {
				case "0000":
					combobox_SegCheckChoice.setSelectedItem("NULL (00)");
					break;
				case "0200":
					combobox_SegCheckChoice.setSelectedItem("Nothing (02)");
					break;
				case "0300":
					combobox_SegCheckChoice.setSelectedItem("Set Target (Entity) (03)");
					break;
				case "0500":
					combobox_SegCheckChoice.setSelectedItem("Set Target (Area) (05)");
					break;
				case "0600":
					combobox_SegCheckChoice.setSelectedItem("IF (Total) Dist. < (06)");
					break;
				case "0700":
					combobox_SegCheckChoice.setSelectedItem("IF (Total) Dist. > (07)");
					break;
				case "0800":
					combobox_SegCheckChoice.setSelectedItem("IF (Hori.) Dist. < (08)");
					break;
				case "0900":
					combobox_SegCheckChoice.setSelectedItem("IF (Hori.) Dist. > (09)");
					break;
				case "0f00":
					combobox_SegCheckChoice.setSelectedItem("IF Height (0F)");
					break;
				case "1600":
					combobox_SegCheckChoice.setSelectedItem("IF Rotation (16)");
					break;
				case "1e00":
					combobox_SegCheckChoice.setSelectedItem("IF Enraged (1E)");
					break;
				case "1f00":
					combobox_SegCheckChoice.setSelectedItem("IF Fatigued (1F)");
					break;
				case "2000":
					combobox_SegCheckChoice.setSelectedItem("IF Poisoned (20)");
					break;
				case "2400":
					combobox_SegCheckChoice.setSelectedItem("IF Mounted Part = (24)");
					break;
				case "2500":
					combobox_SegCheckChoice.setSelectedItem("IF Mounted (25)");
					break;
				case "2c00":
					combobox_SegCheckChoice.setSelectedItem("IF HP < (Para2)% (2C)");
					break;
				case "2e00":
					combobox_SegCheckChoice.setSelectedItem("IF Target's Status (2E)");
					break;
				case "3700":
					combobox_SegCheckChoice.setSelectedItem("IF Quest ID (37)");
					break;
				case "5e00":
					combobox_SegCheckChoice.setSelectedItem("Alt. Target (?) (5E)");
					break;
				case "6b00":
					combobox_SegCheckChoice.setSelectedItem("IF Nearest Meat > (6B)");
					break;
				case "7000":
					combobox_SegCheckChoice.setSelectedItem("IF Flying (70)");
					break;
				case "7600":
					combobox_SegCheckChoice.setSelectedItem("IF Part P1 Broken (76)");
					break;
				case "ae00":
					combobox_SegCheckChoice.setSelectedItem("Chng Area/THK17 (AE)");
					break;
				case "b800":
					combobox_SegCheckChoice.setSelectedItem("IF Quest Rank (B8)");
					break;
				case "bf00":
					combobox_SegCheckChoice.setSelectedItem("Area Check (BF)");
					break;	
				case "8000":
					combobox_SegCheckChoice.setSelectedItem("Set Int A (80)");
					break;
				case "8100":
					combobox_SegCheckChoice.setSelectedItem("Set Int B (81)");
					break;
				case "8200":
					combobox_SegCheckChoice.setSelectedItem("Set Int C (82)");
					break;
				case "8300":
					combobox_SegCheckChoice.setSelectedItem("Set Int D (83)");
					break;					
				case "8400":
					combobox_SegCheckChoice.setSelectedItem("Set Int E (84)");
					break;						
				case "8500":
					combobox_SegCheckChoice.setSelectedItem("Set Int F (85)");
					break;						
				case "8600":
					combobox_SegCheckChoice.setSelectedItem("Set Int G (86)");
					break;						
				case "8700":
					combobox_SegCheckChoice.setSelectedItem("Set Int H (87)");
					break;						
				case "8800":
					combobox_SegCheckChoice.setSelectedItem("Set Int I (88)");
					break;						
				case "8900":
					combobox_SegCheckChoice.setSelectedItem("Set Int J (89)");
					break;						
				case "8a00":
					combobox_SegCheckChoice.setSelectedItem("Set Int K (8A)");
					break;						
				case "8b00":
					combobox_SegCheckChoice.setSelectedItem("Set Int L (8B)");
					break;						
				case "8c00":
					combobox_SegCheckChoice.setSelectedItem("Set Int M (8C)");
					break;						
				case "8d00":
					combobox_SegCheckChoice.setSelectedItem("Set Int N (8D)");
					break;						
				case "8e00":
					combobox_SegCheckChoice.setSelectedItem("Set Int O (8E)");
					break;						
				case "8f00":
					combobox_SegCheckChoice.setSelectedItem("Set Int P (8F)");
					break;						
				case "9000":
					combobox_SegCheckChoice.setSelectedItem("Set Int Q (90)");
					break;						
				case "9100":
					combobox_SegCheckChoice.setSelectedItem("Set Int R (91)");
					break;						
				case "9200":
					combobox_SegCheckChoice.setSelectedItem("Set Int S (92)");
					break;						
				case "9300":
					combobox_SegCheckChoice.setSelectedItem("Set Int T (93)");
					break;		
				case "9400":
					combobox_SegCheckChoice.setSelectedItem("Check Int A (94)");
					break;	
				case "9500":
					combobox_SegCheckChoice.setSelectedItem("Check Int B (95)");
					break;
				case "9600":
					combobox_SegCheckChoice.setSelectedItem("Check Int C (96)");
					break;
				case "9700":
					combobox_SegCheckChoice.setSelectedItem("Check Int D (97)");
					break;					
				case "9800":
					combobox_SegCheckChoice.setSelectedItem("Check Int E (98)");
					break;						
				case "9900":
					combobox_SegCheckChoice.setSelectedItem("Check Int F (99)");
					break;						
				case "9a00":
					combobox_SegCheckChoice.setSelectedItem("Check Int G (9A)");
					break;						
				case "9b00":
					combobox_SegCheckChoice.setSelectedItem("Check Int H (9B)");
					break;						
				case "9c00":
					combobox_SegCheckChoice.setSelectedItem("Check Int I (9C)");
					break;						
				case "9d00":
					combobox_SegCheckChoice.setSelectedItem("Check Int J (9D)");
					break;						
				case "9e00":
					combobox_SegCheckChoice.setSelectedItem("Check Int K (9E)");
					break;						
				case "9f00":
					combobox_SegCheckChoice.setSelectedItem("Check Int L (9F)");
					break;						
				case "a000":
					combobox_SegCheckChoice.setSelectedItem("Check Int M (A0)");
					break;						
				case "a100":
					combobox_SegCheckChoice.setSelectedItem("Check Int N (A1)");
					break;						
				case "a200":
					combobox_SegCheckChoice.setSelectedItem("Check Int O (A2)");
					break;						
				case "a300":
					combobox_SegCheckChoice.setSelectedItem("Check Int P (A3)");
					break;						
				case "a400":
					combobox_SegCheckChoice.setSelectedItem("Check Int Q (A4)");
					break;						
				case "a500":
					combobox_SegCheckChoice.setSelectedItem("Check Int R (A5)");
					break;						
				case "a600":
					combobox_SegCheckChoice.setSelectedItem("Check Int S (A6)");
					break;						
				case "a700":
					combobox_SegCheckChoice.setSelectedItem("Check Int T (A7)");
					break;																	
				default:
						combobox_SegCheckChoice.removeItemAt(combobox_SegCheckChoice.getModel().getSize()-1);
						combobox_SegCheckChoice.addItem("Custom (" + revOrd(selectedSeg.check) + ")");
						combobox_SegCheckChoice.setSelectedIndex(combobox_SegCheckChoice.getModel().getSize()-1);
						break;
				}
				
				if (selectedSeg.referenceType==2) {
					button_goTo.setVisible(true);
				} else {
					button_goTo.setVisible(false);					
				}
				
				textfield_Parameter.setText(Integer.toString(selectedSeg.parameter));
				textfield_Paramete2.setText(Integer.toString(selectedSeg.paramete2));
				combobox_RefTypeChoice.setSelectedIndex(selectedSeg.referenceType);	
				textfield_ReferenceId.setText(Integer.toString(selectedSeg.referenceID));
				textfield_Action.setText(Integer.toString(selectedSeg.action));
				textfield_actUnkn1.setText(Integer.toString(selectedSeg.actUnkn1));
				textfield_actUnkn2.setText(Integer.toString(selectedSeg.actUnkn2));
				textfield_actUnkn3.setText(Integer.toString(selectedSeg.actUnkn3));
				textfield_actUnkn4.setText(Integer.toString(selectedSeg.actUnkn4));
				textfield_actUnkn5.setText(Integer.toString(selectedSeg.actUnkn5));
				if (combobox_SegCheckChoice.getSelectedIndex()==combobox_SegCheckChoice.getModel().getSize()-1) {
					PromptCustomCheck = false;
				} else {
					PromptCustomCheck = true;					
				}
			}		
		}
	
//Formats Nodes when a File is loaded
	public Node loadFile() {
		Node currentNode = null;
		Node prevNode = null;
		Segment currentSeg = null;
		Segment prevSeg = null;
		int nodeCount = Integer.parseInt(revOrd(hexData.substring(12, 16)), 16);
		//System.out.println(nodeCount);
		int offset;
		int segcount;
		int index;
		for (int i=0;i<nodeCount;i++) {
			offset = Integer.parseInt(revOrd(hexData.substring((i*32)+64, (i*32)+80)), 16);
			segcount = Integer.parseInt(revOrd(hexData.substring((i*32)+80, (i*32)+88)), 16);
			index = Integer.parseInt(revOrd(hexData.substring((i*32)+88, (i*32)+96)), 16);			
			currentNode = new Node(prevNode, i+1, segcount, offset, index);				
			if (prevNode!=null) {
				prevNode.next = currentNode;			
			}
			prevNode = currentNode;
			for (int j=0;j<segcount;j++) {
				currentSeg = currentNode.newSegment();
				currentNode.segcount -= 1;
			}
			//System.out.println("Created Node " + (i+1));
			setUpSegs(currentNode, offset);
		
		}
		if (currentNode != null) {
			return(currentNode.GetFirstNode());
		}
		return(null);
	}
//Sets up new segments
	public void setUpSegs(Node baseNode, int trueOffset) {
		int curOffset = trueOffset*2;
		Segment currentSeg = baseNode.firstseg;
		for (int k=0;k<baseNode.segcount;k++) {
			if ((hexData.substring(curOffset+(256*k), curOffset+(256*k)+2).equals("01"))) {
				currentSeg.nodeEnd = true;
			} else {
				currentSeg.choice = hexData.substring(curOffset+(256*k), curOffset+(256*k)+2);
			}
			currentSeg.interruptType = hexData.substring(curOffset+(256*k)+2, curOffset+(256*k)+4);	
			currentSeg.ifCon = hexData.substring(curOffset+(256*k)+4, curOffset+(256*k)+6);
			currentSeg.unknGap1 = hexData.substring(curOffset+(256*k)+6, curOffset+(256*k)+16);
			currentSeg.check = hexData.substring(curOffset+(256*k)+16, curOffset+(256*k)+20);
			currentSeg.unknGap2 = hexData.substring(curOffset+(256*k)+20, curOffset+(256*k)+24);
			currentSeg.parameter = (int) Long.parseLong(revOrd(hexData.substring(curOffset+(256*k)+24, curOffset+(256*k)+32)), 16);
			currentSeg.unknGap3 = hexData.substring(curOffset+(256*k)+32, curOffset+(256*k)+64);
			//System.out.println("Address: " + (curOffset+(256*k)+64)/2);
			currentSeg.paramete2 = (int) Long.parseLong(revOrd(hexData.substring(curOffset+(256*k)+64, curOffset+(256*k)+72)), 16);			
			currentSeg.unknGap4 = hexData.substring(curOffset+(256*k)+72, curOffset+(256*k)+80);
			if ((!hexData.substring(curOffset+(256*k)+80, curOffset+(256*k)+82).equals("ff"))) {
				currentSeg.referenceType = 1;
				currentSeg.referenceID = Integer.parseInt(revOrd(hexData.substring(curOffset+(256*k)+88, curOffset+(256*k)+96)), 16);				
			} else if ((!hexData.substring(curOffset+(256*k)+96, curOffset+(256*k)+98).equals("ff"))) {
				currentSeg.referenceType = 2;
				currentSeg.referenceID = Integer.parseInt(revOrd(hexData.substring(curOffset+(256*k)+96, curOffset+(256*k)+104)), 16);				
			}
			currentSeg.unknGap5 = hexData.substring(curOffset+(256*k)+112, curOffset+(256*k)+152);
			currentSeg.action = (int) Long.parseLong(revOrd(hexData.substring(curOffset+(256*k)+152, curOffset+(256*k)+160)), 16);
			currentSeg.actUnkn1 = (int) Long.parseLong(revOrd(hexData.substring(curOffset+(256*k)+160, curOffset+(256*k)+168)), 16);
			currentSeg.actUnkn2 = (int) Long.parseLong(revOrd(hexData.substring(curOffset+(256*k)+168, curOffset+(256*k)+176)), 16);
			currentSeg.actUnkn3 = (int) Long.parseLong(revOrd(hexData.substring(curOffset+(256*k)+176, curOffset+(256*k)+184)), 16);
			currentSeg.unknGap6 = hexData.substring(curOffset+(256*k)+184, curOffset+(256*k)+192);
			currentSeg.actUnkn4 = (int) Long.parseLong(revOrd(hexData.substring(curOffset+(256*k)+192, curOffset+(256*k)+200)), 16);			
			currentSeg.actUnkn5 = (int) Long.parseLong(revOrd(hexData.substring(curOffset+(256*k)+200, curOffset+(256*k)+208)), 16);
			if (k<baseNode.segcount-1) {
				currentSeg.unknGap7 = hexData.substring(curOffset+(256*k)+208, curOffset+(256*k)+256);
			}
			currentSeg=currentSeg.next;
		}
	}
//Reverses Byte Order
	public String revOrd(String oldval) {
		String newhex = "";	
		for (int i=oldval.length();i>0;i-=2) {			//Reverses Byte Order
			newhex += oldval.substring(i-2, i);
		}
		return(newhex);
	}
// Refreshes the title
	public void refreshTitle() {
    	if (latestVersion) {
    		this.setTitle(currentFile + " - MHWI THK Editor (Ver. " + verNum + ")");
    	} else {
    		this.setTitle("*" + currentFile + " - MHWI THK Editor (Ver. " + verNum + ")");   		
    	}	
	}
// Imports values from .thktit to app
	public void loadNames(String thktit) {
		//System.out.println("Loading Node Titles with: " + thktit);
		int pos = 7;
		String curName = "";
		Node curNode = selectedNode;
		while (pos<(thktit.length())) {
			if (thktit.substring(pos, pos+1).equals("\n")) {
				curNode.title = curName;
				curName = "";
				if (curNode.next!=null) {
					curNode = curNode.next;
				}
				pos++;
			} else {
				curName += thktit.substring(pos, pos+1);
				pos++;
			}
		}
	}
// Takes to Node & Seg (Made for the search)
	public void takeToNodeSeg(Node curNode, Segment curSeg, int segID) {
		selectedNode = curNode;
		selectedSeg = curSeg;
		refreshNodeTab();
		refreshSegTab();
		list_NodeSelect.setSelectedIndex(curNode.index-1);
		list_SegSelect.setSelectedIndex(segID);
	}
// Added for advanced searching. Returns true (the seg. should be included in the search) if the values are equal OR lookingFor is not an integer
	public boolean checkSearchEXValue(JTextField lookingFor, int targetField) {
		String loFo = lookingFor.getText();
		int lF;
		try {
			lF = Integer.parseInt(loFo);
			if (lF == targetField) {
				//System.out.println(lF + " found");
				return(true);
			}
		} catch(Exception NumberFormatException) {
			//System.out.println(loFo + " is not an integer");
			return(true);
		}
		//System.out.println("No match; " + loFo + " does not equal " + targetField);
		return(false);
	}
// Shifts Node References when a node is shifted
// Modes: 0 - Shift Nodes; 1 - Delete Nodes
	public void shiftNodeRef(int from, int by, int mode) {
		//System.out.println("FROM: " + from + "\n BY: " + by + "\n FROM-BY: " + (from-by));
		Node curNode = selectedNode.GetFirstNode();
		Segment curSeg = curNode.firstseg;
		int totalNodes = curNode.CountAllNodes(curNode);
		for (int i=0;i<totalNodes;i++) {
			for (int j=0;j<curNode.segcount;j++) {				
				if ((curSeg != null)&&(curSeg.referenceType==2)) {
					if (curSeg.referenceID==from) {
						//System.out.println("Starting N" + (curNode.index-1) + "_S" + j + "...");
						//System.out.println("A_Found Local Reference_A (" + curSeg.referenceID + ")");
						switch (mode ) {
						case 0:
							curSeg.referenceID = from-by;
							break;
						case 1:
							curSeg.referenceType = 0;
							curSeg.referenceID = 0;
							break;
						}

						//System.out.println("|____________________B_" + (from) +  "->" + (from-by) + "_B____________________|");
					} else if ((by>0)&&(curSeg.referenceID>=from-by)&&(curSeg.referenceID<from)) {
						//System.out.println("Starting N" + (curNode.index-1) + "_S" + j + "...");
						//System.out.println("A_Found Local Reference_A (" + curSeg.referenceID + ")");						
						curSeg.referenceID++;
						//System.out.println("|____________________B_Shifted UP by 1____________________|");						
					} else if ((by<0)&&(curSeg.referenceID<=from-by)&&(curSeg.referenceID>from)) {
						//System.out.println("Starting N" + (curNode.index-1) + "_S" + j + "...");
						//System.out.println("A_Found Local Reference_A (" + curSeg.referenceID + ")");						
						curSeg.referenceID--;
						//System.out.println("|____________________B_Shifted DOWN by 1____________________|");						
					}
					if (curSeg.next != null) {
						curSeg = curSeg.next;
					}
				} else if ((curSeg != null)&(curSeg.next != null)) {
					curSeg = curSeg.next;
				} else if (curSeg != null) {
				}
			}
			if (curNode.next != null) {
				curNode = curNode.next;
				curSeg = curNode.firstseg;
			}
		}
		//System.out.println("Done");
	}
// Updates configData with the appropriate information
	public void configToString() {
		configData = "THKCONFIG" + verNum +
					 "\ndefaultDir=" + defaultDir + "\n";
	}
// Starts-Up the App	
	public static void main(String args[]) {
	    int fontSize = 12;
		String[] keys = new String[]{"Label.font",
									 "TextField.font",
									 "Button.font",
									 "CheckBox.font",
									 "ComboBox.font",
									 "MenuBar.font",
									 "Menu.font",
									 "MenuItem.font",
									 "ScrollPane.font",
									 "Frame.font",
									 "Panel.font",
									 "OptionPane.font",
									 "FileChooser.font",
									 "CheckBox.font",
									 "CheckBoxMenuItem.font",
									 "List.font",
									 "SplitPane.font",
									 "TabbedPane.font"};
	    try {
	    	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    	for (int i=0;i<keys.length;i++) {
	    		UIManager.put(keys[i], new Font("Sans_Serif",Font.PLAIN,fontSize));
	    	}        
	    } catch(Exception e) {}
	    AppGUI app = new AppGUI();
	}	
}