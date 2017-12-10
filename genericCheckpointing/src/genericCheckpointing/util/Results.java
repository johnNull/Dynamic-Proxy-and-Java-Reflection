package genericCheckpointing;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import genericCheckpointing.util.FileIOInterface;
public class Results implements FileDisplayInterface, StdoutDisplayInterface, FileIOInterface{
	private String result = "", directory;
	private BufferedWriter bw;
	private FileWriter fw;
	private PrintWriter write;
	/**
	 * Constructor for Results, sets output.txt directory
	 */
	public Results(String dir){
		directory = dir;
		try{
			if(directory != null && directory.contains(".txt")){
				fw = new FileWriter(dir, true);
				bw = new BufferedWriter(fw);
				write = new PrintWriter(bw);
				write.println("results created");
				fw.write("From FileWriter");
			}
			else
				System.err.format("No output file specified");
		}
		catch(IOException e){
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Write the sum and test results to specified file<p>
	 * https://stackoverflow.com/questions/2885173/<p>
	 * how-do-i-create-a-file-and-write-to-it-in-java
	 */	
	public void writeToFile(){
		try{
			if(directory != null && directory.contains(".txt")){
				PrintWriter writer = new PrintWriter(directory, "UTF-8");
				writer.println(result);
				writer.close();
			}
			else
				System.err.format("No output file specified");
		}
		catch(IOException e){
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Write the sum and test results to specified file
	 */
	public void writeToStdout(){
		System.out.println(result);
	}
	
	/**
	 *Add test results to result string
	 */
	public void append(String s){
		result += s;
	}

	public void write(String s){
		System.out.println("Writing?");
		write.println(s);
	}
}
