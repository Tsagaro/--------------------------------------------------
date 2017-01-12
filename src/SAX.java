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
		System.setProperty("entityExpansionLimit", "1000000");
		SAXParserFactory spfac = SAXParserFactory.newInstance();
		spfac.setNamespaceAware(true);

		SAXParser saxparser = spfac.newSAXParser();

		MyHandler handler = new MyHandler();

		InputSource is = new InputSource("books.xml");
		is.setEncoding("ISO-8859-1");
		System.out.println("Please wait...");
		saxparser.parse(is, handler);

	}

}
