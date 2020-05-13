package com.coco.cloud.leetcode;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 二叉树的层序遍历
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/5/13 22:22
 */
class Solution {

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

}

public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }