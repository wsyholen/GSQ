package com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/5/7 14:25
 * Version: V1.0
 * </pre>
 */
@Data
public class JurisdictionDTO implements Serializable {

    private static final long serialVersionUID = 6686341008045107475L;

    /**
     * 菜单
     */
    private String menuName;

    /**
     * 菜单路径
     */
    private String menuUrl;

    /**
     * 节点代码
     */
    private String nodeNumer;

}
