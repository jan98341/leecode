package com.jan.title100.title70;

import com.alibaba.fastjson.JSON;

/**
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例 1：
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 *
 * 示例 2：
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 200
 */
public class Title64 {
    public static void main(String[] args) {
        int[][] grid1 = {{1,3,1},{1,5,1},{4,2,1}};
        int[][] grid2 = {{1,2,3},{4,5,6}};

        System.out.println(minPathSum(grid1));
        System.out.println(minPathSum(grid2));

        System.out.println(minPathSum2(grid1));
        System.out.println(minPathSum2(grid2));

        System.out.println(minPathSum3(grid1));
        System.out.println(minPathSum3(grid2));
    }

    /**
     * 使用动态规划方法，使用二维数组进行处理
     */
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] f = new int[m][n];
        f[0][0] = grid[0][0];
        // 第一行处理
        for(int i = 1; i < n; i++) {
            f[0][i] = f[0][i - 1] + grid[0][i];
        }
        // 第一列处理
        for (int j = 1; j < m; j++) {
            f[j][0] = f[j - 1][0] + grid[j][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + grid[i][j];
            }
            System.out.println(JSON.toJSONString(f));
        }
        return f[m - 1][n - 1];
    }

    /**
     * 使用动态规划方法，使用二维数组进行处理
     */
    public static int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] f = new int[m + 1][n + 1];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                f[i + 1][j + 1] = grid[i][j] + Math.min(f[i + 1][j], f[i][j + 1]);
            }
            System.out.println(JSON.toJSONString(f));
        }

        return f[m][n];
    }

    /**
     * 使用动态规划方法，使用一维数组进行处理
     */
    public static int minPathSum3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] f = new int[n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 && j == 0) {
                    f[j] = grid[0][0];
                } else if(i == 0) {
                    f[j] = f[j - 1] + grid[0][j];
                }else if(j == 0) {
                    f[j] = f[j] + grid[i][0];
                } else {
                    f[j] = Math.min(f[j], f[j - 1]) + grid[i][j];
                }
            }
            System.out.println(JSON.toJSONString(f));
        }
        return f[n - 1];
    }
}
