package com.jan.title100.title50;

/**
 * 41. 缺失的第一个正数
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 *
 * 示例 1：
 * 输入：nums = [1,2,0]
 * 输出：3
 * 解释：范围 [1,2] 中的数字都在数组中。
 *
 * 示例 2：
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 解释：1 在数组中，但 2 没有。
 *
 * 示例 3：
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 * 解释：最小的正数 1 没有出现。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 */
public class Title41 {
    public static void main(String[] args) {
        int[] nums1 = {1,2,0};
        int[] nums2 = {3,4,-1,1};
        int[] nums3 = {7,8,9,11,12};
        int[] nums4 = {1};

//        System.out.println(firstMissingPositive(nums1));
//        System.out.println(firstMissingPositive(nums2));
//        System.out.println(firstMissingPositive(nums3));
        System.out.println(firstMissingPositive(nums4));

//        System.out.println(firstMissingPositive2(nums1));
//        System.out.println(firstMissingPositive2(nums2));
//        System.out.println(firstMissingPositive2(nums3));
        System.out.println(firstMissingPositive2(nums4));
    }

    /**
     * 对于一个长度为 N 的数组，其中没有出现的最小正整数只能在 [1,N+1] 中。
     * 这是因为如果 [1,N] 都出现了，那么答案是 N+1，否则答案是 [1,N] 中没有出现的最小正整数。
     */
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // 1、我们将数组中所有小于等于 0 的数修改为 N+1；
        for (int i = 0; i < n; i++) {
            if(nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }

        // 2、我们遍历数组中的每一个数 x，它可能已经被打了标记，因此原本对应的数为 ∣x∣，其中 ∣∣ 为绝对值符号。
        //    如果 ∣x∣∈[1,N]，那么我们给数组中的第 ∣x∣−1 个位置的数添加一个负号。注意如果它已经有负号，不需要重复添加；
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if(num <= n && nums[num - 1] > 0) {
                nums[num - 1] = - nums[num - 1];
            }
        }

        // 在遍历完成之后，如果数组中的每一个数都是负数，那么答案是 N+1，否则答案是第一个正数的位置加 1。
        for(int i = 0; i < n; i++) {
            if(nums[i] > 0) {
                return i + 1;
            }
        }

        return n + 1;
    }

    public static int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 置换，对数组进行一次遍历，对于遍历到的数 x=nums[i]，
            // 如果 x∈[1,N]，我们就知道 x 应当出现在数组中的 x−1 的位置，因此交换 nums[i] 和 nums[x−1]，这样 x 就出现在了正确的位置。
            // 在完成交换后，新的 nums[i] 可能还在 [1,N] 的范围内，我们需要继续进行交换操作，直到 x∈[1,N]。
            while((nums[i] > 0 && nums[i] <= n) && nums[nums[i] - 1] != nums[i] ) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if(nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }
}
