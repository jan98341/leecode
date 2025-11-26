package com.jan.title200.title110;

import com.jan.share.TreeNode;

/**
 * 101. 对称二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 * 提示：
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 */
public class Title101 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode p121 = new TreeNode(2);
        TreeNode p122 = new TreeNode(2);
        TreeNode p131 = new TreeNode(3);
        TreeNode p132 = new TreeNode(4);
        TreeNode p133 = new TreeNode(4);
        TreeNode p134 = new TreeNode(3);
        root1.left = p121;
        root1.right = p122;
        p121.left = p131;
        p121.right = p132;
        p122.left = p133;
        p122.right = p134;

        TreeNode root2 = new TreeNode(1);
        TreeNode p221 = new TreeNode(2);
        TreeNode p222 = new TreeNode(2);
        TreeNode p231 = new TreeNode(3);
        TreeNode p232 = new TreeNode(3);
        root2.left = p221;
        root2.right = p222;
        p221.right = p231;
        p222.right = p232;

        Title101 title101 = new Title101();
        System.out.println(title101.isSymmetric(root1));
        System.out.println(title101.isSymmetric(root2));
    }

    public boolean isSymmetric(TreeNode root) {
        return isSameTree(root.left, root.right);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 利用题100验证两棵树是否相同，这里是对称，需要左右互换
        if(p == null || q == null) return p == q;
        return (p.val == q.val) && isSameTree(p.left, q.right) && isSameTree(p.right, q.left);
    }
}
