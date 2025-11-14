package com.jan.titlemorethan2000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2646. 最小化旅行的价格总和
 * 现有一棵无向、无根的树，树中有 n 个节点，按从 0 到 n - 1 编号。给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，
 * 其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条边。
 * 每个节点都关联一个价格。给你一个整数数组 price ，其中 price[i] 是第 i 个节点的价格。
 * 给定路径的 价格总和 是该路径上所有节点的价格之和。
 * 另给你一个二维整数数组 trips ，其中 trips[i] = [starti, endi] 表示您从节点 starti 开始第 i 次旅行，并通过任何你喜欢的路径前往节点 endi 。
 * 在执行第一次旅行之前，你可以选择一些 非相邻节点 并将价格减半。
 * 返回执行所有旅行的最小价格总和。
 *
 * 示例 1：
 * 输入：n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips = [[0,3],[2,1],[2,3]]
 * 输出：23
 * 解释：
 * 上图表示将节点 2 视为根之后的树结构。第一个图表示初始树，第二个图表示选择节点 0 、2 和 3 并使其价格减半后的树。
 * 第 1 次旅行，选择路径 [0,1,3] 。路径的价格总和为 1 + 2 + 3 = 6 。
 * 第 2 次旅行，选择路径 [2,1] 。路径的价格总和为 2 + 5 = 7 。
 * 第 3 次旅行，选择路径 [2,1,3] 。路径的价格总和为 5 + 2 + 3 = 10 。
 * 所有旅行的价格总和为 6 + 7 + 10 = 23 。可以证明，23 是可以实现的最小答案。
 *
 * 示例 2：
 * 输入：n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
 * 输出：1
 *
 * 解释：
 * 上图表示将节点 0 视为根之后的树结构。第一个图表示初始树，第二个图表示选择节点 0 并使其价格减半后的树。
 * 第 1 次旅行，选择路径 [0] 。路径的价格总和为 1 。
 * 所有旅行的价格总和为 1 。可以证明，1 是可以实现的最小答案。
 */
public class Title2646 {
    public static void main(String[] args) {
        int[][] edges1 = {{0,1},{1,2},{1,3}}, trips1 = {{0,3},{2,1},{2,3}};
        int[]  price1 = {2,2,10,6};
        int[][] edges2 = {{0,1}}, trips2 = {{0,0}};
        int[]  price2 = {2,2};

        Title2646 title2646 = new Title2646();
        System.out.println(title2646.minimumTotalPrice(4, edges1, price1, trips1));
        System.out.println(title2646.minimumTotalPrice(2, edges2, price2, trips2));
    }

    /**
     * 暴力 DFS 每条路径
     * 先对每个 trips[i] 都做 dfs 一次这棵树，在 DFS 的过程中，把从 start 到 end 的路径上的每个点 x 的经过次数 cnt[x] 都加一
     * 既然知道了每个点会被经过多少次，把 price[i] 更新成 price[i]⋅cnt[i]，问题就转换成计算减半后的 price[i] 之和的最小值
     * 对于转换后的问题，做法和 337. 打家劫舍 III 是类似的
     */
    private List<Integer>[] g;
    private int[] cnt, price;
    private int end;
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            g[x].add(y);
            g[y].add(x);
        }

        // 先遍历所有旅游路线，获取树每个节点需要经过的次数
        cnt = new int[n];
        for(int[] t : trips) {
            end = t[1];
            dfs(t[0], -1);
        }

        // 根据树每个节点需要经过的次数和费用计算最小值
        this.price = price;
        int[] res = dp(0, -1);
        return Math.min(res[0], res[1]);
    }

    private boolean dfs(int x, int fa) {
        // 找到end，计数加1
        if(x == end) {
            cnt[x]++;
            return true;
        }

        // // x 是 end 的祖先节点，也就在路径上
        for(int y : g[x]) {
            if(y != fa && dfs(y, x)) {
                cnt[x]++;
                return true;
            }
        }
        // // 未找到 end
        return false;
    }

    /**
     * 类似 337. 打家劫舍 III
     */
    private int[] dp(int x, int fa) {
        int notHalve = price[x] * cnt[x];
        int halve = notHalve / 2;
        for(int y : g[x]) {
            if(y != fa) {
                // 计算 y 不变/减半的最小价值总和
                int[] res = dp(y, x);
                // x 不变，那么 y 可以不变，可以减半，取这两种情况的最小值
                notHalve += Math.min(res[0], res[1]);
                // x 减半，那么 y 只能不变
                halve += res[0];
            }
        }
        return new int[]{notHalve, halve};
    }
}
