package com.jan.title800.title720;

/**
 * 713. 乘积小于 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
 *
 * 示例 1：
 * 输入：nums = [10,5,2,6], k = 100
 * 输出：8
 * 解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2]、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
 *
 * 示例 2：
 * 输入：nums = [1,2,3], k = 0
 * 输出：0
 *
 * 提示
 * 1 <= nums.length <= 3 * 10^4
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 10^6
 */
public class Title713 {
    public static void main(String[] args) {
        int[] nums1 = {10,5,2,6};
        int[] nums2 = {1,2,3};

        Title713 title713 = new Title713();
        System.out.println(title713.numSubarrayProductLessThanK(nums1, 100));
        System.out.println(title713.numSubarrayProductLessThanK(nums2, 0));
    }

    /**
     * 内层循环结束后，[left,right] 这个子数组是满足题目要求的。由于子数组越短，越能满足题目要求，
     * 所以除了 [left,right]，还有 [left+1,right],[left+2,right],…,[right,right] 都是满足要求的。
     * 也就是说，当右端点固定在 right 时，左端点在 left,left+1,left+2,…,right 的所有子数组都是满足要求的，这一共有 right−left+1 个，加到答案中。
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k <= 1) {
            return 0;
        }

        int ans = 0, left = 0, prod = 1;
        for (int rihgt = 0; rihgt < nums.length; rihgt++) {
            prod *= nums[rihgt];
            while (prod >= k) {
                prod /= nums[left];
                left++;
            }
            // 对于固定的 right，有 right-left+1 个合法的左端点
            ans += rihgt - left + 1;
        }

        return ans;
    }
}
