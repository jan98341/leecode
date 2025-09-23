package com.jan.title100.title50;

/**
 * 45. 跳跃游戏 II
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置在下标 0。
 * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在索引 i 处，你可以跳转到任意 (i + j) 处：
 * 0 <= j <= nums[i] 且
 * i + j < n
 * 返回到达 n - 1 的最小跳跃次数。测试用例保证可以到达 n - 1。
 *
 * 示例 1:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 * 示例 2:
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 *
 * 提示:
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 * 题目保证可以到达 n - 1
 */
public class Title45 {
    public static void main(String[] args) {
        int[] nums1 = {2,3,1,1,4};
        int[] nums2 = {2,3,0,1,4};
        int[] nums3 = {1,0,3,1,4};

//        System.out.println(jump(nums1));
//        System.out.println(jump(nums2));

        System.out.println(jump2(nums1));
        System.out.println(jump2(nums2));
    }

    public static int jump(int[] nums) {
        int n = nums.length - 1;
        int ans = 0;
        while(n > 0) {
            // 贪心算法，从左到右遍历找出能够到达位置n最远的
            for(int i = 0; i < n; i++) {
                if(i + nums[i] >= n) {
                    n = i;
                    ans++;
                    break;
                }
            }

        }
        return ans;
    }

    public static int jump2(int[] nums) {
        int length = nums.length;
        int steps = 0, maxPosition = 0, end = 0;
        for(int i = 0; i < length - 1; i++) {
            //每次查找中，找出本次能够到达最远的位置
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if(i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
}
