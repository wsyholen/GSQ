package com.sixtyrobbers.GSQ.fourm.service.forumService;

import com.sixtyrobbers.GSQ.fourm.service.entity.BaseResult;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.JurisdictionReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.response.JurisdictionRes;

import java.util.List;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/5/7 13:19
 * Version: V1.0
 * </pre>
 */
public interface JurisdictionService {

    /**
     * <pre>
     * Explain:   权限查询
     * Author: holennnnnn_
     * Create_Time: 2019/5/7 13:24
     * Version: V1.0
     * </pre>
     */
    BaseResult getJurisdictionByRoleId(JurisdictionReq JurisdictionReq);

    /**
     * <pre>
     * Explain: 编辑权限
     * Author: holennnnnn_
     * Create_Time: 2019/5/7 15:38
     * Version: V1.0
     * </pre>
     */
    BaseResult updateJurisdictionByRoleId(JurisdictionReq JurisdictionReq);

}
