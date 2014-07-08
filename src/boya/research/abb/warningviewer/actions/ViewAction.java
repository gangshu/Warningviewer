package boya.research.abb.warningviewer.actions;
/**
 * Gang Shu (gxs213@case.edu) 
 **/
import boya.research.abb.warningviewer.db.QueryWarnings;
import boya.research.abb.warningviewer.ds.KlocworkWarning;
import boya.research.abb.warningviewer.ds.UserOperation;


public class ViewAction {

	public void displayCodeAndWarnings(UserOperation operation){
		
		if (operation.project!=null && operation.checker!=null && operation.warningKey!=-1){
			if (!QueryWarnings.isConnected())
				QueryWarnings.getConnection(operation.project);
			
			KlocworkWarning warning = QueryWarnings.getWarning(operation.warningKey);
			
			if (warning!=null){
				/*
				 * 1. code panel:
				 */
				if (warning.functionName!=null)
					operation.codePanel.viewCode(warning.fileName);
				if(warning.line > 0)
					operation.codePanel.highlight(warning.line);
				operation.codePanel.repaint();
				

				/*
				 * 2. Detail panel:
				 */
				String details = "";
				details += ("********************\n");
				details += ("Project: " + operation.project + "\n");
				details += ("Checker: " + operation.checker + "\n");
				details += ("Warning: " + operation.warningKey + "\n");
				details += ("********************\n");
				details += ("Test: getWarning" + "\n");
				details += ("Key: " + warning.key + "\n");
				details += ("patternKey: " + warning.patternKey + "\n");
				details += ("checkerName: " + warning.checkerName + "\n");
				details += ("type: " + warning.type + "\n");
                details += ("filename: " + warning.fileName + "\n");
				details += ("functionName: " + warning.functionName + "\n");
				details += ("line: " + warning.line + "\n");
				details += ("col: " + warning.col + "\n");
				details += ("message: " + warning.message + "\n");
				details += ("********************\n");
				details += ("confirmed? " + warning.confirm + "\n");
				details += ("reviewer: " + warning.reviewer + "\n");
				details += ("comments: " + warning.comments + "\n");
				details += ("(before submitting your comment.)\n");
				operation.detailsTextPane.setText(details);
				operation.detailsTextPane.repaint();
				
				
				/*
				 * 3. Viewer panel:
				 */
				if (operation.assignedViewer!=null)
					if (warning.reviewer!=null)
						operation.assignedViewer.setText(warning.reviewer);
					else
						operation.assignedViewer.setText("null");

				if (operation.comfirmCheckBox!=null)
					if (warning.confirm!=null && warning.confirm.trim().toUpperCase().equals("Y"))
						operation.comfirmCheckBox.setSelected(true);
					else
						operation.comfirmCheckBox.setSelected(false);

				if (operation.comments!=null)
					if (warning.comments!=null)
						operation.comments.setText(warning.comments);
					else
						operation.comments.setText("");
			}
		}
		
		

	}
	
}
