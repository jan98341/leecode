package com.jan.title100.title50;

/**
 * 50. Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。
 *
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 *
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 *
 * 示例 3：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *
 * 提示：
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * n 是一个整数
 * 要么 x 不为零，要么 n > 0 。
 * -10^4 <= xn <= 10^4
 */
public class Title50 {
    public static void main(String[] args) {
        double x1 = 2.0000;
        int n1 = 10;
        double x2 = 2.1000;
        int n2 = 3;
        double x3 = 2.0000;
        int n3 = -2;
        double x4 = 1.0000;
        int n4 = 2147483647;
        double x5 = 3;
        int n5 = 0;
        double x6 = 2;
        int n6 = -2147483648;
//        System.out.println(myPow(x1, n1));
//        System.out.println(myPow(x2, n2));
//        System.out.println(myPow(x3, n3));
//        System.out.println(myPow(x4, n4));

//        System.out.println(myPow2(x1, n1));
//        System.out.println(myPow2(x2, n2));
//        System.out.println(myPow2(x3, n3));
//        System.out.println(myPow2(x4, n4));
//        System.out.println(myPow2(x5, n5));
//        System.out.println(myPow2(x6, n6));

        System.out.println(myPow4(x1, n1));
        System.out.println(myPow4(x2, n2));
        System.out.println(myPow4(x3, n3));
        System.out.println(myPow4(x4, n4));
        System.out.println(myPow4(x5, n5));
        System.out.println(myPow4(x6, n6));
    }

    public static double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }

        int count = Math.abs(n);
        double ans = 1.0;
        for (int i = 0; i < count; i++) {
            ans *= x;
        }
        if(n < 0) {
            ans = 1.0 / ans;
        }

        return ans;
    }

    public static double myPow2(double x, int n) {
        double ans = 1.0;
        long count = n;
        count = Math.abs(count);
        while (count > 0) {
            if ((count & 1) == 1) ans *= x;
            x *= x;
            count >>= 1;
        }
        if(n < 0) ans = 1.0 / ans;

        return ans;
    }

    public static double myPow3(double x, int n) {
        double ans = 1.0;
        long count = n;
        if(n < 0) {
            count = -count;
            x = 1 / x;
        }
        while (count > 0) {
            if ((count & 1) == 1) ans *= x;
            x *= x;
            count >>= 1;
        }

        return ans;
    }

    public static double myPow4(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public static double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }
}
