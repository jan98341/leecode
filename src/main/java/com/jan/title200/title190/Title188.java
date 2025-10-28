package com.jan.title200.title190;

import java.util.Arrays;

/**
 * 188. 买卖股票的最佳时机 IV
 * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1：
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 *
 * 示例 2：
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 * 提示：
 * 1 <= k <= 100
 * 1 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 */
public class Title188 {
    public static void main(String[] args) {
        int[] prices1 = {2,4,1};
        int[] prices2 = {3,2,6,5,0,3};

        System.out.println(maxProfit(2, prices1));
        System.out.println(maxProfit(2, prices2));

        System.out.println(maxProfit2(2, prices1));
        System.out.println(maxProfit2(2, prices2));
    }

    /**
     * 递推，使用二维数组
     */
    public static int maxProfit(int k, int[] prices) {
        int n = prices.length;

        int[][][] f = new int[n + 1][k + 2][2];
        for(int[][] mat : f) {
            for(int[] row : mat) {
                Arrays.fill(row, Integer.MIN_VALUE / 2);
            }
        }
        for(int j = 1; j <= k + 1; j++) {
            f[0][j][0] = 0;
        }

        for(int i = 0; i < n; i++) {
            for(int j = 1; j <= k + 1; j++) {
                f[i + 1][j][0] = Math.max(f[i][j][0], f[i][j][1] + prices[i]);
                f[i + 1][j][1] = Math.max(f[i][j][1], f[i][j - 1][0] - prices[i]);
            }
        }

        return f[n][k + 1][0];
    }

    /**
     * 递推，使用一维数组
     */
    public static int maxProfit2(int k, int[] prices) {
        int[][] f = new int[k + 2][2];
        for(int j = 1; j <= k + 1; j++) {
            f[j][1] = Integer.MIN_VALUE / 2;
        }
        f[0][0] = Integer.MIN_VALUE / 2;
        for(int p : prices) {
            for(int j = k + 1; j > 0; j--) {
                f[j][0] = Math.max(f[j][0], f[j][1] + p);
                f[j][1] = Math.max(f[j][1], f[j - 1][0] - p);
            }
        }

        return f[k + 1][0];
    }
}
