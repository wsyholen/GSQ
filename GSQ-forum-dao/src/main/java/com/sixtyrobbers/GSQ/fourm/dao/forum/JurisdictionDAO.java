package com.sixtyrobbers.GSQ.fourm.dao.forum;

import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.MenuDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.RoleMenuDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.JurisdictionParam;

import java.util.List;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/5/7 14:28
 * Version: V1.0
 * </pre>
 */
public interface JurisdictionDAO {

    /**
     * <pre>
     * Explain: 权限查询
     * Author: holennnnnn_
     * Create_Time: 2019/5/7 14:28
     * Version: V1.0
     * </pre>
     */
    List<MenuDO> getJurisdictionByRoleId(JurisdictionParam jurisdictionParam);

    /**
     * <pre>
     * Explain: 删除角色权限
     * Author: holennnnnn_
     * Create_Time: 2019/5/7 15:41
     * Version: V1.0
     * </pre>
     */
    void deleteJurisdictionByRoleId(JurisdictionParam jurisdictionParam);

    /**
     * <pre>
     * Explain: 添加角色权限
     * Author: holennnnnn_
     * Create_Time: 2019/5/7 15:45
     * Version: V1.0
     * </pre>
     */
    void addJurisdiction(List<RoleMenuDO> roleMenuDOList);

}
