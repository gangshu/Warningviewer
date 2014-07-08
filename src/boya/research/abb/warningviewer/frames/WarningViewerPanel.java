package boya.research.abb.warningviewer.frames;
/**
 * Gang Shu (gxs213@case.edu) 
 **/
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;

import boya.research.abb.warningviewer.actions.MenuAction;
import boya.research.abb.warningviewer.actions.SubmitAction;
import boya.research.abb.warningviewer.actions.ViewAction;
import boya.research.abb.warningviewer.ds.UserOperation;
import boya.research.abb.warningviewer.util.Keywords;


public class WarningViewerPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private static int currentWarningKey = -1;
	
	private int projectSelected=-1, viewerSelected=-1, checkerSelected=-1, warningSelected=-1;
	
	private JPanel upperJPanel, lowerJPanel;
	private JComboBox ProjectJComboBox, ViewerJComboBox, CheckerJComboBox, WarningJComboBox;
	private String ProjectMenu [] = {Keywords.ProjectMenu, "ac800m", "abb_cir_cirld_5_10"};
	private String ViewerMenu [] = {Keywords.ViewerMenu};
	private String CheckerMenu [] = {Keywords.CheckerMenu};
	private String WarningMenu [] = {Keywords.WarningMenu};
	private JButton prevButton, viewButton, nextButton;
	private JSplitPane jsPanel;
	private JPanel codePanel, detailsPanel;
	private JButton submitButton;
	
	private JTextPane detailsTextPane;
	private JTextPane assignedViewer;
	private JCheckBox comfirmCheckBox;
	private JTextPane comments;
	
	
	public WarningViewerPanel(Dimension d){
		setLayout(new BorderLayout(0, 0));
		setSize(d);
		
		createUpperJPanel(d.width, d.height);
		createLowerJPanel(d.width, d.height);
		
		repaint();
	}
	
	private void createUpperJPanel (int width, int height){
		
		upperJPanel = new JPanel();
		upperJPanel.setPreferredSize(new Dimension(width, (int)(height / 30)));
		
		
		ProjectJComboBox = new JComboBox(ProjectMenu);
		ProjectJComboBox.setPreferredSize(new Dimension (100, 20));
		if (ProjectMenu.length>1)
			ProjectJComboBox.setSelectedIndex(0);
		upperJPanel.add(ProjectJComboBox);
		ProjectJComboBox.addActionListener (new ActionListener (){ 
		    public void actionPerformed(ActionEvent e){ 
		    	if (ProjectJComboBox.getSelectedIndex()>0 && projectSelected!=ProjectJComboBox.getSelectedIndex()){
		    		projectSelected = ProjectJComboBox.getSelectedIndex();
		    		UserOperation operation = new UserOperation();
		    		operation.ProjectJComboBox = ProjectJComboBox;
		    		operation.ViewerJComboBox = ViewerJComboBox;
		    		operation.CheckerJComboBox = CheckerJComboBox;
		    		operation.WarningJComboBox = WarningJComboBox;
		    		operation.project = ProjectJComboBox.getSelectedItem().toString();
		    		operation.viewer = null;
		    		operation.checker = null;
		    		operation.warningKey = -1;
		    		(new MenuAction()).displayMenus(operation);
		    		ViewerJComboBox.setSelectedIndex(0);
			    	CheckerJComboBox.setSelectedIndex(0);
		    		WarningJComboBox.setSelectedIndex(0);
		    		upperJPanel.repaint();
		    		nextButton.setEnabled(false);
		    		prevButton.setEnabled(false);
		    		viewButton.setEnabled(false);
		    		viewerSelected=-1;
		    		checkerSelected=-1;
		    		warningSelected=-1;
		    	}
		    } 
		}); 
		

		ViewerJComboBox = new JComboBox(ViewerMenu);
		ViewerJComboBox.setPreferredSize(new Dimension (100, 20));
		if (ViewerMenu.length>1)
			ViewerJComboBox.setSelectedIndex(0);
		upperJPanel.add(ViewerJComboBox);
		ViewerJComboBox.addActionListener (new ActionListener (){ 
		    public void actionPerformed(ActionEvent e){ 
		    	if (ViewerJComboBox.getSelectedIndex()>0 && viewerSelected!=ViewerJComboBox.getSelectedIndex()){
		    		viewerSelected = ViewerJComboBox.getSelectedIndex();
		    		UserOperation operation = new UserOperation();
		    		operation.ProjectJComboBox = ProjectJComboBox;
		    		operation.ViewerJComboBox = ViewerJComboBox;
		    		operation.CheckerJComboBox = CheckerJComboBox;
		    		operation.WarningJComboBox = WarningJComboBox;
		    		operation.project = ProjectJComboBox.getSelectedItem().toString();
		    		operation.viewer = ViewerJComboBox.getSelectedItem().toString();
		    		operation.checker = null;
		    		operation.warningKey = -1;
		    		(new MenuAction()).displayMenus(operation);
			    	CheckerJComboBox.setSelectedIndex(0);
		    		WarningJComboBox.setSelectedIndex(0);
		    		upperJPanel.repaint();
		    		nextButton.setEnabled(false);
		    		prevButton.setEnabled(false);
		    		viewButton.setEnabled(false);
		    		checkerSelected=-1;
		    		warningSelected=-1;
		    	}
		    } 
		}); 
		
		
		CheckerJComboBox = new JComboBox(CheckerMenu);
		CheckerJComboBox.setPreferredSize(new Dimension (100, 20));
		if (CheckerMenu.length>1)
			CheckerJComboBox.setSelectedIndex(0);
		upperJPanel.add(CheckerJComboBox);
		CheckerJComboBox.addActionListener (new ActionListener (){ 
		    public void actionPerformed(ActionEvent e){ 
		    	if (CheckerJComboBox.getSelectedIndex()>0 && checkerSelected!=CheckerJComboBox.getSelectedIndex()){
		    		checkerSelected = CheckerJComboBox.getSelectedIndex();
		    		UserOperation operation = new UserOperation();
		    		operation.ProjectJComboBox = ProjectJComboBox;
		    		operation.ViewerJComboBox = ViewerJComboBox;
		    		operation.CheckerJComboBox = CheckerJComboBox;
		    		operation.WarningJComboBox = WarningJComboBox;
		    		operation.project = ProjectJComboBox.getSelectedItem().toString();
		    		operation.viewer = ViewerJComboBox.getSelectedItem().toString();
		    		operation.checker = CheckerJComboBox.getSelectedItem().toString();
		    		operation.warningKey = -1;
		    		(new MenuAction()).displayMenus(operation);
			    	WarningJComboBox.setSelectedIndex(0);
		    		upperJPanel.repaint();
		    		nextButton.setEnabled(false);
		    		prevButton.setEnabled(false);
		    		viewButton.setEnabled(false);
		    		warningSelected=-1;
		    	}
		    } 
		}); 
		
		WarningJComboBox = new JComboBox(WarningMenu);
		WarningJComboBox.setToolTipText("Please select a warning from the ComboBox.");
		WarningJComboBox.setPreferredSize(new Dimension (100, 20));
		if (WarningMenu.length>1)
			WarningJComboBox.setSelectedIndex(0);
		upperJPanel.add(WarningJComboBox);
		WarningJComboBox.addActionListener (new ActionListener (){ 
		    public void actionPerformed(ActionEvent e){ 
		    	if (WarningJComboBox.getSelectedIndex()>0 && warningSelected!=WarningJComboBox.getSelectedIndex()){
		    		warningSelected = WarningJComboBox.getSelectedIndex();
		    		nextButton.setEnabled(false);
		    		prevButton.setEnabled(false);
		    	}
	    		viewButton.setEnabled(true);
		    } 
		}); 


		prevButton = new JButton("Prev");
		prevButton.setPreferredSize(new Dimension (100, 20));
		upperJPanel.add(prevButton);
		prevButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (WarningJComboBox.getSelectedIndex()>0){
					UserOperation operation = new UserOperation();
					operation.ProjectJComboBox = ProjectJComboBox;
					operation.ViewerJComboBox = ViewerJComboBox;
		    		operation.CheckerJComboBox = CheckerJComboBox;
		    		operation.WarningJComboBox = WarningJComboBox;
					operation.assignedViewer = assignedViewer;
					operation.comfirmCheckBox = comfirmCheckBox;
					operation.comments = comments;
		    		operation.codePanel = (CodePanel)codePanel;
		    		operation.detailsTextPane = detailsTextPane;
		    		operation.project = ProjectJComboBox.getSelectedItem().toString();
		    		operation.viewer = ViewerJComboBox.getSelectedItem().toString();
		    		operation.checker = CheckerJComboBox.getSelectedItem().toString();
		    		nextButton.setEnabled(true);
		    		prevButton.setEnabled(true);
		    		try{
		    			if (WarningJComboBox.getSelectedIndex()>1){
			    			WarningJComboBox.setSelectedIndex(WarningJComboBox.getSelectedIndex()-1);
							operation.warningKey = Integer.parseInt(WarningJComboBox.getSelectedItem().toString());
							currentWarningKey = operation.warningKey;
							submitButton.setText("Submit to Warning No. "+currentWarningKey);
							if (WarningJComboBox.getSelectedIndex()==1)
								prevButton.setEnabled(false);
							nextButton.setEnabled(true);
		    			}
					}catch(Exception e){}
					(new ViewAction()).displayCodeAndWarnings(operation);
		    		submitButton.setEnabled(false);
		    	}
			}
		});
		
		
		viewButton = new JButton("View");
		viewButton.setPreferredSize(new Dimension (100, 20));
		upperJPanel.add(viewButton);
		viewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (WarningJComboBox.getSelectedIndex()>0){
					UserOperation operation = new UserOperation();
					operation.ProjectJComboBox = ProjectJComboBox;
					operation.ViewerJComboBox = ViewerJComboBox;
		    		operation.CheckerJComboBox = CheckerJComboBox;
		    		operation.WarningJComboBox = WarningJComboBox;
					operation.assignedViewer = assignedViewer;
					operation.comfirmCheckBox = comfirmCheckBox;
					operation.comments = comments;
		    		operation.codePanel = (CodePanel)codePanel;
		    		operation.detailsTextPane = detailsTextPane;
		    		operation.project = ProjectJComboBox.getSelectedItem().toString();
		    		operation.viewer = ViewerJComboBox.getSelectedItem().toString();
		    		operation.checker = CheckerJComboBox.getSelectedItem().toString();
		    		nextButton.setEnabled(true);
		    		prevButton.setEnabled(true);
		    		try{
						operation.warningKey = Integer.parseInt(WarningJComboBox.getSelectedItem().toString());
						currentWarningKey = operation.warningKey;
						submitButton.setText("Submit to Warning No. "+currentWarningKey);
						
						if (WarningJComboBox.getSelectedIndex()==1)
							prevButton.setEnabled(false);
						if (WarningJComboBox.getSelectedIndex()==WarningJComboBox.getItemCount()-1)
							nextButton.setEnabled(false);
					}catch(Exception e){}
					(new ViewAction()).displayCodeAndWarnings(operation);
					submitButton.setEnabled(false);
		    	}
			}
		});
		
	
		nextButton = new JButton("Next");
		nextButton.setPreferredSize(new Dimension (100, 20));
		upperJPanel.add(nextButton);
		nextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (WarningJComboBox.getSelectedIndex()>0){
					UserOperation operation = new UserOperation();
					operation.ProjectJComboBox = ProjectJComboBox;
					operation.ViewerJComboBox = ViewerJComboBox;
		    		operation.CheckerJComboBox = CheckerJComboBox;
		    		operation.WarningJComboBox = WarningJComboBox;
					operation.assignedViewer = assignedViewer;
					operation.comfirmCheckBox = comfirmCheckBox;
					operation.comments = comments;
		    		operation.codePanel = (CodePanel)codePanel;
		    		operation.detailsTextPane = detailsTextPane;
		    		operation.project = ProjectJComboBox.getSelectedItem().toString();
		    		operation.viewer = ViewerJComboBox.getSelectedItem().toString();
		    		operation.checker = CheckerJComboBox.getSelectedItem().toString();
		    		nextButton.setEnabled(true);
		    		prevButton.setEnabled(true);
		    		try{
		    			WarningJComboBox.setSelectedIndex(WarningJComboBox.getSelectedIndex()+1);
						operation.warningKey = Integer.parseInt(WarningJComboBox.getSelectedItem().toString());
						currentWarningKey = operation.warningKey;
						if (WarningJComboBox.getSelectedIndex()==WarningJComboBox.getItemCount()-1)
							nextButton.setEnabled(false);
						prevButton.setEnabled(true);
						submitButton.setText("Submit to Warning No. "+currentWarningKey);
					}catch(Exception e){}
					(new ViewAction()).displayCodeAndWarnings(operation);
		    		submitButton.setEnabled(false);
		    	}
			}
		});
		
		
		upperJPanel.repaint();
		this.add(upperJPanel, BorderLayout.NORTH);
		
	}
	
	
	private void createLowerJPanel (int width, int height){

		int height2 = height - upperJPanel.getPreferredSize().height;
		lowerJPanel = new JPanel();
		lowerJPanel.setPreferredSize(new Dimension(width, height2));
		
		int width3 = lowerJPanel.getPreferredSize().width * 3 / 5;
		
		jsPanel = new JSplitPane();
		jsPanel.setPreferredSize(new Dimension(lowerJPanel.getPreferredSize().width, lowerJPanel.getPreferredSize().height-100));
		jsPanel.setDividerLocation(width3);
		jsPanel.setDividerSize(2);
		lowerJPanel.add(jsPanel);
		
		codePanel = new CodePanel();
		codePanel.setPreferredSize(new Dimension(width3, jsPanel.getPreferredSize().height));
		jsPanel.setLeftComponent(codePanel);
		
		detailsPanel = new CodePanel();
		detailsPanel.setPreferredSize(new Dimension(lowerJPanel.getPreferredSize().width - width3, jsPanel.getPreferredSize().height));
		jsPanel.setRightComponent(detailsPanel);

		
		detailsTextPane = new JTextPane();
		detailsTextPane.setText("");
		detailsTextPane.setEditable(true);
		detailsTextPane.setPreferredSize(new Dimension(detailsPanel.getPreferredSize().width, (int)(detailsPanel.getPreferredSize().height/2)));
		detailsPanel.add(detailsTextPane, BorderLayout.NORTH);
		

		JPanel p1 = new JPanel();
		
		JLabel j1 = new JLabel();
		j1.setText("Assigned viewer: ");
		j1.setPreferredSize(new Dimension(detailsTextPane.getPreferredSize().width-10, 20));
		p1.add(j1);
		assignedViewer = new JTextPane();
		assignedViewer.setText("Who?");
		assignedViewer.setPreferredSize(new Dimension(detailsTextPane.getPreferredSize().width-50, 20));
		detailsTextPane.setEditable(false);
		p1.add(assignedViewer);

		JLabel j2 = new JLabel();
		j2.setText("Confirm the warning (Yes/No): ");
		j2.setPreferredSize(new Dimension(detailsTextPane.getPreferredSize().width-10, 20));
		p1.add(j2);
		comfirmCheckBox = new JCheckBox();
		comfirmCheckBox.setPreferredSize(new Dimension(detailsTextPane.getPreferredSize().width-50, 20));
		comfirmCheckBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				submitButton.setEnabled(true);
				prevButton.setEnabled(false);
				viewButton.setEnabled(false);
				nextButton.setEnabled(false);
				ProjectJComboBox.setEnabled(false);
				CheckerJComboBox.setEnabled(false);
				WarningJComboBox.setEnabled(false);
			}
		});
		p1.add(comfirmCheckBox);
		
		
		
		JLabel j3 = new JLabel();
		j3.setText("Comments: ");
		j3.setPreferredSize(new Dimension(detailsTextPane.getPreferredSize().width-10, 20));
		p1.add(j3);
		comments = new JTextPane ();
		comments.setPreferredSize(new Dimension(detailsTextPane.getPreferredSize().width-50, 250));
		JScrollPane scrollPane2 = new JScrollPane(comments);
		scrollPane2.setPreferredSize(new Dimension(detailsTextPane.getPreferredSize().width-50, comments.getPreferredSize().height));
		scrollPane2.getVerticalScrollBar().setValue(scrollPane2.getVerticalScrollBar().getMaximum()); 
		comments.setToolTipText("Please leave comments here.");
		comments.setEditable(true);
		comments.setText("");
		comments.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				submitButton.setEnabled(true);
				prevButton.setEnabled(false);
				viewButton.setEnabled(false);
				nextButton.setEnabled(false);
				ProjectJComboBox.setEnabled(false);
				ViewerJComboBox.setEnabled(false);
				CheckerJComboBox.setEnabled(false);
				WarningJComboBox.setEnabled(false);
			}
		});
		p1.add(scrollPane2);
		
		

		submitButton = new JButton("Submit");
		submitButton.setEnabled(false);
		submitButton.setPreferredSize(new Dimension(detailsTextPane.getPreferredSize().width/2, 40));
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try{
					UserOperation operation = new UserOperation();
					operation.ProjectJComboBox = ProjectJComboBox;
					operation.ViewerJComboBox = ViewerJComboBox;
		    		operation.CheckerJComboBox = CheckerJComboBox;
		    		operation.WarningJComboBox = WarningJComboBox;
		    		operation.project = ProjectJComboBox.getSelectedItem().toString();
		    		operation.viewer = ViewerJComboBox.getSelectedItem().toString();
		    		operation.checker = CheckerJComboBox.getSelectedItem().toString();
		    		operation.warningKey = currentWarningKey;
					operation.assignedViewer = assignedViewer;
					operation.comfirmCheckBox = comfirmCheckBox;
					operation.comments = comments;
					
					(new SubmitAction()).submitOrUpdateComments(operation);
					prevButton.setEnabled(true);
					viewButton.setEnabled(true);
					nextButton.setEnabled(true);
					ProjectJComboBox.setEnabled(true);
					ViewerJComboBox.setEnabled(true);
					CheckerJComboBox.setEnabled(true);
					WarningJComboBox.setEnabled(true);
					submitButton.setEnabled(false);
                    
				}
				catch(Exception e){}
			}
		});
		p1.add(submitButton);


		detailsPanel.add(p1);

		this.add(lowerJPanel, BorderLayout.WEST);
		
		lowerJPanel.repaint();
	}
	
}