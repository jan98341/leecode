package com.jan.title100.title30;

import com.jan.share.ListNode;
import com.jan.share.ListNodeUtils;

/**
 * 21. 合并两个有序链表
 * https://leetcode.cn/problems/merge-two-sorted-lists/description/
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *
 * 提示：
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 */
public class Title21 {
    public static void main(String[] args) {
        ListNode listNode11 = ListNodeUtils.buildListNode(new Integer[]{1,2,4});
        ListNodeUtils.printList(listNode11);
        ListNode listNode12 = ListNodeUtils.buildListNode(new Integer[]{1,3,4});
        ListNodeUtils.printList(listNode12);
        ListNodeUtils.printList(mergeTwoLists2(listNode11, listNode12));

        ListNode listNode21 = ListNodeUtils.buildListNode(new Integer[]{});
        ListNodeUtils.printList(listNode21);
        ListNode listNode22 = ListNodeUtils.buildListNode(new Integer[]{});
        ListNodeUtils.printList(listNode22);
        ListNodeUtils.printList(mergeTwoLists2(listNode21, listNode22));

        ListNode listNode31 = ListNodeUtils.buildListNode(new Integer[]{});
        ListNodeUtils.printList(listNode31);
        ListNode listNode32 = ListNodeUtils.buildListNode(new Integer[]{0});
        ListNodeUtils.printList(listNode32);
        ListNodeUtils.printList(mergeTwoLists2(listNode31, listNode32));
    }

    public static ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        ListNode head = null;
        ListNode mergeNode = null;
        while (list1 != null && list2 != null) {
            if(list1.val <= list2.val) {
                if(head == null) {
                    head = list1;
                    mergeNode = head;
                } else {
                    head.next = list1;
                    head = head.next;
                }
                list1 = list1.next;
            }else {
                if(head == null) {
                    head = list2;
                    mergeNode = head;
                } else {
                    head.next = list2;
                    head = head.next;
                }
                list2 = list2.next;
            }
        }
        if(list1 != null) {
            if(head == null) {
                mergeNode = list1;
            } else {
                head.next = list1;
            }
        }
        if(list2 != null) {
            if(head == null) {
                mergeNode = list2;
            } else {
                head.next = list2;
            }
        }

        return mergeNode;
    }

    /**
     * 技巧：增加哨兵节点，可以简化代码
     */
    public static ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode mergeNode = dummy;
        while (list1 != null && list2 != null) {
            if(list1.val <= list2.val) {
                mergeNode.next = list1;
                list1 = list1.next;
            } else {
                mergeNode.next = list2;
                list2 = list2.next;
            }
            mergeNode = mergeNode.next;
        }

        mergeNode.next = list1 == null ? list2 : list1;

        return dummy.next;
    }
}
