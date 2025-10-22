package com.jan.title1200.title1150;

/**
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 * 示例 1：
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 *
 * 示例 2：
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 *
 * 示例 3：
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 *
 *
 * 提示：
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 */
public class Title1143 {
    public static void main(String[] args) {
        String text11 = "abcde";
        String text12 = "ace";
        String text21 = "abc";
        String text22 = "abc";
        String text31 = "abc";
        String text32 = "def";

        System.out.println(longestCommonSubsequence(text11, text12));
        System.out.println(longestCommonSubsequence(text21, text22));
        System.out.println(longestCommonSubsequence(text31, text32));

        System.out.println(longestCommonSubsequence2(text11, text12));
        System.out.println(longestCommonSubsequence2(text21, text22));
        System.out.println(longestCommonSubsequence2(text31, text32));

        System.out.println(longestCommonSubsequence3(text11, text12));
        System.out.println(longestCommonSubsequence3(text21, text22));
        System.out.println(longestCommonSubsequence3(text31, text32));
    }

    /**
     * 递推，二维数组存储计算数据
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        char[] s = text1.toCharArray();
        char[] t = text2.toCharArray();
        int n = s.length, m = t.length;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(s[i] == t[j]) {
                    f[i + 1][j + 1] = f[i][j] + 1;
                } else {
                    f[i + 1][j + 1] = Math.max(f[i + 1][j], f[i][j + 1]);
                }
            }
        }

        return f[n][m];
    }

    /**
     * 递推，空间优化，两个数组（滚动数组）
     */
    public static int longestCommonSubsequence2(String text1, String text2) {
        char[] s = text1.toCharArray();
        char[] t = text2.toCharArray();
        int n = s.length, m = t.length;
        int[][] f = new int[2][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(s[i] == t[j]) {
                    f[(i + 1)%2][j + 1] = f[i%2][j] + 1;
                } else {
                    f[(i + 1)%2][j + 1] = Math.max(f[(i + 1)%2][j], f[i%2][j + 1]);
                }
            }
        }

        return f[n%2][m];
    }

    /**
     * 递推，空间优化，一个数组
     */
    public static int longestCommonSubsequence3(String text1, String text2) {
        char[] t = text2.toCharArray();
        int m = t.length;
        int[] f = new int[m + 1];
        for(char x : text1.toCharArray()) {
            int pre = 0;
            for(int j = 0; j < m; j++) {
                int tem = f[j + 1];
                f[j + 1] = (x == t[j]) ? pre + 1 : Math.max(f[j + 1], f[j]);
                pre = tem;
            }
        }

        return f[m];
    }
}
