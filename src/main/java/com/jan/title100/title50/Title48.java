package com.jan.title100.title50;

import com.alibaba.fastjson.JSON;

/**
 * 48. 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 *
 * 示例 2：
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 * 提示：
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 *
 *
 * 矩阵转置:matrix[i][j] ↔ matrix[j][i]
 * 矩阵水平镜像:matrix[i][j] ↔ matrix[i][n-1-j]
 * 矩阵垂直镜像:matrix[i][j] ↔ matrix[m-1-i][j]
 * 矩阵顺时针旋转90度， new_matrix[j][n-1-i] = matrix[i][j],就相当于垂直镜像+转置
 * 矩阵顺时针旋转180度，new_matrix[n-1-i][n-1-j] = matrix[i][j],就相当于水平镜像+垂直镜像
 * 矩阵逆时针旋转90度，new_matrix[n-1-j][i] = matrix[i][j],就相当于水平镜像+转置
 */
public class Title48 {
    public static void main(String[] args) {
        int[][] matrix1 = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix2 = new int[][]{{5,1,9,11}, {2,4,8,10}, {13,3,6,7}, {15,14,12,16}};
//        rotate(matrix1);
//        rotate(matrix2);
        rotate2(matrix1);
        rotate2(matrix2);
        System.out.println(JSON.toJSONString(matrix1));
        System.out.println(JSON.toJSONString(matrix2));

    }

    public static void rotate(int[][] matrix) {
        int l = matrix.length;
        // 先进行转置
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 再进行水平翻转
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][l - j - 1];
                matrix[i][l - j - 1] = temp;
            }
        }
    }

    public static void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            int[] row = matrix[i];
            for (int j = i + 1; j < n; j++) { // 遍历对角线上方元素，做转置
                int tmp = row[j];
                row[j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
            for (int j = 0; j < n / 2; j++) { // 遍历左半元素，做行翻转
                int tmp = row[j];
                row[j] = row[n - 1 - j];
                row[n - 1 - j] = tmp;
            }
        }
    }

}
