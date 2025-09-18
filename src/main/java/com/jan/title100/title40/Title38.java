package com.jan.title100.title40;

/**
 * 38. 外观数列
 * 「外观数列」是一个数位字符串序列，由递归公式定义：
 * countAndSay(1) = "1"
 * countAndSay(n) 是 countAndSay(n-1) 的行程长度编码。
 *
 * 行程长度编码（RLE）是一种字符串压缩方法，其工作原理是通过将连续相同字符（重复两次或更多次）替换为字符重复次数（运行长度）和字符的串联。
 * 例如，要压缩字符串 "3322251" ，我们将 "33" 用 "23" 替换，将 "222" 用 "32" 替换，将 "5" 用 "15" 替换并将 "1" 用 "11" 替换。
 * 因此压缩后字符串变为 "23321511"。
 * 给定一个整数 n ，返回 外观数列 的第 n 个元素。
 *
 * 示例 1：
 * 输入：n = 4
 * 输出："1211"
 * 解释：
 * countAndSay(1) = "1"
 * countAndSay(2) = "1" 的行程长度编码 = "11"
 * countAndSay(3) = "11" 的行程长度编码 = "21"
 * countAndSay(4) = "21" 的行程长度编码 = "1211"
 *
 * 示例 2：
 * 输入：n = 1
 * 输出："1"
 * 解释：
 * 这是基本情况。
 *
 * 提示：
 * 1 <= n <= 30
 * 进阶：你能迭代解决该问题吗？
 */
public class Title38 {
    public static void main(String[] args) {
        System.out.println(countAndSay(6));
        System.out.println(countAndSay2(6));
    }

    public static String countAndSay(int n) {
        String s = "1";
        for(int i = 1; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            // 设置每个数字开始和比较数字的索引变量
            int pos = 0;
            int start = 0;
            while(pos < s.length()) {
                // 当每个开始数字和比较数字相同，比较数据索引加1
                while(pos < s.length() && s.charAt(pos) == s.charAt(start)) {
                    pos++;
                }
                // 如果不相等，通过比较数据索引和开始索引差计算数量，再加上开始字符加入到结果中
                sb.append(pos - start).append(s.charAt(start));
                start = pos;
            }
            s = sb.toString();
        }
        return s;
    }

    public static String countAndSay2(int n) {
        String str = "1";
        for (int i = 2; i <= n; ++i) {
            StringBuilder sb = new StringBuilder();

            // 设置每个数字开始和比较数字的索引变量
            int start = 0;
            int pos = 0;

            while (pos < str.length()) {
                // 当每个开始数字和比较数字相同，比较数据索引加1
                while (pos < str.length() && str.charAt(pos) == str.charAt(start)) {
                    pos++;
                }
                // 如果不相等，通过比较数据索引和开始索引差计算数量，再加上开始字符加入到结果中
                sb.append(pos - start).append(str.charAt(start));
                start = pos;
            }
            str = sb.toString();
        }

        return str;
    }
}
