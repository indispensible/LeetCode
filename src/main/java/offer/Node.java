package offer;

import java.util.Arrays;

/**
 * @author lvgang
 * @date 2020/12/8 10:49
 */
public class Node {
    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            head = head.next;
        } else {
            ListNode preNode = head;
            ListNode deleteNode = head.next;
            while (deleteNode != null && deleteNode.val != val) {
                preNode = deleteNode;
                deleteNode = deleteNode.next;
            }
            if (deleteNode != null) {
                preNode.next = deleteNode.next;
            }
        }
        return head;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode latter = head;
        ListNode former = head;
        int t = 0;
        while (former != null) {
            if (t == k) {
                former = former.next;
                latter = latter.next;
            } else {
                former = former.next;
                t++;
            }
        }
        return latter;
    }

    public ListNode reverseList(ListNode head) {
        ListNode latter, former = head, tmp = null;
        if (head != null && head.next != null) {
            tmp = head.next;
        }
        former.next = null;
        while (tmp != null) {
            latter = former;
            former = tmp;
            tmp = tmp.next;
            former.next = latter;
        }
        return former;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0), cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null? l1: l2;
        return head.next;
    }
}
