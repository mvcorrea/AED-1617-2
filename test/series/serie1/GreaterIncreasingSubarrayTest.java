package series.serie1;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static series.serie1.Arrays.greaterIncreasingSubarray;

public class GreaterIncreasingSubarrayTest {
	private static final int[] original={0, 1, 2, 3, 2, 5, 6, 3, 8, 10, 12 };
	
	@Test
	public void greaterIncreasingSubarray_with_empty_subsequences(){
		int[] emptySequence={};
		int[] res=greaterIncreasingSubarray(emptySequence, 0, emptySequence.length-1);
		assertTrue(res[0] > res[1]);
		res=greaterIncreasingSubarray(original, original.length>>1, (original.length>>1) -1);
		assertTrue(res[0] > res[1]);
		res=greaterIncreasingSubarray(original, original.length, original.length-1);
		assertTrue(res[0] > res[1]);
	}
	
	@Test
	public void greaterIncreasingSubarray_at_the_begin(){
		int[] res=greaterIncreasingSubarray(original, 0, original.length-1);
		assertArrayEquals(new int[]{0,3}, res);
		//res=greaterIncreasingSubarray(original, 1, original.length-2);
		//assertArrayEquals(new int[]{1,3}, res);
	}
	
	@Test
	public void greaterIncreasingSubarray_at_the_end(){
		int[] res=greaterIncreasingSubarray(original, 1, original.length-1);
		assertArrayEquals(new int[]{7,10}, res);
	}
	
	@Test
	public void greaterIncreasingSubarray_at_the_middle(){
		int[] res=greaterIncreasingSubarray(original, 2, original.length-3);
		assertArrayEquals(new int[]{4,6}, res);
	}

	@Test
	public void test_getMaximumIncreasingSubsequence_increasing_sequence(){
		int[][] arrayTest = { {1}, {1,2}, {1,2,3}, {1,2,3,4,5,6} };
		int[] res;
		for (int i = 0; i < arrayTest.length; ++i) {
			res=greaterIncreasingSubarray(arrayTest[i], 0, arrayTest[i].length-1);
			assertEquals(0, res[0]);
			assertEquals(arrayTest[i].length-1, res[1]);
		}
	}
	
	@Test
	public void test_getMaximumIncreasingSubsequence_decreasing_sequence(){
		int[][] arrayTest = { {6,5,4,3,2,1}, {1, 5, 4, 3, 4} };
		int[] res;
		for(int i=0; i < arrayTest.length; ++i ) {
			res=greaterIncreasingSubarray(arrayTest[i], i, arrayTest[i].length-1-i);
			assertEquals(i, res[0]);
			assertEquals(i, res[1]);
		}
	}

}
