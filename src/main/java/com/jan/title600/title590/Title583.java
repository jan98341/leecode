package com.jan.title600.title590;

/**
 * 583. 两个字符串的删除操作
 * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
 * 每步 可以删除任意一个字符串中的一个字符。
 *
 * 示例 1：
 * 输入: word1 = "sea", word2 = "eat"
 * 输出: 2
 * 解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
 *
 * 示例  2:
 * 输入：word1 = "leetcode", word2 = "etco"
 * 输出：4
 *
 * 提示：
 * 1 <= word1.length, word2.length <= 500
 * word1 和 word2 只包含小写英文字母
 */
public class Title583 {
    public static void main(String[] args) {
        String text11 = "sea";
        String text12 = "eat";
        String text21 = "leetcode";
        String text22 = "etco";

        System.out.println(minDistance(text11, text12));
        System.out.println(minDistance(text21, text22));

        System.out.println(minDistance2(text11, text12));
        System.out.println(minDistance2(text21, text22));

        System.out.println(minDistance3(text11, text12));
        System.out.println(minDistance3(text21, text22));

        System.out.println(minDistance4(text11, text12));
        System.out.println(minDistance4(text21, text22));
    }

    /**
     * 动态规划，使用二维数组
     * 动态规划的边界情况如下：
     * 1、当 i=0 时，word1[0:i] 为空，空字符串和任何字符串要变成相同，只有将另一个字符串的字符全部删除，因此对任意 0≤j≤n，有 dp[0][j]=j；
     * 2、当 j=0 时，word2[0:j] 为空，同理可得，对任意 0≤i≤m，有 dp[i][0]=i。
     * 当 i>0 且 j>0 时，考虑 dp[i][j] 的计算：
     * 1、当 word1[i−1]=word2[j−1] 时，将这两个相同的字符称为公共字符，考虑使 word1[0:i−1] 和 word2[0:j−1]
     *    相同的最少删除操作次数，增加一个公共字符之后，最少删除操作次数不变，因此 dp[i][j]=dp[i−1][j−1]。
     * 2、当 word1[i−1]!=word2[j−1] 时，考虑以下两项：
     * （1）使 word1[0:i−1] 和 word2[0:j] 相同的最少删除操作次数，加上删除 word1[i−1] 的 1 次操作；
     * （2）使 word1[0:i] 和 word2[0:j−1] 相同的最少删除操作次数，加上删除 word2[j−1] 的 1 次操作。
     * 要得到使 word1[0:i] 和 word2[0:j] 相同的最少删除操作次数，应取两项中较小的一项，
     * 因此 dp[i][j] =min(dp[i−1][j]+1,dp[i][j−1]+1) = min(dp[i−1][j],dp[i][j−1])+1。
     */
    public static int minDistance(String word1, String word2) {
        char[] s = word1.toCharArray();
        char[] t = word2.toCharArray();
        int n = s.length, m = t.length;
        int[][] f = new int[n + 1][m + 1];
        for (int j = 0; j < m; j++) {
            f[0][j + 1] = j + 1;
        }
        for (int i = 0; i < n; i++) {
            f[i + 1][0] = i + 1;
            for (int j = 0; j < m; j++) {
                f[i + 1][j + 1] = (s[i] == t[j]) ? f[i][j] : Math.min(f[i + 1][j], f[i][j + 1]) + 1;
            }
        }

        return f[n][m];
    }


    /**
     * 动态规划，使用两组一维数组
     */
    public static int minDistance2(String word1, String word2) {
        char[] s = word1.toCharArray();
        char[] t = word2.toCharArray();
        int n = s.length, m = t.length;
        int[][] f = new int[2][m + 1];
        for (int j = 0; j < m; j++) {
            f[0][j + 1] = j + 1;
        }
        for (int i = 0; i < n; i++) {
            f[(i + 1) % 2][0] = i + 1;
            for (int j = 0; j < m; j++) {
                f[(i + 1) % 2][j + 1] = (s[i] == t[j]) ? f[i % 2][j] : Math.min(f[(i + 1) % 2][j], f[i % 2][j + 1]) + 1;
            }
        }

        return f[n % 2][m];
    }


    /**
     * 动态规划，空间优化，使用一维数组
     */
    public static int minDistance3(String word1, String word2) {
        char[] t = word2.toCharArray();
        int m = t.length;
        int[] f = new int[m + 1];
        for (int j = 0; j < m; j++) {
            f[j + 1] = j + 1;
        }
        for (char x : word1.toCharArray()) {
            int pre = f[0];
            f[0]++;
            for (int j = 0; j < m; j++) {
                int tmp = f[j + 1];
                f[j + 1] = (x == t[j]) ? pre : Math.min(f[j], f[j + 1]) + 1;
                pre = tmp;
            }
        }

        return f[m];
    }

    /**
     * 最长公共子序列，先计算两个字符串的最长公共子序列的长度，然后分别计算两个字符串的长度和最长公共子序列的长度之差，即为两个字符串分别需要删除的字符数
     * 参见 1143.最长公共子序列 计算方法
     */
    public static int minDistance4(String word1, String word2) {
        char[] s = word1.toCharArray();
        char[] t = word2.toCharArray();
        int n = s.length, m = t.length;
        int[][] f = new int[n + 1][m + 1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                f[i + 1][j + 1] = (s[i] == t[j]) ? f[i][j] + 1: Math.max(f[i + 1][j], f[i][j + 1]);
            }
        }
        int lcs = f[n][m];

        return m + n - 2 * lcs;
    }
}
