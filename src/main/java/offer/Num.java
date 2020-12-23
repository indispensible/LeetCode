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

//    public String minNumber(int[] nums) {
//        if (nums.length == 0) {
//            return "";
//        }
//        quickSorted(nums, 0, nums.length - 1);
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < nums.length; i++) {
//            stringBuilder.append(String.valueOf(nums[i]));
//        }
//        return stringBuilder.toString();
//    }
//
//    public void quickSorted(int[] nums, int low, int high) {
//        if (low < high) {
//            int index = getIndex(nums, low, high);
//            quickSorted(nums, index + 1, high);
//            quickSorted(nums, low, index - 1);
//        }
//    }
//
//    public int getIndex(int[] nums, int low, int high) {
//        int tmp = nums[low];
//        while (low < high) {
//            while (low < high && judgeNum(nums[high], tmp)) {
//                high--;
//            }
//            nums[low] = nums[high];
//            while (low < high && judgeNum(tmp, nums[low])) {
//                low++;
//            }
//            nums[high] = nums[low];
//        }
//        nums[low] = tmp;
//        return low;
//    }
//
//    public boolean judgeNum(int num1, int num2) {
//        String num1String = String.valueOf(num1), num2String = String.valueOf(num2);
//        StringBuilder num1Str = new StringBuilder(num1String), num2Str = new StringBuilder(num2String);
//        int num1Length = num1Str.length(), num2Length = num2Str.length();
//        char num1End = num1Str.charAt(0), num2End = num2Str.charAt(0);
//        char[] chars1 = num1String.toCharArray(), chars2 = num2String.toCharArray();
//        for (int i = 1; i < chars1.length; i++) {
//            if (chars1[i] > num1End) {
//                num1End = chars1[i];
//            }
//        }
//        for (int i = 1; i < chars2.length; i++) {
//            if (chars2[i] > num2End) {
//                num2End = chars2[i];
//            }
//        }
//        if (num1Length > num2Length) {
//            for (int i = 0; i < num1Length - num2Length; i++) {
//                num2Str.append(num2End);
//            }
//        } else {
//            for (int i = 0; i < num2Length - num1Length; i++) {
//                num1Str.append(num1End);
//            }
//        }
//        if (Long.valueOf(num1Str.toString()) >= Long.valueOf(num2Str.toString())) {
//            return true;
//        }
//        return false;
//    }

    public String minNumber(int[] nums) {
        String[] arrStr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arrStr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(arrStr, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder s = new StringBuilder();
        for (String str : arrStr) {
            s.append(str);
        }
        return s.toString();
    }

    public int translateNum(int num) {
        // 动态规划
        char[] chars = String.valueOf(num).toCharArray();
        int[] dp = new int[chars.length];
        if (chars.length == 1) {
            dp[0] = 1;
        } else {
            dp[0] = 1;
            dp[1] = 2;
            if (Integer.parseInt(String.valueOf(chars, 0, 2)) > 25 || Integer.parseInt(String.valueOf(chars, 0, 2)) < 10) {
                dp[1] = 1;
            }
            for (int i = 2; i < chars.length; i++) {
                dp[i] = dp[i - 1];
                if (('0' < chars[i - 1] && chars[i - 1] < '2') || (chars[i - 1] == '2' && chars[i] <= '5')) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[chars.length - 1];
    }

    public static void main(String[] args) {
        Num num = new Num();

        num.translateNum(506);

//        System.out.println(num.findNthDigit(13));
//        System.out.println(num.findNthDigit(9));
//        System.out.println(num.findNthDigit(1000000000));
//        System.out.println(num.findNthDigit(3));
//        System.out.println(num.findNthDigit(11));
//        int[] nums = {824, 938,1399,5607,6973,5703,9609,4398,8247};
//        int[] nums = {12, 121};
//        String a = "a";
//        a.compareTo("b");
//        num.minNumber(nums);
    }

}
