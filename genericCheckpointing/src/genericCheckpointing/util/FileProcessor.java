package genericCheckpointing.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class FileProcessor{
	BufferedReader reader;
	File file;
	/**
	 * Constructor. Takes in directory and ensures the file exists.<p>
	 * https://www.programcreek.com/2011/03/<p>
	 * java-read-a-file-line-by-line-code-example/
	 */
	public FileProcessor(String dir){
		file = new File(dir);
		if(file.exists()){		
			try{
				reader = new BufferedReader(new FileReader(file));
			}
			catch(IOException e){
				System.err.format("IOException: %s%n", e);
			}
		}
		else{
			System.out.println("File does not exist");
		}
	}

	/**
	 * Returns the next line in the text file. ensures file exists &<p>
	 * the next line is an int.<p>
	 * https://www.programcreek.com/2011/03/<p>
	 * java-read-a-file-line-by-line-code-example/<p>
	 * @return The next line in the text file
	 */
	public String readLine(){
		String line = "";
		if(file.exists()){
			try{
				line = reader.readLine();
				if(line != null)
					return line;
				else{
					return "-1";
				}
			}
			catch(IOException e){
				System.err.format("IOException: %s%n", e);
			}
		}
		else{
			System.out.println("No file to be read");
			return null;
		}
		return null;
	}
}
