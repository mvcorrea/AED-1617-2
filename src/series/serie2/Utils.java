package series.serie2;

import java.util.HashSet;
import java.util.regex.*;

public class Utils {


//    public static class Stack<E> {
//
//        Node<E> stk;
//
//        public Stack() {
//            DCList<char> stk  = new DCList();
//
//            public void push(<E> e){
//                stk.ins(e);
//            }
//
//            public void pop(){
//                stk.del();
//            }
//
//            public <E> peek(){
//                return stk.next.value;
//            }
//
//        }
//    }



    public static HashSet<Character> braces = new HashSet<Character>();

    static {
        braces.add('{');
        braces.add('}');
        braces.add('[');
        braces.add(']');
        braces.add('(');
        braces.add(')');
    }


	public static boolean verifyPairing(String s) {
		//throw new UnsupportedOperationException();

		char[] array = s.toCharArray();
		for (char c : array) {
			System.out.println (c);
			if(braces.contains(c)) System.out.println("-->"+c);
		}

		return true;
	}

	public static void main(String[] args){
		String evalTrue = "{()()[()]{({[]})}}";
		System.out.println(verifyPairing(evalTrue));
	}
}
