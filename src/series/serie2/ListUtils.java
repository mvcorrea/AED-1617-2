package series.serie2;

import java.util.Comparator;

public class ListUtils {

    public static class Dll <E> {
        public Node<E> sentinel = new Node<E>();

        public Dll() {
            sentinel.previous = sentinel.next = sentinel;
        }

        public void addAfter(E e){
            Node<E> nd = new Node<E>(e);
            nd.next = sentinel.next;
            sentinel.next.previous = nd;
            sentinel.next = nd;
            nd.previous = sentinel;
        }
        public void addBefore(E e){
            Node<E> nd = new Node<E>(e);
            nd.next = sentinel;
            sentinel.previous.next = nd;
            sentinel.previous = nd;
            nd.next = sentinel;
        }


        public void del(Node nd){
            Node<E> x = nd.next;
            x.previous.next = x.next;
            x.next.previous = x.previous;
        }

        @Override
        public String toString() {
            Node<E> curr = sentinel.next;
            String out = "[ ";
            while(curr != sentinel){
                out += curr.value + " ";
                curr = curr.next;
            }
            return out+"]";
        }
    }


    public static <E> Node<E> intersectionPoint(Node<E> list1, Node<E> list2, Comparator<E> cmp) {
        //throw new UnsupportedOperationException();

        // maintain order ???
        // at least 2 elements ???

        if(list2.next == list2 || list1.next == list1) return null;  // empty
        if((list1.next == list1.previous || list2.next == list2.previous)){    // single
            if(cmp.compare(list1.next.value, list2.next.value ) == 0 ){ // single & equals
                return list1.next;
            } else {
                return null; // single value
            }
        }



//        if(cmp.compare(list1.next.value, list2.next.value ) == 0 ){ // same first value
//            if((list1.next == list1.previous || list2.next == list2.previous))  return list1.next;   // single lists
//        }
//
//      //  if(list2.next == list2.previous) return list1.next;         // single list 1





   //     System.out.println(list1.next.value);
   //     System.out.println(list2.next.value);

        Node<E> head1 = new Node<E>();
        Node<E> head2 = new Node<E>();
        //head1 = list1.next;
        head2 = list2.next;

        int counter = 0;

        while(head2 != list2){
            head1 = list1.next;
            while(head1 != list1) {
                if(head1.next.value == head2.next.value && head1.value == head2.value) { // remove the rest of the list
                    head1.previous.next = list1;
                    list1.previous  = head1.previous;

                }
                head1 = head1.next;
            }
            head2 = head2.next;
        }
        return head1;
    }

    public static <E> Node<E> deleteNode(Node e){
        Node<E> ret = new Node<E>();
        ret = e.previous;
        e.previous.next = e.next;
        e.next.previous = e.previous;
        e.next = e.previous = null;
        return ret;
    }


    public static <E> Node<E> merge(Node<E>[] lists, Comparator<E> cmp) {
        throw new UnsupportedOperationException();
    }

    public static <E> Node<E> interleaved(Node<Node<E>> list) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {

        Dll<Integer> dll1 = new Dll<>();
        dll1.addBefore(3); dll1.addBefore(5); dll1.addBefore(2); dll1.addBefore(7); dll1.addBefore(4);
        System.out.println("dll1:"+dll1.toString());

        Dll<Integer> dll2 = new Dll<>();
        dll2.addBefore(9); dll2.addBefore(3); dll2.addBefore(10); dll2.addBefore(8); dll2.addBefore(2);
        dll2.addBefore(7); dll2.addBefore(4);
        System.out.println("dll2:"+dll2.toString());

        Comparator<Integer> cmp = (o1, o2) -> Integer.compare(o1, o2);
        Dll res = new Dll<>();
        res.sentinel = intersectionPoint(dll1.sentinel, dll2.sentinel, cmp);
           System.out.println(res.toString());

// -----------------------------

//        DCList<Integer> lst1 = new DCList();
//        DCList<Integer> lst2 = new DCList();
//        lst1.ins(2);
//        lst1.ins(4);
//        lst1.ins(8);
//        lst1.ins(10);
//        System.out.println(lst1.toString());
//
//        lst2.ins(1);
//        lst2.ins(4);
//        lst2.ins(8);
//        lst2.ins(9);
//        System.out.println(lst2.toString());
//
//        Comparator<Integer> cmp = (o1, o2) -> Integer.compare(o1, o2);
//        DCList<Integer> result = new DCList<>();
//        result.list = intersectionPoint(lst1.list, lst2.list, cmp);
//        //System.out.println(result.toString());

    }

}
