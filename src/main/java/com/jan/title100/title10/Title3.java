package com.jan.title100.title10;

import java.util.HashSet;
import java.util.Set;

public class Title3 {

    public static void main(String[] args) {
        String strCheckString = "pwwkew";
        Title3 t3 = new Title3();
//        String result = t3.traverseAllString(strCheckString);
//        System.out.println("搜索结果为：" + result);
        int length = t3.lengthOfLongestSubstring2(strCheckString);
        System.out.println("搜索结果为：" + length);
    }

    private int lengthOfLongestSubstring1(String s) {
        Set<Character> occ = new HashSet<Character>();
        int left = 0, ans = 0;
        for(int i = 0; i < s.length(); i++){
            while(occ.contains(s.charAt(i))){
                occ.remove(s.charAt(left++));
            }
            occ.add(s.charAt(i));
            ans = Math.max(ans, i - left + 1);
        }
        return ans;
    }

    private int lengthOfLongestSubstring2(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    /**
     * 遍历字符串所有可能子串
     */
    private int lengthOfLongestSubstring(String strCheckString) {
        Title3 t3 = new Title3();
        for (int i = strCheckString.length(); i > 0; i--) {
            for (int j = 0; i + j < strCheckString.length() + 1; j++) {
                String strCompare = strCheckString.substring(j, i + j);
                int repeat = t3.checkHashRepeatChar(strCompare);
                if(repeat < 0) {
                    return strCompare.length();
                }
            }
        }

        return 0;
    }

    /**
     * 遍历字符串所有可能子串
     */
    private String traverseAllString(String strCheckString) {
        Title3 t3 = new Title3();
        for (int i = strCheckString.length(); i > 0; i--) {
            for (int j = 0; i + j < strCheckString.length() + 1; j++) {
                String strCompare = strCheckString.substring(j, i + j);
                int repeat = t3.checkHashRepeatChar(strCompare);
                if(repeat < 0) {
                    return strCompare;
                }
            }
        }

        return null;
    }

    /**
     * 用来判断字符串是否包含重复字符
     */
    private int checkHashRepeatChar(String strCheckString) {
        for (int i = 0; i < strCheckString.length(); i++) {
            for (int j = i + 1; j < strCheckString.length(); j++) {
                if (strCheckString.charAt(i) == strCheckString.charAt(j)) {
                    return i;
                }
            }
        }

        return -1;
    }
}
