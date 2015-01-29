import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
    IndexTest.IndexArgumentTest.class,
    IndexTest.IndexOutputTest.class
})
public class IndexTest {

    /** Configure this on your system if you want to have a longer timeout. */
    public static final int TIMEOUT = 60000;

    public static final String[] DEFAULT_ARGS = {};
    public static final String DEFAULT_CONFIG_DESTINATION = "config.json";
    
    /**
     * Helper method to copy a configuration file to the appropriate location.
     * @param configFile
     * @param destination
     */
    public static void copyConfigFile(String configFile, String destination) throws IOException {
		
    	Path configPath = Paths.get(configFile);
    	Path destinationPath = Paths.get(destination);

    	Files.copy(configPath, destinationPath, REPLACE_EXISTING);

    }

    
    public static class IndexArgumentTest {
    	
        @Test(timeout = TIMEOUT)
        public void testBadInputPathKey() {
        	    		
        	String configFile = "test/configurations/badinputpathkey.json";
    		
        	try {
        		copyConfigFile(configFile, DEFAULT_CONFIG_DESTINATION);
        	} catch(IOException ioe) {
        		fail("Failed to copy configuration to correct location.");
        	}
        
        	
            ProjectTest.checkExceptions("Bad input path key", DEFAULT_ARGS);
            
        }

        @Test(timeout = TIMEOUT)
        public void testBadInputPathValue() {
        	
        	String configFile = "test/configurations/badinputpathvalue.json";
    		
        	try {
        		copyConfigFile(configFile, DEFAULT_CONFIG_DESTINATION);
        	} catch(IOException ioe) {
        		fail("Failed to copy configuration to correct location.");
        	}        
        	
            ProjectTest.checkExceptions("Bad input path value", DEFAULT_ARGS);
        }
        
    }

    public static class IndexOutputTest {
    	
    	
    	private void fullTest(String test, boolean digitDelimiterValue) {
    		fullTest(test, digitDelimiterValue, DEFAULT_CONFIG_DESTINATION, "Index " + test + " digitDelimiter " + digitDelimiterValue);
    	}    	
    	
    	private void fullTest(String test, boolean digitDelimiterValue, String destination, 
    			String output) {
    		
    		
    		String expected = "output/index-" + test + "-digitdelimiter" + digitDelimiterValue + ".txt";
    		String configFile = "test/configurations/" + test + "-digitdelimiter" + digitDelimiterValue + ".json";
    		
        	try {
        		copyConfigFile(configFile, destination);
        	} catch(IOException ioe) {
        		//System.out.println(ioe.getMessage());
        		ioe.printStackTrace();
        		fail("Failed to copy configuration to correct location.");        		
        	}        

            ProjectTest.checkProjectOutput(output, DEFAULT_ARGS, FileSystems.getDefault().getPath("output/result.txt"), 
            		FileSystems.getDefault().getPath(expected));

    	}
    	
        @Test(timeout = TIMEOUT)
        public void testIndexSimpleDigitDelimiterTrue() {
        	fullTest("simple", true);        	
        }
        
        @Test(timeout = TIMEOUT)
        public void testIndexSimpleDigitDelimiterFalse() {
        	fullTest("simple", false);        	
        }


        @Test(timeout = TIMEOUT)
        public void testIndexRFCsDigitDelimiterTrue() {
        	fullTest("rfcs", true);        	
        }
        
        @Test(timeout = TIMEOUT)
        public void testIndexRFCsDigitDelimiterFalse() {
        	fullTest("rfcs", false);        	
        }

        @Test(timeout = TIMEOUT)
        public void testIndexGutenDigitDelimiterTrue() {
        	fullTest("guten", true);        	
        }
        
        @Test(timeout = TIMEOUT)
        public void testIndexGutenDigitDelimiterFalse() {
        	fullTest("guten", false);        	
        }
        
        @Test(timeout = TIMEOUT)
        public void testIndexAllDigitDelimiterTrue() {
        	fullTest("all", true);        	
        }
        
        @Test(timeout = TIMEOUT)
        public void testIndexAllDigitDelimiterFalse() {
        	fullTest("all", false);        	
        }
        
        
/*
        @Test(timeout = TIMEOUT)
        public void testIndexRFCs() {
            String name = "index-rfcs.txt";

            Path input = Paths.get(ProjectTest.INPUT_DIR, "rfcs");
            Path output = Paths.get(ProjectTest.OUTPUT_DIR, name);
            Path result = Paths.get(ProjectTest.RESULT_DIR, name);

            String[] args = new String[] {
                    ProjectTest.DIR_FLAG, input.toString(),
                    ProjectTest.INDEX_FLAG, result.toString()
            };

            ProjectTest.checkProjectOutput("Index RFCs", args, result, output);
        }

        @Test(timeout = TIMEOUT)
        public void testIndexGutenberg() {
            String name = "index-guten.txt";

            Path input = Paths.get(ProjectTest.INPUT_DIR, "gutenberg");
            Path output = Paths.get(ProjectTest.OUTPUT_DIR, name);
            Path result = Paths.get(ProjectTest.RESULT_DIR, name);

            String[] args = new String[] {
                    ProjectTest.DIR_FLAG, input.toString(),
                    ProjectTest.INDEX_FLAG, result.toString()
            };

            ProjectTest.checkProjectOutput("Index Gutenberg", args, result, output);
        }

        @Test(timeout = TIMEOUT)
        public void testIndexAll() {
            String name = "index-all.txt";

            Path input = Paths.get(ProjectTest.INPUT_DIR);
            Path output = Paths.get(ProjectTest.OUTPUT_DIR, name);
            Path result = Paths.get(ProjectTest.RESULT_DIR, name);

            String[] args = new String[] {
                    ProjectTest.DIR_FLAG, input.toString(),
                    ProjectTest.INDEX_FLAG, result.toString()
            };

            ProjectTest.checkProjectOutput("Index All", args, result, output);
        }
   */
    }
    
   
}
