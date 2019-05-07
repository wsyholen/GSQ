package com.sixtyrobbers.GSQ.fourm.common.util;

import java.lang.reflect.Field;

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



}
