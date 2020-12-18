package offer;

import java.util.*;

/**
 * @author lvgang
 * @date 2020/12/17 12:59
 */
public class Num {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>(16);
        int majority = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (!hashMap.containsKey(nums[i])) {
                hashMap.put(nums[i], 0);
            }
            hashMap.put(nums[i], hashMap.get(nums[i]) + 1);
            if (hashMap.get(nums[i]) > (nums.length >> 1)) {
                majority = nums[i];
                break;
            }
        }
        return majority;
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr.length == 0 || k == 0) {
            return new int[0];
        }
        Arrays.sort(arr);
        return Arrays.copyOf(arr, k);
    }

    List<Integer> list = new ArrayList<Integer>();

    public void addNum(int num) {
        list.add(num);
    }

    public double findMedian() {
        if (list.size() == 0) {
            return Double.parseDouble(null);
        }
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        int middle = list.size() >> 1;
        if ((list.size() & 1) == 1) {
            return list.get(middle);
        } else {
            return (list.get(middle - 1) + list.get(middle)) / 2.0;
        }
    }

}
