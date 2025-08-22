package com.jan.share;

public class ListNodeUtils {

    public static ListNode buildListNode(Integer[] arr) {
        ListNode head = null;
        ListNode current = null;
        for (int i = 0; i < arr.length; i++) {
            if (head == null) {
                head = new ListNode(arr[i]);
                current = head;
                continue;
            }

            ListNode node = new ListNode(arr[i]);
            current.next = node;
            current = node;
        }

        return head;
    }

    // 打印链表的方法
    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }
}
