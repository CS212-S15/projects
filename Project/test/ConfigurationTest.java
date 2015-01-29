

import static org.junit.Assert.*;

import java.nio.file.FileSystems;

import org.junit.Test;

public class ConfigurationTest {

	@Test
	public void testGoodConfiguration() {
		try {
			Configuration configuration = new Configuration(FileSystems.getDefault().getPath("test/configurations/good.json"));
			configuration.init();
			assertTrue("Configuration getInputPath returns incorrect result.", "input/gutenberg".equals(configuration.getInputPath().trim()));
			assertTrue("Configuration getOutputPath should return null if no output path specified.", configuration.getOutputPath()==null);
			assertFalse("Configuration useDigitDelimiter returns incorrect result.", (configuration.useDigitDelimiter()));
		} catch(InitializationException ie) {
			fail(ie.getMessage());
		}
	}

	@Test
	public void testContainsOutputPath() {
		try {
			Configuration configuration = new Configuration(FileSystems.getDefault().getPath("test/configurations/goodwithoutputpath.json"));
			configuration.init();
			assertTrue("Configuration getOutputPath returns incorrect result.", "output/result.txt".equals(configuration.getOutputPath().trim()));
		} catch(InitializationException ie) {
			fail(ie.getMessage());
		}
	}
	
	@Test
	public void testBadInputPathKey() {
		try {
			Configuration configuration = new Configuration(FileSystems.getDefault().getPath("test/configurations/badinputpathkey.json"));
			configuration.init();
			fail("No InitializationException thrown to indicate inputPath is not specified in configuration file.");
		} catch(InitializationException ie) {
			assertTrue("Configuration init should throw exception if inputPath not specified.", ie.getMessage().contains("inputPath not specified"));
			
		}
	}

	@Test
	public void testBadDigitDelimiterKey() {
		try {
			Configuration configuration = new Configuration(FileSystems.getDefault().getPath("test/configurations/baddigitdelimiterkey.json"));
			configuration.init();
			fail("No InitializationException thrown to indicate digitDelimiter is not specified in configuration file.");
		} catch(InitializationException ie) {
			assertTrue("Configuration init should throw exception if digitDelimiter not specified.", ie.getMessage().contains("digitDelimiter not specified"));
			
		}
	}

	@Test
	public void testBadFileName() {
		try {
			Configuration configuration = new Configuration(FileSystems.getDefault().getPath("test/configurations/UNKNOWN.json"));
			configuration.init();
			fail("No InitializationException thrown to indicate inability to open file.");
		} catch(InitializationException ie) {
			assertTrue("Configuration init should throw exception if unable to open file.", ie.getMessage().contains("Unable to open file"));
			
		}
	}

	@Test
	public void testUnparseableJSON() {
		try {
			Configuration configuration = new Configuration(FileSystems.getDefault().getPath("test/configurations/bad.json"));
			configuration.init();
			fail("No InitializationException thrown to indicate unparseable JSON.");
		} catch(InitializationException ie) {
			assertTrue("Configuration init should throw exception if unable to parse JSON.", ie.getMessage().contains("Unable to parse file"));
			
		}
	}
	
	@Test
	public void testBadDigitDelimiterValue() {
		try {
			Configuration configuration = new Configuration(FileSystems.getDefault().getPath("test/configurations/baddigitdelimitervalue.json"));
			configuration.init();
			fail("No InitializationException thrown to indicate digitDelimiter is not a boolean value.");
		} catch(InitializationException ie) {
			assertTrue("Configuration init should throw exception if digitDelimiter not a boolean value.", ie.getMessage().contains("digitDelimiter not a boolean"));
			
		}
	}
	
}
