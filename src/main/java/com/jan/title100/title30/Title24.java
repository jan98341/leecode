package com.jan.title100.title30;

import com.jan.share.ListNode;
import com.jan.share.ListNodeUtils;

/**
 * 24. 两两交换链表中的节点
 * https://leetcode.cn/problems/swap-nodes-in-pairs/
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 *
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 *
 * 提示：
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 */
public class Title24 {
    public static void main(String[] args) {
        ListNode listNode1 = ListNodeUtils.buildListNode(new Integer[]{1,2,3,4});
        ListNodeUtils.printList(swapPairs(listNode1));
        ListNode listNode2 = ListNodeUtils.buildListNode(new Integer[]{1});
        ListNodeUtils.printList(swapPairs(listNode2));
        ListNode listNode3 = ListNodeUtils.buildListNode(new Integer[]{2,3,4,5,6,7,8,9,10});
        ListNodeUtils.printList(swapPairs(listNode3));
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        while (pre.next != null && pre.next.next != null) {
            ListNode first = pre.next;
            ListNode second = pre.next.next;
            ListNode third = pre.next.next.next;

            pre.next = second;
            second.next = first;
            first.next = third;

            pre = first;
        }

        return dummy.next;
    }

    public static ListNode swapPairs2(ListNode head) {
        ListNode dummy = new ListNode(0, head); // 用哨兵节点简化代码逻辑
        ListNode node0 = dummy;
        ListNode node1 = head;
        while (node1 != null && node1.next != null) { // 至少有两个节点
            ListNode node2 = node1.next;
            ListNode node3 = node2.next;

            node0.next = node2; // 0 -> 2
            node2.next = node1; // 2 -> 1
            node1.next = node3; // 1 -> 3

            node0 = node1; // 下一轮交换，0 是 1
            node1 = node3; // 下一轮交换，1 是 3
        }
        return dummy.next; // 返回新链表的头节点
    }
}
