package series.serie2;

import java.util.ArrayList;
import java.util.Iterator;

public class Iterables_ {

	public static Iterable<Integer> 
	getValuesBetween(final Iterable<Integer> src,  final int min,final int max) {
		//throw new UnsupportedOperationException();
		return new Iterable<Integer>() {
			@Override
			public Iterator<Integer> iterator() {
				return new Iterator<Integer>() {

					Iterator<Integer> it = src.iterator();
					int nxt; boolean flag = false;
					@Override
					public boolean hasNext() {
						while(it.hasNext() && flag == false){
							int val = it.next();
							if (val < min || val > max){
								flag = true;
								nxt = val;
							}
						}
						return flag;
					}

					@Override
					public Integer next() {
						flag = false;
						return nxt;
					}
				};
			}
		};
	}

	
	public static Iterable<String> getPhrasesStart(Iterable<Iterable<String>> phrases, String prefix){
		//throw new UnsupportedOperationException();
		return new Iterable<String>() {
			@Override
			public Iterator<String> iterator() {
				return new Iterator<String>() {
					@Override
					public boolean hasNext() {
						return false;
					}

					@Override
					public String next() {
						return null;
					}
				};
			}
		};

	}


	public static void main(String[] args) {
		ArrayList<Integer> l = new ArrayList<>();
		l.add(2); l.add(3); l.add(4); l.add(5); l.add(6); l.add(7); l.add(8);

		Iterables_ itr = new Iterables_();
		//itr.
		//Iterable<Integer> ex1 = (Iterable<Integer>) itr;
		//ex1.getValuesBetween
		// TODO: implement testing

	}

}
