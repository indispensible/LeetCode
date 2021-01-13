package test;

/**
 * @author lvgang
 * @date 2020/12/16 14:24
 */
public class TestString {

    String b = "a";

    public static void main(String[] args) {
        String data = "abcde";
        System.out.println(data);
//        左闭右开
        System.out.println(data.substring(1, data.length() - 1));

        StringBuilder a = new StringBuilder("abcde");
        System.out.println(a);
        System.out.println(a.delete(1, 3));
    }
}
