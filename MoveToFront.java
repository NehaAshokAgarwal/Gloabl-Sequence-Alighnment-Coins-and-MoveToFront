import edu.princeton.cs.algs4.In;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * The Main class implements an application that reads lines from the standard input
 * and prints them to the standard output.
 */
public class Main {
    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        String[] list = new String[2];
        int i = 0;

        // parsing the input according to the format given.
        while ((line = in.readLine()) != null) {
            list[i] = line;
            if(i == 0){
                String[] list1 = (list[0].split(" "));
                listn1 = new int[list1.length];
                for(int g = 0; g < list1.length; g++){
                    listn1[g] = Integer.parseInt(list1[g]);
            }

            if(i == 1){
                String[] list2  = (list[1].split(" "));
                int[] listn2 = new int[list2.length];
                for(int g = 0; g < list1.length; g++){
                    listn2[g] = Integer.parseInt(list1[g]);
            }

            i++;
        }

        // After this for loop, we know the smallet value wher the list starts
        int value = Integer.MAX_VALUE;
        int index = 0;
        for(int k = 0 ; k < list.length; k++){
            if(listn2[k] < value){
                value = list2[k];
                index = k;
            }

        }

        // we know the index now in the main list of the input numbers provided

        for(int m = 0; m < list1.length; m++){
            int valuen1 = list[m];
            for(int n = 0; n < list1.length; n++){
                if(value1 == list2[n]){
                    System.out.print(n + " ");
                }
            }

        }
        // printing the extra line character

        System.out.println();
    }
}
}
