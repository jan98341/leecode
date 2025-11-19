package com.jan.title300.title210;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 *
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 * 提示：
 * 1 <= target <= 10^9
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 *
 * 进阶：
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 */
public class Title209 {
    public static void main(String[] args) {
        int[] nums1 = {2,3,1,2,4,3};
        int[] nums2 = {1,4,4};
        int[] nums3 = {1,1,1,1,1,1,1,1};

        Title209 title209 = new Title209();
        System.out.println(title209.minSubArrayLen(7, nums1));
        System.out.println(title209.minSubArrayLen(4, nums2));
        System.out.println(title209.minSubArrayLen(11, nums3));
    }

    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length, sum = 0, ans = Integer.MAX_VALUE, flag = 0;
        for(int i = 0; i < n; i++) {
            sum += nums[i];
            while (sum >= target) {
                ans = Math.min(ans, i - flag + 1);
                sum -= nums[flag++];
            }
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
