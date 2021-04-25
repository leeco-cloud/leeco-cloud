package com.coco.cloud.leetcode.算法;

/**
 * 旋转子链表
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/5/16 20:09
 */
public class SolutionLinked {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        // 创建空的头节点
        ListNode tempHead = new ListNode(0);
        tempHead.next = head;
        ListNode pre = tempHead;
        // 开始的时候 尾节点就是头节点
        ListNode end = tempHead;
        // 当前不是尾节点
        while(end.next != null){
            for(int i = 0;i<k && end != null;i++){
                end = end.next;
            }
            if (end == null){
                break;
            }
            // 断开next连接
            ListNode tempStart = pre.next;
            ListNode tempNext = end.next;
            end.next = null;
            // 旋转子连接
            pre.next = reverse(tempStart);
            tempStart.next = tempNext;
            pre = tempStart;
            end = pre;
        }
        return tempHead.next;
    }

    /**
     * 子链表旋转
     */
    private ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nextTemp;
        }
        return pre;
    }

}
