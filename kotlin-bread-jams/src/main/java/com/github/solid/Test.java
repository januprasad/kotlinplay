package com.github.solid;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {

        String line = "4 2 11 6 5 1 4 3 5 6 8 7 7 12 1";
        String[] numberStrs = line.split(" ");

        Integer[] numbers = new Integer[numberStrs.length];
        for (int i = 0; i < numberStrs.length; i++) {
            // Note that this is assuming valid input
            // If you want to check then add a try/catch
            // and another index for the numbers if to continue adding the others (see below)
            numbers[i] = Integer.parseInt(numberStrs[i]);
        }

        List<Integer> commuteTimes = Arrays.asList(numbers);

        // Determine the number of classes
        int N = commuteTimes.size();
        int k = (int) Math.ceil(Math.log(N) / Math.log(2));

        // Calculate class width
        int min = commuteTimes.stream().min(Integer::compareTo).get();
        int max = commuteTimes.stream().max(Integer::compareTo).get();
        int classWidth = (max - min) / k;

        // Create classes and calculate frequency and cumulative frequency
        int[][] classes = new int[k][3];
        for (int i = 0; i < k; i++) {
            classes[i][0] = min + i * classWidth;
            classes[i][1] = min + (i + 1) * classWidth - 1;
            classes[i][2] = 0;
        }

        for (int time : commuteTimes) {
            for (int i = 0; i < k; i++) {
                if (time >= classes[i][0] && time <= classes[i][1]) {
                    classes[i][2]++;
                    break;
                }
            }
        }

//        // Calculate cumulative frequency
        int cumulativeFrequency = 0;
        int[] sample = new int[k];
        for (int i = 0; i < k; i++) {
            cumulativeFrequency += classes[i][2];
//            classes[i][3] = cumulativeFrequency;
            sample[i] = cumulativeFrequency;
        }
//
//        // Print the results
        System.out.println("Class Limits\tFrequency\tCumulative Frequency");
        for (int cls : sample) {
            System.out.print(cls+" ");
        }
    }
}
