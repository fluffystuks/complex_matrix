import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ComplexMatrix firstMatrix = null;
        ComplexMatrix secondMatrix = null;
        int choice;

        do {
            printMenu();
            choice = scanner.nextInt();

            if (choice == 1) {
                firstMatrix = readMatrix(scanner);
                System.out.println("Первая матрица введена:");
                System.out.println(firstMatrix);
            } else if (choice == 2) {
                secondMatrix = readMatrix(scanner);
                System.out.println("Вторая матрица введена:");
                System.out.println(secondMatrix);
            } else if (choice == 3) {
                addMatrices(firstMatrix, secondMatrix);
            } else if (choice == 4) {
                multiplyMatrices(firstMatrix, secondMatrix);
            } else if (choice == 5) {
                divideMatrices(firstMatrix, secondMatrix);
            } else if (choice == 6) {
                transposeMatrix(scanner, firstMatrix, secondMatrix);
            } else if (choice == 7) {
                calculateDeterminant(scanner, firstMatrix, secondMatrix);
            } else if (choice == 0) {
                System.out.println("Выход из программы");
            } else {
                System.out.println("Нет такого пункта меню");
            }
        } while (choice != 0);

        scanner.close();
    }


    private static void printMenu() {
        System.out.println("Меню:");
        System.out.println("1. Ввести первую матрицу");
        System.out.println("2. Ввести вторую матрицу");
        System.out.println("3. Сложить матрицы");
        System.out.println("4. Перемножить матрицы");
        System.out.println("5. Разделить матрицы");
        System.out.println("6. Транспонировать матрицу");
        System.out.println("7. Вычислить определитель");
        System.out.println("0. Выход");
        System.out.print("Выберите пункт: ");
    }


    public static ComplexMatrix readMatrix(Scanner scanner) {
        System.out.print("Введите количество строк: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов: ");
        int cols = scanner.nextInt();

        ComplexMatrix matrix = new ComplexMatrix(rows, cols);

        System.out.println("Введите элементы матрицы: действительная и мнимая часть через пробел");
        for (int i = 0; i < rows; i++) {
            readMatrixRow(scanner, matrix, i);
        }

        return matrix;
    }


    private static void readMatrixRow(Scanner scanner, ComplexMatrix matrix, int row) {
        for (int j = 0; j < matrix.getCols(); j++) {
            System.out.print("Элемент [" + row + "][" + j + "]: ");
            double re = scanner.nextDouble();
            double im = scanner.nextDouble();
            matrix.set(row, j, new ComplexNumber(re, im));
        }
    }


    private static void addMatrices(ComplexMatrix firstMatrix, ComplexMatrix secondMatrix) {
        if (!checkTwoMatrices(firstMatrix, secondMatrix)) {
            return;
        }

        try {
            ComplexMatrix result = MatrixOperations.add(firstMatrix, secondMatrix);
            System.out.println("Результат сложения:");
            System.out.println(result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }


    private static void multiplyMatrices(ComplexMatrix firstMatrix, ComplexMatrix secondMatrix) {
        if (!checkTwoMatrices(firstMatrix, secondMatrix)) {
            return;
        }

        try {
            ComplexMatrix result = MatrixOperations.multiply(firstMatrix, secondMatrix);
            System.out.println("Результат умножения:");
            System.out.println(result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

 
    private static void divideMatrices(ComplexMatrix firstMatrix, ComplexMatrix secondMatrix) {
        if (!checkTwoMatrices(firstMatrix, secondMatrix)) {
            return;
        }

        try {
            ComplexMatrix result = MatrixOperations.divide(firstMatrix, secondMatrix);
            System.out.println("Результат деления:");
            System.out.println(result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }


    private static void transposeMatrix(Scanner scanner, ComplexMatrix firstMatrix, ComplexMatrix secondMatrix) {
        ComplexMatrix matrix = chooseMatrix(scanner, firstMatrix, secondMatrix);

        if (matrix == null) {
            System.out.println("Сначала введите матрицу");
            return;
        }

        try {
            ComplexMatrix result = MatrixOperations.transpose(matrix);
            System.out.println("Результат транспонирования:");
            System.out.println(result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void calculateDeterminant(Scanner scanner, ComplexMatrix firstMatrix, ComplexMatrix secondMatrix) {
        ComplexMatrix matrix = chooseMatrix(scanner, firstMatrix, secondMatrix);

        if (matrix == null) {
            System.out.println("Сначала введите матрицу");
            return;
        }

        try {
            ComplexNumber result = MatrixDeterminant.determinant(matrix);
            System.out.println("Определитель: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }


    private static ComplexMatrix chooseMatrix(Scanner scanner, ComplexMatrix firstMatrix, ComplexMatrix secondMatrix) {
        System.out.print("Какую матрицу выбрать? 1 - первую, 2 - вторую: ");
        int number = scanner.nextInt();

        if (number == 1) {
            return firstMatrix;
        }

        if (number == 2) {
            return secondMatrix;
        }

        System.out.println("Нет такой матрицы");
        return null;
    }

    private static boolean checkTwoMatrices(ComplexMatrix firstMatrix, ComplexMatrix secondMatrix) {
        if (firstMatrix == null || secondMatrix == null) {
            System.out.println("Сначала введите матрицу");
            return false;
        }

        return true;
    }
}
