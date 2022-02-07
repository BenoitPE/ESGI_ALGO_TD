package Models.Graph;

public class Matrix {
    private int[][] values;

    public Matrix(int size) {
        this.values = new int[size][size];
    }

    public static Matrix transposeSquareMatrix(Matrix matrix)
    {
        Matrix transposedMatrix = new Matrix(matrix.values.length);
        for (int i=0; i < matrix.values.length; i++) {
            for (int j=0; j < matrix.values.length; j++) {
                transposedMatrix.values[i][j] = matrix.values[j][i];
            }
        }
        return transposedMatrix;
    }

    public static Matrix transitiveMatrix(Matrix matrix) {
        Matrix transposeSquareMatrix = transposeSquareMatrix(matrix);
        return AddMatrix(matrix, transposeSquareMatrix);
    }

    public static Matrix AddMatrix(Matrix m1, Matrix m2) {
        Matrix matrix = new Matrix(m1.values.length);
        for (int i=0; i< m1.values.length; i++) {
            for (int j=0; j< m1.values.length; j++) {
                matrix.values[i][j] = (m1.values[i][j] + m2.values[i][j]) > 0 ? 1 : 0;
            }
        }
        return matrix;
    }

    //region Getters and Setters
    public int[][] getValues() {
        return values;
    }

    public int getValue(int i, int j) { return values[i][j];}

    public void setValues(int[][] values) {
        this.values = values;
    }

    public void setValue(int i, int j, int value) {
        this.values[i][j] = value;
    }
    //endregion

    //region Print
    public static void print(Matrix matrix) {
        for (int i=0; i < matrix.values.length; i++) {
            for (int j=0; j < matrix.values[i].length; j++) {
                System.out.print(matrix.values[i][j] + " ");
            }
            System.out.println();
        }
    }
    //endregion
}
