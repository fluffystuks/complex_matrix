public class Main {
    public static void main(String[] args) {
        ComplexNumber c1 = new ComplexNumber(3.0, 4.0);
        ComplexNumber c2 = new ComplexNumber(1.0, -2.0);
        ComplexNumber sum = c1.add(c2);

        System.out.println("Первое число: " + c1);
        System.out.println("Второе число: " + c2);
        System.out.println("Сумма чисел: " + sum);
        System.out.println("--");

        ComplexMatrix matrix = new ComplexMatrix(2, 2);
        matrix.set(0, 0, new ComplexNumber(1, 0));
        matrix.set(0, 1, new ComplexNumber(0, 1));
        matrix.set(1, 0, new ComplexNumber(-1, 0));
        matrix.set(1, 1, new ComplexNumber(0, -1));

        System.out.println("Созданная матрица:");
        System.out.println(matrix);
    }
}