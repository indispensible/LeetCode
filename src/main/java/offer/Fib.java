package offer;

import java.math.BigInteger;

/**
 * @author lvgang
 * @date 2020/12/2 10:33
 */
public class Fib {
    public int fib(int n) {
        int a = 0, b = 1, sum;
        for(int i = 0; i < n; i++){
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }

    public static void main(String[] args) {
        Fib fib = new Fib();
        fib.fib(2);
    }
}
