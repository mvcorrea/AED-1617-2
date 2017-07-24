package series.serie1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IncMedian_v0 { // utilizando o qsort

    public static int[] data = new int[0];

    public static int[] updateSet(int val) {
        int[] tmp = new int[data.length + 1];
        System.arraycopy(data, 0, tmp, 1, data.length);
        tmp[0] = val;
        Helper.qsort(tmp);          // sorting O(N*logN)
        data = tmp;
        return data;
    }

    public static int getMedian() {
        return median(data, 0, data.length-1);
    }


    public static int getArg(String in) throws NumberFormatException, ArrayIndexOutOfBoundsException {
        int out = Integer.parseInt(in.split(" ")[1]);
        return out;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Problema AED - Serie 01 v0");
        BufferedReader br = br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            System.out.print("> ");
            String input = br.readLine();

            if(input.matches("updateSet(.*)")){
                try {
                    int arg = getArg(input);
                    System.out.println("input: " + arg);
                    updateSet(arg);
                    System.out.println(Helper.pArray(data));
                } catch (NumberFormatException e){
                    System.out.println("invalid data, try again!");
                } catch (ArrayIndexOutOfBoundsException e) {}
                continue;
            }

            if("getMedian".equals(input)){
                System.out.println("median: "+getMedian());
                continue;
            }

            if("e".equals(input)){
                System.out.println("quitting!");
                System.exit(0);
            }
        }
    }


    //

    public static int median(int[] arr, int l, int r) { // accept an array returns the median

        int len = (l == r) ? r : r - l + 1;
        //System.out.println(l + " - " + r + ": " + len + "\t" + Helper.pArray(arr));
        //java.util.Arrays.sort(arr, l, l + len);
        //Helper.insertionSort(v, l, l+len);            // internal sorter =(N*logN)
        //System.out.println(l + " - " + r + ": " + len + "\t" + Helper.pArray(arr));
        int median = 0;
        if (arr.length == 0) return 0;
        if (l == r) return arr[len];
        if (len % 2 == 0) {
            // comprimento par
            // System.out.println("if ");
            median = (arr[l + (len / 2) - 1] + arr[l + (len / 2)]) / 2;
        } else {
            // comprimento impar
            // System.out.println("else ");
            median = arr[(l + (len + 1) / 2) - 1];
        }
        return median;
    }
}
