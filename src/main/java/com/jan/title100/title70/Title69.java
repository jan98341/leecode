package com.jan.title100.title70;

/**
 * 69. x 的平方根
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 *
 * 示例 1：
 * 输入：x = 4
 * 输出：2
 *
 * 示例 2：
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 *
 * 提示：
 * 0 <= x <= 2^31 - 1
 */
public class Title69 {
    public static void main(String[] args) {
        System.out.println(mySqrt(4));
        System.out.println(mySqrt(8));
        System.out.println(mySqrt(11));
        System.out.println(mySqrt(17));

        System.out.println(mySqrt2(4));
        System.out.println(mySqrt2(8));
        System.out.println(mySqrt2(11));
        System.out.println(mySqrt2(17));
    }

    /**
     * 二分查找，时间复杂度O(log n)
     */
    public static int mySqrt(int x) {
        int left = 0;
        int right = (int)Math.sqrt(Integer.MAX_VALUE) + 1;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (mid * mid <= x) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * 牛顿迭代
     */
    public static int mySqrt2(int x) {
        if(x == 0) {
            return 0;
        }

        double x0 = x, C = x;
        while(true) {
            double xi = 0.5 * (x0 + C / x0);
            if(Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int)x0;
    }
}
