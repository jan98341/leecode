package com.jan.title700.title620;

import java.util.Arrays;

/**
 * 611. 有效三角形的个数
 * 给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
 *
 * 示例 1:
 * 输入: nums = [2,2,3,4]
 * 输出: 3
 * 解释:有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 *
 * 示例 2:
 * 输入: nums = [4,2,3,4]
 * 输出: 4
 *
 * 提示:
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 */
public class Title611 {
    public static void main(String[] args) {
        int[] nums1 = {2,2,3,4};
        int[] nums2 = {4,2,3,4};
        // {2，3，4，4}  2,3,4 2，3，4  3,4,4  2,4,4
        Title611 title611 = new Title611();
        System.out.println(title611.triangleNumber(nums1));
        System.out.println(title611.triangleNumber(nums2));

    }

    /**
     * 枚举最长边 + 相向双指针
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0, n = nums.length;

        for(int k = 2; k < n; k++) {
            int c = nums[k];
            int left = 0, right = k - 1;
            while (left < right) {
                if(nums[left] + nums[right] > c) {
                    ans += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }

    /**
     * 枚举最短边 + 相向双指针
     */
    public int triangleNumber2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, ans = 0;
        for(int i = 0; i < n; i++) {
            int a = nums[i];
            if(a == 0) {
                continue;
            }

            int j = i + 1;
            for(int k = i + 2; k < n; k++) {
                while (nums[k] - nums[j] >= a) {
                    j++;
                }
                ans += k - j;
            }
        }
        return ans;
    }
}
