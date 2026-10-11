public class MatrixDeterminant {
    private static final double EPS = 1e-10;

    private MatrixDeterminant() {
    }


    public static ComplexNumber determinant(ComplexMatrix matrix) {
        checkSquare(matrix);

        if (matrix.getRows() == 1) {
            return matrix.get(0, 0);
        }

        if (matrix.getRows() == 2) {
            return determinant2x2(matrix);
        }

        ComplexNumber result = new ComplexNumber(0, 0);

        for (int j = 0; j < matrix.getCols(); j++) {
            ComplexNumber element = matrix.get(0, j);
            ComplexNumber minorDet = determinant(getMinor(matrix, 0, j));

            if (j % 2 == 0) {
                result = result.add(element.multiply(minorDet));
            } else {
                result = result.subtract(element.multiply(minorDet));
            }
        }

        return result;
    }


    public static ComplexMatrix getMinor(ComplexMatrix matrix, int excludeRow, int excludeCol) {
        ComplexMatrix minor = new ComplexMatrix(matrix.getRows() - 1, matrix.getCols() - 1);
        int minorRow = 0;

        for (int i = 0; i < matrix.getRows(); i++) {
            if (i != excludeRow) {
                copyMinorRow(matrix, minor, i, minorRow, excludeCol);
                minorRow++;
            }
        }

        return minor;
    }


    public static ComplexMatrix inverse(ComplexMatrix matrix) {
        checkSquare(matrix);

        ComplexNumber det = determinant(matrix);
        if (Math.sqrt(det.re * det.re + det.im * det.im) < EPS) {
            throw new IllegalArgumentException("Матрица вырождена, обратной не существует");
        }

        ComplexMatrix cofactors = getCofactorMatrix(matrix);
        ComplexMatrix adjugate = MatrixOperations.transpose(cofactors);
        ComplexMatrix result = new ComplexMatrix(matrix.getRows(), matrix.getCols());

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                result.set(i, j, adjugate.get(i, j).divide(det));
            }
        }

        return result;
    }


    private static void checkSquare(ComplexMatrix matrix) {
        if (matrix.getRows() != matrix.getCols()) {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }
    }


    private static ComplexNumber determinant2x2(ComplexMatrix matrix) {
        ComplexNumber first = matrix.get(0, 0).multiply(matrix.get(1, 1));
        ComplexNumber second = matrix.get(0, 1).multiply(matrix.get(1, 0));
        return first.subtract(second);
    }


    private static void copyMinorRow(ComplexMatrix matrix, ComplexMatrix minor, int row, int minorRow, int excludeCol) {
        int minorCol = 0;

        for (int j = 0; j < matrix.getCols(); j++) {
            if (j != excludeCol) {
                minor.set(minorRow, minorCol, matrix.get(row, j));
                minorCol++;
            }
        }
    }


    private static ComplexMatrix getCofactorMatrix(ComplexMatrix matrix) {
        ComplexMatrix result = new ComplexMatrix(matrix.getRows(), matrix.getCols());

        if (matrix.getRows() == 1) {
            result.set(0, 0, new ComplexNumber(1, 0));
            return result;
        }

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                result.set(i, j, getCofactor(matrix, i, j));
            }
        }

        return result;
    }


    private static ComplexNumber getCofactor(ComplexMatrix matrix, int row, int col) {
        ComplexNumber value = determinant(getMinor(matrix, row, col));

        if ((row + col) % 2 != 0) {
            return new ComplexNumber(0, 0).subtract(value);
        }

        return value;
    }
}
