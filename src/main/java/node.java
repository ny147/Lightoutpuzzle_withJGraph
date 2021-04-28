 /*Siralak    Teekha                 6213133
  Weerawich  Wongchatchalikun       6213166
  Korawit    Wisetsuwan 	    6213192*/
public class node {

    private int[][] state;
    private int value;
    private int size;
    node(int[][] a) {
        size = a.length;
        state = new int[size][size];
        state = a;
        StateValue();
        
    }
    node(int v,int s){
     value = v;
     size =  s;
     state = new int[size][size];
     ValueToMatrix();
    }
    node() {
        value = -1;

    }
    
      public void ValueToMatrix() {
         int value_to_matrix = value;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                state[i][j] = value_to_matrix%2;
               value_to_matrix = value_to_matrix/ 2;
                
                
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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(state[i][j] + " ");

            }
            System.out.println("");
        }
    }
  


    public int GetValue() {
        return value;
    }

    public int[][] GetState() {
        return state;
    }


  
}
