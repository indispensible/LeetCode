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

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays2Plus1(nums2, nums1);
        }
        return findMedianSortedArrays2Plus1(nums1, nums2);
    }

    public double findMedianSortedArrays2Plus1(int[] nums1, int[] nums2) {
        int i = 0, j = 0, len1 = nums1.length, len2 = nums2.length, len = len1 + len2, index = len >> 1;
        int sub = Math.min(index >> 1, len1 - 1);
        if (sub == 0) {
            sub = 1;
        }
        double median = 0;
        while (index != 0 && i < len1 && j < len2) {
            if (nums1[i + sub - 1] <= nums2[j + sub - 1]) {
                i += sub;
            } else {
                j += sub;
            }
            index -= sub;
            sub = Math.min(index >> 1, Math.min(len1 - i -1, len2 - j - 1));
            if (sub == 0) {
                sub = 1;
            }
        }
        if (index != 0) {
            median = nums2[j + index];
            if ((len & 1) == 0) {
                median = (nums2[j + index - 1] + nums2[j + index]) / 2.0;
            }
        } else {
            if (i == len1) {
                median = nums2[j + index];
                if ((len & 1) == 0) {
                    if (j == 0 || nums1[i - 1] > nums2[j + index - 1]) {
                        median += nums1[i - 1];
                    } else {
                        median += nums2[j + index - 1];
                    }
                    median /= 2.0;
                }
            } else {
                if (j == len2) {
                    median = nums1[i];
                } else {
                    median = Math.min(nums1[i], nums2[j]);
                }
                if ((len & 1) == 0) {
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        median += Math.max(nums1[i - 1], nums2[j - 1]);
                    } else if (i == 0) {
                        median += nums2[j - 1];
                    } else {
                        median += nums1[i - 1];
                    }
                    median = median / 2.0;
                }
            }
        }
        return median;
    }

    public static void main(String[] args) {
        int[] num1 = new int[]{2};
        int[] num2 = new int[]{1,3,4,5,6,7,8,9,10};
        Num solution = new Num();
        System.out.println(solution.findMedianSortedArrays(num1, num2));
    }
}
