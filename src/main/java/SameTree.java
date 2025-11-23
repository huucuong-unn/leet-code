import dto.TreeNode;

public class SameTree {
    public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);

        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
        q.right = new TreeNode(3);

        isSameTree(p, q);
    }

    private static boolean isSameTree(TreeNode p, TreeNode q) {
        TreeNode lp = p, lq = q;
        while (lp != null && lq != null) {
            lp = lp.left;
            lq = lq.left;

            if (lp == null || lq == null)
                return false;
            else {
                if (lp.val != lq.val) {
                    return false;
                }
            }
        }

        if (p.val != q.val) {
            return false;
        }

        TreeNode rp = p, rq = q;
        while (rp != null && rq != null) {
            rp = rp.right;
            rq = rq.right;

            if (rp == null || rq == null)
                return false;
            else {
                if (rp.val != rq.val) {
                    return false;
                }
            }
        }

        return true;
    }
}
