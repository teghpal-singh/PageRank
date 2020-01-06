import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue; 
public class test 
{
	     
	public static void main(String[] args) 
	{
		/*
		String url = "google.com";
		int index = 0;
		int rank = 2;
		ArrayList<Integer> links = new ArrayList<Integer>();
		links.add(2);
		links.add(1);
		links.add(3);
		links.add(4);
		links.add(6);
		
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("search");
		keywords.add("knowledge");
		keywords.add("tech");
		*/
		
		/*
		String keywords = "search knowledge tech";
		keywords = keywords.replaceAll(" ", ", ");
		*/
		
		/*
		WebPage w = new WebPage(url, index, rank, links, keywords);
		System.out.println(w.toString());
		*/
		/*
		WebGraph test = WebGraph.buildFromFiles("pages.txt", "links.txt");
		test.printTable();
		*/
		/*
		System.out.println(test.getList().get(0).getKeywords());
		for (int a = 0; a < test.getList().size(); a++)
		{
			System.out.println(test.getList().get(a).toString());
		}
		*/
		/*
		int[][] links = test.getLinks();
		for (int a = 0; a < test.getList().size(); a++)
		{
			for (int b = 0; b < test.getList().size(); b++)
			{
				System.out.print(links[a][b] + " ");
			}
			System.out.println();
		}
		test.removePage("cnn.com");
		System.out.println();
		links = test.getLinks();
		for (int a = 0; a < test.getList().size(); a++)
		{
			for (int b = 0; b < test.getList().size(); b++)
			{
				System.out.print(links[a][b] + " ");
			}
			System.out.println();
		}
		System.out.println();
		test.printTable();
		*/
		/*
		Stack<Integer> iS = new Stack<Integer>();
		iS.push(3);
		String bob = "hek0l";
		if (bob.matches(".*\\d+.*"))
		{
			System.out.println("int");
		}
		System.out.println(iS.pop());
		*/
		/*
		int w[]={22,33,44}, z[]={55,66,77};
		z = w;
		System.out.println(z[0]);
		System.out.println(z[1]);
		*/
		int a = 6;
		int b = (a++)+a;
		System.out.println(a);
		System.out.println(b);
	}
}