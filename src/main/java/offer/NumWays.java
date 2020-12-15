package offer;

/**
 * @author lvgang
 * @date 2020/12/2 10:53
 */
public class NumWays {
    public int numWays(int n) {
        int a = 1, b = 1, sum = 1;
        for (int i = 1; i < n; i++) {
            sum = (b + a) % 1000000007;
            a = b;
            b = sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        NumWays numWays = new NumWays();
        int[] tests = {2, 7, 0};
        for (int i = 0; i < tests.length; i++) {
            System.out.println(numWays.numWays(tests[i]));
        }
    }
}
