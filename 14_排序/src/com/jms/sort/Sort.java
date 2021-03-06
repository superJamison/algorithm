package com.jms.sort;

import java.text.DecimalFormat;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/2 20:51
 *
 *
 */
public abstract class Sort<T extends Comparable<T>> implements Comparable<Sort<T>> {
    protected T[] arrays;
    public int cmpCount = 0;
    public int swapCount = 0;
    private long time;
    private DecimalFormat fmt = new DecimalFormat("#.00");

    public void sort(T[] arrays){
        if (arrays == null || arrays.length < 2){
            return;
        }
        this.arrays = arrays;
        long begin = System.currentTimeMillis();
        sort();
        time = System.currentTimeMillis() - begin;
    }

    protected abstract void sort();

    /**
     * @param i1
     * @param i2
     * @return
     * 返回值为0，代表arrays[i1] == arrays[i2]
     * 返回值小于0，代表arrays[i1] < arrays[i2]
     * 返回值大于0，代表arrays[i1] > arrays[i2]
     */
    protected int cmp(int i1, int i2){
        cmpCount++;
        return arrays[i1].compareTo(arrays[i2]);
    }

    protected int cmpElement(T v1, T v2){
        cmpCount++;
        return v1.compareTo(v2) ;
    }

    protected void swap(int i1, int i2){
        swapCount++;
        T tmp = arrays[i1];
        arrays[i1] = arrays[i2];
        arrays[i2] = tmp;
    }

    private String numberString(int number) {
        if (number < 10000) return "" + number;

        if (number < 100000000) return fmt.format(number / 10000.0) + "万";
        return fmt.format(number / 100000000.0) + "亿";
    }

    @Override
    public String toString() {
        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareCountStr = "比较：" + numberString(cmpCount);
        String swapCountStr = "交换：" + numberString(swapCount);
        return "【" + getClass().getSimpleName() + "】\n"
                + timeStr + " \t"
                + compareCountStr + "\t "
                + swapCountStr + "\n"
                + "------------------------------------------------------------------";
    }

    @Override
    public int compareTo(Sort o) {
        int result = (int) (this.time - o.time);
        if (result != 0) return result;
        result = cmpCount - o.cmpCount;
        if (result != 0) return result;
        return swapCount - o.swapCount;
    }
}
