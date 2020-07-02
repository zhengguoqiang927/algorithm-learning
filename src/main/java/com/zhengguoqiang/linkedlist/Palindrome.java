package com.zhengguoqiang.linkedlist;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断单链表中存储的是否是回文字符串
 */
public class Palindrome {
    static class Node{
        final int value;
        Node next;
        Node(int value){
            this.value = value;
        }
    }

    public static boolean isPalindrome(Node head){

        //1.找到链表的中间结点
        //2.逆序后半部分结点
        //3.对称比较值是否相等
        return false;
    }

    public static void main(String[] args) {
        boolean matches = Pattern.matches("[a-zA-Z_]+", "1523711762930703?origin_mac=");
        System.out.println(matches);
        Pattern compile = Pattern.compile("[a-zA-Z_]*");
        Matcher matcher = compile.matcher("1523711762930703?origin_mac=");
        System.out.println(matcher.find());
    }
}
