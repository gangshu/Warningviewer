package boya.research.abb.warningviewer.actions;
/**
 * Gang Shu (gxs213@case.edu) 
 **/
import java.util.ArrayList;

import boya.research.abb.warningviewer.db.QueryWarnings;
import boya.research.abb.warningviewer.ds.UserOperation;
import boya.research.abb.warningviewer.util.Keywords;

public class MenuAction {

	public void displayMenus(UserOperation operation){
		
		//Project menu
		if (operation.project!=null && operation.viewer==null && operation.checker==null && operation.warningKey==-1){
			QueryWarnings.disConnection();
			QueryWarnings.getConnection(operation.project);
			ArrayList<String> reviwerNames = QueryWarnings.getReviewers();
			operation.ViewerJComboBox.removeAllItems();
			reviwerNames.add(0, Keywords.ViewerMenu);
			for (String str:reviwerNames)
				operation.ViewerJComboBox.addItem(str);
		}

		//Viewer menu:
		if (operation.project!=null && operation.viewer!=null && operation.checker==null && operation.warningKey==-1){
			if (!QueryWarnings.isConnected())
				QueryWarnings.getConnection(operation.project);
			ArrayList<String> checkerNames = QueryWarnings.getCheckerNamesByReviewer(operation.viewer);
			operation.CheckerJComboBox.removeAllItems();
			checkerNames.add(0, Keywords.CheckerMenu);
			for (String str:checkerNames)
				operation.CheckerJComboBox.addItem(str);
		}
		
		//Checker menu:
		if (operation.project!=null && operation.viewer!=null  && operation.checker!=null && operation.warningKey==-1){
			if (!QueryWarnings.isConnected())
				QueryWarnings.getConnection(operation.project);
			ArrayList<Integer> warningKeys = QueryWarnings.getWarningKeys(operation.checker);
			operation.WarningJComboBox.removeAllItems();
			operation.WarningJComboBox.addItem(Keywords.WarningMenu);
			for (int i=0; i<warningKeys.size(); i++)
				operation.WarningJComboBox.addItem(warningKeys.get(i).toString());
		}
		
	}
	
}
