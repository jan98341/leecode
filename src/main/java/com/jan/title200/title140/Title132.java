package com.jan.title200.title140;

import java.util.Arrays;

/**
 * 132. 分割回文串 II
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串。
 * 返回符合要求的 最少分割次数 。
 *
 * 示例 1：
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 *
 * 示例 2：
 * 输入：s = "a"
 * 输出：0
 *
 * 示例 3：
 * 输入：s = "ab"
 * 输出：1
 *
 * 提示：
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 */
public class Title132 {
    public static void main(String[] args) {
        String s1 = "aab";
        String s2 = "a";
        String s3 = "ab";

        System.out.println(minCut(s1));
        System.out.println(minCut(s2));
        System.out.println(minCut(s3));

        System.out.println(minCut2(s1));
        System.out.println(minCut2(s2));
        System.out.println(minCut2(s3));
    }

    /**
     * 递归搜索 + 保存递归返回值 = 记忆化搜索
     * dfs(r)=min(dfs(l−1))+1 ，其中min的l从1->r
     */
    public static int minCut(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int[][] palMemo = new int[n][n];
        for(int[] row : palMemo) {
            // -1 表示没有计算过
            Arrays.fill(row, -1);
        }
        int[] dfsMemo = new int[n];
        Arrays.fill(dfsMemo, -1);
        return dfs(n - 1, c, palMemo, dfsMemo);
    }

    private static int dfs(int r, char[] s, int[][] palMemo, int[] dfsMemo) {
        if(isPalindrome(0, r, s, palMemo)) {
            return 0;
        }
        if(dfsMemo[r] != -1) {
            return dfsMemo[r];
        }
        int res = Integer.MAX_VALUE;
        for(int i = 1; i <= r; i++) {
            if(isPalindrome(i, r, s, palMemo)) {
                // 在 l-1 和 l 之间切一刀
                res = Math.min(res, dfs(i - 1, s, palMemo, dfsMemo) + 1);
            }
        }
        // 记忆化
        return dfsMemo[r] = res;
    }

    private static boolean isPalindrome(int l, int r, char[] s, int[][] palMemo) {
        if(l >= r) {
            return true;
        }
        if(palMemo[l][r] != -1) {
            // 之前计算过
            return palMemo[l][r] == 1;
        }
        boolean res = (s[l] == s[r]) && (isPalindrome(l + 1, r - 1, s, palMemo));
        palMemo[l][r] = res ? 1 : 0;
        return res;
    }


    /**
     * 递归搜索 + 保存递归返回值 = 记忆化搜索
     * f(r)=min(f(l−1))+1 ，其中min的l从1->r
     */
    public static int minCut2(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        // isPalindrome[l][r] 表示 s[l] 到 s[r] 是否为回文串
        boolean[][] isPalindrome = new boolean[n][n];
        for (boolean[] row : isPalindrome) {
            Arrays.fill(row, true);
        }

        for(int i = n - 2; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                isPalindrome[i][j] = (c[i] == c[j]) && (isPalindrome[i + 1][j - 1]);
            }
        }

        int[] f = new int[n];
        for(int r = 0; r < n; r++) {
            if(isPalindrome[0][r]) {
                continue;
            }
            int res = Integer.MAX_VALUE;
            for(int l = 1; l <= r; l++) {
                if(isPalindrome[l][r]) {
                    res = Math.min(res, f[l - 1] + 1);
                }
            }
            f[r] = res;
        }
        return f[n -1];
    }
}
