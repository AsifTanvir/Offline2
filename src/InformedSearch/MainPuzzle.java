package InformedSearch;

//import com.sun.java.util.jar.pack.Package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MainPuzzle {
    public static void main(String[] args) {
        System.out.println("Enter the number of n: ");
        Scanner sc = new Scanner(System.in);
        //File file = new File("/home/asif/Asif/Study/L-3,T-2/CSE 318 Artificial Intelligence Sessional/Offline2/src/InformedSearch/");
        //BufferedReader sc = new BufferedReader(new FileReader(file));
        int n = sc.nextInt();
        int matrix[][] = new int[n][n];
        int matrix2[][] = new int[n][n];
        ArrayList<Node> list = new ArrayList<>();
        for(int i =0 ; i<n ; i++){
            for(int j = 0; j<n;j++){
                matrix[i][j] = sc.nextInt();
            }
        }
        for(int i =0 ; i<n ; i++){
            for(int j = 0; j<n;j++){
                matrix2[i][j] = sc.nextInt();
            }
        }

        Node node = new Node(matrix,n,0,0,null);
        Node node2 = new Node(matrix2,n,0,0,null);

        Heuristics hr = new Heuristics();
        hr.Hamming(node,node2);
        hr.Manhattan(node);
        list = hr.getNeighbors(node);
        for(Node nude : list){
            int a[][] = nude.matrix;
            for(int i =0 ; i<n;i++){
                for(int j =0 ; j<n ; j++){
                    System.out.print(a[i][j]+" ");
                }
                System.out.println();
            }
        }

        Puzzle puzzle = new Puzzle();
        boolean b = puzzle.isPossible(node);
        if(b == false) System.out.println("This puzzle is unsolvable!!!");
        else{
            //puzzle.aStarSearch(node,node2);
        }
        System.out.println(b);
    }
}
