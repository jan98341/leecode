package com.jan.title200.title140;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. 分割回文串
 *  给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *  示例 1：
 *  输入：s = "aab"
 *  输出：[["a","a","b"],["aa","b"]]
 *
 *  示例 2：
 *  输入：s = "a"
 *  输出：[["a"]]
 *
 *  提示：
 *  1 <= s.length <= 16
 *  s 仅由小写英文字母组成
 */
public class Title131 {
    public static void main(String[] args) {
        String s1 = "aab";
        String s2 = "a";

        System.out.println(partition(s1));
        System.out.println(partition(s2));

        System.out.println(partition2(s1));
        System.out.println(partition2(s2));
    }

    /**
     * 输入的视角（逗号选或不选）
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> path = new ArrayList<>();

        dfs(0, 0, s, ans, path);
        return ans;
    }

    /**
     * 考虑 i 后面的逗号怎么选
     * start 表示当前这段回文子串的开始位置
     */
    private static void dfs(int i, int start, String s, List<List<String>> ans, List<String> path) {
        if(i == s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }

        // 不分割，不选 i 和 i+1 之间的逗号
        if(i < s.length() - 1) {
            dfs(i + 1, start, s, ans, path);
        }

        // 分割，选 i 和 i+1 之间的逗号（把 s[i] 作为子串的最后一个字符）
        if(isPalindrome(s, start, i)) {
            path.add(s.substring(start, i + 1));
            // 考虑 i+1 后面的逗号怎么选
            // start=i+1 表示下一个子串从 i+1 开始
            dfs(i + 1, i + 1, s, ans, path);
            path.remove(path.size() - 1);
        }
    }

    private static boolean isPalindrome(String s, int left, int right) {
        while(left < right) {
            if(s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 答案的视角（枚举子串结束位置）,相对比较好理解
     */
    public static List<List<String>> partition2(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs2(0, s, ans, path);
        return ans;
    }

    /**
     * 考虑 s[i] ~ s[n-1] 怎么分割
     */
    private static void dfs2(int i, String s, List<List<String>> ans, List<String> path) {
        if(i == s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for(int j = i; j < s.length(); j++) {
            if(isPalindrome(s, i, j)) {
                path.add(s.substring(i, j + 1));
                // 考虑剩余的 s[j+1] ~ s[n-1] 怎么分割
                dfs2(j + 1, s, ans, path);
                path.remove(path.size() - 1);
            }
        }
    }
}
