package it.conway;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Scanner scanner = new Scanner(System.in);

    public static void main( String[] args ) throws InterruptedException, IOException {
        printTitle();
        printAbout();
        printInstructions();
        char command = readCommand();
//        int i = readInt();
        System.out.println("Command: " + command);
        Thread.sleep(5000);


        /*
        List<List<Boolean>> matrix = new ArrayList<>();
        matrix.add(List.of(true,true,true));
        matrix.add(List.of(false,false,false));
        */
        int nrow = 35; //30
        int ncol = 75; //50
        List<List<Boolean>> matrix = Utils.initializeMatrix(nrow, ncol);
//        Utils.printMatrix(matrix);

        int ngenerations = 1000;
        for (int gen = 0; gen < ngenerations; gen++)
        {
            Thread.sleep(700);
            //to clear console. It works only in console supporting ansi escape sequences.
            //see https://stackoverflow.com/questions/2979383/how-to-clear-the-console-using-java
//            System.out.print("\033[H\033[2J");
//            System.out.flush();

            //workaround to clear console
            final String os = System.getProperty("os.name");
            if (os.contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();
//            Runtime.getRuntime().exec("cls");


//            System.out.println("\033[0;31m"+"===================================");
//            System.out.println("Generation: " + gen);
            Utils.printMatrix(matrix,gen);
            matrix = GameLogic.nextGeneration(matrix);
        }
        System.out.println("*********************************");
        Utils.printMatrix(matrix,ngenerations);
    }

    private static void printTitle() {
        System.out.println(ConsoleColors.BLUE_BOLD +
                "██╗░░░░░██╗███████╗███████╗\n" +
                "██║░░░░░██║██╔════╝██╔════╝\n" +
                "██║░░░░░██║█████╗░░█████╗░░\n" +
                "██║░░░░░██║██╔══╝░░██╔══╝░░\n" +
                "███████╗██║██║░░░░░███████╗\n" +
                "╚══════╝╚═╝╚═╝░░░░░╚══════╝\n\n"
                + ConsoleColors.RESET);
    }

    private static void printInstructions() {
        System.out.println(ConsoleColors.GREEN_BOLD +
                "Welcome to the Conway's Game of Life!\n" +
                "Commands: \n" +
                "- s start the game\n" +
                "- i get some info about Conway's Game of Life\n" +
                "- q quit\n"
               + ConsoleColors.RESET);
    }

    private static void printAbout() {
        System.out.println("Developed in Italy " +ConsoleColors.GREEN+"▊"+
                ConsoleColors.WHITE+"▊"+
                ConsoleColors.RED+"▊"+
                ConsoleColors.RESET+
                " by " + ConsoleColors.PURPLE_UNDERLINED +"alessio176\n"+ConsoleColors.RESET);
    }

    private static char readCommand() {
        System.out.print(ConsoleColors.RED+"> ");
        char command = scanner.nextLine().charAt(0);
        System.out.println(ConsoleColors.RESET);
        return command;
    }

    private static int readInt() {
        System.out.print(ConsoleColors.RED+"> ");
        int value = scanner.nextInt();
        System.out.println(ConsoleColors.RESET);
        return value;
    }
}
