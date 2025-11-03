package com.jan.title700.title680;

/**
 * 673. 最长递增子序列的个数
 * 给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。
 * 注意 这个数列必须是 严格 递增的。
 *
 * 示例 1:
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 *
 * 示例 2:
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 *
 * 提示:
 * 1 <= nums.length <= 2000
 * -106 <= nums[i] <= 10^6
 */
public class Title673 {
    public static void main(String[] args) {
        int[] nums1 = {1,3,5,4,7};
        int[] nums2 = {2,2,2,2,2};

        System.out.println(findNumberOfLIS(nums1));
        System.out.println(findNumberOfLIS(nums2));

        System.out.println(findNumberOfLIS2(nums1));
        System.out.println(findNumberOfLIS2(nums2));
    }

    /**
     * 数组最长上升子序列长度状态转移方程  dp[i]=max(dp[j])+1,其中0≤j<i且num[j]<num[i]
     * 如果满足 nums[j]<nums[i]，说明 nums[i] 可以接在 nums[j] 后面形成上升子序列，这时候对 f[i] 和 f[j]+1 的大小关系进行分情况讨论：
     * 1、满足 f[i]<f[j]+1：说明 f[i] 会被 f[j]+1 直接更新，此时同步直接更新 g[i]=g[j] 即可；
     * 2、满足 f[i]=f[j]+1：说明找到了一个新的符合条件的前驱，此时将值继续累加到方案数当中，即有 g[i]+=g[j]。
     */
    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length, maxLen = 0, ans = 0;
        int[] f = new int[n], g = new int[n];

        for (int i = 0; i < n; i++) {
            f[i] = g[i] = 1;
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    // 需要对f[i]和f[j]情况具体分类，如果f[i]<f[j]+1表示找到更长的子序列则需要更新f[i]和g[i]，
                    // 如果f[i]=f[j]+1表示找到和f[i]子序列长度相同的f[j]，需要把f[j]对应的值g[j]累计到g[i]中
                    if(f[i] < f[j] + 1) {
                        f[i] = f[j] + 1;
                        g[i] = g[j];
                    } else if(f[i] == f[j] + 1) {
                        g[i] += g[j];
                    }
                }
            }
            maxLen = Math.max(maxLen, f[i]);
        }

        for (int i = 0; i < n; i++) {
            if (f[i] == maxLen) {
                ans += g[i];
            }
        }
        return ans;
    }

    /**
     * 数组最长上升子序列长度状态转移方程  dp[i]=max(dp[j])+1,其中0≤j<i且num[j]<num[i]
     * 如果满足 nums[j]<nums[i]，说明 nums[i] 可以接在 nums[j] 后面形成上升子序列，这时候对 f[i] 和 f[j]+1 的大小关系进行分情况讨论：
     * 1、满足 f[i]<f[j]+1：说明 f[i] 会被 f[j]+1 直接更新，此时同步直接更新 g[i]=g[j] 即可；
     * 2、满足 f[i]=f[j]+1：说明找到了一个新的符合条件的前驱，此时将值继续累加到方案数当中，即有 g[i]+=g[j]。
     */
    public static int findNumberOfLIS2(int[] nums) {
        int n = nums.length, maxLen = 0, ans = 0;
        int[] f = new int[n], g = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = g[i] = 1;
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    if(f[i] < f[j] + 1) {
                        f[i] = f[j] + 1;
                        g[i] = g[j];
                    } else if(f[i] == f[j] + 1) {
                        g[i] += g[j];
                    }
                }
            }
            if(maxLen < f[i]) {
                maxLen = f[i];
                ans = g[i];
            } else if(maxLen == f[i]) {
                ans += g[i];
            }
        }
        return ans;
    }
}
