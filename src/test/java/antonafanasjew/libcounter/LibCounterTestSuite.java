package antonafanasjew.libcounter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import antonafanasjew.libcounter.processor.impl.LibraryInfoParserImplTest;
import antonafanasjew.libcounter.processor.impl.TopXAggregatorImplTest;

@RunWith(Suite.class)
@SuiteClasses({
	LibraryInfoParserImplTest.class, 
	TopXAggregatorImplTest.class
})
public class LibCounterTestSuite {

}
