package PROJECT;

import java.io.IOException;
import java.util.Map;

import lineCounter.LineCounter;

import org.json.JSONException;
import org.json.JSONObject;

import DepParser.src.depParser;
import Fuser.Fuser;
import cFA.CFA;
import findbugparser.FindBugParser;


public class ProjectMain {
	public static void main(String[] args) throws IOException, InterruptedException {
		Map<String, Integer> findBugHashMap = null;
		Map<String, Integer> locHashMap = null;
		Map<String, Map<String,Integer>> depHashMap = null;
		JSONObject jsonInput = null;


		Runtime rt = Runtime.getRuntime();
		String command = "java -jar findbugs-3.0.0/lib/findbugs.jar -textui -xml -output fbOutput.xml " + args[0];
		Process pr = rt.exec(command);
		pr.waitFor();
		//command = "cmd /C DependencyExtractor -xml -out depOutput.xml " + args[0];
		command = "DependencyExtractor -xml -out depOutput.xml " + args[0];

		pr = rt.exec(command);
		pr.waitFor();
		String findBugsXML = "fbOutput.xml";
		try{
			findBugHashMap = FindBugParser.findBugParser(findBugsXML);
		} catch (Exception e) {
		}
		String depFinder = "depOutput.xml";
		try{
			depHashMap = depParser.depFinderParser(depFinder);
		} catch (Exception e) {
		}
		locHashMap = LineCounter.locParser(args[0]); 
		//System.out.println(locHashMap);

		try {
			jsonInput = Fuser.mapToJson(Fuser.fuse(findBugHashMap,depHashMap,locHashMap));
			System.out.println("json: " + jsonInput);

		} catch (JSONException e) {
		}
		JSONWrite.JSONtoJS(jsonInput);
		try {
			CFA.visualize();
		} catch (Exception e) {
		}

	}

}
