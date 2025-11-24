package com.jan.title200.title120;

import com.jan.share.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 *
 * 示例 2：
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 *
 * 提示：
 * 树中节点数的范围在 [0, 10^5] 内
 * -1000 <= Node.val <= 1000
 */
public class Title111 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        TreeNode node121 = new TreeNode(9);
        TreeNode node122 = new TreeNode(20);
        TreeNode node133 = new TreeNode(15);
        TreeNode node134 = new TreeNode(7);
        root1.left = node121;
        root1.right = node122;
        node122.left = node133;
        node122.right = node134;

        TreeNode root2 = new TreeNode(2);
        TreeNode node221 = new TreeNode(3);
        TreeNode node231 = new TreeNode(4);
        TreeNode node241 = new TreeNode(5);
        TreeNode node251 = new TreeNode(6);
        root2.right = node221;
        node221.right = node231;
        node231.right = node241;
        node241.right = node251;

        Title111 title111 = new Title111();
        System.out.println(title111.minDepth(root1));
        System.out.println(title111.minDepth(root2));

        System.out.println(title111.minDepth2(root1));
        System.out.println(title111.minDepth2(root2));

        System.out.println(title111.minDepth3(root1));
        System.out.println(title111.minDepth3(root2));

        System.out.println(title111.minDepth4(root1));
        System.out.println(title111.minDepth4(root2));
    }

    /**
     * 通过层次遍历方式，自顶向底进行查找，找第一个叶子节点层数即为答案
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> linked = new LinkedList<>();
        linked.push(root);
        int ans = 0;
        while (!linked.isEmpty()) {
            ans++;
            int size = linked.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = linked.poll();
                if(node.left == null && node.right == null) return ans;
                if(node.left != null) linked.push(node.left);
                if(node.right != null)  linked.push(node.right);
            }
        }
        return ans;
    }

    /**
     * 自顶向下「递」 ????
     */
    public int minDepth2(TreeNode root) {
        dfs(root, 0);
        return root == null ? 0 : ans;
    }

    private int ans = Integer.MAX_VALUE;
    private void dfs(TreeNode node, int cnt) {
        if (node == null || ++cnt >= ans) {
            return;
        }
        if (node.left == null && node.right == null) { // node 是叶子
            ans = cnt;
            return;
        }
        dfs(node.left, cnt);
        dfs(node.right, cnt);
    }

    /**
     * 自底向上「归」
     */
    public int minDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.right == null) {
            return minDepth3(root.left) + 1;
        }
        if (root.left == null) {
            return minDepth3(root.right) + 1;
        }
        return Math.min(minDepth3(root.left), minDepth3(root.right)) + 1;
    }

    /**
     * 自底向上「归」
     */
    public int minDepth4(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) { // root 是叶子
            return 1;
        }
        int leftDepth = root.left != null ? minDepth4(root.left) : Integer.MAX_VALUE;
        int rightDepth = root.right != null ? minDepth4(root.right) : Integer.MAX_VALUE;
        return Math.min(leftDepth, rightDepth) + 1;
    }
}
