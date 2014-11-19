package Fuser;


import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Fuser {
	static Map<String, HashMap<String,String>> blah;
	static Map<String, String> jsonBlah;
	static HashMap<String, String> example;
	static HashMap<String, String> example2;
	static HashMap<String, String> example3;
	
	
	
	public static HashMap<String, Object> fuse( Map<String, String> data,  Map<String, HashMap<String,String>> depData) throws JSONException{
		HashMap<String, Object> fusedMap = new HashMap<String, Object>();
		
		for(Entry<String, String> entry : data.entrySet()){
			ArrayList<Object> list = new ArrayList();
			list.add(entry.getValue());
		    fusedMap.put(entry.getKey(), list);
		}

	   for(Entry<String, HashMap<String, String>> entry : depData.entrySet()){
		   if(fusedMap.containsKey(entry.getKey())){			
			   ArrayList<Object> list;
			  list =  (ArrayList<Object>) fusedMap.get(entry.getKey());
			  list.add(entry.getValue());
			    fusedMap.put(entry.getKey(), list);

		   }
	   }	    
    	System.out.println("fusedMap: " + fusedMap);
		
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
			json2.put("bugs", list.get(0));

			JSONObject json3 = new JSONObject();
			
			HashMap<String,String> dep = (HashMap<String, String>) list.get(1);
			for(Entry<String, String> entry2 : dep.entrySet()){

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
 	    jsonBlah.put("name", "a");
 	    jsonBlah.put("import", "b");
 	    jsonBlah.put("city", "c");
 	    
 	    
   	  mapToJson(fuse(jsonBlah,blah));
    }
	    
    
    /*
     * 	    JSONObject json = new JSONObject(data);
    	System.out.println("JSON: " + json);
	    JSONObject depJson = new JSONObject(depData);
    	System.out.println("JSON: " + depJson);
	    JSONObject mergedObj = new JSONObject();
	    Iterator i1 = json.keys();
	    Iterator i2 = depJson.keys();
	    String tmp_key;
	    while(i1.hasNext()) {
	    	tmp_key = (String) i1.next();
//	    	System.out.printf(i1.next().toString());
		    JSONObject mergedObj2 = new JSONObject();
	        mergedObj2.put("name", tmp_key);
	        mergedObj2.put("bugs", json.get(tmp_key));
	 
	    }
	   	System.out.println("mergedJSON: " + mergedObj);
	    
	    while(i2.hasNext()) {
	        tmp_key = (String) i2.next();
	       if(mergedObj.get("name") == tmp_key)
	    	System.out.println("i2 temp_key: " + tmp_key);
	    	System.out.println("i2 temp_key value: " + depJson.get(tmp_key) );
	       mergedObj.accumulate(tmp_key, depJson.get(tmp_key));
	    }
	    System.out.printf( "JSON: %s", mergedObj.toString(2) );
     */
    
    

}