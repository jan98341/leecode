package com.jan.title100.title80;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 *
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 *
 * 示例 3:
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *
 * 提示：
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 10^5
 * s 和 t 由英文字母组成
 *
 * 进阶：你能设计一个在 o(m+n) 时间内解决此问题的算法吗？
 */
public class Title76 {
    public static void main(String[] args) {
        String s1 = "ADOBECODEBANC", t1 = "ABC";
        String s2 = "a", t2 = "a";
        String s3 = "a", t3 = "aa";

        Title76 title76 = new Title76();
        System.out.println(title76.minWindow(s1, t1));
        System.out.println(title76.minWindow(s2, t2));
        System.out.println(title76.minWindow(s3, t3));

    }

    public String minWindow(String s, String t) {
        int[] cnt = new int[128];
        int less = 0;
        for (char c : t.toCharArray()) {
            if(cnt[c] == 0) {
                // 累计在t中不同字母个数
                less++;
            }
            cnt[c]++;
        }

        char[] ss = s.toCharArray();
        int m = ss.length, left = 0, ansLeft = -1, ansRight = m;
        // 移动子串右端点
        for(int right = 0; right < m; right++) {
            char c = ss[right];
            cnt[c]--;
            if(cnt[c] == 0) {
                // 原来窗口内 c 的出现次数比 t 的少，现在一样多
                less--;
            }

            while(less == 0) {
                // 找到更短的子串
                if(right - left < ansRight - ansLeft) {
                    // 记录此时的左右端点
                    ansRight = right;
                    ansLeft = left;
                }
                // 左端点字母
                char x = ss[left];
                if(cnt[x] == 0) {
                    // x 移出窗口之前，检查出现次数，
                    // 如果窗口内 x 的出现次数和 t 一样，
                    // 那么 x 移出窗口后，窗口内 x 的出现次数比 t 的少
                    less++;
                }
                // 左端点字母移出子串
                cnt[x]++;
                left++;
            }
        }

        return ansLeft < 0 ? "" : s.substring(ansLeft, ansRight + 1);
    }
}
