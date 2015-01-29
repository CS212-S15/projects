import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.TreeMap;

/**
 * Maintains a mapping from a word to a list of documents and positions in those documents where the word was found.
 * See the project description for more information: https://github.com/CS212-S15/projects/blob/master/specifications/project1.md
 * @author srollins
 *
 */
public class InvertedIndex {

	/**
	 * It is up to you to decide what the data members for this class will look like, but
	 * make sure to think about efficiency!
	 * 
	 * Hint, think about how to use objects of type DocumentLocationMap.
	 */

	/**
	 * Constructor to instantiate a new InvertedIndex
	 */
	public InvertedIndex() {

	}
	
	/**
	 * Adds a new word to the index. If the word is already in the index, the method simply adds a new document/position.
	 * 
	 * @param word - the word to be added
	 * @param fileName - the name of the document where the word is found
	 * @param location - the position in the document where the word is found.
	 */
	public void add(String word, String fileName, int location) {		
	}

	/**
	 * Returns a string representation of the index. 
	 * See the project specification for the required format of the String representation
	 * of the index. Your output must match exactly to pass all tests.
	 */
	public String toString() {		
		return null;
	}	
	
	/**
	 * Optional method. I used this method to save the string representation of the index to a file.
	 * @param fileName
	 */
	public void printToFile(Path fileName) {
	}
	

	public static void main(String[] args) {	
		InvertedIndex ii = new InvertedIndex();
		ii.add("hello", "test1", 1);
		ii.add("hello", "test1", 10);
		ii.add("animal", "test1", 4);
		ii.add("hello", "test2", 8);
		ii.add("animal", "test2", 1);
		ii.add("hello", "test2", 4);
		ii.add("bob", "test3", 4);
		System.out.println(ii);
		
	}
	
}
