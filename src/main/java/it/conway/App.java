package it.conway;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        System.out.println("Hello World!");
        /*
        List<List<Boolean>> matrix = new ArrayList<>();
        matrix.add(List.of(true,true,true));
        matrix.add(List.of(false,false,false));
         */
        int nrow = 5;
        int ncol = 7;
        List<List<Boolean>> matrix = Utils.initializeMatrix(nrow, ncol);
        Utils.printMatrix(matrix);

        int ngenerations = 1000;
        for (int gen = 0; gen < ngenerations; gen++)
        {
            Thread.sleep(1000);
            System.out.println("===================================");
            System.out.println("Generation: " + gen);
            Utils.printMatrix(matrix);
            matrix = GameLogic.nextGeneration(matrix);
        }
        System.out.println("*********************************");
        Utils.printMatrix(matrix);
    }
}
