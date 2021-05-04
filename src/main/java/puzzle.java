
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
    private Grid nstart;
    private Graph<Integer, DefaultWeightedEdge> g;

    puzzle(int n) {
        size = n;
        int[][] start = new int[size][size];
        Scanner scan = new Scanner(System.in);
        System.out.printf("Initial states (%d bits, left to right, line by line)", size * size);
        System.out.println();
        String str = scan.nextLine();
        while (true) {
            try {
                
                if (str.length() != size * size) {
                    throw new Exception("String size error ");
                }
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        start[i][j] = str.charAt(i * size + j) - 48;
                        if (start[i][j] != 0 && start[i][j] != 1) {
                            throw new Exception("Number error");
                        }
                       
                    }
                }
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("can't create initital state please ckeck input amout");
           
                str = scan.nextLine();
            } catch (Exception e) {
                System.out.println("Input error -> " + e + "please input again");
                
                str = scan.nextLine();
            }
        }

        g = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        nstart = new Grid(start); // input from user

        System.out.printf("\nBit string  = %s, Decimal ID = %d\n", str, nstart.GetValue());
        nstart.Printstate();
       
        answer = BFS();
        if(answer != null)
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
        System.out.println("Puzzle finish\nThx to play\n");
        

    }

    public List<Integer> BFS() {
        Grid Nopath = new Grid(); // Grid that has -1 value for check
        ArrayDeque<Grid> Q = new ArrayDeque<Grid>();
        Q.add(nstart);
        Q.add(Nopath);
        ArrayList<Integer> checkpath = new ArrayList<Integer>();
        Grid temp_node;
        int value = -2;
        System.out.println("Please wait a minute some states have more time to find solution...");
        while (!Q.isEmpty()) {
         
            temp_node = Q.pollFirst();
            value = temp_node.GetValue();
             
            if (depth > (size * size)) {
               
                System.out.println("This state is no solution");
              
                return null;
                //System.exit(0);

            } else if (value == -1) {
               
                depth++;

                Q.add(temp_node);
            } else if (value == 0) {
                

                System.out.println(depth + " moves to turn off all light");
                if (depth == 0) {
                    System.out.println("Initial states has turn off all light");
                    System.exit(0);
                }
                System.out.println("\nStart");
                break;

            } else {
                for (int i = 0; i < size; i++) {

                    for (int j = 0; j < size; j++) {
                        int[][] st = toggle(temp_node, i, j);
                        Grid newnode = new Grid(st);
                        int temp = newnode.GetValue();
                        if (!checkpath.contains(temp)) {
                            checkpath.add(temp);
                         

                            Graphs.addEdgeWithVertices(g, value, temp, 1);
                            Q.add(newnode);
                        }

                    }
                }

            }

        }
        
        Mygraph solution = new Mygraph(g);
      
        return solution.FindShortestPath(nstart.GetValue(), 0);

        
    }

    public  int[][] deepCopy(int[][] org) {
        if (org == null) {
            return null;
        }

        int[][] res = new int[org.length][];
        for (int i = 0; i < org.length; i++) {

            res[i] = Arrays.copyOf(org[i], org[i].length);
        }
        return res;
    }

    public int[][] toggle(Grid x, int i, int j) {

        int[][] newstate = new int[size][size];
        newstate = deepCopy(x.GetState());
        int c1 = 0;
        int c2 = 0;
        
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
            Grid A = new Grid(answer.get(i), size);
            Grid B = new Grid(answer.get(i + 1), size);
            A.Printstate();
            System.out.println(">>> move " + (i + 1) + " : " + Findtoggle(A, B));
           

        }
    }

    public String Findtoggle(Grid A, Grid B) {
        int state_A[][] = A.GetState();
        int state_B[][] = B.GetState();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (state_A[i][j] != state_B[i][j]) {

                    int value = new Grid(toggle(A, i, j)).GetValue();
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
