
/**
 *
 * Main Driver program for 312 exercise.
 * 
 * This class has been provided to students
 *
 * @Author: Roger Garside, John Mariani, John Vidler, Paul Rayson
 *
 *
 **/

import java.io.* ;
import java.util.Stack;

public class Compile {

	public static String fileName;
	private static final String INDENT = "    "; // 4 spaces for indentation

	/**
	 *
	 * main
	 *
	 **/

	private void go() throws IOException {
		PrintStream out = null;
		boolean goon = true;
		String outputFile = new String( "output1.txt" );

		try {
			out = new PrintStream( new FileOutputStream(outputFile) );
		} catch( Exception e ) {
			System.out.println("unable to open output file "+e);
			System.exit(0);
		}

		while( goon ) {
			fileName = "test.txt";
			goon = ((new File(fileName)).exists());
			if( goon ) {
				System.out.println();

				SyntaxAnalyser syn = new SyntaxAnalyser(fileName) ;
				System.setOut(out);
				System.out.println( "RDPSTART" );
				System.out.println( "RDPFILE " + fileName );
				syn.parse( out ) ;
				System.out.println() ;
				System.out.println("RDPFINISH") ;

				break;
			} else System.out.println(fileName+" does not exist");
		}

		out.flush();out.close();
	} // end of main method
	public static void convertToTree(String filePath, String outputFilePath) {
		File inputFile = new File(filePath);
		File outputFile = new File(outputFilePath);

		Stack<String> stack = new Stack<>();
		int currentIndentation = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
			 PrintWriter out = new PrintWriter(new FileWriter(outputFile))) {

			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains("BEGIN")) {
					printWithIndentation(out, currentIndentation, line);
					// Increase indentation and push to stack
					stack.push(line);
					currentIndentation++;
				} else if (line.contains("END")) {
					// Pop from stack and decrease indentation
					if (!stack.isEmpty()) stack.pop();
					currentIndentation--;
					printWithIndentation(out, currentIndentation, line);
				} else {
					// Print tokens and other lines with current indentation
					printWithIndentation(out, currentIndentation, line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void printWithIndentation(PrintWriter out, int indentationLevel, String content) {
		for (int i = 0; i < indentationLevel; i++) {
			out.print(INDENT);
		}
		out.println(content.trim());
	}

	public static void main(String args[]) throws IOException {
		Compile c = new Compile();
		c.go();
		String outputFile = new String( "output1.txt" );
		String outputFilePath = "output.txt";
		convertToTree(outputFile, outputFilePath);
		File fileToDelete = new File(outputFile);
		fileToDelete.delete();
	};
} // end of class Compile
