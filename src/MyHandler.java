import java.util.ArrayList;
import java.util.Hashtable;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler
{
	private Publication publication = new Publication();
	protected Hashtable<String, ArrayList<Publication>> unipiProf = new Hashtable<String, ArrayList<Publication>>();
	private String temp;

	/*
	 * boolean bFoundTitle = false; boolean bFoundAuthor = false; boolean
	 * bFoundYear = false; boolean bFoundBooktitle = false; boolean bFoundUnipi
	 * = false;
	 */

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		temp = "";
		if (qName.equalsIgnoreCase("author") || qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("title")
				|| qName.equalsIgnoreCase("year") || qName.equalsIgnoreCase("booktitle"))
		{
			publication = new Publication();
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
	{
		temp = new String(ch, start, length);
	}

	// Για όταν συνατάει το τέλος ενός element.Κλείνει το element
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException
	{

		if (qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("inproceedings")
				|| qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("book")
				|| qName.equalsIgnoreCase("incollection") || qName.equalsIgnoreCase("phdthesis")
				|| qName.equalsIgnoreCase("mastersthesis") || qName.equalsIgnoreCase("www"))
		{
			for (int i = 0; i < publication.getAuthors().size(); i++)
			{
				String authorName = publication.getAuthors().get(i);
				if (this.unipiProf.containsKey(authorName))
				{
					this.publication.setType(localName);
					this.unipiProf.get(authorName).add(publication);
				}
			}
		}

		if (qName.equalsIgnoreCase("author"))
		{
			publication.addAuthor(temp);
		}
		else if (qName.equalsIgnoreCase("title"))
		{
			publication.setTitle(temp);
		}
		else if (qName.equalsIgnoreCase("year"))
		{
			publication.setYear(Short.parseShort(temp));
		}
		else if (qName.equalsIgnoreCase("booktitle"))
		{
			publication.setBooktitle(temp);
		}
		// System.out.println(publication.toString());

	}

}
