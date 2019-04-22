package com.sixtyrobbers.GSQ.fourm.service.entity.forum.request;

import com.sixtyrobbers.GSQ.fourm.service.entity.BaseRequest;

import java.io.Serializable;

/**
 * <pre>
 * Explain: 测试req
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:48
 * Version: V1.0
 * </pre>
 */
public class TestReq extends BaseRequest implements Serializable{

    private static final long serialVersionUID = 6825112721073891216L;

    /**
     *
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
