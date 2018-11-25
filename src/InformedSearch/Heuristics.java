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

    public ArrayList<Node> getNeighbors(Node node){
        ArrayList<Node> list = new ArrayList<>();
        int length = node.length;
        int g_score = node.g_score;
        int f_score = node.f_score;
        Node cameFrom = node.came_from;
        int idx_X =0 ;
        int idx_Y = 0;
        int temp_left[][] = new int[length][length];
        int temp_right[][] = new int[length][length];
        int temp_up[][] = new int[length][length];
        int temp_down[][] = new int[length][length];
        for(int i =0 ; i<length ; i++){
            for(int j =0 ; j<length;j++){
                temp_left[i][j] = node.matrix[i][j];
                temp_right[i][j] = node.matrix[i][j];
                temp_up[i][j] = node.matrix[i][j];
                temp_down[i][j] = node.matrix[i][j];
                if(temp_left[i][j] == 0){
                    idx_X = i;
                    idx_Y = j;
                }
            }
        }

        ///All Possible cases of neighbors
        if(idx_X == 0){
            if(idx_Y == 0){
                temp_down = down(idx_X,idx_Y,temp_down);
                temp_right = right(idx_X,idx_Y,temp_right);
                Node node_d = new Node(temp_down,length,g_score,f_score,cameFrom);
                Node node_r = new Node(temp_right,length,g_score,f_score,cameFrom);
                list.add(node_d);
                list.add(node_r);
            }
            else if(idx_Y == length-1){
                temp_down = down(idx_X,idx_Y,temp_down);
                temp_left = left(idx_X,idx_Y,temp_left);
                Node node_d = new Node(temp_down,length,g_score,f_score,cameFrom);
                Node node_l = new Node(temp_left,length,g_score,f_score,cameFrom);
                list.add(node_d);
                list.add(node_l);
            }
            else{
                temp_down = down(idx_X,idx_Y,temp_down);
                temp_left = left(idx_X,idx_Y,temp_left);
                temp_right = right(idx_X,idx_Y,temp_right);
                Node node_d = new Node(temp_down,length,g_score,f_score,cameFrom);
                Node node_r = new Node(temp_right,length,g_score,f_score,cameFrom);
                Node node_l = new Node(temp_left,length,g_score,f_score,cameFrom);
                list.add(node_d);
                list.add(node_r);
                list.add(node_l);
            }
        }
        else if(idx_Y == 0){
            if(idx_X == length-1){
                temp_up = up(idx_X,idx_Y,temp_up);
                temp_right = right(idx_X,idx_Y,temp_right);
                Node node_u = new Node(temp_up,length,g_score,f_score,cameFrom);
                Node node_r = new Node(temp_right,length,g_score,f_score,cameFrom);
                list.add(node_u);
                list.add(node_r);
            }
            else{
                temp_up = up(idx_X,idx_Y,temp_up);
                temp_down = down(idx_X,idx_Y,temp_down);
                temp_right = right(idx_X,idx_Y,temp_right);
                Node node_d = new Node(temp_down,length,g_score,f_score,cameFrom);
                Node node_r = new Node(temp_right,length,g_score,f_score,cameFrom);
                Node node_u = new Node(temp_up,length,g_score,f_score,cameFrom);
                list.add(node_u);
                list.add(node_d);
                list.add(node_r);
            }
        }
        else if(idx_X == length-1){
            if(idx_Y == length -1){
                temp_up = up(idx_X,idx_Y,temp_up);
                temp_left = left(idx_X,idx_Y,temp_left);
                Node node_u = new Node(temp_up,length,g_score,f_score,cameFrom);
                Node node_l = new Node(temp_left,length,g_score,f_score,cameFrom);
                list.add(node_u);
                list.add(node_l);
            }
            else{
                temp_up = up(idx_X,idx_Y,temp_up);
                temp_left = left(idx_X,idx_Y,temp_left);
                temp_right = right(idx_X,idx_Y,temp_right);
                Node node_u = new Node(temp_up,length,g_score,f_score,cameFrom);
                Node node_r = new Node(temp_right,length,g_score,f_score,cameFrom);
                Node node_l = new Node(temp_left,length,g_score,f_score,cameFrom);
                list.add(node_u);
                list.add(node_r);
                list.add(node_l);
            }
        }
        else if(idx_Y == length-1){
            temp_down = down(idx_X,idx_Y,temp_down);
            temp_left = left(idx_X,idx_Y,temp_left);
            temp_up = up(idx_X,idx_Y,temp_up);
            Node node_d = new Node(temp_down,length,g_score,f_score,cameFrom);
            Node node_u = new Node(temp_up,length,g_score,f_score,cameFrom);
            Node node_l = new Node(temp_left,length,g_score,f_score,cameFrom);
            list.add(node_d);
            list.add(node_u);
            list.add(node_l);
        }
        else {
            temp_up = up(idx_X,idx_Y,temp_up);
            temp_down = down(idx_X,idx_Y,temp_down);
            temp_left = left(idx_X,idx_Y,temp_left);
            temp_right = right(idx_X,idx_Y,temp_right);
            Node node_u = new Node(temp_up,length,g_score,f_score,cameFrom);
            Node node_d = new Node(temp_down,length,g_score,f_score,cameFrom);
            Node node_r = new Node(temp_right,length,g_score,f_score,cameFrom);
            Node node_l = new Node(temp_left,length,g_score,f_score,cameFrom);
            list.add(node_u);
            list.add(node_d);
            list.add(node_r);
            list.add(node_l);
        }
        return list;
    }

    private int[][] left(int idx,int idy,int a[][]){
        int temp[][] = a;
        int value_left=temp[idx][idy-1];
        temp[idx][idy] = value_left;
        temp[idx][idy-1]=0;
        return temp;
    }
    private int[][] right(int idx,int idy,int a[][]){
        int temp[][] = a;
        int value_right=temp[idx][idy+1];
        temp[idx][idy] = value_right;
        temp[idx][idy+1]=0;
        return temp;
    }
    private int[][] up(int idx,int idy,int a[][]){
        int temp[][] = a;
        int value_up=temp[idx-1][idy];
        temp[idx][idy] = value_up;
        temp[idx-1][idy]=0;
        return temp;
    }
    private int[][] down(int idx,int idy,int a[][]){
        int temp[][] = a;
        int value_down=temp[idx+1][idy];
        temp[idx][idy] = value_down;
        temp[idx+1][idy]=0;
        return temp;
    }
}
