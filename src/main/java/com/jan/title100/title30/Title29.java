package com.jan.title100.title30;

/**
 * 29. 两数相除
 * 给你两个整数，被除数 dividend 和除数 divisor。将两数相除，要求 不使用 乘法、除法和取余运算。
 * 整数除法应该向零截断，也就是截去（truncate）其小数部分。例如，8.345 将被截断为 8 ，-2.7335 将被截断至 -2 。
 * 返回被除数 dividend 除以除数 divisor 得到的 商 。
 * 注意：假设我们的环境只能存储 32 位 有符号整数，其数值范围是 [−2^31,  2^31 − 1] 。本题中，如果商 严格大于 2^31 − 1 ，则返回 2^31 − 1 ；如果商 严格小于 -2^31 ，则返回 -2^31 。
 *
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = 3.33333.. ，向零截断后得到 3 。
 *
 * 示例 2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = -2.33333.. ，向零截断后得到 -2 。
 *
 * 提示：
 * -2^31 <= dividend, divisor <= 2^31 - 1
 * divisor != 0
 */
public class Title29 {
    public static void main(String[] args) {
        int dividend1 = 10;
        int divisor1 = 3;

        int dividend2 = 7;
        int divisor2 = -3;

        int dividend3 = -1;
        int divisor3 = -1;

        int dividend4 = 37;
        int divisor4 = -5;

//        System.out.println(divide(dividend1, divisor1));
//        System.out.println(divide(dividend2, divisor2));
//        System.out.println(divide(dividend3, divisor3));

//        System.out.println(divide2(dividend1, divisor1));
//        System.out.println(divide2(dividend2, divisor2));
//        System.out.println(divide2(dividend3, divisor3));
        System.out.println(divide3(dividend4, divisor4));
    }

    public static int divide(int dividend, int divisor) {
        boolean negative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);
        int absDividend = Math.abs(dividend);
        int absDivisor = Math.abs(divisor);
        int ans = 0;

        int remainder = absDividend;
        while (remainder >= absDivisor) {
            remainder -= absDivisor;
            ans ++;
        }

        return ans * (negative ? -1 : 1);
    }

    public static int divide2(int dividend, int divisor) {
        long x = dividend;
        long y = divisor;
        boolean negative = (x > 0 && y < 0) || (x < 0 && y > 0);
        if(x < 0) x = -x;
        if(y < 0) y = -y;
        long l = 0, r = x;
        while (l < r) {
            long mid = l + r + 1 >> 1;
            long result = mul(mid, y);
            if(result <= x) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        long ans = negative ? -l : l;
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) return Integer.MAX_VALUE;
        return (int)ans;
    }

    /**
     * 快速乘法步骤：
     * 初始化结果ans=0
     * 如果k的当前最低位为 1，将a加入结果。
     * 将a左移一位（乘以 2），k右移一位（除以 2）。
     * 重复直到b=0
     */
    private static long mul(long a, long k) {
        // 返回结果
        long ans = 0;
        while (k > 0) {
            // 检查 b 的最低位，如果最低位是 1，将 a 加入结果
            if((k & 1) == 1) ans += a;
            // k 右移（相当于除以 2）
            k >>= 1;
            // a 左移（相当于乘以 2）
            a <<= 1;
        }

        return ans;
    }

    /**
     *  主要思路：寻找被除数可除范围内最大 除数 * 2^n 值，其中最大值通过位操作符实现，比如37/5，第一轮寻找的是 5 * 2^2，
     *  剩余被除数是17， 第二轮寻找的是
     */
    private static int divide3(int dividend, int divisor) {
        // 处理唯一会溢出的情况：-2^31 / -1 = 2^31（超出32位有符号整数范围）
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 记录商的符号（异或：不同为负，相同为正）
        int sign = (dividend ^ divisor) < 0 ? -1 : 1;

        // 将被除数和除数转为负数计算（避免正数溢出，因为MIN_VALUE的绝对值比MAX_VALUE大1）
        int dvd = dividend > 0 ? -dividend : dividend;
        int dvs = divisor > 0 ? -divisor : divisor;

        int quotient = 0;
        // 当被除数仍大于等于除数（均为负数，相当于原正数的被除数 >= 除数）
        while (dvd <= dvs) {
            int temp = dvs;  // 临时保存当前除数倍数
            int step = 1;    // 当前倍数对应的步数

            // 寻找最大的2^n倍除数，使得其仍 <= 被除数（左移即乘2）
            while (dvd - temp <= temp) {  // 等价于 dvd <= temp + temp（避免溢出）
                temp <<= 1;   // 除数翻倍（乘2）
                step <<= 1;   // 步数翻倍
            }

            // 减去最大倍数的除数，并累加步数
            dvd -= temp;
            quotient += step;
        }

        // 根据符号返回结果
        return sign > 0 ? quotient : -quotient;
    }
}
