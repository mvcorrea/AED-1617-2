package series.serie1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static series.serie1.Arrays.greatestOccurrence;


import java.util.NoSuchElementException;

public class GreatestOccurrenceTest {


	
	private static final int[] orderSequence={1, 2, 3, 4, 5, 6, 7, 8, 9};
	private static final int[] reverseSequence={9, 8, 7, 6, 5, 4, 3, 2, 1};
	private static final int[] dupsUnorderSequence={6, 2, 1, 2, 5, 5, 6, 2, 4};
	private static final int[] dupsOrderSequence={1, 2, 2, 2, 4, 5, 5, 6, 6};
	private static final int[] equalsSequence={2, 2, 2, 2, 2, 2, 2, 2, 2};
	private static final int[] emptySequence={};
            
	@Test
	public void greatestOccurrence_in_empty_sequence(){
		assertThrows(NoSuchElementException.class, () -> greatestOccurrence(emptySequence, 0, 0));
		assertThrows(NoSuchElementException.class, () -> greatestOccurrence(emptySequence, 1, 9));
		assertThrows(NoSuchElementException.class, () -> greatestOccurrence(emptySequence, 0, 10));
	}

	
	@Test
	public void greatestOccurrence_in_sequence_less_than_limits(){
		assertThrows(IllegalArgumentException.class, () -> greatestOccurrence(orderSequence, 0, 9));
		assertThrows(IllegalArgumentException.class, () -> greatestOccurrence(reverseSequence, 0, 9));
		assertThrows(IllegalArgumentException.class, () ->  greatestOccurrence(dupsUnorderSequence, 0, 9));
		assertThrows(IllegalArgumentException.class, () -> greatestOccurrence(dupsOrderSequence, 0, 9));
		assertThrows(IllegalArgumentException.class, () -> greatestOccurrence(equalsSequence, 0, 9));
	}
	
	
	@Test
	public void greaterIncreasingSubarray_in_sequence_with_excluded_limits(){
		assertEquals(2, greatestOccurrence(dupsUnorderSequence, 0, 7));
		assertEquals(2, greatestOccurrence(dupsOrderSequence, 0, 7));
		assertEquals(2, greatestOccurrence(equalsSequence, 0, 7));
	}
	
	@Test
	public void greaterIncreasingSubarray_in_sequence_with_included_limits(){
		assertEquals(1, greatestOccurrence(orderSequence, 1, 9));
		//assertEquals(9, greatestOccurrence(reverseSequence, 1, 9));
		//assertEquals(2, greatestOccurrence(dupsUnorderSequence, 1, 6));
		//assertEquals(2, greatestOccurrence(dupsOrderSequence, 1, 6));
		//assertEquals(2, greatestOccurrence(equalsSequence, 2, 2));
	}
}
