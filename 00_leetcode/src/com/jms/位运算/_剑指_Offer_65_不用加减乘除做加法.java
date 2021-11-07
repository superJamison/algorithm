package com.jms.位运算;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/18 10:40
 */
public class _剑指_Offer_65_不用加减乘除做加法 {

    public int add(int a, int b) {
        int temp;
        while (b != 0){
            // 查找需要进位的数值
            temp = (a & b) << 1;
            // 异或得到不需要进位的数值
            a ^= b;
            b = temp;
        }
        return a;
    }
}
