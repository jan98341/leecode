package com.jan.title100.title80;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 1、插入一个字符
 * 2、删除一个字符
 * 3、替换一个字符
 *
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * 提示：
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 */
public class Title72 {
    public static void main(String[] args) {
        String word11 = "horse";
        String word12 = "ros";

        String word21 = "intention";
        String word22 = "execution";

        System.out.println(minDistance(word11, word12));
        System.out.println(minDistance(word21, word22));

        System.out.println(minDistance2(word11, word12));
        System.out.println(minDistance2(word21, word22));

        System.out.println(minDistance3(word11, word12));
        System.out.println(minDistance3(word21, word22));
    }

    /**
     * 递推，使用二维数组
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
                f[i + 1][j + 1] = (s[i] == t[j]) ? f[i][j] : Math.min(Math.min(f[i + 1][j], f[i][j + 1]), f[i][j]) + 1;
            }
        }

        return f[n][m];
    }

    /**
     * 递推，空间优化，使用两组一维数组
     */
    public static int minDistance2(String word1, String word2) {
        char[] s = word1.toCharArray();
        char[] t = word2.toCharArray();
        int n = s.length, m = t.length;
        int[][] f = new int[2][m + 1];
        for (int j = 0; j < m; j++) {
            f[0][j + 1] = j + 1;
        }
        for(int i = 0; i < n; i++){
            f[(i + 1) % 2][0] = i + 1;
            for(int j = 0; j < m; j++){
                f[(i + 1) % 2][j + 1] = (s[i] == t[j])? f[i % 2][j] : Math.min(Math.min(f[(i + 1) % 2][j], f[i % 2][j + 1]), f[i % 2][j]) + 1;
            }
        }
        return f[n % 2][m];
    }


    /**
     * 递推，空间优化，使用一维数组
     */
    public static int minDistance3(String word1, String word2) {
        char[] t = word2.toCharArray();
        int m = t.length;
        int[] f = new int[m + 1];
        for (int j = 0; j < m; j++) {
            f[j + 1] = j + 1;
        }

        for(char x : word1.toCharArray()){
            int pre = f[0];
            f[0]++;
            for(int j = 0; j < m; j++){
                int tmp = f[j + 1];
                f[j + 1] = (x == t[j])? pre : Math.min(Math.min(pre, f[j + 1]), f[j]) + 1;
                pre = tmp;
            }
        }
        return f[m];
    }
}
