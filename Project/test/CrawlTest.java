import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	CrawlTest.ExceptionTester.class,
	CrawlTest.LocalTester.class
})
public class CrawlTest {

	public static final int TIMEOUT = 60000;

	/**
	 * This class tests whether {@link Driver} runs without generating
	 * an exception.
	 *
	 * You can right-click this class to run just these set of tests!
	 */
	public static class ExceptionTester {


		@Test(timeout = TIMEOUT)
		public void testBadURL() {
			String configFile = "test/configurations/bad-url.json";

			try {
				ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
			} catch(IOException ioe) {
				fail("Failed to copy configuration to correct location.");
			}

			ProjectTest.checkExceptions("Invalid URL", ProjectTest.DEFAULT_ARGS);
		}

		@Test(timeout = TIMEOUT)
		public void testBadHost() {
			String configFile = "test/configurations/bad-host.json";

			try {
				ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
			} catch(IOException ioe) {
				fail("Failed to copy configuration to correct location.");
			}

			ProjectTest.checkExceptions("Invalid URL", ProjectTest.DEFAULT_ARGS);
		}

	}

	/**
	 * This class tests whether {@link Driver} produces the correct
	 * inverted index and search result output for different LOCAL seed urls.
	 * Test these BEFORE running the external seeds to avoid spamming those
	 * web servers.
	 *
	 * You can right-click this class to run just these set of tests!
	 */
	public static class LocalTester {

		@Test(timeout = TIMEOUT)
		public void testIndexBirds() {
			String test = "birds";
			String configFile = "test/configurations/crawl-" + test + ".json";

			try {
				ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
			} catch(IOException ioe) {
				fail("Failed to copy configuration to correct location.");
			}


			testCrawlIndexOutput(test);
		}

		@Test(timeout = TIMEOUT)
		public void testSearchBirds() {
			String test = "birds";
			String configFile = "test/configurations/crawl-" + test + ".json";

			try {
				ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
			} catch(IOException ioe) {
				fail("Failed to copy configuration to correct location.");
			}

			testCrawlSearchOutput(test);
		}

		@Test(timeout = TIMEOUT)
		public void testIndexYellow() {
			String test = "yellow";
			String configFile = "test/configurations/crawl-" + test + ".json";

			try {
				ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
			} catch(IOException ioe) {
				fail("Failed to copy configuration to correct location.");
			}


			testCrawlIndexOutput(test);

		}

		@Test(timeout = TIMEOUT)
		public void testSearchYellow() {
			String test = "yellow";
			String configFile = "test/configurations/crawl-" + test + ".json";

			try {
				ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
			} catch(IOException ioe) {
				fail("Failed to copy configuration to correct location.");
			}

			testCrawlSearchOutput(test);
		}

		@Test(timeout = TIMEOUT)
		public void testIndexHolmes() {
			String test = "holmes";
			String configFile = "test/configurations/crawl-" + test + ".json";

			try {
				ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
			} catch(IOException ioe) {
				fail("Failed to copy configuration to correct location.");
			}


			testCrawlIndexOutput(test);
		}

		@Test(timeout = TIMEOUT)
		public void testSearchHolmes() {
			String test = "holmes";
			String configFile = "test/configurations/crawl-" + test + ".json";

			try {
				ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
			} catch(IOException ioe) {
				fail("Failed to copy configuration to correct location.");
			}

			testCrawlSearchOutput(test);
		}

		@Test(timeout = TIMEOUT)
		public void testIndexGuten() {
			String test = "gutenweb";
			String configFile = "test/configurations/crawl-" + test + ".json";

			try {
				ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
			} catch(IOException ioe) {
				fail("Failed to copy configuration to correct location.");
			}

			testCrawlIndexOutput(test);
		}

		@Test(timeout = TIMEOUT)
		public void testSearchGuten() {
			String test = "gutenweb";
			String configFile = "test/configurations/crawl-" + test + ".json";

			try {
				ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
			} catch(IOException ioe) {
				fail("Failed to copy configuration to correct location.");
			}

			testCrawlSearchOutput(test);
		}
	}

	/**
	 * This class tests whether {@link Driver} produces the correct
	 * inverted index and search result output for different EXTERNAL seed urls.
	 * Test these AFTER running the local seeds to avoid spamming those
	 * web servers.
	 *
	 * WARNING: This class is NOT run by default by this tester class. Instead,
	 * it is run by the {@link Project4} test suite. If you want to run these
	 * tests ONLY, please right-click the class and "Run-As" a Junit test.
	 */
	public static class ExternalTester {

		@Test(timeout = TIMEOUT)
		public void testIndexHTMLHelp() {
			String test = "htmlhelp";
			String configFile = "test/configurations/crawl-" + test + ".json";

			try {
				ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
			} catch(IOException ioe) {
				fail("Failed to copy configuration to correct location.");
			}

			testCrawlIndexOutput(test);

		}
		
		@Test(timeout = TIMEOUT)
		public void testSearchHTMLHelp() {
			String test = "htmlhelp";
			String configFile = "test/configurations/crawl-" + test + ".json";

			try {
				ProjectTest.copyConfigFile(configFile, ProjectTest.DEFAULT_CONFIG_DESTINATION);
			} catch(IOException ioe) {
				fail("Failed to copy configuration to correct location.");
			}

			testCrawlSearchOutput(test);
		}

	}

	private static void testCrawlIndexOutput(String test) {
		String expect = "index-" + test + ".txt";
		String actual = "output/result.txt";

		Path output = Paths.get(ProjectTest.OUTPUT_DIR, expect);
		Path result = Paths.get(actual);

		ProjectTest.checkProjectOutput("Index: " + test, ProjectTest.DEFAULT_ARGS, result, output);
	}

	private static void testCrawlSearchOutput(String test) {
		String expect = "search-" + test + ".txt";
		String actual = "output/searchresults.txt";

		Path output = Paths.get(ProjectTest.OUTPUT_DIR, expect);
		Path result = Paths.get(actual);

		ProjectTest.checkProjectOutput("Index: " + test, ProjectTest.DEFAULT_ARGS, result, output);
	}

}