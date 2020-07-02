package com.zhengguoqiang.linkedlist;

import java.util.concurrent.ConcurrentHashMap;

public class MergeTwoLists {
    static class ListNode{
        final int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    //方法一：遍历比较 时间复杂度O(n + m)
    public static ListNode merge(ListNode p1,ListNode p2){
        ListNode shard = new ListNode(0);
        ListNode first = shard;
        while (p1 != null && p2 != null){
            if (p1.val < p2.val){
                first.next = p1;
                first = first.next;
                p1 = p1.next;
            }else{
                first.next = p2;
                first = first.next;
                p2 = p2.next;
            }
        }
        first.next = p1 == null ? p2 : p1;
        return shard.next;
    }

    //方法二：递归 时间复杂度O(n + m)
    public static ListNode mergeByRecursive(ListNode p1,ListNode p2){
        if(p1 == null) {
            return p2;
        }else if (p2 == null){
            return p1;
        }
        if (p1.val < p2.val){
            p1.next = mergeByRecursive(p1.next,p2);
            return p1;
        }else {
            p2.next = mergeByRecursive(p1,p2.next);
            return p2;
        }
    }

    public static void main(String[] args) {
        ListNode ln1 = new ListNode(1);
        ListNode ln2 = new ListNode(2);
        ListNode ln3 = new ListNode(5);
        ListNode ln4 = new ListNode(7);
        ln1.next = ln2;
        ln2.next = ln3;
        ln3.next = ln4;
        ln4.next = null;

        ListNode rn1 = new ListNode(1);
        ListNode rn2 = new ListNode(2);
        ListNode rn3 = new ListNode(6);
        ListNode rn4 = new ListNode(8);
        rn1.next = rn2;
        rn2.next = rn3;
        rn3.next = rn4;
        rn4.next = null;

        ListNode merge = mergeByRecursive(ln1, rn1);
        while (merge != null){
            System.out.print(merge.val + " -> ");
            merge = merge.next;
        }
        System.out.print("null");
    }
}
