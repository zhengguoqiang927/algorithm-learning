package com.zhengguoqiang;

public class Counter {
    static int counter = 0;

    public static synchronized void inc(){
        counter += 1;
    }

    public void dec(){
        synchronized (this){
            counter -= 1;
        }
    }

    public static void main(String[] args) {
        for (int i = 0;i<100000;i++){
            inc();
        }
    }
}
