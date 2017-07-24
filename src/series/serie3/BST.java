package series.serie3;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BST<E> implements Iterable<E>{

    public Node<E> root, nxt = new Node<E>();
    public Comparator<E> cmp;


    public static class Node<E>  {
        public E value = null;
        public Node<E> left;
        public Node<E> right;
        public Node<E> prev;
        public int lvl;

        public Node(E value) {
            this.value = value;
            left = right = prev = null;
        }

        public Node() {
            left = right = prev = null;
        }

        @Override
        public String toString() {
            String lft = (left != null) ? String.valueOf(left.value) : "null";
            String rht = (right != null) ? String.valueOf(right.value) : "null";
            return "Node{" +
                    "value=" + value +
                    ", left=" + lft +
                    ", right=" + rht +
                    '}';
        }
    }

    public BST(Comparator cmp) {
        this.cmp = cmp;
        this.root = null;
    }

    @Override
    public Iterator<E> iterator() {
        nxt = getSmallest();
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                //return (nxt.prev != null)  ? true :  false;
                nxt = successor(nxt);
                return (nxt != null) ? true :  false;
            }

            @Override
            public E next() {  // TODO: tenho um bug caso o nivel seja o ultimo (vai dar menos 1)
                //return successor(nxt).value;
                //E out = nxt.value;
                //nxt = successor(nxt);
                return nxt.value;
            }
        };
    }

    public void addRoot(Node<E> rt){
        this.root = rt;
    }

    public Node<E> getSmallest(){
        Node<E> out = root;
        while (out.left != null){
            out = out.left;
        }
        return out;
    }

    public Node<E> search(E data) throws NoSuchElementException {
        Node<E> out = root;
        while(out != null){
            if(cmp.compare(data, out.value) == 0) break;
            out = (cmp.compare(data, out.value) < 0) ? out.left : out.right;
        }
        if(out == null) throw new NoSuchElementException("element not found");
        return out;
    }

    public void insert(Node<E> nd){
        Node<E> rt = root, tmp = null;
        while(rt != null){
            tmp = rt;
            rt = cmp.compare(nd.value, rt.value) < 0 ? rt.left : rt.right;
            nd.lvl+=1;
        }
        nd.prev = tmp;      // set previous
        if(tmp == null) {   // empty tree
            root = nd;
        } else {            // choose the side
            if(cmp.compare(nd.value, tmp.value) < 0){
                tmp.left = nd;
            } else {
                tmp.right = nd;
            }
        }
    }

    public Node<E> delete(Node<E> nd){  // TODO: to implement
        Node<E> out = root;


        return out;
    }

    public Node<E> min(Node<E> nd){
        Node<E> out = nd;
        while (out.left != null){
            out = out.left;
        }
        return out;
    }

    public Node<E> max(Node<E> nd){
        Node<E> out = nd;
        while (out.right != null){
            out = out.right;
        }

        return out;
    }

    public Node<E> successor(Node<E> nd) throws NoSuchElementException {  // TODO: treat all exceptions
        Node<E> tmp = nd;
        if(tmp.right != null) return min(tmp.right);
        Node<E> out = tmp.prev;  // go back one level
        while(out != null && tmp == out.right){
            tmp = out;
            out = out.prev;
        }
        return out;
    }

    public Node<E> predecessor(Node<E> nd) throws NoSuchElementException {
        Node<E> tmp = nd;
        if(tmp.left != null) return max(tmp.left);
        Node<E> out = tmp.prev;  // go back one level
        while(out != null && tmp == out.left){
            tmp = out;
            out = out.prev;
        }
        return out;
    }


    public String myToString(Node<E> rt, String out){
        if(rt == null) return out;
        else return myToString(rt.left, out) +" "+ rt.toString() +" "+ myToString(rt.right, out);   // infix natural order
        //else return rt.toString() +" "+ myToString(rt.left, out) +" "+ myToString(rt.right, out);   // prefix
        //else return myToString(rt.left, out) +" "+ myToString(rt.right, out) +" "+ rt.toString();   // sufix
    }

    @Override
    public String toString() {
        String out = new String();
        return myToString(root, out);
    }

    public static void main(String[] args){

        Comparator<Integer> cmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 != null && o2 != null) return o1 - o2;
                else return 0;
            }
        };

        // Comparator<Integer> cmp = (o1, o2) -> Integer.compare(o1, o2);

        BST<Integer> tree = new BST<Integer>(cmp);
        //tree.iterator() = treeIt;

        tree.insert(new Node<>(10));
        tree.insert(new Node<>(8));
        tree.insert(new Node<>(4));
        tree.insert(new Node<>(6));
        tree.insert(new Node<>(2));
        tree.insert(new Node<>(16));
        tree.insert(new Node<>(12));
        tree.insert(new Node<>(22));


//        System.out.println(tree.search(6).toString());
//
//        System.out.println(tree.toString());
//
//        Node<Integer> nd1 = tree.search(4);
//        System.out.println(tree.predecessor(nd1).toString());
//

        Iterator<Integer> it = tree.iterator();
        while(it.hasNext()){
            Integer tmp = it.next();
            System.out.println(tmp.toString()+"\n");
        }



    }


}
