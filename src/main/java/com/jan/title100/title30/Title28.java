package com.jan.title100.title30;

/**
 * 28. 找出字符串中第一个匹配项的下标
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * 如果 needle 不是 haystack 的一部分，则返回  -1 。
 *
 * 示例 1：
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 *
 * 示例 2：
 * 输入：haystack = "leetcode", needle = "leeto"
 * 输出：-1
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
 *
 * 提示：
 * 1 <= haystack.length, needle.length <= 104
 * haystack 和 needle 仅由小写英文字符组成
 */
public class Title28 {
    public static void main(String[] args) {
//        String haystack1 = "sadbutsad";
//        String needle1 = "sad";
////        System.out.println(strStr(haystack1, needle1));
//        System.out.println(strStr2(haystack1, needle1));
//
//        String haystack2 = "leetcode";
//        String needle2 = "leeto";
////        System.out.println(strStr(haystack2, needle2));
//        System.out.println(strStr2(haystack2, needle2));
//
//        String haystack3 = "leetcode";
//        String needle3 = "tco";
////        System.out.println(strStr(haystack3, needle3));
//        System.out.println(strStr2(haystack3, needle3));

        String haystack4 = "a";
        String needle4 = "a";
//        System.out.println(strStr(haystack4, needle4));
        System.out.println(strStr2(haystack4, needle4));
    }

    public static int strStr(String haystack, String needle) {
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if(haystack.substring(i, i + needle.length()).equals(needle)) {
                return i;
            }
        }

        return -1;
    }

    public static int strStr2(String haystack, String needle) {
        char[] hays = haystack.toCharArray();
        char[] needles = needle.toCharArray();
        for (int i = 0; i <= hays.length - needles.length; i++) {
            if(hays[i] == needles[0]) {
                for(int j = 0; j < needles.length; j++) {
                    if(hays[i + j] != needles[j]) {
                        break;
                    }
                    if(j == needles.length - 1) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }
}
