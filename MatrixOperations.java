public class MatrixOperations {
    private MatrixOperations() {
    }


    public static ComplexMatrix add(ComplexMatrix a, ComplexMatrix b) {
        if (a.getRows() != b.getRows() || a.getCols() != b.getCols()) {
            throw new IllegalArgumentException("Размеры матриц не совпадают");
        }

        ComplexMatrix result = new ComplexMatrix(a.getRows(), a.getCols());

        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < a.getCols(); j++) {
                result.set(i, j, a.get(i, j).add(b.get(i, j)));
            }
        }

        return result;
    }


    public static ComplexMatrix multiply(ComplexMatrix a, ComplexMatrix b) {
        if (a.getCols() != b.getRows()) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы не равно количеству строк второй");
        }

        ComplexMatrix result = new ComplexMatrix(a.getRows(), b.getCols());

        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < b.getCols(); j++) {
                result.set(i, j, getMultiplyCell(a, b, i, j));
            }
        }

        return result;
    }


    private static ComplexNumber getMultiplyCell(ComplexMatrix a, ComplexMatrix b, int row, int col) {
        ComplexNumber sum = new ComplexNumber(0, 0);

        for (int k = 0; k < a.getCols(); k++) {
            sum = sum.add(a.get(row, k).multiply(b.get(k, col)));
        }

        return sum;
    }


    public static ComplexMatrix transpose(ComplexMatrix matrix) {
        ComplexMatrix result = new ComplexMatrix(matrix.getCols(), matrix.getRows());

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                result.set(j, i, matrix.get(i, j));
            }
        }

        return result;
    }

    public static ComplexMatrix divide(ComplexMatrix a, ComplexMatrix b) {
        ComplexMatrix inverse = MatrixDeterminant.inverse(b);
        return multiply(a, inverse);
    }
}
