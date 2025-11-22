package com.jan.titlemorethan2000;

/**
 * 2302. 统计得分小于 K 的子数组数目
 * 一个数组的 分数 定义为数组之和 乘以 数组的长度。
 * 比方说，[1, 2, 3, 4, 5] 的分数为 (1 + 2 + 3 + 4 + 5) * 5 = 75 。
 * 给你一个正整数数组 nums 和一个整数 k ，请你返回 nums 中分数 严格小于 k 的 非空整数子数组数目。
 * 子数组 是数组中的一个连续元素序列。
 *
 * 示例 1：
 * 输入：nums = [2,1,4,3,5], k = 10
 * 输出：6
 * 解释：
 * 有 6 个子数组的分数小于 10 ：
 * - [2] 分数为 2 * 1 = 2 。
 * - [1] 分数为 1 * 1 = 1 。
 * - [4] 分数为 4 * 1 = 4 。
 * - [3] 分数为 3 * 1 = 3 。
 * - [5] 分数为 5 * 1 = 5 。
 * - [2,1] 分数为 (2 + 1) * 2 = 6 。
 * 注意，子数组 [1,4] 和 [4,3,5] 不符合要求，因为它们的分数分别为 10 和 36，但我们要求子数组的分数严格小于 10 。
 *
 * 示例 2：
 * 输入：nums = [1,1,1], k = 5
 * 输出：5
 * 解释：
 * 除了 [1,1,1] 以外每个子数组分数都小于 5 。
 * [1,1,1] 分数为 (1 + 1 + 1) * 3 = 9 ，大于 5 。
 * 所以总共有 5 个子数组得分小于 5 。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= 10^15
 */
public class Title2302 {
    public static void main(String[] args) {
        int[] nums1 = {2,1,4,3,5};
        int[] nums2 = {1,1,1};

        Title2302 title2302 = new Title2302();
        System.out.println(title2302.countSubarrays(nums1, 10));
        System.out.println(title2302.countSubarrays(nums2, 5));
    }

    /**
     * 滑动窗口使用前提：
     * 1、连续子数组/子串
     * 2、有单调性。本题元素均为正数，所以子数组越长，分数越高；子数组越短，分数越低。这意味着只要某个子数组的分数小于 k，在该子数组内的更短的子数组，分数也小于 k。
     */
    public long countSubarrays(int[] nums, long k) {
        int left = 0, sum = 0;
        long ans = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum * (right - left + 1) >= k) {
                sum -= nums[left];
                left++;
            }
            ans += right - left + 1;
        }
        return ans;
    }
}
