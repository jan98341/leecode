package com.jan.title100.title40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
 * 并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 *
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 *
 * 示例 2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 *
 * 提示：
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * candidates 的所有元素 互不相同
 * 1 <= target <= 40
 */
public class Title39 {
    public static void main(String[] args) {
        int[] candidates1 = {2,3,6,7};
        int[] candidates2 = {2,3,5};
        int[] candidates3 = {2};

        System.out.println(combinationSum(candidates1, 7));
        System.out.println(combinationSum(candidates2, 8));
        System.out.println(combinationSum(candidates3, 1));

        System.out.println(combinationSum2(candidates1, 7));
        System.out.println(combinationSum2(candidates2, 8));
        System.out.println(combinationSum2(candidates3, 1));

        System.out.println(combinationSum3(candidates1, 7));
        System.out.println(combinationSum3(candidates2, 8));
        System.out.println(combinationSum3(candidates3, 1));

        System.out.println(combinationSum4(candidates1, 7));
        System.out.println(combinationSum4(candidates2, 8));
        System.out.println(combinationSum4(candidates3, 1));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(0, target, candidates, ans, path);
        return ans;
    }

    private static void dfs(int i, int left, int[] candidates, List<List<Integer>> ans, List<Integer> path) {
        if(left == 0) {
            // 找到符合要求的组合
            ans.add(new ArrayList<>(path));
            return;
        }
        if(left < 0 || i == candidates.length) {
            return;
        }

        // 不选
        dfs(i + 1, left, candidates, ans, path);

        // 选择
        path.add(candidates[i]);
        dfs(i, left - candidates[i], candidates, ans, path);
        path.remove(path.size() - 1);
    }

    /**
     * 剪枝优化
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs2(0, target, candidates, ans, path);
        return ans;
    }

    private static void dfs2(int i, int left, int[] candidates, List<List<Integer>> ans, List<Integer> path) {
        if(left == 0) {
            // 找到符合要求的组合
            ans.add(new ArrayList<>(path));
            return;
        }
        if(i == candidates.length || left < candidates[i]) {
            return;
        }

        // 不选
        dfs2(i + 1, left, candidates, ans, path);

        // 选择
        path.add(candidates[i]);
        dfs2(i, left - candidates[i], candidates, ans, path);
        path.remove(path.size() - 1);
    }

    /**
     * 剪枝优化
     */
    public static List<List<Integer>> combinationSum3(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs3(0, target, candidates, ans, path);
        return ans;
    }

    private static void dfs3(int i, int left, int[] candidates, List<List<Integer>> ans, List<Integer> path) {
        if(left == 0) {
            // 找到符合要求的组合
            ans.add(new ArrayList<>(path));
            return;
        }

        for(int j = i; j < candidates.length && candidates[j] <= left; j++) {
            // 选择
            path.add(candidates[j]);
            dfs3(j, left - candidates[j], candidates, ans, path);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 完全背包预处理 + 可行性剪枝
     */
    public static List<List<Integer>> combinationSum4(int[] candidates, int target) {
        int n = candidates.length;
        // 完全背包
        boolean[][] f = new boolean[n + 1][target + 1];
        f[0][0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                f[i + 1][j] = f[i][j] || j >= candidates[i] && f[i + 1][j - candidates[i]];
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        // 倒着递归，这样参数符合 f 数组的定义
        dfs4(n - 1, target, candidates, f, ans, path);
        return ans;
    }

    private static void dfs4(int i, int left, int[] candidates, boolean[][] f, List<List<Integer>> ans, List<Integer> path) {
        if(left == 0) {
            // 找到符合要求的组合
            ans.add(new ArrayList<>(path));
            return;
        }
        if(left < 0 || !f[i + 1][left]) {
            return;
        }

        // 不选
        dfs4(i - 1, left, candidates, f, ans, path);

        // 选择
        path.add(candidates[i]);
        dfs4(i, left - candidates[i], candidates, f, ans, path);
        path.remove(path.size() - 1);
    }
}
