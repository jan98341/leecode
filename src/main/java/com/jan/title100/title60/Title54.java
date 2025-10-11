package com.jan.title100.title60;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class Title54 {
    public static final int[][] DIR = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static void main(String[] args) {
        int[][] matrix1 ={{1,2,3}, {4,5,6},{7,8,9}};
        int[][] matrix2 ={{1,2,3,4}, {5,6,7,8},{9,10,11,12}};

        System.out.println(spiralOrder(matrix1));
        System.out.println(spiralOrder(matrix2));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = 0, di = 0;
        for(int k = 0; k < m * n; k++) {
            ans.add(matrix[i][j]);
            matrix[i][j] = Integer.MAX_VALUE;
            int x = i + DIR[di][0];
            int y = j + DIR[di][1];
            if(x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] == Integer.MAX_VALUE) {
                di = (di + 1) % 4;
            }
            i += DIR[di][0];
            j += DIR[di][1];
        }

        return ans;
    }
}
