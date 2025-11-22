package com.jan.titlemorethan2000;

/**
 * 2730. 找到最长的半重复子字符串
 * 给你一个下标从 0 开始的字符串 s ，这个字符串只包含 0 到 9 的数字字符。
 * 如果一个字符串 t 中至多有一对相邻字符是相等的，那么称这个字符串 t 是 半重复的 。例如，"0010" 、"002020" 、"0123" 、"2002" 和 "54944"
 * 是半重复字符串，而 "00101022" （相邻的相同数字对是 00 和 22）和 "1101234883" （相邻的相同数字对是 11 和 88）不是半重复字符串。
 * 请你返回 s 中最长 半重复 子字符串 的长度。
 *
 * 示例 1：
 * 输入：s = "52233"
 * 输出：4
 * 解释：
 * 最长的半重复子字符串是 "5223"。整个字符串 "52233" 有两个相邻的相同数字对 22 和 33，但最多只能选取一个。
 *
 * 示例 2：
 * 输入：s = "5494"
 * 输出：4
 * 解释：
 * s 是一个半重复字符串。
 *
 * 示例 3：
 * 输入：s = "1111111"
 * 输出：2
 *
 * 解释：
 * 最长的半重复子字符串是 "11"。子字符串 "111" 有两个相邻的相同数字对，但最多允许选取一个。
 *
 * 提示：
 * 1 <= s.length <= 50
 * '0' <= s[i] <= '9'
 */
public class Title2730 {
    public static void main(String[] args) {
        String s1 = "52233";
        String s2 = "5494";
        String s3 = "1111111";

        Title2730 title2730 = new Title2730();
        System.out.println(title2730.longestSemiRepetitiveSubstring(s1));
        System.out.println(title2730.longestSemiRepetitiveSubstring(s2));
        System.out.println(title2730.longestSemiRepetitiveSubstring(s3));
    }

    /**
     * 移动右指针 right，并统计相邻相同的情况出现了多少次，记作 same。
     * 如果 same>1，则不断移动左指针 left 直到 s[left]=s[left−1]，此时将一对相同的字符移到窗口之外。然后将 same 置为 1。
     * 然后统计子串长度 right−left+1 的最大值。
     */
    public int longestSemiRepetitiveSubstring(String s) {
        char[] c = s.toCharArray();
        int n = c.length, ans = 0, left = 0, same = 0;
        for (int right = 1; right < n; right++) {
            if(c[right -1] == c[right]) {
                same++;
            }
            if (same > 1) {
                left++;
                while (c[left] != c[left - 1]) {
                    left++;
                }
                same = 1;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
