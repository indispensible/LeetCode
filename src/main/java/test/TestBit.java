package test;

/**
 * @author lvgang
 * @date 2020/12/24 15:32
 */
public class TestBit {
    public static void lowbit(int x) {
        System.out.println(x & (-x));
    }

    public static void main(String[] args) {
        TestBit testBit = new TestBit();
        System.out.println((10 ^ 10) == 0);
        System.out.println(8 ^ 8);
        System.out.println(1234234 ^ 1234234);
//        lowbit(-8);
//        lowbit(1);
//        lowbit(2);
//        lowbit(3);
//        lowbit(4);
//        lowbit(5);
//        lowbit(6);
//        lowbit(7);
//        lowbit(8);
//        lowbit(10);
//        lowbit(15);
//        lowbit(100);
    }
}
