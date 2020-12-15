package offer;

/**
 * @author lvgang
 * @date 2020/12/3 16:19
 */
public class MinArray {
    public int minArray(int[] numbers) {
        int head = 0, end = numbers.length - 1, min = numbers[head];
        if (numbers[head] >= numbers[end]) {
            while (head < end && numbers[head] == numbers[head + 1]) {
                head++;
            }
            while (head < end && numbers[end] == numbers[end - 1]) {
                end--;
            }
            while (head < end - 1) {
                int middle = (head + end) / 2;
                if (numbers[middle] > numbers[0]) {
                    head = middle + 1;
                } else if (numbers[middle] <= numbers[0]) {
                    end = middle;
                }
            }
            min = numbers[head];
            if (numbers[head] > numbers[end]) {
                min = numbers[end];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[] input = {4,5,6,7,0,1,2};
    }
}
