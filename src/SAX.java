import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SAX
{
	public static void main(String args[]) throws IOException, SAXException, ParserConfigurationException
	{
		// Θέτουμε να μπορεί να πάρει αρκετές entities ώστε να μην έχουμε
		// overflow
		System.setProperty("entityExpansionLimit", "100000000");
		
		SAXParserFactory spfac = SAXParserFactory.newInstance();
		spfac.setNamespaceAware(true);
		spfac.setValidating(true);
		SAXParser saxparser = spfac.newSAXParser();
		
		
		MyHandler handler = new MyHandler();
		

		/* call to create an html page for user*/

		handler.createHtmlPage();
		
		InputSource is = new InputSource("dblp.xml");

		System.out.println("----------------------\n"+"Please wait...");
		saxparser.parse(is, handler);
		
		
      	System.out.println("----------------------\n"+"Your html page has been created. Please check your working folder to open it!");
		
		
	}

}
