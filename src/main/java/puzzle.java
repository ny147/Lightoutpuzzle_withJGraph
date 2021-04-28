 /*Siralak    Teekha                 6213133
  Weerawich  Wongchatchalikun       6213166
  Korawit    Wisetsuwan 	    6213192*/
import java.util.*;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class puzzle {

    private int depth;
    private List<Integer> answer;
    private int size = 3;
    private node nstart;
    protected Graph<Integer, DefaultWeightedEdge> g;

    puzzle() {
        size = 3;//get size from user
        int[][] start = new int[size][size];
         ///////////////////// get input from user
        start[0][0] = 0;
        start[0][1] = 0;
        start[0][2] = 0;

        start[1][0] = 1;
        start[1][1] = 1;
        start[1][2] = 0;

        start[2][0] = 1;
        start[2][1] = 0;
        start[2][2] = 1;
            ///////////////////// get input from user
        g = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        nstart = new node(start); // input from user
        System.out.printf("%d", nstart.GetValue());

        /*if(He.compareTo(nstart) == 0)
            System.out.println("hehe boi");*/
        //Printstate(ValueToMatrix(344));
        answer = BFS();
        printAns();

    }

    public List<Integer> BFS() {
        node Nopath = new node(); // node that has -1 value for check
        ArrayDeque<node> Q = new ArrayDeque<node>();
        Q.add(nstart);
        Q.add(Nopath);
        ArrayList<Integer> checkpath = new ArrayList<Integer>();
        node temp_node;
        int value = -2;

        while (!Q.isEmpty()) {

            temp_node = Q.pollFirst();
            value = temp_node.GetValue();
            System.out.println("check value " + value);
            if (value == 0) {
                /* answer.add(temp_node);
                while (temp_node.GetPreviousNode() != null) {
                    node previous = temp_node.GetPreviousNode();
                    answer.add(previous);

                    // previous.print();
                    temp_node = previous;
                    //System.out.println("");
                }*/

                System.out.println("find the goal " + depth);
                break;
            } else if (value == -1) {
                depth++;

                Q.add(temp_node);
            } else if (depth > (size * size)) {

                System.out.println("this state is no solution");
                break;

            } else {
                for (int i = 0; i < size; i++) {

                    for (int j = 0; j < size; j++) {
                        int[][] st = toggle(temp_node, i, j);
                        node newnode = new node(st);
                        int temp = newnode.GetValue();
                        if (!checkpath.contains(temp)) {
                            checkpath.add(temp);
                            // count++;
                           
                            Graphs.addEdgeWithVertices(g, value, temp, 1);
                            Q.add(newnode);
                        }

                    }
                }

            }

        }
        System.out.println("finish");
        Mygraph solution = new Mygraph(g);
        return solution.testShortestPath( nstart.GetValue(),0);

        //answer.addAll(solution.testShortestPath());
    }

    public static int[][] deepCopy(int[][] org) {
        if (org == null) {
            return null;
        }

        int[][] res = new int[org.length][];
        for (int i = 0; i < org.length; i++) {

            res[i] = Arrays.copyOf(org[i], org[i].length);
        }
        return res;
    }

    public int[][] toggle(node x, int i, int j) {

        int[][] newstate = new int[size][size];
        newstate = deepCopy(x.GetState());
        int c1 = 0;
        int c2 = 0;
        //c1 = newstate[][]
        newstate[i][j] = togglebit(newstate[i][j]);

        if (i + 1 < size) {

            newstate[i + 1][j] = togglebit(newstate[i + 1][j]);
        }
        if (i - 1 >= 0) {

            newstate[i - 1][j] = togglebit(newstate[i - 1][j]);
        }
        if (j + 1 < size) {

            newstate[i][j + 1] = togglebit(newstate[i][j + 1]);

        }
        if (j - 1 >= 0) {

            newstate[i][j - 1] = togglebit(newstate[i][j - 1]);
        }

        return newstate;
    }

    public int togglebit(int n) {

        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 0;
        }
        return -1;
    }

 
    public void printAns() {

        for (int i = 0; i < answer.size()-1; i++) {
            node A = new node(answer.get(i), size);
            node B = new node(answer.get(i + 1), size);
            System.out.println(Findtoggle(A,B));
            A.Printstate();

        }
    }

    public String Findtoggle(node A, node B) {
        int state_A[][] = A.GetState();
        int state_B[][] = B.GetState();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (state_A[i][j] != state_B[i][j]) {
                    
                    int value = new node(toggle(A,i,j)).GetValue();
                    if(value == B.GetValue()){
                        String toggle = "Row "  + String.valueOf(i) + "Colum" + String.valueOf(j);
                    return toggle;
                    
                    }
                        
                }

            }

        }
        return null;
    }

}
