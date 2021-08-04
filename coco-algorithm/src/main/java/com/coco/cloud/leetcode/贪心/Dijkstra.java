package com.coco.cloud.leetcode.贪心;

import java.util.Arrays;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/8/3 21:57
 */
public class Dijkstra {

    public int networkDelayTime(int[][] times, int n, int k) {
        int max = Integer.MAX_VALUE / 2;
        // 构造邻接矩阵
        int[][] dist = new int[n][n];
        for (int[] time : times) {
            dist[time[0] - 1][time[1] - 1] = time[2];
        }
        // 已经遍历过的节点距离起点的长度
        int[] close = new int[n];
        Arrays.fill(close, max);

        // 遍历到但是没有执行完逻辑的节点距离起点的长度
        int[] open = new int[n];
        Arrays.fill(open, max);
        open[k-1] = 0;

        while(openIsNotEmpty(open,max)){
            int minOpen = getMinOpen(open);
            for (int i = 0; i < dist[minOpen].length; i++) {
                if (dist[minOpen][i] != 0 && i != k - 1) {
                    if (dist[k - 1][i] == 0){
                        open[i] = open[minOpen] + dist[minOpen][i];
                    }else{
                        open[i] = Math.min(dist[k - 1][i], open[minOpen] + dist[minOpen][i]);
                    }
                }
            }
            close[minOpen] = open[minOpen];
            open[minOpen] = max;
        }

        int step = 0;
        for (int value : close) {
            if (value >= max) {
                return -1;
            }
            if (value > step) {
                step = value;
            }
        }
        return step;
    }

    private int getMinOpen(int[] open) {
        int min = open[0];
        int minIndex = 0;
        for (int i = 1; i < open.length; i++) {
            if (open[i] < min){
                min = open[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private boolean openIsNotEmpty(int[] open,int max) {
        for (int i : open) {
            if (i >= max){
                continue;
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        int[][] times = {{1,2,1}};
        int n = 2,k = 2;
        int i = dijkstra.networkDelayTime(times, n, k);
        System.out.println(i);
    }

}

