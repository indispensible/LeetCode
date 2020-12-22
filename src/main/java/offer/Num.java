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

    //  动态规划
    public int countDigitOne(int n) {
        int[] record = new int[10], dp = new int[11];
        char[] chars = String.valueOf(n).toCharArray();
        int j;
        for (int i = 1; i < chars.length; i++) {
            record[i] = (int) (10 * record[i - 1] + Math.pow(10, i - 1));
        }
        for (int i = chars.length - 1; i >= 0; i--) {
            j = chars[i] - '0';
            if (j == 0) {
                dp[i] = dp[i + 1];
            } else if (j == 1) {
                dp[i] = (int) (n % Math.pow(10, chars.length - 1 - i)) + 1 + record[chars.length - 1 - i] + dp[i + 1];
            } else {
                dp[i] = (int) (Math.pow(10, chars.length - 1 - i)) + j * record[chars.length - 1 - i] + dp[i + 1];
            }
        }
        return dp[0];
    }

    public int findNthDigit(int n) {
        int digit = 1;
        long start = 0, count = 9, num = 0;
        while (start + count < n) {
            if (digit == 1) {
                start += count + 1;
            } else {
                start += count;
            }
            digit++;
            count = (int) (digit * (Math.pow(10, digit) - Math.pow(10, digit - 1)));
        }
        if (digit > 1) {
            num = (long) Math.pow(10, digit - 1);
        }
        num += (n - start) / digit;
        n = (int) ((n - start) % digit);
        return Long.toString(num).charAt(n) - '0';
    }

    public static void main(String[] args) {
        Num num = new Num();
        System.out.println(num.findNthDigit(13));
        System.out.println(num.findNthDigit(9));
        System.out.println(num.findNthDigit(1000000000));
        System.out.println(num.findNthDigit(3));
        System.out.println(num.findNthDigit(11));
    }

}
