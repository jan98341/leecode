package com.jan.title1500.title1450;

import com.jan.share.TreeNode;

/**
 * 1448. 统计二叉树中好节点的数目
 * 给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
 * 「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。
 *
 * 示例 1：
 * 输入：root = [3,1,4,3,null,1,5]
 * 输出：4
 * 解释：图中蓝色节点为好节点。
 * 根节点 (3) 永远是个好节点。
 * 节点 4 -> (3,4) 是路径中的最大值。
 * 节点 5 -> (3,4,5) 是路径中的最大值。
 * 节点 3 -> (3,1,3) 是路径中的最大值。
 *
 * 示例 2：
 * 输入：root = [3,3,null,4,2]
 * 输出：3
 * 解释：节点 2 -> (3, 3, 2) 不是好节点，因为 "3" 比它大。
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：1
 * 解释：根节点是好节点。
 *
 * 提示：
 * 二叉树中节点数目范围是 [1, 10^5] 。
 * 每个节点权值的范围是 [-10^4, 10^4] 。
 */
public class Title1448 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        TreeNode node121 = new TreeNode(1);
        TreeNode node122 = new TreeNode(4);
        TreeNode node131 = new TreeNode(3);
        TreeNode node132 = new TreeNode(1);
        TreeNode node133 = new TreeNode(5);
        root1.left = node121;
        root1.right = node122;
        node121.left = node131;
        node122.left = node132;
        node122.right = node133;

        TreeNode root2 = new TreeNode(3);
        TreeNode node221 = new TreeNode(3);
        TreeNode node231 = new TreeNode(4);
        TreeNode node232 = new TreeNode(2);
        root2.left = node221;
        node221.left = node231;
        node221.right = node232;

        TreeNode root3 = new TreeNode(1);

        Title1448 title1448 = new Title1448();
        System.out.println(title1448.goodNodes(root1));
        System.out.println(title1448.goodNodes(root2));
        System.out.println(title1448.goodNodes(root3));

        System.out.println(title1448.goodNodes2(root1));
        System.out.println(title1448.goodNodes2(root2));
        System.out.println(title1448.goodNodes2(root3));
    }

    public int goodNodes(TreeNode root) {
        ans = 0;
        dfs(root, root.val);
        return ans;
    }

    private int ans;
    private void dfs(TreeNode node, int maxValue) {
        if (node == null) return;

        if(node.val >= maxValue) {
            ans++;
            maxValue = node.val;
        }
        dfs(node.left, maxValue);
        dfs(node.right, maxValue);
    }

    public int goodNodes2(TreeNode root) {
        return dfs2(root, root.val);
    }

    private int dfs2(TreeNode node, int maxValue) {
        if (node == null) return 0;
        int left = dfs2(node.left, Math.max(maxValue, node.val));
        int right = dfs2(node.right, Math.max(maxValue, node.val));
        // 如果当前节点是好节点，即 mx <= root.val，那么返回 left+right+1，否则返回 left+right
        return left + right + (node.val >= maxValue ? 1 : 0);
    }
}
