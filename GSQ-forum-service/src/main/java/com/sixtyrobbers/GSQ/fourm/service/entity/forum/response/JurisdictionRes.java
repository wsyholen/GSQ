package com.sixtyrobbers.GSQ.fourm.service.entity.forum.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/5/7 13:20
 * Version: V1.0
 * </pre>
 */
@Data
public class JurisdictionRes implements Serializable {

    private static final long serialVersionUID = 7935782589384326133L;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单地址
     */
    private String menuUrl;

    /**
     * 菜单节点
     */
    private String nodeNumer;

    private List<JurisdictionRes> jurisdictionResList;


}
