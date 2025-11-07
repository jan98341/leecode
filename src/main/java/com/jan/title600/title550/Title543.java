package com.jan.title600.title550;

import com.jan.share.TreeNode;

/**
 * 543. 二叉树的直径
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 * 两节点之间路径的 长度 由它们之间边数表示。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5]
 * 输出：3
 * 解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
 *
 * 示例 2：
 * 输入：root = [1,2]
 * 输出：1
 *
 * 提示：
 * 树中节点数目在范围 [1, 10^4] 内
 * -100 <= Node.val <= 100
 */
public class Title543 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode left11 = new TreeNode(2);
        TreeNode right11 = new TreeNode(3);
        root1.left = left11;
        root1.right = right11;
        TreeNode left12 = new TreeNode(4);
        TreeNode right12 = new TreeNode(5);
        left11.left = left12;
        left11.right = right12;

        TreeNode root2 = new TreeNode(1);
        TreeNode left21 = new TreeNode(2);
        root2.left = left21;

        System.out.println(diameterOfBinaryTree(root1));
        System.out.println(diameterOfBinaryTree(root2));
    }

    private static int ans = 0;
    public static int diameterOfBinaryTree(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private static int dfs(TreeNode t) {
        if (t == null) {
            return -1;
        }
        int l = dfs(t.left) + 1;
        int r = dfs(t.right) + 1;
        ans = Math.max(ans, l + r);
        return Math.max(l, r);
    }
}
