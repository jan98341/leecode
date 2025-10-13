package com.jan.title100.title60;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 60. 排列序列
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 示例 1：
 * 输入：n = 3, k = 3
 * 输出："213"
 *
 * 示例 2：
 * 输入：n = 4, k = 9
 * 输出："2314"
 *
 * 示例 3：
 * 输入：n = 3, k = 1
 * 输出："123"
 *
 *
 * 提示：
 * 1 <= n <= 9
 * 1 <= k <= n!
 */
public class Title60 {
    public static void main(String[] args) {

        long start1 = System.currentTimeMillis();
        System.out.println(JSON.toJSONString(getPermutation(3, 3)));
        System.out.println(JSON.toJSONString(getPermutation(4, 9)));
        System.out.println(JSON.toJSONString(getPermutation(3, 1)));
        long end1 = System.currentTimeMillis();
        System.out.println("第一组示例耗时：" + (end1 - start1));

        long start2 = System.currentTimeMillis();
        System.out.println(JSON.toJSONString(getPermutation2(3, 3)));
        System.out.println(JSON.toJSONString(getPermutation2(4, 9)));
        System.out.println(JSON.toJSONString(getPermutation2(3, 1)));
        long end2 = System.currentTimeMillis();
        System.out.println("第二组示例耗时：" + (end2 - start2));
    }

    /**
     * 获取所有全排列，再获取第k个，这种算法超时
     */
    public static String getPermutation(int n, int k) {
        // 初始化n个元素整型数组，排列顺序从小到大
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }

        List<List<Integer>> ans = new ArrayList<>();
        // 需要初始化长度为n的列表
        List<Integer> path = Arrays.asList(new Integer[nums.length]);
        boolean[] onPath = new boolean[n];
        dfs(0, nums, path, onPath, ans);
        StringBuilder sb = new StringBuilder();
        for(Integer i : ans.get(k - 1)) {
            sb.append(i);
        }
        return sb.toString();
    }

    private static void dfs(int i, int[] nums, List<Integer> path, boolean[] onPath, List<List<Integer>> ans) {
        if(i == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int j = 0; j < nums.length; j++) {
            if(!onPath[j]) {
                // 从没有选的数字中选一个
                path.set(i, nums[j]);
                onPath[j] = true;
                dfs(i + 1, nums, path, onPath, ans);
                // 恢复现场，path无需恢复现场，因为排列长度固定，直接覆盖就行
                onPath[j] = false;
            }
        }
    }

    /**
     * 解析参见 https://leetcode.cn/problems/permutation-sequence/solutions/10642/hui-su-jian-zhi-python-dai-ma-java-dai-ma-by-liwei/
     * 技巧是：在选出某一个数的时候，计算剩下的数字可以组成的全排列个数：如果它小于 k 就跳过（以该数开始的排列都不是我们要找的排练），
     *        同时 k 减去跳过的排列数，否则就继续产生新的分支。
     */
    public static String getPermutation2(int n, int k) {
        int[] fac = calFac(n);
        boolean[] used = new boolean[n + 1];
        StringBuilder path = new StringBuilder();
        dfs2(0, n, k, path, used, fac);
        return path.toString();
    }

    private static void dfs2(int i, int n, int k, StringBuilder path, boolean[] used, int[] fac) {
        if(i == n) {
            return;
        }
        int count = fac[n - 1 - i];
        for (int j = 1; j <= n; j++) {
            if(used[j]) {
                continue;
            }
            if(count < k) {
                k -= count;
                continue;
            }
            used[j] = true;
            path.append(j);
            dfs2(i + 1, n, k, path, used, fac);
            return;
        }
    }

    /**
     * 计算n对应阶乘
     */
    private static int[] calFac(int n) {
        int[] fac = new int[n + 1];
        fac[0] = 1;
        for (int i = 1; i <= n; i++) {
            fac[i] = fac[i - 1] * i;
        }
        return fac;
    }

    private static int[] fac3 = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
    public static String getPermutation3(int n, int k) {
        boolean[] used = new boolean[n + 1];
        StringBuilder path = new StringBuilder();
        dfs3(0, n, k, path, used);
        return path.toString();
    }

    private static void dfs3(int i, int n, int k, StringBuilder path, boolean[] used) {
        if(i == n) {
            return;
        }
        int count = fac3[n - 1 - i];
        for (int j = 1; j <= n; j++) {
            if(used[j]) {
                continue;
            }
            if(count < k) {
                k -= count;
                continue;
            }
            used[j] = true;
            path.append(j);
            dfs3(i + 1, n, k, path, used);
            return;
        }
    }
}
