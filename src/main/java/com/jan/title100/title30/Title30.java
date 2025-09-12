package com.jan.title100.title30;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 30. 串联所有单词的子串
 * 定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
 *  s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
 * 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。 "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
 * 返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：因为 words.length == 2 同时 words[i].length == 3，连接的子字符串的长度必须为 6。
 * 子串 "barfoo" 开始位置是 0。它是 words 中以 ["bar","foo"] 顺序排列的连接。
 * 子串 "foobar" 开始位置是 9。它是 words 中以 ["foo","bar"] 顺序排列的连接。
 * 输出顺序无关紧要。返回 [9,0] 也是可以的。
 *
 * 示例 2：
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * 解释：因为 words.length == 4 并且 words[i].length == 4，所以串联子串的长度必须为 16。
 * s 中没有子串长度为 16 并且等于 words 的任何顺序排列的连接。
 * 所以我们返回一个空数组。
 *
 * 示例 3：
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 * 解释：因为 words.length == 3 并且 words[i].length == 3，所以串联子串的长度必须为 9。
 * 子串 "foobarthe" 开始位置是 6。它是 words 中以 ["foo","bar","the"] 顺序排列的连接。
 * 子串 "barthefoo" 开始位置是 9。它是 words 中以 ["bar","the","foo"] 顺序排列的连接。
 * 子串 "thefoobar" 开始位置是 12。它是 words 中以 ["the","foo","bar"] 顺序排列的连接。
 *
 * 提示：
 * 1 <= s.length <= 104
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] 和 s 由小写英文字母组成
 */
public class Title30 {
    public static void main(String[] args) {
        String s1 = "barfoothefoobarman";
        String[] words1 = {"foo","bar"};

        String s2 = "wordgoodgoodgoodbestword";
        String[] words2 = {"word","good","best","word"};

        String s3 = "barfoofoobarthefoobarman";
        String[] words3 = {"bar","foo","the"};

        String s4 = "wordgoodgoodgoodbestword";
        String[] words4 = {"word","good","best","good"};

        String s5 = "wordgoodgoodgoodbestword";
        String[] words5 = {"word","good","best","word"};

//        System.out.println(JSON.toJSONString(findSubstring(s1, words1)));
//        System.out.println(JSON.toJSONString(findSubstring(s2, words2)));
//        System.out.println(JSON.toJSONString(findSubstring(s3, words3)));
//        System.out.println(JSON.toJSONString(findSubstring(s4, words4)));
//        System.out.println(JSON.toJSONString(findSubstring(s5, words5)));

        System.out.println(JSON.toJSONString(findSubstring2(s1, words1)));
        System.out.println(JSON.toJSONString(findSubstring2(s2, words2)));
        System.out.println(JSON.toJSONString(findSubstring2(s3, words3)));
        System.out.println(JSON.toJSONString(findSubstring2(s4, words4)));
        System.out.println(JSON.toJSONString(findSubstring2(s5, words5)));
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        int wl = words.length;
        int wsl = words[0].length();
        if( s.length() < wl * wsl) {
            return new ArrayList<>();
        }

        // 把words数组转换成map
        Map<String, Integer> wordMap = new HashMap<>();
        for (int i = 0; i < wl; i++) {
            if(wordMap.containsKey(words[i])) {
                wordMap.put(words[i], wordMap.get(words[i]) + 1);
            } else {
                wordMap.put(words[i], 1);
            }
        }

        // 寻找所有匹配的位置
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i <= s.length() - wl * wsl; i++) {
            if(checkEqual(s.substring(i), wl, wsl, wordMap)) {
                ans.add(i);
            }
        }

        return ans;
    }

    /**
     * 通过匹配两个Map包含内容是否一致判断字符串是否为字符串数组组成，需要注意的是由于字符串存在重复，需要考虑存在个数问题
     */
    private static boolean checkEqual(String s, int wl, int wsl, Map<String, Integer> wordMap) {
        // 获取s字符串从当前，拆分wl个子字符串生成Map，Map的Value内容为出现的个数
        Map<String, Integer> sMap = new HashMap<>();
        for (int i = 0; i < wl; i++) {
            String sc = s.substring(i * wsl, (i + 1) * wsl);
            if(sMap.containsKey(sc)) {
                sMap.put(sc, sMap.get(sc) + 1);
            } else {
                sMap.put(sc, 1);
            }
        }

        // 遍历words Map所有字符串，如果在sMap中不存在或者个数小于words个数返回false
        for(Map.Entry<String, Integer> map : wordMap.entrySet()) {
            if(sMap.get(map.getKey()) == null || sMap.get(map.getKey()) < map.getValue()) {
                return false;
            }
        }

        return true;
    }

    public static List<Integer> findSubstring2(String s, String[] words) {
        int wordLen = words[0].length(); // 一个单词的长度
        int windowLen = wordLen * words.length; // 所有单词的总长度，即窗口大小

        // 目标：窗口中的单词出现次数必须与 targetCnt 完全一致
        Map<String, Integer> targetCnt = new HashMap<>();
        for (String w : words) {
            targetCnt.merge(w, 1, Integer::sum); // targetCnt[w]++
        }

        List<Integer> ans = new ArrayList<>();
        // 枚举窗口起点，做 wordLen 次滑动窗口
        for (int start = 0; start < wordLen; start++) {
            Map<String, Integer> cnt = new HashMap<>();
            int overload = 0;
            // 枚举窗口最后一个单词的右端点+1
            for (int right = start + wordLen; right <= s.length(); right += wordLen) {
                // 1. inWord 进入窗口
                String inWord = s.substring(right - wordLen, right);
                // 下面 cnt[inWord]++ 后，inWord 的出现次数过多
                if (cnt.getOrDefault(inWord, 0).equals(targetCnt.getOrDefault(inWord, 0))) {
                    overload++;
                }
                cnt.merge(inWord, 1, Integer::sum); // cnt[inWord]++

                int left = right - windowLen; // 窗口第一个单词的左端点
                if (left < 0) { // 窗口大小不足 windowLen
                    continue;
                }

                // 2. 更新答案
                // 如果没有超出 targetCnt 的单词，那么也不会有少于 targetCnt 的单词
                if (overload == 0) {
                    ans.add(left);
                }

                // 3. 窗口最左边的单词 outWord 离开窗口，为下一轮循环做准备
                String outWord = s.substring(left, left + wordLen);
                cnt.merge(outWord, -1, Integer::sum); // cnt[outWord]--
                if (cnt.get(outWord).equals(targetCnt.getOrDefault(outWord, 0))) {
                    overload--;
                }
            }
        }

        return ans;
    }
}
