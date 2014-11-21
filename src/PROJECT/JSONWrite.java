package PROJECT;

import java.io.File;  
import java.io.FileWriter;  
import java.io.IOException;  
import org.json.JSONObject;

class JSONWrite {  

	public static void JSONtoJS(JSONObject jsonInput) {    

		try {  

			File file=new File("jsonInput.js");  
			file.createNewFile();  
			FileWriter fileWriter = new FileWriter(file);   

			fileWriter.write("var jdata = ");
			fileWriter.write(jsonInput.toString()+"\n");  
			fileWriter.write("var total = countElements(jdata);\nw = parseInt(Math.sqrt(total) * 33, 13);\nh = parseInt(Math.sqrt(total) * 33, 13);\nvar myFlower = new CodeFlower(\"#visualization\", w, h);\nmyFlower.update(jdata);");
			fileWriter.flush();  
			fileWriter.close(); 

		} catch (IOException e) {  
			e.printStackTrace();  
		}  

	}  

}
