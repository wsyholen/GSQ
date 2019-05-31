package com.sixtyrobbers.GSQ.fourm.dao.forum;

import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.UserJurisdictionDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.ForeignJurisdictionParam;

/**
 * @Description:
 * @Author: luoheng
 * @CreateDate: 2019/5/15 21:08
 * @Version: 1.0
 */
public interface ForeignJurisdictionDAO {


    /**
     * @Description: 查找用户对外权限ID
     * @Author: luoheng
     * @CreateDate: 2019/5/15 21:08
     * @Version: 1.0
     */
    UserJurisdictionDO getUserId(ForeignJurisdictionParam foreignJurisdictionParam);


    /**
     * @Description: 更新用户对外权限
     * @Author: luoheng
     * @CreateDate: 2019/5/15 19:51
     * @Version: 1.0
     */
    void updateForeignJurisdictionUser(ForeignJurisdictionParam foreignJurisdictionParam);
}
