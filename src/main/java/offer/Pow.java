package offer;

/**
 * @author lvgang
 * @date 2020/12/7 16:00
 */
public class Pow {
    public double myPow(double x, int n) {
        double result = 0.0000;
        if (x == (double) 1) {
            return 1.00000;
        }
        if (x != (double) 0) {
//            result = powPositive(x, n);
            result = newPowCalculator(x, n);
            if (n < 0){
                result = 1.0 / result;
            }
        }
        return result;
    }

    public double powPositive(double x, int n) {
        double result = 1;
        if (n > 0) {
            n = -1 * n;
        }
        for (int i = 0; i > n; i--) {
            result *= x;
        }
        return result;
    }

    public double newPowCalculator(double x, int n) {
        double result = x;
        if (n < 0) {
            n = -n;
        }
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return result;
        }
        result = newPowCalculator(x, n >> 1);
        result *= result;
        if ((n & 1) == 1) {
            result *= x;
        }
        return result;
    }

    public static void main(String[] args) {
        Pow pow = new Pow();
        System.out.println(pow.myPow(2.0, 10));
        System.out.println(pow.myPow(2.0, -2));
        System.out.println(pow.myPow(2.000, -2147483648));
        System.out.println(pow.myPow(34.00515, -3));
    }

}
