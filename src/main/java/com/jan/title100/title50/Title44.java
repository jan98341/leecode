package com.jan.title100.title50;

/**
 * 44. 通配符匹配
 * 给你一个输入字符串 (s) 和一个字符模式 (p) ，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配：
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符序列（包括空字符序列）。
 * 判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。
 *
 * 示例 1：
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2：
 * 输入：s = "aa", p = "*"
 * 输出：true
 * 解释：'*' 可以匹配任意字符串。
 *
 * 示例 3：
 * 输入：s = "cb", p = "?a"
 * 输出：false
 * 解释：'?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 *
 * 提示：
 * 0 <= s.length, p.length <= 2000
 * s 仅由小写英文字母组成
 * p 仅由小写英文字母、'?' 或 '*' 组成
 */
public class Title44 {
    public static void main(String[] args) {
        String s1 = "aa";
        String p1 = "a";

        String s2 = "aa";
        String p2 = "*";

        String s3 = "cb";
        String p3 = "?a";

        String s4 = "abcdefgh";
        String p4 = "ab?d*h";


        System.out.println(isMatch(s1, p1));
        System.out.println(isMatch(s2, p2));
        System.out.println(isMatch(s3, p3));
        System.out.println(isMatch(s4, p4));
    }

    /**
     * 动态规划
     */
    public static boolean isMatch(String s, String p) {
        char[] sa = s.toCharArray(), pa = p.toCharArray();
        int n = s.length(), m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        // 因为星号才能匹配空字符串，所以只有当模式 p 的前 j 个字符均为星号时，dp[0][j] 才为真
//        for(int i = 1; i <= m; i++) {
//            if(pa[i - 1] == '*') {
//                dp[0][i] = true;
//            } else {
//                break;
//            }
//        }
        int k = 1;
        while(k <= m && pa[k - 1] == '*') {
            dp[0][k] = true;
            k++;
        }
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(pa[j - 1] == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
                // 如果 p[j] 是星号，那么同样对 s[i]没有任何要求，但是星号可以匹配零或任意多个小写字母，因此状态转移方程分为两种情况，即使用或不使用这个星号：
                // 1、如果我们不使用这个星号，那么就会从 dp[i][j−1] 转移而来；
                // 2、如果我们使用这个星号，那么就会从 dp[i−1][j] 转移而来。
                else if(pa[j - 1] == '?' || sa[i - 1] == pa[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[n][m];
    }
}
