package com.jan.title100.title30;

import com.jan.share.ListNode;
import com.jan.share.ListNodeUtils;

import java.util.PriorityQueue;

/**
 * 23、合并K个升序链表
 * https://leetcode.cn/problems/merge-k-sorted-lists/description/
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 *
 *
 * 提示：
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 */
public class Title23 {
    public static void main(String[] args) {
//        ListNode listNode11 = ListNodeUtils.buildListNode(new Integer[]{1,4,5});
//        ListNode listNode12 = ListNodeUtils.buildListNode(new Integer[]{1,3,4});
//        ListNode listNode13 = ListNodeUtils.buildListNode(new Integer[]{2,6});
//        ListNodeUtils.printList(listNode11);
//        ListNodeUtils.printList(listNode12);
//        ListNodeUtils.printList(listNode13);
//        ListNode[] lists1 = new ListNode[]{listNode11, listNode12, listNode13};
//        mergeKLists(lists1);

//        ListNode[] lists2 = new ListNode[]{};
//        mergeKLists(lists2);

//        ListNode listNode31 = ListNodeUtils.buildListNode(new Integer[]{});
//        ListNode[] lists3 = new ListNode[]{listNode31};
//        mergeKLists(lists3);

        ListNode listNode41 = ListNodeUtils.buildListNode(new Integer[]{});
        ListNode listNode42 = ListNodeUtils.buildListNode(new Integer[]{-1,5,11});
        ListNode listNode43 = ListNodeUtils.buildListNode(new Integer[]{});
        ListNode listNode44 = ListNodeUtils.buildListNode(new Integer[]{6,10});
        ListNodeUtils.printList(listNode41);
        ListNodeUtils.printList(listNode42);
        ListNodeUtils.printList(listNode43);
        ListNodeUtils.printList(listNode44);
        ListNode[] lists4 = new ListNode[]{listNode41, listNode42, listNode43,listNode44};
        ListNodeUtils.printList(mergeKLists3(lists4));
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        // 加入哨兵节点可简化代码
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        int nullCount = 0;

        // 遍历所有联调只到所有节点添加完毕为止
        while (lists != null && nullCount < lists.length) {
            nullCount = 0;
            Integer minVal = Integer.MAX_VALUE;
            int minIndex = 0;

            // 遍历所有链表，记录最小节点联调序号
            for(int i = 0; i < lists.length; i++) {
                if(lists[i] == null) {
                    nullCount++;
                }
                if (lists[i] != null && lists[i].val < minVal) {
                    minVal = lists[i].val;
                    minIndex = i;
                }
            }

            // 把最小节点添加到合并联调中
            cur.next = lists[minIndex];
            cur = cur.next;
            if(lists[minIndex] != null) lists[minIndex] = lists[minIndex].next;
        }

        return dummy.next;
    }


    /**
     * 用最小堆实现。初始把所有链表的头节点入堆，然后不断弹出堆中最小节点 x，如果 x.next 不为空就加入堆中。
     * 循环直到堆为空。把弹出的节点按顺序拼接起来，就得到了答案
     */
    public static ListNode mergeKLists2(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b)->a.val-b.val);
        // 把所有非空链表的头节点入堆
        for (ListNode head : lists) {
            if(head != null) {
                pq.add(head);
            }
        }

        // 加入哨兵节点可简化代码
        ListNode dummy = new ListNode();
        ListNode cur = dummy;

        // 循环直到堆为空
        while(!pq.isEmpty()) {
            // 剩余节点中的最小节点
            ListNode node = pq.poll();
            if(node.next != null) {
                pq.add(node.next);
            }
            cur.next = node;
            cur = cur.next;
        }

        return dummy.next;
    }

    public static ListNode mergeKLists3(ListNode[] lists) {
        return mergeKLists(lists, 0, lists.length);
    }
    // 合并从 lists[i] 到 lists[j-1] 的链表
    private static ListNode mergeKLists(ListNode[] lists, int i, int j) {
        int m = j - i;
        if (m == 0) {
            return null; // 注意输入的 lists 可能是空的
        }
        if (m == 1) {
            return lists[i]; // 无需合并，直接返回
        }
        ListNode left = mergeKLists(lists, i, i + m / 2); // 合并左半部分
        ListNode right = mergeKLists(lists, i + m / 2, j); // 合并右半部分
        return mergeTwoLists(left, right); // 最后把左半和右半合并
    }

    // 21. 合并两个有序链表
    private static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(); // 用哨兵节点简化代码逻辑
        ListNode cur = dummy; // cur 指向新链表的末尾
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1; // 把 list1 加到新链表中
                list1 = list1.next;
            } else { // 注：相等的情况加哪个节点都是可以的
                cur.next = list2; // 把 list2 加到新链表中
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 != null ? list1 : list2; // 拼接剩余链表
        return dummy.next;
    }
}
