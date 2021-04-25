package com.coco.cloud.leetcode.算法;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * DFS:深度优先算法 : 二叉树的遍历
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/5/13 23:07
 */
public class Dfs {

    /**
     * 节点栈
     */
    private Stack<TreeNode> treeNodes = new Stack<>();

    /**
     * 已经遍历过的节点
     */
    private List<TreeNode> mark = new ArrayList<>();

    private List<Integer> solution(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if (root != null){
            treeNodes.add(root);
            mark.add(root);
        }
        while(!treeNodes.isEmpty()){
            TreeNode pop = treeNodes.peek();
            if (pop.left != null){
                if (!mark.contains(pop.left)){
                    treeNodes.add(pop.left);
                    mark.add(pop.left);
                    continue;
                }
            }
            if (pop.right != null){
                if (!mark.contains(pop.right)){
                    treeNodes.add(pop.right);
                    mark.add(pop.right);
                    continue;
                }
            }
            result.add(treeNodes.pop().val);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode3.right = treeNode5;
        Dfs dfs = new Dfs();
        System.out.println(dfs.solution(treeNode1));
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
