import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.FileSystems;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ThreadTest.ThreadConfigurationTest.class,
	ThreadTest.ThreadOutputTest.class	
})
public class ThreadTest {

	/** Configure this on your system if you want to have a longer timeout. */
	public static final int TIMEOUT = 60000;

	public static class ThreadOutputTest {

		private void fullTest(String configFile, String expectedIndex, String expectedSearch, String testName) {
			try {
				ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
			} catch(IOException ioe) {
				//System.out.println(ioe.getMessage());
				ioe.printStackTrace();
				fail("Failed to copy configuration to correct location.");        		
			}        

			//check index output
			ProjectTest.checkProjectOutput(testName, ProjectTest.DEFAULT_ARGS, FileSystems.getDefault().getPath("output/result.txt"), 
					FileSystems.getDefault().getPath(expectedIndex));

			//check search output
			ProjectTest.checkProjectOutput(testName, ProjectTest.DEFAULT_ARGS, FileSystems.getDefault().getPath("output/searchresults.txt"), 
					FileSystems.getDefault().getPath(expectedSearch));


		}

		@Test(timeout = TIMEOUT)
		public void testSimpleTenThreads() {
			String configFile = "test/configurations/thread-simple-10.json";
			String expectedIndex = "output/index-simple-digitdelimitertrue.txt";
			String expectedSearch = "output/search-simple-digitdelimitertrue.txt";
			fullTest(configFile, expectedIndex, expectedSearch, "thread-simple-10");
		}

		@Test(timeout = TIMEOUT)
		public void testComplexTenThreads() {
			String configFile = "test/configurations/thread-complex-10.json";
			String expectedIndex = "output/index-all-digitdelimitertrue.txt";
			String expectedSearch = "output/search-complex-digitdelimitertrue.txt";
			fullTest(configFile, expectedIndex, expectedSearch, "thread-complex-10");        
		}

		@Test(timeout = TIMEOUT)
		public void testComplexOneThread() {
			String configFile = "test/configurations/thread-complex-1.json";
			String expectedIndex = "output/index-all-digitdelimitertrue.txt";
			String expectedSearch = "output/search-complex-digitdelimitertrue.txt";
			fullTest(configFile, expectedIndex, expectedSearch, "thread-complex-1");        
		}

		@Test(timeout = TIMEOUT)
		public void testComplexFiveThreads() {
			String configFile = "test/configurations/thread-complex-5.json";
			String expectedIndex = "output/index-all-digitdelimitertrue.txt";
			String expectedSearch = "output/search-complex-digitdelimitertrue.txt";
			fullTest(configFile, expectedIndex, expectedSearch, "thread-complex-5");        
		}

	}

	public static class ThreadConfigurationTest {

		@Test
		public void testNumberThreadsDecimal() {
			try {
				Configuration configuration = new Configuration(FileSystems.getDefault().getPath("test/configurations/threadsdecimal.json"));
				configuration.init();
				fail("No InitializationException thrown to indicate numberThreads is not a valid integer value.");
			} catch(InitializationException ie) {
				assertTrue("Configuration init should throw exception if numberThreads not a valid integer value.", ie.getMessage().contains("numberThreads is not a valid integer value"));

			}
		}


		@Test
		public void testNumberThreadsNegative() {
			try {
				Configuration configuration = new Configuration(FileSystems.getDefault().getPath("test/configurations/threadsnegative.json"));
				configuration.init();
				fail("No InitializationException thrown to indicate numberThreads is not a valid integer value.");
			} catch(InitializationException ie) {
				assertTrue("Configuration init should throw exception if numberThreads not a valid integer value.", ie.getMessage().contains("numberThreads is not a valid integer value"));

			}
		}

		@Test
		public void testNumberThreadsString() {
			try {
				Configuration configuration = new Configuration(FileSystems.getDefault().getPath("test/configurations/threadsstring.json"));
				configuration.init();
				fail("No InitializationException thrown to indicate numberThreads is not a valid integer value.");
			} catch(InitializationException ie) {
				assertTrue("Configuration init should throw exception if numberThreads not a valid integer value.", ie.getMessage().contains("numberThreads is not a valid integer value"));

			}
		}


		@Test
		public void testNumberThreadsTooLarge() {
			try {
				Configuration configuration = new Configuration(FileSystems.getDefault().getPath("test/configurations/threadstoolarge.json"));
				configuration.init();
				fail("No InitializationException thrown to indicate numberThreads is not a valid integer value.");
			} catch(InitializationException ie) {
				assertTrue("Configuration init should throw exception if numberThreads not a valid integer value.", ie.getMessage().contains("numberThreads is not a valid integer value"));

			}
		}

	}
}
