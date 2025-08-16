package com.jan.title100.title1_10;

public class Title2 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        ListNode newNode11 = new ListNode(9);
        ListNode newNode12 = new ListNode(9);
        ListNode newNode13 = new ListNode(9);
        ListNode newNode14 = new ListNode(9);
        ListNode newNode15 = new ListNode(9);
        l1.next = newNode11;
        newNode11.next = newNode12;
        newNode12.next = newNode13;
        newNode13.next = newNode14;
        newNode14.next = newNode15;

        ListNode l2 = new ListNode(9);
        ListNode newNode21 = new ListNode(9);
        ListNode newNode22 = new ListNode(9);
        ListNode newNode23 = new ListNode(9);
        l2.next = newNode21;
        newNode21.next = newNode22;
        newNode22.next = newNode23;

        Title2 title2 = new Title2();
        ListNode resultNode = title2.addTwoNumbers(l1, l2);
        title2.printListNode(resultNode);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        int carry = 0;

        while (l1 != null || l2 != null || carry > 0) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            ListNode newNode = new ListNode(sum % 10);
            carry = sum / 10;

            if(head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = tail.next;
            }

            if(l1 != null && l1.next != null) {
                l1 = l1.next;
            } else {
                l1 = null;
            }
            if(l2 != null && l2.next != null) {
                l2 = l2.next;
            } else {
                l2 = null;
            }
        }

        return head;
    }

    public void printListNode(ListNode listNode) {
        ListNode cur = listNode;
        while (cur != null) {
            System.out.print(cur.val);
            if(cur.next != null) {
                System.out.print("->");
            }
            cur = (cur.next == null) ?  null : cur.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}

