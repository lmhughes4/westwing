package Fuser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Fuser {
	static Map<String, Integer> blah;
	static Map<String, String> jsonBlah;
	
	
	
	public static void fuse(Map<String, Integer> data, Map<String, String> depData) throws JSONException{
		
	    JSONObject json = new JSONObject(data);
	    JSONObject depJson = new JSONObject(depData);
	    JSONObject mergedObj = new JSONObject();
	    Iterator i1 = json.keys();
	    Iterator i2 = depJson.keys();
	    String tmp_key;
	    while(i1.hasNext()) {
	    	tmp_key = (String) i1.next();
//	    	System.out.printf(i1.next().toString());
	        mergedObj.put(tmp_key, json.get(tmp_key));
	    }
	    
	    while(i2.hasNext()) {
	        tmp_key = (String) i2.next();
	       mergedObj.accumulate(tmp_key, depJson.get(tmp_key));
	    }
	    System.out.printf( "JSON: %s", mergedObj.toString(2) );
	    
	   
	}
	
    public static void main(String[] args) throws Exception { 
    	 blah = new HashMap<String, Integer>();
 	    blah.put("name", 11 );
 	    blah.put( "age", 22 );
 	    blah.put( "city", 33);
 	    
 	    jsonBlah = new HashMap<String, String>();
 	    jsonBlah.put("name", "a");
 	    jsonBlah.put("import ..sf", "b");
 	    jsonBlah.put("extends ..", "c");
 	    
 	    
   	  fuse(blah, jsonBlah);
    }
	    

}


