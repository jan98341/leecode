package com.jan.title100.title50;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 * 提示：
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */
public class Title42 {
    public static void main(String[] args) {
        int[] heights1 = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] heights2 = {4,2,0,3,2,5};
        System.out.println(trap(heights1));
        System.out.println(trap(heights2));

        System.out.println(trap2(heights1));
        System.out.println(trap2(heights2));
    }

    /**
     * 算法思路是从左到右、从右到左两次遍历数组，创建遇到最高柱子高度数组，然后获取两个数组最小值与当前柱子高度的差，这个差是当前柱子所容水量
     * 时间复杂度O(n)，空间复杂度(n)
     */
    public static int trap(int[] height) {
        int ans = 0;
        int l = height.length;
        int[] preMax = new int[l], sufMax = new int[l];
        int temMax = 0;
        for (int i = 0; i < l; i++) {
            preMax[i] = Math.max(temMax, height[i]);
            temMax = Math.max(temMax, preMax[i]);
        }
        temMax = 0;
        for(int j = l - 1; j >= 0; j--) {
            sufMax[j] = Math.max(temMax, height[j]);
            temMax = Math.max(temMax, sufMax[j]);
        }
        for(int i = 0; i < l; i++) {
            ans += Math.min(preMax[i], sufMax[i]) - height[i];
        }

        return ans;
    }

    /**
     * 算法思路双指针向最高柱子靠近，在靠近的过程中，当前柱子与左右指针记录最高的柱子进行比较，记录接雨水量
     * 时间复杂度O(n)，空间复杂度(1)
     */
    public static int trap2(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int preMax = 0, sufMax = 0;
        while (left < right) {
            preMax = Math.max(preMax, height[left]);
            sufMax = Math.max(sufMax, height[right]);
            if(preMax <= sufMax) {
                ans += preMax - height[left];
                left++;
            } else {
                ans += sufMax - height[right];
                right--;
            }
        }
        return ans;
    }
}
