package com.jan.titlemorethan1000;

/**
 * 1911. 最大交替子序列和
 * 一个下标从 0 开始的数组的 交替和 定义为 偶数 下标处元素之 和 减去 奇数 下标处元素之 和 。
 * 比方说，数组 [4,2,5,3] 的交替和为 (4 + 5) - (2 + 3) = 4 。
 * 给你一个数组 nums ，请你返回 nums 中任意子序列的 最大交替和 （子序列的下标 重新 从 0 开始编号）。
 * 一个数组的 子序列 是从原数组中删除一些元素后（也可能一个也不删除）剩余元素不改变顺序组成的数组。
 * 比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的一个子序列（加粗元素），但是 [2,4,2] 不是。
 *
 * 示例 1：
 * 输入：nums = [4,2,5,3]
 * 输出：7
 * 解释：最优子序列为 [4,2,5] ，交替和为 (4 + 5) - 2 = 7 。
 *
 * 示例 2：
 * 输入：nums = [5,6,7,8]
 * 输出：8
 * 解释：最优子序列为 [8] ，交替和为 8 。
 *
 * 示例 3：
 * 输入：nums = [6,2,1,2,4,5]
 * 输出：10
 * 解释：最优子序列为 [6,1,5] ，交替和为 (6 + 5) - 1 = 10 。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class Title1911 {
    public static void main(String[] args) {
        int[] nums1 = {4,2,5,3};
        int[] nums2 = {5,6,7,8};
        int[] nums3 = {6,2,1,2,4,5};

        System.out.println(maxAlternatingSum(nums1));
        System.out.println(maxAlternatingSum(nums2));
        System.out.println(maxAlternatingSum(nums3));

        System.out.println(maxAlternatingSum2(nums1));
        System.out.println(maxAlternatingSum2(nums2));
        System.out.println(maxAlternatingSum2(nums3));

        System.out.println(maxAlternatingSum3(nums1));
        System.out.println(maxAlternatingSum3(nums2));
        System.out.println(maxAlternatingSum3(nums3));
    }

    /**
     * 动态规划
     * 定义 f[i][0] 表示前 i 个数中长为偶数的子序列的最大交替和，f[i][1] 表示前 i 个数中长为奇数的子序列的最大交替和，对于第 i 个数有选或不选两种决策：
     * 1、对于 f[i+1][0]，若不选第 i 个数，则从 f[i][0] 转移过来，否则从 f[i][1]−nums[i] 转移过来，取二者最大值。
     *    f[i+1][0]=max(f[i][0],f[i][1]−nums[i])
     * 2、对于 f[i+1][1]，若不选第 i 个数，则从 f[i][1] 转移过来，否则从 f[i][0]+nums[i] 转移过来，取二者最大值。
     *    f[i+1][1]=max(f[i][1],f[i][0]+nums[i])
     */
    public static long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        int[][] f = new int[n + 1][2];
        f[0][1] = -nums[0];
        for(int i = 0; i < n; i++) {
            f[i + 1][0] = Math.max(f[i][0], f[i][1] - nums[i]);
            f[i + 1][1] = Math.max(f[i][0] + nums[i], f[i][1]);
        }

        return Math.max(f[n][0], f[n][1]);
    }

    /**
     * 动态规划，空间优化，使用三个变量
     */
    public static long maxAlternatingSum2(int[] nums) {
        int f0 = 0, f1 = -nums[0];
        for(int p : nums) {
            int newF0 = Math.max(f0, f1 - p);
            f1 = Math.max(f0 + p, f1);
            f0 = newF0;
        }
        return Math.max(f0, f1);
    }

    /**
     * 动态规划
     */
    public static long maxAlternatingSum3(int[] nums) {
        long even = nums[0], odd = 0;
        for (int i = 1; i < nums.length; i++) {
            even = Math.max(even, odd + nums[i]);
            odd = Math.max(odd, even - nums[i]);
        }
        return even;
    }
}
