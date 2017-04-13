package series.serie1;


import java.io.IOException;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Heap<E> { // MaxHeap

    private Comparator<E> cmp;
    private E[] heap;
    private int size;

    public Heap(Comparator<E> cmp) {
        heap = (E[]) new Object();
        size = 0;
        this.cmp = cmp;
    }

    public E peek() {   // obtem o elemento mais prioritario
        if (size == 0) throw new NoSuchElementException();
        return heap[0];
    }

//    public void offer(E e) {    // insere um elemento novo a fila prioritaria
//        if (size >= heap.length) increaseCapacity();
//        heap[size++] = e;
//        increaseKey(size - 1);
//    }

//    public E poll() {    // obtem e remove o elemento mais prioritario
//        E toReturn = heap[0];
//        heap[0] = heap[--size];
//        maxHeapify();       // indice que falha
//    }

//    public void update(int id) { // actualiza a prioridade de um elemento na fila prioritaria
//        int idx;
//        update1(idx);
//    }

    // o heap eh um maxHeap exepto na posicao i (n: num de elementos, i: posicao)
//    public void maxHeapify(int n, int i) {
//        int r = right(i), l = left(i), x = i;
//        if (l < n && heap[l] > heap[i]) x = l;
//        if (r < n && heap[r] > heap[x]) x = r;
//        if (n != i) {
//            exch(i, x);
//            maxHeapify(n, x);
//        }
//    }

//    public void buildMaxHeap(int n) {
//        int pai = parent(n - 1);
//        while (pai >= 0) {
//            maxHeapify(n, pai);
//            pai--;
//        }
//    }

//    public void heapSort(E[] arr, int n) {
//        buildMaxHeap(n);
//        int sortedPos = n - 1;
//        while (sortedPos > 0) {
//            exch(sortedPos, );  //see
//            maxHeapify(--sortedPos, 0);
//        }
//    }


    // private methods --------------------------------------------------------

    private void increaseKey(int i) {
        while (i > 0 && cmp.compare(heap[i], heap[parent(i)]) > 0) {
            int j = parent(i);
            exch(i, j);
            i = j;
        }
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private void exch(int i, int j) {
        E aux = heap[i];
        heap[j] = heap[i];
        heap[i] = aux;
    }

//    private void update1(int index) {
//        if (index == 0 || cmp.compare(heap[index], heap[parent(index)]) < 0)
//            maxHeapify(index, cmp);
//        else
//            increaseKey(index);
//    }


    public static void main(String[] args) throws IOException {
        
    }

}
