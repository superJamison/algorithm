package com.jms.分治;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/27 14:49
 */
public class _剑指_Offer_17_打印从1到最大的n位数 {

    public static void main(String[] args) {
        _剑指_Offer_17_打印从1到最大的n位数 v = new _剑指_Offer_17_打印从1到最大的n位数();
        String[] strings = v.printNumbers1(3);
        for (String string : strings) {
            System.out.print(string + "  ");
        }
    }

    public int[] printNumbers(int n) {

        int[] result = new int[(int) (Math.pow(10, n) - 1)];

        for (int i = 0; i < result.length; i++) {
            result[i] = i + 1;
        }

        return result;

    }

    StringBuilder res;
    int count = 0, n;
    String[] result;
    char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    int index = 0;

    public String[] printNumbers1(int n) {
        res = new StringBuilder();
        this.n = n;
        result = new String[(int) (Math.pow(10, n) - 1)];
        dfs(0, true);
        return result;
    }

    void dfs(int x, boolean flag) {
        if (index == result.length) return;
        if (x == n) {
            if (res.length() == 0) return;
            result[index++] = res.toString();
            return;
        }

        for (int i = 0; i < loop.length; i++) {
            if (i == 0 && flag) {
                dfs(x + 1, true);
            } else {
                res.append(loop[i]);
                int length = res.length();
                dfs(x + 1, false);
                res.deleteCharAt(length - 1);
            }
        }
    }


}
