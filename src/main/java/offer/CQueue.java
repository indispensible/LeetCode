package offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lvgang
 * @date 2020/12/1 12:28
 */
public class CQueue {

    Queue<Integer> queue = new LinkedList<Integer>();

    public CQueue() {

    }

    public void appendTail(int value) {
        queue.add(value);
    }

    public int deleteHead() {
        int returnValue = -1;
        if (queue.peek() != null) {
            returnValue = queue.poll();
        }
        return returnValue;
    }
}
