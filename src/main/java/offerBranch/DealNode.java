package offerBranch;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lvgang
 * @date 2020/12/15 16:29
 */
public class DealNode {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<Node, Node>(16);
        Node cur = head;
        while (cur != null) {
            Node node = new Node(cur.val);
            map.put(cur, node);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.next != null) {
                map.get(cur).next = map.get(cur.next);
            } else {
                map.get(cur).next = null;
            }
            if (cur.random != null) {
                map.get(cur).random = map.get(cur.random);
            } else {
                map.get(cur).random = null;
            }
            cur = cur.next;
        }
        return map.get(head);
    }
}
