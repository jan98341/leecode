package com.jan.titlemorethan2000;

/**
 * 2958. 最多 K 个重复元素的最长子数组
 * 给你一个整数数组 nums 和一个整数 k 。
 * 一个元素 x 在数组中的 频率 指的是它在数组中的出现次数。
 * 如果一个数组中所有元素的频率都 小于等于 k ，那么我们称这个数组是 好 数组。
 * 请你返回 nums 中 最长好 子数组的长度。
 * 子数组 指的是一个数组中一段连续非空的元素序列。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,1,2,3,1,2], k = 2
 * 输出：6
 * 解释：最长好子数组是 [1,2,3,1,2,3] ，值 1 ，2 和 3 在子数组中的频率都没有超过 k = 2 。[2,3,1,2,3,1] 和 [3,1,2,3,1,2] 也是好子数组。
 * 最长好子数组的长度为 6 。
 *
 * 示例 2：
 * 输入：nums = [1,2,1,2,1,2,1,2], k = 1
 * 输出：2
 * 解释：最长好子数组是 [1,2] ，值 1 和 2 在子数组中的频率都没有超过 k = 1 。[2,1] 也是好子数组。
 * 最长好子数组的长度为 2 。
 *
 * 示例 3：
 * 输入：nums = [5,5,5,5,5,5,5], k = 4
 * 输出：4
 * 解释：最长好子数组是 [5,5,5,5] ，值 5 在子数组中的频率没有超过 k = 4 。
 * 最长好子数组的长度为 4 。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= nums.length
 */
public class Title2958 {
    public static void main(String[] args) {
        int[] nums1 = {1,2,3,1,2,3,1,2};
        int[] nums2 = {1,2,1,2,1,2,1,2};
        int[] nums3 = {5,5,5,5,5,5,5};

        Title2958 title2958 = new Title2958();
        System.out.println(title2958.maxSubarrayLength(nums1, 2));
        System.out.println(title2958.maxSubarrayLength(nums2, 1));
        System.out.println(title2958.maxSubarrayLength(nums3, 4));
    }

    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length, ans = 0, left = 0;
        int[] cnt = new int[11]; //  Map<Integer, Integer> cnt = new HashMap<>();
        for(int right = 0; right < n; right++) {
            int x = nums[right];
            cnt[x]++; // cnt.merge(nums[right], 1, Integer::sum)
            while(cnt[x] > k) {
                cnt[nums[left]]--; // cnt.merge(nums[left], -1, Integer::sum);
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
