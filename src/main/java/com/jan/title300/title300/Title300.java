package com.jan.title300.title300;

import java.util.ArrayList;
import java.util.List;

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

        System.out.println(lengthOfLIS2(nums1));
        System.out.println(lengthOfLIS2(nums2));
        System.out.println(lengthOfLIS2(nums3));

        System.out.println(lengthOfLIS3(nums1));
        System.out.println(lengthOfLIS3(nums2));
        System.out.println(lengthOfLIS3(nums3));
    }

    /**
     * 递推，使用一维数组
     */
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

    /**
     * 递推，使用一维数组
     */
    public static int lengthOfLIS2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] f = new int[n];
        f[0] = 1;
        int ans = 1;
        for (int i = 1; i < n; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    /**
     * 贪心 + 二分查找
     * 设当前已求出的最长上升子序列的长度为 len（初始时为 1），从前往后遍历数组 nums，在遍历到 nums[i] 时：
     * 1、如果 nums[i]>d[len] ，则直接加入到 d 数组末尾，并更新 len=len+1；
     * 2、否则，在 d 数组中二分查找，找到第一个比 nums[i] 小的数 d[k] ，并更新 d[k+1]=nums[i]。
     */
    public static int lengthOfLIS3(int[] nums) {
        List<Integer> g = new ArrayList<>();
        for(int x : nums){
            int j = lowerBound(g, x);
            if(g.size() == j){
                g.add(x);
            } else {
                g.set(j, x);
            }
        }

        return g.size();
    }

    private static int lowerBound(List<Integer> g, int target) {
        int left = -1, right = g.size();
        while(left + 1 < right) {
            int mid = (left + right) >>> 1;
            if(g.get(mid) < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
