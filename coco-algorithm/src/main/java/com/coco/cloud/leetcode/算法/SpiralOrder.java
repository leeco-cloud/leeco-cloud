package com.coco.cloud.leetcode.算法;

import java.util.Arrays;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 * 示例 ：
 * 输入：matrix =
 *      [[1,2,3],
 *       [4,5,6],
 *       [7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/6/5 19:57
 */
public class SpiralOrder {

    // 利用螺旋矩阵实现
    public void spiralOrder(int[][] matrix) {
        if (matrix.length == 0){
            return;
        }
        // 一维长度
        int imax = matrix.length;
        // 二维长度
        int jmax = matrix[0].length;
        // 确定最后一轮遍历的结束位置
        int istop = imax % 2 == 1? imax / 2 : imax / 2 - 1;
        int jstop = jmax % 2 == 1? jmax / 2 : jmax / 2;
        // 临时变量
        int iindex = 0;
        int jindex = 0;
        int[] result = new int[imax * jmax];
        int itemp  = 0;
        while(matrix[istop][jstop] != -1){
            for(int j = jindex;j < jmax - jindex;j++){
                result[itemp++] = matrix[iindex][j];
                matrix[iindex][j] = -1;
            }
            for(int i = iindex + 1;i < imax - iindex;i++){
                result[itemp++] = matrix[i][jmax - jindex - 1];
                matrix[i][jmax - jindex - 1] = -1;
            }
            for(int j = jmax - jindex - 2;j >= jindex;j--){
                if (matrix[imax - iindex - 1][j] != -1){
                    result[itemp++] = matrix[imax - iindex - 1][j];
                    matrix[imax - iindex - 1][j] = -1;
                }
            }
            for(int i = imax - iindex - 2;i> iindex;i--){
                if (matrix[i][jindex] != -1){
                    result[itemp++] = matrix[i][jindex];
                    matrix[i][jindex] = -1;
                }
            }
            iindex ++ ;
            jindex ++ ;
        }
        System.out.println(Arrays.toString(result));
    }

    public static void main(String[] args) {
        new SpiralOrder().spiralOrder(new int[][]{{1, 2, 3}, {5, 6,7}, {9,10,11}});
    }

}
