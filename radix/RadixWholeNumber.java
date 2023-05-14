package radix; 
import java.util.Arrays;

public class RadixWholeNumber {

    private int[] arr;
    private int counter;

    public RadixWholeNumber(){
        counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public int[] radixSort() {
        System.out.println("Original array: " + Arrays.toString(arr));                    
        System.out.println("--------------------");
        loopArray(arr);
        System.out.println("Rough number of operation: " + counter);
        System.out.println("--------------------");
        return arr;
    }

    // loop array to sort using radix algorithm 
    public void loopArray(int[] arr) {
        int max = getMax(); counter++;
        for(int exp = 1; max/exp > 0; exp *= 10) {
            countSort(arr, exp);
            counter++;
        } 
    }

    // get the max number
    public int getMax() {
        int max = arr[0];
        for(int i =0; i<arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }


    public  void countSort(int[] arr, int exp ) {
        int[] output = new int[arr.length]; counter++;
        int[] count = new int[10]; counter++;
        for(int i = 0; i<arr.length; i++) {
            count[((arr[i]/exp)%10)]++;
            counter+=3;
        }
        for(int i = 1; i<10; i++) {
            count[i] += count[i-1];
            counter+=2;
        }
        for(int i = arr.length-1; i>=0; i--) {
            output[count[(arr[i]/exp)%10]-1] = arr[i];
            count[(arr[i]/exp)%10]--;
            counter +=7;
        }
        for(int i = 0; i<arr.length; i++) {
            arr[i] = output[i];
            counter++;
        }
    }
}
