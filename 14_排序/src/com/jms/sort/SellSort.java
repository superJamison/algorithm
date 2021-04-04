package com.jms.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/4 20:22
 */
public class SellSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        List<Integer> stepSequence = sedgewickStepSequence();
        for (Integer step : stepSequence) {
            sort(step);
        }
    }

    private void sort(Integer step){
        //col 第几列
        for (int col = 0; col < step; col++) {

            //col、col+step、col+1*step、col+2*step
//            for (int begin = col+step; begin < arrays.length; begin += step) {
//                int cur = begin;
//                while (cur > col && cmp(cur, cur - step) < 0){
//                    swap(cur, cur - step);
//                    cur -= step;
//                }
//            }

            for (int begin = col+step; begin < arrays.length; begin += step) {
                int cur = begin;
                T v = arrays[cur];
                while (cur > col && cmpElement(v, arrays[cur - step]) < 0){
                    arrays[cur] = arrays[cur - step];
                    cur -= step;
                }
                arrays[cur] = v;
            }
        }
    }

    private List<Integer> shellStepSequence() {
        ArrayList<Integer> list = new ArrayList<>();
        int step = arrays.length;
//        while ((step = step >> 1) > 0){
        while ((step >>= 1) > 0){
            list.add(step);
        }
        return list;
    }

    public List<Integer> sedgewickStepSequence() {
        List<Integer> list = new LinkedList<>();
        int k = 0, step = 0;
        while (true){
            if (k % 2 == 0){
                step = (int) (9 * (Math.pow(2, k) - Math.pow(2, k >> 1)) + 1);
            }else {
                step = (int) (8 * Math.pow(2, k) - 6 * Math.pow(2, (k + 1) >> 1) + 1);
            }
            if (step >= arrays.length) break;
            list.add(0, step);
            k++;
        }
//        System.out.println(list);
        return list;
    }


}
