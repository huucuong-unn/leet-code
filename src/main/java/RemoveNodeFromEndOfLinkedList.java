import dto.ListNode;

public class RemoveNodeFromEndOfLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int n = 2; // Example: Remove the 2nd node from the end

        ListNode modifiedHead = removeNthFromEnd(head, n);
        while (modifiedHead != null) {
            System.out.println(modifiedHead.val);
            modifiedHead = modifiedHead.next;
        }
    }

    //Two pointer approach
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode left = dummy;
        ListNode right = head;

        //Move right pointer n steps ahead
        while (n > 0) {
            right = right.next;
            n--;
        }

        //Move both pointers until right pointer reaches the end
        //The left pointer will be at the node before the one we want to remove
        while (right != null) {
            left = left.next;
            right = right.next;
        }

        //Remove the nth node from the end
        left.next = left.next.next;

        return dummy.next;
    }
}
