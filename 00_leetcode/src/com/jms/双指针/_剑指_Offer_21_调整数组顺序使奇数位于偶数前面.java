package com.jms.双指针;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/10 21:34
 */
public class _剑指_Offer_21_调整数组顺序使奇数位于偶数前面 {
    public static void main(String[] args) {
        _剑指_Offer_21_调整数组顺序使奇数位于偶数前面 v = new _剑指_Offer_21_调整数组顺序使奇数位于偶数前面();
        for (int i : v.exchange(new int[]{1, 2, 3, 4})) {
            System.out.print(i + "   ");
        }
    }

    public int[] exchange(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};

        int l = 0, r = nums.length - 1;

        while (l < r){
            while (l < nums.length && (nums[l] & 1) != 0){
                l++;
            }
            while (r >= 0 && (nums[r] & 1) == 0){
                r--;
            }

            if (l == nums.length || r == -1 || l > r) break;
            // 此时l位置就是左边第一个偶数，r位置就是右边第一个奇数
            exchange(nums, l, r);
            l++;
            r--;
        }

        return nums;
    }

    private void exchange(int[] nums, int l, int r) {
        nums[l] = nums[l] + nums[r];
        nums[r] = nums[l] - nums[r];
        nums[l] = nums[l] - nums[r];
    }
}
