package series.serie2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static series.serie2.ListUtilTest.getList;
import static series.serie2.ListUtilTest.isEmptyListWithSentinel;
import static series.serie2.ListUtilTest.newNode;
import static series.serie2.ListUtils.interleaved;

public class InterleavedTest {
	@Test
	public void  interleave_empty_list(){
		Node<Node<Integer>> list = ListUtilTest.emptyListWithSentinel();
		assertTrue( isEmptyListWithSentinel( interleaved(list) ) );
	}

	@Test
	public void  interleave_empty_lists(){
		Node<Node<Integer>> list = new Node<>();
		Node<Node<Integer>> current = list;
		for (int i= 0; i < 3; ++i ) {
		  current.next =  newNode( ListUtilTest.emptyListWithSentinel(), current, list );
		  current= current.next;
		}
		list.previous = list.next;
		assertTrue( isEmptyListWithSentinel( interleaved(list) ) );
		assertTrue( isEmptyListWithSentinel( list ) );
	}

	@Test
	public void  interleave_lists(){
		Node<Node<Integer>> list, current;
		ArrayList<Node<Integer>> array;
		for (int  numberOfLists=1; numberOfLists <=4; ++numberOfLists) {
			for (int lengthOfLists=1; lengthOfLists <=3; ++lengthOfLists) {
			    list = new Node<>();
				current = list;
				array= new ArrayList<>();
				for ( int i= 0; i < numberOfLists; ++i ) {
					  current.next =  newNode( ListUtilTest.emptyListWithSentinel(), current, list);
					  current= current.next;
					  current.next =  newNode( getList(i*lengthOfLists, lengthOfLists, array), current, list );
					  current= current.next;
					}
				list.previous = list.next;
				
				Node<Integer> result = interleaved(list), currResult = result.next;
				for (int i=0; i < lengthOfLists; ++ i ) {
					for (int j=0; j < numberOfLists; ++ j ) {
						assertTrue( array.get(currResult.value) == currResult );
						assertEquals(j*lengthOfLists+i, currResult.value.intValue() );
						currResult= currResult.next;
					}
				}
				assertTrue( currResult == result );
				assertTrue( isEmptyListWithSentinel( list ) );
			}
		}
	}
}
