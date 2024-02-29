package it.conway;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Matrix {
    //todo: do i really need a matrix class? Could I use a simple List<List<Integer>> object?
    //If i need to implement specific methods related to matrix I'll come back here

    private List<Integer> elements;

    public Matrix(Integer nrow, Integer ncol) {
        this.elements = new ArrayList<>(nrow*ncol);
    }
}
