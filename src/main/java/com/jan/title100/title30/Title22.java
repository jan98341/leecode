package com.jan.title100.title30;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;

/**
 * 22、括号生成
 * https://leetcode.cn/problems/generate-parentheses/
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *
 * 提示：
 * 1 <= n <= 8
 */
public class Title22 {
    public static void main(String[] args) {
        System.out.println("示例1：" + JSON.toJSONString(generateParenthesis(3)));
        System.out.println("示例2：" + JSON.toJSONString(generateParenthesis(1)));
        System.out.println("示例3：" + JSON.toJSONString(generateParenthesis(4)));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        char[] path = new char[2*n];
        dfs(0, 0, n, path, ans);
        return ans;
    }

    /**
     * 通过深度遍历枚举可能性
     */
    private static void dfs(int left, int right, int n, char[] path, List<String> ans) {
        if(right == n) {
            ans.add(new String(path));
            return;
        }
        if(left < n) {
            path[left + right] = '(';
            dfs(left + 1, right, n, path, ans);
        }
        if(right < left) {
            path[left + right] = ')';
            dfs(left, right + 1, n, path, ans);
        }
    }
}
