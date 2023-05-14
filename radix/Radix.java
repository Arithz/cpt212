package radix; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Radix {

    private float[] arr;
    private int counter;

    public Radix(float[] numbers){
        arr = numbers;
        counter = 0;
    }

    public float[] radixSort() {
        System.out.println("Original array: " + Arrays.toString(arr));                    
        System.out.println("--------------------");

        loopArray(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
        System.out.println("--------------------");
        float[] sorted = groupNumbers();

        System.out.println("Rough number of operation: " + counter);
        System.out.println("--------------------");
        return sorted;
    }

    // loop array to sort using radix algorithm 
    public void loopArray(float[] arr) {
        int max = getMax(); counter++;
        for(int exp = 1; max/exp > 0; exp *= 10) {
            countSort(arr, exp);
            counter++;
        } 
    }

    public float[] groupNumbers() {
        // make list to group related numbers
        List<List<Float>> groupedNumbers = new ArrayList<>(); counter++;
        for(int i = 0; i< arr.length; i++) {
            // skip if the number is already grouped
            if(i>0 && Math.floor(arr[i]) == Math.floor(arr[i-1])) {
                counter++;
                continue;
            }
            
            // make a list to hold the grouped numbers
            List<Float> buckets = new ArrayList<>();
            if(i < arr.length -1 && Math.floor(arr[i]) == Math.floor(arr[i+1])) {
                for(int j=i; j<arr.length; j++) {
                    // add the number to the bucket if it is related
                    if(Math.floor(arr[j]) == Math.floor(arr[i])) {
                        buckets.add(arr[j]);
                        counter += 2;
                    }
                }
                counter++;
            } else {
                buckets.add(arr[i]);
                counter++;
            }
            // add the bucket to the groupedNumbers list
            groupedNumbers.add(buckets);
            counter++;
        }
        
        System.out.println("Grouped Numbers: " + groupedNumbers);
        System.out.println("--------------------");
        return sortGroupedNumbers(groupedNumbers, arr.length);

    }

    public float[] sortGroupedNumbers(List<List<Float>> groupedNumbers, int arrLength) {
        float[] output = new float[arrLength]; counter++;
        // use to add the sorted groups to the output array
        int index = 0; counter++;
        for(int i=0 ; i<groupedNumbers.size(); i++) {
            float[] group = new float[groupedNumbers.get(i).size()]; counter++;
            // convert the group to an array
            for(float x: groupedNumbers.get(i)) {
                group[groupedNumbers.get(i).indexOf(x)] = x;
                counter+=2;
            }
            int maxDecimalPlaces = getMaxDecimal(group); counter++;
            // apply the maxDecimalplace to all the numbers in the group
            for(int j=0; j<group.length; j++) {
                group[j] = group[j] * (float) Math.pow(10, maxDecimalPlaces);
                counter+=2;
            }
            if(group.length >0) loopArray(group);
            counter++;
            //convert back the group to its original form
            for(int j=0; j<group.length; j++) {
                group[j] = group[j] / (float) Math.pow(10, maxDecimalPlaces);
                counter+=2;
            }
            // add the sorted group to the output array
            for(float x: group) {
                output[index] = x;
                index++;
                counter+=2;
            }
        }
        return output;
    }

    // get the max number
    public int getMax() {
        float max = arr[0];
        for(int i =0; i<arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        return (int)max;
    }

    // get the max decimal places
    public int getMaxDecimal(float[] arr) {
        int maxDecimalPlaces = 0; counter++;
        for (float num : arr) {
            // get the decimal places of the number and compare with previous max decimal places
            int decimalPlaces = String.valueOf(num).split("\\.")[1].length(); counter++;
            if (decimalPlaces > maxDecimalPlaces) {
                maxDecimalPlaces = decimalPlaces;
                counter+=2;
            }
        }
        return maxDecimalPlaces;
    }

    public  void countSort(float[] arr, int exp ) {
        float[] output = new float[arr.length]; counter++;
        int[] count = new int[10]; counter++;
        for(int i = 0; i<arr.length; i++) {
            count[(int) ((arr[i]/exp)%10)]++;
            counter+=3;
        }
        for(int i = 1; i<10; i++) {
            count[i] += count[i-1];
            counter+=2;
        }
        for(int i = arr.length-1; i>=0; i--) {
            output[(int)count[(int)(arr[i]/exp)%10]-1] = arr[i];
            count[(int)(arr[i]/exp)%10]--;
            counter +=7;
        }
        for(int i = 0; i<arr.length; i++) {
            arr[i] = output[i];
            counter++;
        }
    }
}
