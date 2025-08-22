package com.jan.title100.title20;

import com.jan.share.ListNode;
import com.jan.share.ListNodeUtils;

import static com.jan.share.ListNodeUtils.printList;

/**
 * 19. 删除链表的倒数第 N 个结点
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Title19 {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5};
        ListNode head = ListNodeUtils.buildListNode(arr);
        printList(head);
        head = removeNthFromEnd(head, 2);
        printList(head);

        Integer[] arr2 = {1};
        ListNode head2 = ListNodeUtils.buildListNode(arr2);
        printList(head2);
        head2 = removeNthFromEnd(head2, 1);
        printList(head2);

        Integer[] arr3 = {1,2};
        ListNode head3 = ListNodeUtils.buildListNode(arr3);
        printList(head3);
        head3 = removeNthFromEnd(head3, 1);
        printList(head3);

        Integer[] arr4 = {1,2};
        ListNode head4 = ListNodeUtils.buildListNode(arr4);
        printList(head4);
        head4 = removeNthFromEnd(head4, 2);
        printList(head4);
    }

    /**
     * 想象有一把长度固定的尺子，左端点在链表头部，右端点在正数第 n 个节点。向右移动尺子，当尺子右端点到达链表末尾时，左端点就在倒数第 n 个节点。
     * 由于需要删除节点，我们需要找倒数第 n 个节点的前一个节点（倒数第 n+1 个节点），这样才能做删除操作。
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // 技巧：如果遇到需要删除头节点的题目，添加哨兵节点可以简化代码逻辑
        ListNode dummy = new ListNode(0, head);
        ListNode preDel = dummy;
        ListNode cur = dummy;

        // 删除倒数第n节点，需要知道倒数第n+1节点的位置
        int count = 1;
        while (cur.next != null) {
            // 在计数n+1后记录删除前节点
            if(count > n) {
                preDel = preDel.next;
            }

            cur = cur.next;
            count++;
        }

        // 删除倒数第n个节点
        preDel.next = preDel.next.next;
        return dummy.next;
    }
}
