import java.util.ArrayList;

public class Publication
{
	// Δηλώνω τα entities που θα χρησιμοποιήσω
	private ArrayList<String> authors;
	private String title, booktitle, url, pages, ee, crossref;
	private short year;

	public Publication()
	{
		// Αρχικοποιήω τις entities
		authors = new ArrayList<String>();
		this.title = "   ";
		this.booktitle = "   ";
		this.url = "   ";
		this.crossref = "   ";
		this.ee = "   ";
		this.pages = "   ";
		this.year = 0000;

	}

	// Constructor που ελέγχει αν υπάρχει ο συγγραγέας.
	// Παίρνει ως input το όνομα του συγγραφέα και ελέγχει αν υπάρχει αυτό το
	// όνομα στον πίνακα των authors
	// Αν υπάρχει επιστρέφει true
	// Αν δεν υπάρχει επιστρέφει false

	public boolean containAuthors(String author)
	{
		for (int i = 0; i < this.authors.size(); i++)
		{
			if (author == this.authors.get(i))
			{
				return false;
			}
		}
		return true;

	}

}
