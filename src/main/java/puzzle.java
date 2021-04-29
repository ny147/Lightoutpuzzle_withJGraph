
/*Siralak    Teekha                 6213133
  Weerawich  Wongchatchalikun       6213166
  Korawit    Wisetsuwan 	    6213192*/
import java.util.*;
import java.lang.*;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class puzzle {

    private int depth;
    private List<Integer> answer;
    private int size;
    private node nstart;
    protected Graph<Integer, DefaultWeightedEdge> g;

    puzzle(int n) {
        size = n;//get size from user
        int[][] start = new int[size][size];
        Scanner scan = new Scanner(System.in);
        System.out.printf("Initial states (%d bits, left to right, line by line)", size * size);
        System.out.println();
        String str = scan.nextLine();
        try {
            //char[] ch = new char[str.length()];
            ///////////////////// get input from user
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    start[i][j] = str.charAt(i * size + j) - 48;
                    if (start[i][j] != 0 && start[i][j] != 1) {
                        throw new Exception("Number error");
                    }
                    //  System.out.print(start[i][j]);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("can't create initital state please ckeck input amout");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Input error -> " + e);
            System.exit(0);
        }

        /*  start[0][0] = 0; //case 3*3 example
        start[0][1] = 0;
        start[0][2] = 0;

        start[1][0] = 1;
        start[1][1] = 1;
        start[1][2] = 0;

        start[2][0] = 1;
        start[2][1] = 0;
        start[2][2] = 1;*/
        ///////////////////// get input from user
        g = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        nstart = new node(start); // input from user

        System.out.printf("\nBit string  = %s, Decimal ID = %d\n", str, nstart.GetValue());
        nstart.Printstate();
        /*if(He.compareTo(nstart) == 0)
            System.out.println("hehe boi");*/
        //Printstate(ValueToMatrix(344));
        answer = BFS();
        printAns();
        if (depth <= (size * size)) {
            System.out.print("      "); // 6 char
            for (int i = 0; i < size; i++) {
                System.out.printf("| col %d ", i);
            }
            System.out.println();
            for (int i = 0; i < size; i++) {
                System.out.printf("row %d ", i);
                for (int j = 0; j < size; j++) {
                    System.out.printf("|   0   ");

                }
                System.out.println("");
            }
        }

    }

    public List<Integer> BFS() {
        node Nopath = new node(); // node that has -1 value for check
        ArrayDeque<node> Q = new ArrayDeque<node>();
        Q.add(nstart);
        Q.add(Nopath);
        ArrayList<Integer> checkpath = new ArrayList<Integer>();
        node temp_node;
        int value = -2;
        System.out.println("Please wait a minute some states have more time to find solution...");
        while (!Q.isEmpty()) {

            temp_node = Q.pollFirst();
            value = temp_node.GetValue();
            //System.out.println("check value " + value);
            if (depth > (size * size)) {

                System.out.println("This state is no solution");
                System.exit(0);

            } else if (value == -1) {
                //System.out.println("check depth " + depth);
                depth++;

                Q.add(temp_node);
            } else if (value == 0) {
                /* answer.add(temp_node);
                while (temp_node.GetPreviousNode() != null) {
                    node previous = temp_node.GetPreviousNode();
                    answer.add(previous);

                    // previous.print();
                    temp_node = previous;
                    //System.out.println("");
                }*/

                System.out.println(depth + " moves to turn off all light");
                if(depth ==0){
                    System.out.println("Initial states has turn off all light");
                    System.exit(0);
                }
                System.out.println("\nStart");
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
        //System.out.println("finish");
        Mygraph solution = new Mygraph(g);
        return solution.testShortestPath(nstart.GetValue(), 0);

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

        for (int i = 0; i < answer.size() - 1; i++) {
            node A = new node(answer.get(i), size);
            node B = new node(answer.get(i + 1), size);
            A.Printstate();
            System.out.println(">>> move " + (i + 1) + " : " + Findtoggle(A, B));
            // A.PrintStr();

        }
    }

    public String Findtoggle(node A, node B) {
        int state_A[][] = A.GetState();
        int state_B[][] = B.GetState();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (state_A[i][j] != state_B[i][j]) {

                    int value = new node(toggle(A, i, j)).GetValue();
                    if (value == B.GetValue()) {
                        String toggle = "Row " + String.valueOf(i) + " Colum " + String.valueOf(j);
                        return toggle;

                    }

                }

            }

        }
        return null;
    }

}
