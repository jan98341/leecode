package com.jan.title100.title20;

import java.util.Arrays;

/**
 * 16、最接近的三数之和
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 * 返回这三个数的和。
 * 假定每组输入只存在恰好一个解。
 *
 * 示例 1：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2)。
 *
 * 示例 2：
 * 输入：nums = [0,0,0], target = 1
 * 输出：0
 * 解释：与 target 最接近的和是 0（0 + 0 + 0 = 0）。
 *
 *
 * 提示：
 * 3 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * -10^4 <= target <= 10^4
 */
public class Title16 {
    public static void main(String[] args) {
        int[] nums1 = {-1,2,1,-4};
        System.out.println("用例1结果：" + threeSumClosest(nums1, 1));

        int[] nums2 = {0,0,0};
        System.out.println("用例2结果：" + threeSumClosest(nums2, 0));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int s = nums[0] + nums[1] + nums[2];
        int ans = s;
        int diff = Math.abs(target - s);
        for (int i = 0; i < nums.length; i++) {
            // 跳过重复值
            if(i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            // 双指针
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                s = nums[i] + nums[left] + nums[right];
                if(s > target) {
                    // 如果出现更小差值，记录更小差值及合计值
                    if((s - target) < diff) {
                        ans = s;
                        diff = s - target;
                    }
                    right--;
                } else if(s < target) {
                    // 如果出现更小差值，记录更小差值及合计值
                    if((target - s) < diff) {
                        ans = s;
                        diff = target - s;
                    }
                    left++;
                } else {
                    // 合计值为目标值，直接返回
                    return s;
                }
            }
        }

        return ans;
    }
}
