package com.zhengguoqiang.symboltable;

/**
 * 基于无序链表的有序符号表
 *
 * 顺序查找
 *
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchST<Key,Value> {

   private class Node{
       Key key;
       Value value;
       Node next;

       public Node(Key key, Value value, Node next) {
           this.key = key;
           this.value = value;
           this.next = next;
       }
   }

   private Node first;

   public Value get(Key key){
       for (Node x = first;x != null;x = x.next){
            if (key.equals(x.key)){
                return x.value;
            }
       }
       return null;
   }

   public void put(Key key,Value value){
       for (Node x = first;x != null;x = x.next){
           if (key.equals(x.key)){
               x.value = value;
               return;
           }
       }
       //未命中 新建头结点
       first = new Node(key,value,first);
   }
}
