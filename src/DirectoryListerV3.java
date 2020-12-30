import java.io.*; //FileWriter, BufferedWriter, File

public class DirectoryListerV3 
{
	public DirectoryListerV3(String path)
	{	
		//CREATE BUFFERED WRITER
		try
		{
			bufferedWriter = new BufferedWriter(new PrintWriter(getOutputFile()));
		} 
		catch(NullPointerException e)
		{
			System.out.println(e.getLocalizedMessage());
			System.out.println("Please enter a valid directory value.");
		}
		catch(IOException e)
		{
			System.out.println(e.getLocalizedMessage());
			System.out.println("Could not create file.");
		}
		
		//LIST CONTENTS OF DIRECTORY
		listContents(path, level); 
		
		//DISPLAY TOTALS
		output("Total Files: " + totalFiles + "\n");
		output("Total Folders: " + totalFolders + "\n");
		
		//CLOSE FILES
		try
		{
			bufferedWriter.close();
		}
		catch(IOException e)
		{
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	/**
	 * Shows file hierarchy of a given directory through the console
	 * and saves a text copy in program directory
	 * @param directory File/Folder path
	 * @param level Number of layers deep from original directory, initial call should always provide 0 as argument
	 */
	public void listContents(String directory, int level)
	{	
		//OPEN DIRECTORY
		String[] subdirectories = null;
		try
		{
			subdirectories = new File(directory).list();
		}
		catch(NullPointerException e)
		{
			System.out.println(e.getLocalizedMessage());
			System.out.println("Issue with directory");
		}
		catch(SecurityException e)
		{
			System.out.println(e.getLocalizedMessage());
		}
		
		//Hierarchy Formatting
		if(level == 0)
		{
			output(">" + directory + "\n");
		}
		
		//LIST SUB DIRECTORIES AND FILES
		for(int i = 0; i < subdirectories.length; ++i)
		{	
			//CHECK ALL FOLDERS
			try
			{	
				//IS A FOLDER
				if(new File(directory + "/" + subdirectories[i]).isFile() == false)
				{
					//INCREMENT
					++totalFolders;
					
					//output folder name + formatting for hierarchical indication
					for(int j = 0; j <= level; j++)
					{
						output("  ");
					}
					output("|->");
					output(subdirectories[i] + "\n");
					
					//RECURSION
					listContents(new File(directory + "/" + subdirectories[i]).toString(), (level + 1));
				}
				//IS A FILE
				else if (new File(directory + "/" + subdirectories[i]).isFile() == true)
				{
					{
						//INCREMENT
						++totalFiles;
						
						//output file name + formatting for hierarchical indication
						for(int j = 0; j <= level; j++)
						{
							output("  ");
						}
						output("|-");
						output(subdirectories[i] + "\n");
					}
				}
			} 
			catch(NullPointerException e)
			{
				System.out.println("ISSUE ENCOUNTERED WITH DIRECTORY: " + directory);
			}
			
		}
	}
	
	private File getOutputFile()
	{
		File output = null;
		try
		{
			output = new File("output.txt");
			output.createNewFile();
		} 
		catch(NullPointerException e)
		{
			System.out.println(e.getLocalizedMessage());
			System.out.println("Please enter a valid directory value.");
		}
		catch(IOException e)
		{
			System.out.println(e.getLocalizedMessage());
			System.out.println("Could not create file.");
		}
		return output;
	}
	
	/**
	 * CREATE OUTPUT FILE
	 */
	private void output(String data)
	{
		try
		{
			System.out.print(data);
			bufferedWriter.write(data);
		}
		catch(IOException e)
		{
			System.out.println(e.getLocalizedMessage());
			System.out.println("Could not create file.");
		}
		
		
	}
	
	private BufferedWriter bufferedWriter;
	private static int totalFolders = 0, totalFiles = 0;
	private final int level = 0;
}
