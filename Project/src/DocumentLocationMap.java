import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Stores a mapping from a document (a file name) to a set of integers that represent the
 * locations where a word appears in the document.
 * @author srollins
 *
 */
public class DocumentLocationMap {

	/**
	 * It is up to you to decide what the data members for this class will look like, but
	 * make sure to think about efficiency!
	 */
	
	
	/**
	 * Intitialize an empty map.
	 * @param word - the word that this mapping represents included
	 * 					for convenience
	 */
	public DocumentLocationMap(String word) {
	}
	
	
	/**
	 * Add a new location for a given file. The first word in a file is at location 1, the second
	 * word at location 2, and so on.
	 * @param fileName - the name of the file where the word appears.
	 * @param location - the location in the file where the word appears.
	 */
	public void addLocation(String fileName, int location) {		
	}
	
	/**
	 * Returns true if there is already a mapping from the given fileName to the location 
	 * specified and false otherwise. This method is used for convenience and sanity checking
	 * to ensure that the same location is not added multiple times.
	 * @param fileName
	 * @param location
	 * @return
	 */
	public boolean contains(String fileName, int location) {
		return false;
	}
	
	private String toString(String fileName) {
		return null;
	}
	
	/**
	 * Return a string representation of this mapping. Keep in mind that concatenating immutable String objects
	 * is extremely inefficient! Hint: my main toString method iterated over the keys in the mapping and called
	 * a helper method to build each line of the result. The format of the return value will be as follows:
	 * "filename1", location1, location2, location3
	 * "filename2", location1, location2, location3
	 * Example: 
	 * "input/gutenberg/11.txt", 1, 3, 15, 19
	 * "input/gutenberg/1322.txt", 5, 18, 19
	 * @param fileName
	 * @return
	 */
	public String toString() {
		return null;
	}
	
	public static void main(String[] args) {
	
		DocumentLocationMap map = new DocumentLocationMap("sample");
		//map.addLocation("test1", 1);
		//map.addLocation("test1", 5);
		map.addLocation("test2", 12);
		map.addLocation("test1", 2);
		//map.addLocation("test2", 1);
		map.addLocation("test3", 19);
		System.out.println(map);
		
	}
	
}
