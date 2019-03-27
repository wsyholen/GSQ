package com.sixtyrobbers.GSQ.fourm.service.entity.forum.response;

import com.sixtyrobbers.GSQ.fourm.service.entity.BaseResponse;

import java.io.Serializable;

/**
 * <pre>
 * Explain: 测试res
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:49
 * Version: V1.0
 * </pre>
 */
public class TestRes extends BaseResponse implements Serializable {

    private static final long serialVersionUID = -3379612945995740210L;

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
