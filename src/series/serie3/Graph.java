package series.serie3;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

// see java gephi (for visualization)

public class Graph {

    public static class Node {
        public int data;
        //public int deep; // level (starts with zero or one)????
        //public int prev; // previous node
        public Stack<Integer> prev = new Stack<>();

        public Node(int x){
            this.data = x;
        }

        @Override
        public String toString() {
            //return "[" + data + ", " + deep  + ", " + prev + ']';
            //return "[" + data + ", " + deep+ ", "  + prev.toString() +"]";
            return "[" + data + ", " + prev.size() + ", "  + Arrays.toString(prev.toArray()) +"]";
        }

        public LinkedList<Node> getChildren(){
            return adjc[this.data];
        }
    }



    public static int vert;
    public static LinkedList<Node>[] adjc;
    public Graph(int vertices) {
        this.vert = vertices;            // number of vertices
        adjc = new LinkedList[vert];    // array of linked lists
        for(int i = 0; i < vert; i++) adjc[i] = new LinkedList<>();
    }



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
        adjc[v].addFirst(new Node(w));  // undirected graph (combination)
        adjc[w].addFirst(new Node(v));
        // change to add first
    }

    public static void printGraph(){
        for(int x = 0; x < vert; x++ ){  // ASK: should I let the zero index on????
            System.out.print(x +" > ");
            adjc[x].forEach(z -> System.out.print(z.data +" "));
            //adjc[x].forEach(z -> System.out.print(z.toString() +" "));
            System.out.println();
        }
    }

    public static void nodeDegree(){
        for(int x = 0; x < vert; x++ ){
            if(adjc[x].size() > 0) System.out.println(x +" -> "+ adjc[x].size());
        }
    }

//    public static List<Node> dfsFunctional(Node idx) {
//        List<Node> visitedNodes = new LinkedList<>();
//        List<Node> unvisitedNodes = new LinkedList<>();
//        unvisitedNodes.add(idx);
//        while(!unvisitedNodes.isEmpty()) {
//            Node currNode = unvisitedNodes.remove(0);
//            List<Node> newNodes = currNode.getChildren()
//                    .stream()
//                    .filter(node -> !visitedNodes.contains(node))
//                    .collect(Collectors.toList());
//            unvisitedNodes.addAll(0, newNodes);
//            visitedNodes.add(currNode);
//        }
//        return visitedNodes;
//    }

//    public static List<Node> bfsFunctional(Node idx) {
//        List<Node> visitedNodes = new LinkedList<>();
//        List<Node> unvisitedNodes = idx.getChildren();
//        while(!unvisitedNodes.isEmpty()) {
//            List<Node> newNodes = unvisitedNodes
//                    .stream()
//                    .map(Node::getChildren)
//                    .flatMap(List::stream)
//                    .filter(node -> !visitedNodes.contains(node))
//                    .collect(Collectors.toList());
//            visitedNodes.addAll(unvisitedNodes);
//            unvisitedNodes = newNodes;
//        }
//        return visitedNodes;
//    }

    public static void bfs(Node idx){

        boolean visited[] = new boolean[vert];      // visited array (d)
        Arrays.fill(visited, Boolean.FALSE);

        Queue<Node> que = new LinkedList<>();
        Stack<Node> stk = new Stack<>();

        String bfs = new String();
        bfs += "BFS[ ";

        visited[idx.data] = true;
        que.add(idx);

        while (!que.isEmpty()) {
            Node el = que.poll();
            int x = el.data;
            bfs += x + " ";
            Iterator<Node> iter = adjc[x].listIterator();
            while (iter.hasNext()) {
                Node reachable = iter.next();       // reachable node
                if(!visited[reachable.data]){       // path discovery
                    visited[reachable.data] = true;
                    reachable.prev = new Stack<>(); // you can get the "level" on the stack size.
                    reachable.prev.addAll(el.prev);
                    reachable.prev.push(el.data);
                    que.add(reachable);
                }
            }
            stk.push(el);
        }
        bfs+="]";
        System.out.println(bfs);
        System.out.println("stk:"+Arrays.toString(stk.toArray()));
    }

    public static void dfs(Node idx){

        boolean visited[] = new boolean[vert];      // visited array (d)
        Arrays.fill(visited, Boolean.FALSE);

        Stack<Node> que = new Stack<>();
        Stack<Node> stk = new Stack<>();

        String dfs = new String();
        //dfs += "DFS[ "+ idx.data + " ";
        dfs += "DFS[ ";

        //visited[idx.data] = true;
        que.push(idx);

//        while(!que.isEmpty()) {
//            Node el = que.pop();
//            int x = el.data;
//            dfs += x + " ";
//            if (!visited[x]) visited[x] = true;
//            for (Node reachable : adjc[x]) {
//                if (!visited[reachable.data]) que.push(reachable);
//            }
//        }


        while(!que.isEmpty()) {
            Node el = que.pop();
            if(!visited[el.data]){
                visited[el.data] = true;
                dfs += el.data + " ";
                for(Node reachable: adjc[el.data]){
                    reachable.prev = new Stack<>(); // you can get the "level" on the stack size.
                    reachable.prev.addAll(el.prev);
                    reachable.prev.push(el.data);
                    que.push(reachable);
                }
            }
            stk.push(el);
        }

//        visited[idx.data] = true;
//        while(!que.isEmpty()) {
//            Node el = que.pop();
//            dfs += el.data + " ";
//            for(Node adj : adjc[el.data]){
//                if(!visited[adj.data]){
//                    visited[adj.data] = true;
//                    que.push(adj);
//                }
//            }
//        }

// ---





//        while (!que.isEmpty()) {
//            Node el = que.peek();
//            int x = el.data;
//            //dfs += x + " ";
//            Node reachable = adjc[x].poll();
//            if(!visited[reachable.data]){
//                visited[reachable.data] = true;
//                dfs += reachable.data + " ";
//                que.push(reachable);
//            }
//


//            else {
//                que.pop();
//                //que.push(adjc[x].poll());
//            }

//            //Iterator<Node> iter = adjc[x].listIterator();
//            if(iter.hasNext()){
//                Node reachable = iter.next();
//                visited[reachable.data] = true;
//                reachable.prev = new Stack<>(); // you can get the "level" on the stack size.
//                reachable.prev.addAll(el.prev);
//                reachable.prev.push(el.data);
//                que.push(reachable);
//            } else {
//                que.pop();
//            }



//            while (iter.hasNext()) {
//                Node reachable = iter.next();       // reachable node
//                if(!visited[reachable.data]){       // path discovery
//                    visited[reachable.data] = true;
//                    reachable.prev = new Stack<>(); // you can get the "level" on the stack size.
//                    reachable.prev.addAll(el.prev);
//                    reachable.prev.push(el.data);
//                    que.push(reachable);
//                }
//            }
            //stk.push(el);
 //       }
        dfs+="]";
        System.out.println(dfs);
        System.out.println("stk:"+Arrays.toString(que.toArray()));
    }


    public static void betCentrality() {

        double betweenness[] = new double[vert];
        Arrays.fill(betweenness, 0);

        for(int n = 1; n < vert; n++){ // do for all nodes

            String bfs = new String();
            bfs += "BFS: ";

            Node orig = new Node(n);

            Queue<Node> que = new LinkedList<>();               // to be visited queue (Q)
            Stack<Node> stk = new Stack<>();                    // visited stack (S)

            LinkedList<Node> predc[] = new LinkedList[vert];    // predecessors list (P)
            for(int i = 0; i < vert; i++) predc[i] = new LinkedList<>();


            int pathCount[] = new int[vert];                    // path count array (theta)
            Arrays.fill(pathCount, 0);

            int distance[] = new int[vert];                     // distance of each node from origin (d)
            Arrays.fill(distance, 0);

            //orig.deep = 0;
            distance[orig.data] = 1;
            que.add(orig);

            while (!que.isEmpty()) {
                Node el = que.poll();
                stk.push(el);
                int x = el.data;
                bfs += x + " ";
                Iterator<Node> iter = adjc[x].listIterator();
                while (iter.hasNext()) { // reachable list
                    Node reachable = iter.next();
                    if (distance[reachable.data] == 0) {       // path discovery
                        distance[reachable.data] = distance[el.data] + 1;
                        que.add(reachable);
                    }

                    if (distance[reachable.data] == distance[el.data] + 1) { // path counting
                        pathCount[reachable.data] += pathCount[el.data];
                        predc[reachable.data].addLast(el);
                    }
                }
                System.out.println("stk:"+Arrays.toString(stk.toArray()));
            }
            System.out.println("pred:"+Arrays.toString(predc));


//            double dependency[] = new double[vert];
//            Arrays.fill(pathCount, 0);
//
//            while(!stk.empty()) {  // accumulate
//                Node w = stk.pop();
//                double coef = (1.0 + dependency[w.data])/pathCount[w.data];
//
//                for(Node v: predc[w.data]){
//                    double centr = pathCount[v.data] * coef;
//                    dependency[v.data] += centr;
//                }
//                if(w.data != orig.data) betweenness[w.data] += dependency[w.data];
//            }
//            //System.out.println(Arrays.toString(dependency));
//
//            //System.out.println(Arrays.toString(distance));
            System.out.println(bfs);
        }
        //System.out.println("\n" + Arrays.toString(betweenness));
    }

    public static void main(String[] args) throws IOException {

        Graph grp = new Graph(11);
        if (args[0] != null) grp.populate(args[0]);
        else throw new FileNotFoundException();

        System.out.println("\n-> undirected graph representation");
        printGraph();

        System.out.println("\n-> node degree");
        nodeDegree();

        System.out.println("\n->Breadth-first search");
        //for(int i = 1 ; i<vert; i++) bfs(new Node(i));

        System.out.println("\n->Depth-first search");
        for(int i = 1 ; i<vert; i++) dfs(new Node(i));

//        System.out.println("\n->centrality betweenness");
//        betCentrality();


//        // TESTING
//        System.out.println("\n->Depth-first search Functional");
//        List<Node> x = dfsFunctional(new Node(1));
//        String lstXAsString = x.stream().map(Object::toString).collect(Collectors.joining(", "));
//        System.out.println(lstXAsString);
//
//        System.out.println("\n->Breadth-first search Functional");
//        List<Node> y = bfsFunctional(new Node(1));
//        String lstYAsString = x.stream().map(Object::toString).collect(Collectors.joining(", "));
//        System.out.println(lstYAsString);
    }
}
