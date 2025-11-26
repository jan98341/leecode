package com.jan.title200.title110;

import com.jan.share.TreeNode;

/**
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是 平衡二叉树
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 *
 * 示例 3：
 * 输入：root = []
 * 输出：true
 *
 * 提示：
 * 树中的节点数在范围 [0, 5000] 内
 * -10^4 <= Node.val <= 10^4
 */
public class Title110 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        TreeNode p121 = new TreeNode(9);
        TreeNode p122 = new TreeNode(20);
        TreeNode p131 = new TreeNode(15);
        TreeNode p132 = new TreeNode(7);
        root1.left = p121;
        root1.right = p122;
        p122.left = p131;
        p122.right = p132;

        TreeNode root2 = new TreeNode(1);
        TreeNode p221 = new TreeNode(2);
        TreeNode p222 = new TreeNode(2);
        TreeNode p231 = new TreeNode(3);
        TreeNode p232 = new TreeNode(3);
        TreeNode p241 = new TreeNode(4);
        TreeNode p242 = new TreeNode(4);
        root2.left = p221;
        root2.right = p222;
        p221.left = p231;
        p221.right = p232;
        p231.left = p241;
        p231.right = p242;

        Title110 title110 = new Title110();
        System.out.println(title110.isBalanced(root1));
        System.out.println(title110.isBalanced(root2));
    }

    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode node) {
        if(node == null) return 0;
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        if(leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
