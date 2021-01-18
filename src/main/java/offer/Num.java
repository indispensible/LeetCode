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

    public int nthUglyNumber(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        return dp[n - 1];
    }


    public int missingNumber(int[] nums) {
        return missingNumber(nums, 0, nums.length - 1);
    }

    public int missingNumber(int[] nums, int left, int right) {
        int index = (left + right) >> 1;
        if (nums[index] != index) {
            if (index == 0 || nums[index - 1] == index - 1) {
                return index;
            }
            return missingNumber(nums, left, index - 1);
        } else {
            if (left == right) {
                return nums.length;
            }
            return missingNumber(nums, index + 1, right);
        }
    }

    public int[] singleNumbers(int[] nums) {
        int num = 0, mask, num1 = 0, num2 = 0;
        for (int i = 0; i < nums.length; i++) {
            num ^= nums[i];
        }
        mask = num & (-num);
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & mask) == 0) {
                num1 ^= nums[i];
            } else {
                num2 ^= nums[i];
            }
        }
        return new int[]{num1, num2};
    }

    public int newSingleNumber(int[] nums) {
        int[] counts = new int[32];
        int num, j;
        for (int value : nums) {
            num = value;
            for (j = 0; j < 32; j++) {
                counts[j] += num & 1;
                num >>= 1;
            }
        }
        num = 0;
        for (int i = 31; i >= 0; i--) {
            num <<= 1;
            num |= counts[i] % 3;
        }
        return num;
    }

    public int[] twoSum(int[] nums, int target) {
        int i = 0, j = nums.length - 1, mid;
        if (nums[nums.length - 1] <= target) {
            j = nums.length - 1;
        } else {
            mid = nums.length >>> 1;
            while (!(nums[mid] <= target && nums[mid + 1] > target)) {
                if (nums[mid + 1] <= target) {
                    i = mid;
                    mid = (i + j) >>> 1;
                } else {
                    j = mid;
                    mid = (i + j) >>> 1;
                }
            }
            j = mid;
        }
        i = 0;
        while ((nums[i] + nums[j]) != target) {
            if (nums[i] + nums[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return new int[]{nums[i], nums[j]};
    }

    public int[][] findContinuousSequence(int target) {
        List<int[]> lists = new ArrayList<>();
        int n = 2, partSum = n * (n - 1) >> 1, i;
        while (partSum < target) {
            if ((target - partSum) % n == 0) {
                i = (target - partSum) / n;
                int[] ints = new int[n];
                for (int j = 0; j < n; j++) {
                    ints[j] = i + j;
                }
                lists.add(ints);
            }
            n++;
            partSum = n * (n - 1) >> 1;
        }
        int[][] newInts = new int[lists.size()][];
        for (i = 0; i < lists.size(); i++) {
            newInts[i] = lists.get(lists.size() - i - 1);
        }
        return newInts;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        int i = -k + 1, j = 0;
        while (j < nums.length) {
            while (!deque.isEmpty() && deque.getLast() < nums[j]) {
                deque.pollLast();
            }
            deque.add(nums[j]);
            if (i >= 0) {
                res[i] = deque.getFirst();
                if (nums[i] == deque.getFirst()) {
                    deque.poll();
                }
            }
            i++;
            j++;
        }
        return res;
    }

    public double[] dicesProbability(int n) {
        double count = Math.pow(6, n);
        double[][] dp = new double[n + 1][6 * n + 1];
        double[] doubles = new double[5 * n + 1];
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= 6 * i; j++) {
                for (int m = 1; m <= 6; m++) {
                    if (j <= m) {
                        break;
                    }
                    dp[i][j] += dp[i - 1][j - m];
                }
            }
        }
        for (int i = n; i <= 6 * n; i++) {
            doubles[i - n] = dp[n][i] / count;
        }
        return doubles;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<Integer>();
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            left = i + 1;
            right = nums.length - 1;
            while (left != right) {
                if (left > i + 1 && nums[left] == nums[left - 1]) {
                    continue;
                }
                if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    list.clear();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    lists.add(list);
                    left++;
                    right--;
                }
            }
        }
        return lists;
    }

    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            if (nums[0] == target) {
                return 0;
            }
            return -1;
        }
        int l = 0, r = nums.length - 1, mid;
        while (l <= r) {
            mid = (l + r) >> 1;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[nums.length - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    public int trap(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        int l = 0, h = height[l], r = 1, res = 0;
        for (; r < height.length; r++) {
            if (r - l > 1 && height[r] < height[r - 1]) {
                h = height[r - 1];
                while (l != r - 1) {
                    if (h - height[l] > 0) {
                        res += h - height[l];
                    }
                    l++;
                }
            }
            if (height[r] > h) {
                while (l != r) {
                    if (h - height[l] > 0) {
                        res += h - height[l];
                    }
                    l++;
                }
                h = height[l];
            }
        }
        h = height[r - 1];
        while (l != r - 1) {
            if (h - height[l] > 0) {
                res += h - height[l];
            }
            l++;
        }
        return res;
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] nums1, int[] nums2) {
                return nums1[0] - nums2[0];
            }
        });
        List<int[]> list = new ArrayList<>();
        for (int[] nums : intervals) {
            if (list.isEmpty()) {
                list.add(nums);
                continue;
            }
            if (list.get(list.size() - 1)[1] >= nums[0]) {
                list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], nums[1]);
            } else {
                list.add(nums);
            }
        }
        return list.toArray(new int[intervals.length][]);
    }

    public static void main(String[] args) {
        Num num = new Num();

        int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(num.trap(nums));

//        num.dicesProbability(2);
//        num.findContinuousSequence(9);

//        int[] nums = {45,46,67,73,74,74,77,83,89,98};
//        num.twoSum(nums, 147);
//        int[] nums = {3, 4, 3, 3};
//        num.newSingleNumber(nums);

//        num.missingNumber(nums);

//        num.translateNum(506);

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
