package com.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiNan on 2017-12-26.
 * Description: JSON工具类
 */
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转JSON字符串
     * @param object
     * @return
     */
    public static String toStr(Object object){
        String jsonStr = "";
        try {
            jsonStr = objectMapper.writer().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    /**
     * JSON字符串转对象
     * @param clazz
     * @param str
     * @return
     */
    public static Object toObject(String str,Class clazz){
        try {
            return objectMapper.readValue(str,clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSON字符串转集合
     * @param str
     * @param collectionClass
     * @param elementClasses
     * @param <T>
     * @return
     */
    public static <T> T toObject(String str,Class<?> collectionClass,Class<?>... elementClasses){
        JavaType javaType = objectMapper.getTypeFactory().constructParametrizedType(collectionClass,collectionClass,elementClasses);
        try {
            return objectMapper.readValue(str,javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 分页查JSON串
     * @param rows 全部条数
     * @param obj 对象
     * @return
     */
    public static String createPageJson(long rows,Object obj){
        String json = "{\"total\":\""+ rows + "\",\"root\":" + toStr(obj) + "}";
        return json;
    }





    public static void main(String[] args) {
        String  jsonString = "{\"name\":\"MaHesh\", \"age\":21}";
        String  jsonString1 = "[{\"name\":\"MaHesh\", \"age\":21},{\"name\":\"LiNan\", \"age\":27}]";
        Student st = null;
        try {
            st = objectMapper.readValue(jsonString,Student.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Student> students = toObject(jsonString1,ArrayList.class,Student.class);

        HashMap<String,String> map = (HashMap<String, String>) toObject(jsonString,Map.class);
//        System.out.println(toStr(map));
        Student sta = (Student) toObject(toStr(st),Student.class);
        System.out.println(createPageJson(1,sta));
        ArrayList<Student> list = (ArrayList<Student>) toObject(jsonString1,List.class);
//        ArrayList<Student> list1 = (ArrayList<Student>) toObject(jsonString1,List.class);
//        System.out.println(toStr(list));
    }
}
