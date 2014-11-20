package lineCounter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Original source: http://stackoverflow.com/questions/453018/number-of-lines-in-a-file-in-java


public class LineCounter {

	
	private static String fileName = "/Users/michaeltang/Desktop/StockWatcher/src";
	
	public static void main(String[] args) throws IOException {
		  //  System.out.println("Reading files under the folder "+ fileName.getAbsolutePath());
		locParser(fileName);
	}
	
	public static Map<String,Integer> locParser(String directoryName) throws IOException{
		//String[] list = File.list();
		Map<String,Integer> locPerClass = new HashMap<String,Integer>();
		final File folder = new File(directoryName);
		ArrayList<String> listOfFiles = listJavaFilesForFolder(folder);
		for(String name: listOfFiles){
			String className = name.substring(name.lastIndexOf('/')+1, name.lastIndexOf('.')).trim();
			locPerClass.put(className,countLines(name));
			//System.out.println(countLines(name));
			//countLines(name);
		}
		return locPerClass;
	}
	
	public static ArrayList<String> listJavaFilesForFolder(final File folder) {
    	ArrayList<String> gatheredFiles = new ArrayList<String>();
	    for (final File fileEntry : folder.listFiles()) {
	    	String fileEXT = fileEntry.getName();
	    	fileEXT = fileEXT.substring(fileEXT.lastIndexOf('.')+1).trim();
	        if (fileEntry.isDirectory()) {
	            gatheredFiles.addAll(listJavaFilesForFolder(fileEntry));
	        } else if(fileEXT.equals("java")){
	        	gatheredFiles.add(fileEntry.getPath());
	            //System.out.println(fileEntry.getPath());
	        }
	    }
	    return gatheredFiles;
	}





public static int countLines(String filename) throws IOException {
//	LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(filename)));
//	lnr.skip(Long.MAX_VALUE);
//	System.out.println(lnr.getLineNumber());
//	// Finally, the LineNumberReader object should be closed to prevent resource leak
//	lnr.close();
    InputStream is = new BufferedInputStream(new FileInputStream(filename));
    try {
        byte[] c = new byte[1024];
        int count = 0;
        int readChars = 0;
        boolean empty = true;
        while ((readChars = is.read(c)) != -1) {
            empty = false;
            for (int i = 0; i < readChars; ++i) {
                if (c[i] == '\n') {
                    ++count;
                }
            }
        }
        return (count == 0 && !empty) ? 1 : count;
    } finally {
        is.close();
    }
}

	
}
