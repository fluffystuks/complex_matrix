public class Main {
    public static void main(String[] args) {
        System.out.println("ТЕСТ КОМПЛЕКСНЫХ ЧИСЕЛ");
        ComplexNumber c1 = new ComplexNumber(2.0, 3.0);
        ComplexNumber c2 = new ComplexNumber(1.0, -1.0);
        
        System.out.println("Число 1: " + c1);
        System.out.println("Число 2: " + c2);
        System.out.println("Сумма: " + c1.add(c2));
        System.out.println("Произведение: " + c1.multiply(c2));
        
        System.out.println("\nТЕСТ МАТРИЦ");

        ComplexMatrix m1 = new ComplexMatrix(2, 2);
        m1.set(0, 0, new ComplexNumber(1, 0));
        m1.set(0, 1, new ComplexNumber(2, 1));
        m1.set(1, 0, new ComplexNumber(3, -1));
        m1.set(1, 1, new ComplexNumber(4, 0));

        System.out.println("Матрица M1:");
        System.out.println(m1);

        ComplexMatrix mTransposed = MatrixOperations.transpose(m1);
        System.out.println("Транспонированная M1:");
        System.out.println(mTransposed);

        // Определитель
        ComplexNumber det = MatrixDeterminant.determinant(m1);
        System.out.println("Определитель матрицы M1: " + det);
    }
}