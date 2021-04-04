package com.jms.sort;

class SortThread extends Thread{

    public int value;
    public SortThread(int value){
        this.value = value;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(value);
            System.out.println(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 *史上最强排序-休眠排序
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/4 17:33
 */
public class SortThreadDemo {
    public static void main(String[] args) {
        int[] array = new int[]{10, 100, 50, 30, 60};
        for (int i = 0; i < array.length; i++) {
            new SortThread(array[i]).start();
        }
    }
}
