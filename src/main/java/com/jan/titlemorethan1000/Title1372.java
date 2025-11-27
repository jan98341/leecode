package com.jan.titlemorethan1000;

import com.jan.share.TreeNode;

/**
 * 1372. 二叉树中的最长交错路径
 * 给你一棵以 root 为根的二叉树，二叉树中的交错路径定义如下：
 * 选择二叉树中 任意 节点和一个方向（左或者右）。
 * 如果前进方向为右，那么移动到当前节点的的右子节点，否则移动到它的左子节点。
 * 改变前进方向：左变右或者右变左。
 * 重复第二步和第三步，直到你在树中无法继续移动。
 * 交错路径的长度定义为：访问过的节点数目 - 1（单个节点的路径长度为 0 ）。
 * 请你返回给定树中最长 交错路径 的长度。
 *
 * 示例 1：
 * 输入：root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
 * 输出：3
 * 解释：蓝色节点为树中最长交错路径（右 -> 左 -> 右）。
 *
 * 示例 2：
 * 输入：root = [1,1,1,null,1,null,null,1,1,null,1]
 * 输出：4
 * 解释：蓝色节点为树中最长交错路径（左 -> 右 -> 左 -> 右）。
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：0
 *
 * 提示：
 * 每棵树最多有 50000 个节点。
 * 每个节点的值在 [1, 100] 之间。
 */
public class Title1372 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode p121 = new TreeNode(1);
        TreeNode p131 = new TreeNode(1);
        TreeNode p132 = new TreeNode(1);
        TreeNode p141 = new TreeNode(1);
        TreeNode p142 = new TreeNode(1);
        TreeNode p151 = new TreeNode(1);
        TreeNode p161 = new TreeNode(1);
        root1.right = p121;
        p121.left = p131;
        p121.right = p132;
        p132.left = p141;
        p132.right = p142;
        p141.right = p151;
        p151.right = p161;

        Title1372 title1372 = new Title1372();
        System.out.println(title1372.longestZigZag(root1));
    }

    public int longestZigZag(TreeNode root) {
        dfs(root, 1,0);
        dfs(root, 2,0);
        return ans;
    }

    private int ans;
    private void dfs(TreeNode node, int preDirect, int cnt) {
        ans = Math.max(ans, cnt);
        // preDirect=1是父节点左前进，preDirect=2是父节点右前进
        if(preDirect == 1) {
            if(node.left != null) dfs(node.left, 1, 1);
            if(node.right != null) dfs(node.right, 2, cnt + 1);
        }
        if(preDirect == 2) {
            if(node.left != null) dfs(node.left, 1, cnt + 1);
            if(node.right != null) dfs(node.right, 2, 1);
        }
    }
}
