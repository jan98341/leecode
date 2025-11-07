package com.jan.titleMoreThan1000;

import java.util.Arrays;

/**
 * 1770. 执行乘法运算的最大分数
 * 给你两个长度分别 n 和 m 的整数数组 nums 和 multipliers ，其中 n >= m ，数组下标 从 1 开始 计数。
 * 初始时，你的分数为 0 。你需要执行恰好 m 步操作。在第 i 步操作（从 1 开始 计数）中，需要：
 * 选择数组 nums 开头处或者末尾处 的整数 x 。
 * 你获得 multipliers[i] * x 分，并累加到你的分数中。
 * 将 x 从数组 nums 中移除。
 * 在执行 m 步操作后，返回 最大 分数。
 *
 * 示例 1：
 * 输入：nums = [1,2,3], multipliers = [3,2,1]
 * 输出：14
 * 解释：一种最优解决方案如下：
 * - 选择末尾处的整数 3 ，[1,2,3] ，得 3 * 3 = 9 分，累加到分数中。
 * - 选择末尾处的整数 2 ，[1,2] ，得 2 * 2 = 4 分，累加到分数中。
 * - 选择末尾处的整数 1 ，[1] ，得 1 * 1 = 1 分，累加到分数中。
 * 总分数为 9 + 4 + 1 = 14 。
 *
 * 示例 2：
 * 输入：nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
 * 输出：102
 * 解释：一种最优解决方案如下：
 * - 选择开头处的整数 -5 ，[-5,-3,-3,-2,7,1] ，得 -5 * -10 = 50 分，累加到分数中。
 * - 选择开头处的整数 -3 ，[-3,-3,-2,7,1] ，得 -3 * -5 = 15 分，累加到分数中。
 * - 选择开头处的整数 -3 ，[-3,-2,7,1] ，得 -3 * 3 = -9 分，累加到分数中。
 * - 选择末尾处的整数 1 ，[-2,7,1] ，得 1 * 4 = 4 分，累加到分数中。
 * - 选择末尾处的整数 7 ，[-2,7] ，得 7 * 6 = 42 分，累加到分数中。
 * 总分数为 50 + 15 - 9 + 4 + 42 = 102 。
 *
 * 提示：
 * n == nums.length
 * m == multipliers.length
 * 1 <= m <= 10^3
 * m <= n <= 10^5
 * -1000 <= nums[i], multipliers[i] <= 1000
 */
public class Title1770 {
    public static void main(String[] args) {
        int[] nums11 = {1,2,3}, multipliers12 = {3,2,1};
        int[] nums21 = {-5,-3,-3,-2,7,1}, multipliers22 = {-10,-5,3,4,6};

        System.out.println(maximumScore(nums11, multipliers12));
        System.out.println(maximumScore(nums21, multipliers22));

        System.out.println(maximumScore2(nums11, multipliers12));
        System.out.println(maximumScore2(nums21, multipliers22));
    }

    /**
     * i为nums正数第i个数，j为nums倒数第j个数，k为multipliers正数第k个数，他们之间的关系为：(i)+(n-1-j) == k
     */
    public static int maximumScore(int[] nums, int[] multipliers) {
        int m = multipliers.length;
        int[][] memo = new int[m][m];
        for(int[] row : memo){
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        return dfs(0, nums.length - 1, 0, nums, multipliers, memo);
    }

    private static int dfs(int i, int j, int k, int[] nums, int[] mults, int[][] memo) {
        if(k == mults.length){
            return 0;
        }
        if(memo[i][k] != Integer.MIN_VALUE){
            return memo[i][k];
        }
        int l = dfs(i + 1, j, k + 1, nums, mults, memo) + nums[i] * mults[k];
        int r = dfs(i, j - 1, k + 1, nums, mults, memo) + nums[j] * mults[k];
        return memo[i][k] = Math.max(l, r);
    }

    /**
     * 定义二维数组f[m + 1][m + 1]。dp[i][j]：表示nums数组中前i个数和后j个数组成的最大分数。
     * 边界条件
     * 1、f[0][0] = 0;
     * 2、f[i][0]：此状态表示nums数组中前i个数和后0个数组成的最大分数，此状态只可能由f[i - 1][0]转移得到。
     * 3、f[0][j]：此状态表示nums数组中前0个数和后j个数组成的最大分数，此状态只可能由f[0][j - 1]转移得到。
     */
    public static int maximumScore2(int[] nums, int[] multipliers) {
        int n = nums.length,m = multipliers.length;
        int[][] f = new int[m + 1][m + 1];
        for (int i = 1; i <= m; i++) {
            f[i][0] = f[i - 1][0] + nums[i - 1] * multipliers[i - 1];
        }
        for (int j = 1;j <= m; j++) {
            f[0][j] = f[0][j - 1] + nums[n - j] * multipliers[j - 1];
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 1;i <= m; i++){
            for (int j = 1; i + j <= m; j++){
                f[i][j] = Math.max(f[i - 1][j] + nums[i - 1] * multipliers[i + j - 1], f[i][j - 1] + nums[n - j] * multipliers[i + j - 1]);
                ans = Math.max(ans, f[i][j]);
            }
        }
        return ans;
    }
}
