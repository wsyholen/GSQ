package com.sixtyrobbers.GSQ.fourm.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 * Explain: 数据脱敏
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 14:40
 * Version: V1.0
 * </pre>
 */
public class DesensitizeUtil {

    /**
     * 只显示第一个汉字，其他隐藏为2个星号<例子：李**>
     *
     * @param fullName
     * @param index    1 为第index位
     * @return
     */
    public String left(String fullName, int index) {
        if (StringUtils.isBlank(fullName)) {
            return "";
        }
        String name = StringUtils.left(fullName, index);
        return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
    }

    /**
     * [身份证号] 110****58，前面保留3位明文，后面保留2位明文
     *
     * @param name
     * @param index 3
     * @param end   2
     * @return
     */
    public String around(String name, int index, int end) {
        if (StringUtils.isBlank(name)) {
            return "";
        }
        return StringUtils.left(name, index).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(name, end), StringUtils.length(name), "*"), "***"));
    }

    /**
     * [固定电话] 后四位，其他隐藏<例子：****1234>
     *
     * @param num
     * @return
     */
    public String right(String num, int end) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils.leftPad(StringUtils.right(num, end), StringUtils.length(num), "*");
    }

    /**
     * <pre>
     * 说    明: 名字大于3位只保留首和尾。小于只保留首
     * 涉及版本: V1.0.0
     * 创 建 者: Holen
     * 日    期: 2019/3/6 13:18
     * 联系方式: 317776764
     * </pre>
     */
    public String middle(String fullName) {
        if (StringUtils.isBlank(fullName)) {
            return "";
        }
        if (fullName.length() > 2) {
            String s2 = StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(fullName, 1), StringUtils.length(fullName), "*"), "*");
            return StringUtils.left(fullName, 1).concat(s2);
        } else {
            String name = StringUtils.left(fullName, 1);
            return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
        }
    }
}
