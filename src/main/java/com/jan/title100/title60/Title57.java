package com.jan.title100.title60;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. 插入区间
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表 intervals，其中 intervals[i] = [starti, endi] 表示第 i 个区间的开始和结束，并且 intervals 按照 starti 升序排列。同样给定一个区间 newInterval = [start, end] 表示另一个区间的开始和结束。
 * 在 intervals 中插入区间 newInterval，使得 intervals 依然按照 starti 升序排列，且区间之间不重叠（如果有必要的话，可以合并区间）。
 * 返回插入之后的 intervals。
 * 注意 你不需要原地修改 intervals。你可以创建一个新数组然后返回它。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 *
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *
 * 提示：
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^5
 * intervals 根据 starti 按 升序 排列
 * newInterval.length == 2
 * 0 <= start <= end <= 10^5
 */
public class Title57 {
    public static void main(String[] args) {
        int[][] intervals1 = {{1,3}, {6,9}};
        int[] newInterval1 = {2,5};
        int[][] intervals2 = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval2 = {4,8};
        int[][] intervals3 = {};
        int[] newInterval3 = {5,7};

//        System.out.println(JSON.toJSONString(insert(intervals1, newInterval1)));
//        System.out.println(JSON.toJSONString(insert(intervals2, newInterval2)));
        System.out.println(JSON.toJSONString(insert2(intervals1, newInterval1)));
        System.out.println(JSON.toJSONString(insert2(intervals2, newInterval2)));
        System.out.println(JSON.toJSONString(insert2(intervals3, newInterval3)));
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        // 区间列表长度为0时，直接返回待插入区间
        if(intervals.length == 0) {
            return new int[][]{{newInterval[0], newInterval[1]}};
        }

        // 区间列表第一个区间左端点大于等于待插入区间的右端点
        // 大于时，待插入区间直接放在区间列表头
        List<int[]> ans = new ArrayList<>();
        if(intervals[0][0] > newInterval[1]) {
            ans.add(newInterval);
            for(int[] p : intervals) {
                ans.add(p);
            }
            return ans.toArray(new int[ans.size()][]);
        }
        // 大于时，待插入区间右端点与区间列表头左端点合并
        else if(intervals[0][0] == newInterval[1]) {
            for (int i = 0; i < intervals.length; i++) {
                if(i == 0) {
                    intervals[0][0] = newInterval[0];
                }
                ans.add(intervals[i]);
            }
            return ans.toArray(new int[ans.size()][]);
        }

        for(int[] p : intervals) {
            int m = ans.size();
            // 当newInterval左端点大于ans列表末区间的左端点时，插入newInterval区间
            if(m > 0 && ans.get(m - 1)[0] <= newInterval[0]) {
                // ans列表末区间与newInterval区间有交集，newInterval区间和p没有交集，设置ans列表末区间右端点为ans和newInterval右端点最大值
                if(ans.get(m - 1)[1] >= newInterval[0] && newInterval[1] < p[0] ) {
                    ans.get(m - 1)[1] = Math.max(ans.get(m - 1)[1], newInterval[1]);
                }
                // ans列表末区间与newInterval区间没有交集，newInterval区间和p有交集，合并newInterval区间和p后加入到ans列表中
                else if(ans.get(m - 1)[1] < newInterval[0] && newInterval[1] >= p[0] ) {
                    p[0] = Math.min(newInterval[0], p[0]);
                    p[1] = Math.max(p[1], newInterval[1]);
                    ans.add(p);
                }
                // ans列表末区间与newInterval区间有交集，newInterval区间和p有交集，合并newInterval区间和p后加入到ans列表中
                else if(ans.get(m - 1)[1] >= newInterval[0] && newInterval[1] >= p[0] ) {
                    ans.get(m - 1)[1] = Math.max(newInterval[1], p[1]);
                }
                // ans列表末区间、newInterval、p均没有交集，把newInterval和p添加到ans中
                else {
                    ans.add(newInterval);
                    ans.add(p);
                }
            } else {
                ans.add(p);
            }
        }

        return ans.toArray(new int[ans.size()][]);
    }


    public static int[][] insert2(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        boolean flag = false;
        int left = newInterval[0], right = newInterval[1];
        for(int[] p : intervals) {
            // 和newInterval无交集，插入到右侧
            if(p[0] > right) {
                if(!flag) {
                    ans.add(new int[]{left, right});
                    flag = true;
                }
                ans.add(p);
            }
            // 和newInterval无交集，插入到newInterval左侧
            else if(p[1] < left) {
                ans.add(p);
            }
            // 和newInterval无交集，获取p与newInterval交集后的左右端点，暂不加入到ans
            else {
                left = Math.min(p[0], left);
                right = Math.max(p[1], right);
            }
        }
        // 如果newInterval未插入，则插入到ans末尾
        if(!flag) {
            ans.add(new int[]{left, right});
        }
        // 把列表转换为二维数组并返回
        return ans.toArray(new int[ans.size()][]);
    }
}
