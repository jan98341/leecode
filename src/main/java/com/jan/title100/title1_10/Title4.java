package com.jan.title100.title1_10;

/**
 * 4、寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 *
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 */
public class Title4 {
    public static void main(String[] args) {
//        int[] nums1 = {1,3};
//        int[] nums2 = {2};

        int[] nums1 = {1,2};
        int[] nums2 = {3,4};

        Title4 title4 = new Title4();
        double res = title4.findMedianSortedArrays(nums1, nums2);
        System.out.println("结果：" + res);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 确保nums1是较短的数组以优化二分次数
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int low = 0, high = m;
        int totalLeft = (m + n + 1) / 2;  // 左半部分总元素数

        while (low <= high) {
            int i = (low + high) / 2;     // nums1的分割点
            int j = totalLeft - i;        // nums2的分割点

            // 处理边界条件
            int nums1Left = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1Right = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int nums2Left = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2Right = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (nums1Left <= nums2Right && nums2Left <= nums1Right) {
                // 计算中位数
                if ((m + n) % 2 == 1) {
                    return Math.max(nums1Left, nums2Left);
                } else {
                    return (Math.max(nums1Left, nums2Left) + Math.min(nums1Right, nums2Right)) / 2.0;
                }
            } else if (nums1Left > nums2Right) {
                high = i - 1;  // 需要减少i
            } else {
                low = i + 1;   // 需要增加i
            }
        }
        return 0.0;  // 根据题目约束，不会执行到此处
    }
}
