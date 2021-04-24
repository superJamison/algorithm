import java.util.Arrays;

/**
 * 银币问题，假设有不同面值的硬币，当要求找回顾客一定价值的零钱时，
 * 求怎么样使用这几种面值的硬币，可以使得硬币数量最少
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/4/21 22:05
 */
public class CoinChange {
    public static void main(String[] args) {
        coinChange(new Integer[]{25, 10, 5, 1}, 41);
        System.out.println("=============================");
        coinChange(new Integer[]{25, 20, 5, 1}, 41);
    }

    private static void coinChange(Integer[] coins, int money) {
        Arrays.sort(coins);
        int count = 0, idx = coins.length - 1;
        while (idx >= 0){
            while (money >= coins[idx]){
                money -= coins[idx];
                count++;
                System.out.print(coins[idx] + "    ");
            }
            idx--;
        }
        System.out.println("一共使用了" + count + "个硬币");
    }
}
