package com.jan.title100.title50;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 解释：
 * 在 strs 中没有字符串可以通过重新排列来形成 "bat"。
 * 字符串 "nat" 和 "tan" 是字母异位词，因为它们可以重新排列以形成彼此。
 * 字符串 "ate" ，"eat" 和 "tea" 是字母异位词，因为它们可以重新排列以形成彼此。
 *
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 *
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 * 提示：
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 */
public class Title49 {
    public static void main(String[] args) {
        String[] str1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] str2 = {""};
        String[] str3 = {"a"};
        String[] str4 = {"ddddddddddg","dgggggggggg"};
//        System.out.println(groupAnagrams(str1));
//        System.out.println(groupAnagrams(str2));
//        System.out.println(groupAnagrams(str3));
//        System.out.println(groupAnagrams(str4));

        System.out.println(groupAnagrams2(str1));
        System.out.println(groupAnagrams2(str2));
        System.out.println(groupAnagrams2(str3));
        System.out.println(groupAnagrams2(str4));

    }

    /**
     * 最为除暴的处理方式，每个字符串对比长度相同的字符集合的第一个字符串，如果包含字符相同则为字母异位词
     * 需要注意的是字符串包含字符存在重复的情况
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        for (String str : strs) {
            boolean flag = false;
            for(List<String> list : ans) {
                // 只有长度相等的时候，判断包含的字符是否相同
                if(list.get(0).length() == str.length() && anagrams(str, list.get(0))) {
                    list.add(str);
                    flag = true;
                }
            }
            if(!flag) {
                ans.add(new ArrayList<>(Arrays.asList(str)));
            }
        }

        return ans;
    }

    private static boolean anagrams(String src, String tar) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c : src.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        }
        for(char c : tar.toCharArray()) {
            if(!map.containsKey(c) || map.get(c) == 0) {
                return false;
            } else {
                map.merge(c, -1, Integer::sum);
            }
        }

        return true;
    }

    /**
     * 用哈希表分组，把排序后的字符串当作哈希表的 key，排序前的字符串加到对应的列表中（哈希表的 value）。
     */
    public static List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> m = new HashMap<>();
        for (String str : strs) {
            // // 把 s 排序，作为哈希表的 key
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            // 排序后相同的字符串分到同一组
            // computeIfAbsent：如果 key 不在哈希表中，则插入一个新的 ArrayList
            m.computeIfAbsent(new String(chars), __-> new ArrayList<>()).add(str);
        }
        // 哈希表的 value 保存分组后的结果
        return new ArrayList<>(m.values());
    }
}
