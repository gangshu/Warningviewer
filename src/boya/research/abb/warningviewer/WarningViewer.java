package boya.research.abb.warningviewer;
/**
 * Gang Shu (gxs213@case.edu) 
 **/
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import boya.research.abb.warningviewer.frames.WarningViewerPanel;



public class WarningViewer extends JFrame{

	private static final long serialVersionUID = 1781631985442390963L;
	private static int openWindows = 0;
	
	public static void main(String[] args){
		try{
			(new WarningViewer()).init();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public WarningViewer(){
		openWindows ++;
	}
	
	public void init(){
		
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		Insets scrInsets = Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration()); 
		setBounds(scrInsets.left,scrInsets.top,scrSize.width-scrInsets.left-scrInsets.right,scrSize.height-scrInsets.top-scrInsets.bottom); 
		Dimension adjustedSize = new Dimension(scrSize.width-scrInsets.left-scrInsets.right, scrSize.height-scrInsets.top-scrInsets.bottom); 
		
		
		JTabbedPane jTabbedPane = new JTabbedPane();
		jTabbedPane.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		getContentPane().add(jTabbedPane);
		
		jTabbedPane.add("Warning Viewer", new WarningViewerPanel(adjustedSize));
		
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setVisible(true);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				openWindows--;
				if (openWindows == 0) {
					System.exit(0); // Terminate when the last window is closed.
				}
			}
		});
		
	}
	
}
