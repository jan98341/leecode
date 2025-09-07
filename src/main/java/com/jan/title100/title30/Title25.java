package com.jan.title100.title30;

import com.jan.share.ListNode;
import com.jan.share.ListNodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 25、K个一组翻转链表
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 *
 * 示例 2：
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 * 提示：
 * 链表中的节点数目为 n
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 *
 * 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
 */
public class Title25 {
    public static void main(String[] args) {
//        ListNode listNode1 = ListNodeUtils.buildListNode(new Integer[]{1,2,3,4,5,6,7,8,9,10});
//        ListNodeUtils.printList(reverseKGroup(listNode1, 3));
        ListNode listNode2 = ListNodeUtils.buildListNode(new Integer[]{1,2,3,4,5});
        ListNodeUtils.printList(reverseKGroup(listNode2, 1));
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode(0, head);
        ListNode pre = dummyHead;
        List<ListNode> list = getNodes(pre.next, k);
        while (list.size() == k) {
            ListNode next = list.get(k - 1).next;

            pre.next = list.get(k-1);
            for(int i = k - 1; i > 0; i--) {
                list.get(i).next = list.get(i-1);
            }
            list.get(0).next = next;

            pre = list.get(0);
            list = getNodes(pre.next, k);
        }

        return dummyHead.next;
    }

    private static List<ListNode> getNodes(ListNode curr, int k) {
        List<ListNode> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (curr == null) {
                return list;
            }
            list.add(curr);
            curr = curr.next;
        }
        return list;
    }
}
