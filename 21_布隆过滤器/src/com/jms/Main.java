package com.jms;

/**
 * 思考：如果要经常判断一个元素是否存在，你会怎么做：
 * 很容易想到的是使用哈希表（hashset, hashMap）,将元素作为key区查找
 * 时间复杂度：O(1),但是空间利用率不高，需要占用比较高的内存资源
 *
 * 如果需要编写一个网络爬虫去爬10亿个网站的数据，为了避免爬到重复的网站，如何判断一个网站是否爬过？
 *
 * 那么思考是否存在时间复杂度低，但是占用内存较少的方案？
 * 布隆过滤器就是一个比较好的算法（Bloom Filter）
 *
 *  布隆过滤器：
 *  他是一个空间效率高的概率型数据结构，可以告诉你，一个元素（返回true）一定不存在或者（返回false）可能存在
 *  优缺点：
 *      优点：空间效率和查询时间都远远超过一般的算法
 *      缺点：有一定的误判虑，删除困难
 *
 *  它实际上是一个很长的二进制向量和一系列随机的映射函数（hash函数）
 *  网页黑名单系统、垃圾邮件过滤系统、爬虫的网址判重系统、解决缓存穿透问题。
 *
 *  原理：假设布隆过滤器由20位二进制、3个哈希函数组成，每一个元素经过哈希函数处理都能生出一个索引位置
 *  添加元素：将每一个哈希函数生成的索引位置都设为1
 *  查询元素是否存在：
 *      若果一个哈希函数生成的索引位置不为1，就代表存在（100%准确）
 *      如果一个哈希函数生成的索引位置都为1，就代表存在（存在一定的误判虑）
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/26 19:43
 */
public class Main {
    public static void main(String[] args) {
        BloomFilter<Integer> filter = new BloomFilter<>(100_0000, 0.01);
        for (int i = 1; i <= 100_0000; i++) {
            filter.put(i);
        }
        int count = 0;
        for (int i = 100_0001; i <= 200_0000; i++) {
            if (filter.contains(i)){
                count++;
            }
        }
        System.out.println(count);
    }
}
