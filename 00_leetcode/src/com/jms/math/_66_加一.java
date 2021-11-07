package com.jms.math;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/21 16:03
 */
public class _66_加一 {

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9){
                digits[i] = digits[i] + 1;
                return digits;
            }else {
                digits[i] = 0;
            }
        }
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }
}
