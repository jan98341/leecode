package com.jan.title700.title620;

import com.jan.share.TreeNode;

/**
 * 617. 合并二叉树
 * 给你两棵二叉树： root1 和 root2 。
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。
 * 合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
 * 返回合并后的二叉树。
 * 注意: 合并过程必须从两个树的根节点开始。
 *
 * 示例 1：
 * 输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 * 输出：[3,4,5,5,4,null,7]
 *
 * 示例 2：
 * 输入：root1 = [1], root2 = [1,2]
 * 输出：[2,2]
 *
 * 提示：
 * 两棵树中的节点数目在范围 [0, 2000] 内
 * -10^4 <= Node.val <= 10^4
 */
public class Title617 {
    public static void main(String[] args) {

    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return null;
        TreeNode newNode = new TreeNode((root1 == null ? 0 : root1.val) + (root2 == null ? 0 : root2.val));
        newNode.left = mergeTrees(root1 == null ? null : root1.left, root2 == null ? null : root2.left);
        newNode.right = mergeTrees(root1 == null ? null : root1.right, root2 == null ? null : root2.right);
        return newNode;
    }

    /**
     * 如果 root 1是空的，无需合并，直接返回 root 2。
     * 如果 root 2是空的，无需合并，直接返回 root 1。
     * 如果都不为空，那么将这两个节点的值相加，作为合并后节点的新值。然后递归合并 root 1.left 与 root 2.left，得到合并后的左子树；
     * 递归合并 root1.right 与 root2.right，得到合并后的右子树。最后返回合并后的节点。
     */
    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if(root1 == null) return root2;
        if(root2 == null) return root1;
        return new TreeNode(root1.val + root2.val, mergeTrees2(root1.left, root2.left), mergeTrees2(root1.right, root2.right));
    }

    /**
     * 在两个节点都不为空时，把新节点的 val，left 和 right 直接保存到 root1中。
     */
    public TreeNode mergeTrees3(TreeNode root1, TreeNode root2) {
        if(root1 == null) return root2;
        if(root2 == null) return root1;
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }
}
