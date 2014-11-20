import java.io.IOException;
import java.util.Map;


public class ProjectMain {
	public static void main(String[] args) throws IOException, InterruptedException {
		Map<String, Integer> findBugHashMap;
		Map<String, Integer> locHashMap;
			
		//String test = "StockWatcher"
		//def scriptPath = "/Users/me/foo/example.pl"
		Runtime rt = Runtime.getRuntime();
		String command = "java -jar findbugs-3.0.0/lib/findbugs.jar -textui -xml -output OUTPUT.xml " + args[0];
		Process pr = rt.exec(command);
		pr.waitFor();
		//System.out.println(command);
		  String findBugsXML = "OUTPUT.xml";
		  //System.out.println(findBugsXML);
		  try{
			  findBugHashMap = FindBugParser.findBugParser(findBugsXML);
			  System.out.println(findBugHashMap);
		  } catch (Exception e) {
		}
		locHashMap = LineCounter.locParser(args[0]); 
		System.out.println(locHashMap);
		}
}
