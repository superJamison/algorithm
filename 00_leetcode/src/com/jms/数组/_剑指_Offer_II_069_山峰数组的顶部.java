package com.jms.数组;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/14 19:30
 */
public class _剑指_Offer_II_069_山峰数组的顶部 {

    public static void main(String[] args) {
        _剑指_Offer_II_069_山峰数组的顶部 v = new _剑指_Offer_II_069_山峰数组的顶部();
        System.out.println(v.peakIndexInMountainArray(new int[]{3, 4, 5, 1}));
    }

    public int peakIndexInMountainArray(int[] arr) {
        int l = 0, r = arr.length - 1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (mid > 0 && mid < arr.length - 1 && arr[mid - 1] < arr[mid] && arr[mid + 1] < arr[mid]){
                return mid;
            }else if (mid > 0 && arr[mid - 1] > arr[mid]){
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }

        return l;
    }
}
