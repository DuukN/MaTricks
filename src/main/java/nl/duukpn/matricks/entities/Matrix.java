package nl.duukpn.matricks.entities;

import java.util.Scanner;

public class Matrix {

    private Fraction[][] elements;

    public Matrix(int height, int width, Fraction... constants) {
        if (height == 0 || width == 0)
            throw new IllegalArgumentException(height == 0 ? "Height" : "Width" + " cannot be zero");
        elements = new Fraction[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                elements[i][j] = i * height + j < constants.length ? constants[i * height + j] : Fraction.ZERO;
            }
        }
    }
    
    public static Matrix read(int height, int width, Scanner sc) throws MatrixFormatException {
        Matrix matrix = new Matrix(height, width);
        for (int i = 0; i < height; i++) {
            String[] line = sc.nextLine().split("\\s+");
            if (line.length != width) throw new MatrixFormatException("Matrix has illegal format");
            for (int j = 0; j < width; j++) {
                try {
                    matrix.elements[i][j] = Fraction.parseFraction(line[j]);
                } catch (Fraction.FractionFormatException e) {
                    throw new MatrixFormatException(e.getMessage());
                }
            }
        }
        return matrix;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[0].length; j++) {
                sb.append(elements[i][j].toString()).append(" ");
            }
            if (i < elements.length - 1) sb.append("\n");
        }
        return sb.toString();
    }

    public static class MatrixFormatException extends Exception {
        private MatrixFormatException(String message) {
            super(message);
        }
    }

}
