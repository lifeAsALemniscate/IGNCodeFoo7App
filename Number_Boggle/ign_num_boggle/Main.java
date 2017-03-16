import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {




    /*
    Create a program that generates and displays a 3x3 grid of random whole positive numbers (n) where 0 <= n <= 9. The program should then find all possible combinations of numbers that add up to the area of the grid using these rules (similar to Boggle™):
        a. The numbers must be "chained" in sequentially adjacent cells, where "adjacent" cells are those horizontally, vertically, and diagonally neighboring

        b. The chain does not have to be in a line. For example, it could go horizontally once, then vertically once, and finally diagonally once

        c. At least the “grid width - 1” cells must be used in the chain (a 3x3 grid means that at least two cells must be used)

        d. A chain cannot repeat a grid cell that it has already used

        e. Chains that use the exact same cells as a previous chain are considered repeats and should not be counted

        Grid Example:

        9 4 6

        8 1 0

        3 7 2


        Valid Answer Examples:

        7 + 2 = 9

        2 + 1 + 6 = 9

        2 + 1 + 6 + 0 = 9


        Invalid Answer Examples:

          9 = 9

        3 + 6 = 9

        1 + 7 + 1 = 9





     */
    final static int MAX_ROW = 3;
    final static int MAX_COL = 3;
    final static int AREA= MAX_ROW * MAX_COL;
    final static ArrayList<ArrayList<Integer>> validSequences= new ArrayList<>() ;

    public static void main(String[] args) {

        final int AREA = MAX_ROW * MAX_COL;

        //int[][] grid = {{9,4,6},
        //              {8,1,0},
        //            {3,7,2}};

        int [][] grid = getDynamicGrid();
        getSumsRecDriver(grid);
        System.out.println("Random grid of " + MAX_ROW + " rows and " + MAX_COL + " cols.");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.print("\n");
        }

        System.out.println("\nSolutions: \n" + validSequences.toString());

    }

    static void getSumsRecDriver(int[][] grid){


        boolean[][] visited = new boolean[MAX_ROW][MAX_COL];

        for (int x=0; x< MAX_ROW; x++) {
            for (int y = 0; y < MAX_COL; y++) {
                getSums(grid, visited, x, y, new ArrayList<Integer>());
            }
        }
    }
    public static void getSums(int[][] grid, boolean[][] visited, int x, int y, ArrayList<Integer> sequence){

        //we are currently traveling in this cell
        visited[x][y] = true;

        //we assume that current cell will work, add to sequence
        sequence.add(grid[x][y]);

        //we check to see if the sum of the current sequence is equal to 9
        //this is our base case
        if(sum(sequence)== AREA && sequence.size() >= MAX_ROW-1){

            if(!isRepeat(sequence)) {
                //MUST ADD A CLONE OF SEQUENCE OR ELSE YOU REFRENCES WILL BE CORRUPTED
                validSequences.add((ArrayList<Integer>)sequence.clone());

            }
        }


        //for each valid adjacent cell(not visited and within bounds), recursivley call getSums()
        for (int row=x-1; row<=x+1 && row<MAX_ROW; row++)
            for (int col=y-1; col<=y+1 && col<MAX_COL; col++)
                if (row>=0 && col>=0 && !visited[row][col])
                    getSums(grid,visited, row, col, sequence);

        //remove current cell value from sequence and mark the cell as unvisited
        sequence.remove(sequence.size()-1);
        visited[x][y]= false;
    }

    public static boolean isRepeat(ArrayList<Integer> sequence){
        if(validSequences.size()==0)
            return false;
        //for each valid sequence
        for(int x = 0; x < validSequences.size();x++){
            if(validSequences.get(x).size()==sequence.size()) {
                ArrayList<Integer> tempValid = (ArrayList<Integer>) validSequences.get(x).clone();
                ArrayList<Integer> tempSec = (ArrayList<Integer>) sequence.clone();

                Collections.sort(tempValid);
                Collections.sort(tempSec);


                int repeat = 0;
                for(int y = 0; y < tempSec.size();y++){
                    if(tempValid.get(y)- tempSec.get(y) ==0){
                        repeat++;
                    }

                }
                if(repeat==tempSec.size()){
                    return true;
                }
            }
        }
        return false;
    }

    public static int[][] getDynamicGrid(){
        int[][] grid = new int[MAX_ROW][MAX_COL];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = (int)(Math.random()*10);
            }
        }

        return grid;
    }
    public static int sum(List<Integer> list) {
        int sum = 0;

        for (int i : list)
            sum = sum + i;

        return sum;
    }
}
