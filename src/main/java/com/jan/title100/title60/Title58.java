package com.jan.title100.title60;

/**
 * 58. 最后一个单词的长度
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 * 示例 1：
 * 输入：s = "Hello World"
 * 输出：5
 * 解释：最后一个单词是“World”，长度为 5。
 *
 * 示例 2：
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 * 解释：最后一个单词是“moon”，长度为 4。
 *
 * 示例 3：
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 * 解释：最后一个单词是长度为 6 的“joyboy”。
 *
 * 提示：
 * 1 <= s.length <= 104
 * s 仅有英文字母和空格 ' ' 组成
 * s 中至少存在一个单词
 */
public class Title58 {
    public static void main(String[] args) {
        String s1 = "Hello World";
        String s2 = "   fly me   to   the moon  ";
        String s3 = "uffy is still joyboy";
        String s4 = "";
        String s5 = " ";

//        System.out.println(lengthOfLastWord(s1));
//        System.out.println(lengthOfLastWord(s2));
//        System.out.println(lengthOfLastWord(s3));
//        System.out.println(lengthOfLastWord(s4));
//        System.out.println(lengthOfLastWord(s5));
//
//        System.out.println(lengthOfLastWord2(s1));
//        System.out.println(lengthOfLastWord2(s2));
//        System.out.println(lengthOfLastWord2(s3));
//        System.out.println(lengthOfLastWord2(s4));
//        System.out.println(lengthOfLastWord2(s5));

        System.out.println(lengthOfLastWord3(s1));
        System.out.println(lengthOfLastWord3(s2));
        System.out.println(lengthOfLastWord3(s3));
        System.out.println(lengthOfLastWord3(s4));
        System.out.println(lengthOfLastWord3(s5));

        System.out.println(lengthOfLastWord5(s1));
        System.out.println(lengthOfLastWord5(s2));
        System.out.println(lengthOfLastWord5(s3));
        System.out.println(lengthOfLastWord5(s4));
        System.out.println(lengthOfLastWord5(s5));
    }

    public static int lengthOfLastWord(String s) {
        int ans = 0;
        int l = s.length();
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) != ' ') {
                ans++;
            } else if (s.charAt(i) == ' ' && i < l - 1 && s.charAt(i + 1) != ' ') {
                ans = 0;
            }
        }

        return ans;
    }

    public static int lengthOfLastWord2(String s) {
        int ans = 0;
        String st = s.trim();
        for (int i = st.length() - 1; i >= 0; i--) {
            if (st.charAt(i) != ' ') {
                ans++;
            } else {
                return ans;
            }
        }

        return ans;
    }

    public static int lengthOfLastWord3(String s) {
        int ans = 0;
        int i = s.trim().length() - 1;
        while(i >= 0 && s.trim().charAt(i) != ' ') {
            i--;
            ans++;
        }
        return ans;
    }

    public static int lengthOfLastWord4(String s) {
        int ans = 0;
        int i = s.length() - 1;
        for(; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                break;
            }
        }
        while(i >= 0 && s.charAt(i) != ' ') {
            i--;
            ans++;
        }
        return ans;
    }

    public static int lengthOfLastWord5(String s) {
        int length = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                length++;
            } else if (length != 0) {
                return length;
            }
        }
        return length;
    }
}
