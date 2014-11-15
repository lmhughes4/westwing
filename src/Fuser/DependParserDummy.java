package Fuser;

import java.io.BufferedReader;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DependParserDummy extends DefaultHandler {
	
	static Map<String, String> depPerClass;
	boolean testa;
	public static Map<String, String> depParser(String xml) throws Exception{
		String filename = xml;
		/*for (int i = 0; i < args.length; i++) {
		filename = args[i];
		}
		*/
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
		SAXParser sp = spf.newSAXParser();
		XMLReader xmlReader = sp.getXMLReader();
		xmlReader.setContentHandler(new DependParserDummy());
		//xmlReader.parse(convertToFileURL(filename));
		xmlReader.parse(filename);
		return depPerClass;
		}
	
	public void startDocument() throws SAXException {
		depPerClass = new HashMap<String, String>();
		}
	
	
	

		
		
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch(qName){
		case "name":
			testa = true;
		String className = attributes.getValue("name");
		String aClassName = attributes.getValue("inbound type");
		aClassName = aClassName.substring(aClassName.lastIndexOf('.')+1).trim();
	

		}
	}
	 
	public void characters(char ch[], int start, int length) throws SAXException {
		if(testa){
			System.out.println(new String(ch, start, length));
	}
		
		}
	
	
	

		
	  public static void main(String[] args) throws Exception { 
		  depParser("example.xml");
	 
	    }
		
		
		
		
		
		
		
		
		
		
		
		
	}
//   	 blah = new HashMap<String, String>();
//
//		File filer = new File("C:\\Users\\Ali Y. Akgul\\Desktop\\150004_15122012_G.txt");
//		try (BufferedReader reader = new BufferedReader(new FileReader(filer))) {
//            while (true) {
//                String line = reader.readLine();
//                if (line.contains("extends") || line.contains("implements")) {
//                	
//                	blah.put(arg0, arg1)
//                	
//                    
//                }
//                String[] fields = line.split("|");
//                // process fields here
//                for(int i=0;i<=fields.length;i++){
//                    System.out.println(fields[i]);
//                }
//            }
//        }
//}
//}