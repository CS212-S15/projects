import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    SearchTest.SearchConfigurationTest.class,
    SearchTest.SearchOutputTest.class
})
public class SearchTest {

    /** Configure this on your system if you want to have a longer timeout. */
    public static final int TIMEOUT = 60000;


    public static class SearchConfigurationTest {

        @Test(timeout = TIMEOUT)
        public void testMissingQueryPath() {
        	
        	String configFile = "test/configurations/search-missingquery.json";
    		
        	try {
        		ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
        	} catch(IOException ioe) {
        		fail("Failed to copy configuration to correct location.");
        	}
        	
            ProjectTest.checkExceptions("Missing query path", ProjectTest.DEFAULT_ARGS);

        }

        @Test(timeout = TIMEOUT)
        public void testBadQueryPath() {
        	String configFile = "test/configurations/search-badquery.json";
    		
        	try {
        		ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
        	} catch(IOException ioe) {
        		fail("Failed to copy configuration to correct location.");
        	}
        	
            ProjectTest.checkExceptions("Bad query path", ProjectTest.DEFAULT_ARGS);

        }

        @Test(timeout = TIMEOUT)
        public void testDefaultOutput() throws IOException {
        	String configFile = "test/configurations/search-defaultoutput.json";
    		
        	try {
        		ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
        	} catch(IOException ioe) {
        		fail("Failed to copy configuration to correct location.");
        	}

        	
        	Path result = Paths.get(ProjectTest.SEARCH_DEFAULT);

            Files.deleteIfExists(result);
            ProjectTest.checkExceptions("Default Output", ProjectTest.DEFAULT_ARGS);

            String errorMessage = String.format(
                    "%n" + "Test Case: %s%n" + "%s%n",
                    "Default Output",
                    "Output to " + ProjectTest.SEARCH_DEFAULT + " if output path not provided");
             

            Assert.assertTrue(errorMessage, Files.isReadable(result));
        }
	

        /**
         * Tests whether program fails gracefully when attempt to write to
         * a directory instead of a file path.
         */
        @Test(timeout = TIMEOUT)
        public void testNoWriteDirectory() {
        	
        	String configFile = "test/configurations/search-testouttodir.json";
    		
        	try {
        		ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
        	} catch(IOException ioe) {
        		fail("Failed to copy configuration to correct location.");
        	}

        	


            try {
                Path path = Files.createTempDirectory(Paths.get(".").normalize(), "temp");
                path.toFile().deleteOnExit();


                Driver.main(ProjectTest.DEFAULT_ARGS);
            }
            catch (Exception e) {
                StringWriter writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));

                Assert.fail(String.format(
                        "%n" + "Test Case: %s%n" + "Exception: %s%n",
                        "No Write Directory", writer.toString()));
            }
        }
        
    }

    public static class SearchOutputTest {

    	
    	
    	private void fullTest(String test, boolean digitDelimiterValue) {
    		fullTest(test, digitDelimiterValue, ProjectTest.DEFAULT_CONFIG_DESTINATION, "Search " + test + " digitDelimiter " + digitDelimiterValue);
    	}    	
    	
    	private void fullTest(String test, boolean digitDelimiterValue, String destination, 
    			String output) {
    		
    		
    		String expected = "output/search-" + test + "-digitdelimiter" + digitDelimiterValue + ".txt";
    		String configFile = "test/configurations/search-" + test + "-digitdelimiter" + digitDelimiterValue + ".json";
    		
        	try {
        		ProjectTest.copyConfigFile(configFile, destination);
        	} catch(IOException ioe) {
        		//System.out.println(ioe.getMessage());
        		ioe.printStackTrace();
        		fail("Failed to copy configuration to correct location.");        		
        	}        

            ProjectTest.checkProjectOutput(output, ProjectTest.DEFAULT_ARGS, FileSystems.getDefault().getPath("output/searchresults.txt"), 
            		FileSystems.getDefault().getPath(expected));

    	}
    	
        @Test(timeout = TIMEOUT)
        public void testSearchAlphaAllDigitDelimiterTrue() {
        	fullTest("alpha", true);        	
        }
        
        @Test(timeout = TIMEOUT)
        public void testSearchAlphaAllDigitDelimiterFalse() {
        	fullTest("alpha", false);        	
        }

        @Test(timeout = TIMEOUT)
        public void testSearchComplexAllDigitDelimiterTrue() {
        	fullTest("complex", true);        	
        }
        
        @Test(timeout = TIMEOUT)
        public void testSearchComplexAllDigitDelimiterFalse() {
        	fullTest("complex", false);        	
        }

        @Test(timeout = TIMEOUT)
        public void testSearchSimpleAllDigitDelimiterTrue() {
        	fullTest("simple", true);        	
        }
        
        @Test(timeout = TIMEOUT)
        public void testSearchSimpleAllDigitDelimiterFalse() {
        	fullTest("simple", false);        	
        }
    }
}
