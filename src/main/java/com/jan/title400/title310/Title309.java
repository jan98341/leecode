package com.jan.title400.title310;

import java.util.Arrays;

/**
 * 309. 买卖股票的最佳时机含冷冻期
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * 示例 2:
 * 输入: prices = [1]
 * 输出: 0
 *
 * 提示：
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 */
public class Title309 {
    public static void main(String[] args) {
        int[] prices1 = {1,2,3,0,2};
        int[] prices2 = {1};

        System.out.println(maxProfit(prices1));
        System.out.println(maxProfit(prices2));

        System.out.println(maxProfit2(prices1));
        System.out.println(maxProfit2(prices2));

        System.out.println(maxProfit3(prices1));
        System.out.println(maxProfit3(prices2));
    }

    /**
     * 递归搜索 + 保存计算结果 = 记忆化搜索
     */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] memo = new int[n][2];
        for(int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dfs(n - 1, 0, memo, prices);
    }

    private static int dfs(int i, int hold, int[][] memo, int[] prices) {
        if(i < 0) {
            return hold == 1 ? Integer.MIN_VALUE : 0;
        }
        if(memo[i][hold] != -1) {
            return memo[i][hold];
        }

        if(hold == 1) {
            return memo[i][1] = Math.max(dfs(i - 1, 1, memo, prices), dfs(i - 2, 0, memo, prices) - prices[i]);
        } else {
            return memo[i][0] = Math.max(dfs(i - 1, 0, memo, prices), dfs(i - 1, 1, memo, prices) + prices[i]);
        }
    }

    /**
     * 动态规划，使用二维数据
     */
    public static int maxProfit2(int[] prices) {
        int n = prices.length;
        int[][] f = new int[n + 2][2];
        f[1][1] = Integer.MIN_VALUE / 2;
        for(int i = 0; i < n; i++) {
            f[i + 2][0] = Math.max(f[i + 1][0], f[i + 1][1] + prices[i]);
            f[i + 2][1] = Math.max(f[i][0] - prices[i], f[i + 1][1]);
        }

        return f[n + 1][0];
    }

    /**
     * 动态规划，空间优化，使用三个变量
     */
    public static int maxProfit3(int[] prices) {
        int pre0 = 0, f0 = 0, f1 = Integer.MIN_VALUE /2;
        for(int p : prices) {
            int newF0 = Math.max(f0, f1 + p);   // f[i+2][0]
            f1 = Math.max(f1, pre0 - p);        // f[i+2][1]
            pre0 = f0;
            f0 = newF0;
        }

        return f0;
    }
}
