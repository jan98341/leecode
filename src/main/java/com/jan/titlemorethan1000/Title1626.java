package com.jan.titlemorethan1000;

import java.util.Arrays;

/**
 * 1626. 无矛盾的最佳球队
 * 假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 总和 。
 * 然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。
 * 给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。请你返回 所有可能的无矛盾球队中得分最高那支的分数 。
 *
 * 示例 1：
 * 输入：scores = [1,3,5,10,15], ages = [1,2,3,4,5]
 * 输出：34
 * 解释：你可以选中所有球员。
 *
 * 示例 2：
 * 输入：scores = [4,5,6,5], ages = [2,1,2,1]
 * 输出：16
 * 解释：最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。
 *
 * 示例 3：
 * 输入：scores = [1,2,3,5], ages = [8,9,10,1]
 * 输出：6
 * 解释：最佳的选择是前 3 名球员。
 *
 * 提示：
 * 1 <= scores.length, ages.length <= 1000
 * scores.length == ages.length
 * 1 <= scores[i] <= 10^6
 * 1 <= ages[i] <= 1000
 */
public class Title1626 {
    public static void main(String[] args) {
        int[] scores1 = {1,3,5,10,15}, ages1 = {1,2,3,4,5};
        int[] scores2 = {4,5,6,5}, ages2 = {2,1,2,1};
        int[] scores3 = {1,2,3,5}, ages3 = {8,9,10,1};

        System.out.println(bestTeamScore(scores1, ages1));
        System.out.println(bestTeamScore(scores2, ages2));
        System.out.println(bestTeamScore(scores3, ages3));

        System.out.println(bestTeamScore2(scores1, ages1));
        System.out.println(bestTeamScore2(scores2, ages2));
        System.out.println(bestTeamScore2(scores3, ages3));
    }

    /**
     * 排序 + 动态规划
     * 定义 f[i] 表示以 ages[i] 结尾的递增子序列的最大得分，则有转移方程
     * f[i]=max(f[j])+scores[i] ，其中 j<i 且 ages[j]≤ages[i]。初始 f[i]=0，答案为 max(f[i])
     */
    public static int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        Integer[] l = new Integer[n];
        for (int i = 0; i < n; i++) {
            l[i] = i;
        }
        // 按照分数从小到大排序，分数相同的按照年龄从小到大排序
        Arrays.sort(l, (i,j) -> {
            return (scores[i] == scores[j]) ? (ages[i] - ages[j]) : (scores[i] - scores[j]);
        });

        // 按照年龄求非严格递增子序列，获取对应最大的分数
        int[] f = new int[n + 1];
        int ans = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(ages[l[i]] >= ages[l[j]]) {
                    f[i] = Math.max(f[i], f[j]);
                }
            }
            f[i] += scores[l[i]];
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    /**
     * 排序 + 基于值域计算
     */
    public static int bestTeamScore2(int[] scores, int[] ages) {
        int n = scores.length, u = 0;
        Integer[] l = new Integer[n];
        for (int i = 0; i < n; i++) {
            l[i] = i;
            u = Math.max(u, ages[i]);
        }
        // 按照分数从小到大排序，分数相同的按照年龄从小到大排序
        Arrays.sort(l, (i,j) -> {
            return (scores[i] == scores[j]) ? (ages[i] - ages[j]) : (scores[i] - scores[j]);
        });

        int[] maxSum = new int[u + 1];
        int ans = 0;
        for(int i : l) {
            int age = ages[i];
            // 遍历所有年龄得到分数和最大的值
            for(int j = 1; j <= age; j++) {
                maxSum[age] = Math.max(maxSum[age], maxSum[j]);
            }
            maxSum[age] += scores[i];
            ans = Math.max(ans, maxSum[age]);
        }
        return ans;
    }
}
