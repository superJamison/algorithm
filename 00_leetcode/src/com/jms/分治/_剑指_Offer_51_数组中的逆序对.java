package com.jms.分治;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/27 15:38
 */
public class _剑指_Offer_51_数组中的逆序对 {

    public static void main(String[] args) {
        _剑指_Offer_51_数组中的逆序对 v = new _剑指_Offer_51_数组中的逆序对();
        System.out.println(v.reversePairs(new int[]{7,5,6,4,56,1,12,44,66,7,1,33}));
    }

    int[] leftArr;
    int result = 0;

    public int reversePairs(int[] nums) {
        leftArr = new int[nums.length >> 1];
        sort(nums, 0, nums.length);
        return result;
    }

    private void sort(int[] nums, int l, int r) {
        if (r - l < 2) return;

        int mid = (l + r) >> 1;
        sort(nums,l, mid);
        sort(nums, mid, r);
        merge(nums, l, mid, r);
    }

    private void merge(int[] nums, int l, int mid, int r) {
        int li = 0, le = mid - l, ri = mid, re = r, ai = l;

        // 备份左半边数组
        for (int i = li; i < le; i++) {
            leftArr[i] = nums[i + l];
        }

        while (li < le){
            if (ri < re && leftArr[li] > nums[ri]){
                result += ri - ai;
                nums[ai++] = nums[ri++];
            }else {
                nums[ai++] = leftArr[li++];
            }
        }
    }


    public int reversePairs1(int[] nums) {
        int result = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) result++;
            }
        }

        return result;
    }
}
