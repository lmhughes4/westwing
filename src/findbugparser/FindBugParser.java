import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;



//some codes taken from http://www.java-samples.com/showtutorial.php?tutorialid=152
//						http://docs.oracle.com/javase/tutorial/jaxp/sax/parsing.html

public class FindBugParser extends DefaultHandler {
	
	
	private static Map<String, Integer> bugsPerClass;
	
	// Takes in the filename of the xml file output from the FindBug analyzer and creates a hashmap that
	// keeps count of how many bugs are in each class
	public static Map<String, Integer> findBugParser(String xml) throws Exception{  
		
		String filename = xml;
	    /*for (int i = 0; i < args.length; i++) {
	        filename = args[i];
	    }
*/
	    SAXParserFactory spf = SAXParserFactory.newInstance();
	    spf.setNamespaceAware(true);
	    SAXParser sp = spf.newSAXParser();
		
		XMLReader xmlReader = sp.getXMLReader();
		xmlReader.setContentHandler(new FindBugParser());
		//xmlReader.parse(convertToFileURL(filename));
		xmlReader.parse(filename);
		
		
		return bugsPerClass;
	}
	
	/*private static String convertToFileURL(String filename) {
		String path = new File(filename).getAbsolutePath();
		if (File.separatorChar != '/') {
			path = path.replace(File.separatorChar, '/');
		}
		
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		return "file:" + path;
	}
	*/

	

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
