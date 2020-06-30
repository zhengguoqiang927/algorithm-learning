package com.zhengguoqiang.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * 链表中检测是否有环
 */
public class CheckCyclic {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }

    //方案一 哈希表
    //时间复杂度：O(n),空间复杂度：O(n)
    public static boolean solutionOne(ListNode head){
//        if (head == null || head.next == null) return false;
        Set<ListNode> nodeSet = new HashSet<>();
        while (head != null){
            if (nodeSet.contains(head)){
                return true;
            }else {
                nodeSet.add(head);
            }
            head = head.next;
        }
        return false;
    }

    //方案二 快慢指针
    //时间复杂度：O(n),空间复杂度：O(1)
    public static boolean solutionTwo(ListNode head){
//        if (head == null || head.next == null) return false;
        ListNode slow = head,fast = head;
        do {
            if (fast == null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
        }while (slow != fast);
        return true;
    }

    public static void main(String[] args) {
        ListNode ln1 = new ListNode(1);
        ListNode ln2 = new ListNode(2);
        ListNode ln3 = new ListNode(3);
        ListNode ln4 = new ListNode(4);
        ln1.next = ln2;
        ln2.next = ln3;
        ln3.next = ln4;
        ln4.next = ln3;

//        boolean p = solutionOne(ln1);
        boolean p = solutionTwo(ln1);
        System.out.println(p);
    }
}
