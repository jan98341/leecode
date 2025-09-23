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

    }

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        char[] path = new char[s.length()];

        dfs(0, s.toCharArray(), ans, path);
        return ans;
    }

    private void dfs(int i, char[] s, List<List<String>> ans, char[] path) {
        if(i == s.length) {
            ans.add(new ArrayList<>());
            return;
        }

        for(int j = i; j < s.length; ++j) {
            path[i] = s[i];
            dfs(j + 1, s, ans, path);
        }
    }
}
