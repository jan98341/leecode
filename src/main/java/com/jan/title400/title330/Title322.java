package com.jan.title400.title330;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 *
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 *
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 * 提示：
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 */
public class Title322 {
    public static void main(String[] args) {
        int[] coins1 = {1, 2, 5};
        int[] coins2 = {2};
        int[] coins3 = {1};

        System.out.println(coinChange(coins1, 11));
        System.out.println(coinChange(coins2, 3));
        System.out.println(coinChange(coins3, 0));

        System.out.println(coinChange2(coins1, 11));
        System.out.println(coinChange2(coins2, 3));
        System.out.println(coinChange2(coins3, 0));

        System.out.println(coinChange3(coins1, 11));
        System.out.println(coinChange3(coins2, 3));
        System.out.println(coinChange3(coins3, 0));

        System.out.println(coinChange4(coins1, 11));
        System.out.println(coinChange4(coins2, 3));
        System.out.println(coinChange4(coins3, 0));
    }

    /**
     * 递归搜索 + 保存计算结果 = 记忆化搜索
     */
    public static int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] memo = new int[n][amount + 1];
        for(int[] row : memo) {
            // -1 表示没有计算过
            Arrays.fill(row, -1);
        }

        int ans = dfs(n - 1, amount, coins, memo);
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }

    private static int dfs(int i, int c, int[] coins, int[][] memo) {
        if(i < 0) {
            return c == 0 ? 0 : Integer.MAX_VALUE / 2;
        }

        // 之前计算过，使用记忆中的结果
        if(memo[i][c] != -1) {
            return memo[i][c];
        }
        // 剩余金额比当前硬币面额小，直接返回
        if(c < coins[i]) {
            return memo[i][c] = dfs(i - 1, c, coins, memo);
        }
        // 不选 vs 继续选
        return memo[i][c] = Math.min(dfs(i - 1, c, coins, memo), dfs(i, c - coins[i], coins, memo) + 1);
    }

    /**
     * 递推,使用m*n二维数组
     */
    public static int coinChange2(int[] coins, int amount) {
        int n = coins.length;
        int[][] f = new int[n + 1][amount + 1];
        // 除 2 防止后续计算 + 1 溢出
        Arrays.fill(f[0], Integer.MAX_VALUE / 2);
        f[0][0] = 0;
        for(int i = 0; i < n; i++) {
            for(int c = 0; c <= amount; c++) {
                if(c < coins[i]) {
                    // 如果剩余金额比当前硬币面额小，则前一轮计算结果
                    f[i + 1][c] = f[i][c];
                } else {
                    // 如果剩余金额大于等于当前硬币面额，则比较前一轮计算结果和当前计算结果最小值
                    f[i + 1][c] = Math.min(f[i][c], f[i + 1][c - coins[i]] + 1);
                }
            }
        }
        int ans = f[n][amount];
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }

    /**
     * 空间优化：两个数组（滚动数组）
     */
    public static int coinChange3(int[] coins, int amount) {
        int n = coins.length;
        int[][] f = new int[2][amount + 1];
        // 除 2 防止后续计算 + 1 溢出
        Arrays.fill(f[0], Integer.MAX_VALUE / 2);
        f[0][0] = 0;
        for(int i = 0; i < n; i++) {
            for(int c = 0; c <= amount; c++) {
                if(c < coins[i]) {
                    // 如果剩余金额比当前硬币面额小，则前一轮计算结果
                    f[(i + 1) % 2][c] = f[i % 2][c];
                } else {
                    // 如果剩余金额大于等于当前硬币面额，则比较前一轮计算结果和当前计算结果最小值
                    f[(i + 1) % 2][c] = Math.min(f[i % 2][c], f[(i + 1) % 2][c - coins[i]] + 1);
                }
            }
        }

        int ans = f[n%2][amount];
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }

    /**
     * 空间优化：一个数组
     */
    public static int coinChange4(int[] coins, int amount) {
        int[] f = new int[amount + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = 0;
        for(int x : coins) {
            for(int c = x; c <= amount; c++) {
                f[c] = Math.min(f[c], f[c - x] + 1);
            }
        }
        int ans = f[amount];

        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }
}
