package com.jan.title200.title120;

import com.jan.share.TreeNode;

/**
 * 112. 路径总和
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，
 * 这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
 * 叶子节点 是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 * 解释：等于目标和的根节点到叶节点路径如上图所示。
 *
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 * 解释：树中存在两条根节点到叶子节点的路径：
 * (1 --> 2): 和为 3
 * (1 --> 3): 和为 4
 * 不存在 sum = 5 的根节点到叶子节点的路径。
 *
 * 示例 3：
 * 输入：root = [], targetSum = 0
 * 输出：false
 * 解释：由于树是空的，所以不存在根节点到叶子节点的路径。
 *
 * 提示：
 * 树中节点的数目在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */
public class Title112 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(5);
        TreeNode node21 = new TreeNode(4);
        TreeNode node22 = new TreeNode(8);
        TreeNode node31 = new TreeNode(11);
        TreeNode node32 = new TreeNode(13);
        TreeNode node33 = new TreeNode(4);
        TreeNode node41 = new TreeNode(7);
        TreeNode node42 = new TreeNode(2);
        TreeNode node43 = new TreeNode(1);
        root1.left = node21;
        root1.right = node22;
        node21.left = node31;
        node22.left = node32;
        node22.right = node33;
        node31.left = node41;
        node31.right = node42;
        node33.right = node43;

        TreeNode root2 = new TreeNode(1);
        TreeNode node221 = new TreeNode(2);
        TreeNode node222 = new TreeNode(3);
        root2.left = node221;
        root2.right = node222;

        TreeNode root3 = new TreeNode(1);

        Title112 title112 = new Title112();
        System.out.println(title112.hasPathSum(root1, 22));
        System.out.println(title112.hasPathSum(root2, 5));
        System.out.println(title112.hasPathSum(root3, 0));

        System.out.println(title112.hasPathSum2(root1, 22));
        System.out.println(title112.hasPathSum2(root2, 5));
        System.out.println(title112.hasPathSum2(root3, 0));
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        targetSum -= root.val;
        // 如果当前节点是叶子节点
        if(root.left == null && root.right == null) {
            return targetSum == 0;
        }
        return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
    }

    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}
