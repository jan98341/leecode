package com.jan.title100.title70;

import com.alibaba.fastjson.JSON;

/**
 * 63. 不同路径 II
 * 给定一个 m x n 的整数数组 grid。一个机器人初始位于 左上角（即 grid[0][0]）。机器人尝试移动到 右下角（即 grid[m - 1][n - 1]）。机器人每次只能向下或者向右移动一步。
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。机器人的移动路径中不能包含 任何 有障碍物的方格。
 * 返回机器人能够到达右下角的不同路径数量。
 * 测试用例保证答案小于等于 2 * 109。
 *
 * 示例 1：
 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 * 示例 2：
 * 输入：obstacleGrid = [[0,1],[0,0]]
 * 输出：1
 *
 * 提示：
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] 为 0 或 1
 */
public class Title63 {
    public static void main(String[] args) {
        int[][] obstacleGrid1 = {{0,0,0},{0,1,0},{0,0,0}};
        int[][] obstacleGrid2 = {{0,1},{0,0}};
        int[][] obstacleGrid3 = {{0,0,0,0},{0,0,0,0},{0,0,0,1},{0,0,1,0}};
        int[][] obstacleGrid4 = {{0,0}};

//        System.out.println(uniquePathsWithObstacles(obstacleGrid1));
//        System.out.println(uniquePathsWithObstacles(obstacleGrid2));
//        System.out.println(uniquePathsWithObstacles(obstacleGrid3));

        System.out.println(uniquePathsWithObstacles2(obstacleGrid1));
        System.out.println(uniquePathsWithObstacles2(obstacleGrid2));
        System.out.println(uniquePathsWithObstacles2(obstacleGrid3));
        System.out.println(uniquePathsWithObstacles2(obstacleGrid4));

//        System.out.println(uniquePathsWithObstacles3(obstacleGrid1));
//        System.out.println(uniquePathsWithObstacles3(obstacleGrid2));
//        System.out.println(uniquePathsWithObstacles3(obstacleGrid3));

        System.out.println("------------");
        System.out.println(uniquePathsWithObstacles4(obstacleGrid1));
        System.out.println(uniquePathsWithObstacles4(obstacleGrid2));
        System.out.println(uniquePathsWithObstacles4(obstacleGrid3));
        System.out.println(uniquePathsWithObstacles4(obstacleGrid4));
    }

    /**
     * 动态规划，如果依赖前一步骤存在障碍物，则障碍物方向dp值为0
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] a = new int[m][n];
        a[0][0] = (obstacleGrid[0][0] == 1 ? 0 : 1);
        for (int i = 1; i < m; i++) {
            a[i][0] = a[i - 1][0] & (obstacleGrid[i][0] == 1 ? 0 : 1);
        }
        for (int j = 1; j < n; j++) {
            a[0][j] = (obstacleGrid[0][j] == 1 ? 0 : 1) & a[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
//                a[i][j] = ((obstacleGrid[i - 1][j] == 1) ? 0 : a[i - 1][j])
//                        + ((obstacleGrid[i][j - 1] == 1) ? 0 : a[i][j - 1]);

//                if (obstacleGrid[i][j] == 1) {
//                    a[i][j] = 0;
//                } else {
//                    a[i][j] = a[i - 1][j] + a[i][j - 1];
//                }
                a[i][j] = (obstacleGrid[i][j] == 1) ? 0 : (a[i - 1][j] + a[i][j - 1]);
            }
        }
        return a[m - 1][n - 1];
    }

    /**
     * 动态规划，递推方法，使用(m+1)*(n+1)二维数组进行存储信息
     */
    public static int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] f = new int[m + 1][n + 1];
        f[0][1] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(obstacleGrid[i][j] == 0) {
                    f[i + 1][j + 1] = f[i][j + 1] + f[i + 1][j];
                }
            }
        }

        return f[m][n];
    }

    /**
     * 动态规划方法，使用一维数组存放结果
     */
    public static int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] f = new int[n];
        // 设置起始点值
        f[0] = (obstacleGrid[0][0] == 1 ? 0 : 1);
        // 先计算第一行值，每个元素值 = 左边元素值 & 当前是否障碍物
        for (int i = 1; i < n; i++) {
            f[i] = (obstacleGrid[0][i] == 1 ? 0 : 1) & f[i - 1];
        }
        for(int i = 1; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(j == 0) {
                    // 每行首元素值 = 上方元素值 & 当前是否障碍物
                    f[j] = (obstacleGrid[i][j] == 1 ? 0 : 1) & f[j];
                } else {
                    // 非行首元素值 = 如果当前是障碍物则为0，否则为左元素值+上方元素值
                    f[j] = (obstacleGrid[i][j] == 1 ? 0 : (f[j - 1] + f[j]));
                }
            }
        }

        return f[n - 1];
    }


    /**
     * 动态规划方法，使用一维数组存放结果
     */
    public static int uniquePathsWithObstacles4(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] f = new int[n];
        f[0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
//                if(obstacleGrid[i][j] == 1) {
//                    f[j] = 0;
//                    continue;
//                }
//                // j=0时，如果有障碍物则值为0，否则继承上一行的值
//                // j>0时，如果有障碍物则值为0，否则上一行的值+左方向值
//                if(j > 0  && obstacleGrid[i][j] == 0) {
//                    f[j] += f[j - 1];
//                }
                // j=0时，如果有障碍物则值为0，否则继承上一行的值
                // j>0时，如果有障碍物则值为0，否则上一行的值+左方向值
                if(obstacleGrid[i][j] == 1) {
                    f[j] = 0;
                } else if(j > 0  && obstacleGrid[i][j] == 0) {
                    f[j] += f[j - 1];
                }
            }
        }

        return f[n - 1];
    }
}
