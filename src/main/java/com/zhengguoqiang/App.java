package com.zhengguoqiang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws ParseException {
        System.out.println( "Hello World!" );

        String s = "2020-07-08T18:18:38";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

        Date date = sdf.parse(s);
        System.out.println(sdfDate.format(date));
    }
}
