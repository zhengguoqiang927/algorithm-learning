package com.zhengguoqiang.offer;

/**
 * 5.字符串替换空格
 * 注：合并两个数组（包括字符串）时，如果从先往后复制每个数字（或字符）则需要重复移动数字（或字符）多次
 * 可以考虑从后往前复制，这样可以减少移动的次数，提高效率
 */
public class ReplaceBlank {
    private static String replaceBlank(StringBuilder builder){
        if (builder == null || builder.length() == 0) return null;
        int spaceNum = 0;
        int oldIndex = builder.length()-1;
        for (int i=0;i<builder.length();i++){
            if (builder.charAt(i) == ' '){
                spaceNum++;
            }
        }
        System.out.println("space num:" + spaceNum);
        int newIndex = oldIndex + 2 * spaceNum;
        builder.setLength(newIndex+1);//防止数组下标越界`
        while (oldIndex >=0 && oldIndex != newIndex){
            if (builder.charAt(oldIndex) == ' '){
                builder.setCharAt(newIndex--,'0');
                builder.setCharAt(newIndex--,'2');
                builder.setCharAt(newIndex--,'%');
            }else {
                builder.setCharAt(newIndex--,builder.charAt(oldIndex));
            }
            oldIndex--;
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String result = replaceBlank(new StringBuilder("we are happy"));
        System.out.println(result);
    }
}
