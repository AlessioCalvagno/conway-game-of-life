package it.conway;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
    private static final PrintStream printStream = new PrintStream(System.out, false);

    /**
     * Initializes a nrow x ncol boolean matrix with random values
     *
     * @param nrow the number of rows in the matrix
     * @param ncol the number of columns in the matrix
     * @return a list of lists containing booleans, where each sublist represents a row
     */
    public static List<List<Boolean>> initializeMatrix(int nrow, int ncol)
    {
        Random random = new Random();
        List<List<Boolean>> matrix = new ArrayList<>();
        for (int i = 0; i < nrow; i++)
        {
            List<Boolean> row = new ArrayList<>();
            for (int j = 0; j < ncol; j++)
            {
                row.add(random.nextBoolean());
            }
            matrix.add(row);
        }
        return matrix;
    }

    public static void printMatrix(List<List<Boolean>> matrix, int gen, int ngenerations)
    {

        printStream.println(ConsoleColors.YELLOW.getCode()+"===================================");
        printStream.printf("Generation: %d/%d%n", gen, ngenerations);

        matrix.forEach(rowElement -> {
            rowElement.forEach(colElement -> {
                printStream.print((colElement? "■" :" ")  + " ");
//                    printStream.print((colElement? "■" :"□")  + " ");

                // \u25A0 unicode char for ■ \u25A1 unicode char for □
            });
            printStream.println();
        });
        printStream.flush();
    }

    public static void clearConsole() throws IOException, InterruptedException {
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
    }
}
