package boya.research.abb.warningviewer.actions;
/**
 * Gang Shu (gxs213@case.edu) 
 **/
import boya.research.abb.warningviewer.db.QueryWarnings;
import boya.research.abb.warningviewer.ds.UserOperation;

public class SubmitAction {

	public void submitOrUpdateComments(UserOperation operation){

		
		if (operation.project!=null){
			if (!QueryWarnings.isConnected())
				QueryWarnings.getConnection(operation.project);
			
			if (operation.warningKey!=-1 && operation.comfirmCheckBox!=null && operation.comments!=null){
				int warningKey = operation.warningKey;
				String confirmedFlag = operation.comfirmCheckBox.isSelected()==true? "Y" : "N";
				String comments = operation.comments.getText();
				
//				System.out.println(warningKey+"\t"+confirmedFlag+"\t"+comments);
				QueryWarnings.updateWarningFeedback(warningKey, confirmedFlag, comments);
			}	
		}
		
	}
	
}
