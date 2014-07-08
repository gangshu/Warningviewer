package boya.research.abb.warningviewer.frames;
/**
 * Gang Shu (gxs213@case.edu) 
 **/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.*;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter.HighlightPainter;

public class CodePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JPanel lineNumberPanel;
	private JTextPane textPanel;
	private JScrollPane scrollPanel;
	
	private JPanel title;
	private JTextField tf;
	
	public CodePanel(){
		init();
	}
	
	private void init(){		
		setLayout(new BorderLayout());
		textPanel = new JTextPane(){
			private static final long serialVersionUID = 1L;

			public boolean getScrollableTracksViewportWidth(){
			  return false;
			}
			
		};
		
		lineNumberPanel = new LineNumberTextPanel(textPanel);
		
		scrollPanel = new JScrollPane(textPanel);
		scrollPanel.setRowHeaderView(lineNumberPanel);
		scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		title = new JPanel();
		tf = new JTextField();
		title.setPreferredSize(new Dimension(20, 20));
		title.setLayout(new BoxLayout(title, BoxLayout.LINE_AXIS));
		tf.setEditable(false);
		scrollPanel.setSize(textPanel.getPreferredSize().width, textPanel.getPreferredSize().height);
		title.add(tf);
		
		add(title, BorderLayout.NORTH);
		
		add(scrollPanel, BorderLayout.CENTER);
		
	}
	
	public void viewCode(String filename){
		
		BufferedReader bufferedReader;
		try{
			FileInputStream fi = new FileInputStream(filename);
			InputStreamReader ir = new InputStreamReader(fi) ;
			bufferedReader = new BufferedReader(ir);
			
			textPanel.setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height));
			textPanel.getHighlighter().removeAllHighlights();
			textPanel.read(bufferedReader,null);
			tf.setText(filename);
			tf.repaint();	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	public void highlight(int lineNumber){
		if(lineNumber < 0)
			return;
//		System.out.println("lineNumber:"+lineNumber);
		
		Document doc = textPanel.getDocument();
		Element root = doc.getDefaultRootElement();
		Element element = root.getElement(lineNumber - 1);
		
		int offset0 = element.getStartOffset();
		int offset1 = element.getEndOffset();
//		System.out.println(offset0+" - "+offset1);
		textPanel.getHighlighter().removeAllHighlights();

		HighlightPainter painter = new DefaultHighlightPainter(Color.cyan);

		Highlighter highlighter = textPanel.getHighlighter();
		highlighter.removeAllHighlights();
		
		try {
//			System.out.println(element.getDocument().getText(offset0, offset1 - offset0));
			highlighter.addHighlight(offset0, offset1, painter);
			
			int tmp = root.getElementCount();
			if(tmp > lineNumber + 10){
				textPanel.setCaretPosition(root.getElement(lineNumber + 5).getEndOffset());
			}
			else{
				textPanel.setCaretPosition(offset1 + 1);
			}
		}catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
