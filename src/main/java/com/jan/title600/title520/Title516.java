package com.jan.title600.title520;

import java.util.Arrays;

/**
 * 516. 最长回文子序列
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *
 * 示例 1：
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出：2
 * 解释：一个可能的最长回文子序列为 "bb" 。
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由小写英文字母组成
 */
public class Title516 {
    public static void main(String[] args) {
        String s1 = "bbbab";
        String s2 = "cbbd";

        System.out.println(longestPalindromeSubseq(s1));
        System.out.println(longestPalindromeSubseq(s2));

        System.out.println(longestPalindromeSubseq2(s1));
        System.out.println(longestPalindromeSubseq2(s2));
    }

    /**
     * 状态转移方程
     * dfs[i][j] = f[i+1][j-1]+2              s[i]==s[j]
     *           Max(f[i+1][j], f[i][j-1])   s[i]!=s[j]
     * 边界条件当i==j，则f[i][i]=1
     */
    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        char[] c = s.toCharArray();
        int[][] memo = new int[n][n];
        for(int[] row : memo){
            Arrays.fill(row, -1);
        }
        return dfs(0, n - 1, c, memo);
    }

    private static int dfs(int i, int j, char[] s, int[][] memo) {
        if(i > j) {
            return 0;
        }
        if(i == j) {
            return 1;
        }
        if(memo[i][j] != -1) {
            return memo[i][j];
        }
        if(s[i] == s[j]) {
            return memo[i][j] = dfs(i + 1, j - 1, s, memo) + 2;
        } else {
            return memo[i][j] = Math.max(dfs(i + 1, j, s, memo), dfs(i, j - 1, s, memo));
        }
    }

    /**
     * 状态转移方程
     * dfs[i][j] = f[i+1][j-1]+2              s[i]==s[j]
     *           Max(f[i+1][j], f[i][j-1])   s[i]!=s[j]
     * 边界条件当i==j，则f[i][i]=1
     */
    public static int longestPalindromeSubseq2(String s) {
        int n = s.length();
        char[] c = s.toCharArray();
        int[][] f = new int[n][n];
        for(int i = n - 1; i >= 0; i--) {
            //i≥j 是递归边界，j==i时f[i][j]=1,其他j在i的右边
            f[i][i] = 1;
            for(int j = i + 1; j < n; j++) {
                if(c[i] == c[j]) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                } else {
                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                }
            }
        }
        return f[0][n - 1];
    }
}
