package com.jan.titleMoreThan1000;

import java.util.Arrays;

/**
 * 3040. 相同分数的最大操作数目 II
 * 给你一个整数数组 nums ，如果 nums 至少 包含 2 个元素，你可以执行以下操作中的 任意 一个：
 * 1、选择 nums 中最前面两个元素并且删除它们。
 * 2、选择 nums 中最后两个元素并且删除它们。
 * 3、选择 nums 中第一个和最后一个元素并且删除它们。
 * 一次操作的 分数 是被删除元素的和。
 * 在确保 所有操作分数相同 的前提下，请你求出 最多 能进行多少次操作。
 * 请你返回按照上述要求 最多 可以进行的操作次数。
 *
 * 示例 1：
 * 输入：nums = [3,2,1,2,3,4]
 * 输出：3
 * 解释：我们执行以下操作：
 * - 删除前两个元素，分数为 3 + 2 = 5 ，nums = [1,2,3,4] 。
 * - 删除第一个元素和最后一个元素，分数为 1 + 4 = 5 ，nums = [2,3] 。
 * - 删除第一个元素和最后一个元素，分数为 2 + 3 = 5 ，nums = [] 。
 * 由于 nums 为空，我们无法继续进行任何操作。
 *
 * 示例 2：
 * 输入：nums = [3,2,6,1,4]
 * 输出：2
 * 解释：我们执行以下操作：
 * - 删除前两个元素，分数为 3 + 2 = 5 ，nums = [6,1,4] 。
 * - 删除最后两个元素，分数为 1 + 4 = 5 ，nums = [6] 。
 * 至多进行 2 次操作。
 *
 * 提示：
 * 2 <= nums.length <= 2000
 * 1 <= nums[i] <= 1000
 */
public class Title3040 {
    public static void main(String[] args) {
        int[] nums1 = {3,2,1,2,3,4};
        int[] nums2 = {3,2,6,1,4};

        Title3040 title3040 = new Title3040();
        System.out.println(title3040.maxOperations(nums1));
        System.out.println(title3040.maxOperations(nums2));

        System.out.println(title3040.maxOperations2(nums1));
        System.out.println(title3040.maxOperations2(nums2));
    }

    /**
     * 记忆化搜索
     * 前两个数和、后两个数和、前后两个数和，要求所有操作分数相同，那对应着至多三种不同的元素和
     * 枚举三种操作方式，分别从 dfs(i+2,j)+1,dfs(i,j−2)+1,dfs(i+1,j−1)+1 转移过来（如果能操作），取最大值，即为 dfs(i,j)。
     * 递归终点：如果 i≥j，此时至多剩下一个数，无法操作，返回 0
     * 递归入口：根据三种初始操作，分别为 dfs(2,n−1),dfs(0,n−3),dfs(1,n−2)。三者取最大值再加一（加上第一次操作），即为答案
     */
    private int[][] memo;
    private int[] nums;
    public int maxOperations(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        memo = new int[n][n];
        for(int[] row : memo) {
            Arrays.fill(row, -1);
        }
        int res1 = dfs(2, n - 1, nums[0] + nums[1]);
        int res2 = dfs(0, n - 3, nums[n - 2] + nums[n - 1]);
        int res3 = dfs(1, n - 2, nums[0] + nums[n - 1]);
        return Math.max(res1, Math.max(res2, res3)) + 1;
    }

    private int dfs(int i, int j, int target) {
        if(i >= j) {
            return 0;
        }
        if(memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = 0;
        if(nums[i] + nums[i + 1] == target) {
            res = Math.max(res, dfs(i + 2, j, target) + 1);
        }
        if(nums[j - 1] + nums[j] == target) {
            res = Math.max(res, dfs(i, j - 2, target) + 1);
        }
        if(nums[i] + nums[j] == target) {
            res = Math.max(res, dfs(i + 1, j - 1, target) + 1);
        }
        return memo[i][j] = res;
    }

    /**
     * 记忆化搜索优化
     * 答案最大是 ⌊n/2⌋。如果可以递归到 i≥j 的状态，说明可以执行 ⌊n/2⌋ 次操作，不需要再计算了，直接返回
     */
    private boolean done;
    public int maxOperations2(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        memo = new int[n][n];
        for(int[] row : memo) {
            Arrays.fill(row, -1);
        }

        int res1 = helper(2, n - 1, nums[0] + nums[1]);
        int res2 = helper(0, n - 3, nums[n - 2] + nums[n - 1]);
        int res3 = helper(1, n - 2, nums[0] + nums[n - 1]);
        return Math.max(res1, Math.max(res2, res3)) + 1;
    }

    private int helper(int i, int j, int target) {
        if(done) {
            return 0;
        }
        return dfs(i, j, target);
    }

    private int dfs1(int i, int j, int target) {
        if(done) {
            return 0;
        }

        if(i >= j) {
            done = true;
            return 0;
        }
        if(memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = 0;
        if(nums[i] + nums[i + 1] == target) {
            res = Math.max(res, dfs1(i + 2, j, target) + 1);
        }
        if(nums[j - 1] + nums[j] == target) {
            res = Math.max(res, dfs1(i, j - 2, target) + 1);
        }
        if(nums[i] + nums[j] == target) {
            res = Math.max(res, dfs1(i + 1, j - 1, target) + 1);
        }
        return memo[i][j] = res;
    }
}
