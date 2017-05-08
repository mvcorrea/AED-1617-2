package series.serie2;

import java.util.HashSet;
import java.util.regex.*;

public class Utils {

	public static class Stack <E> {

		public DCList<E> stk;

		public Stack() {
			stk = new DCList();
		}

		public void push(E e) {
			stk.ins(e);
		}

		public void pop() {
			stk.del();
		}

		public E peek() {
			return stk.list.next.value;
		}

		public int size(){
		    return stk.size();
        }

		public String print(){
		    return this.stk.toString();
        }
	}

	public static boolean verifyPairing(String s) {
		//throw new UnsupportedOperationException();

        Stack<Character> stk = new Stack<>();
		char[] array = s.toCharArray();

		for (char c : array) {
			System.out.print(c);
			if(c == '{'|| c == '('|| c == '[') stk.push(c);
            if((c == '}'|| c == ')'|| c == ']') && stk.size() == 0) return false;
			if((c == '}'|| c == ')'|| c == ']') && stk.size() != 0) {
			    if(c == '}' && stk.peek() == '{') stk.pop();
                if(c == ')' && stk.peek() == '(') stk.pop();
                if(c == ']' && stk.peek() == '[') stk.pop();
            }
		}
        System.out.println();
        System.out.println(stk.print());
        return stk.size() == 0 ? true : false;
	}

	public static void main(String[] args){
		//String evalTrue = "{(..).(.)[()..]{({..[..]..})}..}";
        String evalTrue ="..}{..";
        System.out.println(verifyPairing(evalTrue));
	}
}
