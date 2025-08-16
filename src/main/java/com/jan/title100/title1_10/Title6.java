package com.jan.title100.title1_10;

import java.util.ArrayList;
import java.util.List;

/**
 * 6、Z字型变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 *
 * 示例 1：
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 *
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * 示例 3：
 * 输入：s = "A", numRows = 1
 * 输出："A"
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 */
public class Title6 {

    public static void main(String[] args) {
        String res1 = convert("PAYPALISHIRING", 3);
        System.out.println("结果为：" + res1);
        String res2 = convert("PAYPALISHIRING", 4);
        System.out.println("结果为：" + res2);
        String res3 = convert("AB", 1);
        System.out.println("结果为：" + res3);
    }

    public static String convert(String s, int numRows) {
        if(s.length() <= numRows || numRows == 1) {
            return s;
        }

        List<StringBuffer> sbl = new ArrayList<>();
        for(int i = 0; i < numRows; i++) {
            StringBuffer sb = new StringBuffer();
            sbl.add(sb);
        }

        int cycle = 2 * numRows - 2;
        for (int j = 0; j < s.length(); j++) {
            int temp = j % cycle;
            if(temp % cycle < numRows) {
                sbl.get(temp).append(s.charAt(j));
            } else {
                sbl.get(cycle - temp).append(s.charAt(j));
            }
        }

        String res = "";
        for (StringBuffer sb : sbl) {
            res += sb;
        }
        return res;
    }
}
