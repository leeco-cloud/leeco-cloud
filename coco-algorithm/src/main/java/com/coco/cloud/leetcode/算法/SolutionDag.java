package com.coco.cloud.leetcode.算法;

import java.util.*;

/**
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 *
 * 示例 2:
 *
 * 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
 * 输出: [0,1,2,3] or [0,2,1,3]
 * 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 *      因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 *
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/5/17 23:18
 */
public class SolutionDag {

    /**
     * 记录可以学完的课程门数
     */
    private int count = 0;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return new int[0];
        }
        // 建立入度表，即学习每门课对应的先修课门数。
        int[] in = new int[numCourses];
        // 定义数组依次存可以学完的课程。
        int[] ans = new int[numCourses];
        // 定义 list 数组存储所有作为其他课程先决条件出发的边。
        List[] edges = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        // 遍历先决条件边缘列表。
        for (int[] pre : prerequisites) {
            // 每有一门先决条件课，则当前课程入度表位置 +1 。
            in[pre[0]]++;
            // 将当前先决条件课出发的边加入数组对应的 list 中。
            edges[pre[1]].add(pre[0]);
        }
        // 遍历所有课程，将所有入度为 0 的课加入可学习数组。
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) {
                ans[count++] = i;
                // 将对应的入度赋值为 -1 ，防止重复添加。
                in[i] = -1;
                // 递归遍历以当前课为先决条件的课程。
                dfs(edges, edges[i], in, ans);
            }
        }
        // 判断是否可以学完所有课程，返回最终结果。
        return count == numCourses ? ans : new int[0];
    }

    public void dfs(List[] edges, List<Integer> list, int[] in, int[] ans) {
        // 遍历以当前课程出发的边，即以当前课程为先决条件的课入度表对应位置 -1 。
        for (int terminus : list) {
            in[terminus]--;
            // 将入度变为 0 的课程加入可学习数组。
            if (in[terminus] == 0) {
                ans[count++] = terminus;
                // 将对应的入度赋值为 -1 ，防止重复添加。
                in[terminus] = -1;
                // 递归遍历以当前课为先决条件的课程。
                dfs(edges, edges[terminus], in, ans);
            }
        }
    }

    public static void main(String[] args) {
        int[][] prerequisites = new int[4][2];
        prerequisites[0][0] = 1;
        prerequisites[0][1] = 0;
        prerequisites[1][0] = 2;
        prerequisites[1][1] = 0;
        prerequisites[2][0] = 3;
        prerequisites[2][1] = 1;
        prerequisites[3][0] = 3;
        prerequisites[3][1] = 2;

        System.out.println(Arrays.toString(new SolutionDag().findOrder(4, prerequisites)));
    }

}
