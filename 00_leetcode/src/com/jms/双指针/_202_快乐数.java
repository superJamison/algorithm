package com.jms.双指针;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/3 16:16
 */
public class _202_快乐数 {

    // 快慢指针
    public boolean isHappy(int n) {
            int slow = getNext(n);
            int fast = getNext(getNext(n));
            while (fast != 1 && slow != fast){
                slow = getNext(slow);
                fast = getNext(getNext(fast));
            }
            return fast == 1;
    }

    private int getNext(int num){
        int sum = 0;
        while (num != 0){
            int a = num % 10;
            sum += a * a;
            num /= 10;
        }
        return sum;
    }

    // set判断是否循环
    public boolean isHappy1(int n) {
        Set<Integer> set = new HashSet<>();

        int num = n, sum = n;
        do {
            num = sum;
            set.add(num);
            sum = 0;
            while (num != 0) {
                int a = num % 10;
                sum += a * a;
                num /= 10;
            }
            if (sum == 1) {
                return true;
            }
        } while (!set.contains(sum));

        return false;
    }
}
