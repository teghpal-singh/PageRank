import java.util.ArrayList;

/**
 * Teghpal Singh
 * 112224062
 * teghpal.singh@stonybrook.edu
 * Programming Assignment Number 7
 * CSE 214
 * Recitation Number 05
 * Teacher Assistant: Thomas Povinelli
 * 
 * The <code>WebPage</code> class basically represents a hyper-linked document.
 */

public class WebPage
{
	private String url;
	private int index;
	private int rank;
	/**
	 * an ArrayList of Integers indicating the links of this 
	 *   <code>WebPage</code>
	 */
	private ArrayList<Integer> links;
	private ArrayList<String> keywords;
	
	/**
	 * This constructs a <code>WebPage</code> with a specific URL, index, rank,
	 *   links, and an ArrayList of keywords that this <code>WebPage</code> 
	 *   contains.
	 *   
	 * @param u - the URL or data source that this <code>WebPage</code> can be 
	 *   connected to
	 * @param i - an Integer to represent its position in the adjacency matrix
	 * @param r - an Integer to represent the rank of this <code>WebPage</code>
	 * @param l - an ArrayList of Strings containing the indices of the links 
	 *   that this <code>WebPage</code> contains
	 * @param k - an ArrayList of Strings containing the keywords describing 
	 *   this <code>WebPage</code>.
	 */
	public WebPage(String u, int i, int r, ArrayList<Integer> l, 
	  ArrayList<String> k)
	{
		this.url = u;
		this.index = i;
		this.rank = r;
		this.links = l;
		this.keywords = k;
	}
	
	/**
	 * This returns the URL or data source of this <code>WebPage</code>.
	 *   
	 * @return - this <code>WebPage's</code> URL
	 */
	public String getURL()
	{
		return this.url;
	}
	
	/**
	 * This returns the index of this <code>WebPage</code> in the adjacency 
	 *   matrix.
	 *   
	 * @return - this <code>WebPage's</code> index
	 */
	public int getIndex()
	{
		return this.index;
	}
	
	/**
	 * This returns the rank of this <code>WebPage</code>.
	 *   
	 * @return - this <code>WebPage's</code> rank
	 */
	public int getRank()
	{
		return this.rank;
	}
	
	/**
	 * This returns the links of this <code>WebPage</code>.
	 *   
	 * @return - this <code>WebPage's</code> links
	 */
	public ArrayList<Integer> getLinks()
	{
		return this.links;
	}
	
	/**
	 * This returns the keywords of this <code>WebPage</code>.
	 *   
	 * @return - this <code>WebPage's</code> keywords
	 */
	public ArrayList<String> getKeywords()
	{
		return this.keywords;
	}
	
	/**
	 * This sets the URL of this <code>WebPage</code> to a new URL.
	 *   
	 * @param u - a new URL that will be set to equal this 
	 *   <code>WebPage's</code> URL
	 */
	public void setURL(String u)
	{
		this.url = u;
	}
	
	/**
	 * This sets the index of this <code>WebPage</code> to a new index.
	 *   
	 * @param i - a new index that will be set to equal this 
	 *   <code>WebPage's</code> index
	 */
	public void setIndex(int i)
	{
		this.index = i;
	}
	
	/**
	 * This sets the rank of this <code>WebPage</code> to a new rank.
	 *   
	 * @param r - a new rank that will be set to equal this 
	 *   <code>WebPage's</code> rank
	 */
	public void setRank(int r)
	{
		this.rank = r;
	}
	
	/**
	 * This sets the links of this <code>WebPage</code> to new links.
	 *   
	 * @param l - new links that will be set to equal this 
	 *   <code>WebPage's</code> links
	 */
	public void setLinks(ArrayList<Integer> l)
	{
		this.links = l;
	}
	
	/**
	 * This sets the keywords of this <code>WebPage</code> to a new String of
	 *   keywords.
	 *   
	 * @param k - a new String of keywords that will be set to equal this 
	 *   <code>WebPage's</code> keywords
	 */
	public void setKeywords(ArrayList<String> k)
	{
		this.keywords = k;
	}
	
	/**
	 * This returns string of data members in tabular form.
	 * 
	 * @return - string of data members in tabular form that displays this 
	 *   <code>WebPage's</code> URL, index, rank, and keywords describing this
	 *   <code>WebPage</code> 
	 */
	public String toString()
    {
        String linksFromArray = "";
        if (this.links != null)
        {
	        for (int x = 0; x < this.links.size(); x++)
	        {
	        	if (x == this.links.size() - 1)
	        	{
	        		linksFromArray += this.links.get(x);
	        	}
	        	else
	        	{
	        		linksFromArray += this.links.get(x) + ", ";
	        	}
	        }
        }
        
        String keywordsFromArray = "";
        for (int x = 0; x < this.keywords.size(); x++)
        {
        	if (x == this.keywords.size() - 1)
        	{
        		keywordsFromArray += this.keywords.get(x);
        	}
        	else
        	{
        		keywordsFromArray += this.keywords.get(x) + ", ";
        	}
        }
        
        String s1 = (String.format("%-10s %-1s %-20s %-1s %-10s %-1s %-20s "
          + "%-1s %-40s", "     " + this.getIndex(), "|", this.getURL(), "|",
          "    " + this.getRank(), "|", linksFromArray, "|", 
          keywordsFromArray));
    	return s1;    
    }
}