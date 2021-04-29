/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

/**
 *
 * @author Nearu
 */
public class myapplicaition {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Number of row for square grid =  ");
        int num = scan.nextInt();
        puzzle p = new puzzle(num);
    }

}
