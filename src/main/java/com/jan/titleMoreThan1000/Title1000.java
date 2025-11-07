package com.jan.titleMoreThan1000;

import java.util.Arrays;

/**
 * 1000. 合并石头的最低成本
 * 有 n 堆石头排成一排，第 i 堆中有 stones[i] 块石头。
 * 每次 移动 需要将 连续的 k 堆石头合并为一堆，而这次移动的成本为这 k 堆中石头的总数。
 * 返回把所有石头合并成一堆的最低成本。如果无法合并成一堆，返回 -1 。
 *
 * 示例 1：
 * 输入：stones = [3,2,4,1], K = 2
 * 输出：20
 * 解释：
 * 从 [3, 2, 4, 1] 开始。
 * 合并 [3, 2]，成本为 5，剩下 [5, 4, 1]。
 * 合并 [4, 1]，成本为 5，剩下 [5, 5]。
 * 合并 [5, 5]，成本为 10，剩下 [10]。
 * 总成本 20，这是可能的最小值。
 *
 * 示例 2：
 * 输入：stones = [3,2,4,1], K = 3
 * 输出：-1
 * 解释：任何合并操作后，都会剩下 2 堆，我们无法再进行合并。所以这项任务是不可能完成的。.
 *
 * 示例 3：
 * 输入：stones = [3,5,1,2,6], K = 3
 * 输出：25
 * 解释：
 * 从 [3, 5, 1, 2, 6] 开始。
 * 合并 [5, 1, 2]，成本为 8，剩下 [3, 8, 6]。
 * 合并 [3, 8, 6]，成本为 17，剩下 [17]。
 * 总成本 25，这是可能的最小值。
 *
 * 提示：
 * n == stones.length
 * 1 <= n <= 30
 * 1 <= stones[i] <= 100
 * 2 <= k <= 30
 */
public class Title1000 {
    public static void main(String[] args) {
        Title1000 title1000 = new Title1000();
        int[] stones1 = {3,2,4,1};
        int[] stones2 = {3,2,4,1};
        int[] stones3 = {3,5,1,2,6};

        System.out.println(title1000.mergeStones(stones1, 2));
        System.out.println(title1000.mergeStones(stones2, 3));
        System.out.println(title1000.mergeStones(stones3, 3));

        System.out.println(title1000.mergeStones2(stones1, 2));
        System.out.println(title1000.mergeStones2(stones2, 3));
        System.out.println(title1000.mergeStones2(stones3, 3));

        System.out.println(title1000.mergeStones3(stones1, 2));
        System.out.println(title1000.mergeStones3(stones2, 3));
        System.out.println(title1000.mergeStones3(stones3, 3));
    }

    /**
     * dfs(i,j,p)=min{dfs(i,m,1)+dfs(m,j,p-1)}, 其中m=i+(k-1)x，p>1
     * 时间复杂度：O(n^3),空间复杂度：O(kn^2)。
     */
    private int[][][] memo;
    private int[] s;
    private int k;
    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if((n - 1) % (k - 1) > 0) {
            return -1;
        }

        this.k = k;
        s = new int[n + 1];
        for(int i = 0; i < n; i++) {
            s[i + 1] = s[i] + stones[i];
        }
        memo = new int[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        return dfs(0, n - 1, 1);
    }
    private int dfs(int i, int j, int p) {
        // 已经计算过，直接使用计算结果
        if(memo[i][j][p] != -1) {
            return memo[i][j][p];
        }
        if(p == 1) {
            return memo[i][j][p] = i == j ? 0 : dfs(i, j, k) + s[j + 1] - s[i];
        }
        int res = Integer.MAX_VALUE;
        for(int m = i; m < j; m += k - 1) {
            // 合并规则是从i开始，从左到右进行合并
            res = Math.min(res, dfs(i, m, 1) + dfs(m + 1, j, p - 1));
        }
        return memo[i][j][p] = res;
    }

    /**
     * dfs(i,j,1)=min{dfs(i,m,1)+dfs(m+1,j,k-1)} + sum(stone[q]), 其中m=i+(k-1)x，i<=q<=j
     * 化简为：
     * 1、当(j-i)mod(k-1)==0时， dfs(i,j)=min{dfs(i,m)+dfs(m+1,j)} + sum(stone[q]), 其中m=i+(k-1)x，i<=q<=j
     * 2、当(j-i)mod(k-1)！=0时， dfs(i,j)=min{dfs(i,m)+dfs(m+1,j)}, 其中m=i+(k-1)x
     */
    private int[][] memo2;
    public int mergeStones2(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) > 0) {
            return -1;
        }

        this.k = k;
        s = new int[n + 1];
        for(int i = 0; i < n; i++) {
            s[i + 1] = s[i] + stones[i];
        }
        memo2 = new int[n][n];
        for (int[] row : memo2) {
            Arrays.fill(row, -1);
        }

        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if(i == j) {
            return 0;
        }
        if(memo2[i][j] != -1) {
            return memo2[i][j];
        }
        int res = Integer.MAX_VALUE;
        for(int m = i; m < j; m += k - 1) {
            res = Math.min(res, dfs(i, m) + dfs(m + 1, j));
        }
        if((j - i) % (k - 1) == 0) {
            res += s[j + 1] - s[i];
        }
        return memo2[i][j] = res;
    }

    public int mergeStones3(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) > 0) {
            return -1;
        }

        int[] s = new int[n + 1];
        for(int i = 0; i < n; i++) {
            s[i + 1] = s[i] + stones[i];
        }

        int[][] f = new int[n][n];
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                f[i][j] = Integer.MAX_VALUE;
                for(int m = i; m < j; m += k - 1) {
                    f[i][j] = Math.min(f[i][j], f[i][m] + f[m + 1][j]);
                }
                if((j - i) % (k - 1) == 0) {
                    f[i][j] += s[j + 1] - s[i];
                }
            }
        }
        return f[0][n - 1];
    }
}
