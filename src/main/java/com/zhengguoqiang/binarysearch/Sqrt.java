package com.zhengguoqiang.binarysearch;

import java.util.LinkedHashMap;
import java.util.Map;

public class Sqrt {

    public static double sqrt(double t,Double precise){
        double lo = 0,hi = t,prec = precise != null ? precise : 1e-7;
        double middle = 0,squre = 0;
        while (hi - lo > prec){
            middle = lo + (hi - lo)/2;
            squre = middle * middle;
            if (squre > t){
                hi = middle;
            }else {
                lo = middle;
            }
        }
        return middle;
    }

    public static int mySqrt(int x){
        int l = 0,r = x,ans = -1;
        while (l <= r){
            int mid = l + (r - l)/2;
            if ((long)mid * mid <= x){
                ans = mid;
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        double sqrt = sqrt(10, 0.000001);
//        System.out.println(sqrt);
        int i = mySqrt(8);
        System.out.println(i);
        String phone = "13708754689";
        String s = phone.substring(0, 3) + "****" + phone.substring(7);
        System.out.println(phone);
        System.out.println(s);
        Map<Integer,Integer> map = new LinkedHashMap<>(10,0.75f,true);
        map.put(3,11);
        map.put(1,12);
        map.put(5,23);
        map.put(2,22);

        map.put(3,26);
        map.get(5);

        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
