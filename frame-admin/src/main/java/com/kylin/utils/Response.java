package com.kylin.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author kylin
 * Description 统一接口返回类型
 * @Date Created in 2018/05/17 15:53
 */
public class Response extends HashMap<String,Object> {

    public Response(){
        put("code",0);
        put("msg","success");
    }

    public static Response error(){
        return error(500,"未知异常，请联系管理员");
    }
    public static Response error(String msg){
        return error(500,msg);
    }
    public static Response error(int code,String msg){
        Response response = new Response();
        response.put("code",code);
        response.put("msg",msg);
        return response;
    }

    public static Response success(){
        return new Response();
    }

    public static Response success(String msg){
        Response response = new Response();
        response.put("msg",msg);
        return response;
    }

    public static Response success(Map<String,Object> map){
        Response response = new Response();
        response.putAll(map);
        return response;
    }

    @Override
    public Response put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
