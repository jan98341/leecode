package com.jan.free.btree;

import com.alibaba.fastjson2.JSON;
import com.jan.share.TreeNode;

import java.util.*;

public class BTreeTraversal2 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node21 = new TreeNode(2);
        TreeNode node22 = new TreeNode(3);
        TreeNode node31 = new TreeNode(4);
        TreeNode node32 = new TreeNode(5);
        TreeNode node33 = new TreeNode(6);
        TreeNode node34 = new TreeNode(7);
        root.left = node21;
        root.right = node22;
        node21.left = node31;
        node21.right = node32;
        node22.left = node33;
        node22.right = node34;

        BTreeTraversal2 bTreeTraversal = new BTreeTraversal2();
        System.out.println(JSON.toJSONString(bTreeTraversal.inOrderTraversal(root)));
        System.out.println(JSON.toJSONString(bTreeTraversal.preOrderTraversal(root)));
        System.out.println(JSON.toJSONString(bTreeTraversal.postOrderTraversal(root)));
        System.out.println(JSON.toJSONString(bTreeTraversal.levelOrder(root)));
    }

    /**
     * 中序遍历，非迭代实现  https://juejin.cn/post/7529721219961585679
     * 中序遍历：左 -> 根 -> 右
     */
    private List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // 先将左子树全部入栈
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();  // 弹出最左节点
            ans.add(cur.val);   // 访问根节点
            cur = cur.right;    // 转向右子树
        }
        return ans;
    }

    /**
     * 前序遍历，非迭代实现 https://juejin.cn/post/7529721219961585679
     * 前序遍历：根 -> 左 -> 右
     */
    private List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(node.val); // 先访问根节点
            if(node.right != null) stack.push(node.right);  // 右子树先入栈（后访问）
            if(node.left != null) stack.push(node.left);    // 左子树后入栈（先访问）
        }
        return ans;
    }

    /**
     * 后序遍历，非迭代实现 https://juejin.cn/post/7529721219961585679
     * 后序遍历：左 -> 右 -> 根
     */
    private List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(0, node.val);   // 逆序添加（根 -> 右 -> 左）
            if(node.left != null) stack.push(node.left);    // 左子树先入栈
            if(node.right != null) stack.push(node.right);  // 右子树后入栈
        }
        return ans;
    }

    /**
     * 按层遍历，非迭代实现 https://juejin.cn/post/7529721219961585679
     * 按层从上到下，从左到右
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);    // 按顺序访问当前层节点
                if(node.left != null) queue.offer(node.left);   // 左子节点入队
                if(node.right != null) queue.offer(node.right); // 右子节点入队
            }
            ans.add(level);
        }
        return ans;
    }
}
