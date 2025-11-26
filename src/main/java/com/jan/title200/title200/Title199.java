package com.jan.title200.title200;

import com.jan.share.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 199. 二叉树的右视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 示例 1：
 * 输入：root = [1,2,3,null,5,null,4]
 * 输出：[1,3,4]
 * 解释：
 *
 * 示例 2：
 * 输入：root = [1,2,3,4,null,null,null,5]
 * 输出：[1,3,4,5]
 * 解释：
 *
 * 示例 3：
 * 输入：root = [1,null,3]
 * 输出：[1,3]
 * 示例 4：
 * 输入：root = []
 * 输出：[]
 *
 * 提示:
 * 二叉树的节点个数的范围是 [0,100]
 * -100 <= Node.val <= 100
 */
public class Title199 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode p121 = new TreeNode(2);
        TreeNode p122 = new TreeNode(3);
        TreeNode p131 = new TreeNode(5);
        TreeNode p132 = new TreeNode(4);
        root1.left = p121;
        root1.right = p122;
        p121.right = p131;
        p122.right = p132;

        TreeNode root2 = new TreeNode(1);
        TreeNode p221 = new TreeNode(2);
        TreeNode p222 = new TreeNode(3);
        TreeNode p231 = new TreeNode(4);
        TreeNode p241 = new TreeNode(5);
        root2.left = p221;
        root2.right = p222;
        p221.left = p231;
        p231.left = p241;

        TreeNode root3 = new TreeNode(1);
        TreeNode p321 = new TreeNode(3);
        root3.right = p321;

        Title199 title199 = new Title199();
        System.out.println(title199.rightSideView(root1));
        System.out.println(title199.rightSideView(root2));
        System.out.println(title199.rightSideView(root3));
        System.out.println(title199.rightSideView(null));

        System.out.println(title199.rightSideView2(root1));
        System.out.println(title199.rightSideView2(root2));
        System.out.println(title199.rightSideView2(root3));
        System.out.println(title199.rightSideView2(null));
    }

    /**
     * 层次遍历获取各层最后一个元素
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while(!deque.isEmpty()) {
            int levelSize = deque.size();
            for(int i = 0; i < levelSize; i++) {
                TreeNode node = deque.poll();
                if (node.left != null) deque.offer(node.left);
                if (node.right != null) deque.offer(node.right);
                if(i == levelSize -1) ans.add(node.val);
            }
        }

        return ans;
    }

    /**
     * 先递归右子树，再递归左子树，当某个深度首次到达时，对应的节点就在右视图中。
     */
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, 0, ans);
        return ans;
    }

    private void dfs(TreeNode treeNode, int depth, List<Integer> ans) {
        if(treeNode == null) return;
        if(depth == ans.size()) ans.add(treeNode.val);
        dfs(treeNode.right, depth + 1, ans);
        dfs(treeNode.left, depth + 1, ans);
    }
}
