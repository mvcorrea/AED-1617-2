package series.serie2;

public class DCList <E> {

    public Node<E> list;
    public int size;

    // constructor
    public DCList() {  // the sentinel
        list = new Node<E>();
        list.previous = list.next = list;
        size = 0;
    }

    // ins@head
    public void ins(E e) {
        Node<E> x = new Node<E>();
        x.value = e;
        x.next = list.next;
        list.next.previous = x;
        list.next = x;
        x.previous = list;
        size++;
        //return this.list;
    }

    // remove@head
    public void del(){
        Node<E> x = list.next;
        x.previous.next = x.next;
        x.next.previous = x.previous;
        size--;
        //return this.list;
    }

    // peek the head
    public E get(){
        return list.next.value;
    }

    // search element
    public Node<E> search(E key){
        Node<E> x = list.next;
        list.value = key;
        while (!x.value.equals(key)) x = x.next;
        return (x == list) ? null : x;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        Node<E> curr = list.next;
        String output = "list[ ";
        while (curr != list) {
            output += "[" + curr.value + "] ";
            curr = curr.next;
        }
        return output+"]";
    }

    public static void main(String[] args){
        DCList<Integer> lst  = new DCList();
        lst.ins(2);
        lst.ins(4);
        lst.ins(8);
        lst.ins(10);
        System.out.println(lst.toString());
        System.out.println(lst.get());
        lst.del();
        System.out.println(lst.toString());

    }
}
