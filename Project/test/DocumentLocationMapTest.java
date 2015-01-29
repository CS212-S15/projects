import static org.junit.Assert.*;
import org.junit.Test;

public class DocumentLocationMapTest {

	
	
	@Test
	public void testBasicAddLocation() {
		
		/*
		 * Expected Output
		 * 
		 	"test1", 2
			"test2", 12
			"test3", 19
		 * 
		 * 
		 */
		
		String expectedOutput = "\"test1\", 2" + "\n" +
								"\"test2\", 12" + "\n" +
								"\"test3\", 19" + "\n";
		
		DocumentLocationMap map = new DocumentLocationMap("sample");
		map.addLocation("test2", 12);
		map.addLocation("test1", 2);
		map.addLocation("test3", 19);
		assertTrue("DocumentLocationMap toString produces incorrect output", expectedOutput.equals(map.toString()));

	}

	@Test
	public void testOutOfOrderAddLocation() {
		
		/*
		 * Expected Output
		 * 
		 	"test1", 1, 2, 5
			"test2", 1, 12
			"test3", 19
		 * 
		 * 
		 */
		
		String expectedOutput = "\"test1\", 1, 2, 5" + "\n" +
								"\"test2\", 1, 12" + "\n" +
								"\"test3\", 19" + "\n";
		
		DocumentLocationMap map = new DocumentLocationMap("sample");
		map.addLocation("test1", 1);
		map.addLocation("test1", 5);
		map.addLocation("test2", 12);
		map.addLocation("test1", 2);
		map.addLocation("test2", 1);
		map.addLocation("test3", 19);
		assertTrue("DocumentLocationMap toString produces incorrect output", expectedOutput.equals(map.toString()));

	}

	@Test
	public void testContainsTrue() {
		DocumentLocationMap map = new DocumentLocationMap("sample");
		map.addLocation("test1", 1);
		map.addLocation("test1", 5);
		map.addLocation("test2", 12);
		map.addLocation("test1", 2);
		map.addLocation("test2", 1);
		map.addLocation("test3", 19);
		assertTrue("\"DocumentLocationMap contains produces incorrect output for true query (\"test1\", 5)", map.contains("test1", 5));
		assertTrue("\"DocumentLocationMap contains produces incorrect output for true query (\"test2\", 12)", map.contains("test2", 12));
	}

	@Test
	public void testContainsFalse() {
		DocumentLocationMap map = new DocumentLocationMap("sample");
		map.addLocation("test1", 1);
		map.addLocation("test1", 5);
		map.addLocation("test2", 12);
		map.addLocation("test1", 2);
		map.addLocation("test2", 1);
		map.addLocation("test3", 19);
		assertFalse("\"DocumentLocationMap contains produces incorrect output for false query (\"test1\", 55)", map.contains("test1", 55));
		assertFalse("\"DocumentLocationMap contains produces incorrect output for false query (\"test1\", 12)", map.contains("test1", 12));		
	}

}
