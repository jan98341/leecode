package com.jan.title100.title100;

import java.util.Arrays;

/**
 * 97. 交错字符串
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接。
 *
 * 示例 1：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 *
 * 示例 2：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 *
 * 示例 3：
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 *
 * 提示：
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1、s2、和 s3 都由小写英文字母组成
 *
 * 进阶：您能否仅使用 O(s2.length) 额外的内存空间来解决它?
 */
public class Title97 {
    public static void main(String[] args) {
        String s11 = "aabcc", s12 = "dbbca", s13 = "aadbbcbcac";
        String s21 = "aabcc", s22 = "dbbca", s23 = "aadbbbaccc";
        String s31 = "", s32 = "", s33 = "";

        System.out.println(isInterleave(s11, s12, s13));
        System.out.println(isInterleave(s21, s22, s23));
        System.out.println(isInterleave(s31, s32, s33));

        System.out.println(isInterleave2(s11, s12, s13));
        System.out.println(isInterleave2(s21, s22, s23));
        System.out.println(isInterleave2(s31, s32, s33));

        System.out.println(isInterleave3(s11, s12, s13));
        System.out.println(isInterleave3(s21, s22, s23));
        System.out.println(isInterleave3(s31, s32, s33));

        System.out.println(isInterleave4(s11, s12, s13));
        System.out.println(isInterleave4(s21, s22, s23));
        System.out.println(isInterleave4(s31, s32, s33));
    }

    /**
     * 递归搜索 + 保存递归返回值 = 记忆化搜索
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        char[] S1 = s1.toCharArray(), S2 = s2.toCharArray(), S3 = s3.toCharArray();
        int n = S1.length, m = S2.length;
        if ((n + m) != S3.length) {
            return false;
        }

        int[][] f = new int[n + 1][m + 1];
        for(int[] row : f) {
            Arrays.fill(row, -1);  // -1 表示没有计算过
        }

        return dfs(n - 1, m - 1, S1, S2, S3, f);
    }

    private static boolean dfs(int i, int j, char[] s1, char[] s2, char[] s3, int[][] memo) {
        if(i < 0 || j < 0) {
            return false;
        }
        if(memo[i + 1][j + 1] != -1) {
            return memo[i + 1][j + 1] == 1;
        }

        boolean res = (i >= 0 && s1[i] == s3[i + j + 1] && dfs(i - 1, j, s1, s2, s3, memo)) ||
                (j >= 0 && s2[j] == s3[i + j + 1] && dfs(i, j - 1, s1, s2, s3, memo));
        memo[i + 1][j + 1] = res ? 1 : 0; // 记忆化
        return res;
    }

    /**
     * 动态规划转移方程f(i,j)=[f(i−1,j) and s1(i−1)=s3(p)]or[f(i,j−1)and s2(j−1)=s3(p)], 其中 p=i+j−1
     */
    public static boolean isInterleave2(String s1, String s2, String s3) {
        char[] S1 = s1.toCharArray(), S2 = s2.toCharArray(), S3 = s3.toCharArray();
        int n = S1.length, m = S2.length, k = S3.length;
        if((n + m) != k) {
            return false;
        }

        boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;
        for(int j = 0; j < m; j++) {
            f[0][j + 1] = f[0][j] && (S2[j] == S3[j]);
        }
        for(int i = 0; i < n; i++) {
            f[i + 1][0] = f[i][0] && (S1[i] == S3[i]);
            for(int j = 0; j < m; j++) {
                f[i + 1][j + 1] = (f[i + 1][j] && (S2[j] == S3[i + j + 1])) || (f[i][j + 1] && (S1[i] == S3[i + j + 1]));
            }
        }

        return f[n][m];
    }

    /**
     * 动态规划，空间优化，使用两组一维数据
     */
    public static boolean isInterleave3(String s1, String s2, String s3) {
        char[] S1 = s1.toCharArray(), S2 = s2.toCharArray(), S3 = s3.toCharArray();
        int n = S1.length, m = S2.length, k = S3.length;
        if((n + m) != k) {
            return false;
        }

        boolean[][] f = new boolean[2][m + 1];
        f[0][0] = true;
        for(int j = 0; j < m; j++) {
            f[0][j + 1] = f[0][j] && (S2[j] == S3[j]);
        }

        for(int i = 0; i < n; i++) {
            f[(i + 1) % 2][0] = f[i % 2][0] && (S1[i] == S3[i]);
            for(int j = 0; j < m; j++) {
                f[(i + 1) % 2][j + 1] = (f[(i + 1) % 2][j] && (S2[j] == S3[i + j + 1])) || (f[i % 2][j + 1] && (S1[i] == S3[i + j + 1]));
            }
        }

        return f[n % 2][m];
    }

    /**
     * 动态规划，空间优化，使用一维数据
     */
    public static boolean isInterleave4(String s1, String s2, String s3) {
        char[] S1 = s1.toCharArray(), S2 = s2.toCharArray(), S3 = s3.toCharArray();
        int n = S1.length, m = S2.length;
        if ((n + m) != S3.length) {
            return false;
        }

        boolean[] f = new boolean[m + 1];
        f[0] = true;
        for (int j = 0; j < m; j++) {
            f[j + 1] = f[j] && (S2[j] == S3[j]);
        }

        for(int i = 0; i < n; i++) {
            f[0] = f[0] && (S1[i] == S3[i]);
            for(int j = 0; j < m; j++) {
                f[j + 1] = (f[j] && (S2[j] == S3[i + j + 1])) || (f[j + 1] && (S1[i] == S3[i + j + 1]));
            }
        }
        return f[m];
    }
}
