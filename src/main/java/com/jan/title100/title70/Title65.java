package com.jan.title100.title70;

/**
 * 65. 有效数字
 * 给定一个字符串 s ，返回 s 是否是一个 有效数字。
 * 例如，下面的都是有效数字："2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"，
 * 而接下来的不是："abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"。
 * 一般的，一个 有效数字 可以用以下的规则之一定义：
 * 1、一个 整数 后面跟着一个 可选指数。
 * 2、一个 十进制数 后面跟着一个 可选指数。
 * 一个 整数 定义为一个 可选符号 '-' 或 '+' 后面跟着 数字。
 * 一个 十进制数 定义为一个 可选符号 '-' 或 '+' 后面跟着下述规则：
 * 1、数字 后跟着一个 小数点 .。
 * 2、数字 后跟着一个 小数点 . 再跟着 数位。
 * 3、一个 小数点 . 后跟着 数位。
 * 指数 定义为指数符号 'e' 或 'E'，后面跟着一个 整数。
 * 数字 定义为一个或多个数位。
 *
 * 示例 1：
 * 输入：s = "0"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "e"
 * 输出：false
 *
 * 示例 3：
 * 输入：s = "."
 * 输出：false
 *
 * 提示：
 * 1 <= s.length <= 20
 * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。
 */
public class Title65 {
    public static void main(String[] args) {

        System.out.println(isNumber("0"));
        System.out.println(isNumber("e"));
        System.out.println(isNumber("."));

        /**
         *  * 例如，下面的都是有效数字："2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"，
         *  * 而接下来的不是："abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"。
         */
        System.out.println("------------");
        System.out.println(isNumber("0089"));
        System.out.println(isNumber("-0.1"));
        System.out.println(isNumber("+3.14"));
        System.out.println(isNumber("4."));
        System.out.println(isNumber("-.9"));
        System.out.println(isNumber("2e10"));
        System.out.println(isNumber("-90E3"));
        System.out.println(isNumber("3e+7"));
        System.out.println(isNumber("+6e-1"));
        System.out.println(isNumber("53.5e93"));
        System.out.println(isNumber("-123.456e789"));

        System.out.println("------------");
        System.out.println(isNumber("abc"));
        System.out.println(isNumber("1a"));
        System.out.println(isNumber("1e"));
        System.out.println(isNumber("e3"));
        System.out.println(isNumber("99e2.5"));
        System.out.println(isNumber("--6"));
        System.out.println(isNumber("-+3"));
        System.out.println(isNumber("95a54e53"));
        System.out.println(isNumber("0.."));
    }

    public static boolean isNumber(String s) {
        int n = s.length();

        // 获取第一个e/E在字符串中的位置
        int ec = -1;
        for (int i = 0; i < n; i++) {
            if( s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                ec = i;
                break;
            }
        }

        // 未包含e/E，按照十进制数判断
        if(ec == -1) {
            return checkDecimalNumber(s, false);
        }
        // 包含e/E，前面按照十进制数判断，后面按照整数判断
        else {
            boolean b1 = checkDecimalNumber(s.substring(0, ec), false);
            boolean b2 = checkDigit(s.substring(ec + 1));
            return checkDecimalNumber(s.substring(0, ec), false) && checkDigitWithFlag(s.substring(ec + 1));
        }
    }

    /**
     * 十进制数判断
     */
    private static boolean checkDecimalNumber(String s, boolean hasFlag) {
        if(s.isEmpty()) {
            return false;
        }

        // 判断首字符是否+/-符号，如果包含则去掉符号继续判断
        if(!hasFlag && (s.charAt(0) == '+' || s.charAt(0) == '-')) {
            return checkDecimalNumber(s.substring(1), true);
        }

        int p = s.indexOf(".");
        if( p >= 0) {
            // 如果存在.符号，需要判断小数点前面为空或后续为空的情况
            boolean p1 = checkDigit(s.substring(0, p));
            boolean p2 = checkDigit(s.substring(p + 1));
            return ((p == 0 && p2) || (p == s.length() - 1 && p1) || ( p1 && p2));
        } else {
            return checkDigit(s);
        }
    }

    /**
     * 校验字符串是否带符号的数字
     */
    private static boolean checkDigitWithFlag(String s) {
        if(s.isEmpty()) {
            return false;
        }

        String check = (s.charAt(0) == '+' || s.charAt(0) == '-') ? s.substring(1) : s;
        return checkDigit(check);
    }

    /**
     * 校验字符串是否数字，字符串为空返回false
     */
    private static boolean checkDigit(String s) {
        if(s.isEmpty()) {
            return false;
        }
        for(char c : s.toCharArray()) {
            if(c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
