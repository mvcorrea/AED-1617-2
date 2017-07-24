package series.serie2;

import series.serie1.Heap;

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
            nd.previous = sentinel.previous;
            sentinel.previous.next = nd;
            sentinel.previous = nd;

        }
        public void setSentinel(Node<E> nd){
            sentinel = nd;
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

    public static <E> String lprint(Node<E> nd){
        Node<E> curr = nd.next;
        String out = "[ ";
        while(curr != nd){
            out += curr.value + " ";
            curr = curr.next;
        }
        return out+"]";
    }

    public static <E> Node<E> intersectionPoint(Node<E> list1, Node<E> list2, Comparator<E> cmp) {
        //throw new UnsupportedOperationException();
        if(list2.next == list2 || list1.next == list1) return null;  // empty
        if(cmp.compare(list1.next.value, list1.previous.value) == 0 ) return list1;
        Node<E> l1 = list1.next;
        Node<E> l2 = list2.next;
        while(l1 != list1){
            //System.out.println(l1.value + " ->");
            while(l2 != list2){
                //System.out.print(l2.value + "\t");
                if( ( cmp.compare(l1.value, l2.value) == 0 ) && l1.next.value == l2.next.value ){ // 2 consecutives remove the node
                        l1.previous.next = l1.next;
                        l1.next.previous = l1.previous;
                }
                //System.out.println(lprint(list1));
                l2 = l2.next;
            }
            l1 = l1.next;
            l2 = list2.next;
        }
        return list1;
    }

    public static <E> Node<E> merge(Node<E>[] lists, Comparator<E> cmp) { // TODO: Not working
        //throw new UnsupportedOperationException();
        Dll<E> out = new Dll<E>();
        if(lists.length == 0) return out.sentinel;
        minHeap(lists, lists.length, cmp);  // TODO: implement minHeap it with lists
        for(int i = 0; i > lists.length; i++){
            // first element of the lists is the minor one (after minheap)
            Node<E> min = lists[0].next;
            // update the lists[0]
            lists[0] = lists[0].next;
            // check if last (innerlist)
            // insert in out list
            // check if last than decrease
            // heapify(lists...); // TODO: implement heapify it with lists
        }
        return out.sentinel;
    }

    public static <E> Node<E> interleaved(Node<Node<E>> list) {
        //throw new UnsupportedOperationException();
        Dll<E> out = new Dll<E>();

        Node<Node<E>> lstlst = list.next;
        Node<E> innerlst;
        while(lstlst != list){
            innerlst = lstlst.value.next;
            if(innerlst == lstlst.value) {    // finished
                // delete the inner lst
                lstlst.previous.next = lstlst.next;
                lstlst.next.previous = lstlst.previous;
                // set el to next lst
                lstlst = lstlst.next;
            } else {            // good to go
                // delete el
                innerlst.previous.next = innerlst.next;
                innerlst.next.previous = innerlst.previous;
                // insert el in out
                innerlst.next = out.sentinel;
                innerlst.previous = out.sentinel.previous;
                out.sentinel.previous.next = innerlst;
                out.sentinel.previous = innerlst;
                // I have a problem when reach end, actual.next == list
                if(lstlst.next == list){
                    lstlst = list.next;     // reset
                } else {
                    lstlst = lstlst.next;   // next inner list
                }
            }
        }
        return out.sentinel;
    }

    // AUX methods -----------------------------------------------------------

    public static <E> void minHeap(Node<E>[] lsts, int size, Comparator<E> cmp){ // for merge function
        int parent = (size - 1) >> 1; // parent = 1, size = 3
        while (parent >= 0) {
            minHeapify(lsts, parent--, size, cmp);
        }
    }

    public static <E> void minHeapify(Node<E>[] lsts, int i, int size, Comparator<E> cmp) { // for merge function
        int left = (i << 1) + 1;
        int right = (i << 1) + 2;
        int smallest = i;
        if (left < size  && cmp.compare(lsts[left].value,  lsts[i].value) < 0) smallest = left;
        if (right < size && cmp.compare(lsts[right].value, lsts[smallest].value) < 0) smallest = right;
        if (smallest != i) {
            exchange(lsts, i, smallest);
            minHeapify(lsts, smallest, size, cmp);
        }
    }

    private static <E> void exchange(Node<E>[] lsts, int idx, int j) {
        Node<E> aux = lsts[idx];
        lsts[idx] = lsts[j];
        lsts[j] = aux;
    }



    public static void main(String[] args) {

        Comparator<Integer> cmp = (o1, o2) -> Integer.compare(o1, o2);

        // ----- intersectionPoint -------------------------------------------
//        Dll<Integer> dll1 = new Dll<>();
//        dll1.addBefore(3); dll1.addBefore(5); dll1.addBefore(2); dll1.addBefore(7); dll1.addBefore(4);
//        System.out.println("dll1:"+dll1.toString());
//
//        Dll<Integer> dll2 = new Dll<>();
//        dll2.addBefore(9); dll2.addBefore(3); dll2.addBefore(10); dll2.addBefore(8); dll2.addBefore(2);
//        dll2.addBefore(7); dll2.addBefore(4);
//        System.out.println("dll2:"+dll2.toString());

//        Dll res1 = new Dll<>();
//        res1.sentinel = intersectionPoint(dll1.sentinel, dll2.sentinel, Integer::compareTo);
//        System.out.println(res1.toString());


        // ----- merge --------------------------------------------------------
        Dll<Integer> dll3 = new Dll<>();
        dll3.addBefore(2); dll3.addBefore(5); dll3.addBefore(8);
        System.out.println("dll3:"+dll3.toString());

        Dll<Integer> dll4 = new Dll<>();
        dll4.addBefore(3); dll4.addBefore(6); dll4.addBefore(9);
        System.out.println("dll4:"+dll4.toString());

        Dll<Integer> dll5 = new Dll<>();
        dll5.addBefore(4); dll5.addBefore(7);
        System.out.println("dll5:"+dll5.toString());

        Dll res2 = new Dll<>();
        Node[] lsts = {dll4.sentinel, dll3.sentinel, dll5.sentinel};
        res2.sentinel = merge(lsts, Integer::compareTo);
        System.out.println(res2.toString());


        // ----- interleaved -------------------------------------------------
        Dll<Node<Integer>> dll6 = new Dll<>();
        dll6.addAfter(dll5.sentinel);
        dll6.addAfter(dll4.sentinel);
        dll6.addAfter(dll3.sentinel);

//        Dll res3 = new Dll<>();
//        res3.sentinel = interleaved(dll6.sentinel);
//        System.out.println(res3.toString());


        // ----- MISC ----
        // apply a node as sentinel
//        Node<Integer> tmp = dll3.sentinel;
//        Dll<Integer> dll6 = new Dll<>();
//        dll6.setSentinel(tmp);
//        System.out.println("-->"+ dll6.toString());

    }

}
