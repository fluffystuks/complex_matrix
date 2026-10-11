public class ComplexMatrix {
    ComplexNumber[][] data;
    int rows;
    int cols;

    public ComplexMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new ComplexNumber[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = new ComplexNumber(0, 0);
            }
        }
    }

    public ComplexMatrix(ComplexNumber[][] data) {
        this.data = data;
        rows = data.length;
        cols = data[0].length;
    }

    public ComplexNumber get(int i, int j) {
        return data[i][j];
    }

    public void set(int i, int j, ComplexNumber value) {
        data[i][j] = value;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public String toString() {
        String result = "";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result += String.format("%18s", data[i][j].toString());
            }
            result += "\n";
        }

        return result;
    }
}