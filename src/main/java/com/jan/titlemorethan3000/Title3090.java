package com.jan.titlemorethan3000;

/**
 * 3090. 每个字符最多出现两次的最长子字符串
 * 给你一个字符串 s ，请找出满足每个字符最多出现两次的最长子字符串，并返回该子字符串的 最大 长度。
 *
 * 示例 1：
 * 输入： s = "bcbbbcba"
 * 输出： 4
 * 解释：
 * 以下子字符串长度为 4，并且每个字符最多出现两次："bcbbbcba"。
 *
 * 示例 2：
 * 输入： s = "aaaa"
 * 输出： 2
 * 解释：
 *
 * 以下子字符串长度为 2，并且每个字符最多出现两次："aaaa"。
 * 提示：
 * 2 <= s.length <= 100
 * s 仅由小写英文字母组成。
 */
public class Title3090 {
    public static void main(String[] args) {
        String s1 = "bcbbbcba";
        String s2 = "aaaa";

        Title3090 title3090 = new Title3090();
        System.out.println(title3090.maximumLengthSubstring(s1));
        System.out.println(title3090.maximumLengthSubstring(s2));
    }

    public int maximumLengthSubstring(String s) {
        char[] c = s.toCharArray();
        int n = c.length, ans = 0, left = 0;
        int[] cnt = new int[128];
        for(int right = 0; right < n; right++) {
            char a = c[right];
            cnt[a]++;
            while(cnt[a] > 2) {
                cnt[c[left]]--;
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
