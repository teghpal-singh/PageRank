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
 * The <code>IndexComparator</code> class basically sorts numerically ASCENDING based
 *   on index of the <code>WebPage</code>.
 */

public class IndexComparator implements Comparator
{
	/**
	 * This compares the indices of each WebPage to sort numerically ASCENDING
	 *   based on the index.
	 */
	public int compare(Object o1, Object o2) 
	{
		WebPage wP1 = (WebPage) o1;
		WebPage wP2 = (WebPage) o2;
		if (wP1.getIndex() == wP2.getIndex())
		{
            return 0;
		}
        else if (wP1.getIndex() > wP2.getIndex())
        {
            return 1;
        }
        else
        {
            return -1;
        }
	}
}