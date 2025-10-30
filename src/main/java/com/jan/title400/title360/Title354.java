package com.jan.title400.title360;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 354. 俄罗斯套娃信封问题
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * 注意：不允许旋转信封。
 *
 * 示例 1：
 * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出：3
 * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 *
 * 示例 2：
 * 输入：envelopes = [[1,1],[1,1],[1,1]]
 * 输出：1
 *
 * 提示：
 * 1 <= envelopes.length <= 10^5
 * envelopes[i].length == 2
 * 1 <= wi, hi <= 10^5
 */
public class Title354 {
    public static void main(String[] args) {
        int[][] envelopes1 = {{5,4}, {6, 4}, {6, 7}, {2, 3}};
        int[][] envelopes2 = {{1, 1}, {1, 1}, {1, 1}};

        System.out.println(maxEnvelopes(envelopes1));
        System.out.println(maxEnvelopes(envelopes2));

        System.out.println(maxEnvelopes2(envelopes1));
        System.out.println(maxEnvelopes2(envelopes2));
    }

    /**
     * 基于二分查找的动态规划
     */
    public static int maxEnvelopes(int[][] envelopes) {
        // 对数组进行排序，按照宽度升序排列，高度按照降序排列，由于宽度可能存在相等的情况，
        // 这样保证答案的数组中宽度相同下至多只有一个在严格递增序列中
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });

        // 二分法求严格递增子序列长度
        List<Integer> g = new ArrayList<>();
        for (int[] e : envelopes) {
            int x = e[1];
            int j = upperBound(g, x);
            if(j == g.size()) {
                g.add(x);
            } else {
                g.set(j, x);
            }
        }
        return g.size();
    }
    private static int upperBound(List<Integer> g, int target) {
        int left = -1, right = g.size();
        while(left + 1 < right) {
            int mid = (left + right) >>> 1;
            if(g.get(mid) >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    /**
     * 动态规划，超时！！！
     */
    public static int maxEnvelopes2(int[][] envelopes) {
        // 对数组进行排序，按照宽度升序排列，高度按照降序排列，由于宽度可能存在相等的情况，
        // 这样保证答案的数组中宽度相同下至多只有一个在严格递增序列中
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });

        int n = envelopes.length, ans = 0;
        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(envelopes[i][1] > envelopes[j][1]) {
                    f[i] = Math.max(f[i], f[j]);
                }
            }
            ans = Math.max(ans, ++f[i]);
        }
        return ans;
    }
}
