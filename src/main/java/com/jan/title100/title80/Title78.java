package com.jan.title100.title80;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 */
public class Title78 {
    public static void main(String[] args) {
        int[] nums1 = {1,2,3};
        int[] nums2 = {0};
        System.out.println(subsets(nums1));
        System.out.println(subsets(nums2));
        System.out.println(subsets2(nums1));
        System.out.println(subsets2(nums2));
    }

    /**
     * 使用选与不选解题
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(0, nums, ans, path);
        return ans;
    }

    private static void dfs(int i, int[] nums, List<List<Integer>> ans, List<Integer> path) {
        if(i == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        // 不选
        dfs(i + 1, nums, ans, path);

        // 选择
        path.add(nums[i]);
        dfs(i + 1, nums, ans, path);
        path.remove(path.size() - 1);
    }

    /**
     * 使用答案角度解题
     */
    public static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs2(0, nums, ans, path);
        return ans;
    }

    private static void dfs2(int i, int[] nums, List<List<Integer>> ans, List<Integer> path) {
        ans.add(new ArrayList<>(path));
        for(int j = i; j < nums.length; j++) {
            path.add(nums[j]);
            dfs2(j + 1, nums, ans, path);
            path.remove(path.size() - 1);
        }
    }
}
