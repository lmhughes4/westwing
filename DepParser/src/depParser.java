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
	
		  for (int s = 0; s < nodeLst.getLength(); s++) {
	
		    Node fstNode = nodeLst.item(s);
		    //System.out.println("test" + s);
		    
		    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
		      //System.out.println("test" + s + ".1");
		      Element fstElmnt = (Element) fstNode;
		      NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("name");
		      
		      Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
		      NodeList fstNm = fstNmElmnt.getChildNodes();
		      
		      System.out.println("Class: "  + ((Node) fstNm.item(0)).getNodeValue());
		      
		      
		      NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("inbound");
		      int NumDep = 0;
		      for(int i=0; i<lstNmElmntLst.getLength(); i++){
			      Element lstNmElmnt = (Element) lstNmElmntLst.item(i);
			      NodeList lstNm = lstNmElmnt.getChildNodes();
			      //Uncomment the following line of code to find out the exact dependency
			      //System.out.println("		Inbound dependency: " + ((Node) lstNm.item(0)).getNodeValue());
			      NumDep = i;
			  }
		      System.out.println("	Number of dependency:" + NumDep);
	    }

	  }
	  } catch (Exception e) {
	    e.printStackTrace();
	  }
	 }
	}