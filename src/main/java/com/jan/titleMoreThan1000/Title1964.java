package com.jan.titleMoreThan1000;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1964. 找出到每个位置为止最长的有效障碍赛跑路线
 * 你打算构建一些障碍赛跑路线。给你一个 下标从 0 开始 的整数数组 obstacles ，数组长度为 n ，其中 obstacles[i] 表示第 i 个障碍的高度。
 * 对于每个介于 0 和 n - 1 之间（包含 0 和 n - 1）的下标  i ，在满足下述条件的前提下，请你找出 obstacles 能构成的最长障碍路线的长度：
 * 你可以选择下标介于 0 到 i 之间（包含 0 和 i）的任意个障碍。
 * 在这条路线中，必须包含第 i 个障碍。
 * 你必须按障碍在 obstacles 中的 出现顺序 布置这些障碍。
 * 除第一个障碍外，路线中每个障碍的高度都必须和前一个障碍 相同 或者 更高 。
 * 返回长度为 n 的答案数组 ans ，其中 ans[i] 是上面所述的下标 i 对应的最长障碍赛跑路线的长度。
 *
 * 示例 1：
 * 输入：obstacles = [1,2,3,2]
 * 输出：[1,2,3,3]
 * 解释：每个位置的最长有效障碍路线是：
 * - i = 0: [1], [1] 长度为 1
 * - i = 1: [1,2], [1,2] 长度为 2
 * - i = 2: [1,2,3], [1,2,3] 长度为 3
 * - i = 3: [1,2,3,2], [1,2,2] 长度为 3
 *
 * 示例 2：
 * 输入：obstacles = [2,2,1]
 * 输出：[1,2,1]
 * 解释：每个位置的最长有效障碍路线是：
 * - i = 0: [2], [2] 长度为 1
 * - i = 1: [2,2], [2,2] 长度为 2
 * - i = 2: [2,2,1], [1] 长度为 1
 *
 * 示例 3：
 * 输入：obstacles = [3,1,5,6,4,2]
 * 输出：[1,1,2,3,2,2]
 * 解释：每个位置的最长有效障碍路线是：
 * - i = 0: [3], [3] 长度为 1
 * - i = 1: [3,1], [1] 长度为 1
 * - i = 2: [3,1,5], [3,5] 长度为 2, [1,5] 也是有效的障碍赛跑路线
 * - i = 3: [3,1,5,6], [3,5,6] 长度为 3, [1,5,6] 也是有效的障碍赛跑路线
 * - i = 4: [3,1,5,6,4], [3,4] 长度为 2, [1,4] 也是有效的障碍赛跑路线
 * - i = 5: [3,1,5,6,4,2], [1,2] 长度为 2
 *
 * 提示：
 * n == obstacles.length
 * 1 <= n <= 10^5
 * 1 <= obstacles[i] <= 10^7
 */
public class Title1964 {
    public static void main(String[] args) {
        int[] obstacles1 = {1, 2, 3, 2};
        int[] obstacles2 = {2, 2, 1};
        int[] obstacles3 = {3, 1, 5, 6, 4, 2};

        System.out.println(JSON.toJSONString(longestObstacleCourseAtEachPosition(obstacles1)));
        System.out.println(JSON.toJSONString(longestObstacleCourseAtEachPosition(obstacles2)));
        System.out.println(JSON.toJSONString(longestObstacleCourseAtEachPosition(obstacles3)));

        System.out.println(JSON.toJSONString(longestObstacleCourseAtEachPosition2(obstacles1)));
        System.out.println(JSON.toJSONString(longestObstacleCourseAtEachPosition2(obstacles2)));
        System.out.println(JSON.toJSONString(longestObstacleCourseAtEachPosition2(obstacles3)));

        System.out.println(JSON.toJSONString(longestObstacleCourseAtEachPosition3(obstacles1)));
        System.out.println(JSON.toJSONString(longestObstacleCourseAtEachPosition3(obstacles2)));
        System.out.println(JSON.toJSONString(longestObstacleCourseAtEachPosition3(obstacles3)));
    }

    /**
     * 超时！！！
     * 转化成求数组每个元素最长非递减序列长度
     */
    public static int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int n = obstacles.length;
        int[] f = new int[n];
        f[0] = 1;
        for (int i = 1; i < n; i++) {
            f[i] = longest(obstacles, i);
        }
        return f;
    }

    private static int longest(int[] nums, int k) {
        List<Integer> g = new ArrayList<>();
        int last = nums[k];
        for (int i = 0; i <= k; i++) {
            int x = nums[i];
            if (x > last) {
                continue;
            }
            int j = upperBound(g, x);
            if (j == g.size()) {
                g.add(x);
            } else {
                g.set(j, x);
            }
        }
        return g.size();
    }

    private static int upperBound(List<Integer> g, int target) {
        int left = -1, right = g.size();
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (g.get(mid) > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    public static int[] longestObstacleCourseAtEachPosition2(int[] obstacles) {
        int n = obstacles.length;
        int[] f = new int[n];
        List<Integer> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = obstacles[i];
            int j = upperBound(g, x);
            if (j == g.size()) {
                g.add(x);
            } else {
                g.set(j, x);
            }
            f[i] = j + 1;
        }

        return f;
    }

    public static int[] longestObstacleCourseAtEachPosition3(int[] obstacles) {
        int n = obstacles.length;
        int[] f =  new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        int[] ans = new int[n];
        f[0] = obstacles[0];
        ans[0] = 1;
        int len = 1;
        for(int i=1;i<n;i++){
            int num = obstacles[i];
            if(num>=f[len-1]){
                f[len++] = num;
                ans[i] = len;
            }else{
                int l = upperBound1(f, num);
//                int l = 0,r = len-1;
//                while(l<=r){
//                    int m = l+((r-l)>>1);
//                    if(f[m]<=num){
//                        l = m+1;
//                    }else{
//                        r = m-1;
//                    }
//                }
                f[l] = num;
                ans[i] = l+1;
            }
        }
        return ans;
    }
    private static int upperBound1(int[] f, int target) {
        int left = -1, right = f.length;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (f[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }
}
