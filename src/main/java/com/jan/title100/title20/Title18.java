package com.jan.title100.title20;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18、四数之和
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 *
 * 示例 1：
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * 示例 2：
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 *
 * 提示：
 * 1 <= nums.length <= 200
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 */
public class Title18 {
    public static void main(String[] args) {
//        System.out.println("case1结果：" + JSON.toJSONString(fourSum(new int[]{1,0,-1,0,-2,2}, 0)));
//        System.out.println("case2结果：" + JSON.toJSONString(fourSum(new int[]{2,2,2,2,2}, 8)));
        System.out.println("case3结果：" + JSON.toJSONString(fourSum(new int[]{1000000000,1000000000,1000000000,1000000000}, -294967296)));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        // 使用双指针先对数组进行排序
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 3; i++) {
            long a = nums[i];
            // 遍历第一层数字时，判断数字是否和前一个相同，如果相同跳过
            if(i > 0 && a == nums[i - 1]) continue;
            for(int j = i + 1; j < nums.length - 2; j++) {
                long b = nums[j];
                // 遍历第二层数字时，判断数字是否和前一个相同，如果相同跳过
                if(j > i + 1 && b == nums[j - 1]) continue;

                // 设置左右指针，由于数组是排序的，根据四数之和与target比较进行游动
                int left = j + 1;
                int right = nums.length - 1;
                while(left < right) {
                    long s = a + b + nums[left] + nums[right];
                    // 如果大于target则右指针向左移
                    if(s > target) {
                        right--;
                    }
                    // 如果小于target则左指针向右移
                    else if(s < target) {
                        left++;
                    }
                    // 如果等于target则把当前组合加入到结果集合中
                    else {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // 在左右移动的时候，判断是否和前一个数相同，如果相同则跳过
                        for(left ++; left < right && nums[left] == nums[left-1]; left ++);
                        for(right --; left < right && nums[right] == nums[right+1]; right --);
                    }
                }
            }
        }

        return ans;
    }
}
