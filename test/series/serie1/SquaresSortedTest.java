package series.serie1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static series.serie1.Arrays.squaresSorted;

/**
 * Created by cvaz on 15/03/2017.
 */
public class SquaresSortedTest {

    @Test
    public void squareSorted_OnEmptyArray(){
        int[] v=new int[0];
        int[] expected=v;
        assertArrayEquals(expected, squaresSorted(v));
    }

   @Test
    public void squareSorted_OnArrayWithOnePositiveElement(){
        int[] v={2};
        int[] expected={4};
        assertArrayEquals(expected, squaresSorted(v));
    }

    @Test
    public void squareSorted_OnArrayWithOneNegativeElement(){
        int[] v={-2};
        int[] expected={4};
        assertArrayEquals(expected, squaresSorted(v));
    }

    @Test
    public void squareSorted_OnArrayWithDistinctPositiveElements(){
        int[] v={0,2,3,6,7,8};
        int[] expected={0,4,9,36,49,64};
        assertArrayEquals(expected, squaresSorted(v));
    }

    @Test
    public void squareSorted_OnArrayWithPositiveElements(){
        int[] v={0, 0, 2, 3, 3, 6, 6, 7, 8};
        int[] expected={0, 0, 4,9, 9, 36, 36, 49, 64};
        assertArrayEquals(expected, squaresSorted(v));
    }



    @Test
    public void squareSorted_OnArrayWithDistinctNegativeElements(){
        int[] v={-8, -7, -6,-3, -2, 0};
        int[] expected={0,4,9,36,49,64};
        assertArrayEquals(expected, squaresSorted(v));
    }

    @Test
    public void squareSorted_OnArrayWithNegativeElements(){
        int[] v={-8, -7, -7,-6,-3, -2, 0};
        int[] expected={0,4,9,36,49, 49, 64};
        assertArrayEquals(expected, squaresSorted(v));
    }

    @Test
    public void squareSorted_OnArrayWithSomeElements(){
        //int[] v = {-9,-7,-5,-3,-1,0,2,4,6,8};
        //int[] expected = {0,1,4,9,16,25,36,49,64,81};

        int[] v={-8, -7, -7,-6,-3, -2, 0, 0, 0, 2, 3, 3, 6, 6, 7, 8};
        int[] expected={0, 0, 0, 4, 4, 9, 9, 9, 36, 36, 36, 49, 49, 49, 64, 64};
        assertArrayEquals(expected, squaresSorted(v));
    }
}
