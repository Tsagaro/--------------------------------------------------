
import java.util.ArrayList;

public class Publication {
	
	private ArrayList<String> authors;
	private String title, booktitle;
	private short year;
    /* idCounter to count the number of total publications and flag to test whether to show or not
     * and their set and get methods 
     */ 
    private static int idCounter=0;

	public static int getIdCounter() {
		return idCounter;
	}

	public static void increaseIdCounter() {
		idCounter++;
	}
	
			
	public Publication() {
		authors = new ArrayList<String>();
		this.title = "-";
		this.booktitle = "-";
		this.year = 0000;
	}
	
	public boolean containAuthor(String author) {
		for(int i=0; i<this.authors.size(); i++) {
			if(author.equals(this.authors.get(i))) {
				return true;
			}
		}
		return false;
	}
	public ArrayList<String> getAuthors() {
		return this.authors;
	}
	public void addAuthor(String author) {
		authors.add(author);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public short getYear() {
		return year;
	}
	public void setYear(short year) {
		this.year = year;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}	
	
	/* Methodos createHtmlTable()pou orizei tin morfi emfanisis kathe dimosieusis.*/
	
	public String createHtmlTable() {
		String temp = "";
		
		/*Create a table row for the new publication data */
		temp+="<tr>";
		
		/*First column of the table for publication Id */
		temp+="<td>"+"# "+getIdCounter()+"</td>";
		
		/*Second column of the table for Authors */
		temp+="<td>";
		for(int i=0; i<this.authors.size(); i++) {
			temp+="\t"+new Integer(i+1)+": "+this.authors.get(i)+"\n"+"</br>";
		}
		temp+="</td>";
		
		/*Third column of the table for publication titles */
		temp+="<td>"+this.getTitle()+"</td>";
		
		/*Fourth column of the table for BookTitles*/
		temp+="<td>"+this.getBooktitle()+"</td>";
		
		/*Fifth column of the table for publication Years*/
		temp+="<td>"+this.getYear()+"</td>";
		
		
		/*Close the row of the table*/
		temp+="</tr>";
		

		return temp;
	}
}
