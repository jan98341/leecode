package com.jan.title100.title60;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 52. N 皇后 II
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 *
 * 示例 1：
 * 输入：n = 4
 * 输出：2
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 *
 * 提示：
 * 1 <= n <= 9
 */
public class Title52 {
    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(totalNQueens(5)));
        System.out.println(JSON.toJSONString(totalNQueens(4)));
        System.out.println(JSON.toJSONString(totalNQueens(3)));
        System.out.println(JSON.toJSONString(totalNQueens(2)));
        System.out.println(JSON.toJSONString(totalNQueens(1)));

        System.out.println(JSON.toJSONString(totalNQueens2(5)));
        ansCount = 0;
        System.out.println(JSON.toJSONString(totalNQueens2(4)));
        ansCount = 0;
        System.out.println(JSON.toJSONString(totalNQueens2(3)));
        ansCount = 0;
        System.out.println(JSON.toJSONString(totalNQueens2(2)));
        ansCount = 0;
        System.out.println(JSON.toJSONString(totalNQueens2(1)));
    }

    public static int totalNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        int[] queens = new int[n];
        boolean[] col = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1];
        boolean[] diag2 = new boolean[2 * n - 1];
        dfs(0, queens, col, diag1, diag2, ans);
        return ans.size();
    }

    private static void dfs(int r, int[] queens, boolean[] col, boolean[] diag1, boolean[] diag2, List<List<String>> ans) {
        int n = queens.length;
        if (r == n) {
            List<String> board = new ArrayList<>();
            for(int c : queens) {
                char[] row = new char[n];
                Arrays.fill(row, '.');
                row[c] = 'Q';
                board.add(new String(row));
            }
            ans.add(board);
            return;
        }
        for (int c = 0; c < n; c++) {
            int rc = r - c + n - 1;
            if(!col[c] && !diag1[r + c] && !diag2[rc]) {
                queens[c] = r;                                  // 直接覆盖，无需恢复现场
                col[c] = diag1[r + c] = diag2[rc] = true;       // 皇后占用了 c 列和两条斜线
                dfs(r + 1, queens, col, diag1, diag2, ans);
                col[c] = diag1[r + c] = diag2[rc] = false;      // 恢复现场
            }
        }
    }

    private static int ansCount = 0;
    public static int totalNQueens2(int n) {
        boolean[] col = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1];
        boolean[] diag2 = new boolean[2 * n - 1];
        dfs2(0, col, diag1, diag2);
        return ansCount;
    }

    private static void dfs2(int r, boolean[] col, boolean[] diag1, boolean[] diag2) {
        int n = col.length;
        if (r == n) {
            ansCount++;
            return;
        }
        for (int c = 0; c < n; c++) {
            int rc = r - c + n - 1;
            if(!col[c] && !diag1[r + c] && !diag2[rc]) {
                col[c] = diag1[r + c] = diag2[rc] = true;       // 皇后占用了 c 列和两条斜线
                dfs2(r + 1, col, diag1, diag2);
                col[c] = diag1[r + c] = diag2[rc] = false;      // 恢复现场
            }
        }
    }
}
