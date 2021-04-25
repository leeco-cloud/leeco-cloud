package com.coco.cloud.leetcode.动态规划;

import com.coco.cloud.leetcode.算法.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，
 * 使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/25 19:42
 */
public class 递增顺序搜索树 {

    public static TreeNode increasingBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        sort(list,root);
        TreeNode node = new TreeNode(-1);
        TreeNode temp = node;
        for (Integer integer : list) {
            temp.right = new TreeNode(integer);
            temp = temp.right;
        }
        return node.right;
    }

    public static void sort(List<Integer> list,TreeNode root){
        if (root.left != null){
            sort(list,root.left);
        }
        list.add(root.val);
        if (root.right != null){
            sort(list,root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root1  = new TreeNode(1);
        TreeNode root2  = new TreeNode(2);
        TreeNode root3  = new TreeNode(3);
        TreeNode root4  = new TreeNode(4);
        TreeNode root5  = new TreeNode(5);
        TreeNode root6  = new TreeNode(6);
        TreeNode root7  = new TreeNode(7);
        TreeNode root8  = new TreeNode(8);
        TreeNode root9  = new TreeNode(9);

        root5.left = root3;
        root5.right = root6;
        root3.left = root2;
        root3.right = root4;
        root2.left = root1;

        root6.right = root8;

        root8.left = root7;
        root8.right = root9;

        increasingBST(root5);
    }

}
