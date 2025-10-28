package com.jan.title200.title130;

/**
 * 122. 买卖股票的最佳时机 II
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。然而，你可以在 同一天 多次买卖该股票，但要确保你持有的股票不超过一股。
 * 返回 你能获得的 最大 利润 。
 *
 * 示例 1：
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：7
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3。
 * 最大总利润为 4 + 3 = 7 。
 *
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4。
 * 最大总利润为 4 。
 *
 * 示例 3：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0。
 *
 *
 * 提示：
 * 1 <= prices.length <= 3 * 1^4
 * 0 <= prices[i] <= 10^4
 */
public class Title122 {
    public static void main(String[] args) {
        int[] prices1 = {7,1,5,3,6,4};
        int[] prices2 = {1,2,3,4,5};
        int[] prices3 = {7,6,4,3,1};

        System.out.println(maxProfit(prices1));
        System.out.println(maxProfit(prices2));
        System.out.println(maxProfit(prices3));

        System.out.println(maxProfit2(prices1));
        System.out.println(maxProfit2(prices2));
        System.out.println(maxProfit2(prices3));

        System.out.println(maxProfit3(prices1));
        System.out.println(maxProfit3(prices2));
        System.out.println(maxProfit3(prices3));

        System.out.println(maxProfit4(prices1));
        System.out.println(maxProfit4(prices2));
        System.out.println(maxProfit4(prices3));
    }

    /**
     * 动态规划，使用二维数据
     * 定义状态 dp[i][0] 表示第 i 天交易完后手里没有股票的最大利润，dp[i][1] 表示第 i 天交易完后手里持有一支股票的最大利润（i 从 0 开始）。
     * 1、考虑 dp[i][0] 的转移方程，如果这一天交易完后手里没有股票，那么可能的转移状态为前一天已经没有股票，即 dp[i−1][0]，
     *    或者前一天结束的时候手里持有一支股票，即 dp[i−1][1]，这时候我们要将其卖出，并获得 prices[i] 的收益。转移方程：
     *    dp[i][0]=max{dp[i−1][0],dp[i−1][1]+prices[i]}
     * 2、按照同样的方式考虑转移状态，那么可能的转移状态为前一天已经持有一支股票，即 dp[i−1][1]，或者前一天结束时还没有股票，即 dp[i−1][0]，
     *    这时候我们要将其买入，并减少 prices[i] 的收益。转移方程：
     *    dp[i][1]=max{dp[i−1][1],dp[i−1][0]−prices[i]}
     */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] f = new int[n + 1][2];
        f[0][1] = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            f[i + 1][0] = Math.max(f[i][0], f[i][1] + prices[i]);
            f[i + 1][1] = Math.max(f[i][0] - prices[i], f[i][1]);
        }

        return f[n][0];
    }

    /**
     * 动态规划，空间优化，使用两组一维数据
     */
    public static int maxProfit2(int[] prices) {
        int n = prices.length;
        int[][] f = new int[2][2];
        f[0][1] = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            f[(i + 1) % 2][0] = Math.max(f[i % 2][0], f[i % 2][1] + prices[i]);
            f[(i + 1) % 2][1] = Math.max(f[i % 2][0] - prices[i], f[i % 2][1]);
        }

        return f[n % 2][0];
    }

    /**
     * 动态规划，空间优化，使用三个变量
     */
    public static int maxProfit3(int[] prices) {
        int f0 = 0, f1 = Integer.MIN_VALUE;
        for(int p : prices) {
            int newF0 = Math.max(f0, f1 + p);
            f1 = Math.max(newF0 - p, f1);
            f0 = newF0;
        }

        return f0;
    }

    /**
     * 贪心算法
     */
    public static int maxProfit4(int[] prices) {
        int ans = 0;
        for(int i = 1; i < prices.length; i++) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }
}
