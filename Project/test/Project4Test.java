import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ThreadTest.class, SearchTest.class, ConfigurationTest.class, DocumentLocationMapTest.class,
	InvertedIndexTest.class, ProjectTest.class, IndexTest.class,ThreadTest.class, CrawlTest.class, CrawlTest.ExternalTester.class}) 
public class Project4Test {

	/*
	 * To receive a 100% on this project, you must pass this test
	 * suite on the lab computers.
	 */
}