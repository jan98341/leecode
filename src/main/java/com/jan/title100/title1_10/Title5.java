package com.jan.title100.title1_10;

/**
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的 回文 子串。
 *
 * 示例 1：
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 */
public class Title5 {

    public static void main(String[] args) {
        Title5 title5 = new Title5();
//        System.out.println(title5.longestPalindrome("babad"));
//        System.out.println(title5.longestPalindrome("cbbd"));
//        System.out.println(title5.longestPalindrome2("babad"));
//        System.out.println(title5.longestPalindrome2("cbbd"));
        System.out.println(title5.longestPalindrome3("babad"));
        System.out.println(title5.longestPalindrome3("cbbd"));
    }

    /**
     * 遍历所有子串组合，判断子串是否回文字符串
     * @param s 需要判断的字符串
     * @return 识别出来回文字符换
     */
    public String longestPalindrome(String s) {
        for (int i = s.length(); i > 0; i--) {
            for (int j = 0; i + j < s.length() + 1; j++) {
                if (checkIsPalindrome(s.substring(j, j + i))) {
                    return s.substring(j, j + i);
                }
            }
        }

        return null;
    }

    /**
     * 判断字符串是否回文字符串
     * @param s 需要判断的字符串
     * @return 是否回文
     */
    private Boolean checkIsPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 通过动态规划，判断子串是否回文字符串
     * @param s 需要判断的字符串
     * @return 识别出来回文字符换
     */
    public String longestPalindrome2(String s) {
        // 边界情况的判断
        int len = s.length();
        if(len < 2){
            return s;
        }

        int maxLength = 1;
        int start = 0;

        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] chars = s.toCharArray();
        for(int j = 1; j < len; j++){
            for(int i = 0; i < j; i ++) {
                if(chars[i] != chars[j]){
                    dp[i][j] = false;
                } else {
                    if(j - i <= 2) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if(dp[i][j] && j -i + 1 > maxLength){
                    maxLength = j - i + 1;
                    start = i;
                }
            }
        }

//        // 先枚举子串长度
//        for(int L = 2; L <= len; L++){
//            for (int i = 0; i < len; i++) {
//                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L
//                int j = i + L - 1;
//                // 如果右边界越界，就可以退出当前循环
//                if (j >= len) {
//                    break;
//                }
//
//                if(chars[i] != chars[j]){
//                    dp[i][j] = false;
//                } else {
//                    if(j - i <= 2) {
//                        // 当字符串只有1个或2个字符时，第i、j字符相同则字符串为回文字符串
//                        dp[i][j] = true;
//                    } else {
//                        // 如果字符串长度大于2，则参考字符串去除头尾字符串是否回文
//                        dp[i][j] = dp[i+1][j-1];
//                    }
//                }
//
//                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
//                if(dp[i][j] && j -i +1 > maxLength){
//                    maxLength = j - i + 1;
//                    start = i;
//                }
//            }
//        }

        return s.substring(start, start + maxLength);
    }

    /**
     * 中心扩展算法，判断子串是否回文字符串
     * @param s 需要判断的字符串
     * @return 识别出来回文字符换
     */
    public String longestPalindrome3(String s) {
        if(s == null || s.length() < 2){
            return s;
        }

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if(len > end - start){
                start = i - (len - 1) / 2;
                end = i + len /2 ;
            }
        }

        return s.substring(start, end + 1);
    }

    /**
     * 判断字符串是否回文字符串
     * @param s 需要判断的字符串
     * @param left 左起始位置
     * @param right 右起始位置
     * @return
     */
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }

        return right - left - 1;
    }
}
