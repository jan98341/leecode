package com.jan.title200.title130;

import com.jan.share.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 *
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *
 * 提示：
 * 树中节点数目范围是 [1, 3 * 10^4]
 * -1000 <= Node.val <= 1000
 */
public class Title124 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode left11 = new TreeNode(2);
        TreeNode right11 = new TreeNode(3);
        root1.left = left11;
        root1.right = right11;

        TreeNode root2 = new TreeNode(-10);
        TreeNode left21 = new TreeNode(9);
        TreeNode right21 = new TreeNode(20);
        TreeNode left221 = new TreeNode(15);
        TreeNode right222 = new TreeNode(7);
        root2.left = left21;
        root2.right = right21;
        right21.left = left221;
        right21.right = right222;

        System.out.println(maxPathSum(root1));
        System.out.println(maxPathSum(root2));
    }

    private static int ans;
    public static int maxPathSum(TreeNode root) {
        ans = Integer.MIN_VALUE;
        dfs(root);
        return ans;
    }

    private static int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        // 两条链拼成路径
        ans = Math.max(ans, left + right + node.val);
        // 当前子树最大链和（注意这里和 0 取最大值了）
        return Math.max(Math.max(left, right) + node.val, 0);
    }
}
