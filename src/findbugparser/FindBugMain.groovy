
class FindBugMain {
	static void main(String[] args) {
		def findBugHashMap
			
		  //String test = "StockWatcher"
		  //def scriptPath = "/Users/me/foo/example.pl"
		  def command = "java -jar findbugs-3.0.0/lib/findbugs.jar -textui -xml -output OUTPUT.xml " + args[0]
		  println command.execute().text
		  String findBugsXML = 'OUTPUT.xml'
		  println findBugsXML
		  try{
			  findBugHashMap = FindBugParser.findBugParser(findBugsXML)  
		  }
		  catch(Error e){
		  }
		  println findBugHashMap
		  //println args[0]
		   
		   //java -jar findbugs-3.0.0/lib/findbugs.jar
	   
		}
}
