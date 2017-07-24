package series.serie1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;

public class IncMedian_v1 { // com um heap

    public static Comparator<Integer> cmp = (o1, o2) -> Integer.compare(o1, o2);
    public static Heap.MaxHeap<Integer> maxHeap = new Heap.MaxHeap(cmp);
    public static Heap.MinHeap<Integer> minHeap = new Heap.MinHeap(cmp);


    public IncMedian_v1(Comparator<Integer> cmp) {
        this.cmp = cmp;
    }

    public static void updateSet(int el) {
        int median = getMedian();
        if (el > median) {
            minHeap.offer(el);
        } else {
            maxHeap.offer(el);
        }
        splitEqualParts();
    }

    public static int getMedian() {
        int minSize = minHeap.size();
        int maxSize = maxHeap.size();
        if (minSize == 0 && maxSize == 0) return 0;
        if (minSize > maxSize) return minHeap.peek();
        if (minSize < maxSize) return maxHeap.peek();
        return (minHeap.peek() + maxHeap.peek()) / 2;
    }

    public static void splitEqualParts() {
        int minSize = minHeap.size();
        int maxSize = maxHeap.size();
        int tmp = 0;
        if (minSize > maxSize + 1) {
            tmp = minHeap.poll();   // remove
            maxHeap.offer(tmp);     // put on the other
        }
        if (maxSize > minSize + 1) {
            tmp = maxHeap.poll();
            minHeap.offer(tmp);
        }
    }

    @Override
    public String toString() {
        return "IncMedian_v1{" + maxHeap + " - " + minHeap + "}\n";
    }

    public static int getArg(String in) throws NumberFormatException, ArrayIndexOutOfBoundsException {
        int out = Integer.parseInt(in.split(" ")[1]);
        return out;
    }

    public static void main(String[] args) throws IOException {
        // java -cp "out/production/AED-Series" series.serie1.IncMedian_v1
        System.out.println("Problema AED - Serie 01 v1");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("> ");
            String input = br.readLine();

            if (input.matches("updateSet(.*)")) {
                try {
                    int arg = getArg(input);
                    System.out.println("input: " + arg);
                    updateSet(arg);
                    System.out.println(maxHeap + " - "+ minHeap);
                } catch (NumberFormatException e) {
                    System.out.println("invalid data, try again!");
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                continue;
            }

            if ("getMedian".equals(input)) {
                System.out.println("median: " + getMedian());
                continue;
            }

            if ("e".equals(input)) {
                System.out.println("quitting!");
                System.exit(0);
            }
        }
    }


}
