package com.jan.title600.title520;

/**
 * 518. 零钱兑换 II
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。
 * 题目数据保证结果符合 32 位带符号整数。
 *
 * 示例 1：
 * 输入：amount = 5, coins = [1, 2, 5]
 * 输出：4
 * 解释：有四种方式可以凑成总金额：
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * 示例 2：
 * 输入：amount = 3, coins = [2]
 * 输出：0
 * 解释：只用面额 2 的硬币不能凑成总金额 3 。
 *
 * 示例 3：
 * 输入：amount = 10, coins = [10]
 * 输出：1
 *
 * 提示：
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * coins 中的所有值 互不相同
 * 0 <= amount <= 5000
 */
public class Title518 {
    public static void main(String[] args) {
        int[] coins1 = {1, 2, 5};
        int[] coins2 = {2};
        int[] coins3 = {10};

        System.out.println(change(5, coins1));
        System.out.println(change(3, coins2));
        System.out.println(change(10, coins3));

        System.out.println(change2(5, coins1));
        System.out.println(change2(3, coins2));
        System.out.println(change2(10, coins3));

        System.out.println(change3(5, coins1));
        System.out.println(change3(3, coins2));
        System.out.println(change3(10, coins3));
    }

    /**
     * 递推，使用m*n 二维数组
     */
    public static int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] f = new int[n + 1][amount + 1];
        f[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for(int c = 0; c <= amount; c++) {
                if(coins[i] > c) {
                    f[i + 1][c] = f[i][c];
                } else {
                    f[i + 1][c] = f[i][c] + f[i + 1][c - coins[i]];
                }
            }
        }

        return f[n][amount];
    }

    /**
     * 空间优化：两个数组（滚动数组）
     */
    public static int change2(int amount, int[] coins) {
        int n = coins.length;
        int[][] f = new int[2][amount + 1];
        f[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for(int c = 0; c <= amount; c++) {
                if(coins[i] > c) {
                    f[(i + 1) % 2][c] = f[i % 2][c];
                } else {
                    f[(i + 1) % 2][c] = f[i % 2][c] + f[(i + 1) % 2][c - coins[i]];
                }
            }
        }

        return f[n % 2][amount];
    }


    /**
     * 空间优化：一个数组
     */
    public static int change3(int amount, int[] coins) {
        int[] f = new int[amount + 1];
        f[0] = 1;
        for(int x : coins) {
            for(int c = x; c <= amount; c++) {
                f[c] += f[c - x];
            }
        }
        return f[amount];
    }
}
