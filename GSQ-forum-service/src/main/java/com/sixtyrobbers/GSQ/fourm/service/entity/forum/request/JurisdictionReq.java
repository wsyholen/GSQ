package com.sixtyrobbers.GSQ.fourm.service.entity.forum.request;

import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.MenuDO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/4/30 14:01
 * Version: V1.0
 * </pre>
 */
@Data
public class JurisdictionReq implements Serializable {

    private static final long serialVersionUID = 7947008987838388070L;

    /**
     * 角色id
     */
    private String roleId;

    private List<MenuDO> menuDOList;

}
