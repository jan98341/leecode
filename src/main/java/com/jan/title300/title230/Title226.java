package com.jan.title300.title230;

import com.alibaba.fastjson.JSON;
import com.jan.share.TreeNode;

/**
 * 226. 翻转二叉树
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 *
 *  示例 1：
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 *
 * 示例 2：
 * 输入：root = [2,1,3]
 * 输出：[2,3,1]
 *
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 *
 * 提示：
 * 树中节点数目范围在 [0, 100] 内
 * -100 <= Node.val <= 100
 */
public class Title226 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(4);
        TreeNode p121 = new TreeNode(2);
        TreeNode p122 = new TreeNode(7);
        TreeNode p131 = new TreeNode(1);
        TreeNode p132 = new TreeNode(3);
        TreeNode p133 = new TreeNode(6);
        TreeNode p134 = new TreeNode(9);
        root1.left = p121;
        root1.right = p122;
        p121.left = p131;
        p121.right = p132;
        p122.left = p133;
        p122.right = p134;

        Title226 title226 = new Title226();
        TreeNode printNode = new TreeNode();
        printNode.print(title226.invertTree(root1));
        printNode.print(title226.invertTree2(root1));
        printNode.print(title226.invertTree3(root1));
    }

    public TreeNode invertTree(TreeNode root) {
        dfs(root);
        return root;
    }

    private void dfs(TreeNode treeNode) {
        if(treeNode == null) return;
        TreeNode temp = new TreeNode();
        temp = treeNode.left;
        treeNode.left = treeNode.right;
        treeNode.right = temp;
        dfs(treeNode.left);
        dfs(treeNode.right);
    }

    public TreeNode invertTree2(TreeNode root) {
        if(root == null) return null;
        TreeNode left = invertTree2(root.left);
        TreeNode right = invertTree2(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public TreeNode invertTree3(TreeNode root) {
        if(root == null) return null;
        TreeNode temp = root.left;
        root.left = root.right;     // 交换左右儿子
        root.right = temp;
        invertTree3(root.left);     // 翻转左子树
        invertTree3(root.right);    // 翻转右子树
        return root;
    }
}
