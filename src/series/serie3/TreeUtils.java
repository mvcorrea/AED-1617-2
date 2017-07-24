package series.serie3;


import java.util.Comparator;
import java.util.Iterator;

public class TreeUtils {

    // retorna o maior integer i presente na bst  com raiz root que seja estritamente menor que k
    public static Integer lower(BST.Node<Integer> root, int k){
        int out = 0;
        Comparator<Integer> cmp = (o1, o2) -> Integer.compare(o1, o2);
        BST<Integer> tree = new BST<>(cmp);
        tree.addRoot(root);

        Iterator<Integer> it = tree.iterator();

        while(it.hasNext()){
            Integer val = it.next();
            if(val > k){
                out = tree.predecessor(tree.nxt).value;
                break;
            }
        }
        return out;
    }

    // retorna o numero de folhas da bst com raiz roo no nivel k
    public static int countLeavesAtLevel(BST.Node<Integer> root, int k){
        int leaves = 0;
        if(root == null) return -1;

        Comparator<Integer> cmp = (o1, o2) -> Integer.compare(o1, o2);
        BST<Integer> tree = new BST<>(cmp);
        tree.addRoot(root);

        Iterator<Integer> it = tree.iterator();

        while(it.hasNext()){
            Integer val = it.next();
            if(tree.nxt.left == null && tree.nxt.right == null ){ // is a leaf
                if(tree.nxt.lvl == k) leaves++;
            }

        }
        return leaves;
    }




    public static void main(String[] args){
        Comparator<Integer> cmp = (o1, o2) -> Integer.compare(o1, o2);
        BST<Integer> tree = new BST<Integer>(cmp);

        tree.insert(new BST.Node<>(10));
        tree.insert(new BST.Node<>(8));
        tree.insert(new BST.Node<>(4));
        tree.insert(new BST.Node<>(6));
        tree.insert(new BST.Node<>(2));
        tree.insert(new BST.Node<>(16));
        tree.insert(new BST.Node<>(12));
        tree.insert(new BST.Node<>(22));

//        int low = lower(tree.root, 15);
//        System.out.println(low);

        int low = countLeavesAtLevel(tree.root, 2);
        System.out.println(":"+low);

    }

}
