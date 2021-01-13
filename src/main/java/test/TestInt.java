package test;

/**
 * @author lvgang
 * @date 2021/1/7 10:44
 */
public class TestInt {
    public static void main(String[] args) {
        int a = 1000;
        int b = 1000;
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));
        a = 2;
        System.out.println(System.identityHashCode(a));
    }
}
