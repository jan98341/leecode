package com.jan.title100.title70;

/**
 * 67. 二进制求和
 * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
 *
 * 示例 1：
 * 输入:a = "11", b = "1"
 * 输出："100"
 *
 * 示例 2：
 * 输入：a = "1010", b = "1011"
 * 输出："10101"
 *
 * 提示：
 * 1 <= a.length, b.length <= 104
 * a 和 b 仅由字符 '0' 或 '1' 组成
 * 字符串如果不是 "0" ，就不含前导零
 */
public class Title67 {
    public static void main(String[] args) {
        String a1 = "11", b1 = "1";
        String a2 = "1010", b2 = "1011";
        String a3 = "111111010", b3 = "0";

        System.out.println(addBinary(a1, b1));
        System.out.println(addBinary(a2, b2));
        System.out.println(addBinary(a3, b3));

        System.out.println(addBinary2(a1, b1));
        System.out.println(addBinary2(a2, b2));
        System.out.println(addBinary2(a3, b3));
    }

    public static String addBinary(String a, String b) {
        int m = a.length(), n = b.length();
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for(int i = m - 1, j = n - 1; i >= 0 || j >= 0; i--, j--) {
            flag = add((i >=0 ? a.charAt(i) : '0'), (j >= 0 ? b.charAt(j) : '0'), flag, sb);
        }
        if (flag) sb.append('1');
        return sb.reverse().toString();
    }

    private static boolean add(char a, char b, boolean flag, StringBuilder sb) {
        int r = (a - '0') + (b - '0') + (flag ? 1 : 0);
        sb.append(r % 2);
        return r >= 2;
    }

    public static String addBinary2(String a, String b) {
        int m = a.length(), n = b.length();
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for(int i = m - 1, j = n - 1; i >= 0 || j >= 0; i--, j--) {
            int r = (i >= 0 ? a.charAt(i) - '0' : 0) + (j >= 0 ? b.charAt(j) - '0' : 0) + (flag ? 1 : 0);
            sb.append(r % 2);
            flag = r >= 2;
        }
        if (flag) {
            sb.append('1');
        }
        return sb.reverse().toString();
    }
}
