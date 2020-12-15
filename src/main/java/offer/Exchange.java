package offer;

/**
 * @author lvgang
 * @date 2020/12/10 12:56
 */
public class Exchange {
    public int[] exchange(int[] nums) {
        int oddIndex = 0;
        int tmp;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 1) {
                tmp = nums[i];
                nums[i] = nums[oddIndex];
                nums[oddIndex++] = tmp;
            }
        }
        return nums;
    }
}
