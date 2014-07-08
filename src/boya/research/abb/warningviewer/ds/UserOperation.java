package boya.research.abb.warningviewer.ds;
/**
 * Gang Shu (gxs213@case.edu) 
 **/
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

import boya.research.abb.warningviewer.frames.CodePanel;

public class UserOperation {

	//Current selected
	public String project = null;
	public String viewer = null;
	public String checker = null;
	public int warningKey = -1;

	//
	public JComboBox ProjectJComboBox, ViewerJComboBox, CheckerJComboBox, WarningJComboBox;

	//
	public CodePanel codePanel;
	public JTextPane detailsTextPane;
	public JTextPane assignedViewer;
	public JCheckBox comfirmCheckBox;
	public JTextPane comments;
	
}
