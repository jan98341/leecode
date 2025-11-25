package com.jan.title1000.title990;

import com.jan.share.TreeNode;

import java.util.*;

/**
 * 987. 二叉树的垂序遍历
 * 给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。
 * 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的根结点位于 (0, 0) 。
 * 二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。
 * 如果同行同列上有多个结点，则按结点的值从小到大进行排序。
 * 返回二叉树的 垂序遍历 序列。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[9],[3,15],[20],[7]]
 * 解释：
 * 列 -1 ：只有结点 9 在此列中。
 * 列  0 ：只有结点 3 和 15 在此列中，按从上到下顺序。
 * 列  1 ：只有结点 20 在此列中。
 * 列  2 ：只有结点 7 在此列中。
 *
 * 示例 2：
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[[4],[2],[1,5,6],[3],[7]]
 * 解释：
 * 列 -2 ：只有结点 4 在此列中。
 * 列 -1 ：只有结点 2 在此列中。
 * 列  0 ：结点 1 、5 和 6 都在此列中。
 *           1 在上面，所以它出现在前面。
 *           5 和 6 位置都是 (2, 0) ，所以按值从小到大排序，5 在 6 的前面。
 * 列  1 ：只有结点 3 在此列中。
 * 列  2 ：只有结点 7 在此列中。
 *
 * 示例 3：
 * 输入：root = [1,2,3,4,6,5,7]
 * 输出：[[4],[2],[1,5,6],[3],[7]]
 * 解释：
 * 这个示例实际上与示例 2 完全相同，只是结点 5 和 6 在树中的位置发生了交换。
 * 因为 5 和 6 的位置仍然相同，所以答案保持不变，仍然按值从小到大排序。
 *
 * 提示：
 * 树中结点数目总数在范围 [1, 1000] 内
 * 0 <= Node.val <= 1000
 */
public class Title987 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        TreeNode node121 = new TreeNode(9);
        TreeNode node122 = new TreeNode(20);
        TreeNode node131 = new TreeNode(15);
        TreeNode node132 = new TreeNode(7);
        root1.left = node121;
        root1.right = node122;
        node122.left = node131;
        node122.right = node132;

        TreeNode root2 = new TreeNode(1);
        TreeNode node221 = new TreeNode(2);
        TreeNode node222 = new TreeNode(3);
        TreeNode node231 = new TreeNode(4);
        TreeNode node232 = new TreeNode(5);
        TreeNode node233 = new TreeNode(6);
        TreeNode node234 = new TreeNode(7);
        root2.left = node221;
        root2.right = node222;
        node221.left = node231;
        node221.right = node232;
        node222.left = node233;
        node222.right = node234;

        Title987 title987 = new Title987();
        System.out.println(title987.verticalTraversal(root1));
        System.out.println(title987.verticalTraversal(root2));

        System.out.println(title987.verticalTraversal2(root1));
        System.out.println(title987.verticalTraversal2(root2));
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // 这里需要使用TreeMap对列进行排序
        Map<Integer, List<int[]>> groups = new TreeMap<>();
        dfs(root, 0, 0, groups);

        List<List<Integer>> ans = new ArrayList<>(groups.size());
        for(List<int[]> g : groups.values()) {
            // 对每组数据按照行值进行排序
            g.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
            List<Integer> vals = new ArrayList<>(g.size());
            for(int[] a : g) {
                vals.add(a[1]);
            }
            ans.add(vals);
        }
        return ans;
    }

    private void dfs(TreeNode node, int row, int col, Map<Integer, List<int[]>> groups) {
        if (node == null) return;
        groups.computeIfAbsent(col, k -> new ArrayList<>()).add(new int[]{row, node.val});

        // col 相同的分到同一组
        dfs(node.left, row + 1, col - 1, groups);
        dfs(node.right, row + 1, col + 1, groups);
    }

    /**
     * 把所有 (col,row,val) 全部丢到同一个列表中，排序后按照 col 分组
     */
    public List<List<Integer>> verticalTraversal2(TreeNode root) {
        List<int[]> data = new ArrayList<>();
        dfs2(root, 0, 0, data);

        ArrayList<List<Integer>> ans = new ArrayList<>();
        data.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] != b[1] ? a[1] - b[1] : a[2] - b[2]);
        int lastCol = Integer.MIN_VALUE;
        for(int[] d : data) {
            if(d[0] != lastCol) {
                lastCol = d[0];
                ans.add(new ArrayList<>());
            }
            ans.get(ans.size() - 1).add(d[2]);
        }
        return ans;
    }

    private void dfs2(TreeNode node, int row, int col, List<int[]> data) {
        if (node == null) return;
        data.add(new int[]{col, row, node.val});
        dfs2(node.left, row + 1, col - 1, data);
        dfs2(node.right, row + 1, col + 1, data);
    }
}
