package com.jan.title1000.title970;

import com.jan.share.TreeNode;

/**
 * 968. 监控二叉树
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 * 计算监控树的所有节点所需的最小摄像头数量。
 *
 * 示例 1：
 * 输入：[0,0,null,0,0]
 * 输出：1
 * 解释：如图所示，一台摄像头足以监控所有节点。
 *
 * 示例 2：
 * 输入：[0,0,null,0,null,0,null,null,0]
 * 输出：2
 * 解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
 *
 * 提示：
 * 给定树的节点数的范围是 [1, 1000]。
 * 每个节点的值都是 0。
 */
public class Title968 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(0);
        TreeNode node12 = new TreeNode(0);
        TreeNode node131 = new TreeNode(0);
        TreeNode node132 = new TreeNode(0);
        root1.left = node12;
        node12.left = node131; node12.right = node132;

        TreeNode root2 = new TreeNode(0);
        TreeNode node22 = new TreeNode(0);
        TreeNode node23 = new TreeNode(0);
        TreeNode node24 = new TreeNode(0);
        TreeNode node25 = new TreeNode(0);
        root2.left = node22; node22.left = node23;
        node23.left = node24; node24.right = node25;

        Title968 title968 = new Title968();
        System.out.println(title968.minCameraCover(root1));
        System.out.println(title968.minCameraCover(root2));
    }

    public int minCameraCover(TreeNode root) {
        int[] res = dfs(root);
        return Math.min(res[0], res[2]);
    }

    /**
     * 三种状态
     * 1、choose：自己本身装摄像头，肯定能监控到父节点和孩子节点，所以孩子节点装不装都可以。
     *   这时候的最少摄像头为：min(l_choose, l_by_fa) + min(r_choose, r_by_fa) + 1
     * 2、by_fa：自己的父节点装摄像头，自己可以被监控到，孩子节点顾好自己 就行，所以孩子节点可以自己装，也可以不装被下面的节点监控到就行。
     *   这时候的最少摄像头为：min(l_choose, l_by_children) + min(r_choose, r_by_children)
     * 3、by_children：自己的孩子装摄像头，那么自己装不装都可以，那么其中一个孩子一定要装，所以情况是：
     *   （1）左孩子装，右孩子不装但能被监控到（l_choose+r_by_children)；
     *   （2）右孩子装，左孩子不装但能被监控到（r_choose+l_by_children)；
     *   （3）孩子都装：l_choose+r_choose。这时候的最少摄像头为：min(l_choose+r_by_children, r_choose+l_by_children, l_choose+r_choose)
     */
    private int[] dfs(TreeNode node) {
        if(node == null) {
            return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int choose = Math.min(left[0], left[1]) + Math.min(right[0], right[1]) + 1;
        int byFa = Math.min(left[0], left[2]) + Math.min(right[0], right[2]);
        int byChildren = Math.min(Math.min(left[0] + right[2], left[2] + right[0]), left[0] + right[0]);
        return new int[]{choose, byFa, byChildren};
    }
}
