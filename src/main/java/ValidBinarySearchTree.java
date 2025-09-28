import dto.TreeNode;

public class ValidBinarySearchTree {

    /**
     * Valid Binary Search Tree
     * Given the root of a binary tree, return true if it is a valid binary search tree, otherwise return false.
     *
     * A valid binary search tree satisfies the following constraints:
     *
     * The left subtree of every node contains only nodes with keys less than the node's key.
     * The right subtree of every node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees are also binary search trees.
     */

    public static void main(String[] args) {

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(0);

        System.out.println(isValidBST(root)); // Output: true
    }

    private static boolean isValidBST(TreeNode root) {
        return checkValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean checkValid(TreeNode node, long left, long right) {
        if (node == null) {
            return true;
        }

        if (!(left <= node.val && node.val <= right)) {
            return false;
        }

        return checkValid(node.left, left, node.val) && checkValid(node.right, node.val, right);
    }
}
