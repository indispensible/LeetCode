package offer;

import java.util.Arrays;

/**
 * @author lvgang
 * @date 2020/12/25 10:32
 */
public class Pairs {
    public int reversePairs(int[] nums) {
        int sum = 0;
        int[] ranks = new int[nums.length];
        ranks = Arrays.copyOf(nums, nums.length);
        Arrays.sort(ranks);
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Arrays.binarySearch(ranks, nums[i]) + 1;
        }
        BitTree bitTree = new BitTree(nums.length);
        for (int i = nums.length - 1; i >= 0; i--) {
            bitTree.update(nums[i], 1);
            sum += bitTree.query(nums[i] - 1);
        }
        return sum;
    }

    class BitTree {
        int len;
        int[] trees;

        public BitTree (int len) {
            trees = new int[len];
            this.len = len;
        }

        public int lowbit(int x) {
            return x & (-x);
        }

        public void update(int index, int delta) {
            while (index <= len) {
                trees[index - 1] += delta;
                index += lowbit(index);
            }
        }

        public int query(int index) {
            int sum = 0;
            while (index > 0) {
                sum += trees[index - 1];
                index -= lowbit(index);
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        Pairs pairs = new Pairs();
        int[] nums = {7, 5, 6, 4};
        pairs.reversePairs(nums);
    }
}
