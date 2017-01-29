
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {

       private Publication publication = new Publication();
       /* Array for searching the names from the file names.txt with the names of authors */
       private static String[] namesArray = new String[27];
       protected Hashtable<String, ArrayList<Publication>> professorsPublications = new Hashtable<String, ArrayList<Publication>>();
       private String temp ;
       /* idCounter to count the number of total publications and flag to test whether to show or not*/ 
       private int idCounter=0;
       private boolean flag = false;     
       
       public MyHandler() {
    	   super();
    	   /* call to readFile method to read the names from the file and put them in namesArray */
    	   readFile();
       }
       
      
       public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
       {
    	   temp = "";
    	   if (qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("inproceedings") 
    			   || qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("book") 
    			   || qName.equalsIgnoreCase("incollection") || qName.equalsIgnoreCase("phdthesis")
    			   || qName.equalsIgnoreCase("mastersthesis") || qName.equalsIgnoreCase("www")) {
    		   publication = new Publication();
    	   }
       }
       
       
       
       public void characters(char[] ch, int start, int length) {
    	   temp = new String(ch, start, length);
    	             
    	   
    	   for(int j=0; j< 26 ;j++)
    	  {
    		   String name = namesArray[j];
    		   
	    	   if(temp.contentEquals(name)){
	  	       		idCounter++;
	  	       		flag = true;		
	  	       	}
    	   }
       
       }
      

       public void endElement(String uri, String localName, String qName) throws SAXException 
       {
    	   if (qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("inproceedings") 
    			   || qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("book") 
    			   || qName.equalsIgnoreCase("incollection") || qName.equalsIgnoreCase("phdthesis")
    			   || qName.equalsIgnoreCase("mastersthesis") || qName.equalsIgnoreCase("www")) 
    	   {
    		   for(int i=0; i<publication.getAuthors().size(); i++) {
    			   String authorName = publication.getAuthors().get(i);
    			   if(this.professorsPublications.containsKey(authorName)) {
    				  this.professorsPublications.get(authorName).add(publication);
    			   }
    		   }
    		   if(flag)
    		   System.out.println("# "+idCounter+publication.toString());
    		   flag=false;
    		   
    	   }
    	   if(qName.equalsIgnoreCase("author")) {
    		   publication.addAuthor(temp);
    	   }
    	   else if(qName.equalsIgnoreCase("title")) {
    		   publication.setTitle(temp);
           }
    	   else if(qName.equalsIgnoreCase("year")) {
    		   publication.setYear(Short.parseShort(temp));
           } 
    	   else if(qName.equalsIgnoreCase("booktitle")) {
    		   publication.setBooktitle(temp);
    	   }
    	   
    		  
       }
       
       
      private static void readFile(){
    	  
    	  String datei = "names.txt";


    	  FileReader fr = null;
    	  {
    		  try {
    			  fr = new FileReader(datei);
    		  } catch (FileNotFoundException e1) {
    			  e1.printStackTrace();
    		  }
    		  BufferedReader bf = new BufferedReader(fr);
    		  try {

    			  int i=0;
    			  String line;
    			  while((line = bf.readLine()) != null){
    				  namesArray[i] = line;
    				  System.out.println(namesArray[i]);  
    				  i++;

    			  }
    			  bf.close();

    		  } catch (IOException e) {
    			  e.printStackTrace();
    		  }
    	  }
      }
       
         
          
 }




