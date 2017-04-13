package series.serie1;

//import java.util.Arrays;

import java.util.NoSuchElementException;

public class Arrays {

    public static int[] sumClosestToZero(int[] v) {
        //throw new UnsupportedOperationException();
        if (v.length < 2) return null;
        int idx1 = 0, idx2 = 0, sum = Integer.MAX_VALUE;
        for (int x = 0; x < v.length; x++) {
            for (int y = x; y < v.length; y++) {
                int res = v[x] + v[y];
                if (res < sum) {
                    idx1 = v[x];
                    idx2 = v[x + 1];
                    sum = res;
                }
            }
        }

        //System.out.println(java.util.Arrays.toString(v));
        //System.out.println(java.util.Arrays.toString(new int[] {idx1, idx2}));
        return new int[]{idx1, idx2};
    }

    public static int[] greaterIncreasingSubarray(int[] v, int l, int r) {
        //throw new UnsupportedOperationException();
        //System.out.println(java.util.Arrays.toString(v) + ", " + l + ", " + r);
        if (r < l) return new int[]{1, 0};
        int cnt = 1, cntMax = 1, idxL = l, idxR = l, idxLMax = l, idxRMax = l;
        for (int x = l; x < r; x++) {
            if (v[x] < v[x + 1]) {  // increasing sequence
                idxR++;
                cnt++;
            } else {                // reset sequence
                idxL = idxR = x + 1;
                cnt = 1;
            }
            if (cnt > cntMax) {     // found new max
                idxLMax = idxL;
                idxRMax = idxR;
                cntMax = cnt;
            }
            //System.out.println("> [" + v[x] + " " + cnt + " " + cntMax + " (" + idxL + ", " + idxR + ")" + "(" + idxLMax + ", " + idxRMax + ")] ");
        }
        //System.out.println(java.util.Arrays.toString(new int[]{idxLMax, idxRMax}));
        return new int[]{idxLMax, idxRMax};
    }

    public static int[] squaresSorted(int[] v) {
        //throw new UnsupportedOperationException();
        if (v.length < 1) return v;
        if (v.length == 1) return new int[]{(int) Math.pow(v[0], 2)};

        int[] out = new int[v.length];
        int vUp = 0, vDown = v.length - 1, mid = v.length - 1;

        for (int x = 0; x < v.length; x++) {
            if (v[x] >= 0) {
                out[vUp++] = (int) Math.pow(v[x], 2);
                // System.out.println("up:"+vUp+" ");
            } else {
                out[vDown--] = (int) Math.pow(v[x], 2);
                // System.out.println("dw:"+vDown+" ");
            }
        }
        //System.out.println(java.util.Arrays.toString(out));
        Helper.merge(out, 0, vDown, out.length - 1);
        //System.out.print(vUp+ " "+ vDown + " ");
        //System.out.println(java.util.Arrays.toString(out));
        return out;
    }

    public static int median(int[] v, int l, int r) {
        //throw new UnsupportedOperationException();
        Helper.qsort(v);
        //System.out.println(java.util.Arrays.toString(v));
        int nElmns = r - l + 1;
        if(nElmns < 1) return 0;                // empty
        if(nElmns == 1) return v[0];            // single
        if(nElmns == 2) return (v[0]+v[1])/2;   // 2 elements
        if (nElmns % 2 == 0) {  // even squence
            return (v[nElmns/2-1] + v[nElmns/2])/2;
        } else {                // odd sequence
            return v[nElmns/2];
        }
    }

    public static int greatestOccurrence(int[] v, int min, int max) {
        //throw new UnsupportedOperationException();
        int histLen = max-min+1, maxPos = 0;
        if(v.length == 0) throw new NoSuchElementException();
        if(histLen > v.length) throw new IllegalArgumentException();

        int[] histogram =  new int[histLen];
        //System.out.println(java.util.Arrays.toString(v) + " "+ min +" "+ max);

        // build the histogram: O(n)
        for(int x = 0; x < v.length; x++){
            histogram[v[x]-min]++;
        }
        //System.out.println(java.util.Arrays.toString(histogram));

         // find the index of max : O(n)
        for(int x = 0; x < histogram.length; x++){
            if(histogram[x] > maxPos) maxPos = x;
        }
        //System.out.println(maxPos);

        return maxPos;
    }

}
