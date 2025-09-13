import dto.ListNode;

public class MergeTwoSortedLinkedList {

    /**
     * Merge Two Sorted Linked Lists - easy
     * You are given the heads of two sorted linked lists list1 and list2.
     *
     * Merge the two lists into one sorted linked list and return the head of the new sorted linked list.
     *
     * The new list should be made up of nodes from list1 and list2.
     * Input: list1 = [1,2,4], list2 = [1,3,5]
     *
     * Output: [1,1,2,3,4,5]
     * Example 2:
     *
     * Input: list1 = [], list2 = [1,2]
     *
     * Output: [1,2]
     * Example 3:
     *
     * Input: list1 = [], list2 = []
     *
     * Output: []
     * Constraints:
     *
     * 0 <= The length of the each list <= 100.
     * -100 <= Node.val <= 100
     */

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(4);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(5);

        ListNode merged = mergeTwoLists(head, head2);
        while (merged != null) {
            System.out.println(merged.val);
            merged = merged.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        ListNode prehead = new ListNode(0); //dummy node to help with edge cases
        ListNode cur = prehead; //the pointer to build the new list

        //this loop is going to run until one of the lists is exhausted
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }

            cur = cur.next;
        }

        //at this point at least one of the lists is exhausted
        //we simply point the next of the current node to the non-exhausted list
        cur.next = (list1 == null) ? list2 : list1;

        return prehead.next;
    }
}
