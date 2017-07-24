package series.serie3;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class UGraph <E> {

    public static class GNode <E>  {
        public E data;
        public LinkedList<GNode<E>> adjcLst = new LinkedList<GNode<E>>();
        public int level;
        public GNode(E x){ this.data = x; }
        public void add2Adj(GNode e) { adjcLst.add(e); }
        //public String adj2String(){ return adjcLst.stream().map(Object::toString).reduce((t, u) -> t + "\t" + u).orElse(""); }
        public String node2String(){ return adjcLst.stream().map(e -> e.toString()).reduce("", String::concat); }
        //public String toString() { return "["+data+", "+level+", "+adj2String()+"] "; }
        @Override public String toString() { return data+", "+level; }
    }

    public static LinkedList<GNode> nodes = new LinkedList<>();


    public <N> void populate(String filename) throws IOException {
        String line = null;
        BufferedReader br = new BufferedReader(new FileReader(filename));
        while ((line = br.readLine()) != null) {
            System.out.println(line);

            //N[] parts = Arrays.stream(line.split(" ")).map(x -> new Node(x)).toArray();
            //.map((x, y) -> new N[]{new Node<N>(x), new Node<N>(y)});

            int[] parts = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            addE(parts[0], parts[1]);
        }
        br.close();
    }

    public void addE(int v, int w){ // v: left, w: right
        GNode nv = new GNode(v);
        GNode nw = new GNode(w);
        nv.adjcLst.add(nw);
        nw.adjcLst.add(nv);
        nodes.add(nv);
        nodes.add(nw);
    }

    public static void printGraph(){
//        String output = new String();
//        output += "["+ n.toString() +"] ";
//
//        n.adjcLst.forEach( node -> { });
     //   nodes.forEach(System.out::println);

        for(GNode g: nodes){
            System.out.print(g.data +" > ");
            LinkedList<GNode<Integer>> adj = g.adjcLst;
            //adj.forEach(x->System.out.print(x.data+" "));
            //adj.forEach(x-> System.out::println);
            //System.out.println(adj.toString());
            //g.adjcLst.forEach(z -> c));

        }
        System.out.println();

//        for(int x = 0; x < vert; x++ ){  // ASK: should I let the zero index on????
//            System.out.print(x +" > ");
//            adjc[x].forEach(z -> System.out.print(z.data +" "));
//            //adjc[x].forEach(z -> System.out.print(z.toString() +" "));
//            System.out.println();
//        }
    }


    public static void main(String[] args) throws IOException {

        UGraph grp = new UGraph();
        if (args[0] != null) grp.populate(args[0]);
        else throw new FileNotFoundException();
        System.out.println("\n-> undirected graph representation");
        printGraph();
        //System.out.println(grp.nodes.getFirst().node2String());
    }

}
