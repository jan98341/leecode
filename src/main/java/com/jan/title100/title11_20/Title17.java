package com.jan.title100.title11_20;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 17、电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 *
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 * 提示：
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 */
public class Title17 {

    private static final String[] MAPPING = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public static void main(String[] args) {
        System.out.println("case:1" + JSON.toJSONString(letterCombinations("23")));
        System.out.println("case:2" + JSON.toJSONString(letterCombinations("")));
        System.out.println("case:3" + JSON.toJSONString(letterCombinations("2")));
        System.out.println("case:3" + JSON.toJSONString(letterCombinations("456")));
    }

    public static List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        // 如果输入为空，直接返回空组合
        if(digits.length() == 0) {
            return ans;
        }

        // 定义长度为输入字符串长度的字符数组，用于存放组合字符串结果
        char[] path = new char[digits.length()];
        dfs(0, path, ans, digits.toCharArray());

        return ans;
    }

    /**
     * 使用深度遍历，采用递归写法
     */
    private static void dfs(int i, char[] path, List<String> ans, char[] digits ) {
        // 如果到达末端，返回该组合
        if(i == digits.length) {
            ans.add(new String(path));
            return;
        }

        // 获取电话数字所对应的字母集合，遍历字母
        String letter = MAPPING[digits[i] - '0'];
        for(char c : letter.toCharArray()) {
            path[i] = c;
            dfs(i + 1, path, ans, digits);
        }
    }
}
