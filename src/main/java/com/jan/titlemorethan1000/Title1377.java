package com.jan.titlemorethan1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1377. T 秒后青蛙的位置
 * 给你一棵由 n 个顶点组成的无向树，顶点编号从 1 到 n。青蛙从 顶点 1 开始起跳。规则如下：
 * 在一秒内，青蛙从它所在的当前顶点跳到另一个 未访问 过的顶点（如果它们直接相连）。
 * 青蛙无法跳回已经访问过的顶点。
 * 如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。
 * 如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。
 * 无向树的边用数组 edges 描述，其中 edges[i] = [ai, bi] 意味着存在一条直接连通 ai 和 bi 两个顶点的边。
 * 返回青蛙在 t 秒后位于目标顶点 target 上的概率。与实际答案相差不超过 10-5 的结果将被视为正确答案。
 *
 * 示例 1：
 * 输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
 * 输出：0.16666666666666666
 * 解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，第 1 秒 有 1/3 的概率跳到顶点 2 ，然后第 2 秒 有 1/2 的概率跳到顶点 4，
 * 因此青蛙在 2 秒后位于顶点 4 的概率是 1/3 * 1/2 = 1/6 = 0.16666666666666666 。
 *
 * 示例 2：
 * 输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
 * 输出：0.3333333333333333
 * 解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，有 1/3 = 0.3333333333333333 的概率能够 1 秒 后跳到顶点 7 。
 *
 * 提示：
 * 1 <= n <= 100
 * edges.length == n - 1
 * edges[i].length == 2
 * 1 <= ai, bi <= n
 * 1 <= t <= 50
 * 1 <= target <= n
 */
public class Title1377 {
    public static void main(String[] args) {
        int[][] edges1 = {{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}};

        Title1377 title1377 = new Title1377();
        System.out.println(title1377.frogPosition(7, edges1, 2, 4));
        System.out.println(title1377.frogPosition(7, edges1, 1, 7));

        System.out.println(title1377.frogPosition2(7, edges1, 2, 4));
        System.out.println(title1377.frogPosition2(7, edges1, 1, 7));
    }

    /**
     * 自底向上（归）
     */
    public double frogPosition(int n, int[][] edges, int t, int target) {
        List<Integer>[] g = new ArrayList[n + 1];
        Arrays.setAll(g, e->new ArrayList<>());
        g[1].add(0);
        for(int[] edge : edges) {
            int x = edge[0], y = edge[1];
            g[x].add(y);
            g[y].add(x);
        }

        long prod = dfs(g, target, 1, 0, t);
        return prod != 0 ? 1.0 / prod : 0;
    }

    private long dfs(List<Integer>[] g, int target, int x, int fa, int leftT) {
        if(leftT == 0) {
            return x == target ? 1 : 0;
        }
        if(target == x) {
            return g[x].size() == 1 ? 1 : 0;
        }

        for(int y : g[x]) {
            if(y != fa) {
                long prod = dfs(g, target, y, x, leftT - 1);
                if(prod != 0) {
                    return prod * (g[x].size() - 1);
                }
            }
        }

        return 0;
    }

    /**
     * 自顶向下（递）
     */
    private double ans;
    public double frogPosition2(int n, int[][] edges, int t, int target) {
        List<Integer>[] g = new ArrayList[n + 1];
        Arrays.setAll(g, e -> new ArrayList<>());
        g[1].add(0);
        for(int[] edge : edges) {
            int x = edge[0], y = edge[1];
            g[x].add(y);
            g[y].add(x);
        }

        dfs2(g, target, 1, 0, t, 1);
        return ans;
    }

    private boolean dfs2(List<Integer>[] g, int target, int x, int fa, int leftT, long prod) {
        if(x == target && (leftT == 0 || g[x].size() == 1)) {
            ans = 1.0 / prod;
            return true;
        }

        if(leftT == 0 || x == target) {
            return false;
        }

        for(int y : g[x]) {
            if(y != fa && dfs2(g, target, y, x, leftT, prod * (g[x].size() - 1))) {
                return true;
            }
        }

        return false;
    }
}
