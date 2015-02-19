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
    IndexTest.IndexConfigurationTest.class,
    IndexTest.IndexOutputTest.class
})
public class IndexTest {

    /** Configure this on your system if you want to have a longer timeout. */
    public static final int TIMEOUT = 60000;
    
    public static class IndexConfigurationTest {
    	
        @Test(timeout = TIMEOUT)
        public void testBadInputPathKey() {
        	    		
        	String configFile = "test/configurations/badinputpathkey.json";
    		
        	try {
        		ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
        	} catch(IOException ioe) {
        		fail("Failed to copy configuration to correct location.");
        	}
        
        	
            ProjectTest.checkExceptions("Bad input path key", ProjectTest.DEFAULT_ARGS);
            
        }

        @Test(timeout = TIMEOUT)
        public void testBadInputPathValue() {
        	
        	String configFile = "test/configurations/badinputpathvalue.json";
    		
        	try {
        		ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
        	} catch(IOException ioe) {
        		fail("Failed to copy configuration to correct location.");
        	}        
        	
            ProjectTest.checkExceptions("Bad input path value", ProjectTest.DEFAULT_ARGS);
        }
        
    }

    public static class IndexOutputTest {
    	
    	
    	private void fullTest(String test, boolean digitDelimiterValue) {
    		fullTest(test, digitDelimiterValue, ProjectTest.DEFAULT_CONFIG_DESTINATION, "Index " + test + " digitDelimiter " + digitDelimiterValue);
    	}    	
    	
    	private void fullTest(String test, boolean digitDelimiterValue, String destination, 
    			String output) {
    		
    		
    		String expected = "output/index-" + test + "-digitdelimiter" + digitDelimiterValue + ".txt";
    		String configFile = "test/configurations/" + test + "-digitdelimiter" + digitDelimiterValue + ".json";
    		
        	try {
        		ProjectTest.copyConfigFile(configFile, destination);
        	} catch(IOException ioe) {
        		//System.out.println(ioe.getMessage());
        		ioe.printStackTrace();
        		fail("Failed to copy configuration to correct location.");        		
        	}        

            ProjectTest.checkProjectOutput(output, ProjectTest.DEFAULT_ARGS, FileSystems.getDefault().getPath("output/result.txt"), 
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
        
        
    }
    
   
}
