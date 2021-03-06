package DepParser;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class depParser {

	public static Map<String, Map<String,Integer>> depFinderParser(String arg) {
		Map<String, Map<String,Integer>> hm = new HashMap<String, Map<String,Integer>>();
		try {
			String Filepath = arg;
			File file = new File(Filepath);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeLst = doc.getElementsByTagName("class");
			String temp="";


			for (int s = 0; s < nodeLst.getLength(); s++) {

				Node fstNode = nodeLst.item(s);


				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
					Element fstElmnt = (Element) fstNode;
					if(fstElmnt.getAttribute("confirmed").equals("yes")){
						NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("name");
						Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
						NodeList fstNm = fstNmElmnt.getChildNodes();

						temp = (fstNm.item(0)).getNodeValue();
						String className = temp.substring(temp.lastIndexOf('.')+1).trim();
						HashMap<String, Integer> classmap = new HashMap<String, Integer>();


						NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("outbound");

						String depName="";
						for(int i=0; i<lstNmElmntLst.getLength(); i++){
							Element lstNmElmnt = (Element) lstNmElmntLst.item(i);
							if(lstNmElmnt.getAttribute("type").equals("class")){
								if(lstNmElmnt.getAttribute("confirmed").equals("yes")){
									NodeList lstNm = lstNmElmnt.getChildNodes();
									temp = (lstNm.item(0)).getNodeValue();
									depName = temp.substring(temp.lastIndexOf('.')+1).trim();

									if(classmap.get(depName)==null){
										classmap.put(depName,1);
									}
									else{
										classmap.put(depName, classmap.get(depName)+1);
									}
								}
							}
						}
						if(classmap.isEmpty()){
							hm.put(className, null);
						}else{
							hm.put(className,classmap);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hm;
	}
}