
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
       /* Array for searching the names from the file names.txt with the names of authors 
        * Size should be changed depending on number of entries.
        * */
       private static String[] namesArray = new String[27];
       protected Hashtable<String, ArrayList<Publication>> professorsPublications = new Hashtable<String, ArrayList<Publication>>();
       private String temp ;
       private boolean flag = false, setBold = false;   
       PrintWriter myconsole;
 	  
	         
       public MyHandler() {
    	   super();
    	   /* call to readFile method to read the names from the file and put them in namesArray */
    	   this.readFile();
    	   
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
    	             
    	   /*iteration through the file names to see if one of the authors in the file = temp
    	    * to set the flag true so we can keep the information we need. 
    	    * Besides the flag we also have a setBold boolean to take note of the name that's being kept.
    	    */
    	   for(int j=0; j< namesArray.length-1 ;j++)
    	  {
    		   String name = namesArray[j];
    		   
	    	   if(temp.contentEquals(name)){
	  	       		Publication.increaseIdCounter();
	  	       		flag = true;	
	  	       		setBold = true;
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
    		   
    		   
    		   /*if the flag has been set to true, one of the authors from our file has been found 
    		    * and we can add all the information we need from the publication to the html page. 
    		    * We also set flag to false to let the program know the specific author's publications have been added.
    		    */ 
				if(flag){
					myconsole.append(publication.createHtmlTable());
					flag=false;
					
					//Uncomment this if you want to see the results through the Console
					//System.out.println(publication.toString());
				}
    		   
    	   }
    	   if(qName.equalsIgnoreCase("author")) {
    		   /*This is a simple test to see if one of the authors we are searching for is being added.
    		    * If so it will add the name with an HTML <b> stamp to the publication so that it appears in bold in the html page.
    		    */
			if(setBold){
    			publication.addAuthor("<b>"+temp+"</b>");
    			setBold = false;
			}
			else
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
       
       

       
      /*method to read file with name 'names.txt'
       * static method which means we need to call it when an instance of the class is run.
       */
      private void readFile(){
    	  
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
    			  System.out.println("Professor names to be searched for: "+"\n");
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
      
      /*
       * Method to create an html page called by SAX.java
       * The table's css has been implemented on the html for better appearance.
       * It creates the page on the Eclipse project folder. Refresh may be needed.
       * 
       * We create initially a table to be set with the names that are being searched for from 'names.txt' and afterwards
       * we create a second table which is populated in Publication.java after receiving the values we need from runtime.
       */

      public void createHtmlPage(){
    	  {
  	        try
  	        {
  	           myconsole = new PrintWriter("htmlPage.html");
  	           
  	           myconsole.append("<table align='left' width='23%' ><tr><th>The professor names you are searching for are: </th></tr>");
  	           for(int j=0; j< namesArray.length-1 ;j++)
  	           {
  	    		   myconsole.append("<tr><td>"+(j+1)+" : "+namesArray[j]+"</td></tr>");
  	           }
  	           myconsole.append("</table>");
  	           
  	           
  	           myconsole.append("<table align='right' width='70%' border='4px' style='text-align:center' >"
  	        		   +"<tr font-weight='bold' border='2px'> <th width='5%' >Counter </th> <th width='30%'>Authors</th> <th width='30%'>Title</th> <th width='15%'>Book Title</th> <th width='20%'>Year</th> </tr>");
  	           
  	           
  	        } catch (FileNotFoundException e)
  	        {
  	            e.printStackTrace();
  	        }finally{
  	            if(myconsole == null){
  	            	myconsole.append("</table>");
  	                myconsole.close();
  	            }
  	        }
  	    } 
      }
         
          
 }




