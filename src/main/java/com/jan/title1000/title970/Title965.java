package com.jan.title1000.title970;

import com.jan.share.TreeNode;

/**
 * 965. 单值二叉树
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 *
 * 示例 1：
 * 输入：[1,1,1,1,1,null,1]
 * 输出：true
 *
 * 示例 2：
 * 输入：[2,2,2,5,2]
 * 输出：false
 *
 * 提示：
 * 给定树的节点数范围是 [1, 100]。
 * 每个节点的值都是整数，范围为 [0, 99] 。
 */
public class Title965 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode node121 = new TreeNode(1);
        TreeNode node122 = new TreeNode(1);
        TreeNode node131 = new TreeNode(1);
        TreeNode node132 = new TreeNode(1);
        TreeNode node133 = new TreeNode(1);
        root1.left = node121;
        root1.right = node122;
        node121.left = node131;
        node121.right = node132;
        node122.right = node133;

        TreeNode root2 = new TreeNode(2);
        TreeNode node221 = new TreeNode(2);
        TreeNode node222 = new TreeNode(2);
        TreeNode node231 = new TreeNode(5);
        TreeNode node232 = new TreeNode(2);
        root2.left = node221;
        root2.right = node222;
        node221.left = node231;
        node221.right = node232;

        Title965 title965 = new Title965();
        System.out.println(title965.isUnivalTree(root1));
        System.out.println(title965.isUnivalTree(root2));

        System.out.println(title965.isUnivalTree2(root1));
        System.out.println(title965.isUnivalTree2(root2));
    }

    public boolean isUnivalTree(TreeNode root) {
        if(root == null) return true;
        dfs(root, root.val);
        return same;
    }

    private boolean same = true;
    private void dfs(TreeNode node, int val) {
        if(node == null || !same) {
            return;
        }

        if(node.val != val) {
            same = false;
            return;
        }

        dfs(node.left, val);
        dfs(node.right, val);
    }

    public boolean isUnivalTree2(TreeNode root) {
        if(root == null) return true;
        if(root.left != null && root.val != root.left.val) return false;
        if(root.right != null && root.val != root.right.val) return false;
        return isUnivalTree2(root.left) && isUnivalTree2(root.right);
    }
}
