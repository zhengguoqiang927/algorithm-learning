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

    public static String replaceSpace(String s){
        char[] oldChar = s.toCharArray();
        int spaceNum = 0;
        int oldIndex = oldChar.length-1;
        for (int i = 0;i<=oldIndex;i++){
            if (oldChar[i] == 32) spaceNum++;
        }
        int newIndex = oldIndex + 2 * spaceNum;
        char[] newChar = new char[newIndex+1];
        while (oldIndex >= 0){
            if (oldChar[oldIndex] == 32){
                newChar[newIndex--] = '0';
                newChar[newIndex--] = '2';
                newChar[newIndex--] = '%';
            }else {
                newChar[newIndex--] = oldChar[oldIndex];
            }
            oldIndex--;
        }
        return new String(newChar);
    }

    public static String replaceSpaceWithStringBuilder(String s){
        StringBuilder sb = new StringBuilder();
        for (Character c:s.toCharArray()){
            if (c == ' ') sb.append("%20");
            else sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String result = replaceBlank(new StringBuilder("we are happy"));
        System.out.println(result);
        String s = replaceSpace("we are happy");
        System.out.println(s);
        String we_are_happy = replaceSpaceWithStringBuilder("we are happy");
        System.out.println(we_are_happy);
    }
}
