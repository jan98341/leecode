package com.jan.titleMoreThan1000;

/**
 * 1771. 由子序列构造的最长回文串的长度
 * 给你两个字符串 word1 和 word2 ，请你按下述方法构造一个字符串：
 * 从 word1 中选出某个 非空 子序列 subsequence1 。
 * 从 word2 中选出某个 非空 子序列 subsequence2 。
 * 连接两个子序列 subsequence1 + subsequence2 ，得到字符串。
 * 返回可按上述方法构造的最长 回文串 的 长度 。如果无法构造回文串，返回 0 。
 * 字符串 s 的一个 子序列 是通过从 s 中删除一些（也可能不删除）字符而不更改其余字符的顺序生成的字符串。
 * 回文串 是正着读和反着读结果一致的字符串。
 *
 * 示例 1：
 * 输入：word1 = "cacb", word2 = "cbba"
 * 输出：5
 * 解释：从 word1 中选出 "ab" ，从 word2 中选出 "cba" ，得到回文串 "abcba" 。
 *
 * 示例 2：
 * 输入：word1 = "ab", word2 = "ab"
 * 输出：3
 * 解释：从 word1 中选出 "ab" ，从 word2 中选出 "a" ，得到回文串 "aba" 。
 *
 * 示例 3：
 * 输入：word1 = "aa", word2 = "bb"
 * 输出：0
 * 解释：无法按题面所述方法构造回文串，所以返回 0 。
 *
 * 提示：
 * 1 <= word1.length, word2.length <= 1000
 * word1 和 word2 由小写英文字母组成
 */
public class Title1771 {
    public static void main(String[] args) {
        String word11 = "cacb", word12 = "cbba";
        String word21 = "ab", word22 = "ab";
        String word31 = "aa", word32 = "bb";

        System.out.println(longestPalindrome(word11, word12));
        System.out.println(longestPalindrome(word21, word22));
        System.out.println(longestPalindrome(word31, word32));
    }

    /**
     * 这里需要注意的是该题要求从 word1和 word2中选出的子序列不能为空，那就不能直接s = word1 + word2后套用516 求最长回文序列
     * 需要在判断前后两字符相等的情况下，判断i、j的位置是否对应在word1和word2中，如果在对应字符串中满足条件，否则不满足条件
     */
    public static int longestPalindrome(String word1, String word2) {
        String s = word1 + word2;
        int n = s.length(), ans = 0;
        char[] c = s.toCharArray();
        int[][] f = new int[n][n];
        for(int i = n - 1; i >= 0; i--) {
            //i≥j 是递归边界，j==i时f[i][j]=1,其他j在i的右边
            f[i][i] = 1;
            for(int j = i + 1; j < n; j++) {
                if(c[i] == c[j]) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                    if(i < word1.length() && j >= word1.length()) {
                        ans = Math.max(ans, f[i][j]);
                    }
                } else {
                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                }
            }
        }
        return ans;
    }
}
