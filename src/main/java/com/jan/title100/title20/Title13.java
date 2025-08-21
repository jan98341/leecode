package com.jan.title100.title20;

import java.util.HashMap;
import java.util.Map;

/**
 * 13、罗马数字转整数
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。
 *
 * 示例 1:
 * 输入: s = "III"
 * 输出: 3
 *
 * 示例 2:
 * 输入: s = "IV"
 * 输出: 4
 *
 * 示例 3:
 * 输入: s = "IX"
 * 输出: 9
 *
 * 示例 4:
 * 输入: s = "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 *
 * 示例 5:
 * 输入: s = "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * 提示：
 * 1 <= s.length <= 15
 * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
 * 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
 * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
 * IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
 */
public class Title13 {
    public static void main(String[] args) {
        int result1 = romanToInt("III");
        System.out.println("结果为:" + result1);
        int result2 = romanToInt("IV");
        System.out.println("结果为:" + result2);
        int result3 = romanToInt("IX");
        System.out.println("结果为:" + result3);
        int result4 = romanToInt("LVIII");
        System.out.println("结果为:" + result4);
        int result5 = romanToInt("MCMXCIV");
        System.out.println("结果为:" + result5);

//        int result1 = romanToInt2("III");
//        System.out.println("结果为:" + result1);
//        int result2 = romanToInt2("IV");
//        System.out.println("结果为:" + result2);
//        int result3 = romanToInt2("IX");
//        System.out.println("结果为:" + result3);
//        int result4 = romanToInt2("LVIII");
//        System.out.println("结果为:" + result4);
//        int result5 = romanToInt2("MCMXCIV");
//        System.out.println("结果为:" + result5);
    }

    /**
     * 通常情况下，罗马数字中小的数字在大的数字的右边。若输入的字符串满足该情况，那么可以将每个字符视作一个单独的值，累加每个字符对应的数值即可。
     * 例如 XXVII 可视作 X+X+V+I+I=10+10+5+1+1=27。
     * 若存在小的数字在大的数字的左边的情况，根据规则需要减去小的数字。对于这种情况，我们也可以将每个字符视作一个单独的值，若一个数字右侧的数字比它大，则将该数字的符号取反。
     * 例如 XIV 可视作 X−I+V=10−1+5=14。
     */
    public static int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int v = map.get(s.charAt(i));
            if(i + 1 < s.length() && v < map.get(s.charAt(i + 1)) ) {
                ans -= v;
            } else {
                ans += v;
            }
        }

        return ans;
    }

    /**
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * @param s
     * @return
     */
    public static int romanToInt2(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char n = 'a';
            if(i + 1 < s.length()) {
                n = s.charAt(i + 1);
            }

            switch (c) {
                case 'I':
                    if(n == 'V') {
                        result += 4;
                        i ++;
                    } else if(n == 'X') {
                        result += 9;
                        i ++;
                    } else {
                        result += 1;
                    }
                    break;
                case 'V':
                    result += 5;
                    break;
                case 'X':
                    if(n == 'L') {
                        result += 40;
                        i ++;
                    } else if(n == 'C') {
                        result += 90;
                        i ++;
                    } else {
                        result += 10;
                    }
                    break;
                case 'L':
                    result += 50;
                    break;
                case 'C':
                    if(n == 'D') {
                        result += 400;
                        i ++;
                    } else if(n == 'M') {
                        result += 900;
                        i ++;
                    } else {
                        result += 100;
                    }
                    break;
                case 'D':
                    result += 500;
                    break;
                case 'M':
                    result += 1000;
                    break;
            }
        }

        return result;
    }
}
