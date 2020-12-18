package test;

/**
 * @author lvgang
 * @date 2020/12/16 14:24
 */
public class TestString {
    public static void main(String[] args) {
        String data = "abcde";
        System.out.println(data);
//        左闭右开
        System.out.println(data.substring(1, data.length() - 1));
    }
}
