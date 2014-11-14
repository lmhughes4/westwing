package Fuser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Fuser {
	static Map<String, Integer> blah;
	static Map<String, Integer> jsonBlah;
	
	
	
	public static void fuse(Map<String, Integer> data, Map<String, Integer> depData) throws JSONException{
		
	    JSONObject json = new JSONObject(data);
	    JSONObject depJson = new JSONObject(depData);
	    JSONObject mergedObj = new JSONObject();
	    Iterator i1 = json.keys();
	    Iterator i2 = depJson.keys();
	    String tmp_key;
	    while(i1.hasNext()) {
	    	tmp_key = (String) i1.next();
	        mergedObj.put(tmp_key, json.get(tmp_key));
	    }
	    while(i2.hasNext()) {
	        tmp_key = (String) i2.next();
	        mergedObj.put(tmp_key, depJson.get(tmp_key));
	    }
	    System.out.printf( "JSON: %s", mergedObj.toString(2) );
	    
	   
	}
	
    public static void main(String[] args) throws Exception { 
    	 blah = new HashMap<String, Integer>();
 	    blah.put("name", 11 );
 	    blah.put( "age", 32 );
 	    blah.put( "city", 22);
 	    
 	    jsonBlah = new HashMap<String, Integer>();
 	    jsonBlah.put("import ...", 1);
 	    jsonBlah.put("import ..sf", 2);
 	    jsonBlah.put("extends ..", 3);
 	    
 	    
   	  fuse(blah, jsonBlah);
    }
	    

}


