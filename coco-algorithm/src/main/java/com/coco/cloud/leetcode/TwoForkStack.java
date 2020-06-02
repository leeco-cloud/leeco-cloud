package com.coco.cloud.leetcode;

/**
 * 二叉堆算法 => 堆排序
 * 代码以最小二叉树实现
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/6/1 21:08
 */
public class TwoForkStack {

    /**
      二叉堆 : (利用数组表示 特性 :位置 :左子节点 = 父节点 * 2 + 1 , 右子节点 = 父节点 *2 + 2)
           最小二叉堆  最小二叉堆的父节点小于左右孩子节点
           最大二叉堆  最大二叉堆的父节点大于左右孩子节点
      利用二叉堆的特性 可以推出堆排序
     */
    public static void main(String[] args) {

    }

    /**
     * 构建二叉堆
     * 最小二叉堆：数组由后向前找出非叶子节点 依次进行下沉
     * 最小二叉堆：同上
     */
    public void srted(int[] nums){
        for(int i = (nums.length - 2)/2; i >= 0; i--){
            down(nums,nums[i],nums.length);
        }
    }

    /**
     * 添加节点：
     * 最小二叉堆：将尾节点和父节点对比 若小于父节点 则和父节点兑换 ，然后再和父节点对比 直到不满足条件
     * 最大二叉堆：将尾节点和父节点对比 若大于父节点 则和父节点兑换 ，然后再和父节点对比 直到不满足条件
     */
    public void up(int[] nums){
        int index = 0;
        while(index < nums.length){
            int lastNode = nums[nums.length - 1];
            int parentIndex = (nums.length - 1)/2;
            if (lastNode < nums[parentIndex]){
                nums[parentIndex] = lastNode;
            }
            index = parentIndex;
        }
    }

    /**
     * 移除根节点：
     * 最小二叉堆：将尾节点代替根节点 找出最小的孩子节点 进行替换 一次下沉 直到不满足条件
     * 最大二叉堆：同上
     */
    public void down(int[] nums,int parent,int length){

        int leftChildIndex = parent * 2 + 1;

        int parentNode = nums[parent];

        while(leftChildIndex < length){
            // 右子节点更小
            if (leftChildIndex + 1 < length && nums[leftChildIndex + 1] < nums[leftChildIndex]){
                leftChildIndex ++;
            }
            if (parentNode <= nums[leftChildIndex]){
                break;
            }
            nums[parent] = nums[leftChildIndex];
            nums[leftChildIndex] = parentNode;
            leftChildIndex = 2 * leftChildIndex + 1;
        }
    }

}
