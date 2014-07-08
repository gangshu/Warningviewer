package boya.research.abb.warningviewer.parser;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import boya.research.abb.warningviewer.db.QueryWarnings;
import boya.research.abb.warningviewer.ds.KlocworkWarning;

public class MessageXMLParser {
		
	public static void main(String []args){
		QueryWarnings.getConnection("ac800m");
		
		parseXmlFile("data/AC800M/AC800M - Custom Checkers Results.xml");
		
		QueryWarnings.disConnection();
	}
	
	private static void parseXmlFile(String filename){
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(filename);
			Element docEle = dom.getDocumentElement();
			NodeList nl = docEle.getElementsByTagName("problem");
			
			if(nl != null && nl.getLength() > 0) {
				for(int i = 0 ; i < nl.getLength();i++) {

					Element el = (Element)nl.item(i);
					
					try{
						KlocworkWarning warning = new KlocworkWarning();
						warning.key = getIntValue(el,"problemID");
						warning.checkerName = getTextValue(el,"code");
						warning.patternKey = parsePatternKey(warning.checkerName);
						warning.type = "custom";
						warning.functionName = getTextValue(el,"method");
						warning.fileName = getTextValue(el,"file").replace("C:\\Documents and Settings\\V2IP User\\Desktop\\CIT51_BL_5.1.48.40\\Atlas","C:\\project_src\\AC800M").replace("\\", "/");
						warning.line = getIntValue(el,"line");
						warning.col = getIntValue(el,"column");
						warning.message = getTextValue(el,"message");
						QueryWarnings.insertWarning(warning);
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
			}

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private static String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

	private static int getIntValue(Element ele, String tagName) {
		return Integer.parseInt(getTextValue(ele,tagName));
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
