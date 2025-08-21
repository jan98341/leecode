package com.jan.title100.title20;

/**
 * 14、最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 * 提示：
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 如果非空，则仅由小写英文字母组成
 */
public class Title14 {
    public static void main(String[] args) {
//        String[] strs1 = {"flower","flow","flight"};
//        System.out.println("结果为：" + longestCommonPrefix(strs1));
//        String[] strs2 = {"dog","racecar","car"};
//        System.out.println("结果为：" + longestCommonPrefix(strs2));
        String[] strs1 = {"flower","flow","flower1","flow2","flight","flight2","flight2","flight3"};
        System.out.println("结果为：" + longestCommonPrefix3(strs1));
    }

    public static String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                String str = strs[j];
                if(str.length() <= i || c != str.charAt(i)) {
                    return ans.toString();
                }
            }
            ans.append(c);
        }

        return ans.toString();
    }

    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        } else {
            return longestCommonPrefix(strs, 0, strs.length - 1);
        }
    }

    public static String longestCommonPrefix(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        } else {
            int mid = (end - start) / 2 + start;
            String lcpLeft = longestCommonPrefix(strs, start, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, end);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    public static String commonPrefix(String lcpLeft, String lcpRight) {
        int minLength = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < minLength; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, minLength);
    }

    public static String longestCommonPrefix3(String[] strs) {
        if(strs == null || strs.length == 0) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }

        int low = 0, high = minLength;
        while (low < high) {
            int mid = (high + low + 1) / 2;
            if(isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }

        return strs[0].substring(0, low);
    }

    public static boolean isCommonPrefix(String[] strs, int len) {
        for(int i = 1; i < strs.length; i++) {
            for(int j = 0; j < len; j++) {
                if(strs[0].charAt(j) != strs[i].charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

}
