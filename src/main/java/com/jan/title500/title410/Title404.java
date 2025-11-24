package com.jan.title500.title410;

import com.jan.share.TreeNode;

import java.util.Stack;

/**
 * 404. 左叶子之和
 * 给定二叉树的根节点 root ，返回所有左叶子之和。
 *
 * 示例 1：
 * 输入: root = [3,9,20,null,null,15,7]
 * 输出: 24
 * 解释: 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 *
 * 示例 2:
 * 输入: root = [1]
 * 输出: 0
 *
 * 提示:
 * 节点数在 [1, 1000] 范围内
 * -1000 <= Node.val <= 1000
 */
public class Title404 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        TreeNode node121 = new TreeNode(9);
        TreeNode node122 = new TreeNode(20);
        TreeNode node131 = new TreeNode(15);
        TreeNode node132 = new TreeNode(7);
        root1.left = node121;
        root1.right = node122;
        node122.left = node131;
        node122.right = node132;

        TreeNode root2 = new TreeNode(1);

        Title404 title404 = new Title404();
        System.out.println(title404.sumOfLeftLeaves(root1));
        System.out.println(title404.sumOfLeftLeaves(root2));

        System.out.println(title404.sumOfLeftLeaves2(root1));
        System.out.println(title404.sumOfLeftLeaves2(root2));
    }

    /**
     * 前序遍历中累计左子树值
     */
    public int sumOfLeftLeaves(TreeNode root) {
        int ans = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node.left != null) {
                ans += node.left.val;
                stack.push(node.left);
            }
            if(node.right != null) stack.push(node.right);
        }
        return ans;
    }

    /**
     * 中序遍历中累计左子树值
     */
    public int sumOfLeftLeaves2(TreeNode root) {
        int ans = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()) {
            while(cur != null) {
                stack.push(cur);
                if(cur.left != null)  ans += cur.left.val;
                cur = cur.left;
            }
            cur = stack.pop();
            cur = cur.right;
        }
        return ans;
    }
}
