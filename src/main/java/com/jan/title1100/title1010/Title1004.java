package com.jan.title1100.title1010;

/**
 * 1004. 最大连续1的个数 III
 * 给定一个二进制数组 nums 和一个整数 k，假设最多可以翻转 k 个 0 ，则返回执行操作后 数组中连续 1 的最大个数 。
 *
 * 示例 1：
 * 输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：[1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 *
 * 示例 2：
 * 输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * nums[i] 不是 0 就是 1
 * 0 <= k <= nums.length
 */
public class Title1004 {
    public static void main(String[] args) {
        int[] nums1 = {1,1,1,0,0,0,1,1,1,1,0};
        int[] nums2 = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};

        Title1004 title1004 = new Title1004();
        System.out.println(title1004.longestOnes(nums1, 2));
        System.out.println(title1004.longestOnes(nums2, 3));

        System.out.println(title1004.longestOnes2(nums1, 2));
        System.out.println(title1004.longestOnes2(nums2, 3));
    }

    public int longestOnes(int[] nums, int k) {
        int ans = 0, left = 0, cnt = 0;
        for(int right = 0; right < nums.length; right++) {
            if(nums[right] == 0) {
                cnt++;
            }

            if(cnt > k) {
                while(nums[left] != 0) {
                    left++;
                }
                left++;
                cnt--;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public int longestOnes2(int[] nums, int k) {
        int ans = 0, left = 0, cnt = 0;
        for(int right = 0; right < nums.length; right++) {
            cnt += 1 - nums[right];  // 0 变成 1，用来统计cnt
            while(cnt > k) {
                cnt -= 1 - nums[left];
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
