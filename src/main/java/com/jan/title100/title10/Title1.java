package com.jan.title100.title10;

import java.util.HashMap;
import java.util.Map;

public class Title1 {
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;

        Title1 title1 = new Title1();
//        int[] res = title1.twoSum(nums, target);
        int[] res = title1.twoSumTuning(nums, target);
        if(res != null) {
            System.out.printf("%d %d \n", res[0], res[1]);
        } else {
            System.out.printf("未找到结果，返回为null");
        }
    }

    public int[] twoSum(int[] nums, int target) {
        int[] resultArray = new int[2];
        for(int i = 0; i < nums.length; i++) {
            for(int j = i+1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    resultArray[0] = i;
                    resultArray[1] = j;
                    return resultArray;
                }
            }
        }
        return null;
    }

    /**
     * 使用哈希表，可以将寻找target - x的时间复杂度降低到从O(N)降低到O(1)
     */
    public int[] twoSumTuning(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }

        return null;
  }
}
