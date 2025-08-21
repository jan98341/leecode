package com.jan.title100.title10;

/**
 * 10、正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s 的，而不是部分字符串。
 *
 * 示例 1：
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入：s = "aa", p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 示例 3：
 * 输入：s = "ab", p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 提示：
 * 1 <= s.length <= 20
 * 1 <= p.length <= 20
 * s 只包含从 a-z 的小写字母。
 * p 只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 */
public class Title10 {

    public static void main(String[] args) {
        String s = "aa", p = "a";
        System.out.println("第一个例子：" + isMatch(s, p));
        String s1 = "aa", p1 = "a*";
        System.out.println("第二个例子：" + isMatch(s1, p1));
        String s2 = "ab", p2 = ".*";
        System.out.println("第三个例子：" + isMatch(s2, p2));
    }

    public static boolean isMatch(String s, String p) {
        char[] cs = s.toCharArray(), cp = p.toCharArray();
        boolean[][] dp = new boolean[cs.length + 1][cp.length + 1];
        dp[0][0] = true;

        for (int j = 1; j <= cp.length; j++) {
            if(cp[j-1] == '*') dp[0][j] = dp[0][j-2];
        }

        for (int i = 1; i <= cs.length; i++) {
            for (int j = 1; j <= cp.length; j++) {
                if(cs[i-1] == cp[j-1] || cp[j-1] == '.') {
                    dp[i][j] = dp[i-1][j-1];
                } else if(cp[j-1] == '*') {
                    if(cs[i-1] == cp[j-2] || cp[j-2] == '.') {
                        dp[i][j] = dp[i][j-2] || dp[i-1][j];
                    } else {
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }

        return dp[cs.length][cp.length];
    }
}
