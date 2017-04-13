package series.serie1;

public class Helper {

    static void qsort(int[] arr){
        quickSort(arr, 0, arr.length-1);
    }

    static void quickSort(int[] arr, int l, int r){
        if ( l < r ) {
            int q = partition( arr, l, r);
            quickSort(arr, q+1, r);;
            quickSort(arr, l, q-1);
        }
    }

    public static int partition( int[] arr, int l, int r){
        int pivot = arr[r];
        int i = l-1;
        for ( int j = l; j < r; ++j ) {
            if ( arr[j] <= pivot )
                swap( arr, ++i, j );
        }
        swap(arr, i + 1, r);
        return i+1;
    }

    public static void swap(int[] arr, int i1, int i2) {
        int aux = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = aux;
    }

    public static String pArray(int[] arr){
        return java.util.Arrays.toString(arr);
        //return "["+ java.util.Arrays.stream(arr).forEach(x->x+", ")+"]";
    }



    public static void main(String[] args){
        int[] myArr = {2, 1, 13, 8, 9, 5};
        System.out.println(java.util.Arrays.toString(myArr));
        qsort(myArr);
        System.out.println(java.util.Arrays.toString(myArr));
    }


    public static void mergesort(int[] arr, int l, int r){
        if(l < r) {
            int mid = l + (r - l) / 2;
            mergesort(arr, l, mid);
            mergesort(arr, mid + 1, r);
            merge(arr, l, mid, r);
        }
    }

    public static void merge(int[] a, int l, int m, int r) {
        int leftLen = m - l + 1;
        int[] arrayLeft = new int[ leftLen];

        System.arraycopy(a, l, arrayLeft, 0, leftLen);

        int indexPut = l, indexLeft= 0, indexRight= m+1;
        while( indexLeft < leftLen && indexRight <= r ) {
            if ( arrayLeft[indexLeft] <= a[indexRight])
                a[indexPut++]= arrayLeft[indexLeft++];
            else
                a[indexPut++]= a[ indexRight++];
        }
        System.arraycopy(arrayLeft, indexLeft, a, indexPut, leftLen - indexLeft);
    }


/*    public static void merge1(int[] arr, int[] left, int[] mid, int[] right){ // assume arrays ja ordenados
        int iarr = 0, ileft= 0, iright= 0;
        while(iarr < arr.length && ileft < b.length){
            if(a[ia] <= b[ib]){
                c[ic] = a[ia++];
            }else{
                c[ic] = b[ib++];
            }
            ic++;
        }
        for(; ia < a.length; ia++, ic++) c[ic] = a[ia];
        for(; ib < b.length; ib++, ic++) c[ic] = b[ib];
    }*/

    public static void insertionSort(int[] arr, int l, int r){
        int v, i;
        for(int j= l+1; j<=r; j++){
            v = arr[j];
            i = j;
            while(i > l && v < arr[i-1]){
                arr[i] = arr[i-j];
                i--;
            }
            arr[i] = v;
        }
    }

    public static void bubbleSort(int[] arr, int l, int r){
        for (int i = l; i < r; i++){
            for (int j = r; j > i; j--){
                if(arr[j] == arr[j-1]){
                    swap(arr, j, j-1);
                }
            }
        }
    }

    // implement a heap



}
