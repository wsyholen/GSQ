package com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo;

import com.sixtyrobbers.GSQ.fourm.dao.entity.BaseDO;

import java.io.Serializable;

/**
 * <pre>
 * Explain: 测试DO
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:46
 * Version: V1.0
 * </pre>
 */
public class TestDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = -2010737511321687L;

    private int id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
