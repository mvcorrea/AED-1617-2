package series.serie1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static series.serie1.Arrays.sumClosestToZero;

/**
 * Created by cvaz on 15/03/2017.
 */
public class SumClosestToZeroTest {

    @Test
    public void sumClosestToZero_onArrayWithNoElements(){
        int[] array={};
        assertEquals(null,sumClosestToZero(array));
    }

    @Test
    public void sumClosestToZero_onArrayWithOneElements(){
        int[] array={1};
        assertEquals(null,sumClosestToZero(array));
    }

    @Test
    public void sumClosestToZero_onArrayWithEqualElements(){
        int[] array={1,1,1,1,1,1,1,1};
        int[] result={1,1};
        assertArrayEquals(result,sumClosestToZero(array));
    }


    @Test
    public void sumClosestToZero_onArrayInscreasingWithTwoDistinctElements(){
        int[] array={1,1,1,1,2,2,2};
        int[] result={1,1};
        assertArrayEquals(result,sumClosestToZero(array));
    }


    @Test
    public void sumClosestToZero_onArrayWithSolutionAtBeggin(){
        int[] array={-11,10,13,5,20,1,17};
        int[] result={-11,10};
        assertArrayEquals(result,sumClosestToZero(array));
    }

    @Test
    public void sumClosestToZero_onArrayWithSolutionAtEnd(){
        int[] array={10,7,13,5,20,-16,17};
        int[] result={-16,17};
        assertArrayEquals(result,sumClosestToZero(array));
    }

    @Test
    public void sumClosestToZero_onArrayWithSomeElements(){
        int[] array={1, 60, -10, 70, -80, 85};
        int[] result={-80,85};
        assertArrayEquals(result,sumClosestToZero(array));
    }
}
