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
        System.out.println("Welcome to Lightout puzzle solve program ");
        menu();
    }

    public static void menu() {
        Scanner scanchoice = new Scanner(System.in);
        String choice;
        boolean checksize;
        while (true) {
            System.out.println("1. Solve puzzle");
            System.out.println("2. Information about light out puzzle");
            System.out.println("3. Exit program");
            System.out.println("choose your Activity");
            try {
                choice = scanchoice.nextLine();
                switch (choice) {

                    case "1":
                        //System.out.println("Number of row for square grid =  ");
                        int num = InputSize();
                        puzzle p = new puzzle(num);
                        break;

                    case "2":
                        Information();
                        break;
                    case "3":
                        System.out.println("Exit program thx to play");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("please input choice on menu");
                }
                if (choice == "1") {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("please input positive Integer");

            }

        }

    }

    public static void Information() {
        System.out.printf("\n=================================================================\n");
        System.out.printf("                        Lights out puzzle game\n");
        System.out.println("  Lights Out is a puzzle game. consisting of a grid of lights ");
        System.out.println("  that are either on or off Pressing any light will toggle  ");
        System.out.println("  it and its adjacent lights. The goal of the game is to switch ");
        System.out.println("  all the lights off.");
        System.out.printf("\n     you can play it on http://daattali.com/shiny/lightsout/  \n");
        System.out.printf("=================================================================\n");
    }

    public static int InputSize() {
        Scanner scan = new Scanner(System.in);
        int size;
        while (true) {
            try {
                System.out.println("Number of row for square grid =  ");
                size = Integer.parseInt(scan.nextLine());
                if (size == 3 || size == 4 || size == 5) {
                    //loop = false;
                    break;
                } else {
                    //System.out.println("Please input size again: size must be 3, 4 ,5");
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Please input size again: size must be 3, 4 ,5");

            }

        }
        return size;
    }
}
