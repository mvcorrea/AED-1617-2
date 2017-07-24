package series.serie2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DocumentSimilarity_v1 {

    public static AEDMap<String,Integer> addWords2Hash(String words) throws IOException {
        AEDMap<String,Integer> out = new AEDMap<>(10);
        String line = null;
        BufferedReader br = new BufferedReader(new FileReader(words));

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");
            Arrays.stream(parts).forEach(x -> {
                int v = (out.get(x) != null) ? out.get(x) + 1 : 1;
                out.put(x, v);
            });
        }
        return out;
    }

    public static int similarity(AEDMap<String, Integer> f1, AEDMap<String, Integer> f2){
        int result = 0;



        Iterator it1 = f1.entrySet().iterator();
        while (it1.hasNext()) { // f1 -> f2
            AEDMap.HNode f1Entry = (AEDMap.HNode) it1.next();
            //Map.Entry f1Entry = (Map.Entry) it1.next();
            String keyF1 = (String) f1Entry.pair.key;
            //String keyF1 = (String) f1Entry.getKey();
            if (f2.get(keyF1) != null) { // exist, check how many
                if (f2.get(keyF1) != f1.get(keyF1)) result += 1; //
            } else result += 2; // non existing key f1->f2
        }

        Iterator it2 = f2.entrySet().iterator();
        while (it2.hasNext()) { // f2 -> f1
            AEDMap.HNode f2Entry = (AEDMap.HNode) it2.next();
            //Map.Entry f2Entry = (Map.Entry) it2.next();
            String keyF2 = (String) f2Entry.pair.key;
            //String keyF2 = (String) f2Entry.getKey();
            if (f2.get(keyF2) != null) { // exist, check how many
                if (f2.get(keyF2) != f1.get(keyF2)) result += 1; //
            } else result += 2; // non existing key f2->f1
        }

        //System.out.println(result);
        return result/2;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("args: "+ Arrays.toString(args));
        AEDMap<String, Integer> f1 = addWords2Hash(args[0]);
        System.out.println("f1:"+f1.size());
        AEDMap<String, Integer> f2 = addWords2Hash(args[1]);
        System.out.println("f2:"+f2.size());

        System.out.println("-> "+similarity(f1, f2));
    }

}
