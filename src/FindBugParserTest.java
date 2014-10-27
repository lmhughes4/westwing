import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;



//some codes taken from http://www.java-samples.com/showtutorial.php?tutorialid=152
//						http://docs.oracle.com/javase/tutorial/jaxp/sax/parsing.html

public class FindBugParserTest extends DefaultHandler {
	

	private static Map<String, Integer> bugsPerClass;
	
	//this function is only main for testing
	public static void main(String[] args) throws Exception{  
		String filename = "findbugsoutput.xml";
		
		SAXParserFactory spf = SAXParserFactory.newInstance();
	    spf.setNamespaceAware(true);
	    SAXParser sp = spf.newSAXParser();
		
		XMLReader xmlReader = sp.getXMLReader();
		xmlReader.setContentHandler(new FindBugParserTest());
		//xmlReader.parse(convertToFileURL(filename));
		xmlReader.parse(filename);
		System.out.println(bugsPerClass.toString());

	}
	
	
	

	//Event Handlers
	
	public void startDocument() throws SAXException {
        bugsPerClass = new HashMap<String, Integer>();
    }
	

	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		switch(qName){
		case "Class":
			String className = attributes.getValue("classname");
			className = className.substring(className.lastIndexOf('.')+1).trim();
			Integer count = bugsPerClass.get(className);
			if(count == null){
				bugsPerClass.put(className, 1);
			}
			else{
				bugsPerClass.put(className, count+1);
			}
				
		}
	}
	
	

}
