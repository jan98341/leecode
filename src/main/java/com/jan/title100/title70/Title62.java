package com.jan.title100.title70;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 *
 * 示例 1：
 * 输入：m = 3, n = 7
 * 输出：28
 *
 * 示例 2：
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 *
 * 示例 3：
 * 输入：m = 7, n = 3
 * 输出：28
 *
 * 示例 4：
 * 输入：m = 3, n = 3
 * 输出：6
 *
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10^9
 */
public class Title62 {
    public static void main(String[] args) {
//        System.out.println(uniquePaths(3,7));
//        System.out.println(uniquePaths(3,2));
//        System.out.println(uniquePaths(7,3));
//        System.out.println(uniquePaths(3,3));
//
//        System.out.println(uniquePaths2(3,7));
//        System.out.println(uniquePaths2(3,2));
//        System.out.println(uniquePaths2(7,3));
//        System.out.println(uniquePaths2(3,3));
//
//        System.out.println(uniquePaths3(3,7));
//        System.out.println(uniquePaths3(3,2));
//        System.out.println(uniquePaths3(7,3));
//        System.out.println(uniquePaths3(3,3));
//
        System.out.println(uniquePaths4(3,7));
        System.out.println(uniquePaths4(3,2));
        System.out.println(uniquePaths4(7,3));
        System.out.println(uniquePaths4(3,3));

        System.out.println(uniquePaths5(3,7));
        System.out.println(uniquePaths5(3,2));
        System.out.println(uniquePaths5(7,3));
        System.out.println(uniquePaths5(3,3));

        System.out.println(uniquePaths6(3,7));
        System.out.println(uniquePaths6(3,2));
        System.out.println(uniquePaths6(7,3));
        System.out.println(uniquePaths6(3,3));
    }

    /**
     * 动态规划方法，使用m*n二维数组进行存储信息
     */
    public static int uniquePaths(int m, int n) {
        int[][] a = new int[m][n];
        // 设置起点值
        a[0][0] = 1;
        // 设置边界只有一种走法
        for(int i = 0; i < m; i++) {
            a[i][0] = 1;
        }
        for(int j = 0; j < n; j++) {
            a[0][j] = 1;
        }

        // 通过动态规划方程，dp[i][j]=dp[i][j-1]+dp[i-1][j]推导如下公式
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                a[i][j] = a[i - 1][j] + a[i][j - 1];
            }
        }

        return a[m - 1][n - 1];
    }

    /**
     * 动态规划，使用(m+1)*(n+1)二维数组进行存储信息
     */
    public static int uniquePaths2(int m, int n) {
        int[][] f = new int[m + 1][n + 1];
        f[0][1] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                f[i + 1][j + 1] = f[i][j + 1] + f[i + 1][j];
            }
        }
        return f[m][n];
    }

    /**
     * 动态规划方法，使用一维数组存放结果
     */
    public static int uniquePaths3(int m, int n) {
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = 1;
        }
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                a[j] += a[j - 1];
            }
        }
        return a[n - 1];
    }

    /**
     * 动态规划，使用(n+1)一维数组进行存储信息
     */
    public static int uniquePaths4(int m, int n) {
        int[] f = new int[n + 1];
        f[1] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                f[j + 1] += f[j];
            }
        }
        return f[n];
    }

    /**
     * 组合数学
     */
    public static int uniquePaths5(int m, int n) {
        return comb(m + n - 2, m - 1);
    }

    // C = n!/m!(n-m)! = n*...(n+1-m)/1*2*3...*m
    private static int comb(int n, int k) {
        k = Math.min(k, n - k);
        long ans = 1;
        for (int i = 1; i <= k; i++) {
            ans = ans * (n + 1 - i) / i;
        }
        return (int) ans;
    }

    /**
     * 组合数学2
     */
    public static int uniquePaths6(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; x++, y++) {
            ans = ans * x / y;
        }
        return (int)ans;
    }
}
