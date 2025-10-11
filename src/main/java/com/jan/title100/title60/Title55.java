package com.jan.title100.title60;

/**
 * 55. 跳跃游戏
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 * 示例 2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *
 * 提示：
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 10^5
 */
public class Title55 {
    public static void main(String[] args) {
        int[] nums1 = {2,3,1,1,4};
        int[] nums2 = {3,2,1,0,4};
        int[] nums3 = {1,1,0,1,1,2};

        System.out.println(canJump(nums1));
        System.out.println(canJump(nums2));
        System.out.println(canJump(nums3));
    }

    public static boolean canJump(int[] nums) {
        int length = nums.length;
        int  maxPosition = 0, end = 0;
        for(int i = 0; i < length - 1; i++) {
            // 每次查找中，找出本次能够到达最远的位置
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if(i == end) {
                // 如果出现本次超找出最远的位置是当前最后位置，那么就无法向后跳远，则返回false
                if(maxPosition == end) {
                    return false;
                }
                end = maxPosition;
            }
        }
        return true;
    }
}
