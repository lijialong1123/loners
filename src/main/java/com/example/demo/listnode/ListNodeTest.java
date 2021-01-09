package com.example.demo.listnode;

import java.util.*;

/**
 * @author : coder
 * @create 2020/10/28 15:55
 */
public class ListNodeTest {

    /**
     * 删除中间结点
     */
    public void deleteNode1(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 删除重复结点(利用额外空间)
     */
    public static ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Set<Integer> set = new HashSet<>();
        ListNode p = head;
        set.add(p.val);
        while (p.next != null) {
            if (set.contains(p.next.val)) {
                p.next = p.next.next;
            } else {
                set.add(p.next.val);
                p = p.next;
            }
        }
        return head;
    }

    /**
     * 删除重复结点(不利用额外空间，时间换空间)
     */
    public static ListNode removeDuplicateNodes1(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    /**
     * 反转链表(使用栈)
     */
    private static ListNode reverseList(ListNode head) {

        if (head == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        while (head.next != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode l2 = head;

        while (!stack.isEmpty()) {
            l2.next = stack.pop();
            l2 = l2.next;
        }
        l2.next = null;
        return head;
    }

    /**
     * 反转链表(使用双链表)
     */
    private static ListNode reverseList1(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }

    /**
     * 删除结点
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = deleteNode(head.next, val);
        return head.val == val ? head.next : head;
    }

    /**
     * 删除结点(双链表)
     */
    public ListNode deleteNode1(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return dummyHead.next;

    }

    /**
     * 合并两个排序的链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dum = new ListNode(0);
        ListNode cur = dum;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dum.next;
    }

    /**
     * 两个链表相交
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //temp1和temp2我们可以认为是A,B两个指针
        ListNode temp1 = headA;
        ListNode temp2 = headB;
        while (temp1 != temp2) {
            //如果指针temp1不为空，temp1就往后移一步。
            //如果指针temp1为空，就让指针temp1指向headB（注意这里是headB不是tempB）
            temp1 = headA == null ? headB : headA.next;
            temp2 = headB == null ? headA : headB.next;
        }
        //temp1要么是空，要么是两链表的交点
        return temp1;
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    /**
     * 回文链表
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        List<Integer> result = new ArrayList<>();
        while (head != null) {
            result.add(head.val);
            head = head.next;
        }
        int front = 0, back = result.size() - 1;
        while (front < back) {
            if (!result.get(front).equals(result.get(back))) {
                return false;
            }
            back++;
            front--;
        }
        return true;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = null;
        ListNode node = removeDuplicateNodes1(head);

    }


}
