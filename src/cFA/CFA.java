package cFA;

import java.awt.Desktop;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.parser.Element;


import org.json.JSONObject;
import org.apache.commons.*;
import org.apache.http.client.utils.URIUtils;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;




public class CFA {
		public static JSONObject json;
		
	    public static void main(String[] args) throws Exception {  
	    	json = new JSONObject("	{\"name\":\"root\",\"children\":[{\"name\":\"PhoneNumber.php\",\"size\":22,\"bugs\":300,\"language\":\"PHP\"}],\"size\":139837}");
	   // 	json =  "{"name":"root","children":[{"name":"PhoneNumber.php","size":22,"bugs":300,"language":"PHP"},{"name":"build.xml","size":122,"bugs":25,"language":"XML"}],"size":139837}";
	    //	setAttribute("CONNECTION_DBNAME", json);
	//    	String blah = "index.html#{\"name\":\"root\",\"children\":[{\"name\":\"PhoneNumber.php\",\"size\":22,\"bugs\":300,\"language\":\"PHP\"}],\"size\":139837}";
	  //  	System.out.println("JSON: " + json);
	 //   	URIUtils.encodeQuery("http://www.google.com?q=a b");
	  //  	Path blah = Paths.get("index.html#", "	{\"name\":\"root\",\"children\":[{\"name\":\"PhoneNumber.php\",\"size\":22,\"bugs\":300,\"language\":\"PHP\"}],\"size\":139837}");
	//   	UriBuilder uri = UriBuilder.fragment(blah);
	//    	System.out.println("path: " + blah);
	       // opens browser window with CodeFlower image 
	        if(Desktop.isDesktopSupported())
	        {
	          Desktop.getDesktop().browse(new URI("index.html"));
	        }

	   //     submittingForm();
	       /* 
	        HTMLDocument htmlDoc = new HTMLDocument();
	        javax.swing.text.Element[] roots = htmlDoc.getRootElements(); // #0 is the HTML element, #1 the bidi-root
	        javax.swing.text.Element body = null;
	        for( int i = 0; i < roots[0].getElementCount(); i++ ) {
	            javax.swing.text.Element element = roots[0].getElement( i );
	            if( element.getAttributes().getAttribute( StyleConstants.NameAttribute ) == HTML.Tag.BODY ) {
	                body = element;
	                break;
	            }
	        }
	        htmlDoc.insertAfterStart( body, "<font>text</font>" );*/
	        
	        
	    }
	    
	    public JSONObject getJSON(){
	    	return json;
	    }
	 /*   
	    public static void submittingForm() throws Exception {
	        final WebClient webClient = new WebClient();

	        // Get the first page
	        final HtmlPage page1 = webClient.getPage("./index.html");

	        // Get the form that we are dealing with and within that form, 
	        // find the submit button and the field that we want to change.
	        final HtmlForm form = page1.getFormByName("form");

	       // final HtmlSubmitInput button = form.getInputByName("submitbutton");
	        final HtmlTextInput textField = form.getInputByName("json");

	        // Change the value of the text field
	        textField.setValueAttribute("root");

	        // Now submit the form by clicking the button and get back the second page.
	       // final HtmlPage page2 = button.click();

	        webClient.closeAllWindows();
	    }*/
	}


