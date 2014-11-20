package Fuser;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class Fuser {
	static Map<String, HashMap<String,String>> blah;
	static Map<String, String> jsonBlah;
	static HashMap<String, String> example;
	static HashMap<String, String> example2;
	static HashMap<String, String> example3;
	
	
	
	public static HashMap<String, Object> fuse( Map<String, String> bugData,  Map<String, HashMap<String,String>> depData, Map<String,Integer> lineCount) throws JSONException{
		HashMap<String, Object> fusedMap = new HashMap<String, Object>();
		
		for(Entry<String, Integer> entry : lineCount.entrySet()){
			ArrayList<Object> list = new ArrayList<Object>();
			list.add(entry.getValue());
		    fusedMap.put(entry.getKey(), list);
		}

		for(Entry<String, String> entryL : bugData.entrySet()){
			   if(fusedMap.containsKey(entryL.getKey())){	
					ArrayList<Object> list = new ArrayList<Object>();
					list = (ArrayList<Object>) fusedMap.get(entryL.getKey());
					list.add(entryL.getValue());
				    fusedMap.put(entryL.getKey(), list);
			   }
		}
		
	   for(Entry<String, HashMap<String, String>> entry : depData.entrySet()){
		   if(fusedMap.containsKey(entry.getKey())){			
			   ArrayList<Object> list;
			  list =  (ArrayList<Object>) fusedMap.get(entry.getKey());
			  list.add(entry.getValue());
			    fusedMap.put(entry.getKey(), list);
		   }
	   }	    

		
	    return fusedMap;  
	}
	
	public static void mapToJson(HashMap<String, Object> map) throws JSONException{
		JSONObject json = new JSONObject();
		json.put("name","root");
		for(Entry<String, Object> entry : map.entrySet()){
			JSONObject json2 = new JSONObject();
			json2.put("name", entry.getKey());
			ArrayList<Object> list;
			list = (ArrayList<Object>) entry.getValue();
			
			if(list.get(1) == null){
				json2.put("bugs", "0");
			}
			else json2.put("bugs", list.get(1));

			json2.put("loc", list.get(0));

			JSONObject json3 = new JSONObject();

			HashMap<String,Object> dep = (HashMap<String, Object>) list.get(2);
			for(Entry<String, Object> entry2 : dep.entrySet()){

				json3.put("dep_name", entry2.getKey());
				json3.put("call_number", entry2.getValue());
			}
			json2.put("dependencies", json3);
			json.accumulate("children",json2);
			System.out.println("json: " + json);
		}

	}
	
    public static void main(String[] args) throws Exception { 
	    example = new HashMap<String, String>();
	    example.put("ex", "1");
	    example2 = new HashMap<String, String>();
	    example2.put("ex2", "2");
	    example3 = new HashMap<String, String>();
	    example3.put("ex3", "3");
	    example3.put("ex4", "4");
	    example3.put("ex5", "5");
	
    	
    	 blah = new HashMap<String, HashMap<String,String>>();
 	    blah.put("name", example);
 	    blah.put( "import", example2 );
 	    blah.put( "city", example3);
 	    
 	    jsonBlah = new HashMap<String, String>();
 	    jsonBlah.put("name", null);
 	    jsonBlah.put("import", "b");
 	    jsonBlah.put("city", "c");

 		 HashMap<String, Integer> loc = new HashMap<String, Integer>();
 		 loc.put("name",2);
 		 loc.put("import",3);
 		 loc.put("city",4);
 	   
 		 //input has to be in order: bug data, dependency data, line count data 
   	  mapToJson(fuse(jsonBlah,blah,loc));
    } 
    

}