package test;


import java.util.PriorityQueue;

/**
 * @author lvgang
 * @date 2020/12/10 19:19
 */
public class Test3 {
    public int[] test1() {
        int[] array = new int[10];
        test1(array);
        return array;
    }

    public void test1(int[] array) {
        array[2] = 100;
    }

    public static void main(String[] args) {
//        System.out.println(-1 >> 1);
//        System.out.println(-1 >>> 1);
        Test3 test3 = new Test3();
        test3.test1();
    }
}
