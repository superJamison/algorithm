package com.jms.数组;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/27 20:20
 */
public class _4_寻找两个正序数组的中位数 {

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{3,4,}));

        // 1,1,2,2,3,5,7,8,9
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 1,1,2,2
        // 0,1,2,3
        int l1 = nums1.length, l2 = nums2.length;

        if (l1 == 0) return (l2 & 1) == 1 ? nums2[l2 >> 1] :(nums2[(l2 - 1) >> 1] + nums2[(l2 + 1) >> 1]) / 2.0;
        if (l2 == 0) return (l1 & 1) == 1 ? nums1[l1 >> 1] :(nums1[(l1 - 1) >> 1] + nums1[(l1 + 1) >> 1]) / 2.0;

        int mid = (l1 + l2) >> 1;
        boolean flag = false;

        if (((l1 + l2) & 1) != 1) {
            mid = mid - 1;
            // 偶数
            flag = true;
        }

        int index1 = 0, index2 = 0, result = 0;

        if (flag){
            while (mid-- >= 0){
                if (index1 == l1){
                    result = nums2[index2++];
                    continue;
                }
                if (index2 == l2){
                    result = nums1[index1++];
                    continue;
                }
                if (nums1[index1] > nums2[index2]){
                    result = nums2[index2++];
                }else {
                    result = nums1[index1++];
                }
            }

            System.out.println(index1 + "  " + index2);
            if (index1 == l1){
                result = Math.max(nums1[index1 - 1], nums2[index2]) + result;
                return result / 2.0;
            }
            if (index2 == l2){
                result = Math.max(nums1[index1], nums2[index2 - 1]) + result;
                return result / 2.0;
            }
            result = Math.min(nums1[index1], nums2[index2]) + result;
            return result / 2.0;
        }else {
            while (mid-- >= 0){
                if (index1 == l1){
                    result = nums2[index2++];
                    continue;
                }
                if (index2 == l2){
                    result = nums1[index1++];
                    continue;
                }
                if (nums1[index1] > nums2[index2]){
                    result = nums2[index2++];
                }else {
                    result = nums1[index1++];
                }
            }
            return result / 1.0;
        }
    }
}
