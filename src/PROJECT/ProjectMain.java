package PROJECT;

import java.io.IOException;
import java.util.Map;

import lineCounter.LineCounter;

import findbugparser.FindBugParser;


public class ProjectMain {
	public static void main(String[] args) throws IOException, InterruptedException {
		Map<String, Integer> findBugHashMap;
		Map<String, Integer> locHashMap;
		Map<String, Map<String,Integer>> depHashMap;
			
		//String test = "StockWatcher"
		//def scriptPath = "/Users/me/foo/example.pl"
		Runtime rt = Runtime.getRuntime();
		String command = "java -jar findbugs-3.0.0/lib/findbugs.jar -textui -xml -output fbOutput.xml " + args[0];
		Process pr = rt.exec(command);
		pr.waitFor();
		command = "cmd /C DependencyExtractor -xml -out depOutput.xml " + args[0];
		pr = rt.exec(command);
		pr.waitFor();
		//System.out.println(command);
		String findBugsXML = "fbOutput.xml";
		//System.out.println(findBugsXML);
		try{
			findBugHashMap = FindBugParser.findBugParser(findBugsXML);
			System.out.println(findBugHashMap);
			System.out.println("/n");
		  } catch (Exception e) {
		}
		String depFinder = "depOutput.xml";
		try{
			depHashMap = depParser.depFinderParser(depFinder);
			System.out.println(depHashMap);
			System.out.println("/n");
		} catch (Exception e) {
		}
		locHashMap = LineCounter.locParser(args[0]); 
		System.out.println(locHashMap);
		}
}