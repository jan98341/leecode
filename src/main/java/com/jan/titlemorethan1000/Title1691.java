package com.jan.titlemorethan1000;

import java.util.Arrays;

/**
 * 1691. 堆叠长方体的最大高度
 * 给你 n 个长方体 cuboids ，其中第 i 个长方体的长宽高表示为 cuboids[i] = [widthi, lengthi, heighti]（下标从 0 开始）。
 * 请你从 cuboids 选出一个 子集 ，并将它们堆叠起来。
 * 如果 widthi <= widthj 且 lengthi <= lengthj 且 heighti <= heightj ，你就可以将长方体 i 堆叠在长方体 j 上。
 * 你可以通过旋转把长方体的长宽高重新排列，以将它放在另一个长方体上。
 * 返回 堆叠长方体 cuboids 可以得到的 最大高度 。
 *
 * 示例 1：
 * 输入：cuboids = [[50,45,20],[95,37,53],[45,23,12]]
 * 输出：190
 * 解释：
 * 第 1 个长方体放在底部，53x37 的一面朝下，高度为 95 。
 * 第 0 个长方体放在中间，45x20 的一面朝下，高度为 50 。
 * 第 2 个长方体放在上面，23x12 的一面朝下，高度为 45 。
 * 总高度是 95 + 50 + 45 = 190 。
 *
 * 示例 2：
 * 输入：cuboids = [[38,25,45],[76,35,3]]
 * 输出：76
 * 解释：
 * 无法将任何长方体放在另一个上面。
 * 选择第 1 个长方体然后旋转它，使 35x3 的一面朝下，其高度为 76 。
 *
 * 示例 3：
 * 输入：cuboids = [[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]]
 * 输出：102
 * 解释：
 * 重新排列长方体后，可以看到所有长方体的尺寸都相同。
 * 你可以把 11x7 的一面朝下，这样它们的高度就是 17 。
 * 堆叠长方体的最大高度为 6 * 17 = 102 。
 *
 * 提示：
 * n == cuboids.length
 * 1 <= n <= 100
 * 1 <= widthi, lengthi, heighti <= 100
 */
public class Title1691 {
    public static void main(String[] args) {
        int[][] cuboids1 = {{50,45,20},{95,37,53},{45,23,12}};
        int[][] cuboids2 = {{38,25,45},{76,35,3}};
        int[][] cuboids3 = {{7,11,17},{7,17,11},{11,7,17},{11,17,7},{17,7,11},{17,11,7}};

        System.out.println(maxHeight(cuboids1));
        System.out.println(maxHeight(cuboids2));
        System.out.println(maxHeight(cuboids3));

        System.out.println(maxHeight2(cuboids1));
        System.out.println(maxHeight2(cuboids2));
        System.out.println(maxHeight2(cuboids3));
    }

    /**
     * 记忆化搜索
     * A可以堆叠在B上面，当且仅当 w1<w2且l1<l2且h1<h2，可以通过反证法进行证明
     * 通过上面的推论，每个长方体自身先按长宽高照升序排序，然后各长方体之间按照长宽高按照升序排列，最后使用LIS算法求最大高度
     */
    public static int maxHeight(int[][] cuboids) {
        for(int[] c : cuboids) {
            Arrays.sort(c);
        }
        Arrays.sort(cuboids, (a, b) -> (a[0] != b[0]) ? a[0] - b[0] : (a[1] != b[1]) ? a[1] - b[1] : a[2] - b[2]);

        int n = cuboids.length, ans = 0;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(i, cuboids, memo));
        }
        return ans;
    }

    private static int dfs(int i, int[][] cuboids, int[] memo) {
        if(memo[i] != -1) {
            return memo[i];
        }
        int res = 0;
        for(int j = 0; j < i; j++) {
            if(cuboids[i][1] >= cuboids[j][1] && cuboids[i][2] >= cuboids[j][2]) {
                res = Math.max(res, dfs(j, cuboids, memo));
            }
        }
        return memo[i] = res + cuboids[i][2];
    }

    /**
     * A可以堆叠在B上面，当且仅当 w1<w2且l1<l2且h1<h2，可以通过反证法进行证明
     * 通过上面的推论，每个长方体自身先按长宽高照升序排序，然后各长方体之间按照长宽高按照升序排列，最后使用LIS算法求最大高度
     */
    public static int maxHeight2(int[][] cuboids) {
        for(int[] c : cuboids) {
            Arrays.sort(c);
        }
        Arrays.sort(cuboids, (a, b) -> (a[0] != b[0]) ? a[0] - b[0] : (a[1] != b[1]) ? a[1] - b[1] : a[2] - b[2]);

        int n = cuboids.length, ans = 0;
        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(cuboids[i][1] >= cuboids[j][1] && cuboids[i][2] >= cuboids[j][2]) {
                    f[i] = Math.max(f[i], f[j]);
                }
            }
            f[i] += cuboids[i][2];
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
