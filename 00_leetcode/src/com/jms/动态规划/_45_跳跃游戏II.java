package com.jms.动态规划;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/17 23:10
 */
public class _45_跳跃游戏II {
    public static void main(String[] args) {
        int jump = jump(new int[]{2, 3, 1, 1, 4});
//        int jump = jump(new int[]{2,3,0,1,4});

        System.out.println();
        System.out.println(jump);
    }

    public static int jump(int[] nums) {
        int maxPosition = 0, result = 0, end = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            // 已经跳到当前的最远距离了，表明这个位置就是这一步要跳跃的位置了。刷新下一个最远距离
            if (i == end){
                end = maxPosition;
                result++;
            }
        }
        return result;
    }

    public static int jump2(int[] nums) {
        int result = 0, maxPosition = 0, end = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                result++;
            }
        }
        return result;
    }

    public static int jump1(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int index = nums.length - 1;
        int count = 0;

        // index到第一个位置就不需要往前找了
        while (index > 0) {
            // 找到跳到index位置最远的距离
            for (int i = 0; i < index; i++) {
                if (nums[i] + i >= index) {
                    count++;
                    index = i;
                    break;
                }
            }
        }

        return count;
    }
}
