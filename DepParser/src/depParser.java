import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class depParser {

	 public static void main(String argv[]) {

	  try {
		  String Filepath = "xml\\example.xml";
		  File file = new File(Filepath);
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  DocumentBuilder db = dbf.newDocumentBuilder();
		  Document doc = db.parse(file);
		  doc.getDocumentElement().normalize();
		  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
		  NodeList nodeLst = doc.getElementsByTagName("class");
		  System.out.println("Information of all classes");
		  String temp="";

		  for (int s = 0; s < nodeLst.getLength(); s++) {
			
		    Node fstNode = nodeLst.item(s);
		    //System.out.println("test" + s);
		    //if( ((Element) fstNode).getAttributeNode("confirmed").equals("confirmed=yes")){
		    //System.out.println(((Element) fstNode).getAttribute("confirmed"));
		    
		    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
		      //System.out.println("test" + s + ".1");
		      Element fstElmnt = (Element) fstNode;
		      if(fstElmnt.getAttribute("confirmed").equals("yes")){
			      NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("name");
			      Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
			      NodeList fstNm = fstNmElmnt.getChildNodes();
			      
			      temp = ((Node) fstNm.item(0)).getNodeValue();
			      
			      System.out.println("Class: "  + temp.substring(temp.lastIndexOf('.')+1).trim());
			      System.out.println("	"+fstElmnt.getAttributeNode("confirmed"));			      
			      
			      NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("outbound");
			      
			      int NumDep = 0;
			      for(int i=0; i<lstNmElmntLst.getLength(); i++){
				      Element lstNmElmnt = (Element) lstNmElmntLst.item(i);
				      if(lstNmElmnt.getAttribute("type").equals("class")){
				    	  if(lstNmElmnt.getAttribute("confirmed").equals("yes")){
				    		  NodeList lstNm = lstNmElmnt.getChildNodes();
						      //Uncomment the following line of code to find out the exact dependency
				    		  temp = ((Node) lstNm.item(0)).getNodeValue();
						      System.out.println("	Out dependency: " + temp.substring(temp.lastIndexOf('.')+1).trim());
						      NumDep = i;
				    	  }
				      }
				  }
			      System.out.println("	Number of dependency:" + NumDep);
	    }

	  }
	  }
	  } catch (Exception e) {
	    e.printStackTrace();
	  }
	 }
	}