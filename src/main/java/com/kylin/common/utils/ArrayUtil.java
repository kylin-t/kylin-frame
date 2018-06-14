package com.kylin.common.utils;

/**
 * @Author kylin
 * Description
 * @Date Created in 2018/06/06 19:26
 */
public class ArrayUtil {
    public static Long[] strToLongArr(String str){
        String[] strArr = str.split(",");
        Long[] longArr = new Long[strArr.length];
        for (int i = 0;i<strArr.length;i++){
            longArr[i] = new Long(strArr[i]);
        }
        return longArr;
    }
}
