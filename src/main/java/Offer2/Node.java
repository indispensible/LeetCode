package Offer2;


import java.util.List;

/**
 * @author lvgang
 * @date 2020/12/16 9:47
 */
public class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }

    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummy = new ListNode(0, head), dummyPrev = new ListNode(0, head);
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            dummy.next = dummyPrev.next;
            ListNode head1 = head, head2 = head, cur = dummyPrev.next, tmp = dummy, prev = dummyPrev;
            while (cur != null) {
                head1 = cur;
                for (int i = 0; i < subLength && cur != null; i++) {
                    tmp = tmp.next;
                    cur = cur.next;
                }
                tmp.next = null;
                dummy.next = cur;
                tmp = dummy;
                head2 = cur;
                for (int i = 0; i < subLength && cur != null; i++) {
                    tmp = tmp.next;
                    cur = cur.next;
                }
                tmp.next = null;
                prev.next = merge(head1, head2);
                while (prev.next != null) {
                    prev = prev.next;
                }
                dummy.next = cur;
                tmp = dummy;
            }
        }
        return dummyPrev.next;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        if (head2 == null) {
            return head1;
        }
        ListNode dummyHead = new ListNode(0), cur = dummyHead;
        while (head1 != null && head2 != null) {
            if (head1.val > head2.val) {
                cur.next = head2;
                head2 = head2.next;
            } else {
                cur.next = head1;
                head1 = head1.next;
            }
            cur = cur.next;
        }
        cur.next = head1 != null? head1: head2;
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(1, n1);
        ListNode n3 = new ListNode(2, n2);
        ListNode n4 = new ListNode(4, n3);
        Node node = new Node();
        node.sortList(n4);
    }
}
