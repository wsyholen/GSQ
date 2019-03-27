package com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param;

import com.sixtyrobbers.GSQ.fourm.dao.entity.BaseParam;

import java.io.Serializable;

/**
 * <pre>
 * Explain: 测试param
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:47
 * Version: V1.0
 * </pre>
 */
public class TestParam extends BaseParam implements Serializable {

    private static final long serialVersionUID = -4031429484891672736L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
