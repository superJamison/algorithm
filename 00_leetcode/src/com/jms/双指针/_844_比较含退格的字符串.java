package com.jms.双指针;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/3 20:42
 */
public class _844_比较含退格的字符串 {
    public static void main(String[] args) {
        boolean result = backspaceCompare("ab#c", "ad#c");

        System.out.println(result);
    }

    public static boolean backspaceCompare(String s, String t) {
        int sIndex = s.length() - 1, tIndex = t.length() - 1;
        int skipS = 0, skipT = 0;

        while (sIndex >= 0 || tIndex >= 0){

            // 找到s中第一个需要比较的字符
            while (sIndex >= 0){
                if (s.charAt(sIndex) == '#'){
                    skipS++;
                    sIndex--;
                }else if (skipS > 0){
                    skipS--;
                    sIndex--;
                }else {
                    break;
                }
            }

            // 找到t中第一个需要比较的字符
            while (tIndex >= 0){
                if (t.charAt(tIndex) == '#'){
                    skipT++;
                    tIndex--;
                }else if (skipT > 0){
                    skipT--;
                    tIndex--;
                }else {
                    break;
                }
            }

            // 然后开始比较,注意有下面这个 if 条件的原因是：如果 index = 0 位置上为 '#'，则 i, j 会为 -1
            // 而 index = -1 的情况应当处理。
            if (sIndex >= 0 && tIndex >= 0){
                if (s.charAt(sIndex) != t.charAt(tIndex)){
                    return false;
                }
            }
            // (i >= 0 && j >= 0) 为 false 情况为
            // 1. i < 0 && j >= 0
            // 2. j < 0 && i >= 0
            // 3. i < 0 && j < 0
            // 其中，第 3 种情况为符合题意情况，因为这种情况下 s 和 t 都是 index = 0 的位置为 '#' 而这种情况下
            // 退格空字符即为空字符，也符合题意，应当返回 True。
            // 但是，情况 1 和 2 不符合题意，因为 s 和 t 其中一个是在 index >= 0 处找到了待比较字符，另一个没有找到
            // 这种情况显然不符合题意，应当返回 False，下式便处理这种情况。
            else if (sIndex >= 0 || tIndex >= 0){
                return false;
            }
            sIndex--;
            tIndex--;
        }

        return true;
    }

    public static boolean backspaceCompare1(String s, String t) {

        return backspace(s).toString().equals(backspace(t).toString());
    }

    private static StringBuilder backspace(String s) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '#'){
                if (stringBuilder.length() > 0) stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder;
    }
}
