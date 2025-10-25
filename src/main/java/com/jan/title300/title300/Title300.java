package com.jan.title300.title300;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 *
 * 示例 3：
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 * 提示：
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *
 * 进阶：
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 */
public class Title300 {
    public static void main(String[] args) {
        int[] nums1 = {10,9,2,5,3,7,101,18};
        int[] nums2 = {0,1,0,3,2,3};
        int[] nums3 = {7,7,7,7,7,7,7};
        System.out.println(lengthOfLIS(nums1));
        System.out.println(lengthOfLIS(nums2));
        System.out.println(lengthOfLIS(nums3));
    }

    public static int lengthOfLIS(int[] nums) {
        int n = nums.length, ans = 0;
        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    f[i] = Math.max(f[i], f[j]);
                }
            }
            ans = Math.max(ans, ++f[i]);
        }

        return ans;
    }
}
