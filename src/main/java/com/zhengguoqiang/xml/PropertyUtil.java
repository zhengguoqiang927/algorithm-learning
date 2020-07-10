package com.zhengguoqiang.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertyUtil {
   private static final Properties prop = new Properties();
   static{
       try {
           InputStream resourceAsStream = PropertyUtil.class.getClassLoader().getResourceAsStream("config.properties");
           if (resourceAsStream == null){
               System.out.println("配置文件加载错误！！！");
           }else {
               prop.load(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8));
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   public static String getProperty(String key){
       return prop.getProperty(key);
   }
}
