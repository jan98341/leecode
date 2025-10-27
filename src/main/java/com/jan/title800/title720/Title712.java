package com.jan.title800.title720;

/**
 * 712. 两个字符串的最小ASCII删除和
 * 给定两个字符串s1 和 s2，返回 使两个字符串相等所需删除字符的 ASCII 值的最小和 。
 *
 * 示例 1:
 * 输入: s1 = "sea", s2 = "eat"
 * 输出: 231
 * 解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
 * 在 "eat" 中删除 "t" 并将 116 加入总和。
 * 结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
 *
 * 示例 2:
 * 输入: s1 = "delete", s2 = "leet"
 * 输出: 403
 * 解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
 * 将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
 * 结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
 * 如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。
 *
 * 提示:
 * 0 <= s1.length, s2.length <= 1000
 * s1 和 s2 由小写英文字母组成
 */
public class Title712 {
    public static void main(String[] args) {
        String text11 = "sea";
        String text12 = "eat";

        String text21 = "delete";
        String text22 = "leet";

        System.out.println(minimumDeleteSum(text11, text12));
        System.out.println(minimumDeleteSum(text21, text22));

        System.out.println(minimumDeleteSum2(text11, text12));
        System.out.println(minimumDeleteSum2(text21, text22));

        System.out.println(minimumDeleteSum3(text11, text12));
        System.out.println(minimumDeleteSum3(text21, text22));

        System.out.println(minimumDeleteSum4(text11, text12));
        System.out.println(minimumDeleteSum4(text21, text22));
    }

    /**
     * 动态规划，使用二维数组
     */
    public static int minimumDeleteSum(String s1, String s2) {
        char[] s = s1.toCharArray();
        char[] t = s2.toCharArray();
        int n = s.length, m = t.length;
        int[][] f = new int[n + 1][m + 1];
        for (int j = 0; j < m; j++) {
            f[0][j + 1] = f[0][j] + (int)t[j];
        }

        for(int i = 0; i < n; i++) {
            f[i + 1][0] = f[i][0] + (int)s[i];
            for(int j = 0; j < m; j++) {
                f[i + 1][j + 1] = (s[i] == t[j]) ? f[i][j] : Math.min(f[i + 1][j] + (int)t[j], f[i][j + 1] + (int)s[i]);
            }
        }

        return f[n][m];
    }

    /**
     * 动态规划，使用二维数组
     */
    public static int minimumDeleteSum2(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] f = new int[n + 1][m + 1];
        for (int j = 0; j < m; j++) {
            f[0][j + 1] = f[0][j] + s2.codePointAt(j);
        }

        for(int i = 0; i < n; i++) {
            f[i + 1][0] = f[i][0] + s1.codePointAt(i);
            for(int j = 0; j < m; j++) {
                f[i + 1][j + 1] = (s1.codePointAt(i) == s2.codePointAt(j)) ? f[i][j] : Math.min(f[i + 1][j] + s2.codePointAt(j), f[i][j + 1] + s1.codePointAt(i));
            }
        }

        return f[n][m];
    }

    /**
     * 动态规划，空间优化使用两组一维数组
     */
    public static int minimumDeleteSum3(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] f = new int[2][m + 1];
        for (int j = 0; j < m; j++) {
            f[0][j + 1] = f[0][j] + s2.codePointAt(j);
        }

        for (int i = 0; i < n; i++) {
            f[(i + 1) % 2][0] = f[i % 2][0] + s1.codePointAt(i);
            for(int j = 0; j < m; j++) {
                f[(i + 1) % 2][j + 1] = (s1.charAt(i) == s2.charAt(j)) ? f[i % 2][j] : Math.min(f[(i + 1) % 2][j] + s2.codePointAt(j), f[i % 2][j + 1] + s1.codePointAt(i));
            }
        }

        return f[n % 2][m];
    }

    /**
     * 动态规划，空间优化，使用一组一维数组
     */
    public static int minimumDeleteSum4(String s1, String s2) {
        char[] t = s2.toCharArray();
        int  m = t.length;
        int[] f = new int[m + 1];
        for (int j = 0; j < m; j++) {
            f[j + 1] = f[j] + t[j];
        }

        for (char x : s1.toCharArray()) {
            int pre = f[0];
            f[0] += x;
            for(int j = 0; j < m; j++) {
                int tmp = f[j + 1];
                f[j + 1] = (x == t[j]) ? pre : Math.min(f[j] + t[j], f[j + 1] + x);
                pre = tmp;
            }
        }

        return f[m];
    }
}
