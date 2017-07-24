package series.serie2;


import java.util.*;

// http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/HashMap.java#HashMap.HashIterator.nextEntry%28%29
public class AEDMap <K,V> { // enderecamento directo

    public static class Pair <K,V> {
        public K key;
        public V val;

        public Pair(K key, V val){
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "key=" + key +
                    ", val=" + val +
                    '}';
        }
    }

    public static class HNode <K,V> {
        public HNode<K, V> next;
        public Pair<K, V> pair;

        @Override
        public String toString() {
            return "HNode{" +
                    "next=" + next +
                    ", pair=" + pair +
                    '}';
        }
    }

    public HNode<K, V>[] ht;
    public HNode<K, V> p, nxt;
    public LinkedList<K> keySet = new LinkedList<K>();
    int index = 0;

    public <K,V> AEDMap(int size){  // use an array here ???
        this.ht = new HNode[size];
        this.nxt = ht[0];
    }

    public void put(K key, V val){
        p = new HNode<K, V>();
        int hcode = key.hashCode() % ht.length;  // I dont know if its OK (got from internet)
        //System.out.println(key + " & "+ key.hashCode()+" >"+hcode);
        p.pair = new Pair<K, V>(key, val);
        p.next = ht[hcode];
        keySet.add(key);
        this.ht[hcode] = p;
    }

    public V get(K key){
        int hcode = key.hashCode() % ht.length;
        HNode<K, V> tmp = ht[hcode]; // grab the index
        while(tmp != null){
            if(key.equals(tmp.pair.key)) return tmp.pair.val;
            tmp = tmp.next;
        }
        return null;
    }

    public HNode<K, V> getNode(K key){
        int hcode = key.hashCode() % ht.length;
        HNode<K, V> tmp = ht[hcode]; // grab the index
        while(tmp != null){
            if(key.equals(tmp.pair.key)) return tmp;
            tmp = tmp.next;
        }
        return null;
    }

    public int size(){ // all lists in all array positions
        int cnt = 0;
        for(HNode i: ht){
            while(i != null){
                cnt++;
                i = i.next;
            }
        }
        return cnt;
    }

    public HNode nextEntry(Iterator<K> iter){
        if(iter.hasNext()){
            K tmp = iter.next();
            nxt = getNode(tmp);
        } else {
            nxt = null;
        }
        return nxt;
    }

    public Set<HNode<K, V>> entrySet(){

        Iterator<K> iter = keySet.iterator();

        Set<HNode<K, V>> set = new Set<HNode<K, V>>() {


            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<HNode<K, V>> iterator() {
                return new Iterator<HNode<K, V>>() {
                    @Override
                    public boolean hasNext() {
                        //return nxt != null;
                        return iter.hasNext();
                    }

                    @Override
                    public HNode<K, V> next() {
                        return nextEntry(iter);
                    }
                };
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(HNode<K, V> kvhNode) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends HNode<K, V>> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };

        return set;
    }

    @Override
    public String toString() {
        return "AEDMap{" +
                "ht=" + Arrays.toString(ht) +
                ", p=" + p +
                '}';
    }

    public static void main(String[] args){
        AEDMap<String, Integer> map = new AEDMap<>(10);
        map.put("a", 15);
        map.put("b", 23);
        map.put("c", 12);
        map.put("d", 21);
        map.put("e", 18);
        map.put("x", 30);
        map.put("y", 42);
        map.put("z", 51);
        map.put("w", 64);
        System.out.println();
        System.out.println(map.toString());
        System.out.println("> "+map.get("x"));
        System.out.println("> "+map.size());

        //Set<HNode<String, Integer> > set = map.entrySet();
        Iterator<HNode<String, Integer>> it = map.entrySet().iterator();
        while(it.hasNext()){
            HNode x = it.next();
            System.out.println(">> "+x.pair.toString());
        }
    }
}
