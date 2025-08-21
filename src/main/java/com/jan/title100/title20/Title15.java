package com.jan.title100.title20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15、三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 *
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0
 *
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 *
 * 提示：
 * 3 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 */
public class Title15 {

    public static void main(String[] args) {
        int[] nums1 = {-1,0,1,2,-1,2,1,-4};
        System.out.println("结果：" + Arrays.toString(threeSum2(nums1).toArray()));
//        int[] nums2 = {0,1,1};
//        System.out.println("结果：" + Arrays.toString(threeSum2(nums2).toArray()));
//        int[] nums3 = {0,0,0};
//        System.out.println("结果：" + Arrays.toString(threeSum2(nums3).toArray()));
//        int[] nums4 = {0,0,0,0};
//        System.out.println("结果：" + Arrays.toString(threeSum2(nums4).toArray()));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ansList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k]);
                        ansList.add(list);
                    }
                }
            }
        }

        return ansList;
    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ansList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int a = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right) {
                if(nums[left] + nums[right] + a > 0) {
                    right --;
                } else if(nums[left] + nums[right] + a < 0) {
                    left ++;
                } else {
                    ansList.add(Arrays.asList(a, nums[left], nums[right]));
                    for(left++; left < right && nums[left] == nums[left - 1]; left++){
                        int aa = 0;
                    };
                    for(right--; right > left && nums[right] == nums[right + 1]; right--){
                        int aa = 0;
                    };
                }
            }
        }

        return ansList;
    }
}
