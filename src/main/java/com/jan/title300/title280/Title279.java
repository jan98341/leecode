package com.jan.title300.title280;

import java.util.Arrays;

/**
 * 279. 完全平方数
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 * 示例 1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 *
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *
 * 提示：
 * 1 <= n <= 10^4
 */
public class Title279 {

    public static void main(String[] args) {
        System.out.println(numSquares(12));
        System.out.println(numSquares(13));

        System.out.println(numSquares2(12));
        System.out.println(numSquares2(13));

        System.out.println(numSquares3(12));
        System.out.println(numSquares3(13));
    }

    private static final int N = 10000;

    /**
     * 记忆化搜索
     */
    private static int[][] memo = new int[101][N + 1];
    static {
        for(int[] row : memo) {
            Arrays.fill(row, -1);
        }
    }
    private static int dfs(int i, int j) {
        if( i == 0) {
            return j == 0 ? 0 : Integer.MAX_VALUE;
        }
        if(memo[i][j] != -1) {
            return memo[i][j];
        }
        if(j < i * i) {
            return memo[i][j] = dfs(i - 1, j);
        }
        return memo[i][j] = Math.min(dfs(i - 1, j), dfs(i, j - i * i) +1);
    }
    public static int numSquares(int n) {
        return dfs((int)Math.sqrt(n), n);
    }

    /**
     * 递推，使用二维数组
     */
    private static int[][] f = new int[101][N + 1];
    static {
        Arrays.fill(f[0], Integer.MAX_VALUE);
        f[0][0] = 0;
        for(int i = 1; i * i <= N; i++) {
            for(int c = 0; c <= N; c++) {
                if(i * i > c) {
                    f[i][c] = f[i - 1][c];
                } else {
                    f[i][c] = Math.min(f[i - 1][c], f[i][c - i * i] + 1) ;
                }
            }
        }
    }
    public static int numSquares2(int n) {
        return f[(int)Math.sqrt(n)][n];
    }

    /**
     * 递推，空间优化，使用一维数组
     */
    private static int[] f2 = new int[N + 1];
    private static boolean initialized = false;
    private static void init() {
        if(initialized) {
            return;
        }
        initialized = true;
        Arrays.fill(f2, Integer.MAX_VALUE);
        f2[0] = 0;
        for(int i = 1; i * i <= N; i++) {
            for(int c = i * i; c <= N; c++) {
                f2[c] = Math.min(f2[c], f2[c - i * i] + 1) ;
            }
        }

    }
    public static int numSquares3(int n) {
        init();
        return f2[n];
    }
}
