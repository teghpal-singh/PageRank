import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
 * The <code>WebGraph</code> class basically organizes the <code>WebPage</code>
 *   objects as a directed graph.
 */

public class WebGraph implements Comparable
{	
	private ArrayList<WebPage> pages = new ArrayList<WebPage>();
	final static int MAX_PAGES = 40;
	int[][] links = new int[MAX_PAGES][MAX_PAGES];
	
	/**
	 * static int index is used to increment the index every time a WebPage is
	 *   created which represents its position according to in the adjacency
	 *   matrix
	 */
	static int index = 0;
	
	/**
	 * static boolean sourceCheck lets the program know if the sourceURL exists
	 *   and it is not in a valid form
	 */
	
	static boolean sourceCheck = false;
	/**
	 * static boolean destinationCheck lets the program know if the 
	 *   destinationURL exists and it is not in a valid form
	 */
	
	static boolean destinationCheck = false;
	
	/**
	 * This returns the pages of this <code>WebGraph</code>.
	 *   
	 * @return - this <code>WebGraph's</code> page
	 */
	public ArrayList<WebPage> getList()
	{
		return this.pages;
	}
	
	/**
	 * This returns the links of this <code>WebGraph</code>.
	 *   
	 * @return - this <code>WebGraph's</code> links
	 */
	public int[][] getLinks()
	{
		return this.links;
	}
	
	/**
	 * This constructs a <code>WebGraph</code> object using the indicated files
	 *   as the source for pages and links.
	 *   
	 * @param pagesFile - String of the relative path to the file containing 
	 *   the page information
	 * @param linksFile - String of the relative path to the file containing 
	 *   the link information
	 *   
	 * @return - The <code>WebGraph</code> constructed from the text files
	 * 
	 * @throws IllegalArgumentException - indicating the files does not 
	 *   reference a valid text file, or if the files are not formatted 
	 *   correctly
	 */
	public static WebGraph buildFromFiles(String pagesFile, String linksFile)
	  throws IllegalArgumentException
	{
		WebGraph wG = new WebGraph();
		String data = "";
		String data2 = "";
		try
		{
			File file = new File(pagesFile);
			FileInputStream fis = new FileInputStream(file);
		    InputStreamReader inStream = new InputStreamReader(fis);
		    BufferedReader reader = new BufferedReader(inStream);
		    data = reader.readLine();
		    while (data != null)
		    {
		    	String line = data.trim();
			    String lineFromData[] = line.split(" ");
			    if (lineFromData.length < 2)
			    {
			    	throw new IllegalArgumentException();
			    }
			    String url = lineFromData[0];
			    if (url.indexOf(".") == -1)
			    {
			    	throw new IllegalArgumentException();
			    }
			    
			    ArrayList<String> k = new ArrayList<String>();
			    for (int x = 1; x < lineFromData.length; x++)
			    {
			    	String checkKeyword = lineFromData[x];
			    	if (checkKeyword.indexOf(".") != -1)
			    	{
			    		throw new IllegalArgumentException();
			    	}
			    	k.add(checkKeyword);
			    }
			    WebPage wP = new WebPage(url, index, 0, null, k);
			    index++;
			    wG.pages.add(wP);
			    data = reader.readLine();
		    }
		    
		    File file2 = new File(linksFile);
			FileInputStream fis2 = new FileInputStream(file2);
		    InputStreamReader inStream2 = new InputStreamReader(fis2);
		    BufferedReader reader2 = new BufferedReader(inStream2);
		    data2 = reader2.readLine();
		    while (data2 != null)
		    {
		    	String line2 = data2.trim();
		    	String lineFromData2[] = line2.split(" ");
		    	if (lineFromData2.length > 2 || lineFromData2.length < 2)
		    	{
		    		throw new IllegalArgumentException();
		    	}
		    	
		    	String source = lineFromData2[0];
			    if (source.indexOf(".") == -1)
			    {
			    	throw new IllegalArgumentException();
			    }
			    String destination = lineFromData2[1];
			    if (destination.indexOf(".") == -1)
			    {
			    	throw new IllegalArgumentException();
			    }
			    wG.addLink(source, destination);
			    data2 = reader2.readLine();
		    }
		    
		    for (int a = 0; a < wG.pages.size(); a++)
		    {
		    	ArrayList<Integer> setLinks = new ArrayList<Integer>();
		    	for (int b = 0; b < wG.pages.size(); b++)
		    	{
		    		if (wG.links[a][b] == 1)
		    		{
		    			setLinks.add(b);
		    		}
		    	}
		    	wG.getList().get(a).setLinks(setLinks);
		    }
		    
		    for (int c = 0; c < wG.pages.size(); c++)
		    {
		    	int counter = 0;
		    	for (int d = 0; d < wG.pages.size(); d++)
		    	{
		    		if (wG.links[d][c] == 1)
		    		{
		    			counter++;
		    		}
		    	}
		    	wG.getList().get(c).setRank(counter);
		    }
		}
		catch (IOException iOE)
		{
			System.out.println("Invalid file detected.");
		}
		return wG;
	}
	
	/**
	 * This adds a page to the <code>WebGraph</code>.
	 * 
	 * @param url - the URL of the <code>WebPage</code> (must not already exist
	 *   in the <code>WebGraph</code>)
	 * @param keywords - the keywords associated with the WebPage
	 * 
	 * @throws IllegalArgumentException - indicating if the URL is not unique 
	 *   and already exists in the graph, or if either argument is null
	 */
	public void addPage(String url, ArrayList<String> keywords) throws 
	  IllegalArgumentException
	{
		if (url == null || keywords == null)
		{
			throw new IllegalArgumentException();
		}
		for (int x = 0; x < this.pages.size(); x++)
		{
			if (this.pages.get(x).getURL().equalsIgnoreCase(url))
			{
				throw new DuplicateURLException();
			}
		}
		WebPage wP = new WebPage(url, index, 0, null, keywords);
		index++;
		this.pages.add(wP);
		updatePageRanks();
	}
	
	/**
	 * This adds a link from the <code>WebPage</code> with the URL indicated 
	 *   by source to the <code>WebPage</code> with the URL indicated by 
	 *   destination.
	 *   
	 * @param source - the URL of the page which contains the hyperlink to 
	 *   destination
	 * @param destination - the URL of the page which the hyperlink points to
	 * 
	 * @throws IllegalArgumentException - indicating if either of the URLs are
	 *   null or could not be found in pages
	 */
	public void addLink(String source, String destination) throws 
	  IllegalArgumentException
	{
		sourceCheck = false;
		destinationCheck = false;
		int getSourceIndex = 0;
		int getDestinationIndex = 0;
		if (source == null || destination == null)
		{
			throw new IllegalArgumentException();
		}
		for (int x = 0; x < this.pages.size(); x++)
		{
			if (this.pages.get(x).getURL().equalsIgnoreCase(source))
			{
				sourceCheck = true;
				getSourceIndex = this.pages.get(x).getIndex();
			}
			if (this.pages.get(x).getURL().equalsIgnoreCase(destination))
			{
				destinationCheck = true;
				getDestinationIndex = this.pages.get(x).getIndex();
			}
		}
		for (int a = 0; a < this.links.length; a++)
		{
			for (int b = 0; b < this.links.length; b++)
			{
				if (a == getSourceIndex && b == getDestinationIndex && this
				  .links[a][b] == 1)
				{
					throw new NullPointerException();
				}
			}
		}
		if (sourceCheck == false)
		{
			throw new NonExistentURLException();
		}
		if (destinationCheck == false)
		{
			throw new NonExistentURLException();
		}
		this.links[getSourceIndex][getDestinationIndex] = 1;
		updatePageRanks();
		for (int a = 0; a < this.pages.size(); a++)
		{
	    	ArrayList<Integer> setLinks = new ArrayList<Integer>();
	    	for (int b = 0; b < this.pages.size(); b++)
	    	{
	    		if (this.links[a][b] == 1)
	    		{
	    			setLinks.add(b);
	    		}
	    	}
	    	this.getList().get(a).setLinks(setLinks);
		}
	}
	
	/**
	 * This removes the <code>WebPage</code> from the graph with the given URL.
	 * 
	 * @param url - the URL of the page to remove from the graph
	 */
	public void removePage(String url)
	{
		int indexOfPageInMatrix = 0;
		boolean pageExists = false;
		for (int x = 0; x < this.pages.size(); x++)
		{
			if (this.pages.get(x).getURL().equalsIgnoreCase(url))
			{
				indexOfPageInMatrix = this.pages.get(x).getIndex();
				pageExists = true;
			}
		}
		if (pageExists == true)
		{
			for (int y = indexOfPageInMatrix; y < this.pages.size(); y++)
			{
				for (int z = 0; z < this.pages.size(); z++)
				{
					this.links[y][z] = this.links[y+1][z];
				}
			}
			for (int y = 0; y < this.pages.size(); y++)
			{
				for (int z = indexOfPageInMatrix; z < this.pages.size(); z++)
				{
					this.links[y][z] = this.links[y][z+1];
				}
			}
			
			for (int x = 0; x < this.pages.size(); x++)
			{
				if (this.pages.get(x).getURL().equalsIgnoreCase(url))
				{
					this.pages.remove(x);
				}
			}
			for (int a = indexOfPageInMatrix; a < this.pages.size(); a++)
			{
				this.pages.get(a).setIndex(this.pages.get(a).getIndex()-1);
			}
			updatePageRanks();
			for (int a = 0; a < this.pages.size(); a++)
			{
		    	ArrayList<Integer> setLinks = new ArrayList<Integer>();
		    	for (int b = 0; b < this.pages.size(); b++)
		    	{
		    		if (this.links[a][b] == 1)
		    		{
		    			setLinks.add(b);
		    		}
		    	}
		    	this.getList().get(a).setLinks(setLinks);
			}
		}
		else
		{
			throw new NonExistentURLException();
		}
	}
	
	/**
	 * This removes the link from <code>WebPage</code> with the URL indicated 
	 *   by source to the <code>WebPage</code> with the URL indicated by 
	 *   destination.
	 *   
	 * @param source - the URL of the WebPage to remove the link
	 * @param destination - the URL of the link to be removed
	 */
	public void removeLink(String source, String destination)
	{
		boolean sourceCheck = false;
		boolean destinationCheck = false;
		int getSourceIndex = 0;
		int getDestinationIndex = 0;
		for (int x = 0; x < this.pages.size(); x++)
		{
			if (this.pages.get(x).getURL().equalsIgnoreCase(source))
			{
				sourceCheck = true;
				getSourceIndex = this.pages.get(x).getIndex();
			}
			if (this.pages.get(x).getURL().equalsIgnoreCase(destination))
			{
				destinationCheck = true;
				getDestinationIndex = this.pages.get(x).getIndex();
			}
		}
		for (int a = 0; a < this.links.length; a++)
		{
			for (int b = 0; b < this.links.length; b++)
			{
				if (a == getSourceIndex && b == getDestinationIndex && this
				  .links[a][b] == 0)
				{
					throw new NullPointerException();
				}
			}
		}
		if (sourceCheck == true && destinationCheck == true)
		{
			this.links[getSourceIndex][getDestinationIndex] = 0;
		}
		if (sourceCheck == false || destinationCheck == false)
		{
			throw new NonExistentURLException();
		}
		updatePageRanks();
		for (int a = 0; a < this.pages.size(); a++)
	    {
	    	ArrayList<Integer> setLinks = new ArrayList<Integer>();
	    	for (int b = 0; b < this.pages.size(); b++)
	    	{
	    		if (this.links[a][b] == 1)
	    		{
	    			setLinks.add(b);
	    		}
	    	}
	    	this.getList().get(a).setLinks(setLinks);
	    }
	}
	
	/**
	 * This calculates and assigns the PageRank for every page in the 
	 *   <code>WebGraph</code>.
	 */
	public void updatePageRanks()
	{
		for (int c = 0; c < this.pages.size(); c++)
	    {
	    	int counter = 0;
	    	for (int d = 0; d < this.pages.size(); d++)
	    	{
	    		if (this.links[d][c] == 1)
	    		{
	    			counter++;
	    		}
	    	}
	    	this.getList().get(c).setRank(counter);
	    }
	}
	
	/**
	 * This prints the <code>WebGraph</code> in tabular form.
	 */
	public void printTable()
	{
		String s1 = (String.format("%-10s %-1s %-20s %-1s %-10s %-1s %-20s "
		  + "%-1s" + " %-40s","   Index", "", "    URL", "", "PageRank", "",
		  "Links", "", "Keywords"));
        s1 += "\n";
     	s1 += (String.format("%-120s", "--------------------------------------"
     	  + "-----------------------------------------------------------------"
     	  + "--------------------------"));
        s1 += "\n";
        
        for (int x = 0; x < this.pages.size(); x++)
        {
        	s1 += this.pages.get(x).toString();
        	if (x != this.pages.size()-1)
        	{
        		s1 += "\n";
        	}
        }
        System.out.println(s1);
	}

	/**
	 * This goes through each <code>WebPages</code> of this 
	 *   <code>WebGraph</code> and finds if a certain keyword is present. If it
	 *    is, it prints it out in a tabular form.
	 *    
	 * @param key - the associated keywords you are trying to find if it exists
	 *   in each <code>WebPage</code> of this <code>WebGraph</code>
	 */
	public void searchKeyword(String key)
	{
		int rank = 1;
		boolean searchedResultAtLeastOne = false;
		String s1 = (String.format("%-10s %-1s %-10s %-1s %-20s", "   Rank",
		  "", "PageRank", "", "    URL"));
		s1 += "\n";
     	s1 += (String.format("%-60s", "---------------------------------------"
     	  + "--------------"));
        s1 += "\n";
        
        for (int x = 0; x < this.pages.size(); x++)
        {
        	for (int y = 0; y < this.pages.get(x).getKeywords().size(); y++)
        	{
        		if (this.pages.get(x).getKeywords().get(y).equals(key))
        		{
        			s1 += (String.format("%-10s %-1s %-10s %-1s %-20s", "     "
        			  + rank, "|", "    " + this.pages.get(x).getRank(), "|", 
        			  this.pages.get(x).getURL()));
        			if (x != this.pages.size()-1)
                	{
                		s1 += "\n";
                	}
        			rank++;
        			searchedResultAtLeastOne = true;
        		}
        	}
        }
        if (searchedResultAtLeastOne == false)
        {
        	throw new SearchResultException();
        }
        System.out.println(s1);
	}
	
	/**
	 * A default compareTo method needed to over-ride Comparable's compareTo. 
	 *   Just needed this because eclipse put it there.
	 */
	public int compareTo(Object arg0) 
	{
		return 0;
	}
}