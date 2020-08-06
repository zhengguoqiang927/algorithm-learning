package com.zhengguoqiang;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void test(){
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){

            System.out.println(ClassLayout.parseInstance(o).toPrintable());

//            User user = new User(100,"testtesttest");
            User[] user = new User[1];
            System.out.println(ClassLayout.parseInstance(user).toPrintable());

            String name  = "testtesttest";
            System.out.println(name.getBytes().length);
        }
    }

    public static int binarySearch(int[] a,int value){
        int low = 0;
        int high = a.length - 1;
        while(low <= high){
            int mid = low + (high - low)/2;
            if (value == a[mid]){
                return mid;
            }else if (value > a[mid]){
                low = mid + 1;
            }else {
                high = mid -1;
            }
        }
        return -1;
    }

    public static int binarySearchByRecusive(int[] a,int lo,int hi,int value){
        if (lo > hi) return -1;
        int mid = lo + (hi - lo)/2;
        if (value == a[mid]){
            return mid;
        }else if (value > a[mid]){
            return binarySearchByRecusive(a,mid + 1,hi,value);
        }else {
            return binarySearchByRecusive(a,lo,mid - 1,value);
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7,8};
        int i = binarySearch(a,3);
        System.out.println("index I :" + i);
        int j = binarySearchByRecusive(a,0,a.length-1,3);
        System.out.println("index J :" + j);

        String s = "flsjdsadfljs\r\nsfjsldkfj";
        String s1 = s.replaceAll("[\\r\\n]", "\n");
//        String s2 = s1.replaceAll("^\\n$","00");
        System.out.println(s1);

        Map<String,Integer> map = new HashMap<>();
        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
        map.put("d",4);
        Set<String> strings = map.keySet();
        for (String ss:strings){
            System.out.println(ss);
        }
    }

    @Test
    public void testSimpleInt(){
        SimpleInt instance = new SimpleInt();
        System.out.println("The identity hash code is " + System.identityHashCode(instance));
        System.out.println(ClassLayout.parseInstance(instance).toPrintable());
    }

    @Test
    public void testSimpleLong(){
        System.out.println(ClassLayout.parseClass(SimpleLong.class).toPrintable());
        SimpleLong simpleLong = new SimpleLong();
        System.out.println(ClassLayout.parseInstance(simpleLong).toPrintable());
    }

    @Test
    public void testSimpleArray(){
        System.out.println(ClassLayout.parseClass(SimpleArray.class).toPrintable());
        SimpleArray simpleArray = new SimpleArray();
        simpleArray.ans[0] = 12L;
        simpleArray.ans[1] = 12L;
        System.out.println(ClassLayout.parseInstance(simpleArray).toPrintable());
    }

class User{
    int id;
    String name;

//    int[] books;

    public User(){}

    public User(int id, String name) {
        this.id = id;
        this.name = name;
//        this.books = books;
    }
}


}

class SimpleInt{
    private int state;
}

class SimpleLong{
    private long state;
}

class SimpleArray{
    int state;
    long[] ans = new long[10];
}