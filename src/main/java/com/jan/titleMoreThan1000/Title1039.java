package com.jan.titleMoreThan1000;

import java.util.Arrays;

/**
 * 1039. 多边形三角剖分的最低得分
 * 你有一个凸的 n 边形，其每个顶点都有一个整数值。给定一个整数数组 values ，其中 values[i] 是按 顺时针顺序 第 i 个顶点的值。
 * 假设将多边形 剖分 为 n - 2 个三角形。对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 n - 2 个三角形的值之和。
 * 返回 多边形进行三角剖分后可以得到的最低分 。
 *
 * 示例 1：
 * 输入：values = [1,2,3]
 * 输出：6
 * 解释：多边形已经三角化，唯一三角形的分数为 6。
 *
 * 示例 2：
 * 输入：values = [3,7,4,5]
 * 输出：144
 * 解释：有两种三角剖分，可能得分分别为：3*7*5 + 4*5*7 = 245，或 3*4*5 + 3*4*7 = 144。最低分数为 144。
 *
 * 示例 3：
 * 输入：values = [1,3,1,4,1,5]
 * 输出：13
 * 解释：最低分数三角剖分的得分情况为 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13。
 *
 * 提示：
 * n == values.length
 * 3 <= n <= 50
 * 1 <= values[i] <= 100
 */
public class Title1039 {
    public static void main(String[] args) {
        int[] values1 = {1,2,3};
        int[] values2 = {3,7,4,5};
        int[] values3 = {1,3,1,4,1,5};

        System.out.println(minScoreTriangulation(values1));
        System.out.println(minScoreTriangulation(values2));
        System.out.println(minScoreTriangulation(values3));

        System.out.println(minScoreTriangulation2(values1));
        System.out.println(minScoreTriangulation2(values2));
        System.out.println(minScoreTriangulation2(values3));
    }

    /**
     * 记忆化搜索
     * i,j,k分别为三角形三个边顶点序号，k从i+1变化到j-1,转移方程如下：
     * dfs(i,j) = Min(dfs(i,k)+dfs(k,j)+v[i]*v[j][k])
     * 边界条件 dfs(i,i+1)=0
     */
    public static int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] memo = new int[n][n];
        for(int[] row : memo){
            Arrays.fill(row, -1);
        }
        return dfs(0, n - 1, values, memo);
    }

    private static int dfs(int i, int j, int[] v, int[][] memo) {
        if(i + 1 == j) {
            return 0;
        }
        if(memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = Integer.MAX_VALUE;
        for(int k = i + 1; k < j; k++) {
            int t = dfs(i, k, v, memo) + dfs(k, j, v, memo) + v[i] * v[k] * v[j];
            res = Math.min(res, t);
        }
        return res;
    }

    /**
     * 动态规划
     * i,j,k分别为三角形三个边顶点序号，k从i+1变化到j-1,转移方程如下：
     * dfs(i,j) = Min(dfs(i,k)+dfs(k,j)+v[i]*v[j][k])   其中k=i+1 -> k=j-1
     * 边界条件 dfs(i,i+1)=0
     */
    public static int minScoreTriangulation2(int[] values) {
        int n = values.length;
        int[][] f = new int[n][n];
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 2; j < n; j++) {
                f[i][j] = Integer.MAX_VALUE;
                for(int k = i + 1; k < j; k++) {
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k][j] + values[i] * values[j] * values[k]);
                }
            }
        }
        return f[0][n - 1];
    }
}
