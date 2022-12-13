package pa3;
import edu.princeton.cs.algs4.In;
import java.util.*;

public class Coins {
    //Instance Variables
    private int M; // amount
    private ArrayList<Integer> totalcoins; // # of different coins
    private HashMap<Integer, Integer> map; // map coins with #coins used
    private int[]memo; // used to memoize the sub problems
    private int[] lastCoin; // keep track of the last coin used for each sub-problem.

    public Coins(String s) {
        // Initialising the instance variables
        this.map = new HashMap<>();
        this.totalcoins = new ArrayList<>();
        // Reading the file and instantiating the instance variables.
       In in = new In(s);
       ArrayList<String> list = new ArrayList<>();
       while(in.hasNextLine()){
           list.add(in.readLine());
       }
       // Parsing the file according the given format.
       this.M = Integer.parseInt(list.get(0));
       String[] integers = list.get(1).split(" ");
       for(String string : integers){
           this.totalcoins.add(Integer.parseInt(string));
       }
       // Initialising the memo list to its each value in the list to Maximum value
        // cause maximum number of coins will be simply just the amount value (1 cents).
        // Initialising the lastCoin list.
        this.memo = new int[this.M + 1];
        this.lastCoin = new int[this.M+1];
        for (int i = 1; i <= this.M; i++) {
            memo[i]= M;
        }

        // Memorizing the sub-problems and adding it into the memo list.
        for (int i = 1; i <= this.M; i++) {
            for (int j = 0; j < this.totalcoins.size(); j++) {
                // if the coin is smaller than the amount... (otherwise, the coin can't be used)
                // if the initial value at the ith position in memo is greater than the one we just got, them replace the value in memo.
                if (i >= totalcoins.get(j) && memo[i - this.totalcoins.get(j)] + 1 < memo[i]) {
                        memo[i] = memo[i - this.totalcoins.get(j)]  + 1;
                        // adding the last coin used into the lastCoin list.
                        lastCoin[i] = this.totalcoins.get(j);
                }

            }

        }
        // Initialising the map to keep track of the number of coins used of each type given.
        // amount = M (amount need to make a change for)
        int amount = this.M;
        // As long as the make change is not 0 (amount is not 0)...
        while (amount != 0) {
            // Get the last coin used (max coin so far smaller or equal to the given amount)
            int coin = lastCoin[amount];
            // if the map already contains the coin, then increment the value or make a new entry with 1 as the value.
            if(map.containsKey(coin)){
                map.put(coin, map.get(coin)+1);
            }
            else{
                map.put(coin, 1);
            }
            // deduct the value of the coin from the amount and go on until it gets 0 (i.e. make change got successfully)
            amount = amount - coin;
        }
    }

    // returning the number of coins used in the optimized solution to make change.
    public int makeChange() {
        return this.memo[this.M];
    }

    // Returning the configuration of the #of coins used of each type of coin used in the optimized solution.
    public int howMany(int coin) {
        return this.map.getOrDefault(coin, 0);
    }


    // main method.
    public static void main(String[] args) {
        Coins c = new Coins(args[0]);
    }
}