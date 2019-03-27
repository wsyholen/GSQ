package com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dto;

import com.sixtyrobbers.GSQ.fourm.dao.entity.BaseDO;

import java.io.Serializable;

/**
 * <pre>
 * Explain: 测试DTO
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:46
 * Version: V1.0
 * </pre>
 */
public class TestDTO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 517124241087319964L;

    private String name;

    private int id;

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
