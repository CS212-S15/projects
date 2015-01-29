import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class InvertedIndexTest {

	@Test
	public void testEmpty() {
		
		InvertedIndex ii = new InvertedIndex();
		assertTrue("InvertedIndex toString does not return expected result for index with no elements.", ii.toString().trim().isEmpty());
	}	
	
	@Test
	public void testAdd() {
		
		String[] doc1 = "One two three four CAT cat cat cat cat".toLowerCase().split("\\s+");
		
		String[] doc2 = "cat nine eight seven   dog dog dog dog".toLowerCase().split("\\s+");
		
		String[] doc3 = "cat dog elephant bird mouse dog rat rat rat rat".toLowerCase().split("\\s+");

		
		InvertedIndex ii = new InvertedIndex();
		
		int index = 0;
		for(String str: doc1) {
			ii.add(str, "doc1", ++index);
		}
		
		index = 0;
		for(String str: doc2) {
			ii.add(str, "doc2", ++index);
		}

		index = 0;
		for(String str: doc3) {
			ii.add(str, "doc3", ++index);
		}
		
		String expectedResult = "bird\n" + 
				"\"doc3\", 4\n" + 
				"\n" + 
				"cat\n" + 
				"\"doc1\", 5, 6, 7, 8, 9\n" + 
				"\"doc2\", 1\n" + 
				"\"doc3\", 1\n" + 
				"\n" + 
				"dog\n" + 
				"\"doc2\", 5, 6, 7, 8\n" + 
				"\"doc3\", 2, 6\n" + 
				"\n" + 
				"eight\n" + 
				"\"doc2\", 3\n" + 
				"\n" + 
				"elephant\n" + 
				"\"doc3\", 3\n" + 
				"\n" + 
				"four\n" + 
				"\"doc1\", 4\n" + 
				"\n" + 
				"mouse\n" + 
				"\"doc3\", 5\n" + 
				"\n" + 
				"nine\n" + 
				"\"doc2\", 2\n" + 
				"\n" + 
				"one\n" + 
				"\"doc1\", 1\n" + 
				"\n" + 
				"rat\n" + 
				"\"doc3\", 7, 8, 9, 10\n" + 
				"\n" + 
				"seven\n" + 
				"\"doc2\", 4\n" + 
				"\n" + 
				"three\n" + 
				"\"doc1\", 3\n" + 
				"\n" + 
				"two\n" + 
				"\"doc1\", 2";
		
		assertTrue("InvertedIndex toString does not return expected result for three documents.", expectedResult.equals(ii.toString().trim()));
	}
	
}
