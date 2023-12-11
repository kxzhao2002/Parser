
/**
 *
 * lexical analyser for RDP exercise.
 *
 * This class has been provided to students
 *
 * Author: Roger Garside, John Mariani, John Vidler, Paul Rayson
 *
 *
 **/
import java.io.* ;
import java.util.Scanner;

public class Token_extract
{
	/** The EOF character. */
	private static final char EOF = '\000' ;
	/** An input stream from the filename mentioned above. */
	private BufferedReader sourceFile ;
	String[] current_token;
	String text;
	String sy;
	private String currentLine ;

	/** Creates a new LexicalAnalyser which will run over the given file.

	 @param fileName The file to read.
	 @throws IOException if any read errors occur during parsing.
	 */
	public Token_extract(String fileName) throws IOException
	{
		sourceFile = new BufferedReader(new FileReader(fileName)) ;
		currentLine = sourceFile.readLine() ;
	} // end of constructor method
	public Token getNextToken() throws IOException
	{
		if (currentLine == null) {
			// Handle the end of file or initial state appropriately
			// For example, you might want to return a special end-of-file token
			// or throw an exception if the file has not been read properly
			return new Token(35, "EOF");}
		current_token = currentLine.split(", ");
		text = current_token[0].substring(1,current_token[0].length());
		sy = current_token[1].substring(0,current_token[1].length() - 1);
		currentLine = sourceFile.readLine();
		return new Token(Integer.parseInt(sy),text);

	} // end of method getNextToken

} // end of class LexicalAnalyser