package com.jan.title500.title500;

import java.util.Arrays;

/**
 * 494. 目标和
 * 给你一个非负整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * 示例 1：
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 *
 * 示例 2：
 * 输入：nums = [1], target = 1
 * 输出：1
 *
 * 提示：
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 */
public class Title494 {
    public static void main(String[] args) {
        int[] nums1 = {1,1,1,1,1};
        int[] nums2 = {1};
        System.out.println(findTargetSumWays(nums1, 3));
        System.out.println(findTargetSumWays(nums2, 1));

        System.out.println(findTargetSumWays2(nums1, 3));
        System.out.println(findTargetSumWays2(nums2, 1));

        System.out.println(findTargetSumWays3(nums1, 3));
        System.out.println(findTargetSumWays3(nums2, 1));

        System.out.println(findTargetSumWays4(nums1, 3));
        System.out.println(findTargetSumWays4(nums2, 1));
    }

    /**
     * 递归搜索 + 保存计算结果 = 记忆化搜索
     * 本题看上去与背包问题无关，但实际为0/1背包的变形问题，我们假设数组和为sum，添加+号的数和为p，那么添加-号的和为sum-p，
     * 则有p-(sum-p)=target，则2p=target+sum，则题目的要求可以写为选择数组中的数，使其和恰好为(target+sum)/2。
     */
    public static int findTargetSumWays(int[] nums, int target) {
        // 获取数组元素总和
        int s = 0;
        for (int num : nums) {
            s += num;
        }

        // s需大于0且为偶数
        s -= Math.abs(target);
        if(s < 0 || s % 2 == 1) {
            return 0;
        }

        int n = nums.length;
        int m = s / 2;
        int[][] memo = new int[n][m + 1];
        for(int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dfs(n - 1, m, nums, memo);
    }

    private static int dfs(int i, int c, int[] nums, int[][] memo) {
        // 只有c=0表示刚好满足条件，方法数量加一
        if(i < 0) {
            return c == 0 ? 1 : 0;
        }
        // 在记忆中计算结果
        if(memo[i][c] != -1) {
            return memo[i][c];
        }
        // 不选
        int res = dfs(i - 1, c, nums, memo);
        // 如果满足条件，选
        if(c >= nums[i]) {
            res += dfs(i - 1, c - nums[i], nums, memo);
        }
        // 加入到记忆结果中并返回
        return memo[i][c] = res;
    }

    /**
     * 递推，时间复杂度：O(nm)，空间复杂度：O(nm)
     * p=(sum-|target|)/2
     */
    public static int findTargetSumWays2(int[] nums, int target) {
        // 获取数组元素总和
        int s = 0;
        for (int num : nums) {
            s += num;
        }

        // s需大于0且为偶数
        s -= Math.abs(target);
        if(s < 0 || s % 2 == 1) {
            return 0;
        }

        int n = nums.length;
        int m = s / 2;
        int[][] f = new int[n + 1][m + 1];
        f[0][0] = 1;
        for(int i = 0; i < n; i++) {
            for(int c = 0; c <= m; c++) {
                // 当前塞入包裹体积值大于包裹可容纳值，则当前计算值=前一元素计算值
                if(c < nums[i]) {
                    f[i + 1][c] = f[i][c];
                }
                // 当前物品可塞入包裹，则当前计算值=前一元素计算值+前前一元素计算值
                else {
                    f[i + 1][c] = f[i][c] + f[i][c - nums[i]];
                }
            }
        }
        return f[n][m];
    }

    /**
     * 递推，空间优化：两个数组（滚动数组）
     */
    public static int findTargetSumWays3(int[] nums, int target) {
        // 获取数组元素总和
        int s = 0;
        for (int num : nums) {
            s += num;
        }

        // s需大于0且为偶数
        s -= Math.abs(target);
        if(s < 0 || s % 2 == 1) {
            return 0;
        }

        int m = s / 2;
        int n = nums.length;
        int[][] f = new int[2][m + 1];
        f[0][0] = 1;
        for(int i = 0; i < n; i++) {
            for(int c = 0; c <= m; c++) {
                // 当前塞入包裹体积值大于包裹可容纳值，则当前计算值=前一元素计算值
                if(c < nums[i]) {
                    f[(i + 1)%2][c] = f[i%2][c];
                }
                // 当前物品可塞入包裹，则当前计算值=前一元素计算值+前前一元素计算值
                else {
                    f[(i + 1)%2][c] = f[i%2][c] + f[i%2][c - nums[i%2]];
                }
            }
        }

        return f[n%2][m];
    }

    /**
     * 空间优化：一个数组
     */
    public static int findTargetSumWays4(int[] nums, int target) {
        // 获取数组元素总和
        int s = 0;
        for (int num : nums) {
            s += num;
        }

        // s需大于0且为偶数
        s -= Math.abs(target);
        if(s < 0 || s % 2 == 1) {
            return 0;
        }

        int m = s / 2;
        int[] f = new int[m + 1];
        f[0] = 1;
        for(int x : nums) {
            for(int c = m; c >= x; c--) {
                f[c] += f[c - x];
            }
        }
        return f[m];
    }
}
