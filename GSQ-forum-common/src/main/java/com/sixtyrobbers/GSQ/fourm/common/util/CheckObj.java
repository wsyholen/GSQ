package com.sixtyrobbers.GSQ.fourm.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/4/12 11:05
 * Version: V1.0
 * </pre>
 */
public class CheckObj {

    /**
     * <pre>
     * Explain: 判断对象里面属性是否为空
     * Author: holennnnnn_
     * Create_Time: 2019/4/12 11:06
     * Version: V1.0
     * </pre>
     */
    public static boolean checkObjFieldIsNull(Object obj) throws IllegalAccessException {
        boolean flag = true;
        for (Field f : obj.getClass().getDeclaredFields()) {
            //取消Java的权限控制检查
            f.setAccessible(true);
            if (f.get(obj) == null) {
                flag = false;
                return flag;
            }
        }
        return flag;
    }

    /**
     * <pre>
     * Explain: 判断对象里面属性是否为空并返回结果
     * Author: holennnnnn_
     * Create_Time: 2019/5/7 16:25
     * Version: V1.0
     * </pre>
     */
    public static String checkObjIsNull(Object object,String [] arr) throws IllegalAccessException {
        List<String> result = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            //取消Java的权限控制检查
            field.setAccessible(true);
            if (arr != null){
                for (String res: arr) {
                    if (field.getName().equals(res)){
                        if (field.get(object) == null) {
                            result.add(field.getName());
                        }
                    }
                }
            }else {
                if (field.get(object) == null) {
                    result.add(field.getName());
                }
            }
        }
        if (result.size() != 0) {
            String str = null;
            for (int i = 0; i < result.size(); i++) {
                if (str == null) {
                    str = result.get(i);
                } else {
                    str = str + "、" + result.get(i);
                }
            }
            return "参数：" + str + "不能为空！";
        }
        return null;
    }

    public static String[] addArray(String [] arr,String param){
        String [] result = new String [arr.length+1];
        System.arraycopy(arr,0,result,0,arr.length);
        result[result.length-1] = param;
        return result;
    }

    public static boolean checkArray(String [] arr,String param){
        for (String s : arr) {
            if (s.equals(param)){
                return true;
            }
        }
        return false;
    }


}
