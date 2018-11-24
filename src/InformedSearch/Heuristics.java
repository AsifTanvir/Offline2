package InformedSearch;

import java.util.ArrayList;

public class Heuristics {

    public int Hamming(Node node,Node goal){
        int length = node.length;
        int count = (length*length)-1;
        System.out.println(count);
        for(int i =0 ; i<node.length ; i++){
            for(int j =0 ; j<node.length ; j++){
                if(node.matrix[i][j] != goal.matrix[i][j]) continue;
                count--;
            }
        }
        System.out.println("Hamming distance: "+count);
        return count;
    }

    public int Manhattan(Node node){
        int manDistance = 0;
        for(int i =0 ; i < node.length ; i++){
            for(int j =0 ; j<node.length; j++){
                int value = node.matrix[i][j];
                if(value != 0){
                    int horizon = (value-1)/node.length;
                    int vertical = (value-1)%node.length;
                    int x = i-horizon;
                    int y = j-vertical;
                    manDistance += Math.abs(x) + Math.abs(y);
                }
            }
        }
        System.out.println("Manhattan distance: "+manDistance);
        return manDistance;
    }

    public int LinearConflict(Node node){
        int conflict = 0;


        return conflict;
    }

    public ArrayList<Node> getChildren(Node node){
        ArrayList<Node> list = new ArrayList<>();
        
        return list;
    }
}
