package com.jan.title100.title40;

/**
 * 33. 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 向左旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 下标 3 上向左旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 *
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 *
 * 示例 3：
 * 输入：nums = [1], target = 0
 * 输出：-1
 *
 * 提示：
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -104 <= target <= 104
 */
public class Title33 {
    public static void main(String[] args) {
        int[] nums1 = {4,5,6,7,0,1,2};
        int[] nums2 = {4,5,6,7,0,1,2};
        int[] nums3 = {1};

//        System.out.println(search(nums1, 0));
//        System.out.println(search(nums2, 3));
//        System.out.println(search(nums3, 0));

//        System.out.println(search2(nums1, 0));
//        System.out.println(search2(nums2, 3));
//        System.out.println(search2(nums3, 0));

        System.out.println(search3(nums1, 0));
        System.out.println(search3(nums2, 3));
        System.out.println(search3(nums3, 0));

    }

    public static int search(int[] nums, int target) {
        int n = nums.length;
        int i = findMin(nums);
        if(nums[n - 1] < target) {
            // target 在第一段
            return lowerBound(nums, -1, i, target);
        } else {
            // target 在第二段
            return lowerBound(nums, i - 1, n, target);
        }
    }

    /**
     * 寻找旋转排序数组中的最小值（返回的是下标）
     */
    private static int findMin(int[] nums) {
        int n = nums.length;
        // 开区间 (-1, n-1)
        int left = -1, right = n - 1;
        while(left + 1 < right) {
            int mid = (left + right) >>> 1;
            if(nums[mid] < nums[n - 1]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    /**
     * 有序数组中找 target 的下标
     */
    private static int lowerBound(int[] nums, int left, int right, int target) {
        while(left + 1 < right) {
            // 循环不变量：
            // nums[right] >= target
            // nums[left] < target
            int mid = (left + right) >>> 1;
            if(nums[mid] >= target) {
                // 范围缩小到 (left, mid)
                right = mid;
            } else {
                // 范围缩小到 (mid, right)
                left = mid;
            }
        }
        return nums[right] == target ? right : -1;
    }

    /**
     * 一次二分
     */
    public static int search2(int[] nums, int target) {
        int last = nums[nums.length - 1];
        int left = -1, right = nums.length - 1; // 开区间 (-1, n-1)
        while(left + 1 < right) {
            int mid = (left + right) >>> 1;
            int x = nums[mid];
            if(target > last && x <= last) {
                right = mid;
            } else if(target <= last && x > last) {
                left = mid;
            } else if(x >= target) {
                right = mid;
            } else {
                left = mid;
            }

        }
        return nums[right] == target ? right : -1;
    }

    /**
     * 一次二分
     */
    public static int search3(int[] nums, int target) {
        int n = nums.length;
        int left = -1, right = n - 1; // 开区间 (-1, n-1)
        while(left + 1 < right) {
            int mid = (left + right) >>> 1;
            if(check(nums, target, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return nums[right] == target ? right : -1;
    }

    private static boolean check(int[] nums, int target, int i) {
        int last = nums[nums.length - 1];
        int x = nums[i];
        if(x > last) {
            return target > last && x >= target;
        }
        return target > last || x >= target;
    }
}
