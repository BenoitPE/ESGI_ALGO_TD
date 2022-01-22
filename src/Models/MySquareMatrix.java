package Models;

public class MySquareMatrix {
    private int[][] values;

    public MySquareMatrix(int[][] values) {
        this.values = values;
    }

    public MySquareMatrix(int size) {
        this.values = new int[size][size];
    }

    public static MySquareMatrix transposeSquareMatrix(MySquareMatrix matrix)
    {
        MySquareMatrix transposedMatrix = new MySquareMatrix(matrix.values.length);
        for (int i=0; i < matrix.values.length; i++) {
            for (int j=0; j < matrix.values.length; j++) {
                transposedMatrix.values[i][j] = matrix.values[j][i];
            }
        }
        return transposedMatrix;
    }


    public static MySquareMatrix transitiveMatrixFromAdjancyMatrix(MySquareMatrix matrix) {
        MySquareMatrix transposeSquareMatrix = transposeSquareMatrix(matrix);
        return AddMatrix(matrix, transposeSquareMatrix);
    }

    public static MySquareMatrix AddMatrix(MySquareMatrix m1, MySquareMatrix m2) {
        MySquareMatrix matrix = new MySquareMatrix(m1.values.length);
        for (int i=0; i< m1.values.length; i++) {
            for (int j=0; j< m1.values.length; j++) {
                matrix.values[i][j] = (m1.values[i][j] + m2.values[i][j]) > 0 ? 1 : 0;
            }
        }
        return matrix;
    }

    public static void print(MySquareMatrix matrix) {
        for (int i=0; i < matrix.values.length; i++) {
            for (int j=0; j < matrix.values[i].length; j++) {
                System.out.print(matrix.values[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getValues() {
        return values;
    }

    public void setValues(int[][] values) {
        this.values = values;
    }

    public void setValue(int i, int j, int value) {
        this.values[i][j] = value;
    }
}
