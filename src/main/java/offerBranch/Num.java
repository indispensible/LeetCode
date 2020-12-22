package offerBranch;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author lvgang
 * @date 2020/12/18 13:56
 */
public class Num {

    private Queue<Integer> bigQueue = new PriorityQueue<>((x, y) -> (y - x));
    private Queue<Integer> smallQueue = new PriorityQueue<>();

    public void addNum(int num) {
        if (bigQueue.size() == 0) {
            bigQueue.add(num);
            return;
        }
        if (bigQueue.size() == smallQueue.size()) {
            if (num > smallQueue.peek()) {
                smallQueue.add(num);
                num = smallQueue.poll();
            }
            bigQueue.add(num);
        } else {
            if (num < bigQueue.peek()) {
                bigQueue.add(num);
                num = bigQueue.poll();
            }
            smallQueue.add(num);
        }
    }

    public double findMedian() {
        if (bigQueue.size() == smallQueue.size()) {
            return (bigQueue.peek() + smallQueue.peek()) / 2.0;
        }
        return bigQueue.peek();
    }
}
