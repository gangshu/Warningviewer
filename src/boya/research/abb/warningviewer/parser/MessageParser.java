package boya.research.abb.warningviewer.parser;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import boya.research.abb.warningviewer.db.QueryWarnings;
import boya.research.abb.warningviewer.ds.KlocworkWarning;

/**
 * @author Gang Shu
 */
public class MessageParser {
	
	/*
	 * TODO: replace and make sure the data format:
	 */
	final private static int itemNum = 17;
	final private static String oldChar = "d:", newChar = "c:";
	
	
	public static void main(String []args){
		QueryWarnings.getConnection("ac800m");
		rootKeyId = QueryWarnings.maxKey();
		
		parseWarning("C:/Documents and Settings/Gang/Desktop/CIR_issues_found_by_checkers.txt");
		
		/**
		 * If multiply files need to be parsed, please follow the below format: 
		 * 		parseWarning("A.txt");
		 * 		parseWarning("B.txt");
		 * 		parseWarning("C.txt");
		 */
		
		QueryWarnings.disConnection();
	}
	

	private static int rootKeyId;
	private static void parseWarning(String filename){
		
		if (filename!=null){
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
				
				KlocworkWarning warning;
				String inline;
				String [] items;
				while ((inline = in.readLine())!= null){
					items = inline.split(";");
					if (items.length==itemNum){
						
						try{
							warning = new KlocworkWarning();
							warning.key = ++rootKeyId;
							warning.checkerName = items[6];
							warning.patternKey = parsePatternKey(warning.checkerName);
							warning.type = "default";
							warning.functionName = items[8];
							warning.fileName = items[0].replace("\\", "/").replace(oldChar, newChar);
							warning.line = Integer.parseInt(items[1]);
							warning.col = Integer.parseInt(items[2]);
							warning.message = items[10];
							QueryWarnings.insertWarning(warning);
						}
						catch(Exception e){
							e.printStackTrace();
						}
					}
					else{
						System.out.println("Incorrect format, and no data has been imported into database.");
						System.out.println(inline);
						System.out.println("----------------");
					}
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
			finally{
				try{
					if (in!=null)
						in.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private static int parsePatternKey(String checkerName){
		
		int patternKey = 0;
		
		if (checkerName!=null && checkerName.toUpperCase().startsWith("P")){
			char c;
			int start = -1, end = checkerName.length();
			for (int i=0; i<checkerName.length(); i++){
				c = checkerName.charAt(i);
				
				if ('0'<=c && c<='9' && start==-1){
					start = i;
				}
				else if (!('0'<=c && c<='9') && start>-1){
					end = i;
					break;
				}
				
			}
			
			if (end>start){
				String str = checkerName.substring(start, end);
				patternKey = Integer.parseInt(str);
			}
		}

		System.out.println(checkerName+"\t"+patternKey);
		
		return patternKey;
		
	}

}
