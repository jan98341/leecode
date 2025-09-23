package com.jan.title100.title50;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 *
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 * 提示：
 * 1 <= num1.length, num2.length <= 200
 * num1 和 num2 只能由数字组成。
 * num1 和 num2 都不包含任何前导零，除了数字0本身。
 */
public class Title43 {
    public static void main(String[] args) {

        String num21 = "123";
        String num22 = "456";
//        multiply(num21, num22);
        multiply2(num21, num22);

    }

    /**
     * 时间复杂度：O(mn+n^2)
     * 空间复杂度：O(m+n)
     */
    public static String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int len1 = num1.length(), len2 = num2.length();
        String ans = "";
        for(int i = len2 - 1; i >= 0; i--) {
            int x = num2.charAt(i) - '0';
            StringBuilder s = new StringBuilder();
            for(int k = 0; k < len2 - i - 1; k++) {
                s.append('0');
            }
            int add = 0;
            for(int j = len1 - 1; j >= 0; j --) {
                int y = num1.charAt(j) - '0';
                add = x * y + add;
                s.append(add % 10);
                add = add / 10;
            }
            if(add > 0) {
                s.append(add);
            }
            ans = addString(ans, s.reverse().toString());
        }

        return ans;
    }

    /**
     * 按照竖式计算从低位到高位计算，通过取10余数加入当前的位数，进位通过除以10实现
     */
    private static String addString(String s1, String s2) {
        StringBuilder ans = new StringBuilder();
        int i = s1.length() - 1, j = s2.length() - 1;
        int add = 0;
        while(i >=0 || j >=0) {
            int x = (i >= 0)? s1.charAt(i) - '0' : 0;
            int y = (j >= 0)? s2.charAt(j) - '0' : 0;
            add = x + y + add;
            ans.append(add % 10);
            add = add / 10;
            i--;
            j--;
        }
        if(add > 0) {
            ans.append(add);
        }
        ans.reverse();
        System.out.println(ans);
        return ans.toString();
    }

    /**
     * 时间复杂度：O(mn)，其中 m 和 n 分别是 num1 和 num2的长度。
     * 空间复杂度：O(m+n)，其中 m 和 n 分别是 num1 和 num2的长度。
     */
    public static String multiply2(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int len1 = num1.length(), len2 = num2.length();
        int[] ansArray = new int[len1 + len2];
        for(int i = len2 - 1; i >= 0; i--) {
            int x = num2.charAt(i) - '0';
            for(int j = len1 - 1; j >= 0; j--) {
                int y = num1.charAt(j) - '0';
                ansArray[i + j + 1] += x * y;
            }
        }
        for(int k = len1 + len2 - 1; k > 0; k--) {
            ansArray[k - 1] += ansArray[k] / 10;
            ansArray[k] %= 10;
        }
        StringBuilder ans = new StringBuilder();
        int index = (ansArray[0] == 0) ? 1 : 0;
        while(index < len1 + len2) {
            ans.append(ansArray[index]);
            index++;
        }
        System.out.println(ans);
        return ans.toString();
    }
}
