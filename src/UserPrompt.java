import java.util.Scanner;
import java.io.File;
import java.lang.Exception;

public class UserPrompt 
{
	Scanner scan = new Scanner(System.in);
	public UserPrompt()
	{
		String input; // user input
		System.out.println("Enter a folder path or enter ''test'' to use sample path");
		input = scan.nextLine();
		try
		{
			//USER ENTERS "TEST"
			if(input.equalsIgnoreCase("test") == true)
			{
				new DirectoryListerV3("ListerTest");
			}
			else
			{
				//FILE LOCATION ENTERED
				if(new File(input).isFile() == true)
				{
					System.out.println("\nThis doesn't work the best right now.");
				}
				new DirectoryListerV3(input);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}
	}
}
