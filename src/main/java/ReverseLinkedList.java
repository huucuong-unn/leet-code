public class ReverseLinkedList {

    /**
     *
     * Reverse Linked List - Easy
     * Given the beginning of a singly linked list head, reverse the list, and return the new beginning of the list.
     *
     * Example 1:
     *
     * Input: head = [0,1,2,3]
     *
     * Output: [3,2,1,0]
     * Example 2:
     *
     * Input: head = []
     *
     * Output: []
     */

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);

        ListNode reversed = reverse(head);

        while (reversed != null) {
            System.out.println(reversed.val);
            reversed = reversed.next;
        }
    }

    public static ListNode reverse(ListNode head) {
        ListNode current = head;
        ListNode prev = null;

        //Idea is to keep track of previous node and current node (using 2 pointers)
        //Then we will point current node's next to previous node
        //Then we will move both pointers one step forward

        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        //return previous node as it will be the new head of the reversed list
        //current will be null at the end of the loop
        return prev;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
