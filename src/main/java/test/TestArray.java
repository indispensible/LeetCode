package test;

import java.util.Arrays;

/**
 * @author lvgang
 * @date 2020/12/4 14:29
 */
public class TestArray {
    public static void main(String[] args) {
        int[][] a = new int[10][];
//        int[] b = a;
//        int[] c = a.clone();
//        a[2] = 10;
//        System.out.println(b.toString());
        int[][] a1 = {{1}, {2}, {5}};
        int[][] c = Arrays.copyOfRange(a1, 0, 1);
        System.out.println(a1);
        int[][] b = {{3},{4}};
        System.out.println(b);
        a1 = b;
        System.out.println(a1);
    }
}
