package test;

/**
 * @author lvgang
 * @date 2020/12/4 14:29
 */
public class TestArray {
    public static void main(String[] args) {
        int[] a = new int[10];
        int[] b = a;
        int[] c = a.clone();
        a[2] = 10;
        System.out.println(b.toString());
    }
}
