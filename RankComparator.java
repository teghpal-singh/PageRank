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
 *  The <code>RankComparator</code> class basically sorts numerically 
 *    DESCENDING based on the PageRank of the <code>WebPage</code>.
 */

public class RankComparator implements Comparator
{
	/**
	 * This compares the PageRank of each WebPage to sort numerically 
	 *   DESCENDING based on the PageRank.
	 */
	public int compare(Object o1, Object o2) 
	{
		WebPage wP1 = (WebPage) o1;
		WebPage wP2 = (WebPage) o2;
		if (wP1.getRank() == wP2.getRank())
		{
            return 0;
		}
        else if (wP1.getRank() > wP2.getRank())
        {
            return -1;
        }
        else
        {
            return 1;
        }
	}
}