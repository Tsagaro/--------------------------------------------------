import java.util.ArrayList;
import java.util.Hashtable;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler
{

	private Publication publication = new Publication();
    protected Hashtable<String, ArrayList<Publication>> professorsPublications = new Hashtable<String, ArrayList<Publication>>();
    private String temp ;
    private static boolean flag;
    private static long counter = 0;
    private static long doulkes = 0;
    private static String myTitle = " " ;
    private static int titlecount = 0;
    
    public MyHandler() {
 	   super();
    }
   
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
    {
 	   temp = "";
 	   
 	   
 	  if(qName.equalsIgnoreCase("title")&&flag){
		  titlecount++;
 	  }	   
 	   
 	  if(qName.equalsIgnoreCase("author")){
 		  counter++;
 		 System.out.println(counter+" # "+doulkes+" title count:  "+titlecount+" Latest Title: "+myTitle );
 	  }
 	   
 	 
// 	   if (qName.equalsIgnoreCase("article")){
// 		  publication = new Publication();
// 		  
// 	   }
// 	   
// 	   if(qName.equalsIgnoreCase("title")){
// 		   
// 		   
// 		   
// 			   for(int i=0; i<publication.getAuthors().size(); i++) {
// 				   
// 				   String authorName = publication.getAuthors().get(i);
// 				   if(authorName.equalsIgnoreCase("Athanasios G. Kanatas")){
// 					    					   
// 					   doulkes++;
// 					   //System.out.println("Ekana to flag true!");
// 					   flag=true;
// 				   
// 			   }
// 		   }
// 	   }
    }
    
    public void characters(char[] ch, int start, int length) {
    	temp = new String(ch, start, length);
    	
    	
    	if(temp.contentEquals("Christos Doulkeridis")){
    		doulkes++;
    		flag = true;
    	}
    }
   

    public void endElement(String uri, String localName, String qName) throws SAXException 
    {
// 	   if (qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("inproceedings") 
// 			   || qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("book") 
// 			   || qName.equalsIgnoreCase("incollection") || qName.equalsIgnoreCase("phdthesis")
// 			   || qName.equalsIgnoreCase("mastersthesis") || qName.equalsIgnoreCase("www")) {
    	
//    	
//    		if(qName.equalsIgnoreCase("author")){
//    			this.professorsPublications.put(key, value)
    			
    			
    			
//    			for(int i=0; i<publication.getAuthors().size(); i++) {
//    				String authorName = publication.getAuthors().get(i);
//    				if(this.professorsPublications.containsKey(authorName)) {
//    					this.professorsPublications.get(authorName).add(publication);
//    				}
//    			}
//    		}
    		
//	    	if(qName.equalsIgnoreCase("author")) {
//	    		publication.addAuthor(temp);
//	    	}
//	    	else if(qName.equalsIgnoreCase("title")) {
//	    		publication.setTitle(temp);
//	    	}
//	    	else if(qName.equalsIgnoreCase("year")) {
//	    		publication.setYear(Short.parseShort(temp));
//	    	} 
//	    	else if(qName.equalsIgnoreCase("booktitle")) {
//	    		publication.setBooktitle(temp);
//	    	}
//
//    		
    	
    	if(qName.equalsIgnoreCase("title")&&flag) {
    		myTitle=temp;
    	}
    		if(qName.equalsIgnoreCase("article")){
    			flag=false;
    		}
//    			if(flag)
//    				System.out.println(publication.toString());
//    			    			
//    			flag=false;
//    			System.out.println(++counter+"tou doulke einai: "+(doulkes-0)+" ");
//    		}
//    		
    		
    	}
//    	
//    	if(temp.contentEquals("Christos Doulkeridis")){
//  			   System.out.println(publication.toString());
//    	}
}

//	public String getProfessors() {
//		// TODO Auto-generated method stub
//		return professorsPublications.toString();
//	}
	


