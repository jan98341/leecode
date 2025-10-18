package com.jan.title200.title200;

import java.util.Arrays;

/**
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class Title198 {
    public static void main(String[] args) {
        int[] nums1 = {1,2,3,1};
        int[] nums2 = {2,7,9,3,1};

        System.out.println(rob(nums1));
//        System.out.println(rob(nums2));
//
//        System.out.println(rob2(nums1));
//        System.out.println(rob2(nums2));
//
//        System.out.println(rob3(nums1));
//        System.out.println(rob3(nums2));
    }

    /**
     * 使用动态规划解题,空间复杂度为O(n)
     * 动态转移方程 f[i]=Max(f[i-1], f[i-2 ]+nums[i])，可以调整为 f[i+2]=Max(f[i+1], f[i]+nums[i])
     */
    public static int rob(int[] nums) {
        int n = nums.length;
        int[] f = new int[n + 2];
        for (int i = 0; i < n; i++) {
            f[i + 2] = Math.max(f[i + 1], f[i] + nums[i]);
        }
        return f[n + 1];
    }

    /**
     * 使用动态规划解题,空间复杂度为O(1)
     */
    public static int rob2(int[] nums) {
        int n = nums.length;
        int f0 = 0, f1 = 0;
        for (int i = 0; i < n; i++) {
            int new_f = Math.max(f1, f0 + nums[i]);
            f0 = f1;
            f1 = new_f;
        }
        return f1;
    }

    /**
     * 递归搜索 + 保存计算结果 = 记忆化搜索
     */
    public static int rob3(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return dfs(n - 1, nums, memo);
    }

    // dfs(i) 表示从 nums[0] 到 nums[i] 最多能偷多少
    private static int dfs(int i, int[] nums, int[] memo) {
        if(i < 0) {
            return 0;
        }
        if(memo[i] != -1) {
            return memo[i];
        }
        int noChoose = dfs(i - 1, nums, memo);
        int choose = dfs(i - 2, nums, memo) + nums[i];

        return memo[i] = Math.max(noChoose, choose);
    }
}
