package it.conway;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GameLogic {

    public static Boolean computeNewCellState(Boolean cell, Integer liveNeighbors)
    {

        if(liveNeighbors < 0 || liveNeighbors > 8)
            throw new IllegalStateException("Invalid liveNeighbors count: " + liveNeighbors);

        if(cell)
        {
            //cell alive
            switch (liveNeighbors)
            {
                case 0:
                case 1:
                    return false; //rule 1
                /* these two cases are redundant (replaced by "return cell" at the end)
                case 2:
                case 3:
                    return true; //rule 2
                 */
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    return false; //rule 3
            }
        }
        else
        {
            //cell dead
            if(liveNeighbors == 3)
                return true; //rule 4
        }
        return cell; //if I'm here it means that cell state doesn't change in next generation
    }

    public static Integer countLiveNeighbors(List<List<Boolean>> matrix, int row, int col)
    {
        //todo: handle elements in the borders
        Boolean cell = matrix.get(row).get(col);
        List<Boolean> neighbors = new ArrayList<>(8);
        AtomicInteger liveNeighbors = new AtomicInteger(0);
        for(int i = -1; i<=1; i++)
        {
            for (int j=-1; j<=1; j++)
            {
                if(!(i == 0 && j == 0)) //i!=0 || j!= 0)
                {
                    //if cell is on the border and neighbor would go outside matrix,
                    // consider that "external" value as a dead neighbor
                    if(row+i < 0 || row+i >= matrix.size() || col+j < 0 || col+j >= matrix.get(row+i).size())
                    {
                        neighbors.add(false);
                    }
                    else
                        neighbors.add(matrix.get(row+i).get(col+j));
                }
            }
        }
        neighbors.forEach(n -> {
            if(n)
                liveNeighbors.getAndSet(liveNeighbors.get() + 1);
        });
        return liveNeighbors.get();
    }

    public static List<List<Boolean>> nextGeneration(List<List<Boolean>> matrix)
    {
        List<List<Boolean>> nextGenerationMatrix = new ArrayList<>();
        for(int rowIndex = 0; rowIndex < matrix.size(); rowIndex++) {
            List<Boolean> row = new ArrayList<>();
            for(int colIndex = 0; colIndex < matrix.get(rowIndex).size(); colIndex++) {
                Boolean newCell = computeNewCellState(matrix.get(rowIndex).get(colIndex), countLiveNeighbors(matrix, rowIndex, colIndex));
                row.add(newCell);
            }
            nextGenerationMatrix.add(row);
        }
        return nextGenerationMatrix;
    }
}
