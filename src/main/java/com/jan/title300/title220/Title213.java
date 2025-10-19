package com.jan.title300.title220;

/**
 * 213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 3：
 * 输入：nums = [1,2,3]
 * 输出：3
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
public class Title213 {
    public static void main(String[] args) {
        int[] nums1 = {2,3,2};
        int[] nums2 = {1,2,3,1};
        int[] nums3 = {1,2,3};

        System.out.println(rob(nums1));
        System.out.println(rob(nums2));
        System.out.println(rob(nums3));

        System.out.println(rob2(nums1));
        System.out.println(rob2(nums2));
        System.out.println(rob2(nums3));
    }

    /**
     * 动态规划解题，基于198题目解法分类：
     * 1、如果偷 nums[0]，那么 nums[1] 和 nums[n−1] 不能偷，问题变成从 nums[2] 到 nums[n−2] 的非环形版本，调用 198 题的代码解决；
     * 2、如果不偷 nums[0]，那么问题变成从 nums[1] 到 nums[n−1] 的非环形版本，同样调用 198 题的代码解决。
     */
    public static int rob(int[] nums) {
        int n = nums.length;
        return Math.max(nums[0] + rob1(nums, 2, n - 1), rob1(nums, 1, n));
    }

    /**
     * 求指定开始和结尾数组最大金额，以下空间复杂度为O(n)
     */
    public static int rob1(int[] nums, int start, int end) {
        int[] f = new int[end + 2];
        for (int i = start; i < end; i++) {
            f[i + 2] = Math.max(f[i] + nums[i], f[i + 1]);
        }
        return f[end + 1];
    }

    public static int rob2(int[] nums) {
        int n = nums.length;
        return Math.max(nums[0] + rob3(nums, 2, n - 1), rob3(nums, 1, n));
    }

    /**
     * 求指定开始和结尾数组最大金额，以下空间复杂度为O(1)
     */
    public static int rob3(int[] nums, int start, int end) {
        int f0 = 0, f1 = 0;
        for (int i = start; i < end; i++) {
            int new_f = Math.max(f0 + nums[i], f1);
            f0 = f1;
            f1 = new_f;
        }
        return f1;
    }
}
