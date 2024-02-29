package it.conway;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    /**
     * Initializes a nrow x ncol boolean matrix with random values
     *
     * @param nrow the number of rows in the matrix
     * @param ncol the number of columns in the matrix
     * @return a list of lists containing booleans, where each sublist represents a row
     */
    public static List<List<Boolean>> initializeMatrix(int nrow, int ncol)
    {
        Random random = new Random(1);
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

    /**
     * Inserts a frame of false values around matrix.
     * The extended matrix has size nrow + 1 x ncol +1.
     * This frame is used to compute live neighbors count without IndexOutOfBounds exceptions.
     *
     * @param matrix
     */
    public static void extendFrame(List<List<Boolean>> matrix) {
        //TODO: really needed? Could i handle borders in other way?
    }



    public static void printMatrix(List<List<Boolean>> matrix)
    {
        matrix.forEach(rowElement -> {
            rowElement.forEach(colElement -> {
                System.out.print((colElement? '■' :'□')  + " "); // \u25A0 unicode char for ■ \u25A1 unicode char for □
            });
            System.out.println();
        });
    }
}
