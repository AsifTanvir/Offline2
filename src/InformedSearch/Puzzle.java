package InformedSearch;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Puzzle {

    public boolean aStarSearchHamming(Node start,Node goal){
        PriorityQueue<Node> closedSet = new PriorityQueue<>();
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Heuristics hr = new Heuristics();
        ArrayList<Node> neighbors = new ArrayList<>();

        openSet.add(start);
        start.g_score = 0;
        start.f_score = start.g_score + hr.Hamming(start,goal);

        while(!openSet.isEmpty()){
            Node current = openSet.remove();
            if(current == goal){
                return true;//////////////// kaj ache
            }
            closedSet.add(current);
            neighbors = hr.getNeighbors(start);
            for( Node neighbor : neighbors){
                if(closedSet.contains(neighbor)) continue;
                int tentative_score = current.g_score + 1;

                if(!openSet.contains(neighbor) || tentative_score<neighbor.g_score){
                    neighbor.came_from = current;
                    neighbor.g_score = tentative_score;
                    neighbor.f_score = neighbor.g_score + hr.Hamming(neighbor,goal);
                    if(!openSet.contains(neighbor)){
                        openSet.add(neighbor);
                    }
                }
            }

        }
        return false;
    }

    public boolean isPossible(Node start){

        int inversion = 0;
        int length = start.length;
        int lengthArray = length*length;
        int array[] = new int[lengthArray];
        int k =0 ;
        int idx = 0;
        for(int i =0 ; i< length; i++){
            for(int j=0 ; j<length ; j++){
                array[k] = start.matrix[i][j];
                k++;
                if(start.matrix[i][j] == 0) idx = i; //// contains the row number of blank space
            }
        }
        for(int i =0 ; i< lengthArray-1; i++){
            for(int j =i+1; j<lengthArray; j++) {
                if(array[i]!=0 && array[j]!=0 && array[i]>array[j]) inversion++;
            }
        }
        System.out.println("Inversion: "+inversion);

        int parity = inversion+idx;
        if(length%2 == 0 && parity%2 == 0) {
            return false;
        }
        else if(length%2 != 0 && inversion%2 != 0 ) return false;
        else {
            return true;
        }
    }
}
