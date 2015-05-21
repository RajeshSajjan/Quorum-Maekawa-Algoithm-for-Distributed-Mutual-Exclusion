import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class testqueue {
	public static void main(String[] args)
	{
		Queue<String> a = new LinkedList<String>();
		HashSet<String> grant=new HashSet<String>();  
		a.add("10");
		a.add("20");
		a.add("30");
		
		
		grant.add("10");
		grant.add("20");
		grant.add("30");
		grant.add("40");
		
		if(grant.contains("10") && grant.contains("20") && grant.contains("50"))
			System.out.println("worked");
		
		
		Iterator in = a.iterator();
		while(in.hasNext())
		{
			System.out.println(in.next());
		}
		System.out.println(a.size());
		a.remove("20");
		Iterator in22 = a.iterator();
		while(in22.hasNext())
		{
			System.out.println(in22.next());
		}
		
		
		String s1 = "raj";
		String s2 = "rj";
		if(s1.equalsIgnoreCase(s2))
		{
			System.out.println("equal");
		}
		else
		{
			System.out.println("not equal");
		}
	}
}
