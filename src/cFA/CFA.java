package cFA;

import java.awt.Desktop;
import java.net.URI;


public class CFA {
		
	    public static void visualize() throws Exception {  
	 
	       // opens browser window with CodeFlower image 
	        if(Desktop.isDesktopSupported())
	        {
	          Desktop.getDesktop().browse(new URI("index.html"));
	        }

	    }
	}


