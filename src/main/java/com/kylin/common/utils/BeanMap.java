package com.kylin.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author kylin
 * Description
 * @Date Created in 2018/06/04 19:47
 */
public class BeanMap {
    /**
     * @author xxxxxxxxxxx
     * @param object
     *            待转化类
     * @return Map<String,String> 将传入的DTO转化为Map，1、Date转化为String类型的时间戳。 2、null转化为null
     *         3、enum通过反射使用getCode()获取code编码。4、其他类型不做转换value=toString()。5、Dto中属性名中的大写字母将被替换为
     *         "_小写字母"形式，为了与库中返回map一致。
     */
    public static Map<String, Object> objectToMap(Object object) {
        if (object == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                String key = field.getName();
                if (key.equals("serialVersionUID")) {
                    continue;
                }
                String tmpKey=key;
//                for(int i=0;i<key.length();i++) {
//                    if(Character.isUpperCase(key.charAt(i))) {
//                        tmpKey=tmpKey.replaceAll(""+key.charAt(i), "_"+Character.toLowerCase(key.charAt(i)));
//                    }
//                }
                key=tmpKey;
                Object value = field.get(object);
                if (value == null) {
                    map.put(key, null);
                } else if (value instanceof Date) {
                    map.put(key, String.valueOf(((Date) value).getTime()));
                } else if (value.getClass().isEnum()) {
                    Method method = value.getClass().getMethod("getCode", null);
                    Object val = method.invoke(value, null);
                    map.put(key, String.valueOf(val));
                } else if (value.getClass().isPrimitive()) {
                    map.put(key, value + "");
                } else {
                    map.put(key, value.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return map;
    }
}
