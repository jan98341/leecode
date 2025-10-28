package com.jan.title800.title720;

import java.util.Arrays;

/**
 * 714. 买卖股票的最佳时机含手续费
 * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * 示例 1：
 * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出：8
 * 解释：能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
 *
 * 示例 2：
 * 输入：prices = [1,3,7,5,10,3], fee = 3
 * 输出：6
 *
 * 提示：
 * 1 <= prices.length <= 5 * 10^4
 * 1 <= prices[i] < 5 * 10^4
 * 0 <= fee < 5 * 10^4
 */
public class Title714 {
    public static void main(String[] args) {
        int[] prices1 = {1, 3, 2, 8, 4, 9};
        int[] prices2 = {1,3,7,5,10,3};

        Title714 title714 = new Title714();
        System.out.println(title714.maxProfitX(prices1, 2));
        System.out.println(title714.maxProfitX(prices2, 3));

        System.out.println(maxProfit(prices1, 2));
        System.out.println(maxProfit(prices2, 3));

        System.out.println(maxProfit2(prices1, 2));
        System.out.println(maxProfit2(prices2, 3));

        System.out.println(maxProfit3(prices1, 2));
        System.out.println(maxProfit3(prices2, 3));

        System.out.println(maxProfit4(prices1, 2));
        System.out.println(maxProfit4(prices2, 3));

        System.out.println(maxProfit5(prices1, 2));
        System.out.println(maxProfit5(prices2, 3));
    }

    /**
     * 递归搜索 + 保存计算结果 = 记忆化搜索
     * 公共变量提到方法外，减少方法传递参数个数
     */
    private int[] prices;
    private int[][] memo;
    private int fee;
    public int maxProfitX(int[] prices, int fee) {
        this.prices = prices;
        this.fee = fee;
        int n = prices.length;
        memo = new int[n][2];
        for(int[] row : memo) {
            // -1 表示还没有计算过
            Arrays.fill(row, -1);
        }

        return dfsX(n - 1, 0);
    }
    private int dfsX(int i, int hold) {
        if (i < 0) {
            return hold == 1 ? Integer.MIN_VALUE / 2 : 0;
        }
        if(memo[i][hold] != -1) {
            return memo[i][hold];
        }
        if (hold == 1) {
            return memo[i][hold] = Math.max(dfsX(i - 1, 1), dfsX(i - 1, 0) - prices[i]);
        }
        return memo[i][hold] = Math.max(dfsX(i - 1, 0), dfsX(i - 1, 1) + prices[i] - fee);
    }

    /**
     * 递归搜索 + 保存计算结果 = 记忆化搜索
     */
    public static int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] memo = new int[n][2];
        for(int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(prices.length - 1, 0, fee, memo, prices);
    }

    private static int dfs(int i, int hold,  int fee, int[][] memo, int[] prices) {
        if(i < 0) {
            return hold == 1 ? Integer.MIN_VALUE / 2 : 0;
        }
        if(memo[i][hold] != -1) {
            return memo[i][hold];
        }
        if(hold == 1) {
            return  memo[i][hold] = Math.max(dfs(i - 1, 1, fee, memo, prices), dfs(i - 1, 0, fee, memo, prices) - prices[i]);
        } else {
            return memo[i][hold] = Math.max(dfs(i - 1, 0, fee, memo, prices), dfs(i - 1, 1, fee, memo, prices) + prices[i] - fee);
        }
    }

    /**
     * 动态规划，使用二维数据
     * 定义状态 dp[i][0] 表示第 i 天交易完后手里没有股票的最大利润，dp[i][1] 表示第 i 天交易完后手里持有一支股票的最大利润（i 从 0 开始）。
     * 1、考虑 dp[i][0] 的转移方程，如果这一天交易完后手里没有股票，那么可能的转移状态为前一天已经没有股票，即 dp[i−1][0]，
     *    或者前一天结束的时候手里持有一支股票，即 dp[i−1][1]，这时候我们要将其卖出，并获得 prices[i] 的收益并扣除手续费fee。转移方程：
     *    dp[i][0]=max{dp[i−1][0],dp[i−1][1]+prices[i]-fee}
     * 2、按照同样的方式考虑转移状态，那么可能的转移状态为前一天已经持有一支股票，即 dp[i−1][1]，或者前一天结束时还没有股票，即 dp[i−1][0]，
     *    这时候我们要将其买入，并减少 prices[i] 的收益。转移方程：
     *    dp[i][1]=max{dp[i−1][1],dp[i−1][0]−prices[i]}
     */
    public static int maxProfit2(int[] prices, int fee) {
        int n = prices.length;
        int[][] f = new int[n + 1][2];
        f[0][1] = -prices[0];

        for(int i = 0; i < n; i++) {
            // 注意买入不需要手续费，卖出需要手续费
            f[i + 1][0] = Math.max(f[i][0], f[i][1] + prices[i] - fee);
            f[i + 1][1] = Math.max(f[i][1], f[i][0] - prices[i]);
        }

        return f[n][0];
    }

    /**
     * 动态规划，使用二维数据
     */
    public static int maxProfit3(int[] prices, int fee) {
        int f0 = 0, f1 = -prices[0];
        for(int p : prices) {
            int newF0 = Math.max(f0, f1 + p - fee);
            f1 = Math.max(f1, f0 - p);
            f0 = newF0;
        }

        return f0;
    }

    /**
     * 动态规划，使用二维数据
     */
    public static int maxProfit4(int[] prices, int fee) {
        int sell = 0, buy = -prices[0];
        for(int p : prices) {
            sell = Math.max(sell, buy + p - fee);
            buy = Math.max(buy, sell - p);
        }

        return sell;
    }


    /**
     * 贪心算法
     * 1、如果当前的股票价格 prices[i] 加上手续费 fee 小于 buy，那么与其使用 buy 的价格购买股票，我们不如以 prices[i]+fee 的价格购买股票，
     *    因此我们将 buy 更新为 prices[i]+fee；
     * 2、如果当前的股票价格 prices[i] 大于 buy，那么我们直接卖出股票并且获得 prices[i]−buy 的收益。
     *    但实际上，我们此时卖出股票可能并不是全局最优的（例如下一天股票价格继续上升），因此我们可以提供一个反悔操作，看成当前手上拥有一支买入价格为 prices[i] 的股票，
     *    将 buy 更新为 prices[i]。这样一来，如果下一天股票价格继续上升，我们会获得 prices[i+1]−prices[i] 的收益，加上这一天 prices[i]−buy 的收益，
     *    恰好就等于在这一天不进行任何操作，而在下一天卖出股票的收益；
     * 3、对于其余的情况，prices[i] 落在区间 [buy−fee,buy] 内，它的价格没有低到我们放弃手上的股票去选择它，也没有高到我们可以通过卖出获得收益，
     *    因此我们不进行任何操作。
     * 上面的贪心思想可以浓缩成一句话，即当我们卖出一支股票时，我们就立即获得了以相同价格并且免除手续费买入一支股票的权利。
     */
    public static int maxProfit5(int[] prices, int fee) {
        int buy = prices[0] + fee;
        int profit = 0;
        for(int p : prices) {
            if(p + fee < buy) {
                buy = p + fee;
            } else if(p > buy) {
                profit += p - buy;
                buy = p;
            }
        }
        return profit;
    }
}
