package antonafanasjew.libcounter.processor.impl;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import antonafanasjew.libcounter.model.LibraryInfo;
import antonafanasjew.libcounter.model.WebResource;

public class LibraryInfoParserImplTest {

	@Test
	public void testScriptFinding() throws ParseException {
		LibraryInfoParserImpl testObject = new LibraryInfoParserImpl();
		List<LibraryInfo> libraryInfos = testObject.parse(WebResource.fromText("<script src=\"scriptAtStart.js\"/>some text<script src=\"scriptInTheMiddle.js\"/><script src=\"scriptInTheMiddle.js\"/>some more text<script src=\"scriptAtEnd.js\"/>"));
		assertEquals(4, libraryInfos.size());
		assertEquals("scriptAtStart.js", libraryInfos.get(0).getName()); 
		assertEquals("scriptInTheMiddle.js", libraryInfos.get(1).getName());
		assertEquals("scriptInTheMiddle.js", libraryInfos.get(2).getName());
		assertEquals("scriptAtEnd.js", libraryInfos.get(3).getName());
	}
	
}
