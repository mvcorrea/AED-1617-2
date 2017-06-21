package series.serie3;

import java.io.*;
import java.util.*;

// see java gephi (for visualization)

public class Graph {

    public class  Node {
        public int data;
        public boolean visited = false;
        public Node(int x){
            this.data = x;
        }
        public int get(){
            return data;
        }
        public void setVisited(){
            this.visited = true;
        }
    }

    public static int vert;                 // vertices
    public static LinkedList<Node>[] adjc; // lista de adjacencias
    //public static Node root;

    public Graph(int vertices) {
        this.vert = vertices;            // number of vertices
        adjc = new LinkedList[vert];    // array of linked lists
        for(int i = 0; i < vert; i++) adjc[i] = new LinkedList<>();
    }

//    public void rootNode(Node root){
//        this.root = root;
//    }

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

    public void addE(int v, int w){
        adjc[v].add(new Node(w));
        adjc[w].add(new Node(v));
    }

    public static void printGraph(){
        for(int x = 0; x < vert; x++ ){
            System.out.print(x +" > ");
            adjc[x].forEach(z -> System.out.print(z.data +" "));
            System.out.println();
        }
    }

    public static void nodeDegree(){
        for(int x = 0; x < vert; x++ ){
            if(adjc[x].size() > 0) System.out.println(x +" -> "+ adjc[x].size());
        }
    }

    public static void bfs(int index){

        boolean visited[] = new boolean[vert];  // por aqui ou dentro do no proprio node!!!
        Arrays.fill(visited, Boolean.FALSE);

        Queue<Node> q = adjc[index];
        visited[index] = true;
        System.out.print(index +" ");

        while(!q.isEmpty()){
            int x = (int) q.poll().data;
            visited[x] = true;
            System.out.print( x+" "); // elements
            Iterator<Node> iter = adjc[x].listIterator();
            while(iter.hasNext()){
                Node nxt = iter.next();
                if(!visited[nxt.data]){
                    visited[nxt.data] = true;
                    q.add(nxt);
                }
            }
        }
        System.out.println();
    }

    public static void nodeBetweenneess(){
        //HashMap<Integer, Integer> betweenness = new HashMap<Integer, Integer>();
        //betweenness.forEach((k,v)->{v=0});
    }


    




    public static void main(String[] args) throws IOException {

        Graph grp = new Graph(11);
        if (args[0] != null) grp.populate(args[0]);
        else throw new FileNotFoundException();
        System.out.println();
        printGraph();
        System.out.println();
        nodeDegree();
        System.out.println();
        bfs(1);


    }

}
