package com.jan.titlemorethan1000;

/**
 * 1312. 让字符串成为回文串的最少插入次数
 * 给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。
 * 请你返回让 s 成为回文串的 最少操作次数 。
 * 「回文串」是正读和反读都相同的字符串。
 *
 * 示例 1：
 * 输入：s = "zzazz"
 * 输出：0
 * 解释：字符串 "zzazz" 已经是回文串了，所以不需要做任何插入操作。
 *
 * 示例 2：
 * 输入：s = "mbadm"
 * 输出：2
 * 解释：字符串可变为 "mbdadbm" 或者 "mdbabdm" 。
 *
 * 示例 3：
 * 输入：s = "leetcode"
 * 输出：5
 * 解释：插入 5 个字符后字符串变为 "leetcodocteel" 。
 *
 * 提示：
 * 1 <= s.length <= 500
 * s 中所有字符都是小写字母。
 */
public class Title1312 {
    public static void main(String[] args) {
        String s1 = "zzazz";
        String s2 = "mbadm";
        String s3 = "leetcode";

        System.out.println(minInsertions(s1));
        System.out.println(minInsertions(s2));
        System.out.println(minInsertions(s3));

        System.out.println(minInsertions2(s1));
        System.out.println(minInsertions2(s2));
        System.out.println(minInsertions2(s3));
    }

    /**
     * 解题思路，要求回文串的最少插入次数转化为先求字符串最长回文长度，通过字符串长度-字符串最长回文长度就是答案
     */
    public static int minInsertions(String s) {
        int n = s.length();
        char[] c = s.toCharArray();
        int[][] f = new int[n][n];
        // f[i][.]是从f[i+1][.]状态变化而来，所以需要倒序处理；f[.][j]是从f[j-1][j]状态变化而来，所以需要正序处理
        for(int i = n - 1; i >= 0; i--) {
            //i≥j 是递归边界，j==i时f[i][j]=1,其他j在i的右边
            f[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if(c[i] == c[j]) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                } else {
                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                }
            }
        }
        return n - f[0][n - 1];
    }

    /**
     * 解题思路，区间动态规划，二维数组保存f状态，f[i][j]表示下标i到下标j的字符串的最少插入次数
     * f分两种情况：
     * 1、s.charAt(i)==s.charAt(j) 此时不需要计算，取其内部字符串的值就行
     * 2、s.charAt(i)！=s.charAt(j) 分两种情况，选择较小值
     */
    public static int minInsertions2(String s) {
        int n = s.length();
        int[][] f = new int[n][n];

        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1];
                } else {
                    f[i][j] = Math.min(f[i + 1][j], f[i][j - 1]) + 1;
                }
            }
        }
        return f[0][n - 1];
    }
}
