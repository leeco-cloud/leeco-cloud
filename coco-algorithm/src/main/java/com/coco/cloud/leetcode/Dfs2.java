package com.coco.cloud.leetcode;

import java.util.*;

/**
 * DFS:深度优先算法 : 有向图的遍历
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/5/13 23:07
 */
public class Dfs2 {

    /**
     * 节点栈
     */
    private Stack<TreeNode> treeNodes = new Stack<>();

    /**
     * 已经遍历过的节点
     */
    private List<Integer> mark = new ArrayList<>();

    /**
     * 正在遍历的节点
     */
    private List<Integer> marking = new ArrayList<>();

    /**
     * 遍历结果
     */
    private List<Integer> result = new ArrayList<>();

    private List<Integer> solution(TreeNode root){
        if (root != null){
            treeNodes.add(root);
            marking.add(root.id);
        }
        while(!treeNodes.isEmpty()){
            dfs(treeNodes.peek());
        }
        System.out.println(mark.toString());
        System.out.println(marking.toString());
        return result;
    }

    private void dfs(TreeNode node){
        if (node.child != null){
            List<TreeNode> child = node.child;
            for (TreeNode treeNode : child) {
                if (marking.contains(treeNode.id)){
                    System.out.println("成环");
                    throw new RuntimeException();
                }
                if (!mark.contains(treeNode.id)){
                    treeNodes.add(treeNode);
                    marking.add(treeNode.id);
                    dfs(treeNode);
                }
            }
        }
        if (marking.remove((Object)node.id)){
            mark.add(node.id);
        }
        result.add(treeNodes.pop().id);
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        List<TreeNode> child = new ArrayList<>();
        child.add(treeNode2);
        child.add(treeNode3);
        treeNode1.child = child;
        List<TreeNode> child2 = new ArrayList<>();
        child2.add(treeNode5);
        treeNode2.child = child2;
        List<TreeNode> child3 = new ArrayList<>();
        child3.add(treeNode2);
        child3.add(treeNode4);
        treeNode3.child = child3;

        List<TreeNode> child4 = new ArrayList<>();
        child4.add(treeNode3);
        treeNode5.child = child4;


        Dfs2 dfs = new Dfs2();
        System.out.println(dfs.solution(treeNode1));
    }


    private static class TreeNode {
        int id;
        List<TreeNode> child;
        TreeNode(int id) { this.id = id; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TreeNode treeNode = (TreeNode) o;
            return id == treeNode.id &&
                    Objects.equals(child, treeNode.child);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, child);
        }
    }

}
