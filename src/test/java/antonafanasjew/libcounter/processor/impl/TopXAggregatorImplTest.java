package antonafanasjew.libcounter.processor.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import antonafanasjew.libcounter.model.LibraryInfo;

public class TopXAggregatorImplTest {

	private TopXAggregatorImpl testObjectForTop0;
	private TopXAggregatorImpl testObjectForTop1;
	private TopXAggregatorImpl testObjectForTop5;
	
	List<LibraryInfo> listWith0Entries;
	List<LibraryInfo> listWith1Entries;
	List<LibraryInfo> listWith4Entries;
	List<LibraryInfo> listWith10Entries;
	
	
	
	@Before
	public void prepareTest() {
		
		testObjectForTop0 = new TopXAggregatorImpl(0);
		testObjectForTop1 = new TopXAggregatorImpl(1);
		testObjectForTop5 = new TopXAggregatorImpl(5);
		
		listWith0Entries = new ArrayList<>();
		
		listWith1Entries = new ArrayList<>();
		listWith1Entries.add(LibraryInfo.fromName("A"));
		
		listWith4Entries = new ArrayList<>();
		listWith4Entries.add(LibraryInfo.fromName("A"));
		listWith4Entries.add(LibraryInfo.fromName("B"));
		listWith4Entries.add(LibraryInfo.fromName("B"));
		listWith4Entries.add(LibraryInfo.fromName("C"));
		
		listWith10Entries = new ArrayList<>();
		listWith10Entries.add(LibraryInfo.fromName("A"));
		listWith10Entries.add(LibraryInfo.fromName("B"));
		listWith10Entries.add(LibraryInfo.fromName("B"));
		listWith10Entries.add(LibraryInfo.fromName("C"));
		listWith10Entries.add(LibraryInfo.fromName("B"));
		listWith10Entries.add(LibraryInfo.fromName("B"));
		listWith10Entries.add(LibraryInfo.fromName("B"));
		listWith10Entries.add(LibraryInfo.fromName("C"));
		listWith10Entries.add(LibraryInfo.fromName("D"));
		listWith10Entries.add(LibraryInfo.fromName("D"));
	}
	
	@Test
	public void testTopZero() {
		List<String> l;
		l = testObjectForTop0.aggregate(listWith0Entries);
		assertEquals(new ArrayList<String>(), l);
		
		l = testObjectForTop0.aggregate(listWith1Entries);
		assertEquals(new ArrayList<String>(), l);
		
		l = testObjectForTop0.aggregate(listWith4Entries);
		assertEquals(new ArrayList<String>(), l);
		
		l = testObjectForTop0.aggregate(listWith10Entries);
		assertEquals(new ArrayList<String>(), l);
		
	}
	
	@Test
	public void testTopOne() {
		List<String> l;
		l = testObjectForTop1.aggregate(listWith0Entries);
		assertEquals(expected(""), l);
		
		l = testObjectForTop1.aggregate(listWith1Entries);
		assertEquals(expected("A"), l);
		
		l = testObjectForTop1.aggregate(listWith4Entries);
		assertEquals(expected("B"), l);
		
		l = testObjectForTop1.aggregate(listWith10Entries);
		assertEquals(expected("B"), l);
	}
	
	@Test
	public void testTopFive() {
		List<String> l;
		l = testObjectForTop5.aggregate(listWith0Entries);
		assertEquals(expected(""), l);
		
		l = testObjectForTop5.aggregate(listWith1Entries);
		assertEquals(expected("A"), l);
		
		l = testObjectForTop5.aggregate(listWith4Entries);
		//Can't compare complete lists as the order is not defined for equal occurrences 
		assertEquals(3, l.size());
		assertEquals("B", l.get(0));
		
		l = testObjectForTop5.aggregate(listWith10Entries);
		//Can't compare complete lists as the order is not defined for equal occurrences
		assertEquals(4, l.size());
		assertEquals("B", l.get(0));
		assertEquals("A", l.get(3));
	}
	
	private List<String> expected(String s) {
		List<String> l = s
			.chars()
			.mapToObj(c -> String.valueOf((char)c))
			.collect(Collectors.toList());
		return l;
	}
	
}
