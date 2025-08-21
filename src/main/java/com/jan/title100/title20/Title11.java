package com.jan.title100.title20;

/**
 * 11、盛最多水的容器
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 *
 * 示例 1：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例 2：
 * 输入：height = [1,1]
 * 输出：1
 *
 * 提示：
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 */
public class Title11 {
    public static void main(String[] args) {
        int[] height1 = {1,8,6,2,5,4,8,3,7};
        int[] height2 = {1,1};

//        System.out.println("结果为：" + maxArea(height1));
//        System.out.println("结果为：" + maxArea(height2));

//        System.out.println("结果为：" + maxArea2(height1));
//        System.out.println("结果为：" + maxArea2(height2));

        System.out.println("结果为：" + maxArea3(height1));
        System.out.println("结果为：" + maxArea3(height2));
    }

    public static int maxArea(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = (j - i) * Math.min(height[i], height[j]);
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

    public static int maxArea2(int[] height) {
        int maxArea = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static int maxArea3(int[] height) {
        int max = 0, l = 0, r = height.length - 1;
        while (l < r) {
            int shoter = Math.min(height[l], height[r]);
            max = Math.max(max, shoter * (r - l));
            while (l < r && height[l] <= shoter) l ++;
            while (l < r && height[r] <= shoter) r --;
        }
        return max;
    }
}
