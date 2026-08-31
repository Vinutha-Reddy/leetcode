/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int first = -1;
        int prev = -1;
        int minDist = Integer.MAX_VALUE;
        int maxDist = -1;

        int index = 1; // current node index (1-based for middle node)

        ListNode prevNode = head;
        ListNode curr = head.next;

        while (curr != null && curr.next != null) {
            int a = prevNode.val;
            int b = curr.val;
            int c = curr.next.val;

            boolean isCritical = (b > a && b > c) || (b < a && b < c);

            if (isCritical) {
                if (first == -1) {
                    first = index;
                } else {
                    minDist = Math.min(minDist, index - prev);
                    maxDist = Math.max(maxDist, index - first);
                }
                prev = index;
            }

            prevNode = curr;
            curr = curr.next;
            index++;
        }

        if (minDist == Integer.MAX_VALUE) {
            return new int[]{-1, -1};
        }

        return new int[]{minDist, maxDist};
    }
}