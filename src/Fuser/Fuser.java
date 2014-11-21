import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

public class Fuser {
	static Map<String, Map<String,Integer>> blah;
	static Map<String, Integer> jsonBlah;
	static HashMap<String, Integer> example;
	static HashMap<String, Integer> example2;
	static HashMap<String, Integer> example3;
	private static boolean add;
	
	
	
	public static HashMap<String, Object> fuse( Map<String, Integer> bugData,  Map<String, Map<String,Integer>> depData, Map<String,Integer> lineCount) throws JSONException{
		HashMap<String, Object> fusedMap = new HashMap<String, Object>();
		
		for(Entry<String, Integer> entry : lineCount.entrySet()){
			ArrayList<Object> list = new ArrayList<Object>();
			list.add(entry.getValue());
		    fusedMap.put(entry.getKey(), list);
		}

		for(Entry<String, Object> fmEntry : fusedMap.entrySet()){
			boolean matched = false;
			for(Entry<String, Integer> entryL : bugData.entrySet()){
				if(entryL.getKey().equals(fmEntry.getKey())){
					
					matched = true;
					
				}
			}
			if(matched){
				ArrayList<Object> list = new ArrayList<Object>();
				list = (ArrayList<Object>) fusedMap.get(fmEntry.getKey());
				list.add(bugData.get(fmEntry.getKey()));
				fusedMap.put(fmEntry.getKey(), list);
			}
			else{
				ArrayList<Object> list = new ArrayList<Object>();
				list = (ArrayList<Object>) fusedMap.get(fmEntry.getKey());
				list.add(null);
				fusedMap.put(fmEntry.getKey(), list);
				//System.out.println(fusedMap);
			}
//			else if(fusedMap.containsKey(entryL.getKey())){	
//					ArrayList<Object> list = new ArrayList<Object>();
//					list = (ArrayList<Object>) fusedMap.get(entryL.getKey());
//					list.add(entryL.getValue());
//					System.out.println(entryL.getValue());
//
//				    fusedMap.put(entryL.getKey(), list);
//			   }
//			   else{
//				   ArrayList<Object> list = new ArrayList<Object>();
//				   list.add("null");
//				   fusedMap.put(entryL.getKey(), list);
//			   }
		}
		for(Entry<String, Object> fmEntry : fusedMap.entrySet()){
			boolean matched = false;
			for(Entry<String, Map<String,Integer>> entryL : depData.entrySet()){
				if(entryL.getKey().equals(fmEntry.getKey())){
					
					matched = true;
					
				}
			}
			if(matched){
				ArrayList<Object> list;
				list =  (ArrayList<Object>) fusedMap.get(fmEntry.getKey());
				list.add(depData.get(fmEntry.getKey()));
				fusedMap.put(fmEntry.getKey(), list);
			}
			else{
				ArrayList<Object> list = new ArrayList<Object>();
				list = (ArrayList<Object>) fusedMap.get(fmEntry.getKey());
				list.add(null);
				fusedMap.put(fmEntry.getKey(), list);
			}
		}
//	   for(Entry<String, Map<String, Integer>> entry : depData.entrySet()){
//		   if(fusedMap.containsKey(entry.getKey())){			
//			   ArrayList<Object> list;
//			  list =  (ArrayList<Object>) fusedMap.get(entry.getKey());
//			  list.add(entry.getValue());
//			    fusedMap.put(entry.getKey(), list);
//		   }
//	   }	    

		System.out.println(fusedMap);
	    return fusedMap;  
	}

	
	public static JSONObject mapToJson(HashMap<String, Object> map) throws JSONException{
		JSONObject json = new JSONObject();
		json.put("name","root");
		for(Entry<String, Object> entry : map.entrySet()){
			JSONObject json2 = new JSONObject();
			json2.put("name", entry.getKey());
			//System.out.println(json2);
			ArrayList<Object> list;
			list = (ArrayList<Object>) entry.getValue();
			
			if(list.get(1) == null){
				json2.put("bugs", "0");
			}
			else json2.put("bugs", list.get(1));

			json2.put("loc", list.get(0));

			//JSONObject json3 = new JSONObject();

			HashMap<String,Object> dep = (HashMap<String, Object>) list.get(2);
			if(!dep.entrySet().isEmpty()){
			for(Entry<String, Object> entry2 : dep.entrySet()){
				if(entry2 == null){
					json2.accumulate("dependencies", "0");
					System.out.println("beenhere");
					break;
				}
				JSONObject json3 = new JSONObject();
				json3.put("dep_name", entry2.getKey());
				json3.put("call_number", entry2.getValue());
				//System.out.println(json3);
				json2.accumulate("dependencies", json3);
			}
			}else{
				json2.accumulate("dependencies", 0);
			}
			//json2.put("dependencies", json3);
			json.accumulate("children",json2);

		}
		//System.out.println(json.ge);
		//System.out.println("json: " + json);
        return json;

	}
	
    public static void main(String[] args) throws Exception { 
	    example = new HashMap<String, Integer>();
	    example.put("ex", 1);
	    example2 = new HashMap<String, Integer>();
	    example2.put("ex2", 2);
	    example3 = new HashMap<String, Integer>();
	    example3.put("ex3", 3);
	    example3.put("ex4", 4);
	    example3.put("ex5", 5);
	
    	
    	 blah = new HashMap<String, Map<String,Integer>>();
 	    blah.put("name", example);
 	    blah.put( "import", example2 );
 	    blah.put( "city", example3);
 	    
 	    jsonBlah = new HashMap<String, Integer>();
 	    //jsonBlah.put("name", null);
 	    jsonBlah.put("import", 10);
 	    jsonBlah.put("city", 11);

 		 HashMap<String, Integer> loc = new HashMap<String, Integer>();
 		 loc.put("name",2);
 		 loc.put("import",3);
 		 loc.put("city",4);
 	   
 		 //input has to be in order: bug data, dependency data, line count data 
   	  mapToJson(fuse(jsonBlah,blah,loc));
 		HashMap<String, Object> output = fuse(jsonBlah,blah,loc);
 		 //System.out.println(output);
 		} 
    
//name=[2, null, {ex=1}], import=[3, 10, {ex2=2}], city=[4, 11, {ex3=3, ex4=4, ex5=5}]}

}