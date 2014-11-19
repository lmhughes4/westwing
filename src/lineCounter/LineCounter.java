package lineCounter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

//Original source: http://stackoverflow.com/questions/453018/number-of-lines-in-a-file-in-java


public class LineCounter {

	
	private static String fileName = "C:/Users/Lisa/Documents/UBC/CPSC 310/MapPractice/src/com/example/google/gwt/mapstutorial/client/client";
	
	  public static void main(String[] args) throws IOException {
		  //  System.out.println("Reading files under the folder "+ fileName.getAbsolutePath());
		   filesList(fileName);
		  }
	
	public static void filesList(String directoryName) throws IOException{
		//String[] list = File.list();
		final File folder = new File(fileName);
		String[] bah = listFilesForFolder(folder);
		for(String name: bah){
			System.out.println(countLines(name));
			
		}
	
	}
	
	public static String[] listFilesForFolder(final File folder) {
    	String[] foo = new String[100];
    	int i = 0;
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	foo[i] = fileEntry.getPath();
	        	i++;
	            System.out.println(fileEntry.getPath());
	        }
	    }
	    return foo;
	}





public static int countLines(String filename) throws IOException {
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
