package com.jan.title200.title110;

import com.jan.share.TreeNode;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：3
 *
 * 示例 2：
 * 输入：root = [1,null,2]
 * 输出：2
 *
 * 提示：
 * 树中节点的数量在 [0, 10^4] 区间内。
 * -100 <= Node.val <= 100
 */
public class Title104 {
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
        root2.right = node221;

        Title104 title104 = new Title104();
        System.out.println(title104.maxDepth(root1));
        System.out.println(title104.maxDepth(root2));

        System.out.println(title104.maxDepth2(root1));
        System.out.println(title104.maxDepth2(root2));
    }

    /**
     * 自底向上
     */
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 自顶向下
     */
    public int maxDepth2(TreeNode root) {
        ans = 0;
        dfs(root, 0);
        return ans;
    }

    private int ans;
    private void dfs(TreeNode node, int depth) {
        if(node == null) return;
        depth++;
        ans = Math.max(ans, depth);
        dfs(node.left, depth);
        dfs(node.right, depth);
    }
}
