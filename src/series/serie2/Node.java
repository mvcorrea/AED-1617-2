package series.serie2;

public class Node<E> {
	public Node<E> previous;
	public Node<E> next;
	public E value;
	
	public Node(){}
	
	public Node(E e){value=e;}

	public E get(){ return this.value; }
}
