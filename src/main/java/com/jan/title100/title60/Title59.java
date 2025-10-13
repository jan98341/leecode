package com.jan.title100.title60;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * 59. 螺旋矩阵 II
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 *
 * 提示：
 * 1 <= n <= 20
 */
public class Title59 {
    private static final int[][] DIR = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(generateMatrix(3)));
        System.out.println(JSON.toJSONString(generateMatrix(1)));
        System.out.println(JSON.toJSONString(generateMatrix2(3)));
        System.out.println(JSON.toJSONString(generateMatrix2(1)));
    }

    public static int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        for(int[] row : ans) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }

        int i = 0, j = 0, di = 0;
        for (int k = 0; k < n * n; k++) {
            ans[i][j] = k + 1;
            int x = i + DIR[di][0], y = j + DIR[di][1];
            if(x < 0 || x >= n || y < 0 || y >= n || ans[x][y] > 0) {
                di = (di + 1) % 4;
            }
            i += DIR[di][0];
            j += DIR[di][1];
        }

        return ans;
    }

    public static int[][] generateMatrix2(int n) {
        // 整型二维数组元素默认为0
        int[][] ans = new int[n][n];
        int i = 0, j = 0, di = 0;
        for (int k = 1; k <= n * n; k++) {
            ans[i][j] = k;
            int x = i + DIR[di][0], y = j + DIR[di][1];
            // 当遇到边界或者遇到已经赋值的格子进行转向
            if(x < 0 || x >= n || y < 0 || y >= n || ans[x][y] != 0) {
                di = (di + 1) % 4;
            }
            i += DIR[di][0];
            j += DIR[di][1];
        }

        return ans;
    }
}
