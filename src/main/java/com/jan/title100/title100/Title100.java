package com.jan.title100.title100;

import com.jan.share.TreeNode;

/**
 * 100. 相同的树
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * 示例 1：
 * 输入：p = [1,2,3], q = [1,2,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：p = [1,2], q = [1,null,2]
 * 输出：false
 *
 * 示例 3：
 * 输入：p = [1,2,1], q = [1,1,2]
 * 输出：false
 *
 * 提示：
 * 两棵树上的节点数目都在范围 [0, 100] 内
 * -10^4 <= Node.val <= 10^4
 */
public class Title100 {
    public static void main(String[] args) {
        TreeNode p1 = new TreeNode(1);
        TreeNode p121 = new TreeNode(2);
        TreeNode p122 = new TreeNode(3);
        p1.left = p121;
        p1.right = p122;
        TreeNode q1 = new TreeNode(1);
        TreeNode q121 = new TreeNode(2);
        TreeNode q122 = new TreeNode(3);
        q1.left = q121;
        q1.right = q122;

        TreeNode p2 = new TreeNode(1);
        TreeNode p221 = new TreeNode(2);
        p2.left = p221;
        TreeNode q2 = new TreeNode(1);
        TreeNode q221 = new TreeNode(2);
        q2.right = q221;

        Title100 title100 = new Title100();
        System.out.println(title100.isSameTree(p1, q1));
        System.out.println(title100.isSameTree(p2, q2));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null || q == null) return p == q;
        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
