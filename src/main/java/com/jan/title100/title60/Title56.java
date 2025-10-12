package com.jan.title100.title60;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 示例 3：
 * 输入：intervals = [[4,7],[1,4]]
 * 输出：[[1,7]]
 * 解释：区间 [1,4] 和 [4,7] 可被视为重叠区间。
 *
 * 提示：
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 */
public class Title56 {
    public static void main(String[] args) {
        int[][] intervals1 = {{1,3}, {2,6}, {8,10}, {15,18}};
        int[][] intervals2 = {{1,4}, {4,5}};
        int[][] intervals3 = {{4,7}, {1,4}};

        System.out.println(JSON.toJSONString(merge(intervals1)));
        System.out.println(JSON.toJSONString(merge(intervals2)));
        System.out.println(JSON.toJSONString(merge(intervals3)));
    }

    public static int[][] merge(int[][] intervals) {
        List<int[]> ans = new ArrayList<>();
        // 按照左端点从小到大排序
        Arrays.sort(intervals, (o1, o2)-> o1[0] - o2[0]);
        for(int[] p: intervals) {
            int m = ans.size();
            if(m > 0 && p[0] <= ans.get(m - 1)[1]) {
                // 可以合并，更新右端点最大值
                ans.get(m - 1)[1] = Math.max(ans.get(m - 1)[1], p[1]);
            } else {
                // 不相交，无法合并
                ans.add(p);
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
