package com.coco.cloud.leetcode.算法;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 二叉树的层级遍历 :  BFS + LEVEL
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/5/13 22:22
 */
public class Solution {

    /**
     * 深度
     */
    private Integer k = 0;

    /**
     * 邻接顶点队列
     */
    private Queue<Map<Integer,TreeNode>> queue = new LinkedBlockingDeque<>();

    private List<Map<Integer,TreeNode>> list = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root != null){
            Map<Integer,TreeNode> node = new HashMap<>(1);
            node.put(k,root);
            queue.add(node);
        }
        while(!queue.isEmpty()){
            Map<Integer, TreeNode> poll = queue.poll();
            list.add(poll);
            AtomicReference<TreeNode> treeNode = new AtomicReference<>();
            poll.forEach((key,v) -> {
                k = key;
                treeNode.set(v);
            });
            k++;
            if (treeNode.get().left != null){
                Map<Integer,TreeNode> node = new HashMap<>(1);
                node.put(k, treeNode.get().left);
                queue.add(node);
            }
            if (treeNode.get().right != null){
                Map<Integer,TreeNode> node = new HashMap<>(1);
                node.put(k, treeNode.get().right);
                queue.add(node);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            List<Integer> data = new ArrayList<>();
            for (Map<Integer, TreeNode> integerTreeNodeMap : list) {
                if (integerTreeNodeMap.get(i) != null){
                    TreeNode treeNode = integerTreeNodeMap.get(i);
                    data.add(treeNode.val);
                }
            }
            if (!data.isEmpty()){
                result.add(data);
            }
        }
        return result;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
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
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.levelOrder(treeNode1);
        System.out.println(lists.toString());
    }

}