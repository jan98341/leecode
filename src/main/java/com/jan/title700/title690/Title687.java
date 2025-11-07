package com.jan.title700.title690;

import com.jan.share.TreeNode;

/**
 * 687. 最长同值路径
 * 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
 * 两个节点之间的路径长度 由它们之间的边数表示。
 *
 * 示例 1:
 * 输入：root = [5,4,5,1,1,5]
 * 输出：2
 *
 * 示例 2:
 * 输入：root = [1,4,5,4,4,5]
 * 输出：2
 *
 * 提示:
 * 树的节点数的范围是 [0, 10^4]
 * -1000 <= Node.val <= 1000
 * 树的深度将不超过 1000
 */
public class Title687 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(5);
        TreeNode node121 = new TreeNode(4);
        TreeNode node122 = new TreeNode(5);
        TreeNode node131 = new TreeNode(1);
        TreeNode node132 = new TreeNode(1);
        TreeNode node133 = new TreeNode(5);
        root1.left = node121;root1.right = node122;
        node121.left = node131;node121.right = node132;node122.right = node133;

        TreeNode root2 = new TreeNode(1);
        TreeNode node221 = new TreeNode(4);
        TreeNode node222 = new TreeNode(5);
        TreeNode node231 = new TreeNode(4);
        TreeNode node232 = new TreeNode(4);
        TreeNode node233 = new TreeNode(5);
        root2.left = node221;root2.right = node222;
        node221.left = node231;node221.right = node232;
        node222.left = node233;

        Title687 title687 = new Title687();
        System.out.println(title687.longestUnivaluePath(root1));
        System.out.println(title687.longestUnivaluePath(root2));
    }

    private int ans;
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            // 下面 +1 后，对于叶子节点就刚好是 0
            return -1;
        }
        int lLen = dfs(node.left) + 1;
        int rLen = dfs(node.right) + 1;
        if(node.left != null && node.val != node.left.val) {
            lLen = 0;
        }
        if(node.right != null && node.val != node.right.val) {
            rLen = 0;
        }
        ans = Math.max(ans, lLen + rLen);
        return Math.max(lLen, rLen);
    }
}
