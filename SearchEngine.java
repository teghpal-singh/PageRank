import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Teghpal Singh
 * 112224062
 * teghpal.singh@stonybrook.edu
 * Programming Assignment Number 7
 * CSE 214
 * Recitation Number 05
 * Teacher Assistant: Thomas Povinelli
 * 
 * The <code>SearchEngine</code> class basically initializes a 
 *   <code>WebGraph</code> from the appropriate text files and allow the user 
 *   to search for keywords in the graph. The class also provides functionality
 *   to add/remove pages to/from the graph, as well as alter the hyper-links 
 *   between pages in the graph.
 */

public class SearchEngine 
{
	public static final String PAGES_FILE = "pages.txt";
	public static final String LINKS_FILE = "links.txt";
	private WebGraph web;
	
	/**
	 * This checks to see if the table is printed and sorted by index
	 */
	static boolean option1 = false;
	
	/**
	 * This checks to see if the table is printed and sorted by URL
	 */
	static boolean option2 = false;
	
	/**
	 * This checks to see if the table is printed and sorted by rank
	 */
	static boolean option3 = false;

	public static void main(String[] args) 
	{
		String keyboardInput;
        Scanner input = new Scanner(System.in);
        System.out.println("Loading WebGraph data...");
        SearchEngine sE = new SearchEngine();
        sE.web = WebGraph.buildFromFiles("pages.txt", "links.txt");
        System.out.println("Success!");
        System.out.println();
        /**
         * The bottom is the whole menu that basically keeps being displayed
         *   until "Q" is selected. It keeps on looping until int menu = 1
         *   and int menu = 1 when "Q" is selected
         */
        int menu = 0;
        do
        {
        	/**
        	 * Menu Option Below:
        	 */
        	System.out.println("Menu:");
            System.out.println("    (AP) - Add a new page to the graph.\n    "
              + "(RP) - " + "Remove a page from the graph.\n" + "    "
              + "(AL) - Add a link between pages in the graph.\n    "
              + "(RL) " + "- Remove a link between pages in the graph.\n" + 
              "    (P) - Print the graph.\n   " + " (S) - Search for pages "
              + "with a keyword.\n" + "    (Q) - Quit\n");
            System.out.print("Please select an option: ");
            keyboardInput = input.nextLine();
            System.out.print("");
            
            if (keyboardInput.equalsIgnoreCase("AP"))
            {
            	String URL = "";
            	try
            	{
	            	System.out.print("Enter a URL: ");
	            	keyboardInput = input.nextLine();
	            	URL = keyboardInput;
	            	if (URL.indexOf(".") == -1)
				    {
				    	throw new IllegalArgumentException();
				    }
				    
	            	System.out.print("Enter keywords (space-separated): ");
	            	keyboardInput = input.nextLine();
	            	String keywords = keyboardInput;
	            	String splitKeywords[] = keywords.split(" ");
	            	ArrayList<String> k = new ArrayList<String>();
				    for (int x = 0; x < splitKeywords.length; x++)
				    {
				    	String checkKeyword = splitKeywords[x];
				    	if (checkKeyword.indexOf(".") != -1)
				    	{
				    		throw new IllegalArgumentException();
				    	}
				    	k.add(checkKeyword);
				    }
	            	sE.web.addPage(URL, k);
	            	System.out.println();
	            	System.out.println(URL + " successfully added to the "
	            	  + "WebGraph!");
	                System.out.println();
	                continue;
            	}
            	catch (IllegalArgumentException iAE)
            	{
        			System.out.println();
            		System.out.println("Error: Please input an appropriate "
            		  + "WebPage next time.");
            		System.out.println();
            		continue;
            	}
            	catch (DuplicateURLException dUE)
            	{
        			System.out.println();
            		System.out.println("Error: " + URL + " already exists in"
            		  + " the WebGraph. Could not add new WebPage.");
            		System.out.println();
            		continue;
            	}
            }
            else if (keyboardInput.equalsIgnoreCase("RP"))
            {
            	String URL = "";
            	try
            	{
	            	System.out.print("Enter a URL: ");
	            	keyboardInput = input.nextLine();
	            	URL = keyboardInput;
	            	sE.web.removePage(URL);
	            	System.out.println();
	            	System.out.println(URL + " has been removed from the "
	            	  + "graph!");
            	}
            	catch (NonExistentURLException nEURLE)
            	{
            		System.out.println();
            		System.out.println("Error: " + URL + " does not exist.");
            		System.out.println();
            		continue;
            	}
                System.out.println();
                continue;
            }
            else if (keyboardInput.equalsIgnoreCase("AL"))
            {
            	String sourceURL = "";
            	String destURL = "";
            	String errorURL = "";
            	try
            	{
	            	System.out.print("Enter a source URL: ");
	            	keyboardInput = input.nextLine();
	            	sourceURL = keyboardInput;
	            	if (sourceURL.indexOf(".") == -1)
				    {
				    	throw new IllegalArgumentException();
				    }
	            	System.out.print("Enter a destination URL: ");
	            	keyboardInput = input.nextLine();
	            	destURL = keyboardInput;
	            	if (destURL.indexOf(".") == -1)
				    {
				    	throw new IllegalArgumentException();
				    }
	            	sE.web.addLink(sourceURL, destURL);
            	}
            	catch (IllegalArgumentException iAE)
            	{
            		System.out.println();
            		System.out.println("Error: Please input an appropriate URL"
            		  + " next time.");
            		System.out.println();
            		continue;
            	}
            	catch (NonExistentURLException nEURL)
            	{
            		if (WebGraph.sourceCheck == false)
            		{
            			errorURL = sourceURL;
            		}
            		else if (WebGraph.destinationCheck == false)
            		{
            			errorURL = destURL;
            		}
            		System.out.println();
            		System.out.println("Error: " + errorURL + " could not be"
            		  + " found in the WebGraph.");
            		System.out.println();
            		continue;
            	}
            	catch (NullPointerException npE)
            	{
            		System.out.println();
            		System.out.println("Error: A link already exists from " + 
            		  sourceURL + " to " + destURL + "!");
            		System.out.println();
            		continue;
            	}
            	System.out.println();
            	System.out.println("Link successfully added from " + sourceURL 
            	  + " to " + destURL + "!");
            	System.out.println();
        		continue;
            }
            else if (keyboardInput.equalsIgnoreCase("RL"))
            {
            	String sourceURL = "";
            	String destURL = "";
            	try
            	{
	            	System.out.print("Enter a source URL: ");
	            	keyboardInput = input.nextLine();
	            	sourceURL = keyboardInput;
	            	if (sourceURL.indexOf(".") == -1)
				    {
				    	throw new IllegalArgumentException();
				    }
	            	System.out.print("Enter a destination URL: ");
	            	keyboardInput = input.nextLine();
	            	destURL = keyboardInput;
	            	if (destURL.indexOf(".") == -1)
				    {
				    	throw new IllegalArgumentException();
				    }
	            	sE.web.removeLink(sourceURL, destURL);
	            	System.out.println();
	            	System.out.println("Link removed from " + sourceURL + 
	            	  " to " + destURL + "!");
            	}
            	catch (IllegalArgumentException iAE)
            	{
            		System.out.println();
            		System.out.println("Error: Please input an appropriate URL"
            		  + " next time.");
            		System.out.println();
            		continue;
            	}
            	catch (NonExistentURLException nEURLE)
            	{
            		System.out.println();
            		System.out.println("Error: Link from " + sourceURL + " to "
            		  + destURL + " does not exist.");
            		System.out.println();
            		continue;
            	}
            	catch (NullPointerException npE)
            	{
            		System.out.println();
            		System.out.println("Error: No link exists from " + 
            		  sourceURL + " to " + destURL + "!");
            		System.out.println();
            		continue;
            	}
            	System.out.println();
            	continue;
            }
            else if (keyboardInput.equalsIgnoreCase("P"))
            {
            	option1 = false;
            	option2 = false;
            	option3 = false;
            	System.out.println();
            	System.out.println("    (I) - Sort based on index (ASC)\n    "
                  + "(U) - " + "Sort based on URL (ASC)\n" + "    "
            	  + "(R) - Sort based on rank (DSC)\n");
            	 System.out.print("Please select an option: ");
            	 keyboardInput = input.nextLine();
            	 String sorter = keyboardInput;
            	 if (sorter.equalsIgnoreCase("I"))
            	 {
            		 option1 = true;
            		 Collections.sort(sE.web.getList(), new IndexComparator());
            	 }
            	 else if (sorter.equalsIgnoreCase("U"))
            	 {
            		 option2 = true;
            		 Collections.sort(sE.web.getList(), new URLComparator());
            	 }
            	 else if (sorter.equalsIgnoreCase("R"))
            	 {
            		 option3 = true;
            		 Collections.sort(sE.web.getList(), new RankComparator());
            	 }
            	 else
            	 {
            		System.out.println();
              		System.out.println("Error: Please select a choice from the"
              		  + " menu next time.");
              		System.out.println();
              		continue;
            	 }
            	System.out.println();
            	sE.web.printTable();
            	System.out.println();
            	continue;
            }
            else if (keyboardInput.equalsIgnoreCase("S"))
            {
            	String keywordSearch = "";
            	try
            	{
	            	System.out.print("Search keyword: ");
	            	keyboardInput = input.nextLine();
	            	keywordSearch = keyboardInput;
	            	System.out.println();
	            	if (option3 == false)
	            	{
	            		Collections.sort(sE.web.getList(), new RankComparator());
	            	}
	            	sE.web.searchKeyword(keywordSearch);
	            	if (option1 == true)
	            	{
	            		Collections.sort(sE.web.getList(), new IndexComparator());
	            	}
	            	if (option2 == true)
	            	{
	            		Collections.sort(sE.web.getList(), new URLComparator());
	            	}
            	}
            	catch (SearchResultException sRE)
            	{
            		System.out.println("Error: No search results found for the"
            		  + " keyword " + keywordSearch + ".");
            		System.out.println();
            		continue;
            	}
            	System.out.println();
            	continue;
            }
            else if (keyboardInput.equalsIgnoreCase("Q"))
            {
            	/**
            	 * Exits out of the program efficiently
            	 */
                System.out.println("Goodbye.");
                menu = 1;
                System.exit(0);
            }
            else
            {
            	/**
            	 * Keeps on displaying the menu until an appropriate choice is
            	 *   selected from the menu
            	 */
            	System.out.println("Please input a choice from the menu.");
            	System.out.println("Make sure you have imported data from a "
            	  + "URL first.");
            	System.out.println();
            	continue;
            }
            keyboardInput = input.nextLine();
        }
	        while (menu == 0);
	     /**
	      * Menu has ended
	      */
	}
}