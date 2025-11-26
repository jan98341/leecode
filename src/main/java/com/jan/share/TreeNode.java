package com.jan.share;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public void print(TreeNode root) {
        if(root == null) {
            System.out.println("结果为空");
        };

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        System.out.print("结果为:");
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + ",");
                if(node.left != null) queue.offer(node.left);   // 左子节点入队
                if(node.right != null) queue.offer(node.right); // 右子节点入队
            }
        }
        System.out.println();
    }
}
