package com.jan.titleMoreThan1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1377. T 秒后青蛙的位置
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
