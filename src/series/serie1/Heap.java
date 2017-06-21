package series.serie1;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public abstract class Heap <E> {

    public LinkedList<E> heap;
    public Comparator<E> cmp;

    public Heap(Comparator<E> cmp) {
        this.heap = new LinkedList<E>();
        this.cmp = cmp;
    }

    // operations
    public void buildHeap(int n) {
        int parent = parent(n);
        while (parent >= 0) {
            this.heapify(parent--, heap.size());
        }
    }

    public Heap<E> heapSort() { // ordena o conjunto
        int sortedPos = heap.size() - 1;
        while (sortedPos > 0) {
            exchange(0, sortedPos);
            heapify(0, sortedPos--);
        }
        return this;
    }

    public E peek() throws NoSuchElementException { // obtem o elemento mais prioritario -> O(1)
        if (heap.size() == 0) throw new NoSuchElementException("no such element");
        return heap.peek();
    }

    public void offer(E e) { // insere um elemento novo ao heap -> O(logN)
        this.heap.addFirst(e);
        this.heapify(0, heap.size());
    }

    public E poll() { // obtem e remove o elemento mais prioritario -> O(logN)
        E toReturn = heap.pollFirst();
        this.buildHeap(heap.size() - 1);
        return toReturn;
    }

//    public void update(int n) { // actualiza a prioridade de um elemento na fila prioritaria -> O(logN)
//        int idx =
//    }


    // to implement
    abstract public Heap<E> heapify(int i, int n);

    //abstract public void buildHeap(int n);

    // protected methods
    protected int parent(int i) { // i element position
        return (i - 1) >> 1;
    }

    protected int left(int i) {
        return (i << 1) + 1;
    }

    protected int right(int i) {
        return (i << 1) + 2;
    }

    protected int size() {
        return heap.size();
    }

    protected void exchange(int i, int j) {
        E aux = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, aux);
    }

    public void addAllFromArray(E[] arr){
        for (E i : arr){
            this.offer(i);
        }
    }

    public E[] asArray() {
        //return (E[]) this.heap.toArray();
        return (E[]) this.heap.toArray();
    }

    @Override
    public String toString() {
        return "Heap{" + "heap=" + heap + '}';
    }


    // Max Heap implementation -----------------------------------------------------------------------------------------
    public static class MaxHeap<E> extends Heap<E> { // utilizado no heapSort
        public MaxHeap(Comparator<E> cmp) {
            super(cmp);
        }

        // i: element index n: how deep
        public MaxHeap<E> heapify(int i, int n) { // maxHeapify
            int r = right(i), l = left(i), largest = i;
            if (l < n && cmp.compare(heap.get(l), heap.get(i)) > 0) largest = l;
            if (r < n && cmp.compare(heap.get(r), heap.get(largest)) > 0) largest = r;
            if (largest != i) {
                exchange(i, largest);
                this.heapify(largest, n);
            }
            return this;
        }

        public void increaseKey(int i) { // bubbleUP element in the heap
            E actual = heap.get(i);
            E parent = heap.get(parent(i));
            while (i > 0 && cmp.compare(actual, parent) > 0) {
                int parentIdx = parent(i);
                exchange(i, parentIdx);
                i = parentIdx;
            }
        }

        // TODO: build increasekey and update (priority key)
        public void update(int idx, E e) { // idx:
            if (cmp.compare(heap.get(idx), e) > 0) { //
                //increaseKey();
            }else{
                //heapify()
            }
        }

    }

    // Min Heap Implementation -----------------------------------------------------------------------------------------
    public static class MinHeap<E> extends Heap<E> {
        public MinHeap(Comparator cmp) {
            super(cmp);
        }

        public MinHeap<E> heapify(int i, int n) {
            int r = right(i), l = left(i), smallest = i;
            if (l < n && cmp.compare(heap.get(l), heap.get(i)) < 0) smallest = l;
            if (r < n && cmp.compare(heap.get(r), heap.get(smallest)) < 0) smallest = r;
            if (smallest != i) {
                exchange(i, smallest);
                this.heapify(smallest, n);
            }
            return this;
        }

    }

    // testing subject -------------------------------------------------------------------------------------------------
    public static void main(String[] args) throws IOException {

        Comparator<Integer> cmp = (o1, o2) -> Integer.compare(o1, o2);

        //Heap<int> myHeap = new Heap.MinHeap(cmp);

        System.out.println("> running.... ");

        //MinHeap myHeap = new MinHeap(cmp);
        MaxHeap myHeap = new MaxHeap(cmp);

        myHeap.offer(5);
        myHeap.offer(1);
        myHeap.offer(3);
        myHeap.offer(8);
        myHeap.offer(12);
        myHeap.offer(20);
        myHeap.offer(15);
        myHeap.offer(7);
        myHeap.offer(2);
        myHeap.offer(6);

        System.out.println(myHeap.peek());
        //System.out.println(myHeap.toString());
        //myHeap.buildHeap(myHeap.size()-1);
        System.out.println(myHeap.toString());
        System.out.println("removed: " + myHeap.poll().toString());
        System.out.println(myHeap.toString());
        System.out.println("removed: " + myHeap.poll().toString());
        System.out.println(myHeap.toString());
        System.out.println("removed: " + myHeap.poll().toString());
        System.out.println(myHeap.toString());
        System.out.println(myHeap.peek());
        System.out.println("---");
        myHeap.heapSort();
        System.out.println(">" + myHeap.toString());

    }

}
