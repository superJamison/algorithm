package com.jms.回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/16 21:35
 */
public class _282_给表达式添加运算符2 {

    public static void main(String[] args) {
        _282_给表达式添加运算符2 v = new _282_给表达式添加运算符2();
        v.addOperators("123", 6);
//        v.addOperators("3456237490", 9191);
        System.out.println(v.results);
    }

    private List<String> results = new ArrayList<>();
    private List<List<String>> nums = new ArrayList<>();
    private int target;

    public List<String> addOperators(String num, int target) {
        this.target = target;
        slicingNum(num);

        return new ArrayList<>(results);
    }

    private void slicingNum(String num) {
        slicingNum2List(num, 0, new ArrayList<>());
        for (List<String> list : nums) {
            StringBuilder builder = new StringBuilder();
            builder.append(list.get(0));
            addOperator2Result(list, Long.valueOf(list.get(0)), '+', Long.valueOf(list.get(0)), builder, 1);
        }
    }

    private void addOperator2Result(List<String> list, Long total, char c, Long prevNum, StringBuilder stringBuilder, int index) {
        if (index == list.size()) {
            if (total == target) {
                results.add(stringBuilder.toString());
            }
            return;
        }

        String cur = list.get(index);
        Long curNum = Long.valueOf(cur);

        // +
        int addL = 0;
        if (stringBuilder.length() > 0) {
            stringBuilder.append("+").append(cur);
            addL++;
        } else stringBuilder.append(cur);
        addOperator2Result(list, total + curNum, '+', curNum, stringBuilder, index + 1);
        stringBuilder.delete(stringBuilder.length() - cur.length() - addL, stringBuilder.length());

        // -
        int subL = 0;
        if (stringBuilder.length() > 0) {
            stringBuilder.append("-").append(cur);
            subL++;
        } else stringBuilder.append(cur);
        addOperator2Result(list, total - curNum, '-', curNum, stringBuilder, index + 1);
        stringBuilder.delete(stringBuilder.length() - cur.length() - subL, stringBuilder.length());

        // *
        int rideL = 0;
        if (stringBuilder.length() > 0) {
            stringBuilder.append("*").append(cur);
            rideL++;
        } else stringBuilder.append(cur);
        if (c == '+') {
            addOperator2Result(list, total - prevNum + prevNum * curNum, c, prevNum * curNum, stringBuilder, index + 1);
        } else if (c == '-') {
            addOperator2Result(list, total + prevNum - prevNum * curNum, c, prevNum * curNum, stringBuilder, index + 1);
        }
        stringBuilder.delete(stringBuilder.length() - cur.length() - rideL, stringBuilder.length());
    }

    private void slicingNum2List(String num, int i, ArrayList<String> list) {
        if (i == num.length()) {
            List<String> newList = new ArrayList<>(list);
            if (newList.size() > 0) {
                nums.add(newList);
            }
            return;
        }

        if (list.size() > 0) {
            String oldNum = list.get(list.size() - 1);
            if (Integer.parseInt(oldNum) != 0L) {
                String s = oldNum + num.charAt(i);
                list.set(list.size() - 1, s);
                slicingNum2List(num, i + 1, list);
                list.remove(list.size() - 1);
                list.add(oldNum);
            }
        }

        list.add(num.substring(i, i + 1));
        slicingNum2List(num, i + 1, list);
        list.remove(list.size() - 1);
    }
}
