package series.serie2;

import java.util.Comparator;
import java.util.List;

public class ListUtilTest {
	
	/*
	 * For circular lists with sentinel
	 * 
	 */
	
	static <E> Node<E> emptyListWithSentinel() {
		Node<E> empty= new Node<>();
		empty.next = empty.previous = empty;
		return empty;
	}

	static <E> boolean isEmptyListWithSentinel(Node<E> list) {
		return list.next == list && list.previous == list;
	}
	
	static Node<Integer> getList( int start, int length, List<Node<Integer>> array) {
		Node<Integer> list = emptyListWithSentinel();
		for (int i= length-1; i>=0; --i) {
			list.next = newNode(i+start, list, list.next);
			list.next.next.previous = list.next;
		}
		for (Node<Integer> current = list.next; current != list; current= current.next) {
			array.add(current);
		}
		return list;
	}

	static <E>  Node<E> makeList( E ... array) {
		Node<E> list = emptyListWithSentinel();
		for ( E v : array ) {
			list.previous = newNode( v, list.previous, list);
			list.previous.previous.next= list.previous;
		}
		return list;
	}

	/*
	 * For non_circular lists with no sentinel
	 * 
	 */
	
	static <E> Node<E> emptyListWithoutSentinel() {
		return null;
	}
	static <E> boolean isEmptyListWithoutSentinel(Node<E> list) {
		return list==null;
	}

	static  <E> boolean isSorted(Node<E> list,Comparator<E> cmp){
		Node<E> curr=list.next;
		if(curr==list || curr==list.previous) return true;
		while( curr.next!=list){
		    if(cmp.compare(curr.value, curr.next.value)>0) return false;
			curr=curr.next;
		}
		return true;
	}	
	
	/*
	 * 
	 * Generic Methods
	 * 
	 * 
	 */
		
	private static <E> Node<E> newNode( E v ) {
		Node<E> result = new Node<>();
		result.value = v; 
		return result;
	}

	static <E> Node<E> newNode(E v, Node<E> p, Node<E> n) {
		Node<E> result = newNode( v );
		result.previous = p;
		result.next = n;
		return result;
	}
}
