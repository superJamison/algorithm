package com.jms;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/26 20:11
 */
public class BloomFilter<T> {
    /**
     * 二进制向量的长度（一共有多少个二进制位）
     */
    private int bitSize;
    /**
     * 二进制向量
     */
    private long[] bits;
    /**
     * 哈希函数的个数
     */
    private int hashSize;

    /**
     * @param n 数据规模
     * @param p 误判虑，取值范围（0，1）
     */
    public BloomFilter(int n, double p) {
        if (n <= 0 || p <= 0 || p >= 1){
            throw new IllegalArgumentException("wrong n or p！！！");
        }

        // 求出ln2的值
        double ln2 = Math.log(2);

        // 求出二进制向量的长度
        bitSize = (int) (-(n * Math.log(p)) / (ln2 * ln2));
        // 求出哈希函数的个数
        hashSize = (int) (bitSize * ln2 / n);
        // 初始化bits数组，求出数组长度
        bits = new long[(bitSize + Long.SIZE - 1)  / Long.SIZE];

    }

    /**
     * 添加元素
     */
    public void put(T value){
        nullCheck(value);
        // 利用value生成两个hash整数
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;
        for (int i = 1; i <= hashSize; i++) {
            int combinedHash = hash1 + (i * hash2);
            if (combinedHash < 0){
                combinedHash = ~combinedHash;
            }
            // 生成一个二进制位索引
            int index = combinedHash % bitSize;
            // 设置index位置的二进制位为1
            set(index);
        }
    }

    /**
     * 设置index位置的二进制为1
     * @param index
     */
    private void set(int index) {
        // 找到第几个long
        long value = bits[index / Long.SIZE];
        bits[index / Long.SIZE] = value | (1 << (index % Long.SIZE));
    }

    /**
     * 判断一个元素是否存在
     * @param value
     */
    public boolean contains(T value){
        nullCheck(value);
        // 利用value生成两个hash整数
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;
        for (int i = 1; i <= hashSize; i++) {
            int combinedHash = hash1 + (i * hash2);
            if (combinedHash < 0){
                combinedHash = ~combinedHash;
            }
            // 生成一个二进制位索引
            int index = combinedHash % bitSize;
            // 查看index位置的二进制位是否为1
            if (!get(index)) return false;
        }
        return true;
    }

    /**
     * 查看index位置的二进制位是否为1
     * @return  true：是为1   false：不是为1
     */
    private boolean get(int index) {
        // 找到第几个long
        long value = bits[index / Long.SIZE];
        if ((value & (1 << (index % Long.SIZE))) == 0) return false;
        return true;
    }

    private void nullCheck(T value) {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null.");
        }
    }

}
