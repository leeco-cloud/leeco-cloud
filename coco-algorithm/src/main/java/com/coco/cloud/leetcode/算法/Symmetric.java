package com.coco.cloud.leetcode.算法;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 * 这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/5/31 19:51
 */
public class Symmetric {

    public boolean isSymmetric(TreeNode root) {
        // 先判断根节点的左右子节点是否对称
        if (root == null){
            return true;
        }
        return check(root.left,root.right);
    }

    private boolean check(TreeNode leftNode,TreeNode rightNode){
        if (leftNode == null && rightNode == null){
            return true;
        }
        if (leftNode == null && rightNode != null){
            return false;
        }
        if (leftNode != null && rightNode == null){
            return false;
        }
        if (leftNode.val == rightNode.val){
            if (check(leftNode.left,rightNode.right) && check(leftNode.right,rightNode.left)){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode root22 = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        TreeNode root4 = new TreeNode(4);
        root.left = root2;
        root.right = root22;
        root2.left = root3;
        root2.right = root4;
        root22.left = root4;
        root22.right = root3;
        boolean symmetric = new Symmetric().isSymmetric(root);
        System.out.println(symmetric);
    }

}
