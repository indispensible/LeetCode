package offer;

import java.math.BigInteger;

/**
 * @author lvgang
 * @date 2020/12/7 9:27
 */
public class Rope {
    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n ; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    public int cuttingRope2(int n) {
        BigInteger[] dp = new BigInteger[n + 1];
        dp[1] = BigInteger.valueOf(1L);
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (dp[i - j] == null) {
                    dp[i - j] = BigInteger.valueOf(0L);
                }
                if (dp[i] == null) {
                    dp[i] = BigInteger.valueOf(0L);
                }
                dp[i] = dp[i].max(dp[i - j].multiply(BigInteger.valueOf(j)).max(BigInteger.valueOf(j * (i - j))));
            }
        }
        return dp[n].mod(BigInteger.valueOf(1000000007L)).intValue();
    }

    public static void main(String[] args) {
        Rope rope = new Rope();
        System.out.println(rope.cuttingRope2(2));
        System.out.println(rope.cuttingRope2(10));
        System.out.println(rope.cuttingRope2(120));
    }
}
