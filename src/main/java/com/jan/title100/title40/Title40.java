package com.jan.title100.title40;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. 组合总和 II
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * 注意：解集不能包含重复的组合。
 *
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 * 提示:
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */
public class Title40 {
    public static void main(String[] args) {
        int[] candidates1 = {10,1,2,7,6,1,5};
        int[] candidates2 = {2,5,2,1,2};

        System.out.println(combinationSum(candidates1, 8));
        System.out.println(combinationSum(candidates2, 5));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
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
        if(i == candidates.length || left < candidates[i]) {
            return;
        }

        // 选择
        path.add(candidates[i]);
        dfs(i + 1, left - candidates[i], candidates, ans, path);
        path.remove(path.size() - 1);

        // 关键在这个地方：跳过后续所有等于 candidates[i] 的数，递归到 dfs(i,left)，其中 [i,i−1] 中的数都相同。
        // 如果不跳过这些数，设 x=candidates[i],x =candidates[i+1]，那么「选 x 不选 x」和「不选 x 选 x」这两种情况都会加到答案中，这就重复了
        int pos = i + 1;
        while(pos < candidates.length && candidates[pos] == candidates[i]) {
            pos++;
        }

        // 不选
        dfs(pos, left, candidates, ans, path);
    }
}
