public class ComplexNumber {
    double re; /// re - действительная часть
    double im; /// im - мнимая часть

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }
    /// дальше - операции с числами
    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(re + other.re, im + other.im);
    }

    public ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(re - other.re, im - other.im);
    }

    public ComplexNumber multiply(ComplexNumber other) {
        double newRe = re * other.re - im * other.im;
        double newIm = re * other.im + im * other.re;
        return new ComplexNumber(newRe, newIm);
    }

    public ComplexNumber divide(ComplexNumber other) {
        double denominator = other.re * other.re + other.im * other.im;
        double newRe = (re * other.re + im * other.im) / denominator;
        double newIm = (im * other.re - re * other.im) / denominator;
        return new ComplexNumber(newRe, newIm);
    }

    public String toString() {
        if (im < 0) {
            return re + " - " + Math.abs(im) + "i";
        }
        return re + " + " + im + "i";
    }
}
