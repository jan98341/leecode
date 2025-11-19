package com.jan.title200.title130;

/**
 * 125. 验证回文串
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 * 字母和数字都属于字母数字字符。
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入: s = "A man, a plan, a canal: Panama"
 * 输出：true
 * 解释："amanaplanacanalpanama" 是回文串。
 *
 * 示例 2：
 * 输入：s = "race a car"
 * 输出：false
 * 解释："raceacar" 不是回文串。
 *
 * 示例 3：
 * 输入：s = " "
 * 输出：true
 * 解释：在移除非字母数字字符之后，s 是一个空字符串 "" 。
 * 由于空字符串正着反着读都一样，所以是回文串。
 *
 * 提示：
 * 1 <= s.length <= 2 * 10^5
 * s 仅由可打印的 ASCII 字符组成
 */
public class Title125 {
    public static void main(String[] args) {
        String s1 = "A man, a plan, a canal: Panama";
        String s2 = "race a car";
        String s3 = " ";

        Title125 title125 = new Title125();
        System.out.println(title125.isPalindrome(s1));
        System.out.println(title125.isPalindrome(s2));
        System.out.println(title125.isPalindrome(s3));

        System.out.println(title125.isPalindrome2(s1));
        System.out.println(title125.isPalindrome2(s2));
        System.out.println(title125.isPalindrome2(s3));
    }

    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            char l = getChar(chars[left]);
            while(l == '0' && left < right) {
                left++;
                l = getChar(chars[left]);
            }
            char r = getChar(chars[right]);
            while(r == '0' && left < right) {
                right--;
                r = getChar(chars[right]);
            }
            if(l != r) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }

    private char getChar(char c) {
        if((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
            return c;
        } else if(c >= 'A' && c <= 'Z') {
            return (char)(c + 32);
        } else {
            return '0';
        }
    }

    public boolean isPalindrome2(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if(!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            } else if(!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            } else if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }
}
