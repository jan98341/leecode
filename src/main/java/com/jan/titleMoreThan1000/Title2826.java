package com.jan.titleMoreThan1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2826. 将三个组排序
 * 给你一个整数数组 nums 。nums 的每个元素是 1，2 或 3。在每次操作中，你可以删除 nums 中的一个元素。返回使 nums 成为 非递减 顺序所需操作数的 最小值。
 *
 * 示例 1：
 * 输入：nums = [2,1,3,2,1]
 * 输出：3
 * 解释：
 * 其中一个最优方案是删除 nums[0]，nums[2] 和 nums[3]。
 *
 * 示例 2：
 * 输入：nums = [1,3,2,1,3,3]
 * 输出：2
 * 解释：
 * 其中一个最优方案是删除 nums[1] 和 nums[2]。
 *
 * 示例 3：
 * 输入：nums = [2,2,2,2,3,3]
 * 输出：0
 * 解释：
 * nums 已是非递减顺序的。
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 3
 * 进阶：你可以使用 O(n) 时间复杂度以内的算法解决吗？
 */
public class Title2826 {
    public static void main(String[] args) {
        List nums1 = Arrays.asList(2, 1, 3, 2, 1);
        List nums2 = Arrays.asList(1,3,2,1,3,3);
        List nums3 = Arrays.asList(2,2,2,2,3,3);

        System.out.println(minimumOperations(nums1));
        System.out.println(minimumOperations(nums2));
        System.out.println(minimumOperations(nums3));

        System.out.println(minimumOperations2(nums1));
        System.out.println(minimumOperations2(nums2));
        System.out.println(minimumOperations2(nums3));

        System.out.println(minimumOperations3(nums1));
        System.out.println(minimumOperations3(nums2));
        System.out.println(minimumOperations3(nums3));
    }

    /**
     * 先求最长非递减子序列长度(动态规划)，再通过数组长度减去该长度为所求值
     * 最长非递减子序列长度 解法参见300题目
     */
    public static int minimumOperations(List<Integer> nums) {
        int n = nums.size(), max = 0;
        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = 1;
            for(int j = 0; j < i; j++) {
                if(nums.get(i) >= nums.get(j)) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            max = Math.max(max, f[i]);
        }
        return nums.size() - max;
    }

    /**
     * 先求最长非递减子序列长度(二分法)，再通过数组长度减去该长度为所求值
     */
    public static int minimumOperations2(List<Integer> nums) {
        List<Integer> g = new ArrayList<>();
        for(int p : nums) {
            int j = upperBound(p, g);
            if(j == g.size()) {
                g.add(p);
            } else {
                g.set(j, p);
            }
        }

        return nums.size() - g.size();
    }
    private static int upperBound(int p, List<Integer> g) {
        int left = -1, right = g.size();
        while(left + 1 < right) {
            int mid = (left + right) >>> 1;
            if(g.get(mid) > p) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    /**
     * 多维 DP ???
     * 由于值域很小，考虑把值域当作 DP 的一个维度。
     * 定义 f[i+1][j] 表示 nums[0] 到 nums[i] 的最长非递减子序列的长度，其中子序列最后一个数 ≤j。
     * f[i+1][j] 怎么算？设 x=nums[i]，分类讨论：
     * 1、不选 x，问题变成 nums[0] 到 nums[i−1] 的最长非递减子序列的长度，其中子序列最后一个数 ≤j，即 f[i+1][j]=f[i][j]。
     * 2、选 x，也就是把 x 当作子序列最后一个数，需要满足 x≤j。问题变成 nums[0] 到 nums[i−1] 的最长非递减子序列的长度，
     *    其中子序列最后一个数 ≤x，即 f[i+1][j]=f[i][x]+1。
     */
    public static int minimumOperations3(List<Integer> nums) {
        int n = nums.size();
        int[][] f = new int[n + 1][4];
        for(int i = 0; i < n; i++) {
            int x = nums.get(i);
            for(int j = 1; j <= 3; j++) {
                if(j < x) {
                    f[i + 1][j] = f[i][j];
                } else {
                    f[i + 1][j] = Math.max(f[i][j], f[i][x] + 1);
                }
            }
        }
        return n - f[n][3];
    }
}
