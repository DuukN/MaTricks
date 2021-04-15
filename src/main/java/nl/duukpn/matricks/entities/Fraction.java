package nl.duukpn.matricks.entities;

public class Fraction extends Number implements Comparable<Fraction> {

    private int numerator;
    private int denominator;

    public static final Fraction ZERO = new Fraction(0, 1);
    public static final Fraction ONE = new Fraction(1, 1);

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) throw new IllegalArgumentException("Denominator cannot be zero");
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public Fraction(int number) {
        numerator = number;
        denominator = 1;
    }

    public static Fraction parseFraction(String string) {
        try {
            return new Fraction(Integer.parseInt(string));
        } catch (NumberFormatException e) {
            String[] numbers = string.split("/");
            try {
                return new Fraction(Integer.parseInt(numbers[0], Integer.parseInt(numbers[1])));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException err) {
                return null;
            }
        }
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public Fraction multiply(Fraction frac) {
        return new Fraction(numerator * frac.numerator,
                denominator * frac.denominator);
    }

    public Fraction multiply(int a) {
        return new Fraction(numerator * a, denominator);
    }

    public Fraction divide(Fraction frac) {
        if (frac.denominator == 0) throw new IllegalArgumentException("Cannot divide by zero");
        return multiply(new Fraction(frac.denominator, frac.numerator));
    }

    public Fraction divide(int a) {
        return new Fraction(numerator, denominator * a);
    }

    public Fraction add(Fraction frac) {
        int d = lcm(denominator, frac.denominator);
        int n = frac.numerator * (d / frac.denominator)
                + numerator * (d / denominator);
        return new Fraction(n, d);
    }

    public Fraction add(int a) {
        return new Fraction(numerator + (a * denominator), denominator);
    }

    public Fraction subtract(Fraction frac) {
        return add(frac.multiply(-1));
    }

    public Fraction subtract(int a) {
        return add(-a);
    }

    private void simplify() {
        if (numerator == 0) {
            denominator = 1;
        } else {
            int gcd = gcd(numerator, denominator);
            numerator /= gcd;
            denominator /= gcd;
            if (denominator < 0) {
                denominator *= -1;
                numerator *= -1;
            }
        }
    }

    @Override
    public String toString() {
        //TODO implement
        return null;
    }

    @Override
    public int compareTo(Fraction frac) {
        //TODO implement
        return 0;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fraction)) return false;
        Fraction fraction = (Fraction) o;
        return numerator == fraction.numerator &&
                denominator == fraction.denominator;
    }

    @Override
    public double doubleValue() {
        return (double) numerator/(double) denominator;
    }

    @Override
    public int intValue() {
        return (int) doubleValue();
    }

    @Override
    public long longValue() {
        return (long) doubleValue();
    }

    @Override
    public float floatValue() {
        return (float) doubleValue();
    }

}
