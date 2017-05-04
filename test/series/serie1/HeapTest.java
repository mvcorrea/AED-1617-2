package series.serie1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class HeapTest {

    Comparator<Integer> cmp = (o1, o2) -> Integer.compare(o1, o2);
    public static Heap.MaxHeap maxHeap; // = new Heap.MaxHeap(cmp);
    public static Heap.MinHeap minHeap; // = new Heap.MinHeap(cmp);

    @Test
    @DisplayName("retriving from an empty hash")
    public void hashEmpty() {
        maxHeap = new Heap.MaxHeap(cmp);
        minHeap = new Heap.MinHeap(cmp);

        Throwable maxException = assertThrows(NoSuchElementException.class, () -> {
            maxHeap.peek();
        });
        Throwable minException = assertThrows(NoSuchElementException.class, () -> {
            maxHeap.peek();
        });

        assertEquals("no such element", maxException.getMessage());
        assertEquals("no such element", minException.getMessage());
    }

    @Test
    @DisplayName("retriving from a hash with a single element")
    public void hashWith1Element() {

        maxHeap.offer(5);
        minHeap.offer(5);

        assertEquals(5, maxHeap.peek());
        assertEquals(5, minHeap.peek());
    }

    @Test
    @DisplayName("retriving from a hash with two elements")
    public void hashWith2Elements() {

        maxHeap.offer(2);
        minHeap.offer(2);

        assertEquals(5, maxHeap.peek());
        assertEquals(2, minHeap.peek());
    }

    @Test
    @DisplayName("retriving from a hash with three elements")
    public void hashWith3Elements() {

        maxHeap.offer(8);
        minHeap.offer(8);

        assertEquals(8, maxHeap.peek());
        assertEquals(2, minHeap.peek());
    }

    @Test
    @DisplayName("retriving from a hash with multiple elements")
    public void hashWithMElements() {

        maxHeap.offer(14);
        maxHeap.offer(12);
        maxHeap.offer(10);
        minHeap.offer(14);
        minHeap.offer(12);
        minHeap.offer(10);

        assertEquals(14, maxHeap.peek());
        assertEquals(2, minHeap.peek());

        //System.out.println(maxHeap.toString());
        //System.out.println(minHeap.toString());
    }

    @Test
    @DisplayName("removing from a hash with multiple elements")
    public void hashRemoveElements() {
        int max1 = (int) maxHeap.poll();
        int max2 = (int) maxHeap.poll();
        int min1 = (int) minHeap.poll();
        int min2 = (int) minHeap.poll();

        assertEquals(14, max1);
        assertEquals(12, max2);
        assertEquals(2, min1);
        assertEquals(5, min2);
    }

    @Test
    @DisplayName("verify hash elements")
    public void hashVerifyElements() {
        int[] max = {10, 8, 5, 2};
        int[] min = {8, 10, 12, 14};

        // TODO:  ASK about the hash order when testing !!!

        assertEquals("[10, 8, 5, 2]", maxHeap.heap.toString());
        assertEquals("[8, 10, 12, 14]", minHeap.heap.toString());
    }


    @Test
    @DisplayName("verify sorting elements")
    public void hashSortElements() {

        maxHeap.heapSort();
        minHeap.heapSort();

       assertEquals("[2, 5, 8, 10]", maxHeap.heap.toString());
       assertEquals("[14, 12, 10, 8]", minHeap.heap.toString());
    }

}
