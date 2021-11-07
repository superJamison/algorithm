package com.jms.数组;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/2 12:02
 */
public class _剑指_Offer_11_旋转数组的最小数字 {
    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) return -1;

        int l = 0, r = numbers.length - 1;

        while (l <= r){
            int mid = (l + r) >> 1;

            if (numbers[mid] > numbers[r]){
                l = mid + 1;
            }else if (numbers[mid] < numbers[r]){
                r = mid;
            }else {
                r = r - 1;
            }
        }

        return numbers[l];
    }
}
