import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import lab.itunes.Library;
import lab.itunes.Playlist;
import lab.itunes.Track;

/**
 * Lab1.
 *
  */
public class Lab1 
{
    public static void main(String[] args)
    {
	Scanner input = new Scanner(System.in);
	PrintStream output = System.out;

	output.print("Enter the name of the iTunes library XML file: ");
	String fileName = input.next();

	try
	{
	    Library library = new Library(fileName);
	    Map<Integer, Set<String>> counts = new TreeMap<Integer, Set<String>>();

	    for (int i = 0; i < library.numberOfPlayLists(); i++)
	    {
		Playlist playlist = library.get(i);
		
		for (Integer ID : playlist)
		{
		    Track track = library.getTrack(ID);
		    int count = track.getPlayCount();
		    String name = track.getName();
		    if (!counts.containsKey(count))
		    {
			counts.put(count, new TreeSet<String>());
		    }
		    Set<String> names = counts.get(count);
		    names.add(name);
		}
	    }
		
	    StringBuffer ranking = new StringBuffer();
	    for (Integer count : counts.keySet())
	    {
		Set<String> names = counts.get(count);
		String temp = "";
		for (String name : names)
		{
		    temp += count + "\t" + name + "\n";
		}
		ranking.insert(0, temp);
	    }
	    
	    output.println(ranking);
	}
	catch (FileNotFoundException e)
	{
	    output.println("That file does not exist.");
	}
	catch (Exception e)
	{
	    output.println("That file is not in the right format.");
	}
    }
}
