package InformedSearch;

import java.util.Arrays;
import java.util.Objects;

public class Node implements Comparable<Node>{
    int matrix[][];
    int length;
    int g_score;
    int f_score;
    Node came_from;

    public Node(int[][] matrix, int length, int g_score, int f_score, Node came_from) {
        this.matrix = matrix;
        this.length = length;
        this.g_score = g_score;
        this.f_score = f_score;
        this.came_from = came_from;
    }

    @Override
    public String toString() {
        return "Node{" +
                "matrix=" + Arrays.toString(matrix) +
                ", length=" + length +
                ", g_score=" + g_score +
                ", f_score=" + f_score +
                ", came_from=" + came_from +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return length == node.length &&
                g_score == node.g_score &&
                f_score == node.f_score &&
                Arrays.equals(matrix, node.matrix) &&
                Objects.equals(came_from, node.came_from);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(length, g_score, f_score, came_from);
        result = 31 * result + Arrays.hashCode(matrix);
        return result;
    }

    @Override
    public int compareTo(Node o) {
        if(this.f_score>o.f_score){
            return 1;
        }
        else if(this.f_score<o.f_score){
            return -1;
        }
        else {
            return 0;
        }
    }
}
