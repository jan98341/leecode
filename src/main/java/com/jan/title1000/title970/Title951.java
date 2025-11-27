package com.jan.title1000.title970;

import com.jan.share.TreeNode;

/**
 * 951. 翻转等价二叉树
 * 我们可以为二叉树 T 定义一个 翻转操作 ，如下所示：选择任意节点，然后交换它的左子树和右子树。
 * 只要经过一定次数的翻转操作后，能使 X 等于 Y，我们就称二叉树 X 翻转 等价 于二叉树 Y。
 * 这些树由根节点 root1 和 root2 给出。如果两个二叉树是否是翻转 等价 的树，则返回 true ，否则返回 false 。
 *
 * 示例 1：
 * Flipped Trees Diagram
 * 输入：root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * 输出：true
 * 解释：我们翻转值为 1，3 以及 5 的三个节点。
 *
 * 示例 2:
 * 输入: root1 = [], root2 = []
 * 输出: true
 *
 * 示例 3:
 * 输入: root1 = [], root2 = [1]
 * 输出: false
 *
 * 提示：
 * 每棵树节点数在 [0, 100] 范围内
 * 每棵树中的每个值都是唯一的、在 [0, 99] 范围内的整数
 */
public class Title951 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode p121 = new TreeNode(2);
        TreeNode p122 = new TreeNode(3);
        TreeNode p131 = new TreeNode(4);
        TreeNode p132 = new TreeNode(5);
        TreeNode p133 = new TreeNode(6);
        TreeNode p141 = new TreeNode(7);
        TreeNode p142 = new TreeNode(8);
        root1.left = p121;
        root1.right = p122;
        p121.left = p131;
        p121.right = p132;
        p122.left = p133;
        p132.left = p141;
        p132.right = p142;

        TreeNode root2 = new TreeNode(1);
        TreeNode p221 = new TreeNode(3);
        TreeNode p222 = new TreeNode(2);
        TreeNode p231 = new TreeNode(6);
        TreeNode p232 = new TreeNode(4);
        TreeNode p233 = new TreeNode(5);
        TreeNode p241 = new TreeNode(8);
        TreeNode p242 = new TreeNode(7);
        root2.left = p221;
        root2.right = p222;
        p221.right = p231;
        p222.left = p232;
        p222.right = p233;
        p233.left = p241;
        p233.right = p242;

        Title951 title951 = new Title951();
        System.out.println(title951.flipEquiv(root1, root2));
        System.out.println(title951.flipEquiv(null, null));
        System.out.println(title951.flipEquiv(null, new TreeNode(1)));
    }

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null || root1.val != root2.val) return false;
        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) ||
                flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }
}
