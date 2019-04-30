package com.sixtyrobbers.GSQ.fourm.common.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 共通类
 * Created by hekang on 19/4/30.
 */
public class GSQUtil {
    private static final Logger logger = LoggerFactory.getLogger(GSQUtil.class);

    /**
     * 判断dto是否为空
     *
     * @return
     */
    public static boolean isBeanEmpty(Object object) {
        Boolean flag = Boolean.TRUE;
        if (object == null) {
            return flag;
        }
        Method[] methods = object.getClass().getMethods();
        if (methods != null) {
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                if (method.getName().startsWith("get") && !method.getName().equals("getClass")) {
                    try {
                        Object resultObj = method.invoke(object, null);
                        if (resultObj != null) {
                            flag = false;
                        }
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 检测指定对象是否为null或者空字符串 如果对象为null或空字符串则返回true，否则返回false
     *
     * @param value
     * @param trim  可选参数，当value为String类型时，是否调用trim后再判断其长度
     * @return
     */
    public static boolean isNullOrEmpty(Object value, boolean trim) {
        if (null == value) {
            return true;
        }
        if (value instanceof String) {
            String s = (String) value;
            if (trim) {
                s = s.trim();
            }
            if (s.length() == 0) {
                return true;
            }
        } else if (value instanceof Map) {
            Map map = (Map) value;
            if (map.isEmpty()) {
                return true;
            }
        } else if (value instanceof String[]) {
            String[] s = (String[]) value;
            if (s.length == 0) {
                return true;
            }
        }
        return false;
    }
}
