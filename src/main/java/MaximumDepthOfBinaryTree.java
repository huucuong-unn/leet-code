import dto.TreeNode;

public class MaximumDepthOfBinaryTree {

    /**
     * Maximum Depth of Binary Tree - Easy
     *
     * Given the root of a binary tree, return its maximum depth.
     * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     *
     */

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        MaximumDepthOfBinaryTree solution = new MaximumDepthOfBinaryTree();
        int depth = solution.maxDepth(root);
        System.out.println("Maximum Depth: " + depth); // Output: 3
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
