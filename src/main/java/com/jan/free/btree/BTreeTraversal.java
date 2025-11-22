package com.jan.free.btree;

import com.alibaba.fastjson2.JSON;
import com.jan.share.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BTreeTraversal {

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

        BTreeTraversal bTreeTraversal = new BTreeTraversal();
        System.out.println(JSON.toJSONString(bTreeTraversal.inOrderTraversal(root)));
        System.out.println(JSON.toJSONString(bTreeTraversal.preOrderTraversal(root)));
        System.out.println(JSON.toJSONString(bTreeTraversal.postOrderTraversal(root)));
    }

    /**
     * 中序遍历，非迭代实现  https://www.cnblogs.com/chiaki/p/13543524.html
     * 1、从当前节点一直向其最左孩子搜索，只到没有孩子为止，这个过程中将路程中的所有节点入栈
     * 2、弹出栈顶元素，将结果记录在答案中，并把当前置为弹出节点右节点并重复第一步
     */
    private List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if(root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.pop();
                ans.add(node.val);
                root = node.right;
            }
        }
        return ans;
    }

    /**
     * 前序遍历，非迭代实现
     * 1、如果栈不为空，则弹出栈顶元素存入结果中
     * 2、如果弹出元素的右孩子不为空则右孩子入栈，如果其左孩子不为空则也将左孩子入栈
     */
    private List<Integer> preOrderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            ans.add(root.val);
            if(root.right != null) stack.push(root.right);
            if(root.left != null) stack.push(root.left);
        }

        return ans;
    }

    /**
     * 后序遍历，非迭代实现
     * 因为前序遍历的顺序是“左-中-右”，而后序遍历是“左-右-中”，不考虑左节点区别在于中节点和右节点访问顺序相反而已，
     * 因此可以利用前面前序遍历的代码进行调整，只需要将前序遍历对左右孩子压栈的顺序相反即可。
     * 除此之外，按照这种方法调整的遍历顺序为“中-右-左”，正好是后序遍历的反向顺序，因此获取遍历序列后还需要进行逆操作
     */
    private List<Integer> postOrderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            ans.add(0, root.val);
            if(root.left != null) stack.push(root.left);
            if(root.right != null) stack.push(root.right);
        }

        return ans;
    }

    /**
     * https://juejin.cn/post/7529721219961585679
     */
}
