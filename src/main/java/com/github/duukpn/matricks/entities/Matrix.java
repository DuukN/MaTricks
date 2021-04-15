package com.github.duukpn.matricks.entities;

import static com.github.duukpn.matricks.entities.Fraction.ZERO;

public class Matrix {

    private Fraction[][] mat;

    public Matrix(int height, int width, Fraction... constants) {
        if (height == 0 || width == 0)
            throw new IllegalArgumentException(height == 0 ? "Height" : "Width" + " cannot be zero");
        mat = new Fraction[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mat[i][j] = i * height + j < constants.length ? constants[i * height + j] : ZERO;
            }
        }
    }

}
