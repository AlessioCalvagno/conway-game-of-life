package it.conway;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException, IOException {
        System.out.println("Hello World!");
        /*
        List<List<Boolean>> matrix = new ArrayList<>();
        matrix.add(List.of(true,true,true));
        matrix.add(List.of(false,false,false));
         */
        int nrow = 70; //30
        int ncol = 150; //50
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
}
