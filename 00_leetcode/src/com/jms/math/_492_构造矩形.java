package com.jms.math;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/23 14:53
 */
public class _492_构造矩形 {

    public static void main(String[] args) {
        _492_构造矩形 v = new _492_构造矩形();
        for (int i : v.constructRectangle(5)) {
            System.out.print(i + " ");
        }
    }

    public int[] constructRectangle(int area) {
        int w = (int) Math.sqrt(area);

        while ((area % w) != 0){
            w--;
        }

        return new int[]{area / w, w};
    }

    public int[] constructRectangle1(int area) {
        double l = 1, r = area;
        double curArea = l * r;
        int[] result = new int[2];
        result[0] = (int) r;
        result[1] = (int) l;
        double min = r - l;

        while (l <= area / 2){
            curArea = l * r;
            if (curArea == area && (r - l) < min){
                result[0] = (int) r;
                result[1] = (int) l;
                min = r - l;
            }
            l++;
            r = Math.floor(area / l);
            if (l > r) break;
        }

        return result;
    }
}
