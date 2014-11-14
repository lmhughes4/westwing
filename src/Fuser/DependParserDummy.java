package Fuser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DependParserDummy {
	
	public static void depParser() throws IOException {
		File filer = new File("C:\\Users\\Ali Y. Akgul\\Desktop\\150004_15122012_G.txt");
		try (BufferedReader reader = new BufferedReader(new FileReader(filer))) {
            while (true) {
                String line = reader.readLine();
                if (line.contains("import")) {
                    break;
                }
                String[] fields = line.split("|");
                // process fields here
                for(int i=0;i<=fields.length;i++){
                    System.out.println(fields[i]);
                }
            }
        }
}
}