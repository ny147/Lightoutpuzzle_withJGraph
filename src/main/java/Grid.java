
/*Siralak    Teekha                 6213133
  Weerawich  Wongchatchalikun       6213166
  Korawit    Wisetsuwan 	    6213192*/
public class Grid {

    private int[][] state;
    private int value;
    private int size;
    private int[] str;

    Grid(int[][] a) {
        size = a.length;
        state = new int[size][size];
        state = a;
        StateValue();

    }

    Grid(int v, int s) {
        value = v;
        size = s;
        state = new int[size][size];
        str = new int[size * size];
        ValueToMatrix();
    }

    Grid() {
        value = -1;

    }

    public void ValueToMatrix() {
        int value_to_matrix = value;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                state[i][j] = value_to_matrix % 2;
                
                str[(i * size) + j] = state[i][j];
                value_to_matrix = value_to_matrix / 2;
            }
        }

    }

    public void StateValue() {

        int base = 1;
        int on = 0;
        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {
                on += state[i][j];
                value = value + base * state[i][j];
                base = base * 2;
            }
        }

    }

    public void Printstate() {
        System.out.print("      "); // 6 char
        for (int i = 0; i < size; i++) {
            System.out.printf("| col %d ",i);
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.printf("row %d ",i);
            for (int j = 0; j < size; j++) {
                System.out.printf("|   %d   ",state[i][j] );

            }
            System.out.println("");
        }
        System.out.println();
    }

    public int GetValue() {
        return value;
    }

    public int[][] GetState() {
        return state;
    }


}
