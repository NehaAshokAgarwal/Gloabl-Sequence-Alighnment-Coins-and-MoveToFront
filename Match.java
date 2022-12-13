package pa3;
import edu.princeton.cs.algs4.In;
import java.util.*;


public class Match {
    public Match(){
    }
    
// return the optimal match between the strings a and b
// return null if either string is null or if either string is length 0
    public Path match(String a, String b){
        //edge cases
        if(a.equals(null) || b.equals(null)){
            return null;
        }
        //edge case
        if(a.length() == 0 || b.length() == 0){
            return null;
        }
        // Creating a matrix of Path objects.
        Path[][] matrix = new Path[a.length()+1][b.length()+1];

        // Initialising the matrix
        for(int i = 0; i <= a.length(); i++){
            for(int j = 0; j <= b.length(); j++){
                matrix[i][j] = new Path();
                matrix[i][j].setRow(i);
                matrix[i][j].setCol(j);
            }
        }

        // Filling matrix of path objects
        // filling the first column of the matrix.
        for(int i = 0; i <= a.length(); i++){
            matrix[i][0].setCost(i*2);
        }
        // filling the first row of the matrix.
        for(int j = 0; j <= b.length(); j++){
            matrix[0][j].setCost(j*2);
        }
        // filling the middle portion of the matrix according the dynamic algorithm.
        // for all the rows...
        for(int i = 1; i < a.length()+1; i++){
            // for all the columns...
            for(int j = 1; j < b.length()+1; j++){
                //If the character in the string at the ith position matches with
                // that of the character at jth position in string b, then the next
                // element will be the same as of the previous one - matrix[i-1][j-1]
                if(a.charAt(i-1)==b.charAt(j-1)){
                    matrix[i][j].setCost(matrix[i-1][j-1].getCost());
                }
                //Otherwise,
                // the minimum cost oout of the three neighbour elements is taken.
                else if(a.charAt(i-1)!=b.charAt(j-1)){
                    matrix[i][j].setCost(min(matrix[i-1][j-1].getCost()+1,
                        matrix[i-1][j].getCost()+2,
                        matrix[i][j-1].getCost()+2));
                }
            }
        }

        //Backtrack
        // Creating a list to keep track of the elements taken during tracing back.
        ArrayList <Path> list = new ArrayList<>();
        for(int i = a.length(); i > 0 ; i--){
            for(int j = b.length(); j > 0; j--){
                // If the character at the ith position in the string a matches with that
                // of the jth position in the string b, then we track in the diagonal direction
                if(a.charAt(i-1)==b.charAt(j-1)){
                    matrix[i][j].setNext(matrix[i-1][j-1]);
                    list.add(matrix[i][j]);
                }
                // otherwise, there is a gap, and we then select the elements which has the min,
                // cost of its all the neighbours. Also, add the element into the list.
                else if(a.charAt(i-1)!=b.charAt(j-1)){
                    int v = (min(matrix[i-1][j-1].getCost()+1,
                            matrix[i-1][j].getCost()+2,
                            matrix[i][j-1].getCost()+2));
                    if(v == matrix[i-1][j-1].getCost()+1){
                    matrix[i][j].setNext(matrix[i-1][j-1]);
                    list.add(matrix[i-1][j-1]);}
                    else if(v ==matrix[i-1][j].getCost()+2){
                        matrix[i][j].setNext(matrix[i-1][j]);
                        list.add(matrix[i-1][j]);
                    }
                    else{
                        matrix[i][j].setNext(matrix[i][j-1]);
                        list.add(matrix[i][j-1]);
                    }

                }
            }
        }
        // setting the first element in the matrix as 0 and its next element as null.
        matrix[0][0].setNext(null);
         return matrix[a.length()][b.length()];
    }

    // Private minimum function to help in the Match() method.
    private static int min(int a, int b, int c) {
        int min1 = (Math.min(a, b));
        return Math.min(min1, c);
    }
    // Main method
    public static void main(String[] args){
        ArrayList<String> list = new ArrayList<>();
        // Reading the file from the standard input.
        In in = new In(args[0]);
        while(in.hasNextLine()){
            list.add(in.readLine());
        }
        // creating the Match object.
        Match match = new Match();
        match.match(list.get(0), list.get(1));

        /* String a = "AACAGTTACC";
        String b = "TAAGGTCA";
        Match match = new Match();
        Path path = match.match("AACAGTTACC", "TAAGGTCA" );
        for(Path p = path; p!= null; p = p.getNext()){
            int row = p.getRow();
            if(p.getRow() == 0){
                break;
            }
            System.out.print(a.charAt(row-1) + " ");

        }
        System.out.println();
        for(Path p = path; p!= null; p = p.getNext()){
            int row = p.getCol();
            if(p.getRow() == 0){
                break;
            }
            System.out.print(b.charAt(row-1) + " ");

        }

         */

    }


}

