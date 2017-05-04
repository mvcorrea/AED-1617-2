package series.serie2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static series.serie2.ListUtilTest.*;
import static series.serie2.ListUtils.intersectionPoint;

public class IntersectionPointTest {

    private static final Node<String> EMPTY_LIST = emptyListWithSentinel();
    private static final Node<String> SINGLETON_LIST_WITH_STR_A = makeList("A");
    private static final Node<String> SINGLETON_LIST_WITH_STR_b = makeList("b");
    @Test
    public void  intersectionPoint_empty_lists(){
        assertNull( intersectionPoint( EMPTY_LIST , EMPTY_LIST, null ) );
        assertNull( intersectionPoint( EMPTY_LIST, SINGLETON_LIST_WITH_STR_A, null ) );
        assertNull( intersectionPoint( SINGLETON_LIST_WITH_STR_A, EMPTY_LIST,null ) );
    }

    @Test
    public void  intersectionPoint_one_element_lists(){
        Node<String> l1 = SINGLETON_LIST_WITH_STR_A;
        assertEquals( l1.previous, intersectionPoint(  l1 , makeList( "a" ),String::compareToIgnoreCase ) );
        assertNull( intersectionPoint(  l1 , makeList( "a" ), String::compareTo ) );
        assertNull( intersectionPoint(  l1 , makeList( "b" ),String::compareTo ) );
    }

    @Test
    public void  intersectionPoint_one_element_match(){
        Node<String> l1 = makeList( "b", "a" );
        Node<String> l2 = SINGLETON_LIST_WITH_STR_A;
        Node<String> l3 = makeList( "c", "a" );
        assertEquals( l1.previous, intersectionPoint( l1 , l2, String::compareToIgnoreCase ) );
        assertEquals( l2.previous, intersectionPoint( l2 , l1, String::compareToIgnoreCase ) );
        assertEquals( l1.previous, intersectionPoint( l1 , l3,String::compareToIgnoreCase ) );
    }

    @Test
    public void  intersectionPoint_two_element_match(){
        Node<String> l1 = makeList( "b", "a" );
        Node<String> l2 = makeList( "c", "b", "a" );
        Node<String> l3 = makeList( "d", "b", "a" );
        assertEquals( l1.previous.previous, intersectionPoint( l1 , l2, String::compareToIgnoreCase ) );
        assertEquals( l2.previous.previous, intersectionPoint( l2 , l1, String::compareToIgnoreCase ) );
        assertEquals( l1.previous.previous, intersectionPoint( l1 , l3,String::compareToIgnoreCase ) );
    }

    @Test
    public void  intersectionPoint_not_match(){
        Node<String> l = makeList( "b", "a" );
        Node<String> reverseL = makeList( "a", "b" );
        Node<String> greaterL = makeList("b", "a", "c" );
        assertNull( intersectionPoint( l , reverseL, String::compareTo ) );
        assertNull( intersectionPoint( l , greaterL, String::compareTo ) );
        assertNull( intersectionPoint( l , SINGLETON_LIST_WITH_STR_A,String::compareTo ) );
        assertNull( intersectionPoint( SINGLETON_LIST_WITH_STR_A , SINGLETON_LIST_WITH_STR_b, String::compareTo ) );
        assertNull( intersectionPoint( SINGLETON_LIST_WITH_STR_A , reverseL,String::compareTo ) );
        assertNull( intersectionPoint( SINGLETON_LIST_WITH_STR_A , greaterL,String::compareTo ) );
    }

    @Test
    public void intersectionPoint_all_match() {
        Node<String> singleton = SINGLETON_LIST_WITH_STR_A;
        assertEquals(singleton.next, intersectionPoint(singleton, makeList("a"), String::compareToIgnoreCase));
        assertEquals(singleton.next, intersectionPoint(singleton, makeList("A"), String::compareTo));

        Node<String> twoElements =  makeList("b", "a");
        assertEquals(twoElements.next, intersectionPoint(twoElements, makeList("B", "A"), String::compareToIgnoreCase));
        assertEquals(twoElements.next, intersectionPoint(twoElements, makeList("b", "a"), String::compareTo));

        Node<String> fiveElements = makeList("a", "b", "c", "d", "e");
        assertEquals(fiveElements.next, intersectionPoint(fiveElements, makeList("a", "b", "c", "d", "e"), String::compareToIgnoreCase));
    }
}
