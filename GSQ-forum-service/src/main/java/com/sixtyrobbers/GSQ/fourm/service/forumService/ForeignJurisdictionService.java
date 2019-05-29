package com.sixtyrobbers.GSQ.fourm.service.forumService;

import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.ForeignJurisdictionReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.BaseResult;

import java.util.List;

/**
 * @Description:    java类作用描述
 * @Author:         luoheng
 * @CreateDate:     2019/5/15 21:29
 * @Version:        1.0
 */
public interface ForeignJurisdictionService {

   /**
    * @Description:    更新用户对外权限
    * @Author:         luoheng
    * @CreateDate:     2019/5/15 21:31
    * @Version:        1.0
    */

   BaseResult updateForeignJurisdictionUser(ForeignJurisdictionReq foreignJurisdictionReq);
}
