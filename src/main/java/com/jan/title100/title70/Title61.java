package com.jan.title100.title70;

import com.jan.share.ListNode;
import com.jan.share.ListNodeUtils;

import static com.jan.share.ListNodeUtils.printList;

/**
 * 61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 *
 * 示例 2：
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 *
 * 提示：
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 10^9
 */
public class Title61 {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5};
        ListNode head = ListNodeUtils.buildListNode(arr);
        printList(head);
        printList(rotateRight(head, 2));

        Integer[] arr2 = {0,1,2};
        ListNode head2 = ListNodeUtils.buildListNode(arr2);
        printList(head2);
        printList(rotateRight(head2, 304));
    }

    public static ListNode rotateRight(ListNode head, int k) {
        // 当链表为空或者只有一个节点返回head
        if (head == null || head.next == null) {
            return head;
        }

        // 先获取链表长度
        int length = 1;
        ListNode cur = head;
        while(cur.next != null) {
            cur = cur.next;
            length++;
        }

        // 对偏移量按长度求余数，如果余数为0，则不需要移动
        int nk = k % length;
        if (nk == 0) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode flagNode = dummy;
        ListNode cur1 = dummy;
        int count = 1;
        while (cur1.next != null) {
            // 在计数n+1后记录删除前节点，todo 其实在这题里面不需要，因为已经知道
            if(count > nk) {
                flagNode = flagNode.next;
            }

            cur1 = cur1.next;
            count++;
        }

        cur1.next = dummy.next;
        head = flagNode.next;
        flagNode.next = null;

        return head;
    }
}
