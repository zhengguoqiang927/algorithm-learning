package com.zhengguoqiang;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * nRigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
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


}
