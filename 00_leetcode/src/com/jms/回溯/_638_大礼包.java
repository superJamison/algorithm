package com.jms.回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/24 11:38
 */
public class _638_大礼包 {

    public static void main(String[] args) {
        _638_大礼包 v = new _638_大礼包();
        List<Integer> price = new ArrayList<>();
        List<List<Integer>> special = new ArrayList<>();
        List<Integer> needs = new ArrayList<>();
        price.add(0);
        price.add(0);
        price.add(0);

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(1);
        list1.add(0);
        list1.add(4);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(2);
        list2.add(1);
        list2.add(9);
        special.add(list1);
        special.add(list2);

        needs.add(1);
        needs.add(1);
        needs.add(1);

        System.out.println(v.shoppingOffers(price, special, needs));
    }

    private int minPrice = Integer.MAX_VALUE;
    private int needsCount = 0;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // 直接购买
        int total = 0;
        for (int i = 0; i < needs.size(); i++) {
            needsCount += needs.get(i);
            total += needs.get(i) * price.get(i);
        }
        minPrice = total;

        // 过滤掉礼包价格比原价购买还贵的礼包
        List<List<Integer>> filterSpecial = new ArrayList<>();
        for (List<Integer> sp : special) {
            int totalCount = 0, totalPrice = 0;
            for (int i = 0; i < price.size(); ++i) {
                totalCount += sp.get(i);
                totalPrice += sp.get(i) * price.get(i);
            }
            if (totalCount > 0 && totalPrice > sp.get(price.size())) {
                filterSpecial.add(sp);
            }
        }

        // 回溯
        if (filterSpecial.size() > 0) {
            int[] buySpecial = new int[filterSpecial.size()];
            dfs(price, filterSpecial, needs, buySpecial, 0, 0, 0);
        }

        return minPrice;
    }

    /**
     * @param buySpecial buySpecial[i] 保存第i个礼包的数量
     * @param curTotalCount 当前购买物品的数量
     * @param curTotalPrice 当前购买物品的总价格
     * @param i 第i个礼包
     */
    private void dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int[] buySpecial, int curTotalCount, int curTotalPrice, int i) {
        if (curTotalCount > needsCount || curTotalPrice > minPrice) {
            return;
        }
        // 遍历到最后一个礼包
        if (i == buySpecial.length) {
            int curPrice = 0;
            int[] buyProduct = new int[needs.size()];
            for (int j = 0; j < buySpecial.length; j++) {
                if (buySpecial[j] != 0) {
                    for (int x = 0; x < buyProduct.length; x++) {
                        buyProduct[x] += buySpecial[j] * special.get(j).get(x);
                        if (buyProduct[x] > needs.get(x)) return;
                    }
                }
                curPrice += buySpecial[j] * special.get(j).get(needs.size());
                if (curPrice > minPrice) return;
            }
            for (int j = 0; j < needs.size(); j++) {
                curPrice += (needs.get(j) - buyProduct[j]) * price.get(j);
                if (curPrice > minPrice) return;
            }
            if (curPrice < minPrice) minPrice = curPrice;
            return;
        }

        // 计算第i个礼包购买的数量
        int count = Integer.MAX_VALUE;
        List<Integer> list = special.get(i);
        // 当前礼包物品的总数量
        int c = 0;
        for (int j = 0; j < list.size() - 1; j++) {
            if (list.get(j) != 0) {
                c += list.get(j);
                int curCount = needs.get(j) / list.get(j);
                count = Math.min(count, curCount);
            }
        }

        while (count >= 0) {
            buySpecial[i] = count;
            dfs(price, special, needs, buySpecial, curTotalCount + count * c, curTotalPrice + count * special.get(i).get(needs.size()), i + 1);
            count--;
        }
    }
}
