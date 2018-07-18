import java.util.Random;

public class Sort {
    private static void QuickSort(int Array[],int p,int r){
        if(p<r){
            int q = partition(Array,p,r);
            QuickSort(Array,p,q-1);
            QuickSort(Array,q+1,r);
        }
    }
    private static int partition(int Array[],int p,int r){
        //First element as pivot
        int x = Array[p];
        int i = r+1;
        for(int j = r;j > p ;j--){
            if(Array[j] >= x) {
                i--;
                swap(Array,i,j);
            }
        }
        swap(Array,i-1,p);

        return i-1;
    }


    private static void QuickSort2(int Array[],int p,int r) {
        int x = Array[(p + r) / 2];
        int i = r;
        int j = p;

        while (j <= i) {
            while (Array[j] < x) {
                j++;
            }
            while (Array[i] > x) {
                i--;
            }
            if(j<=i) {
                swap(Array,i,j);
                j++;
                i--;
            }
        }
        if(p<i)
            QuickSort2(Array,p,i);
        if(r>j)
            QuickSort2(Array,j,r);
    }

    private static void QuickSort3(int Array[],int p,int r){
        //Random pivot
        if(p<r){
            int q = random_Partition(Array,p,r);
            QuickSort3(Array,p,q-1);
            QuickSort3(Array,q+1,r);
        }
    }

    private static int random_Partition(int Array[],int p,int r){

            //Random num = new Random();
            //int i = num.nextInt(r);
            //swap(Array,i,r);

            return partition3(Array, p, r);
    }

    private static int partition3(int Array[],int p,int r) {
        int x = Array[r];
        int i = p - 1;

        for (int j = p; j < r; j++) {
            if (Array[j] <= x) {
                i++;
                swap(Array,j,i);
            }
        }
        swap(Array,i+1,r);

        return i+1;
    }


    private static void InsertionSort(int Array[], int n) {
        int key, i;
        for (int j = 1; j < n; j++) {
            key = Array[j];
            i = j - 1;
            while (i >= 0 && Array[i] > key) {
                Array[i + 1] = Array[i];
                i = i - 1;
            }
            Array[i + 1] = key;
        }
    }

    private static void MergeSort(int Array[], int p, int r) {

        if (p < r) {
            int q = (p + r) / 2;
            MergeSort(Array, p, q);
            MergeSort(Array, q + 1, r);
            Merge(Array, p, q, r);
        }

    }

    private static void Merge(int Array[], int p, int q, int r) {
        int n1 = q + 1 - p;
        int n2 = r - q;

        int[] leftSubArray = new int[n1+1];
        int[] rightSubArray = new int[n2+1];
        for (int i = 0; i < n1; i++)
            leftSubArray[i] = Array[p + i];

        for (int j = 0; j < n2; j++)
            rightSubArray[j] = Array[q + 1 + j];


        leftSubArray[n1]=Integer.MAX_VALUE;
        rightSubArray[n2]=Integer.MAX_VALUE;
        int i = 0;
        int j = 0;

        for(int k = p;k<=r;k++){
            if(leftSubArray[i]<=rightSubArray[j]){
                Array[k]=leftSubArray[i];
                i++;
            }
            else{
                Array[k]=rightSubArray[j];
                j++;
            }
        }
    }

    private static void CountingSort(int A[],int B[],int n){

        int C[] = new int[n];
        for(int j = 0;j < n;j++){
            C[A[j]]++;
        }
        for(int i = 1;i < n;i++){
            C[i] = C[i]+C[i-1];
        }
        for(int j = n-1;j >= 0;j--){
            B[C[A[j]]-1] = A[j];
            C[A[j]]=C[A[j]]-1;
        }
    }

    private static void RadixSort(int A[],int d){
        int i,m=A[0],exp=1;
        int b[]=new int[d];
        for(i = 1;i<d;i++)
            if(A[i]>m)
                m=A[i];
        while(m/exp>0) {

            int Bucket[] = new int[d];

            for (i = 0; i < d; i++)
                Bucket[(A[i]/exp)%d]++;
            for (i = 1; i < d; i++)
                Bucket[i] += Bucket[i - 1];
            for (i = d - 1; i >= 0; i--)
                b[--Bucket[(A[i]/exp)%d]]=A[i];
            for(i = 0;i < d;i++)
                A[i]=b[i];
            exp*=d;
        }
    }

    private static void Heapify(int Array[],int i,int n){

        int L = 2*i+1;
        int R = 2*i+2;
        int largest;
        if(L < n && Array[L] > Array[i]){
            largest = L;
        }
        else{
            largest = i;
        }
        if(R < n && Array[R] > Array[largest]){
            largest = R;
        }
        if(largest!=i){
            swap(Array,i,largest);
            Heapify(Array,largest,n);
        }
    }

    private static void BuildHeap(int Array[]){
        int n = Array.length;
        for(int i = (n/2)-1;i>=0;i--){
            Heapify(Array,i,n);
        }

    }

    private static void HeapSort(int Array[],int n){
        BuildHeap(Array);
        for(int i = n-1;i>0;i--){
            swap(Array,0,i);
            Heapify(Array,0,i);
        }
    }

//----------------------utility functions----------------------------------------

    private static void swap(int Array[],int a,int b){
        int temp = Array[a];
        Array[a]=Array[b];
        Array[b]=temp;
    }

    private static void printArray(int Array[], int size) {
        for (int i = 0; i < size; i++) {
            System.out.printf("%d ", Array[i]);
        }
        System.out.println();
    }

    private static void randomArray(int Array[],int size) {

        for (int i = 0; i < size; i++) {
            Random number = new Random();
            Array[i] = number.nextInt(size);
        }
    }
//-----------------------------------------------------------------------------

    public static void main(String[] args){
        int n = 10;
        System.out.println("---InsertionSort---");
        int Array[] = new int[n];
        randomArray(Array,n);
        printArray(Array,n);
        InsertionSort(Array,n);
        printArray(Array,n);

        System.out.println("-----MergeSort-----");
        int[]Array2 = new int[n];
        randomArray(Array2,n);
        printArray(Array2,n);
        int p = 0;
        int r = n-1;
        MergeSort(Array2,p,r);
        printArray(Array2,n);

        System.out.println("-----QuickSort-----");
        int Array3[]=new int[n];
        randomArray(Array3,n);
        printArray(Array3,n);
        QuickSort(Array3,p,r);
        printArray(Array3,n);

        System.out.println("-----QuickSort2-----");
        int Array4[]=new int[n];
        randomArray(Array4,n);
        printArray(Array4,n);
        QuickSort2(Array4,p,r);
        printArray(Array4,n);

       System.out.println("-----QuickSort3-----");
        int Array5[]=new int[n];
        randomArray(Array5,n);
        printArray(Array5,n);
        Random num = new Random();
        int index = num.nextInt(n);
        swap(Array5,index,r);
        System.out.println("  Randomized pivot");
        printArray(Array5,n);
        QuickSort3(Array5,p,r);
        printArray(Array5,n);


        System.out.println("----CountingSort----");
        int Array6[]=new int[n];
        int Array7[]=new int[n];
        randomArray(Array6,n);
        printArray(Array6,n);
        CountingSort(Array6,Array7,n);
        printArray(Array7,n);

        System.out.println("-----RadixSort------");
        int d = 10;
        int Array8[] = new int[d];
        randomArray(Array8,d);
        printArray(Array8,d);
        RadixSort(Array8,d);
        printArray(Array8,d);

        System.out.println("------HeapSort------");
        int Array9[]=new int[n];
        randomArray(Array9,n);
        printArray(Array9,n);
        HeapSort(Array9,n);
        printArray(Array9,n);
    }
}
