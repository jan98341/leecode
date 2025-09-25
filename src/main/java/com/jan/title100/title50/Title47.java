package com.jan.title100.title50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 提示：
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
public class Title47 {
    public static void main(String[] args) {
        int[] nums1 = {1,1,2};
        int[] nums2 = {1,2,3};
        int[] nums3 = {1,1,1};

//        System.out.println(permuteUnique(nums1));
//        System.out.println(permuteUnique(nums2));
//        System.out.println(permuteUnique(nums3));

        System.out.println(permuteUnique2(nums1));
        System.out.println(permuteUnique2(nums2));
        System.out.println(permuteUnique2(nums3));
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        Integer[] path = new Integer[nums.length];
        boolean[] signs = new boolean[nums.length];
        dfs(0, signs, nums, ans, path);
        return ans;
    }

    private static void dfs(int i, boolean[] signs, int[] nums, List<List<Integer>> ans, Integer[] path) {
        if(i == nums.length) {
            ans.add(new ArrayList<>(Arrays.asList(path)));
            return;
        }
        for(int j = 0; j < nums.length; j++) {
            // 如果 nums[i]==nums[i−1]
            // 如果 nums[i−1] 没有填入排列，为了避免生成重复的排列，绝对不能填 nums[i];
            // 如果 nums[i−1] 已经填入排列，那么 nums[i] 是剩余元素（子问题）中的第一个等于 nums[i] 的数，可以随意填
            if(signs[j] || (j > 0 && nums[j] == nums[j - 1] && !signs[j - 1])) {
                continue;
            }
            signs[j] = true;
            // 对第i个数进行处理，由于使用的整数数组会自动覆盖第i位，path不需要恢复现场
            path[i] = nums[j];
            dfs(i + 1, signs, nums, ans, path);
            // 标记位需要现场恢复
            signs[j] = false;
        }
    }

    public static List<List<Integer>> permuteUnique2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] signs = new boolean[nums.length];
        dfs2(0, signs, nums, ans, path);
        return ans;
    }

    private static void dfs2(int i, boolean[] signs, int[] nums, List<List<Integer>> ans, List<Integer> path) {
        if(i == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for(int j = 0; j < nums.length; j++) {
            // 如果 nums[i]==nums[i−1]
            // 如果 nums[i−1] 没有填入排列，为了避免生成重复的排列，绝对不能填 nums[i];
            // 如果 nums[i−1] 已经填入排列，那么 nums[i] 是剩余元素（子问题）中的第一个等于 nums[i] 的数，可以随意填
            if(signs[j] || (j > 0 && nums[j] == nums[j - 1] && !signs[j - 1])) {
                continue;
            }
            signs[j] = true;
            path.add(nums[j]);
            dfs2(i + 1, signs, nums, ans, path);
            // 现场恢复
            signs[j] = false;
            path.remove(path.size() - 1);
        }
    }
}
