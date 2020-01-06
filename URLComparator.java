import java.util.Comparator;

/**
 * Teghpal Singh
 * 112224062
 * teghpal.singh@stonybrook.edu
 * Programming Assignment Number 7
 * CSE 214
 * Recitation Number 05
 * Teacher Assistant: Thomas Povinelli
 * 
 * The <code>URLComparator</code> class basically sorts alphabetically 
 *   ASCENDING based on the URL of the <code>WebPage</code>.
 */

public class URLComparator implements Comparator
{
	/**
	 * This compares the URL of each WebPage to sort alphabetically ASCENDING
     *   based on the URL.
	 */
	 public int compare(Object o1, Object o2)
	 {
		 WebPage wP1 = (WebPage) o1;
		 WebPage wP2 = (WebPage) o2;
         return (wP1.getURL().compareTo(wP2.getURL()));
	 }
}