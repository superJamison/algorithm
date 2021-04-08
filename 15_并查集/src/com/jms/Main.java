package com.jms;

import com.jms.union.*;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/8 19:25
 */
public class Main {
    private static final int COUNT = 2000000;

    public static void main(String[] args) {
//        UnionFind unionFind = new UnionFind_QF(12);
//        UnionFind unionFind = new UnionFind_QU(12);
//        UnionFind unionFind = new UnionFind_QU_S(12);
//        test(unionFind);
//            testTime(new UnionFind_QF(COUNT));
//            testTime(new UnionFind_QU(COUNT));
//            testTime(new UnionFind_QU_S(COUNT));
//            testTime(new UnionFind_QU_R(COUNT));
            testTime(new UnionFind_QU_R_PC(COUNT));
            testTime(new UnionFind_QU_R_PS(COUNT));
            testTime(new UnionFind_QU_R_PH(COUNT));
    }

    private static void testTime(UnionFind unionFind) {
        Times.test(unionFind.getClass().getSimpleName(), () -> {
            for (int i = 0; i < COUNT; i++) {
                unionFind.union((int) (Math.random() * COUNT),
                        (int) (Math.random() * COUNT));
            }

            for (int i = 0; i < COUNT; i++) {
                unionFind.isSame((int) (Math.random() * COUNT),
                        (int) (Math.random() * COUNT));
            }
        });
    }

    private static void test(UnionFind unionFind) {
        unionFind.union(0, 1);
        unionFind.union(0, 3);
        unionFind.union(0, 4);
        unionFind.union(2, 3);
        unionFind.union(2, 5);

        unionFind.union(6, 7);

        unionFind.union(8, 10);
        unionFind.union(9, 10);
        unionFind.union(9, 11);

        unionFind.union(6, 2);
        unionFind.union(7, 9);
    }
}
