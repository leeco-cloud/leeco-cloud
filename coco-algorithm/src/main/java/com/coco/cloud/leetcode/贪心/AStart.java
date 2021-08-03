package com.coco.cloud.leetcode.贪心;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AStart {

    public static class Grid{

        // 当前格子的坐标
        private int[] point;

        // H:距离起点的步数  G:距离终点的步数  F:两者的和
        private int H,G,F;

        // 父格子
        private Grid parent;

        public Grid(int[] start, int[] current, int[] end, Grid parent) {
            this.point = new int[]{current[0],current[1]};
            this.H = Math.abs(current[0] - start[0]) + Math.abs(current[1] - start[1]);
            this.G = Math.abs(current[0] - end[0]) + Math.abs(current[1] - end[1]);
            this.F = H + G;
            this.parent = parent;
        }

        public int[] getPoint() {
            return point;
        }

        public void setPoint(int[] point) {
            this.point = point;
        }

        public int getH() {
            return H;
        }

        public void setH(int h) {
            H = h;
        }

        public int getG() {
            return G;
        }

        public void setG(int g) {
            G = g;
        }

        public int getF() {
            return F;
        }

        public void setF(int f) {
            F = f;
        }

        public Grid getParent() {
            return parent;
        }

        public void setParent(Grid parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Grid{" +
                    "point=" + Arrays.toString(point) +
                    ", H=" + H +
                    ", G=" + G +
                    ", F=" + F +
                    ", parent=" + parent +
                    '}';
        }
    }

    public Grid sortRoad(int[][] grids,int[] start,int[] end) {
        int m = grids.length;
        int n = grids[0].length;
        // 能达到的格子 采用小顶堆实现排序和增删
        List<Grid> openList = new ArrayList<>();
        // 已经访问过的格子
        List<Grid> closeList = new ArrayList<>();
        Grid startGrid = new Grid(start,start,end, null);
        openList.add(startGrid);
        Grid result = null;
        while(!openList.isEmpty()){
            Grid grid = removeGrid(openList,closeList);

            int[] point = grid.getPoint();
            int x = point[0];
            int y = point[1];
            if (x > 0){
                // 上
                if (grids[x-1][y] != 1){
                    if (!isExistGrid(openList,closeList,x-1,y)){
                        Grid up = new Grid(start, new int[]{x-1,y}, end, grid);
                        if (x-1 == end[0] && y == end[1]){
                            result = up;
                            break;
                        }
                        addGrid(openList,up);
                    }
                }
            }
            if (x < m - 1){
                // 下
                if (grids[x+1][y] != 1){
                    if (!isExistGrid(openList,closeList,x+1,y)){
                        Grid down = new Grid(start, new int[]{x+1,y}, end, grid);
                        if (x+1 == end[0] && y == end[1]){
                            result = down;
                            break;
                        }
                        addGrid(openList,down);
                    }
                }
            }
            if (y > 0){
                // 左
                if (grids[x][y-1] != 1){
                    if (!isExistGrid(openList,closeList,x,y-1)){
                        Grid left = new Grid(start, new int[]{x,y-1}, end, grid);
                        if (x == end[0] && y-1 == end[1]){
                            result = left;
                            break;
                        }
                        addGrid(openList,left);
                    }
                }
            }
            if (y < n - 1){
                // 右
                if (grids[x][y+1] != 1){
                    if (!isExistGrid(openList,closeList,x,y+1)){
                        Grid right = new Grid(start, new int[]{x,y+1}, end, grid);
                        if (x == end[0] && y+1 == end[1]){
                            result = right;
                            break;
                        }
                        addGrid(openList,right);
                    }
                }
            }
        }
        return result;
    }

    private boolean isExistGrid(List<Grid> openList, List<Grid> closeList, int x, int y) {
        if (openList != null && !openList.isEmpty()){
            for (Grid grid : openList) {
                if (grid.point[0] == x && grid.point[1] == y){
                    return true;
                }
            }
        }
        if (closeList != null && !closeList.isEmpty()){
            for (Grid grid : closeList) {
                if (grid.point[0] == x && grid.point[1] == y){
                    return true;
                }
            }
        }
        return false;
    }

    /** 插入(直接插入堆底 然后逐步上浮 */
    private static void addGrid(List<Grid> openList, Grid grid) {
        openList.add(grid);
        int index = openList.size() - 1;
        if (index == 1){
            if (openList.get(1).getF() < openList.get(0).getF()){
                openList.set(1,openList.get(0));
                openList.set(0,grid);
                return;
            }
        }
        while(index > 1){
            Grid current = openList.get(index);
            if (index % 2 == 0){
                // 右节点
                Grid parent = openList.get((index - 2) / 2);
                if (current.getF() < parent.getF()){
                    openList.set((index - 2) / 2,current);
                    openList.set(index,parent);
                }
                index = (index - 2) / 2;
            }else{
                // 左节点
                Grid parent = openList.get((index - 1) / 2);
                if (current.getF() < parent.getF()){
                    openList.set((index - 1) / 2,current);
                    openList.set(index,parent);
                }
                index = (index - 1) / 2;
            }
        }
    }

    /** 删除堆顶元素 拿堆底元素补充 然后逐步下沉 */
    public static Grid removeGrid(List<Grid> openList, List<Grid> closeList) {
        Grid grid = openList.get(0);
        closeList.add(grid);
        if (openList.size() <= 1){
            return grid;
        }
        Grid lastNode = openList.get(openList.size() - 1);
        openList.set(0,lastNode);
        openList.remove(openList.size() - 1);
        int index = 0;
        while(index < ((openList.size() - 2) / 2)){
            Grid current = openList.get(index);
            int min;
            Grid leftChild = openList.get(index * 2 + 1);
            Grid rightChild = openList.get(index * 2 + 2);
            if (rightChild.getF() < leftChild.getF()){
                min = index * 2 + 1;
            }else{
                min = index * 2 + 2;
            }
            if (current.getF() < openList.get(min).getF()){
                openList.set(index,openList.get(min));
                openList.set(min,current);
                index = min;
            }else{
                break;
            }
        }
        return grid;
    }

    public static void main(String[] args) {
        AStart solution = new AStart();
        int[][] grids = new int[][]{
                {0,0,0,0,0},
                {0,0,1,0,0},
                {0,0,1,0,0},
                {0,0,1,0,0},
                {0,0,0,0,0}
        };
        Grid result = solution.sortRoad(grids,new int[]{1,1},new int[]{2,3});
        System.out.println(result.toString());
    }

}
