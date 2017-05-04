package series.serie2;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IterablesTest {

	<T> void assertIterableEmpty(Iterable<T> result) {
		assertFalse( result.iterator().hasNext() );
	}

	static <T> void assertIterableEquals(Iterable<T> expected, Iterable<T> result) {
		Iterator<T> itE= expected.iterator(), 
		            itR= result.iterator();
		while ( itE.hasNext() ) {
			assertTrue( itR.hasNext() );
			assertEquals( itE.next(), itR.next() );
		}
		assertFalse( itR.hasNext() );
	}


	private static <T> void testNextWithoutHasNext(Iterable<T> expected, Iterable<T> result) {
		Iterator<T> itE= expected.iterator(), 
		            itR= result.iterator();
		while ( itE.hasNext() ) {
			assertEquals( itE.next(), itR.next() );
		}
		assertFalse( itR.hasNext() );
	}
	
	private static <T> void testMultipleIterations(Iterable<T> expected, Iterable<T>  result ) {
		Iterator<T> itR, itE;
		for (int i = 0; i < 2; ++i ) {
			itE= expected.iterator();
            itR= result.iterator();
			while ( itR.hasNext() ) {
				assertEquals( itE.next(), itR.next() );
			}
			assertFalse( itE.hasNext() );
		}
	}
	
	private static <T> void testDoubleHasNext(Iterable<T> expected, Iterable<T>  result ) {
		Iterator<T> itE= expected.iterator(),
                    itR= result.iterator();
		while ( itR.hasNext() ) {
			assertTrue( itR.hasNext() );
			assertTrue( itE.hasNext() );
			assertEquals( itE.next(), itR.next() );
		}
		assertFalse( itR.hasNext() );
		assertFalse( itE.hasNext() );
	}

	
	protected static <T> void test(Iterable<T> expected, Iterable<T>  result ) {
		assertIterableEquals( expected, result );
		testNextWithoutHasNext(expected, result);
		testDoubleHasNext(expected, result);
		testMultipleIterations(expected, result);
	}

}
