package com.jan.title100.title50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 *
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 */
public class Title46 {
    public static void main(String[] args) {
        int[] nums1 = {1,2,3};
        int[] nums2 = {0,1};
        int[] nums3 = {1};

//        System.out.println(permute(nums1));
//        System.out.println(permute(nums2));
//        System.out.println(permute(nums3));

        System.out.println(permute2(nums1));
        System.out.println(permute2(nums2));
        System.out.println(permute2(nums3));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = Arrays.asList(new Integer[nums.length]);
        boolean[] signs = new boolean[nums.length];
        dfs(0, signs, nums, ans, path);
        return ans;
    }

    private static void dfs(int i, boolean[] signs, int[] nums, List<List<Integer>> ans, List<Integer> path) {
        if(i == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for(int j = 0; j < nums.length; j++) {
            if(signs[j]) {
                continue;
            }
            signs[j] = true;
            //path[i] = nums[j];
            path.set(i, nums[j]);
            dfs(i + 1, signs, nums, ans, path);
            // 恢复现场
            signs[j] = false;
            // 注意 path 无需恢复现场，因为排列长度固定，直接覆盖就行
        }
    }

    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Integer[] path = new Integer[nums.length];
        boolean[] signs = new boolean[nums.length];
        dfs2(0, signs, nums, ans, path);
        return ans;
    }

    private static void dfs2(int i, boolean[] signs, int[] nums, List<List<Integer>> ans, Integer[] path) {
        if(i == nums.length) {
            ans.add(new ArrayList<>(Arrays.asList(path)));
            return;
        }
        for(int j = 0; j < nums.length; j++) {
            if(signs[j]) {
                continue;
            }
            signs[j] = true;
            // 对第i个数进行处理，由于使用的整数数组会自动覆盖第i位，path不需要恢复现场
            path[i] = nums[j];
            dfs2(i + 1, signs, nums, ans, path);
            // 标记位需要现场恢复
            signs[j] = false;
        }
    }
}
