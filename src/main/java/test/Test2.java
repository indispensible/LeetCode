package test;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author lvgang
 * @date 2020/12/9 10:14
 */
public class Test2 {
    public static void main(String[] args) {
        Queue<Integer> smallQueue = new PriorityQueue<>((x, y) -> (y - x));
        smallQueue.add(5);
        smallQueue.add(2);
        smallQueue.add(3);
        smallQueue.add(4);
        System.out.println(smallQueue);
    }
}
