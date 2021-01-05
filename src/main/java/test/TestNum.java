package test;

/**
 * @author lvgang
 * @date 2020/12/29 9:48
 */
public class TestNum {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 1;
        Integer c = 128;
        Integer d = 128;
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(c == d);
        System.out.println(c.equals(d));
        System.out.println(Integer.parseInt("0000000000012345678"));
    }
}
