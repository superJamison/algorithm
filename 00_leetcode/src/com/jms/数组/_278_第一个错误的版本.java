package com.jms.数组;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/16 9:51
 */
public class _278_第一个错误的版本 {
    public static void main(String[] args) {
        System.out.println(firstBadVersion(5));
    }

    public static int firstBadVersion(int n) {

        int left = 1, right = n;

        while (left < right){
            int mid = left + ((right - left) >> 1);

            if (isBadVersion(mid)){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean isBadVersion(int version) {
        if (version < 4){
            return false;
        }
        return true;
    }


}
