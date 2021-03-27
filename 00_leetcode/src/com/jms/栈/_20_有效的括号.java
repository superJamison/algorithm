package com.jms.栈;

import java.util.HashMap;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
 * @author Jamison
 * @version 1.0
 * @date 2021/3/23 10:41
 */
public class _20_有效的括号 {

    private static HashMap<Character, Character> map = new HashMap<>();
    static {
        map.put('[', ']');
        map.put('{', '}');
        map.put('(', ')');
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) { //左括号
                stack.push(c);
            }else {
                if (stack.isEmpty()) return false;
                Character left = stack.pop();
                if (c != map.get(left)) return false;
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid3(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '[' || c == '{' || c == '(') {
                stack.push(c);
            }else {
                if (stack.isEmpty()) return false;
                Character left = stack.pop();
                if (left == '[' && c != ']') return false;
                if (left == '{' && c != '}') return false;
                if (left == '(' && c != ')') return false;
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid2(String s) {
        while(s.contains("{}")
                || s.contains("[]")
                || s.contains("()")){
            s.replace("{}", "");
            s.replace("[]", "");
            s.replace("()", "");
        }
        if (s.isEmpty()) {
            return true;
        }
        return false;
    }

}
