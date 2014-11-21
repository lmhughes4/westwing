package DepParser.src;


import java.io.File;
import java.io.IOException;
import java.util.Map;


public class depParserMain {
	public static void main(String[] args) throws IOException, InterruptedException {
		Map<String, Map<String,Integer>> DepFinderHM;
		File tmp = new File(System.getProperty("user.dir"), "depOutput.xml");
		Runtime rt = Runtime.getRuntime();
		//String command = "java -jar findbugs-3.0.0/lib/findbugs.jar -textui -xml -output OUTPUT.xml " + args[0];
		String command = "cmd /C DependencyExtractor -xml -out depOutput.xml " + args[0];
		Process pr = rt.exec(command);
		pr.waitFor();
		//System.out.println(command)
		  String depFinder = System.getProperty("user.dir") + "\\depOutput.xml";
		  System.out.println(depFinder);
		  try{
			  DepFinderHM = depParser.depFinderParser(depFinder);
			  System.out.println(DepFinderHM);
		  } catch (Exception e) {
		}
		  //println args[0]
		   //java -jar findbugs-3.0.0/lib/findbugs.jar
	   	}
}